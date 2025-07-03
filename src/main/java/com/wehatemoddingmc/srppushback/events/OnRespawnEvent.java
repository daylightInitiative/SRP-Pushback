package com.wehatemoddingmc.srppushback.events;

import com.wehatemoddingmc.srppushback.init.InitConfigServer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.Random;

public class OnRespawnEvent {

    /* (non-Javadoc)
     *  Return a Vector 3d pointing to the random spawn location within the radius
     */
    public static Vec3d getRandomSurfaceLevel(World world, int minX, int maxX, int minZ, int maxZ) {
        // Create a random generator
        Random rand = new Random();

        // Generate a random X and Z within the given bounds
        int randomX = rand.nextInt(maxX - minX + 1) + minX;
        int randomZ = rand.nextInt(maxZ - minZ + 1) + minZ;

        // Get the surface level at the random X, Z position
        int world_height = world.getHeight(randomX, randomZ);

        return new Vec3d(randomX, world_height, randomZ);
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (!event.player.world.isRemote) { // server-only
            EntityPlayerMP player = (EntityPlayerMP) event.player;
            boolean fromEnd = event.isEndConquered();
            World world = player.world;

            Vec3d randomPosition = getRandomSurfaceLevel(world, 5, 50, 5, 50);
            System.out.println("Random position: " + randomPosition.toString());

            player.setPosition(randomPosition.x, randomPosition.y, randomPosition.z);

            //player.inventory.addItemStackToInventory(new ItemStack(Items.COOKED_BEEF, 5));
            //player.sendMessage(new TextComponentString("Welcome back!" + (fromEnd ? " from The End!" : "")));

        }
    }
}
