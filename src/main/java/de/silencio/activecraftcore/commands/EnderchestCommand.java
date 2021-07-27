package de.silencio.activecraftcore.commands;

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

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(sender.hasPermission("activecraft.enderchest")) {

                if(args.length == 0) {

                    player.openInventory(player.getEnderChest());
                    player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1f, 1f);
                    player.sendMessage(ChatColor.GOLD + "Opened Enderchest.");

                }

            } else sender.sendMessage(Errors.NO_PERMISSION);

            if(sender.hasPermission("activecraft.enderchest.others")) {

                if(args.length == 1) {

                    Player target = Bukkit.getPlayer(args[0]);

                    player.openInventory(target.getEnderChest());
                    player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1f, 1f);
                    player.sendMessage(ChatColor.GOLD + "Opened Enderchest of " + ChatColor.AQUA + target.getDisplayName());
                }
            } else sender.sendMessage(Errors.NO_PERMISSION);

        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
