package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
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
                    if (sender.hasPermission("activecraft.suicide.self")) {

                        Player player = ((Player) sender);
                        player.setHealth(0);
                        player.sendMessage(CommandMessages. SUICIDE());
                        Bukkit.broadcastMessage(CommandMessages.BROADCAST_SUICIDE(player));

                    } else sender.sendMessage(Errors.NO_PERMISSION());
                } else sender.sendMessage(Errors.NOT_A_PLAYER());
            } else if(args.length == 1) {
                if(sender.hasPermission("activecraft.suicide.others")) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Errors.INVALID_PLAYER());
                        return false;
                    }
                    Player target = Bukkit.getPlayer(args[0]);

                    if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                        if (!sender.hasPermission("activecraft.suicide.self")) {
                            sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                            return false;
                        }
                    }

                    target.setHealth(0);
                    sender.sendMessage(CommandMessages.SUICIDE_OTHERS(target));
                    Bukkit.broadcastMessage(CommandMessages.BROADCAST_SUICIDE(target));

                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
        return true;
    }
}