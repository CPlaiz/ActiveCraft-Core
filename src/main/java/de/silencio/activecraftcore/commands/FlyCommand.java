package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {

            if(sender.hasPermission("activecraft.fly")) {

                if(args.length == 1) {
                    Player player = (Player) sender;

                    switch(args[0]) {
                        case "on":
                        case "true":
                            ((Player) sender).setAllowFlight(true);
                            sender.sendMessage(ChatColor.GOLD + "Flight mode activated.");
                            break;
                        case "off":
                        case "false":
                            ((Player) sender).setAllowFlight(false);
                            sender.sendMessage(ChatColor.GOLD + "Flight mode deactivated.");
                            break;
                    }
                }

            } else sender.sendMessage(Errors.NO_PERMISSION);

            if(sender.hasPermission("activecraft.fly.others")) {

                if(args.length == 2) {
                    Player target = Bukkit.getPlayer(args[0]);

                    switch (args[1]) {
                        case "on":
                        case "true":
                            target.setAllowFlight(true);
                            sender.sendMessage(ChatColor.GOLD + "Flight mode activated for " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + ".");
                            target.sendMessage(ChatColor.GOLD + "Flight mode activated by " + ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD + ".");
                            break;
                        case "off":
                        case "false":
                            target.setAllowFlight(false);
                            sender.sendMessage(ChatColor.GOLD + "Flight mode deactivated for " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + ".");
                            target.sendMessage(ChatColor.GOLD + "Flight mode deactivated by " + ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD + ".");
                            break;
                    }
                }
            } else sender.sendMessage(Errors.NO_PERMISSION);
        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
