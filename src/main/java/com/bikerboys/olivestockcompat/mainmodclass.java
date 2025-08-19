package com.bikerboys.olivestockcompat;

import com.bikerboys.olivestockcompat.util.Event;
import com.mojang.logging.LogUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;



// The value here should match an entry in the META-INF/mods.toml file
@Mod(mainmodclass.MODID)
public class mainmodclass
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "olivestockcompat";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public mainmodclass(FMLJavaModLoadingContext context)
    {


        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);


    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {


    }

    // Add the example block item to the building blocks tab

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent e) {
        Player player = e.player;
        Level level = player.level();
        if (level.isClientSide || !e.phase.equals(TickEvent.Phase.START)) {
            return;
        }

        Event.onPlayerTick((ServerLevel)level, (ServerPlayer)player);
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {

    }
}
