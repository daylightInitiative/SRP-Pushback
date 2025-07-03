package com.wehatemoddingmc.srppushback.init;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class InitConfigClient {

    public static Configuration config;

    // Client-side configuration options
    public static boolean enableFancyRendering = true;
    public static int maxParticleEffects = 100;

    public static void init(FMLPreInitializationEvent event) {
        // Create a new Configuration instance
        config = new Configuration(event.getSuggestedConfigurationFile());

        // Load the config
        syncConfig();
    }

    // Method to load and sync the config
    public static void syncConfig() {
        try {
            config.load(); // Load the config file

            // Get or create config properties
            enableFancyRendering = config.getBoolean("enableFancyRendering", "rendering", enableFancyRendering, "Enable fancy rendering effects");
            maxParticleEffects = config.getInt("maxParticleEffects", "rendering", maxParticleEffects, 0, 500, "Maximum number of particle effects");

            // Other properties...

            if (config.hasChanged()) {
                config.save(); // Save the config if it changed
            }

        } catch (Exception e) {
            // Handle exceptions (e.g., log an error)
            e.printStackTrace();
        }
    }

    // You can add a dynamic config reloading option or listener (if needed)
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        // Example to show how to dynamically adjust config settings during runtime
        if (event.phase == TickEvent.Phase.START) {
            // Sync or check config every tick if you need dynamic behavior
            if (config.hasChanged()) {
                syncConfig(); // Reload config on change
            }
        }
    }
}
