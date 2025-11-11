package org.homanti.wacorp.item;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.homanti.wacorp.render.PhoneScreen;

public class PhoneItem extends Item {
    public PhoneItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide) {
            Minecraft minecraft = Minecraft.getInstance();

            minecraft.setScreen(new PhoneScreen(Component.translatable("screen.wacorp.phone")));
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
