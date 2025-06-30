package com.wehatemoddingmc.srppushback.proxy;

import com.wehatemoddingmc.srppushback.sound.SoundInstance;
import com.wehatemoddingmc.srppushback.util.Reference;

import com.wehatemoddingmc.srppushback.init.InitModKeybinds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


// TODO: Auto-generated Javadoc
@EventBusSubscriber(value = Side.CLIENT, modid = Reference.MOD_ID)
public class ClientProxy extends CommonProxy {

    // create our sound map for our client
    public static final Map<UUID, SoundInstance> itemSounds = new HashMap<>();

    /* (non-Javadoc)
     * @see com.wehatemoddingmc.srppushback.proxy.CommonProxy#getPlayerEntityFromContext(net.minecraftforge.fml.common.network.simpleimpl.MessageContext)
     */
    @Override
    public EntityPlayer getPlayerEntityFromContext(MessageContext ctx)
    {
        // Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
        // your packets will not work because you will be getting a client
        // player even when you are on the server! Sounds absurd, but it's true.

        // Solution is to double-check side before returning the player:
        return (ctx.side.isClient() ? Minecraft.getMinecraft().player : ctx.getServerHandler().player);
    }
}