package com.wehatemoddingmc.srppushback.init;

import java.util.ArrayList;

import com.wehatemoddingmc.srppushback.blocks.BlockBase;
//import com.wehatemoddingmc.srppushback.blocks.BlockCompressedCobblestone;

import com.wehatemoddingmc.srppushback.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.wehatemoddingmc.srppushback.blocks.PhosphorusOre;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class InitModBlocks {
    public static PhosphorusOre PHOSPHORUS_ORE = new PhosphorusOre("phosphorus_ore", CreativeTabs.MATERIALS);

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(PHOSPHORUS_ORE);
    }

}