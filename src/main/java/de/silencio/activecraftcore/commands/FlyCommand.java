package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

                if(label.equalsIgnoreCase("fly")) {
                    if (args.length == 0) {
                        if (sender instanceof Player) {
                            if (sender.hasPermission("activecraft.fly")) {
                                Player player = (Player) sender;
                                FileConfig fileConfig = new FileConfig("playerdata" + File.separator + player.getName() + ".yml");

                                if (fileConfig.getBoolean("fly")) {
                                    player.setAllowFlight(false);
                                    player.sendMessage(ChatColor.GOLD + "Flight mode deactivated.");
                                    fileConfig.set("fly", false);
                                    fileConfig.saveConfig();
                                } else if (!fileConfig.getBoolean("fly")) {
                                    player.setAllowFlight(true);
                                    player.sendMessage(ChatColor.GOLD + "Flight mode activated.");
                                    fileConfig.set("fly", true);
                                    fileConfig.saveConfig();
                                }
                            } else sender.sendMessage(Errors.NO_PERMISSION);
                        } else sender.sendMessage(Errors.NOT_A_PLAYER);
                    } else if (args.length == 1) {
                        if (sender.hasPermission("activecraft.fly.others")) {
                            Player target = Bukkit.getPlayer(args[0]);
                            FileConfig fileConfig = new FileConfig("playerdata" + File.separator + target.getName() + ".yml");

                            if (fileConfig.getBoolean("fly")) {
                                target.setAllowFlight(false);
                                target.sendMessage(ChatColor.GOLD + "Flight mode deactivated by " + ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD + ".");
                                sender.sendMessage(ChatColor.GOLD + "Flight mode deactivated for " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + ".");
                                fileConfig.set("fly", false);
                                fileConfig.saveConfig();
                            } else if (!fileConfig.getBoolean("fly")) {
                                target.setAllowFlight(true);
                                target.sendMessage(ChatColor.GOLD + "Flight mode activated by " + ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD + ".");
                                sender.sendMessage(ChatColor.GOLD + "Flight mode activated for " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + ".");
                                fileConfig.set("fly", true);
                                fileConfig.saveConfig();
                            }
                        } else sender.sendMessage(Errors.NO_PERMISSION);
                    } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
                }

            if(label.equalsIgnoreCase("flyspeed")) {
                if(args.length == 1) {
                    if(sender instanceof Player) {
                        if (sender.hasPermission("activecraft.flyspeed")) {
                            Player player = (Player) sender;
                            FileConfig fileConfig = new FileConfig("playerdata" + File.separator + player.getName() + ".yml");
                            if(Integer.parseInt(args[0]) <= 10) {
                                player.setFlySpeed((float) Integer.parseInt(args[0])/10);
                                player.sendMessage(ChatColor.GOLD + "Fly speed set to " + ChatColor.AQUA + args[0] + ChatColor.GOLD + ".");
                                fileConfig.set("flyspeed", args[0]);
                                fileConfig.saveConfig();
                            } else sender.sendMessage(Errors.WARNING + "Given number is too large!");
                        } else sender.sendMessage(Errors.NO_PERMISSION);
                    } else sender.sendMessage(Errors.NOT_A_PLAYER);
                } else if(args.length == 2) {
                    if (sender.hasPermission("activecraft.flyspeed.other")) {
                        Player player = (Player) sender;
                        Player target = Bukkit.getPlayer(args[0]);
                        FileConfig fileConfig = new FileConfig("playerdata" + File.separator + target.getName() + ".yml");
                        if(Integer.parseInt(args[0]) <= 10) {
                            target.setFlySpeed((float) Integer.parseInt(args[0]) / 10);
                            target.sendMessage(ChatColor.GOLD + "Fly speed set to " + ChatColor.AQUA + args[0] + ChatColor.GOLD + " by " + ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD + ".");
                            sender.sendMessage(ChatColor.GOLD + "Fly speed set to " + ChatColor.AQUA + args[0] + ChatColor.GOLD + " for " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + ".");
                            fileConfig.set("flyspeed", args[0]);
                            fileConfig.saveConfig();
                        } else sender.sendMessage(Errors.WARNING + "Given number is too large!");
                    } else sender.sendMessage(Errors.NO_PERMISSION);
                } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
            }
        return true;
    }
}