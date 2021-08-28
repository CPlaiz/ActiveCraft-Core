package de.silencio.activecraftcore.commands;

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

        if (sender.hasPermission("activecraft.clearinv")) {

            if(args.length == 0) {
                if(sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(ChatColor.GOLD + "Cleared your inventory.");
                    player.getInventory().clear();
                } else sender.sendMessage(Errors.NOT_A_PLAYER);
            }

            if(args.length == 1) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER);
                    return false;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null) {
                    sender.sendMessage(ChatColor.GOLD + "Cleared " + ChatColor.AQUA + target.getDisplayName() + "'s" + ChatColor.GOLD + " inventory.");
                    if (sender instanceof Player) {
                        target.sendMessage(ChatColor.GOLD + "Your inventory was cleared by " + ChatColor.AQUA + ((Player) sender).getDisplayName());
                    } else target.sendMessage(ChatColor.GOLD + "Your inventory was cleared by " + ChatColor.AQUA + sender.getName());
                    target.getInventory().clear();
                } else sender.sendMessage(Errors.INVALID_PLAYER);
            }
            
            if(args.length > 1) {
                sender.sendMessage(Errors.TOO_MANY_ARGUMENTS);
            }
            
            return true;
        } else sender.sendMessage(Errors.NO_PERMISSION);
        return true;
    }
}