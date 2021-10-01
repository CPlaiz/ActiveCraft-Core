package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BreakCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (sender.hasPermission("activecraft.break.self")) {

                    var block = player.getTargetBlock(null, 9999);
                    player.playSound(player.getLocation(), Sound.BLOCK_STONE_BREAK, 1f, 1f);
                    block.setType(Material.AIR);
                    sender.sendMessage(CommandMessages.BREAK_SELF());

                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.NOT_A_PLAYER());
        } else if (args.length == 1) {
            if (sender.hasPermission("activecraft.break.other")) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER());
                    return false;
                }
                Player target = Bukkit.getPlayer(args[0]);
                    if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                        if (!sender.hasPermission("activecraft.break.self")) {
                            sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                            return false;
                        }
                    }
                var block = target.getTargetBlock(null, 9999);
                target.playSound(target.getLocation(), Sound.BLOCK_STONE_BREAK, 1f, 1f);
                sender.sendMessage(CommandMessages.BREAK_OTHERS(target, sender));
                block.setType(Material.AIR);
            }
        }
        return true;
    }
}