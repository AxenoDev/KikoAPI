package fr.kikoplugins.kikoapi.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import fr.kikoplugins.kikoapi.utils.CommandUtils;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.command.CommandSender;

import static fr.kikoplugins.kikoapi.KikoAPI.LANG;

public class KikoAPICommandDebug {

    public static LiteralArgumentBuilder<CommandSourceStack> get() {
        return Commands.literal("debug")
                .requires(css -> CommandUtils.defaultRequirements(css, "kikoapi.command.kikoapi.debug"))
                .then(sendTestMessageCommand());
    }

    private static LiteralArgumentBuilder<CommandSourceStack> sendTestMessageCommand() {
        return Commands.literal("sendtestmessage")
                .requires(css -> CommandUtils.defaultRequirements(css, "kikoapi.command.kikoapi.debug.sendtestmessage"))
                .then(Commands.argument("key", StringArgumentType.word())
                        .executes(ctx -> {
                            CommandSender sender = CommandUtils.sender(ctx);
                            String key = ctx.getArgument("key", String.class);

                            LANG.sendMessage(sender, key);

                            return Command.SINGLE_SUCCESS;
                        })
                );
    }

}
