package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickAllCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("activecraft.kickall")) {
            if (!(args.length > 0)) {
                sender.sendMessage(CommandMessages.DEFAULT_KICKALL());

                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!player.hasPermission("activecraft.kickall.bypass")) {
                        player.kickPlayer(CommandMessages.DEFAULT_KICKALL_MESSAGE());
                    }
                }
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                for (String arg : args) {
                    stringBuilder.append(arg);
                    stringBuilder.append(" ");
                }
                sender.sendMessage(CommandMessages.CUSTOM_KICKALL(stringBuilder.toString()));

                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!player.hasPermission("activecraft.kickall.bypass")) {
                        player.kickPlayer(CommandMessages.CUSTOM_KICKALL_MESSAGE(stringBuilder.toString()));
                    }
                }
            }
        } else sender.sendMessage(Errors.NO_PERMISSION());
        return true;
    }
}