package com.wehatemoddingmc.srppushback.blocks;

import com.wehatemoddingmc.srppushback.Main;
import com.wehatemoddingmc.srppushback.init.InitModBlocks;
import com.wehatemoddingmc.srppushback.init.InitModItems;
import com.wehatemoddingmc.srppushback.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;


public class BlockBase extends Block {
    protected final String name;

    public BlockBase(Material material, String name, CreativeTabs tab) {
        super(material);
        this.name = name;
        setUnlocalizedName(name);
        setSoundType(SoundType.STONE);
        setRegistryName(Reference.MOD_ID, name);
        setCreativeTab(tab != null ? tab : CreativeTabs.MISC);
        setHardness(3.0f);
        setResistance(15.0f);
        setHarvestLevel("pickaxe", 1);
    }

    /** Creates the ItemBlock for this block with matching registry name */
    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

    @Override
    public BlockBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}