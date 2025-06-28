
package com.wehatemoddingmc.srppushback.init;

import com.wehatemoddingmc.srppushback.util.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;


// default .cfg will be the MOD_ID
@Config(modid = Reference.MOD_ID)
public class InitModConfig {

    public static SubCategory subcat = new SubCategory();

    public boolean enabledRandomSpawns = false;

    private static class SubCategory {
        public boolean someBool = false;
        public int relatedInt = 0;
    }

}