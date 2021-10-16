package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.projectiles.ProjectileSource;

import java.util.ArrayList;
import java.util.List;

public class BowCommand implements CommandExecutor, Listener, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length != 1) return false;

        if(args[0].equalsIgnoreCase("explode")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (sender.hasPermission("activecraft.bow.explode")) {

                    ItemStack boombow = new ItemStack(Material.BOW, 1);
                    ItemMeta boombowmeta = boombow.getItemMeta();

                    boombowmeta.setDisplayName(ChatColor.GOLD + "Boom Bow");
                    boombow.setItemMeta(boombowmeta);

                    boombow.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
                    boombow.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                    player.getInventory().addItem(boombow);

                } else sender.sendMessage(Errors.NO_PERMISSION());

            } else sender.sendMessage(Errors.NOT_A_PLAYER());
        }

        if(args[0].equalsIgnoreCase("lightning")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (sender.hasPermission("activecraft.bow.lightning")) {

                    ItemStack lightningbow = new ItemStack(Material.BOW, 1);
                    ItemMeta lightningbowbowmeta = lightningbow.getItemMeta();

                    lightningbowbowmeta.setDisplayName(ChatColor.GOLD + "Lightning Bow");
                    lightningbow.setItemMeta(lightningbowbowmeta);

                    lightningbow.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
                    lightningbow.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                    player.getInventory().addItem(lightningbow);

                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.NOT_A_PLAYER());
        }
        return true;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile entity = event.getEntity();
        ProjectileSource source = entity.getShooter();

        if (!(source instanceof Player)) return;
        if (!(entity.getType() == EntityType.ARROW)) return;
        Player player = (Player) source;
        ItemStack item = player.getInventory().getItemInMainHand();

        if(item.getType() != Material.BOW) return;
        ItemMeta itemMeta = item.getItemMeta();

        if(!item.hasItemFlag(ItemFlag.HIDE_ENCHANTS)) return;

        if(player.hasPermission("activecraft.bow.explode.trigger") && itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Boom Bow")) {
            entity.getWorld().createExplosion(entity.getLocation(), 5F, false, true);
        }
        if(player.hasPermission("activecraft.bow.lightning.trigger") && itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Lightning Bow")) {
            entity.getWorld().strikeLightning(entity.getLocation());
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Player p = (Player) sender;

        if (args.length == 0) return list;
        if (args.length == 1) {
            list.add("explode");
            list.add("lightning");
        }
        return list;
    }
}