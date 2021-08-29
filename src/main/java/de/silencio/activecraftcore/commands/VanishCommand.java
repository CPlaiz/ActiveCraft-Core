package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.ownlisteners.VanishListener;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            VanishManager vanishManager = Main.getVanishManager();

            FileConfig fileConfig = new FileConfig("config.yml");
            String joinFormat = fileConfig.getString("join-format");
            String quitFormat = fileConfig.getString("quit-format");

            if (args.length == 1) {
                if (sender.hasPermission("activecraft.vanish.others")) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Errors.INVALID_PLAYER);
                        return false;
                    }
                    Player target = Bukkit.getPlayer(args[0]);

                    if (target != null) {
                        if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                            if (!sender.hasPermission("activecraft.vanish.self")) {
                                sender.sendMessage(Errors.CANNOT_TARGET_SELF);
                                return false;
                            }
                        }
                        FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + target.getName().toLowerCase() + ".yml");
                        if (vanishManager.isVanished(target)) {
                            vanishManager.setVanished(target, false);

                            setDisplaynameFromConfig(target, playerdataConfig.getString("colornick"), playerdataConfig.getString("nickname"));
                            target.sendMessage(ChatColor.GOLD + "You are now " + ChatColor.AQUA + "visible.");
                            sender.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " is now " + ChatColor.AQUA + "visible.");
                            for (Player forPlayer : Bukkit.getOnlinePlayers()) {
                                if (forPlayer.hasPermission("activecraft.vanish.see")) {
                                    if (forPlayer != sender || forPlayer != target) {
                                        forPlayer.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " is now " + ChatColor.AQUA + "visible.");
                                    }
                                } else
                                if (forPlayer != sender || forPlayer != target) {
                                    forPlayer.sendMessage(joinFormat.replace("%displayname%", joinQuitWithColor(target, playerdataConfig.getString("nickname"), playerdataConfig.getString("colornick"))));
                                }
                            }
                            //Bukkit.broadcastMessage(joinFormat.replace("%displayname%", target.getDisplayName()));
                            for (VanishListener vl : Main.getPlugin().getListenerManager().getVanishListenerList()) {
                                vl.onUnvanish(target);
                            }
                            playerdataConfig.set("vanished", false);
                            playerdataConfig.saveConfig();
                        } else {
                            vanishManager.setVanished(target, true);
                            setDisplaynameFromConfig(target, playerdataConfig.getString("colornick"), playerdataConfig.getString("nickname") + ChatColor.GRAY + " " + fileConfig.getString("vanish-format"));
                            target.sendMessage(ChatColor.GOLD + "You are now " + ChatColor.AQUA + "invisible.");
                            sender.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " is now " + ChatColor.AQUA + "invisible.");
                            for (Player forPlayer : Bukkit.getOnlinePlayers()) {
                                if (forPlayer.hasPermission("activecraft.vanish.see")) {
                                    if (forPlayer != sender || forPlayer != target) {
                                        forPlayer.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " is now " + ChatColor.AQUA + "invisible.");
                                    }
                                } else
                                if (forPlayer != sender || forPlayer != target) {
                                    forPlayer.sendMessage(quitFormat.replace("%displayname%", joinQuitWithColor(target, playerdataConfig.getString("nickname"), playerdataConfig.getString("colornick"))));
                                }
                            }
                            //Bukkit.broadcastMessage(quitFormat.replace("%displayname%", target.getDisplayName()));
                            for (VanishListener vl : Main.getPlugin().getListenerManager().getVanishListenerList()) {
                                vl.onVanish(target);
                            }
                            playerdataConfig.set("vanished", true);
                            playerdataConfig.saveConfig();
                        }
                    } else sender.sendMessage(Errors.INVALID_PLAYER);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            } else if (sender instanceof Player) {
                Player p = (Player) sender;
                FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + p.getName().toLowerCase() + ".yml");
                if (sender.hasPermission("activecraft.vanish.self")) {
                    Player player = (Player) sender;
                    if (vanishManager.isVanished(p)) {
                        vanishManager.setVanished(p, false);
                        setDisplaynameFromConfig(p, playerdataConfig.getString("colornick"), playerdataConfig.getString("nickname"));
                        p.sendMessage(ChatColor.GOLD + "You are now " + ChatColor.AQUA + "visible.");
                        for (Player forPlayer : Bukkit.getOnlinePlayers()) {
                            if (forPlayer.hasPermission("activecraft.vanish.see")) {
                                if (forPlayer != sender) {
                                    forPlayer.sendMessage(ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD + " is now " + ChatColor.AQUA + "visible.");
                                }
                            } else
                            if (forPlayer != sender) {
                                forPlayer.sendMessage(joinFormat.replace("%displayname%", joinQuitWithColor(player, playerdataConfig.getString("nickname"), playerdataConfig.getString("colornick"))));
                            }
                        }
                        //Bukkit.broadcastMessage(joinFormat.replace("%displayname%", p.getDisplayName()));
                        for (VanishListener vl : Main.getPlugin().getListenerManager().getVanishListenerList()) {
                            vl.onUnvanish(player);
                        }
                        playerdataConfig.set("vanished", false);
                        playerdataConfig.saveConfig();
                    } else {
                        vanishManager.setVanished(p, true);
                        setDisplaynameFromConfig(p, playerdataConfig.getString("colornick"), playerdataConfig.getString("nickname") + ChatColor.GRAY + " " + fileConfig.getString("vanish-format"));
                        p.sendMessage(ChatColor.GOLD + "You are now " + ChatColor.AQUA + "invisible.");
                        for (Player forPlayer : Bukkit.getOnlinePlayers()) {
                            if (forPlayer.hasPermission("activecraft.vanish.see")) {
                                if (forPlayer != sender) {
                                    forPlayer.sendMessage(ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD + " is now " + ChatColor.AQUA + "invisible.");
                                }
                            } else
                            if (forPlayer != sender) {

                                forPlayer.sendMessage(quitFormat.replace("%displayname%", joinQuitWithColor(player, playerdataConfig.getString("nickname"), playerdataConfig.getString("colornick"))));
                            }
                        }
                        //Bukkit.broadcastMessage(quitFormat.replace("%displayname%", p.getDisplayName()));
                        for (VanishListener vl : Main.getPlugin().getListenerManager().getVanishListenerList()) {
                            vl.onVanish(player);
                        }
                        playerdataConfig.set("vanished", true);
                        playerdataConfig.saveConfig();
                    }
                } else sender.sendMessage(Errors.NO_PERMISSION);
            } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }

    public void setDisplaynameFromConfig(Player p, String colorname, String displayname) {
        for (ChatColor color : ChatColor.values()) {
            if (colorname.toLowerCase().equals(color.name().toLowerCase())) {
                if (!colorname.equals("BOLD") && !colorname.equals("MAGIC") && !colorname.equals("STRIKETHROUGH") &&
                        !colorname.equals("ITALIC") && !colorname.equals("UNDERLINE") && !colorname.equals("RESET")) {
                    p.setDisplayName(color + displayname);
                    p.setPlayerListName(color + displayname);
                }
            }
        }
    }

    public String joinQuitWithColor(Player p, String displayname, String colorname) {
        String outputDisplayname = null;
        for (ChatColor color : ChatColor.values()) {
            if (colorname.toLowerCase().equals(color.name().toLowerCase())) {
                if (!colorname.equals("BOLD") && !colorname.equals("MAGIC") && !colorname.equals("STRIKETHROUGH") &&
                        !colorname.equals("ITALIC") && !colorname.equals("UNDERLINE") && !colorname.equals("RESET")) {
                    outputDisplayname = color + displayname;
                }
            }
        }
        return outputDisplayname;
    }
}