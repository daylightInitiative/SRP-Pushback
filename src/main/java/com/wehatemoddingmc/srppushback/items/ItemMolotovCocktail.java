package com.wehatemoddingmc.srppushback.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMolotovCocktail extends ItemBase {
    private final String IS_THROWN = "isthrown";

    public ItemMolotovCocktail(String registryName, CreativeTabs tab) {
        super(registryName, tab);
        setMaxStackSize(16);  // Set stack size limit to 16
        setHasSubtypes(true);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entity) {

//        NBTTagCompound nbt = getNBT(stack);
//        if(nbt.getBoolean(IS_THROWN)) {
//           return stack;
//        }

        if(entity instanceof EntityPlayer) {

        }

        return stack;
    }
}
