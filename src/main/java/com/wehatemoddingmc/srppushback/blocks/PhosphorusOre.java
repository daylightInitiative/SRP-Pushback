package com.wehatemoddingmc.srppushback.blocks;

import com.wehatemoddingmc.srppushback.blocks.BlockBase;
import com.wehatemoddingmc.srppushback.init.InitModItems;
import com.wehatemoddingmc.srppushback.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.util.Random;

public class PhosphorusOre extends BlockBase {

    /** Creating the Base of a new Mod Block */
    public PhosphorusOre(String registryName, CreativeTabs tab) {
        super(Material.ROCK, registryName, tab);
        setHardness(3.0f);
        setResistance(5.0f);
    }

    @Override
    public PhosphorusOre setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}