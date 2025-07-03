
package com.wehatemoddingmc.srppushback.init;

import com.wehatemoddingmc.srppushback.util.Reference;
import net.minecraftforge.common.config.Config;

// default .cfg will be the MOD_ID
@Config(modid = Reference.MOD_ID, name = "srppushback-server", type = Config.Type.INSTANCE)
public class InitConfigServer {

    // https://docs.minecraftforge.net/en/1.12.x/config/annotations/

    //@Config.Name(value = "Respawn Configuration")
    public static SubCategory RespawnConfig = new SubCategory();

    private static class SubCategory {
        public boolean enableRandomSpawns = true;

        public int minRespawnRadius = 5;
        public int maxRespawnRadius = 100;
    }





}