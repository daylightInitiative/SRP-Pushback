package com.wehatemoddingmc.srppushback.events;

import com.wehatemoddingmc.srppushback.init.InitServerConfig;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class OnRespawnEvent {
    private static class TeleportInfo {
        final EntityPlayerMP player;
        final Vec3d pos;
        final ChunkPos chunk;
        TeleportInfo(EntityPlayerMP p, Vec3d v) {
            player = p;
            pos = v;
            chunk = new ChunkPos((int)v.x >> 4, (int)v.z >> 4);
        }
    }

    private final List<TeleportInfo> respawnQueue = Collections.synchronizedList(new ArrayList<>());
    private final Map<ChunkPos, List<TeleportInfo>> chunkAwaiting = new HashMap<>();

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent ev) {
        if (ev.player.world.isRemote || !InitServerConfig.enableRandomSpawns) return;
        EntityPlayerMP p = (EntityPlayerMP) ev.player;
        World w = p.world;
        WorldInfo info = w.getWorldInfo();

        int dx = ThreadLocalRandom.current().nextInt(
                InitServerConfig.minRespawnRadiusX,
                InitServerConfig.maxRespawnRadiusX + 1);
        int dz = ThreadLocalRandom.current().nextInt(
                InitServerConfig.minRespawnRadiusZ,
                InitServerConfig.maxRespawnRadiusZ + 1);
        int x = info.getSpawnX() + dx;
        int z = info.getSpawnZ() + dz;
        int surfaceY = w.getHeight(x, z);
        Vec3d safePos = new Vec3d(x + 0.5, surfaceY + 6.0, z + 0.5);

        TeleportInfo ti = new TeleportInfo(p, safePos);
        respawnQueue.add(ti);

        // Force-chunk load & mark block to ensure server/client readiness
        w.getChunkFromChunkCoords(ti.chunk.x, ti.chunk.z);
        w.getBlockState(new BlockPos(x, surfaceY, z));  // cache block
        w.markBlockRangeForRenderUpdate(x, surfaceY, z, x, surfaceY, z);
    }

    @SubscribeEvent
    public void onChunkLoad(ChunkEvent.Load ev) {
        if (ev.getChunk() == null) return;
        ChunkPos cp = new ChunkPos(ev.getChunk().x, ev.getChunk().z);
        respawnQueue.removeIf(ti -> {
            if (ti.chunk.equals(cp)) {
                chunkAwaiting.computeIfAbsent(cp, k -> new ArrayList<>()).add(ti);
                return true;
            }
            return false;
        });
    }

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent ev) {
        if (ev.world.isRemote || ev.phase != TickEvent.Phase.START) return;

        for (Iterator<Map.Entry<ChunkPos, List<TeleportInfo>>> it = chunkAwaiting.entrySet().iterator(); it.hasNext();) {
            Map.Entry<ChunkPos, List<TeleportInfo>> entry = it.next();
            for (TeleportInfo ti : entry.getValue()) {
                // Delay teleport to next tick for safe loading
                ti.player.getServer().addScheduledTask(() -> {
                    ti.player.getServer().addScheduledTask(() -> {
                        ti.player.connection.setPlayerLocation(
                                ti.pos.x, ti.pos.y, ti.pos.z,
                                ti.player.rotationYaw, ti.player.rotationPitch
                        );
                        ti.player.motionX = 0;
                        ti.player.motionY = 0;
                        ti.player.motionZ = 0;
                        ti.player.fallDistance = 0;
                        ti.player.velocityChanged = true;
                    });
                });
            }
            it.remove();
        }
    }
}
