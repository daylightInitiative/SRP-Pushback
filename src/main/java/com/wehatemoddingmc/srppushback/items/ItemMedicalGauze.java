package com.wehatemoddingmc.srppushback.items;

import com.wehatemoddingmc.srppushback.util.Handlers.SoundsHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemMedicalGauze extends Item {

    public ItemMedicalGauze() {
        super();
        setMaxStackSize(16);  // Set stack size limit to 16
        setCreativeTab(CreativeTabs.COMBAT);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        BlockPos pos = player.getPosition();
        ItemStack stack = player.getHeldItem(hand);

        if (!world.isRemote) {

            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 1));
            stack.shrink(1);

        } else {
            player.sendMessage(new TextComponentString("You used a healing item!"));
            world.playSound(player.posX, player.posY, player.posZ, SoundsHandler.ITEM_WRAP_GAUZE, SoundCategory.AMBIENT, 1.0F, 1.0F, false);
        }

        return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
    }

}