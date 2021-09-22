package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            Location lastLoc = Main.getPlugin().getLastLocationForPlayer(player);
            if(args.length == 0) {
                if (sender.hasPermission("activecraft.back.self")) {

                    if (lastLoc != null) {
                        player.teleport(lastLoc);
                        sender.sendMessage(ChatColor.GOLD + "Teleported to last location.");
                    } else sender.sendMessage(Errors.WARNING() + "No location to return to!");

                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else if(args.length >= 2) sender.sendMessage(Errors.TOO_MANY_ARGUMENTS());
        } else sender.sendMessage(Errors.NOT_A_PLAYER());

        if(args.length == 1) {
            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(Errors.INVALID_PLAYER());
                return false;
            }
            Player target = Bukkit.getPlayer(args[0]);
            Location lastLoc = Main.getPlugin().getLastLocationForPlayer(target);
            if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                return false;
            }
            if(sender.hasPermission("activecraft.back.others")) {

                if (lastLoc != null) {
                    target.teleport(lastLoc);
                    sender.sendMessage(ChatColor.GOLD + "Teleported " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " to their last location.");
                } else sender.sendMessage(Errors.WARNING() + "No location to return " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " to!");

            } else sender.sendMessage(Errors.NO_PERMISSION());
        }
        return true;
    }
}