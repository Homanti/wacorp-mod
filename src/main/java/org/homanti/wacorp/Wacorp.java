package org.homanti.wacorp;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
//import org.homanti.wacorp.render.SmartphoneScreen;
//import org.homanti.wacorp.client.registry.ModCommands;
import org.homanti.wacorp.client.registry.ModCreativeTabs;
import org.homanti.wacorp.client.registry.ModItems;

@Mod(Wacorp.MODID)
public class Wacorp {
    public static final String MODID = "wacorp";

    public Wacorp() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
//        MinecraftForge.EVENT_BUS.register(new ModCommands());
    }

    @Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onClientLogout(ClientPlayerNetworkEvent.LoggingOut event) {
//            SmartphoneScreen.handleWorldUnload();
        }
    }
}