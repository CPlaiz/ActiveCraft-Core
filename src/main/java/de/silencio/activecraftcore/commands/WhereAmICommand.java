package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
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
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (sender.hasPermission("activecraft.whereami.self")) {
                    sender.sendMessage(CommandMessages.WHEREAMI(  ChatColor.GOLD + "x" + ChatColor.AQUA + player.getLocation().getBlockX() + ChatColor.GOLD
                            + " y" + ChatColor.AQUA + player.getLocation().getBlockY() + ChatColor.GOLD +
                            " z" + ChatColor.AQUA + player.getLocation().getBlockZ(), player.getWorld().getName()));
                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.NOT_A_PLAYER());
        } else if (args.length == 1) {
            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(Errors.INVALID_PLAYER());
                return false;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                if (sender.hasPermission("activecraft.whereami.others")) {
                    if (sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                        if (!sender.hasPermission("activecraft.whereami.self")) {
                            sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                            return false;
                        }
                    }

                    sender.sendMessage(CommandMessages.WHEREAMI_OTHERS(target,
                              ChatColor.GOLD + "x" + ChatColor.AQUA + target.getLocation().getBlockX() + ChatColor.GOLD
                                    + " y" + ChatColor.AQUA + target.getLocation().getBlockY() + ChatColor.GOLD +
                                    " z" + ChatColor.AQUA + target.getLocation().getBlockZ(), target.getWorld().getName()));
                } else sender.sendMessage(Errors.NO_PERMISSION());
            }
        } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
        return true;
    }
}