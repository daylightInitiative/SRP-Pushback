
package com.wehatemoddingmc.srppushback.init;

import com.wehatemoddingmc.srppushback.util.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

// default .cfg will be the MOD_ID
@Config(modid = Reference.MOD_ID, name = "Server Config", type = Config.Type.INSTANCE)
@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class InitServerConfig {

    // https://docs.minecraftforge.net/en/1.12.x/config/annotations/

    //@Config.Name(value = "Respawn Configuration")
    public static boolean enableRandomSpawns = true;

    public static int minRespawnRadiusX = 5;
    public static int minRespawnRadiusZ = 5;

    public static int maxRespawnRadiusX = 20;
    public static int maxRespawnRadiusZ = 20;

    /*
    * @(non-Javadoc)
    * Reloads the server config if a change is detected internally.
     */
    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Reference.MOD_ID)) {
            ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
            System.out.println(Reference.MOD_ID + "/SERVER: Reloaded server configs!");
        }
    }



}