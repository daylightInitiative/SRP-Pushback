package com.wehatemoddingmc.srppushback.init;

import java.util.ArrayList;

import com.wehatemoddingmc.srppushback.blocks.BlockBase;
//import com.wehatemoddingmc.srppushback.blocks.BlockCompressedCobblestone;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class InitModBlocks {
    public static final ArrayList<BlockBase> BLOCKS = new ArrayList<BlockBase>();
    //public static final BlockCompressedCobblestone compressedCobblestone = new BlockCompressedCobblestone("compressed_cobblestone");
    // upon instantiation it's added to the blocks arraylist
    // TODO: weigh if we should add this functionality to the items

    public static void register(IForgeRegistry<Block> registry) {
        for(BlockBase block : BLOCKS) {
            registry.register(block);
        }
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        for(BlockBase block : BLOCKS) {
            registry.register(block.createItemBlock());
        }
    }

    public static void registerModels() {
        for(BlockBase block : BLOCKS) {
            block.registerItemModel(Item.getItemFromBlock(block));
        }
    }
}