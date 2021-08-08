package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeatherCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(sender.hasPermission("activecraft.weather")) {
                if(args.length == 1) {

                        if(args[0] == "thunder") {
                            player.getWorld().setThundering(true);
                            player.sendMessage(ChatColor.GOLD + "Changed weather in " + ChatColor.AQUA + player.getWorld() + ChatColor.GOLD + " to " + ChatColor.AQUA + "Thunder" + ChatColor.GOLD + ".");
                        }
                        if(args[0] == "rain") {
                             player.getWorld().setStorm(true);
                             player.sendMessage(ChatColor.GOLD + "Changed weather in " + ChatColor.AQUA + player.getWorld() + ChatColor.GOLD + " to " + ChatColor.AQUA + "Rain" + ChatColor.GOLD + ".");
                        }
                        if(args[0] == "clear") {
                            player.getWorld().setClearWeatherDuration(999999999 * 99999);
                            player.sendMessage(ChatColor.GOLD + "Changed weather in " + ChatColor.AQUA + player.getWorld() + ChatColor.GOLD + " to " + ChatColor.AQUA + "Clear" + ChatColor.GOLD + ".");
                       }
                } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
            } else sender.sendMessage(Errors.NO_PERMISSION);

        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
