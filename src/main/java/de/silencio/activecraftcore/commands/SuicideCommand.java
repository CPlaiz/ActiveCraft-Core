package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SuicideCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if(args.length == 0) {
                if (sender instanceof Player) {
                    if (sender.hasPermission("activecraft.suicide")) {

                        Player player = ((Player) sender);
                        player.setHealth(0);
                        player.sendMessage(ChatColor.GOLD + "You killed yourself.");
                        Bukkit.broadcastMessage(ChatColor.AQUA + player.getDisplayName() + ChatColor.GOLD + " committed suicide.");

                    } else sender.sendMessage(Errors.NO_PERMISSION);
                } else sender.sendMessage(Errors.NOT_A_PLAYER);
            } else if(args.length == 1) {
                if(sender.hasPermission("activecraft.suicide.others")) {
                    Player target = Bukkit.getPlayer(args[0]);

                    target.setHealth(0);
                    sender.sendMessage(ChatColor.GOLD + "Made " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " commit suicide.");
                    Bukkit.broadcastMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " committed suicide.");

                } else sender.sendMessage(Errors.NO_PERMISSION);
            } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
        return true;
    }
}