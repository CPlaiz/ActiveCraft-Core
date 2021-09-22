package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                if (sender.hasPermission("activecraft.skull.self")) {

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give @p minecraft:player_head{SkullOwner:\"" + player.getName() + "\"}");
                    sender.sendMessage(ChatColor.GOLD + "Gave yourself your head.");

                } else sender.sendMessage(Errors.NO_PERMISSION());
            }

            if (args.length == 1) {
                if (sender.hasPermission("activecraft.skull.others")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if(player.getUniqueId().toString().equals(Bukkit.getServer().getOfflinePlayer(args[0]).getUniqueId().toString())) {
                        if (!sender.hasPermission("activecraft.skull.self")) {
                            sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                            return false;
                        }
                    }
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give @p minecraft:player_head{SkullOwner:\"" + args[0] + "\"}");
                    sender.sendMessage(ChatColor.GOLD + "Gave yourself " + ChatColor.AQUA + args[0] + ChatColor.GOLD + "'s head.");

                } else sender.sendMessage(Errors.NO_PERMISSION());
            }

            if(args.length == 2) {
                if (sender.hasPermission("activecraft.skull.multiple")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if(player.getUniqueId().toString().equals(Bukkit.getServer().getOfflinePlayer(args[0]).getUniqueId().toString())) {
                        if (!sender.hasPermission("activecraft.skull.self")) {
                            sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                            return false;
                        }
                    }
                        Integer num = null;
                        try {
                            num = Integer.valueOf(args[1]);
                        } catch (NumberFormatException ignored) {
                        }
                        if (num == null) {
                            sender.sendMessage(Errors.INVALID_NUMBER());
                            return false;
                        }
                    for (int i = Integer.parseInt(args[1]); i > 0; i--) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give @p minecraft:player_head{SkullOwner:\"" + args[0] + "\"}");
                    }
                    sender.sendMessage(ChatColor.GOLD + "Gave yourself " + ChatColor.AQUA + args[1] + ChatColor.GOLD + "x " + ChatColor.AQUA + args[0] + ChatColor.GOLD + "'s head.");
                } else sender.sendMessage(Errors.NO_PERMISSION());
            }


            if(args.length >= 3) {
                sender.sendMessage(Errors.TOO_MANY_ARGUMENTS());
            }

        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }
}