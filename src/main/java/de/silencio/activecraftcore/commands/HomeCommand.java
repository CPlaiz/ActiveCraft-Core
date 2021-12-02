package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HomeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            FileConfiguration homeconfig = new FileConfig("homes.yml");

            if (args.length == 1) {
                if (sender.hasPermission("activecraft.home.self")) {
                    Player player = (Player) sender;
                    String playerName = player.getName();

                    if (args[0].equalsIgnoreCase("home_list")) {
                        sender.sendMessage(Errors.INVALID_ARGUMENTS());
                        return false;
                    }
                    if (homeconfig.contains(playerName + "." + args[0])) {
                        player.teleport(homeconfig.getLocation(playerName + "." + args[0]));
                        sender.sendMessage(CommandMessages.TELEPORT_HOME_COMPLETE(args[0]));
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);

                    } else sender.sendMessage(Errors.WARNING() + CommandMessages.HOME_NOT_SET(args[0]));
                } else sender.sendMessage(Errors.NO_PERMISSION());
            }

            if (args.length == 2) {
                if (sender.hasPermission("activecraft.home.others")) {
                    Player player = (Player) sender;
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Errors.INVALID_PLAYER());
                        return false;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if (sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                        if (!sender.hasPermission("activecraft.home.self")) {
                            sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                            return false;
                        }
                    }
                    String targetName = target.getName();

                    if (args[1].equalsIgnoreCase("home_list")) {
                        sender.sendMessage(Errors.INVALID_ARGUMENTS());
                        return false;
                    }

                    if (homeconfig.contains(targetName + "." + args[1])) {

                        player.teleport(homeconfig.getLocation(targetName + "." + args[1]));
                        sender.sendMessage(CommandMessages.TELEPORT_HOME_OTHERS_COMPLETE(target, args[1]));
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);

                    } else
                        sender.sendMessage(Errors.WARNING() + CommandMessages.HOME_OTHERS_NOT_SET(target, args[1]));
                } else sender.sendMessage(Errors.NO_PERMISSION());
            }
            if (args.length >= 3) {
                sender.sendMessage(Errors.TOO_MANY_ARGUMENTS());
            }
            if (args.length == 0) {
                sender.sendMessage(Errors.INVALID_ARGUMENTS());
            }
        } else sender.sendMessage(Errors.NOT_A_PLAYER());
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