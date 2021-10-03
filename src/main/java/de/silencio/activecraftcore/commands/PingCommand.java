package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if (sender.hasPermission("activecraft.ping")) {
                sender.sendMessage(CommandMessages.PING_PLAYER(player.getPing() + ""));
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else sender.sendMessage(CommandMessages.PING_CONSOLE());
        return true;
    }
}