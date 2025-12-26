package org.homanti.wacorp.item.devices;

import net.minecraft.world.item.Item;

public class SmartphoneItem extends Item {
    public SmartphoneItem(Properties properties) {
        super(properties);
    }

//    @Override
//    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
//        if (level.isClientSide) {
//            Minecraft minecraft = Minecraft.getInstance();
//
//            minecraft.setScreen(new SmartphoneScreen(Component.translatable("screen.wacorp.smartphone")));
//        }
//
//        return InteractionResultHolder.success(player.getItemInHand(hand));
//    }
}
