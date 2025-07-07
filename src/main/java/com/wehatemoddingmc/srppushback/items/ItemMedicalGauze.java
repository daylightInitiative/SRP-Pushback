package com.wehatemoddingmc.srppushback.items;

import com.wehatemoddingmc.srppushback.proxy.ClientProxy;
import com.wehatemoddingmc.srppushback.sound.SoundInstance;
import com.wehatemoddingmc.srppushback.util.Handlers.SoundsHandler;
import com.wehatemoddingmc.srppushback.util.Reference;
import net.minecraft.client.Minecraft;
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
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import static com.wehatemoddingmc.srppushback.util.Reference.getNBT;

public class ItemMedicalGauze extends ItemBase {

    // nbt shortcuts
    private final String ALREADY_USED = "used_gauze";
    private final String WRAP_PROGRESS = "wrapping_gauze";
    private final int IS_DISINFECTED = 1;
    private final int CHARGE_DELAY = 25;

    public ItemMedicalGauze(String registryName, CreativeTabs tab) {
        super(registryName, tab);
        setMaxStackSize(32);  // Set stack size limit to 16
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (stack.getMetadata() == IS_DISINFECTED) {

            if(player.getHealth() < player.getMaxHealth()) {
                player.setActiveHand(hand);  // begins charging
                if (world.isRemote) {  // play charge start sound
                    SoundInstance sound = new SoundInstance(SoundsHandler.ITEM_WRAP_GAUZE);
                    Minecraft.getMinecraft().getSoundHandler().playSound(sound);
                    ClientProxy.itemSounds.put(player.getUniqueID(), sound);
                }
            } else {
                player.sendStatusMessage(new TextComponentString("You are already healthy"), true);
            }
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }


    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        if (slotChanged) return true;
        // If only your specific NBT tag changed, don't re-equip
        return !ItemStack.areItemsEqualIgnoreDurability(oldStack, newStack);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return CHARGE_DELAY;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entity, ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase user) {

        if (!world.isRemote && user instanceof EntityPlayer && stack.getMetadata() == IS_DISINFECTED) {
            EntityPlayer player = (EntityPlayer) user;
            if (player.getHealth() < player.getMaxHealth()) {

                PotionEffect MedicalRegen = new PotionEffect(
                        MobEffects.REGENERATION, //Potion effect
                        800, //Duration (20 seconds)
                        2, // Amplifier
                        false, // isAmbient
                        false // show particles
                );

                player.addPotionEffect(MedicalRegen);
                stack.shrink(1);
                SoundInstance sound = ClientProxy.itemSounds.remove(player.getUniqueID());
                if (sound != null) sound.stopSound();
                //player.sendMessage(new TextComponentString("You applied the gauze."));
                player.sendStatusMessage(new TextComponentString("You applied the gauze"), true);
            }
        }
        return stack;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase user, int timeLeft) {
        // User let go before fully charged — stop sound

        if (world.isRemote && user instanceof EntityPlayer) {
            SoundInstance sound = ClientProxy.itemSounds.remove(user.getUniqueID());
            if (sound != null) sound.stopSound();
        }
    }
}