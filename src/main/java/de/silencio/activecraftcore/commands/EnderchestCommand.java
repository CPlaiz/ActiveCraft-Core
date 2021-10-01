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

import java.util.ArrayList;
import java.util.UUID;

public class EnderchestCommand implements CommandExecutor {

    public static ArrayList<UUID> enderchest = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                if (sender.hasPermission("activecraft.enderchest.self")) {
                    player.openInventory(player.getEnderChest());
                    player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1f, 1f);
                    player.sendMessage(CommandMessages.ENDERCHEST_OPEN());
                } else sender.sendMessage(Errors.NO_PERMISSION());

            } else if (sender.hasPermission("activecraft.enderchest.others")) {

                if (args.length == 1) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Errors.INVALID_PLAYER());
                        return false;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                        if (!sender.hasPermission("activecraft.enderchest.self")) {
                            sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                            return false;
                        }
                    }

                    player.openInventory(target.getEnderChest());
                    player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1f, 1f);
                    player.sendMessage(CommandMessages.ENDERCHEST_OPEN_OTHERS(target));
                } else sender.sendMessage(Errors.TOO_MANY_ARGUMENTS());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }
}