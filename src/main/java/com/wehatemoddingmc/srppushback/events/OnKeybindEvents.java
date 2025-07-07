package com.wehatemoddingmc.srppushback.events;

import com.wehatemoddingmc.srppushback.init.InitModKeybinds;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

@Mod.EventBusSubscriber(modid = "srppushback")
public class OnKeybindEvents
{
    // Register the key bindings during initialization.
    public static void registerKeybinds(FMLInitializationEvent event)
    {
        InitModKeybinds.registerKeyBindings(); // Register the keybindings
        MinecraftForge.EVENT_BUS.register(OnKeybindEvents.class); // Register event handler for key inputs
    }

    // Listen for key presses and handle them.
    @SubscribeEvent
    public static void onKeyPress(KeyInputEvent event)
    {
        // Check if the "P" key was pressed (structure key)
        if (InitModKeybinds.keyBindings[0].isPressed())
        {
            // Handle "P" key press event
            System.out.println("Structure Key Pressed!");
        }

        // Check if the "H" key was pressed (HUD key)
        if (InitModKeybinds.keyBindings[1].isPressed())
        {
            // Handle "H" key press event
            System.out.println("HUD Key Pressed!");
        }
    }
}