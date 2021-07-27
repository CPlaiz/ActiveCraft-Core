package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {

            if(args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                Player player = (Player) sender;

                if (sender.hasPermission("activecraft.tp")) {

                    player.teleport(target.getLocation());
                    player.sendMessage(ChatColor.GOLD + "Teleported to " + ChatColor.AQUA + target.getDisplayName());
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);

                } else sender.sendMessage(Errors.NO_PERMISSION);
            } else if(args.length == 0) {
                sender.sendMessage(Errors.INVALID_ARGUMENTS);
            } else if(args.length > 1) {
                sender.sendMessage(Errors.TOO_MANY_ARGUMENTS);
            }
        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
