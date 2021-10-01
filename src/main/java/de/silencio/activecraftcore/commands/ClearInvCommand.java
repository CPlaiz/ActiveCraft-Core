package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearInvCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (args.length == 0) {
            if (sender.hasPermission("activecraft.clearinv.self")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(CommandMessages.CLEARED_SELF());
                    player.getInventory().clear();
                } else sender.sendMessage(Errors.NOT_A_PLAYER());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else if (args.length == 1) {
            if (sender.hasPermission("activecraft.clearinv.others")) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER());
                    return false;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                    if (!sender.hasPermission("activecraft.clearinv.self")) {
                        sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                        return false;
                    }
                }
                sender.sendMessage(CommandMessages.CLEARED_OTHERS(target));
                target.sendMessage(CommandMessages.CLEARED_OTHERS_MESSAGE(sender));
                target.getInventory().clear();
            } else sender.sendMessage(Errors.NO_PERMISSION());
        }

        if (args.length > 1) {
            sender.sendMessage(Errors.TOO_MANY_ARGUMENTS());
        }
        return true;
    }
}