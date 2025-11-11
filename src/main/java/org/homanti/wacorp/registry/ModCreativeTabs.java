package org.homanti.wacorp.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.homanti.wacorp.Wacorp;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Wacorp.MODID);

    public static final RegistryObject<CreativeModeTab> WACORP_TAB = TABS.register("wacorp_tab",
        () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.PHONE.get()))
            .title(Component.literal("WacoRP"))
            .displayItems((params, output) -> {
                output.accept(ModItems.PHONE.get());
            })
            .build()
    );

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}
