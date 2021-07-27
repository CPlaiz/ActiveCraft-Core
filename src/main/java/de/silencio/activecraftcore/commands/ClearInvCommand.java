package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearInvCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("activecraft.clearinv")) {

            if(args.length == 0) {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.GOLD + "Cleared your inventory.");
                player.getInventory().clear();
            }

            if(args.length == 1) {
                Player player = (Player) sender;
                Player target = Bukkit.getPlayer(args[0]);
                player.sendMessage(ChatColor.GOLD + "Cleared " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + "'s inventory.");
                target.sendMessage(ChatColor.GOLD + "Your inventory was cleared by " + ChatColor.AQUA + player.getDisplayName());
                target.getInventory().clear();
            }

            if(args.length > 1) {
                sender.sendMessage(Errors.TOO_MANY_ARGUMENTS);
            }

            return true;
        } else sender.sendMessage(Errors.NO_PERMISSION);
        return true;
    }
}