package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpAllCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if(sender instanceof Player) {
                Player player = (Player) sender;
                if (sender.hasPermission("activecraft.tpall")) {
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        if (!target.hasPermission("activecraft.tpall.exept")) {
                            target.teleport(player.getLocation());
                            target.sendMessage(ChatColor.GOLD + "You were teleported to " + ChatColor.AQUA + sender.getName() + ChatColor.GOLD + ".");
                        } else sender.sendMessage( ChatColor.AQUA + sender.getName() + ChatColor.GOLD + " executed a tpall, but you were not teleported.");
                    }
                    sender.sendMessage(ChatColor.GOLD + "Teleported all players to you.");
                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }
}