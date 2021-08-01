package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("activecraft.ping")) {

            if (sender instanceof Player) {
                Player player = (Player) sender;

                sender.sendMessage(ChatColor.GOLD + "Pong!");
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 0.1f);
            } else sender.sendMessage(ChatColor.GOLD + "Pong!");
            return true;
        } else sender.sendMessage(Errors.NO_PERMISSION);
        return true;
    }
}
