package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

                if(args.length < 1) {
                    if(sender instanceof Player) {
                        Player player = (Player) sender;
                        if(sender.hasPermission("activecraft.heal.self")) {
                            player.setHealth(20);
                            player.setFoodLevel(20);
                            player.sendMessage(CommandMessages.HEAL());
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.4f, 1f);
                        } else sender.sendMessage(Errors.NO_PERMISSION());
                    } else sender.sendMessage(Errors.NOT_A_PLAYER());
                } else if(args.length == 1) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Errors.INVALID_PLAYER());
                        return false;
                    }
                    Player target = Bukkit.getPlayer(args[0]);

                    if(target != null) {
                        if(sender.hasPermission("activecraft.heal.others")) {
                            if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                                if (!sender.hasPermission("activecraft.god.self")) {
                                    sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                                    return false;
                                }
                            }
                            target.setHealth(20);
                            target.setFoodLevel(20);
                            sender.sendMessage(CommandMessages.HEAL_OTHERS(target));
                            target.sendMessage(CommandMessages.HEAL_OTHERS_MESSAGE(sender));
                            target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.4f, 1f);
                        } else sender.sendMessage(Errors.NO_PERMISSION());
                    } else sender.sendMessage(Errors.INVALID_PLAYER());
                } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
        return true;
    }
}