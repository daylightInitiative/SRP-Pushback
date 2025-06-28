package com.wehatemoddingmc.srppushback.util.Handlers;

import com.wehatemoddingmc.srppushback.Main;
import com.wehatemoddingmc.srppushback.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {

    public static void registerEntities() {

        // no entities yet
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2) {

        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, Main.instance, range, 1, true, color1, color2);

    }
}
