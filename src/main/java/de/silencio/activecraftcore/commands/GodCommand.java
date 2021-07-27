package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {

            if(sender.hasPermission("activecraft.god")) {

                if(args.length == 1) {
                    switch(args[0]) {
                        case "on":
                        case "true":
                            ((Player) sender).setInvulnerable(true);
                            sender.sendMessage(ChatColor.GOLD + "God mode activated.");
                            break;
                        case "off":
                        case "false":
                            ((Player) sender).setInvulnerable(false);
                            sender.sendMessage(ChatColor.GOLD + "God mode deactivated.");
                            break;
                    }
                }

            } else sender.sendMessage(Errors.NO_PERMISSION);

            if(sender.hasPermission("activecraft.god.others")) {
                if (args.length == 2) {

                    Player target = Bukkit.getPlayer(args[0]);
                    Player player = (Player) sender;

                    switch (args[1]) {
                        case "on":
                        case "true":
                            target.setInvulnerable(true);
                            player.sendMessage(ChatColor.GOLD + "God mode activated for " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + ".");
                            target.sendMessage(ChatColor.GOLD + "God mode activated by " + ChatColor.AQUA + player.getDisplayName() + ChatColor.GOLD + ".");
                            break;
                        case "off":
                        case "false":
                            target.setInvulnerable(false);
                            player.sendMessage(ChatColor.GOLD + "God mode deactivated for " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + ".");
                            target.sendMessage(ChatColor.GOLD + "God mode deactivated by " + ChatColor.AQUA + player.getDisplayName() + ChatColor.GOLD + ".");
                            break;
                    }
                }
            } else sender.sendMessage(Errors.NO_PERMISSION);
        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
