package com.wehatemoddingmc.srppushback.events;

import com.wehatemoddingmc.srppushback.util.Reference;

import com.wehatemoddingmc.srppushback.init.ModKeyBindings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
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

@Mod.EventBusSubscriber(modid = "srppushback")
public class KeybindEvents
{
    // Register the key bindings during initialization.
    public static void onClientSetup(FMLInitializationEvent event)
    {
        ModKeyBindings.registerKeyBindings(); // Register the keybindings
        MinecraftForge.EVENT_BUS.register(KeybindEvents.class); // Register event handler for key inputs
    }

    // Listen for key presses and handle them.
    @SubscribeEvent
    public static void onKeyPress(KeyInputEvent event)
    {
        // Check if the "P" key was pressed (structure key)
        if (ModKeyBindings.keyBindings[0].isPressed())
        {
            // Handle "P" key press event
            System.out.println("Structure Key Pressed!");
        }

        // Check if the "H" key was pressed (HUD key)
        if (ModKeyBindings.keyBindings[1].isPressed())
        {
            // Handle "H" key press event
            System.out.println("HUD Key Pressed!");
        }
    }
}