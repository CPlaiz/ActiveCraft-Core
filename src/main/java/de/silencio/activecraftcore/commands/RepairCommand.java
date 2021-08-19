package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class RepairCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {

            Player player = (Player) sender;

            if(sender.hasPermission("activecraft.repair")) {

                    ItemStack item = player.getInventory().getItemInMainHand();
                    //System.out.println(item.getType().toString());
                    ItemMeta meta = item.getItemMeta();
                    if (meta instanceof Damageable) {
                        ((Damageable) meta).setDamage(0);
                    }
                    item.setItemMeta(meta);

            } else sender.sendMessage(Errors.NO_PERMISSION);

        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}