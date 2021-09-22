package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class XpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Integer num = null;
                try {
                    num = Integer.valueOf(args[0].replace("l", ""));
                } catch (NumberFormatException ignored) {
                }
                if (num != null) {
                    if (sender.hasPermission("activecraft.xp.self")) {
                        if (args[0].endsWith("l")) {
                            player.giveExpLevels(Integer.parseInt(args[0].replace("l", "")));
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                            player.sendMessage(ChatColor.GOLD + "Gave yourself " + ChatColor.AQUA + args[0].replace("l", "") + ChatColor.GOLD + " levels.");
                        } else {
                            player.giveExp(Integer.parseInt(args[0]));
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                            player.sendMessage(ChatColor.GOLD + "Gave yourself " + ChatColor.AQUA + args[0] + ChatColor.GOLD + " xp.");
                        }
                    } else sender.sendMessage(Errors.NO_PERMISSION);
                } else sender.sendMessage(Errors.INVALID_NUMBER);
            } else sender.sendMessage(Errors.NOT_A_PLAYER);
        } else if (args.length == 2) {
            if (sender.hasPermission("activecraft.xp.others")) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER);
                    return false;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                    if (!sender.hasPermission("activecraft.xp.self")) {
                        sender.sendMessage(Errors.CANNOT_TARGET_SELF);
                        return false;
                    }
                }
                Integer num = null;
                try {
                    num = Integer.valueOf(args[0].replace("l", ""));;
                } catch (NumberFormatException ignored) {
                }
                if (num == null) {
                    sender.sendMessage(Errors.INVALID_NUMBER);
                    return false;
                }
                if (target != null) {
                    if (args[1].endsWith("l")) {
                        target.giveExpLevels(Integer.parseInt(args[1].replace("l", "")));
                        target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                        sender.sendMessage(ChatColor.GOLD + "Gave " + ChatColor.AQUA + target.getDisplayName() + " " + args[1].replace("l", "") + ChatColor.GOLD + " levels.");
                        if (sender instanceof Player) {
                            target.sendMessage(ChatColor.GOLD + "You were given " + ChatColor.AQUA + args[1].replace("l", "") + ChatColor.GOLD + " levels by " + ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD + ".");
                        } else  target.sendMessage(ChatColor.GOLD + "You were given " + ChatColor.AQUA + args[1].replace("l", "") + ChatColor.GOLD + " levels by " + ChatColor.AQUA + sender.getName() + ChatColor.GOLD + ".");
                    } else {
                        target.giveExp(Integer.parseInt(args[1]));
                        target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                        sender.sendMessage(ChatColor.GOLD + "Gave " + ChatColor.AQUA + target.getDisplayName() + " " + args[1] + ChatColor.GOLD + " xp.");
                        if (sender instanceof Player) {
                            target.sendMessage(ChatColor.GOLD + "You were given " + ChatColor.AQUA + args[1] + ChatColor.GOLD + " xp by " + ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD + ".");
                        } else target.sendMessage(ChatColor.GOLD + "You were given " + ChatColor.AQUA + args[1] + ChatColor.GOLD + " xp by " + ChatColor.AQUA + sender.getName() + ChatColor.GOLD + ".");

                    }
                } else sender.sendMessage(Errors.INVALID_PLAYER);
            } else sender.sendMessage(Errors.NO_PERMISSION);
        } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
        return true;
    }
}