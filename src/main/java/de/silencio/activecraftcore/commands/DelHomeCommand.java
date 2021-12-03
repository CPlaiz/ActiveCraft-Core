package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DelHomeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            FileConfig homeconfig = new FileConfig("homes.yml");

            if(sender.hasPermission("activecraft.delhome.self")) {
                if(args.length == 1) {
                    if(sender instanceof Player) {
                        Player player = (Player) sender;
                        String playerName = player.getName();

                        if (args[0].equalsIgnoreCase("home_list")) {
                            sender.sendMessage(Errors.INVALID_ARGUMENTS());
                            return false;
                        }

                        if (homeconfig.contains(playerName + "." + args[0])) {
                            List<String> homeList = homeconfig.getStringList(playerName + ".home_list");
                            homeList.remove(args[0]);
                            homeconfig.set(playerName + ".home_list", homeList);
                            homeconfig.set(playerName + "." + args[0], null);
                            homeconfig.saveConfig();
                            sender.sendMessage(CommandMessages.HOME_DELETED(args[0]));
                        } else sender.sendMessage(Errors.WARNING() + CommandMessages.NO_HOME(args[0]));
                    } else sender.sendMessage(Errors.NOT_A_PLAYER());
                }
            } else sender.sendMessage(Errors.NO_PERMISSION());

            if(args.length == 2) {
                if (sender.hasPermission("activecraft.delhome.others")) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Errors.INVALID_PLAYER());
                        return false;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                        if (!sender.hasPermission("activecraft.delhome.self")) {
                            sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                            return false;
                        }
                    }
                    String targetName = target.getName();

                    if (args[1].equalsIgnoreCase("home_list")) {
                        sender.sendMessage(Errors.INVALID_ARGUMENTS());
                        return false;
                    }

                    if(homeconfig.contains(targetName + "." + args[1])) {
                        List<String> homeList = homeconfig.getStringList(targetName + ".home_list");
                        homeList.remove(args[1]);
                        homeconfig.set(targetName + ".home_list", homeList);
                        homeconfig.set(targetName + "." + args[1].toLowerCase(), null);
                        homeconfig.saveConfig();
                        sender.sendMessage(CommandMessages.HOME_OTHERS_DELETED(target, args[1].toString()));
                    } else sender.sendMessage(Errors.WARNING() + CommandMessages.NO_HOME_OTHERS(target, args[1].toString()));
                } else sender.sendMessage(Errors.NO_PERMISSION());
            }
            if(args.length >= 3) {
                sender.sendMessage(Errors.TOO_MANY_ARGUMENTS());
            }
            if(args.length == 0) {
                sender.sendMessage(Errors.INVALID_ARGUMENTS());
            }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();

        Player p = (Player) sender;

        if (args.length == 0) return list;

        if (args.length == 1) {
            FileConfig warpListConfig = new FileConfig("homes.yml");
            list.addAll(warpListConfig.getStringList(p.getName() + ".home_list"));
            for (Player player : Bukkit.getOnlinePlayers()) {
                list.add(player.getName());
            }
        } else if (args.length == 2) {
            FileConfig warpListConfig = new FileConfig("homes.yml");
            if (Bukkit.getPlayer(args[0]) != null) {
                list.addAll(warpListConfig.getStringList(Bukkit.getPlayer(args[0]).getName() + ".home_list"));
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
}