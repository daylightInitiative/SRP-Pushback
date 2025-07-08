package com.wehatemoddingmc.srppushback.util.Handlers;

import com.wehatemoddingmc.srppushback.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTableHandler {

    public static ResourceLocation registerLootTable(String resourcePath) {
        return LootTableList.register(new ResourceLocation(Reference.MOD_ID, resourcePath));
    }

    public static void init() {

        System.out.println("Registering loot tables...");

        registerLootTable("blocks/phosphorus_ore");

    }

}
