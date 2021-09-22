package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
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
                    if(!item.getType().equals(Material.AIR)) {
                        ItemMeta meta = item.getItemMeta();
                        if (meta instanceof Damageable) {
                            ((Damageable) meta).setDamage(0);
                            item.setItemMeta(meta);
                            sender.sendMessage(ChatColor.GOLD + "Fixed your " + ChatColor.AQUA + meta.getDisplayName() + ChatColor.GOLD + ".");
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1f, 1f);
                        } else sender.sendMessage(Errors.WARNING + "Item can't be repaired!");
                    } else sender.sendMessage(Errors.WARNING + "No item in hand!");

            } else sender.sendMessage(Errors.NO_PERMISSION);

        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}