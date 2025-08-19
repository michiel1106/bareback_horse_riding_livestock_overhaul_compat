package com.bikerboys.olivestockcompat.mixin;


import com.dragn0007.dragnlivestock.entities.horse.OHorse;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractOMount.class)
public abstract class OhorseMixin {


    @Inject(method = "isSaddled", at = @At("RETURN"), cancellable = true)
    private void SaddleStuff(CallbackInfoReturnable<Boolean> cir) {


        AbstractOMount abstractHorse = (AbstractOMount)(Object)this;
        if (abstractHorse instanceof OHorse horse) {

            if (horse.isTamed() && horse.isVehicle() && horse.getPassengers().size() > 0) {
                cir.setReturnValue(true);
            }
        }
    }


}
