package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhereAmICommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if (sender.hasPermission("activecraft.whereami")) {
                    sender.sendMessage(ChatColor.GOLD + "You are in " + ChatColor.AQUA + player.getWorld().getName() + ChatColor.GOLD + " at x" + ChatColor.AQUA + player.getLocation().getBlockX() + ChatColor.GOLD + " y" + ChatColor.AQUA + player.getLocation().getBlockY() + ChatColor.GOLD + " z" + ChatColor.AQUA + player.getLocation().getBlockZ());
                } else sender.sendMessage(Errors.NO_PERMISSION);
            } else sender.sendMessage(Errors.NOT_A_PLAYER);
        } else if(args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if(target != null) {
                if (sender.hasPermission("activecraft.whereami.other")) {
                    sender.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " is in " + ChatColor.AQUA + target.getWorld().getName() + ChatColor.GOLD + " at x" + ChatColor.AQUA + target.getLocation().getBlockX() + ChatColor.GOLD + " y" + ChatColor.AQUA + target.getLocation().getBlockY() + ChatColor.GOLD + " z" + ChatColor.AQUA + target.getLocation().getBlockZ());
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }
        } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
        return true;
    }
}