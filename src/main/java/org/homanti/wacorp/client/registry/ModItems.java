package org.homanti.wacorp.client.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.homanti.wacorp.item.devices.SmartphoneItem;
import org.homanti.wacorp.item.wacoins.WacoinItem;

import static org.homanti.wacorp.Wacorp.MODID;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> SMARTPHONE = ITEMS.register("smartphone", () -> new SmartphoneItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> WACOINS_10 = ITEMS.register("10_wacoins", () -> new WacoinItem(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> WACOINS_50 = ITEMS.register("50_wacoins", () -> new WacoinItem(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> WACOINS_100 = ITEMS.register("100_wacoins", () -> new WacoinItem(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> WACOINS_500 = ITEMS.register("500_wacoins", () -> new WacoinItem(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> WACOINS_1000 = ITEMS.register("1000_wacoins", () -> new WacoinItem(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> WACOINS_5000 = ITEMS.register("5000_wacoins", () -> new WacoinItem(new Item.Properties().stacksTo(64)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
