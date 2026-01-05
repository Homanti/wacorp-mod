package org.homanti.wacorp.item.devices;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.homanti.wacorp.render.SmartphoneScreen;

public class SmartphoneItem extends Item {
    public SmartphoneItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide) {
            Minecraft minecraft = Minecraft.getInstance();

            minecraft.setScreen(new SmartphoneScreen(Component.translatable("screen.wacorp.smartphone")));
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
