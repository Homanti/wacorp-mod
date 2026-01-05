package org.homanti.wacorp.client.registry;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.homanti.wacorp.Wacorp;

import static org.homanti.wacorp.render.SmartphoneScreen.setUrl;

@Mod.EventBusSubscriber(modid = Wacorp.MODID)
public class ModCommands {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        registerSetUrlCommand(event.getDispatcher());
    }

    private static void registerSetUrlCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("seturl")
                        .requires(source -> source.hasPermission(0))
                        .then(Commands.argument("url", StringArgumentType.greedyString())
                                .executes(context -> executeSetUrl(context, StringArgumentType.getString(context, "url")))
                        )
        );
    }

    private static int executeSetUrl(CommandContext<CommandSourceStack> context, String newUrl) {
        setUrl(newUrl);
        context.getSource().sendSuccess(
                () -> Component.literal("URL изменён на: " + newUrl),
                false
        );
        return 1;
    }
}