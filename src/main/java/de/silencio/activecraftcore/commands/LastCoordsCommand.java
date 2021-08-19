package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LastCoordsCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 2) {

            FileConfig playerList = new FileConfig("playerlist.yml");
            List<String> lowercaseList = new ArrayList<>();
            for (String s : playerList.getStringList("players")) {
                lowercaseList.add(s.toLowerCase());
            }
            if (lowercaseList.contains(args[0].toLowerCase())) {

                if (sender.hasPermission("activecraft.lastcoords")) {
                    FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + args[0].toLowerCase() + ".yml");
                    if (Bukkit.getWorld(args[1]) == null) {
                        sender.sendMessage(Errors.WARNING + "World not found!");
                        return false;
                    }

                    World world = Bukkit.getWorld(args[1]);

                    Location lastLocation = playerdataConfig.getLocation("last-location." + args[1]);
                    if (lastLocation == null) {
                        sender.sendMessage(Errors.WARNING + "Player has never entered this world.");
                        return false;
                    }
                    sender.sendMessage(ChatColor.AQUA + playerdataConfig.getString("name") + "'s " + ChatColor.GOLD + "last coords in " + world.getName() + ": \n" + lastLocation.getBlockX()
                            + "; " + lastLocation.getBlockY() + "; " + lastLocation.getBlockZ() + "; " + (int) lastLocation.getYaw() + "; " + (int) lastLocation.getPitch()
                    );
                } else sender.sendMessage(Errors.NO_PERMISSION);
            } else sender.sendMessage(Errors.INVALID_PLAYER);

        } else sender.sendMessage(Errors.INVALID_ARGUMENTS);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        if (args.length == 1) {
            FileConfig playerList = new FileConfig("playerlist.yml");
            list.addAll(playerList.getStringList("players"));
        } else if (args.length == 2) {
            for (World world : Bukkit.getWorlds()) {
                list.add(world.getName());
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