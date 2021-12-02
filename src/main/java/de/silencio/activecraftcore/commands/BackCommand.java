package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            Location lastLoc = ActiveCraftCore.getPlugin().getLastLocationForPlayer(player);
            if(args.length == 0) {
                if (sender.hasPermission("activecraft.back.self")) {

                    if (lastLoc != null) {
                        player.teleport(lastLoc);
                        sender.sendMessage(CommandMessages.TELEPORTED_BACK());
                    } else sender.sendMessage(Errors.WARNING() + CommandMessages.NO_RETURN_LOCATION());

                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else if(args.length >= 2) sender.sendMessage(Errors.TOO_MANY_ARGUMENTS());
        } else sender.sendMessage(Errors.NOT_A_PLAYER());

        if(args.length == 1) {
            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(Errors.INVALID_PLAYER());
                return false;
            }
            Player target = Bukkit.getPlayer(args[0]);
            Location lastLoc = ActiveCraftCore.getPlugin().getLastLocationForPlayer(target);
            if(sender.getName().equalsIgnoreCase(target.getName())) {
                sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                return false;
            }
            if(sender.hasPermission("activecraft.back.others")) {

                if (lastLoc != null) {
                    target.teleport(lastLoc);
                    sender.sendMessage(CommandMessages.TELEPORTED_BACK_OTHERS(target));
                } else sender.sendMessage(Errors.WARNING() + CommandMessages.NO_RETURN_LOCATION_OTHERS(target));

            } else sender.sendMessage(Errors.NO_PERMISSION());
        }
        return true;
    }
}