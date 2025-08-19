package com.bikerboys.olivestockcompat.util;

import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Util {




    public static void giveSlowness(Player player) {
        player.addEffect(new MobEffectInstance(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 0)));

        Entity vehicle = player.getVehicle();
        if (vehicle instanceof OHorse) {
            OHorse horse = (OHorse)vehicle;

            horse.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 0));
        }
    }

    public static void damagePlayer(Player player, int halfheartdamage) {
        Level level = player.level();
        float newhealth = player.getHealth() - (float)halfheartdamage;
        if (newhealth > 0f) {
            player.hurt(level.damageSources().generic(), 0.1F);
            player.setHealth(newhealth);
        }
        else {
            player.hurt(level.damageSources().generic(), Float.MAX_VALUE);
        }
    }

    public static void itemHurtBreakAndEvent(ItemStack itemStack, ServerPlayer player, InteractionHand hand, int damage) {
        if (!player.getAbilities().instabuild && itemStack.isDamageableItem() && itemStack.hurt(damage, player.getRandom(), player)) {
            Item item = itemStack.getItem();
            itemStack.shrink(1);
            itemStack.setDamageValue(0);
            player.awardStat(Stats.ITEM_BROKEN.get(item));
        }

    }
}
