package com.wehatemoddingmc.srppushback.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

// itembase so we dont override the real item methods

public class ItemBase extends Item {
    public ItemBase(String registryName, CreativeTabs tab) {
        setRegistryName(registryName);               // internal ID (e.g., yourmod:example_item)
        setUnlocalizedName(registryName);            // translation key -> lang file
        setCreativeTab(tab != null ? tab : CreativeTabs.MISC);
    }
}