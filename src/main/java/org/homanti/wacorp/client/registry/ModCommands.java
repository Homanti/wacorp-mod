//package org.homanti.wacorp.client.registry;
//
//import com.mojang.brigadier.CommandDispatcher;
//import com.mojang.brigadier.arguments.StringArgumentType;
//import net.minecraft.commands.CommandSourceStack;
//import net.minecraft.commands.Commands;
//import net.minecraft.network.chat.Component;
//import net.minecraftforge.event.RegisterCommandsEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//import org.homanti.wacorp.Wacorp;
//
//import static org.homanti.wacorp.render.SmartphoneScreen.setUrl;
//
//@Mod.EventBusSubscriber(modid = Wacorp.MODID)
//public class ModCommands {
//    @SubscribeEvent
//    public static void onRegisterCommands(RegisterCommandsEvent event) {
//        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
//
//        dispatcher.register(
//            Commands.literal("seturl")
//                .requires(source -> source.hasPermission(0))
//                .then(Commands.argument("url", StringArgumentType.greedyString())
//                    .executes(context -> {
//                        String newUrl = StringArgumentType.getString(context, "url");
//                        setUrl(newUrl);
//                        context.getSource().sendSuccess(
//                                () -> Component.literal("URL изменён на: " + newUrl),
//                                false
//                        );
//                        return 1;
//                    })
//                )
//        );
//    }
//}
