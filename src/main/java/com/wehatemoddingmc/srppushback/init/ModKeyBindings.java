package com.wehatemoddingmc.srppushback.init;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ModKeyBindings
{
    // Store key bindings in a static final array as they are constants.
    public static final KeyBinding[] keyBindings = new KeyBinding[2];

    /**
     * Register key bindings.
     */
    public static void registerKeyBindings()
    {
        // instantiate the key bindings
        keyBindings[0] = new KeyBinding("key.structure.desc", Keyboard.KEY_P, "key.srppushback.category");
        keyBindings[1] = new KeyBinding("key.hud.desc", Keyboard.KEY_H, "key.srppushback.category");

        // register all the key bindings
        for (KeyBinding keyBinding : keyBindings)
        {
            ClientRegistry.registerKeyBinding(keyBinding);
        }
    }
}