package de.silencio.activecraftcore.commands;

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

                    Player target = Bukkit.getPlayer(args[0]);
                    Player player = (Player) sender;

                    target.teleport(((Player) sender).getLocation());
                    sender.sendMessage(ChatColor.GOLD + "Teleported " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " to you.");

                } else sender.sendMessage(Errors.NO_PERMISSION);
            } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
