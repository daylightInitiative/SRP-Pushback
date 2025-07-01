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
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.wehatemoddingmc.srppushback.util.Reference.getNBT;

public class ItemMedicalGauze extends Item {

    // nbt shortcuts
    private final String ALREADY_USED = "used_gauze";
    private final String WRAP_PROGRESS = "wrapping_gauze";
    private final int IS_DISINFECTED = 1;
    private final int CHARGE_DELAY = 25;


    public ItemMedicalGauze() {
        super();
        setMaxStackSize(16);  // Set stack size limit to 16
        setCreativeTab(CreativeTabs.COMBAT);
        setHasSubtypes(true);
        setMaxDamage(0);
    }

//    public boolean isDisinfected(ItemStack stack) {
//        NBTTagCompound nbt = getNBT(stack);
//        return nbt.getBoolean(IS_DISINFECTED);
//    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (stack.getMetadata() == IS_DISINFECTED && player.getHealth() < player.getMaxHealth()) {
            player.setActiveHand(hand);  // begins charging
            if (world.isRemote) {  // play charge start sound
                SoundInstance sound = new SoundInstance(SoundsHandler.ITEM_WRAP_GAUZE);
                Minecraft.getMinecraft().getSoundHandler().playSound(sound);
                ClientProxy.itemSounds.put(player.getUniqueID(), sound);
            }
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        }
        return super.onItemRightClick(world, player, hand);
    }


    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        if (slotChanged) return true;
        // If only your specific NBT tag changed, don't re-equip
        return !ItemStack.areItemsEqualIgnoreDurability(oldStack, newStack);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return CHARGE_DELAY; // matches your target wrap progress
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE; // plays bow charge animation
    }



    @Override
    public boolean onEntitySwing(EntityLivingBase entity, ItemStack stack) {
        return true; // Prevents animation
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase user) {
        if (!world.isRemote && user instanceof EntityPlayer && stack.getMetadata() == IS_DISINFECTED) {
            EntityPlayer player = (EntityPlayer) user;
            if (player.getHealth() < player.getMaxHealth()) {
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 1));
                stack.shrink(1);
                SoundInstance sound = ClientProxy.itemSounds.remove(player.getUniqueID());
                if (sound != null) sound.stopSound();
                player.sendMessage(new TextComponentString("You applied the gauze."));
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
            ((EntityPlayer) user).sendMessage(new TextComponentString("Charging canceled"));
        }
    }
}