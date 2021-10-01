package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
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
                            CommandMessages.NAME(player.getName()) + "\n"
                                    + CommandMessages.NICKNAME(player.getDisplayName()) + "\n"
                                    + CommandMessages.COLORNICK(playerdataConfig.getString("colornick").toLowerCase()) + "\n"
                                    + CommandMessages.UUID(player.getUniqueId().toString()) + "\n"
                                    + CommandMessages.IS_OP(player.isOp() + "") + "\n" + "\n"
                                    + CommandMessages.HEALTH(Math.round(player.getHealth()) + "") + "\n"
                                    + CommandMessages.FOOD(player.getFoodLevel() + "") + "\n"
                                    + CommandMessages.WORLD(player.getWorld().getName()) + "\n"
                                    + CommandMessages.COORDS(player.getLocation().getBlockX() + "" + ChatColor.GOLD + ", Y: "
                                    + ChatColor.AQUA + player.getLocation().getBlockY() + ChatColor.GOLD + ", Z: "
                                    + ChatColor.AQUA + player.getLocation().getBlockZ())
                                    + CommandMessages.AFK(playerdataConfig.getString("afk")) + "\n"
                                    + CommandMessages.CLIENT(player.getClientBrandName()) + "\n"
                                    + CommandMessages.ADDRESS(player.getAddress().toString().replace("/", "")) + "\n"
                                    + CommandMessages.GAMEMODE(player.getGameMode().name().toLowerCase()) + "\n"
                                    + CommandMessages.MUTED(playerdataConfig.getString("muted")) + "\n"
                                    + CommandMessages.WHITELISTED(player.isWhitelisted() + "") + "\n"
                                    + CommandMessages.GOD(playerdataConfig.getString("godmode")) + "\n"
                                    + CommandMessages.VANISHED(playerdataConfig.getString("vanished")) + "\n"

                    );
                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.NOT_A_PLAYER());
        } else if (args.length == 1) {
            if (sender.hasPermission("activecraft.whois.others")) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER());
                    return false;
                }
                Player target = Bukkit.getPlayer(args[0]);

                if (sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                    if (!sender.hasPermission("activecraft.whois.self")) {
                        sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                        return false;
                    }
                }

                FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + target.getName().toLowerCase() + ".yml");

                sender.sendMessage(
                        CommandMessages.NAME(target.getName()) + "\n"
                                + CommandMessages.NICKNAME(target.getDisplayName()) + "\n"
                                + CommandMessages.COLORNICK(playerdataConfig.getString("colornick").toLowerCase()) + "\n"
                                + CommandMessages.UUID(target.getUniqueId().toString()) + "\n"
                                + CommandMessages.IS_OP(target.isOp() + "") + "\n" + "\n"
                                + CommandMessages.HEALTH(Math.round(target.getHealth()) + "") + "\n"
                                + CommandMessages.FOOD(target.getFoodLevel() + "") + "\n"
                                + CommandMessages.WORLD(target.getWorld().getName()) + "\n"
                                + CommandMessages.COORDS(target.getLocation().getBlockX() + "" + ChatColor.GOLD + ", Y: "
                                + ChatColor.AQUA + target.getLocation().getBlockY() + ChatColor.GOLD + ", Z: "
                                + ChatColor.AQUA + target.getLocation().getBlockZ())
                                + CommandMessages.AFK(playerdataConfig.getString("afk")) + "\n"
                                + CommandMessages.CLIENT(target.getClientBrandName()) + "\n"
                                + CommandMessages.ADDRESS(target.getAddress().toString().replace("/", "")) + "\n"
                                + CommandMessages.GAMEMODE(target.getGameMode().name().toLowerCase()) + "\n"
                                + CommandMessages.MUTED(playerdataConfig.getString("muted")) + "\n"
                                + CommandMessages.WHITELISTED(target.isWhitelisted() + "") + "\n"
                                + CommandMessages.GOD(playerdataConfig.getString("godmode")) + "\n"
                                + CommandMessages.VANISHED(playerdataConfig.getString("vanished")) + "\n"

                );
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
        return true;
    }
}