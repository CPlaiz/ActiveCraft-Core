package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MoreCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(sender.hasPermission("activecraft.more")) {

                if(args.length == 0) {
                    ItemStack is = player.getInventory().getItemInMainHand();
                    if (is.getType() != Material.AIR) {
                        is.setAmount(is.getMaxStackSize());
                    } else sender.sendMessage(Errors.WARNING() + "No Item in hand.");
                }
                if(args.length == 1) {
                    if(Integer.parseInt(args[0]) < 128) {
                        ItemStack is = player.getInventory().getItemInMainHand();
                        if (is.getType() != Material.AIR) {
                            is.setAmount(Integer.parseInt(args[0]));
                        } else sender.sendMessage(Errors.WARNING() + "No Item in hand.");
                    } else sender.sendMessage(Errors.WARNING() + "Cannot stack to this amount!");
                } if(args.length > 1) {
                    sender.sendMessage(Errors.TOO_MANY_ARGUMENTS());
                }

            } else sender.sendMessage(Errors.NO_PERMISSION());

        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }
}
