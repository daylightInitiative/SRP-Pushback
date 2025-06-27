package com.wehatemoddingmc.srppushback.proxy;

import com.wehatemoddingmc.srppushback.util.Reference;

import com.wehatemoddingmc.srppushback.init.ModKeyBindings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;


// TODO: Auto-generated Javadoc
@EventBusSubscriber(value = Side.CLIENT, modid = Reference.MOD_ID)
public class ClientProxy extends CommonProxy {

    /* (non-Javadoc)
     * @see com.wehatemoddingmc.srppushback.proxy.IProxy#getPlayerEntityFromContext(net.minecraftforge.fml.common.network.simpleimpl.MessageContext)
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


    /**
     * On event.
     *
     * @param event the event
     */
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public static void onEvent(KeyInputEvent event)
    {
//        // DEBUG
//        System.out.println("Key Input Event");

        // make local copy of key binding array
        KeyBinding[] keyBindings = ModKeyBindings.keyBindings;

        // check each enumerated key binding type for pressed and take appropriate action
        if (keyBindings[0].isPressed())
        {
            // DEBUG
            System.out.println("Key binding ="+keyBindings[0].getKeyDescription());

            // do stuff for this key binding here
            // remember you may need to send packet to server
        }
        if (keyBindings[1].isPressed())
        {
            // DEBUG
            System.out.println("Key binding ="+keyBindings[1].getKeyDescription());

            // do stuff for this key binding here
            // remember you may need to send packet to server
        }
    }
}