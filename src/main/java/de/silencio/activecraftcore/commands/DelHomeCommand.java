package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class DelHomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            FileConfig homeconfig = new FileConfig("homes.yml");

            if(sender.hasPermission("activecraft.delhome.self")) {
                if(args.length == 1) {
                    Player player = (Player) sender;
                    String playerName = player.getName();

                    if (args[0].equalsIgnoreCase("home_list")) {
                        sender.sendMessage(Errors.INVALID_ARGUMENTS);
                        return false;
                    }

                    if (homeconfig.contains(playerName + "." + args[0].toLowerCase())) {
                        List<String> homeList = homeconfig.getStringList(playerName + ".home_list");
                        homeList.remove(args[0]);
                        homeconfig.set(playerName + ".home_list", homeList);
                        homeconfig.set(playerName + "." + args[0].toLowerCase(), null);
                        homeconfig.saveConfig();
                        sender.sendMessage(ChatColor.GOLD + "Home " + ChatColor.AQUA + args[0] + ChatColor.GOLD + " deleted.");
                    } else sender.sendMessage(Errors.WARNING + "You do not have a home called " + ChatColor.AQUA + args[0] + ChatColor.GRAY + ".");
                }
            } else sender.sendMessage(Errors.NO_PERMISSION);

            if(args.length == 2) {
                if (sender.hasPermission("activecraft.delhome.others")) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Errors.INVALID_PLAYER);
                        return false;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                        if (!sender.hasPermission("activecraft.delhome.self")) {
                            sender.sendMessage(Errors.CANNOT_TARGET_SELF);
                            return false;
                        }
                    }
                    String targetName = target.getName();

                    if (args[1].equalsIgnoreCase("home_list")) {
                        sender.sendMessage(Errors.INVALID_ARGUMENTS);
                        return false;
                    }

                    if(homeconfig.contains(targetName + "." + args[1].toLowerCase())) {
                        List<String> homeList = homeconfig.getStringList(targetName + ".home_list");
                        homeList.remove(args[0]);
                        homeconfig.set(targetName + ".home_list", homeList);
                        homeconfig.set(targetName + "." + args[1].toLowerCase(), null);
                        homeconfig.saveConfig();
                    } else sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " does not have a home called " + ChatColor.AQUA + args[1] + ChatColor.GOLD + ".");

                    sender.sendMessage(ChatColor.GOLD + "Deleted the home " + ChatColor.AQUA + args[1] + ChatColor.GOLD + " of player " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + ".");
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }
            if(args.length >= 3) {
                sender.sendMessage(Errors.TOO_MANY_ARGUMENTS);
            }
            if(args.length == 0) {
                sender.sendMessage(Errors.INVALID_ARGUMENTS);
            }
        return true;
    }
}