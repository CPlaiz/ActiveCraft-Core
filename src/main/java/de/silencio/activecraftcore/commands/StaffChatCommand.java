package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand implements CommandExecutor {

    String message = "";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {

            for (int i = 0; i < args.length; i++) {
                message = message + args[i] + " ";
            }
                Bukkit.broadcast(ChatColor.GOLD + "[StaffChat] " + ChatColor.AQUA + ((Player) sender).getName() + ChatColor.RESET + ": " + message, "activecraft.staffchat");
            message = "";

        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
