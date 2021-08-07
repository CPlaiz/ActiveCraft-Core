package de.silencio.activecraftcore.commands;

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

        if(sender instanceof Player) {

            Player player = (Player) sender;

            if(sender.hasPermission("activecraft.heal")) {

                if(args.length < 1) {
                    player.setHealth(20);
                    player.setFoodLevel(20);
                    player.sendMessage(ChatColor.GOLD + "You were healed.");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.4f, 1f);
                } else if(args.length == 1) {

                    Player target = Bukkit.getPlayer(args[0]);

                    if(target != null) {
                        target.setHealth(20);
                        target.setFoodLevel(20);
                        player.sendMessage(ChatColor.GOLD + "You healed " + ChatColor.AQUA + target.getDisplayName());
                        target.sendMessage(ChatColor.GOLD + "You were healed by " + ChatColor.AQUA + player.getDisplayName() + ChatColor.GOLD + ".");
                        target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.4f, 1f);
                    } else sender.sendMessage(Errors.WARNING + "Player not found!");
                } else sender.sendMessage(Errors.INVALID_ARGUMENTS);

            } else sender.sendMessage(Errors.NO_PERMISSION);

        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
