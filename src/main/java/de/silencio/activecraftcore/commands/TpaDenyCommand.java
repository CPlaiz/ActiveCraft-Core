package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaDenyCommand extends TpaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            if(args.length == 0) {
                if (sender.hasPermission("activecraft.tpadeny")) {
                    if (tpaList.containsKey(sender)) {

                        Player player = (Player) sender;
                        Player target = tpaList.get(sender);

                        player.sendMessage(ChatColor.GOLD + "Denied the TPA request from " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + ".");
                        target.sendMessage(ChatColor.AQUA + player.getDisplayName() + ChatColor.GOLD + "Denied your TPA request.");

                        tpaList.remove(sender);

                    } else sender.sendMessage(Errors.WARNING + " You don't have any pending TPA requests!");
                } else sender.sendMessage(Errors.NO_PERMISSION);
            } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
