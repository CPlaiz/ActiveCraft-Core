package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
                    if (ActiveCraftCore.getPlugin().getPlayerlist().containsKey(args[0].toLowerCase())) {

                        FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + args[0].toLowerCase() + ".yml");

                        String lastonline = playerdataConfig.getString("last-online");
                        Player target = Bukkit.getPlayer(playerdataConfig.getString("name"));
                        if (lastonline.equalsIgnoreCase("online")) {
                            sender.sendMessage(CommandMessages.LASTONLINE_ONLINE(target, lastonline));
                        } else
                            sender.sendMessage(CommandMessages.LASTONLINE_OFFLINE(playerdataConfig.getString("name"), lastonline).replace("%t_displayname%", ChatColor.AQUA + playerdataConfig.getString("nickname") + ChatColor.GOLD));
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
            for (String playername : ActiveCraftCore.getPlugin().getPlayerlist().keySet()) {
                list.add(new Profile(playername).getName());
            }
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