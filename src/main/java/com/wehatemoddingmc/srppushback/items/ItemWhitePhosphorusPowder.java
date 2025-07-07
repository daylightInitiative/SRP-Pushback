package com.wehatemoddingmc.srppushback.items;

import net.minecraft.creativetab.CreativeTabs;

public class ItemWhitePhosphorusPowder extends ItemBase {

    public ItemWhitePhosphorusPowder(String registryName, CreativeTabs tab) {
        super(registryName, tab);
        setMaxStackSize(64);  // Set stack size limit to 16
        setHasSubtypes(true);
        setMaxDamage(0);
    }


}
