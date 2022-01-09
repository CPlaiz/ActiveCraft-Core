package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.exceptions.InvalidArgumentException;
import de.silencio.activecraftcore.manager.WarnManager;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.ComparisonType;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WarnCommand extends ActiveCraftCommand {

    public WarnCommand() {
        super("warn");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {



        if (args.length >= 2) {
            if (Bukkit.getPlayer(args[1]) == null) {
                sender.sendMessage(Errors.INVALID_PLAYER());
                return false;
            }
            Player target = Bukkit.getPlayer(args[1]);

            WarnManager warnManager = new WarnManager(target);
            if (args[0].equalsIgnoreCase("add")) {
                StringBuilder stringBuilder = new StringBuilder();
                if(sender.hasPermission("activecraft.warn.add")) {
                    if (args.length >= 3) {
                        for (int i = 2; i < args.length; i++) {
                            if (!(i == 2)) stringBuilder.append(" ");
                            stringBuilder.append(args[i]);
                        }
                    } else stringBuilder.append(CommandMessages.DEFAULT_WARN_REASON());
                } else sender.sendMessage(Errors.NO_PERMISSION());

                String source = sender.getName();
                warnManager.add(stringBuilder.toString(), source);
                sender.sendMessage( CommandMessages.WARN_ADD(target, stringBuilder.toString()));
            } else if (args[0].equalsIgnoreCase("remove")) {
                StringBuilder stringBuilder = new StringBuilder();
                if(sender.hasPermission("activecraft.warn.remove")) {
                    if (args.length >= 3) {
                        for (int i = 2; i < args.length; i++) {
                            if (!(i == 2)) stringBuilder.append(" ");
                            stringBuilder.append(args[i]);
                        }
                        sender.sendMessage(CommandMessages.WARN_REMOVE(target, warnManager.getWarnEntry(stringBuilder.toString()).reason));
                        warnManager.remove(args[2]);
                    } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else if (args[0].equalsIgnoreCase("get")) {
                StringBuilder stringBuilder = new StringBuilder();
                if(sender.hasPermission("activecraft.warn.get")) {
                    if (args.length >= 3) {
                        for (int i = 2; i < args.length; i++) {
                            if (!(i == 2)) stringBuilder.append(" ");
                            stringBuilder.append(args[i]);
                        }
                        StringBuilder strBuilder = new StringBuilder();
                        strBuilder.append(CommandMessages.WARN_GET_HEADER(target) + "\n")
                                        .append(CommandMessages.WARN_GET(warnManager.getWarnEntry(stringBuilder.toString()).source,
                                                warnManager.getWarnEntry(stringBuilder.toString()).reason,
                                                warnManager.getWarnEntry(stringBuilder.toString()).created,
                                                warnManager.getWarnEntry(stringBuilder.toString()).id + ""));
                        sender.sendMessage(strBuilder.toString());
                    } else sender.sendMessage(Errors.INVALID_PLAYER());
                } else sender.sendMessage(Errors.NO_PERMISSION());
            }
        } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
        return true;
    }
}
