package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RealNameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {

            if(sender.hasPermission("activecraft.realname")) {

                if(args.length == 1) {

                    Player target = Bukkit.getPlayer(args[0]);

                    sender.sendMessage(ChatColor.GOLD + "Real name of " + ChatColor.AQUA + args[0] + ChatColor.GOLD + " is " + ChatColor.AQUA + target.getName() + ChatColor.GOLD + ".");

                } else sender.sendMessage(Errors.INVALID_ARGUMENTS);

            } else sender.sendMessage(Errors.NO_PERMISSION);

        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
