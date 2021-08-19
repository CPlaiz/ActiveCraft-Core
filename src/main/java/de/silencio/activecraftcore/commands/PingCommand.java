package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (sender.hasPermission("activecraft.ping")) {
                sender.sendMessage(ChatColor.GOLD + "Your ping: " + player.getPing());
        } else sender.sendMessage(Errors.NO_PERMISSION);
        return true;
    }
}