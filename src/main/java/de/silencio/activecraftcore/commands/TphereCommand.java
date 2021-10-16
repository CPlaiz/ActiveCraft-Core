package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TphereCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {

            if (args.length == 1) {
                if (sender.hasPermission("activecraft.tphere")) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Errors.INVALID_PLAYER());
                        return false;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    Player player = (Player) sender;

                    if(target == player) {
                        player.sendMessage(Errors.WARNING() + CommandMessages.CANNOT_TP_TO_SELF_TPHERE());
                        return false;
                    }

                    target.teleport(player.getLocation());
                    sender.sendMessage(CommandMessages.TELEPORT_PLAYER_TO_YOU(target));

                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }
}
