package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class KnockbackStickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (sender.hasPermission("activecraft.knockbackstick.self")) {
                    ItemStack stick = new ItemStack(Material.STICK);
                    ItemMeta stickmeta = stick.getItemMeta();
                    stickmeta.setDisplayName(ChatColor.GOLD + "Knockback Stick");
                    stickmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    stickmeta.setLore(Collections.singletonList(CommandMessages.KNOCKBACKSTICK_LORE()));
                    stick.setItemMeta(stickmeta);
                    stick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 255);
                    player.getInventory().addItem(stick);
                    player.sendMessage(CommandMessages.KNOCKBACKSTICK());

                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.NOT_A_PLAYER());
        } else if(args.length == 1) {
            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(Errors.INVALID_PLAYER());
                return false;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (sender.hasPermission("activecraft.knockbackstick.others")) {
                if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                    if (!sender.hasPermission("activecraft.knockbackstick.self")) {
                        sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                        return false;
                    }
                }
                ItemStack stick = new ItemStack(Material.STICK);
                ItemMeta stickmeta = stick.getItemMeta();
                stickmeta.setDisplayName(ChatColor.GOLD + "Knockback Stick");
                stickmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                stickmeta.setLore(Collections.singletonList(CommandMessages.KNOCKBACKSTICK_LORE()));
                stick.setItemMeta(stickmeta);
                stick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 255);
                target.getInventory().addItem(stick);
                target.sendMessage(CommandMessages.KNOCKBACKSTICK_OTHERS_MESSAGE(sender));
                sender.sendMessage(CommandMessages.KNOCKBACKSTICK_OTHERS(target));

            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else sender.sendMessage(Errors.TOO_MANY_ARGUMENTS());
        return true;
    }
}