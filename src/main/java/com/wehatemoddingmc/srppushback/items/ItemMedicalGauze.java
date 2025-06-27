package com.wehatemoddingmc.srppushback.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemMedicalGauze extends Item {
    public ItemMedicalGauze() {
        super();
        setMaxStackSize(16);  // Set stack size limit to 16
        setCreativeTab(CreativeTabs.COMBAT);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        // You can add some functionality here, like healing the player
        System.out.println("Right click!");
        return super.onItemRightClick(world, player, hand);
    }
}
