package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WeatherCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (sender.hasPermission("activecraft.weather")) {
                if (args.length == 1) {
                    if (Objects.equals(args[0], "thunder")) {
                        player.getWorld().setThundering(true);
                        player.sendMessage(CommandMessages.TO_THUNDER(player.getWorld().getName()));
                    } else if (Objects.equals(args[0], "rain")) {
                        player.getWorld().setStorm(true);
                        player.sendMessage(CommandMessages.TO_RAIN(player.getWorld().getName()));
                    } else if (Objects.equals(args[0], "clear")) {
                        player.getWorld().setClearWeatherDuration(999999999);
                        player.sendMessage(CommandMessages.TO_CLEAR(player.getWorld().getName()));
                    } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
                } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
            } else sender.sendMessage(Errors.NO_PERMISSION());

        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Player p = (Player) sender;

        if (args.length == 0) return list;
        if (args.length == 1) {
            list.add("clear");
            list.add("thunder");
            list.add("rain");
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
