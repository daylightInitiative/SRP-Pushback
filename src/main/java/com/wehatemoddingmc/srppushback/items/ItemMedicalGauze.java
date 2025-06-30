package com.wehatemoddingmc.srppushback.items;

import com.wehatemoddingmc.srppushback.proxy.ClientProxy;
import com.wehatemoddingmc.srppushback.sound.SoundInstance;
import com.wehatemoddingmc.srppushback.util.Handlers.SoundsHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.wehatemoddingmc.srppushback.util.Reference.getNBT;

//import blusunrize.immersiveengineering.client.ClientUtils;

public class ItemMedicalGauze extends Item {

    // nbt shortcuts
    private final String ALREADY_USED = "used_gauze";
    private final String WRAP_PROGRESS = "wrapping_gauze";
    private final String IS_DISINFECTED = "disinfected";


    public ItemMedicalGauze() {
        super();
        setMaxStackSize(16);  // Set stack size limit to 16
        setCreativeTab(CreativeTabs.COMBAT);
    }

    public boolean isDisinfected(ItemStack stack) {
        NBTTagCompound nbt = getNBT(stack);
        return nbt.getBoolean(IS_DISINFECTED);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (world.isRemote && player.getHealth() < player.getMaxHealth()) {
            SoundInstance soundToRemove = ClientProxy.itemSounds.remove(player.getUniqueID());
            if (soundToRemove != null) soundToRemove.stopSound();

            SoundInstance sound = new SoundInstance(SoundsHandler.ITEM_WRAP_GAUZE);
            Minecraft.getMinecraft().getSoundHandler().playSound(sound);
            ClientProxy.itemSounds.put(player.getUniqueID(), sound);
        }
        player.setActiveHand(hand);
        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }


    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        if (slotChanged) return true;
        // If only your specific NBT tag changed, don't re-equip
        return !ItemStack.areItemsEqualIgnoreDurability(oldStack, newStack);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 80; // matches your target wrap progress
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE; // plays bow charge animation
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase user, int timeLeft) {
        if (world.isRemote && user instanceof EntityPlayer) {
            SoundInstance sound = ClientProxy.itemSounds.remove(user.getUniqueID());
            if (sound != null) sound.stopSound();
        }

        if (user instanceof EntityPlayer) {
            NBTTagCompound nbt = getNBT(stack);
            if (nbt.getInteger(WRAP_PROGRESS) < 100) {
                nbt.setInteger(WRAP_PROGRESS, 0);
            }
        }
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase entity, int count) {
        super.onUsingTick(stack, entity, count);

        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            NBTTagCompound nbt = getNBT(stack);
            if (!player.getHeldItemMainhand().equals(stack) || nbt.getBoolean(ALREADY_USED)) {
                nbt.setInteger(WRAP_PROGRESS, 0);
                return;
            }

            int charge = nbt.getInteger(WRAP_PROGRESS);
            if (charge < 100) {
                nbt.setInteger(WRAP_PROGRESS, charge + 1);
            }

            if (charge >= 100 && player.getHealth() < player.getMaxHealth()) {
                nbt.setBoolean(ALREADY_USED, true);
                if (!player.world.isRemote) {
                    player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 1));
                    stack.shrink(1);
                } else {
                    player.sendMessage(new TextComponentString("You applied the gauze."));
                }
            }
        }
    }


}