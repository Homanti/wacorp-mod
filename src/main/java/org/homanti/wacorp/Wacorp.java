package org.homanti.wacorp;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.homanti.wacorp.render.PhoneScreen;
import org.homanti.wacorp.registry.ModCommands;
import org.homanti.wacorp.registry.ModCreativeTabs;
import org.homanti.wacorp.registry.ModItems;

@Mod(Wacorp.MODID)
public class Wacorp {
    public static final String MODID = "wacorp";
    public static String url = "https://www.google.com";

    public Wacorp() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ModCommands());
    }

    @Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onClientLogout(ClientPlayerNetworkEvent.LoggingOut event) {
            PhoneScreen.handleWorldUnload();
        }
    }
}