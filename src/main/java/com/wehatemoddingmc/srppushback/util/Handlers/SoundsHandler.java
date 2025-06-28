package com.wehatemoddingmc.srppushback.util.Handlers;

import com.wehatemoddingmc.srppushback.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.HashMap;
import java.util.Random;

public class SoundsHandler {

    public static SoundEvent ITEM_WRAP_GAUZE;

    public static void registerSounds() {

        ITEM_WRAP_GAUZE = registerSound("items.wrap_gauze");

    }

    // Register a single sound
    public static SoundEvent registerSound(String path) {
        ResourceLocation location = new ResourceLocation(Reference.MOD_ID, path);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(path);
        ForgeRegistries.SOUND_EVENTS.register(event);
        System.out.println("Registered sound: " + location);  // Log to ensure registration
        return event;
    }

}
