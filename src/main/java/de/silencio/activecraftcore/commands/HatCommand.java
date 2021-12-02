package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(sender.hasPermission("activecraft.hat")) {

                ItemStack handitem = player.getInventory().getItemInMainHand();
                ItemStack helmetitem = player.getInventory().getHelmet();
                if (helmetitem == null) {
                    helmetitem = new ItemStack(Material.AIR);
                }

                ItemStack setEmptyHand = new ItemStack(Material.AIR);
                ItemStack air = new ItemStack(Material.AIR, 0);

                if (!(handitem.getType() == Material.AIR && helmetitem.getType() == Material.AIR)) {
                    player.getInventory().addItem(helmetitem);
                    player.getInventory().setHelmet(handitem);
                    player.getInventory().setItemInMainHand(setEmptyHand);
                    player.sendMessage(CommandMessages.HAT_SUCCESS());
                } else if (handitem.getType() != Material.AIR) {
                    player.getInventory().setHelmet(handitem);
                    player.getInventory().setItemInMainHand(setEmptyHand);
                    player.sendMessage(CommandMessages.HAT_SUCCESS());
                } else sender.sendMessage(Errors.NOT_HOLDING_ITEM());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }
}