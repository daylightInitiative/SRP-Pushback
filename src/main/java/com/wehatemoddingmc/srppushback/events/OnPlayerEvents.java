package com.wehatemoddingmc.srppushback.events;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Collection;

@Mod.EventBusSubscriber(modid = "srppushback")
public class OnPlayerEvents {

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        DamageSource source = event.getSource();
        float amount = event.getAmount();

//        // Example: prevent all fall damage
//        if (source == DamageSource.FALL) {
//            event.setCanceled(true);
//        }

        // when player is damaged we remove the regeneration effect
        if(entity instanceof EntityPlayer) {

            EntityPlayerMP player = (EntityPlayerMP)entity;
            World world = player.world;

            if(!world.isRemote) {
                for (PotionEffect effect : player.getActivePotionEffects()) {
                    if(effect.getEffectName().equals("regeneration")) {
                        player.removeActivePotionEffect(effect.getPotion());
                    }
                    //player.removePotionEffect(effect.getType());
                }
            }
        }


        // Example: halve all incoming damage
        // event.setAmount(amount * 0.5f);
    }
}
