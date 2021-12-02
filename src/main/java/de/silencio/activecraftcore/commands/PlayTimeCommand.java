package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PlayTimeCommand implements CommandExecutor, TabCompleter {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfig fileConfig = new FileConfig("playtime.yml");
        if (args.length == 0) {
            if (sender.hasPermission("activecraft.playtime.self")) {
                if (sender instanceof Player) {
                    int hours = fileConfig.getInt(sender.getName() + ".hours");
                    int minutes = fileConfig.getInt(sender.getName() + ".minutes");
                    sender.sendMessage(CommandMessages.PLAYTIME(hours + "", minutes + ""));
                } else sender.sendMessage(Errors.NOT_A_PLAYER());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else if (args.length == 1) {
            if (sender.hasPermission("activecraft.playtime.others")) {
                if (!ActiveCraftCore.getPlugin().getPlayerlist().containsKey(args[0].toLowerCase())) {
                    sender.sendMessage(Errors.INVALID_PLAYER());
                    return false;
                }
                if (sender.getName().toLowerCase().equals(args[0].toLowerCase())) {
                    if (!sender.hasPermission("activecraft.playtime.self")) {
                        sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                        return false;
                    }
                }
                FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + args[0].toLowerCase() + ".yml");
                int hours = fileConfig.getInt(args[0] + ".hours");
                int minutes = fileConfig.getInt(args[0] + ".minutes");
                sender.sendMessage(CommandMessages.PLAYTIME_OTHERS(args[0], playerdataConfig.getString("nickname"), hours + "", minutes + ""));
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