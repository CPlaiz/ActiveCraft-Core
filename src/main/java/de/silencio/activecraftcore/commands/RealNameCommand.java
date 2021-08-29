package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RealNameCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        List<String> associatedPlayerList = new ArrayList<>();

        if (args.length == 1) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                FileConfig playerlistConfig = new FileConfig("playerlist.yml");

                if (sender.hasPermission("activecraft.realname")) {

                  for (String playername : playerlistConfig.getStringList("players")) {
                      FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + playername.toLowerCase() + ".yml");
                      if (args[0].equalsIgnoreCase(MessageUtils.removeColorAndFormat(playerdataConfig.getString("nickname")))) {
                            associatedPlayerList.add(playername);
                      }
                  }
                  StringBuilder associatedPlayers = new StringBuilder();
                  boolean isFirst = true;
                  for (String s : associatedPlayerList) {
                      if (!isFirst) {
                          associatedPlayers.append(ChatColor.GOLD + ", " + ChatColor.AQUA);
                      } else isFirst = false;
                      associatedPlayers.append(s);
                  }
                  sender.sendMessage(ChatColor.GOLD + "Players with the nickname " + ChatColor.AQUA + args[0] + ChatColor.GOLD + ": \n" + ChatColor.AQUA + associatedPlayers);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            } else sender.sendMessage(Errors.NOT_A_PLAYER);
        } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        if (args.length == 1) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");
                list.add(MessageUtils.removeColorAndFormat(playerdataConfig.getString("nickname")));
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