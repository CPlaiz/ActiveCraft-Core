package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.events.LockdownEvent;
import de.silencio.activecraftcore.events.PlayerUnvanishEvent;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LockdownCommand implements CommandExecutor, Listener, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (label.equalsIgnoreCase("lockdown")) {
            if (sender.hasPermission("activecraft.lockdown")) {
                if (args.length == 1 && args[0].equalsIgnoreCase("enable")) {
                    FileConfig fileConfig = new FileConfig("config.yml");
                    if (!fileConfig.getBoolean("lockdown")) {

                        LockdownEvent event = new LockdownEvent(true);
                        Bukkit.getPluginManager().callEvent(event);
                        if (event.isCancelled()) return false;

                        fileConfig.set("lockdown", true);
                        fileConfig.saveConfig();
                        sender.sendMessage(ChatColor.GOLD + "Lockdown mode was enabled. Kicking all players.");
                    } else sender.sendMessage(Errors.WARNING + "Lockdown mode is already enabled.");
                } else if (args.length == 1 && args[0].equalsIgnoreCase("disable")) {
                    FileConfig fileConfig = new FileConfig("config.yml");
                    if (fileConfig.getBoolean("lockdown")) {

                        LockdownEvent event = new LockdownEvent(false);
                        Bukkit.getPluginManager().callEvent(event);
                        if (event.isCancelled()) return false;

                        fileConfig.set("lockdown", false);
                        fileConfig.saveConfig();
                        sender.sendMessage(ChatColor.GOLD + "Lockdown mode was disabled. Players can now join the server again.");
                    } else sender.sendMessage(Errors.WARNING + "Lockdown mode is not enabled.");
                } else sender.sendMessage(Errors.INVALID_ARGUMENTS);

            } else sender.sendMessage(Errors.NO_PERMISSION);
        } else if (label.equalsIgnoreCase("lockdownbypass")) {
            if (sender.hasPermission("activecraft.lockdown.allowbypass")) {
                if (args.length == 2) {
                    FileConfig playerList = new FileConfig("playerlist.yml");
                    List<String> lowercaseList = new ArrayList<>();
                    for (String s : playerList.getStringList("players")) {
                        lowercaseList.add(s.toLowerCase());
                    }
                    if (lowercaseList.contains(args[0].toLowerCase())) {
                        FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + args[0].toLowerCase() + ".yml");

                        if (args[1].equalsIgnoreCase("true")) {
                            if (!playerdataConfig.getBoolean("lockdown-bypass")) {
                                playerdataConfig.set("lockdown-bypass", true);
                                playerdataConfig.saveConfig();
                                sender.sendMessage(ChatColor.GOLD + "Allowed player " + ChatColor.AQUA + args[0] + ChatColor.GOLD + " to join during lockdown.");
                                sender.sendMessage(ChatColor.GOLD + "They will not be able to join again if they leave and do not have the permission " + ChatColor.AQUA + "activecraft.lockdown.bypass" + ChatColor.GOLD + ".");
                            } else
                                sender.sendMessage(Errors.WARNING + "Lockdown bypass is already enabled for " + playerdataConfig.getString("name"));
                        } else if (args[1].equalsIgnoreCase("false")) {
                            if (playerdataConfig.getBoolean("lockdown-bypass")) {
                                playerdataConfig.set("lockdown-bypass", false);
                                playerdataConfig.saveConfig();
                                sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.AQUA + args[0] + ChatColor.GOLD + " will not be able to join during lockdown.");
                                sender.sendMessage(ChatColor.GOLD + "If they have the permission " + ChatColor.AQUA + "activecraft.lockdown.bypass" + ChatColor.GOLD + " they will be able to join during lockdown once they join and leave the server again.");
                            } else
                                sender.sendMessage(Errors.WARNING + "Lockdown bypass is not enabled for " + playerdataConfig.getString("name"));
                        } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
                    } else sender.sendMessage(Errors.INVALID_PLAYER);
                } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
            } else sender.sendMessage(Errors.NO_PERMISSION);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Player p = (Player) sender;

        if (args.length == 0) return list;
        if (alias.equalsIgnoreCase("lockdown")) {
            if (args.length == 1) {
                list.add("enable");
                list.add("disable");
            }
        } else if (alias.equalsIgnoreCase("lockdownbypass")) {
            if (args.length == 0) return list;
            if (args.length == 1) {
                FileConfig playerList = new FileConfig("playerlist.yml");
                list.addAll(playerList.getStringList("players"));
            }
            if (args.length == 2) {
                list.add("true");
                list.add("false");
            }
        }
        ArrayList<String> completerList = new ArrayList<>();
        String currentarg = args[args.length - 1].toLowerCase();
        for (String s : list) {
            String s1 = s.toLowerCase();
            if (s1.startsWith(currentarg)) {
                completerList.add(s);
            }
        }
        return completerList;
    }

    @EventHandler
    public void on(ServerListPingEvent event) {
        FileConfig fileConfig = new FileConfig("config.yml");
        if (fileConfig.getBoolean("lockdown")) {
            fileConfig.set("old-modt", event.getMotd());
            fileConfig.saveConfig();
            event.setMotd(fileConfig.getString("lockdown-modt"));
        }

        if (!fileConfig.getBoolean("lockdown") && fileConfig.get("lockdown") != null) {
            event.setMotd(fileConfig.getString("old-modt"));
        }
    }
}
