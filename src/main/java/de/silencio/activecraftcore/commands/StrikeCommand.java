package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StrikeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(sender.hasPermission("activecraft.strike")) {
                if(args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);

                    target.getWorld().strikeLightning(target.getLocation());
                    target.sendMessage(ChatColor.GOLD + "Thor has struck lightning upon you!");
                    player.sendMessage(ChatColor.GOLD + "Struck " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " with lightning.");

                } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
            } else sender.sendMessage(Errors.NO_PERMISSION);
        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
