package com.bikerboys.olivestockcompat.util;

import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.natamus.barebackhorseriding_common_forge.config.ConfigHandler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;

import net.minecraft.world.item.DyeableArmorItem;

public class Event {

    public static void onPlayerTick(ServerLevel level, ServerPlayer player) {
        if (!player.isPassenger()) {
            return;
        }

        if (player.isCreative()) {
            return;
        }

        Entity vehicleEntity = player.getVehicle();
        if (!(vehicleEntity instanceof AbstractOMount)) {
            return;
        }

        OHorse OHorse = (OHorse)vehicleEntity;
        if (!OHorse.isTamed()) {
            return;
        }

        if (OHorse.getFlag(4)) {
            return;
        }

        boolean wearingLeatherPants = player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof DyeableArmorItem;

        if (ConfigHandler.shouldReceiveSlownessDuringRidingWithoutSaddle) {
            if (player.tickCount % 20 == 0) {
                if (ConfigHandler.leatherPantsNegateEffect && wearingLeatherPants) {
                    if (ConfigHandler.leatherPantsLoseDurabilityOnNegation) {
                        Util.itemHurtBreakAndEvent(player.getItemBySlot(EquipmentSlot.LEGS), player, null, 1);
                    }
                    return;
                }

                Util.giveSlowness(player);
            }
        }
        if (ConfigHandler.shouldDamageDuringRidingWithoutSaddle) {
            if (player.tickCount % ConfigHandler.ticksBetweenDamage == 0) {
                if (ConfigHandler.leatherPantsNegateEffect && wearingLeatherPants) {
                    if (ConfigHandler.leatherPantsLoseDurabilityOnNegation) {
                        Util.itemHurtBreakAndEvent(player.getItemBySlot(EquipmentSlot.LEGS), player, null, 1);
                    }
                    return;
                }

                Util.damagePlayer(player, ConfigHandler.halfHeartDamageAmount);
            }
        }
    }

}
