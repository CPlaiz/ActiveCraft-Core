package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {

            FileConfiguration homeconfig = new FileConfig("homes.yml");

            if (args.length == 1) {
                if (sender.hasPermission("activecraft.home.self")) {
                    Player player = (Player) sender;
                    String playerName = player.getName();

                    if (args[0].equalsIgnoreCase("home_list")) {
                        sender.sendMessage(Errors.INVALID_ARGUMENTS);
                        return false;
                    }
                    if (homeconfig.contains(playerName + "." + args[0].toLowerCase())) {
                        player.teleport(homeconfig.getLocation(playerName + "." + args[0].toLowerCase()));
                        sender.sendMessage(ChatColor.GOLD + "Teleported to " + ChatColor.AQUA + args[0] + ChatColor.GOLD + ".");
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);

                    } else sender.sendMessage(Errors.WARNING + "This home is not set!");
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }

            if (args.length == 2) {
                if (sender.hasPermission("activecraft.home.others")) {
                    Player player = (Player) sender;
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Errors.INVALID_PLAYER);
                        return false;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if (sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                        if (!sender.hasPermission("activecraft.home.self")) {
                            sender.sendMessage(Errors.CANNOT_TARGET_SELF);
                            return false;
                        }
                    }
                    String targetName = target.getName();

                    if (args[1].equalsIgnoreCase("home_list")) {
                        sender.sendMessage(Errors.INVALID_ARGUMENTS);
                        return false;
                    }

                    if (homeconfig.contains(targetName + "." + args[1].toLowerCase())) {

                        player.teleport(homeconfig.getLocation(targetName + "." + args[1].toLowerCase()));
                        sender.sendMessage(ChatColor.GOLD + "Teleported to " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + "'s home " + ChatColor.AQUA + args[1] + ChatColor.GOLD + ".");
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);

                    } else
                        sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " does not have a home called " + ChatColor.AQUA + args[1] + ChatColor.GOLD + ".");
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }
            if (args.length >= 3) {
                sender.sendMessage(Errors.TOO_MANY_ARGUMENTS);
            }
            if (args.length == 0) {
                sender.sendMessage(Errors.INVALID_ARGUMENTS);
            }
        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}