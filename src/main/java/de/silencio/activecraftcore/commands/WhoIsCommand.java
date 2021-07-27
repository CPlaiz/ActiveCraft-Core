package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhoIsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("activecraft.whois")) {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                sender.sendMessage(

                        ChatColor.AQUA + "Name: " + ChatColor.WHITE + target.getName() + "\n"
                      + ChatColor.AQUA + "Nickname: " + ChatColor.WHITE + target.getDisplayName() + "\n"
                      + ChatColor.AQUA + "UUID: " + ChatColor.WHITE + target.getUniqueId() + "\n"
                      + ChatColor.AQUA + "Health: " + ChatColor.WHITE +  Math.round(target.getHealth()) + "\n"
                      + ChatColor.AQUA + "Food: " + ChatColor.WHITE +  target.getFoodLevel() + "\n"
                      + ChatColor.AQUA + "World: " + ChatColor.WHITE +  target.getWorld().getName() + "\n"
                      + ChatColor.AQUA + "Coordinates: " +
                                ChatColor.WHITE + "X: " + target.getLocation().getBlockX() + ", Y: " + target.getLocation().getBlockY() + ", Z: " + target.getLocation().getBlockZ() + "\n"
                      + ChatColor.AQUA + "Client: " + ChatColor.WHITE + target.getClientBrandName() + "\n"
                      + ChatColor.AQUA + "Address: " + ChatColor.WHITE + target.getAddress().toString() + "\n"
                      + ChatColor.AQUA + "Gamemode: " + ChatColor.WHITE + target.getGameMode().name().toLowerCase() + "\n"

                );
            }
        } else sender.sendMessage(Errors.NO_PERMISSION);

        return true;
    }
}
