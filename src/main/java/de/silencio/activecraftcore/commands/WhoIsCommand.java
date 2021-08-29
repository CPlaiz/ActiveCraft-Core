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


        if (args.length == 0) {
            if (sender instanceof Player) {
                if (sender.hasPermission("activecraft.whois.self")) {
                    Player player = (Player) sender;
                    FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + sender.getName().toLowerCase() + ".yml");

                    sender.sendMessage(

                            ChatColor.GOLD + "Name: " + ChatColor.AQUA + player.getName() + "\n"
                                    + ChatColor.GOLD + "Nickname: " + ChatColor.AQUA + player.getDisplayName() + "\n"
                                    + ChatColor.GOLD + "Colornick: " + ChatColor.AQUA + playerdataConfig.getString("colornick") + "\n"
                                    + ChatColor.GOLD + "UUID: " + ChatColor.AQUA + player.getUniqueId() + "\n"
                                    + ChatColor.GOLD + "Op: " + ChatColor.AQUA + player.isOp() + "\n"
                                    + ChatColor.GOLD + "Health: " + ChatColor.AQUA + Math.round(player.getHealth()) + "\n"
                                    + ChatColor.GOLD + "Food: " + ChatColor.AQUA + player.getFoodLevel() + "\n"
                                    + ChatColor.GOLD + "World: " + ChatColor.AQUA + player.getWorld().getName() + "\n"
                                    + ChatColor.GOLD + "Coordinates: X: " + ChatColor.AQUA + player.getLocation().getBlockX() + ChatColor.GOLD + ", Y: "
                                        + ChatColor.AQUA + player.getLocation().getBlockY() + ChatColor.GOLD + ", Z: "
                                        + ChatColor.AQUA + player.getLocation().getBlockZ() + "\n"
                                    + ChatColor.GOLD + "AFK: " + ChatColor.AQUA + playerdataConfig.getString("afk") + "\n"
                                    + ChatColor.GOLD + "Client: " + ChatColor.AQUA + player.getClientBrandName() + "\n"
                                    + ChatColor.GOLD + "Address: " + ChatColor.AQUA + player.getAddress().toString().replace("/", "") + "\n"
                                    + ChatColor.GOLD + "Gamemode: " + ChatColor.AQUA + player.getGameMode().name().toLowerCase() + "\n"
                                    + ChatColor.GOLD + "Muted: " + ChatColor.AQUA + playerdataConfig.getString("muted") + "\n"
                                    + ChatColor.GOLD + "Whitelisted: " + ChatColor.AQUA + player.isWhitelisted() + "\n"
                                    + ChatColor.GOLD + "God: " + ChatColor.AQUA + playerdataConfig.getString("godmode") + "\n"
                                    + ChatColor.GOLD + "Vanished: " + ChatColor.AQUA + playerdataConfig.getString("vanished") + "\n"
                                    + ChatColor.GOLD + "On Duty: " + ChatColor.AQUA + playerdataConfig.getString("on-duty") + "\n"

                    );
                } else sender.sendMessage(Errors.NO_PERMISSION);
            } else sender.sendMessage(Errors.NOT_A_PLAYER);
        } else if (args.length == 1) {
            if (sender.hasPermission("activecraft.whois.others")) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER);
                    return false;
                }
                Player target = Bukkit.getPlayer(args[0]);

                if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                    if (!sender.hasPermission("activecraft.whois.self")) {
                        sender.sendMessage(Errors.CANNOT_TARGET_SELF);
                        return false;
                    }
                }

                FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + target.getName().toLowerCase() + ".yml");

                sender.sendMessage(

                        ChatColor.GOLD + "Name: " + ChatColor.AQUA + target.getName() + "\n"
                                + ChatColor.GOLD + "Nickname: " + ChatColor.AQUA + target.getDisplayName() + "\n"
                                + ChatColor.GOLD + "Colornick: " + ChatColor.AQUA + playerdataConfig.getString("colornick") + "\n"
                                + ChatColor.GOLD + "UUID: " + ChatColor.AQUA + target.getUniqueId() + "\n"
                                + ChatColor.GOLD + "Op: " + ChatColor.AQUA + target.isOp() + "\n"
                                + ChatColor.GOLD + "Health: " + ChatColor.AQUA + Math.round(target.getHealth()) + "\n"
                                + ChatColor.GOLD + "Food: " + ChatColor.AQUA + target.getFoodLevel() + "\n"
                                + ChatColor.GOLD + "World: " + ChatColor.AQUA + target.getWorld().getName() + "\n"
                                + ChatColor.GOLD + "Coordinates: X: " + ChatColor.AQUA + target.getLocation().getBlockX() + ChatColor.GOLD + ", Y: "
                                    + ChatColor.AQUA + target.getLocation().getBlockY() + ChatColor.GOLD + ", Z: "
                                    + ChatColor.AQUA + target.getLocation().getBlockZ() + "\n"
                                + ChatColor.GOLD + "AFK: " + ChatColor.AQUA + playerdataConfig.getString("afk") + "\n"
                                + ChatColor.GOLD + "Client: " + ChatColor.AQUA + target.getClientBrandName() + "\n"
                                + ChatColor.GOLD + "Address: " + ChatColor.AQUA + target.getAddress().toString().replace("/", "") + "\n"
                                + ChatColor.GOLD + "Gamemode: " + ChatColor.AQUA + target.getGameMode().name().toLowerCase() + "\n"
                                + ChatColor.GOLD + "Muted: " + ChatColor.AQUA + playerdataConfig.getString("muted") + "\n"
                                + ChatColor.GOLD + "Whitelisted: " + ChatColor.AQUA + target.isWhitelisted() + "\n"
                                + ChatColor.GOLD + "God: " + ChatColor.AQUA + playerdataConfig.getString("godmode") + "\n"
                                + ChatColor.GOLD + "Vanished: " + ChatColor.AQUA + playerdataConfig.getString("vanished") + "\n"
                                + ChatColor.GOLD + "On Duty: " + ChatColor.AQUA + playerdataConfig.getString("on-duty") + "\n"

                );
            } else sender.sendMessage(Errors.NO_PERMISSION);
        } else sender.sendMessage(Errors.INVALID_ARGUMENTS);


        return true;
    }
}
