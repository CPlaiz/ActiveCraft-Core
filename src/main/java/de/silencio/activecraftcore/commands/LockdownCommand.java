package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
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

public class LockdownCommand implements CommandExecutor, Listener, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            Player player = (Player) sender;
            if(sender.hasPermission("activecraft.lockdown")) {
                if(args.length == 1 && args[0].equalsIgnoreCase("true")) {
                    FileConfig fileConfig = new FileConfig("config.yml");
                    fileConfig.set("lockdown", true);
                    fileConfig.saveConfig();
                    player.sendMessage(ChatColor.GOLD + "Lockdown mode was enabled. Kicking all players.");
                } else if(args.length == 1 && args[0].equalsIgnoreCase("false")) {
                    FileConfig fileConfig = new FileConfig("config.yml");
                    fileConfig.set("lockdown", false);
                    fileConfig.saveConfig();
                    player.sendMessage(ChatColor.GOLD + "Lockdown mode was disabled. Players can now join the server again.");
                }

            } else player.sendMessage(Errors.NO_PERMISSION);

            if(label.equalsIgnoreCase("lockdownbypass")) {
                if (player.hasPermission("activecraft.lockdown.allowbypass")) {
                    if (args.length == 2) {
                        FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + args[0] + ".yml");

                        if (args[1].equalsIgnoreCase("true")) {
                            playerdataConfig.set("lockdown-bypass", true);
                            playerdataConfig.saveConfig();
                            player.sendMessage(ChatColor.GOLD + "Allowed player " + ChatColor.AQUA + args[0] + ChatColor.GOLD + " to join during lockdown.");
                            player.sendMessage(ChatColor.GOLD + "They will not be able to join again if they leave and do not have the permission " + ChatColor.AQUA + "activecraft.lockdown.bypass" + ChatColor.GOLD + ".");
                        } else if (args[1].equalsIgnoreCase("false")) {
                            playerdataConfig.set("lockdown-bypass", false);
                            playerdataConfig.saveConfig();
                            player.sendMessage(ChatColor.GOLD + "Player " + ChatColor.AQUA + args[0] + ChatColor.GOLD + " will not be able to join during lockdown.");
                            player.sendMessage(ChatColor.GOLD + "If they have the permission " + ChatColor.AQUA + "activecraft.lockdown.bypass" + ChatColor.GOLD + " they will be able to join during lockdown once they join and leave the server again.");
                        }
                    } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
                }else player.sendMessage(Errors.NO_PERMISSION);
            }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Player p = (Player) sender;

        if (args.length == 0) return list;
        if (args.length == 1) {
            list.add("true");
            list.add("false");

        }

        if (args.length == 0) return list;
        if (args.length == 2) {
            list.add("true");
            list.add("false");
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
        if(fileConfig.getBoolean("lockdown")) {
            fileConfig.set("old-modt", event.getMotd());
            fileConfig.saveConfig();
            event.setMotd(fileConfig.get("lockdown-modt").toString());
        }

        if(!fileConfig.getBoolean("lockdown") && fileConfig.get("lockdown") != null) {
            event.setMotd(fileConfig.get("old-modt").toString());
        }
    }
}
