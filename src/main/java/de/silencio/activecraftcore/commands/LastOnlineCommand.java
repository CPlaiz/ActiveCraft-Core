package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LastOnlineCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
                if (sender.hasPermission("activecraft.lastonline")) {
                    FileConfig playerList = new FileConfig("playerlist.yml");
                    List<String> lowercaseList = new ArrayList<>();
                    for (String s : playerList.getStringList("players")) {
                        lowercaseList.add(s.toLowerCase());
                    }
                    if (lowercaseList.contains(args[0].toLowerCase())) {

                        FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + args[0].toLowerCase() + ".yml");

                        String lastonline = playerdataConfig.getString("last-online");
                        if (lastonline.equalsIgnoreCase("online")) {
                            sender.sendMessage(ChatColor.AQUA + playerdataConfig.getString("name") + ChatColor.GOLD + " is " + ChatColor.GREEN + lastonline);
                        } else
                            sender.sendMessage(ChatColor.AQUA + playerdataConfig.getString("name") + ChatColor.GOLD + " was last online: " + ChatColor.GREEN + lastonline);
                    } else sender.sendMessage(Errors.INVALID_PLAYER());
                } else sender.sendMessage(Errors.NO_PERMISSION());
        } else sender.sendMessage(Errors.INVALID_ARGUMENTS());

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        if (args.length == 1) {
            FileConfig playerList = new FileConfig("playerlist.yml");
            list.addAll(playerList.getStringList("players"));
        }
        ArrayList<String> completerList = new ArrayList<>();
        String currentarg = args[args.length-1].toLowerCase();
        for (String s : list) {
            String s1 = s.toLowerCase();
            if (s1.startsWith(currentarg)){
                completerList.add(s);
            }
        }

        return completerList;
    }
}
