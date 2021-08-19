package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class WhoIsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("activecraft.whois")) {
            if (args.length == 1) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER);
                    return false;
                }
                Player target = Bukkit.getPlayer(args[0]);

                FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + target.getName().toLowerCase() + ".yml");

                sender.sendMessage(

                        ChatColor.AQUA + "Name: " + ChatColor.WHITE + target.getName() + "\n"
                      + ChatColor.AQUA + "Nickname: " + ChatColor.WHITE + target.getDisplayName() + "\n"
                      + ChatColor.AQUA + "Colornick: " + ChatColor.WHITE + playerdataConfig.getString("colornick") + "\n"
                      + ChatColor.AQUA + "UUID: " + ChatColor.WHITE + target.getUniqueId() + "\n"
                      + ChatColor.AQUA + "Op: " + ChatColor.WHITE + target.isOp() + "\n"
                      + ChatColor.AQUA + "Health: " + ChatColor.WHITE +  Math.round(target.getHealth()) + "\n"
                      + ChatColor.AQUA + "Food: " + ChatColor.WHITE +  target.getFoodLevel() + "\n"
                      + ChatColor.AQUA + "World: " + ChatColor.WHITE +  target.getWorld().getName() + "\n"
                      + ChatColor.AQUA + "Coordinates: " +
                                ChatColor.WHITE + "X: " + target.getLocation().getBlockX() + ", Y: " + target.getLocation().getBlockY() + ", Z: " + target.getLocation().getBlockZ() + "\n"
                      + ChatColor.AQUA + "AFK: " + ChatColor.WHITE + playerdataConfig.getString("afk") + "\n"
                      + ChatColor.AQUA + "Client: " + ChatColor.WHITE + target.getClientBrandName() + "\n"
                      + ChatColor.AQUA + "Address: " + ChatColor.WHITE + target.getAddress().toString().replace("/", "") + "\n"
                      + ChatColor.AQUA + "Gamemode: " + ChatColor.WHITE + target.getGameMode().name().toLowerCase() + "\n"
                      + ChatColor.AQUA + "Muted: " + ChatColor.WHITE + playerdataConfig.getString("muted") + "\n"
                      + ChatColor.AQUA + "Whitelisted: " + ChatColor.WHITE + target.isWhitelisted() + "\n"
                      + ChatColor.AQUA + "God: " + ChatColor.WHITE + playerdataConfig.getString("godmode") + "\n"
                      + ChatColor.AQUA + "Vanished: " + ChatColor.WHITE + playerdataConfig.getString("vanished") + "\n"
                      + ChatColor.AQUA + "On Duty: " + ChatColor.WHITE + playerdataConfig.getString("on-duty") + "\n"

                );
            }
        } else sender.sendMessage(Errors.NO_PERMISSION);

        return true;
    }
}
