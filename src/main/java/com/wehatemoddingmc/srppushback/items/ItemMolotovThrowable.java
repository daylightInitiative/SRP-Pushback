package com.wehatemoddingmc.srppushback.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import static com.wehatemoddingmc.srppushback.util.Reference.getNBT;

public class ItemMolotovThrowable extends ItemBase {
    private final String IS_THROWN = "isthrown";

    public ItemMolotovThrowable(String registryName, CreativeTabs tab) {
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
