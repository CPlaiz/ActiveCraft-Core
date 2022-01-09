package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
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
}
