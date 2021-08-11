package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Calendar;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            VanishManager vanishManager = Main.getVanishManager();
            Player p = (Player) sender;

            FileConfig fileConfig = new FileConfig("config.yml");
            String joinFormat = fileConfig.getString("join-format");
            String quitFormat = fileConfig.getString("quit-format");

            if (args.length == 1) {
                if (sender.hasPermission("activecraft.vanish.other")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        FileConfig playerdataconfig = new FileConfig("playerdata" + File.separator + target.getName() + ".yml");
                        if (vanishManager.isVanished(target)) {
                            vanishManager.setVanished(target, false);
                            target.sendMessage(ChatColor.GOLD + "You are now " + ChatColor.AQUA + "visible.");
                            sender.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " is now " + ChatColor.AQUA + "visible.");
                            for (Player forPlayer : Bukkit.getOnlinePlayers()) {
                                if (forPlayer.hasPermission("activecraft.vanish.see")) {
                                    if (forPlayer != sender || forPlayer != target) {
                                        forPlayer.sendMessage(ChatColor.AQUA + target.getName() + ChatColor.GOLD + " is now " + ChatColor.AQUA + "visible.");
                                    }
                                } else
                                if (forPlayer != sender || forPlayer != target) {
                                    forPlayer.sendMessage(joinFormat.replace("%displayname%", target.getDisplayName()));
                                }
                            }
                            //Bukkit.broadcastMessage(joinFormat.replace("%displayname%", target.getDisplayName()));

                            playerdataconfig.set("vanished", false);
                            playerdataconfig.saveConfig();
                        } else {
                            vanishManager.setVanished(target, true);
                            target.sendMessage(ChatColor.GOLD + "You are now " + ChatColor.AQUA + "invisible.");
                            sender.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " is now " + ChatColor.AQUA + "invisible.");
                            for (Player forPlayer : Bukkit.getOnlinePlayers()) {
                                if (forPlayer.hasPermission("activecraft.vanish.see")) {
                                    if (forPlayer != sender || forPlayer != target) {
                                        forPlayer.sendMessage(ChatColor.AQUA + target.getName() + ChatColor.GOLD + " is now " + ChatColor.AQUA + "invisible.");
                                    }
                                } else
                                if (forPlayer != sender || forPlayer != target) {
                                    forPlayer.sendMessage(quitFormat.replace("%displayname%", target.getDisplayName()));
                                }
                            }
                            //Bukkit.broadcastMessage(quitFormat.replace("%displayname%", target.getDisplayName()));

                            playerdataconfig.set("vanished", true);
                            playerdataconfig.saveConfig();
                        }
                    } else sender.sendMessage(Errors.INVALID_PLAYER);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            } else if (sender instanceof Player) {
                Player p = (Player) sender;
                FileConfig playerdataconfig = new FileConfig("playerdata" + File.separator + p.getName() + ".yml");
                if (sender.hasPermission("activecraft.vanish")) {
                    if (vanishManager.isVanished(p)) {
                        vanishManager.setVanished(p, false);
                        p.sendMessage(ChatColor.GOLD + "You are now " + ChatColor.AQUA + "visible.");
                        for (Player forPlayer : Bukkit.getOnlinePlayers()) {
                            if (forPlayer.hasPermission("activecraft.vanish.see")) {
                                if (forPlayer != sender) {
                                    forPlayer.sendMessage(ChatColor.AQUA + sender.getName() + ChatColor.GOLD + " is now " + ChatColor.AQUA + "visible.");
                                }
                            } else
                            if (forPlayer != sender) {
                                forPlayer.sendMessage(joinFormat.replace("%displayname%", player.getDisplayName()));
                            }
                        }
                        //Bukkit.broadcastMessage(joinFormat.replace("%displayname%", p.getDisplayName()));

                        playerdataconfig.set("vanished", false);
                        playerdataconfig.saveConfig();
                    } else {
                        vanishManager.setVanished(p, true);
                        p.sendMessage(ChatColor.GOLD + "You are now " + ChatColor.AQUA + "invisible.");
                        for (Player forPlayer : Bukkit.getOnlinePlayers()) {
                            if (forPlayer.hasPermission("activecraft.vanish.see")) {
                                if (forPlayer != sender) {
                                    forPlayer.sendMessage(ChatColor.AQUA + sender.getName() + ChatColor.GOLD + " is now " + ChatColor.AQUA + "invisible.");
                                }
                            } else
                            if (forPlayer != sender) {
                                forPlayer.sendMessage(quitFormat.replace("%displayname%", player.getDisplayName()));
                            }
                        }
                        //Bukkit.broadcastMessage(quitFormat.replace("%displayname%", p.getDisplayName()));

                        playerdataconfig.set("vanished", true);
                        playerdataconfig.saveConfig();
                    }
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }
        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}