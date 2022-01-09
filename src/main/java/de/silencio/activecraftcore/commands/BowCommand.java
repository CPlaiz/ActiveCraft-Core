package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.utils.ComparisonType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
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

public class BowCommand extends ActiveCraftCommand implements Listener {

    public BowCommand() {
        super("bow");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkArgsLength(args, ComparisonType.GREATER_EQUAL, 1);
        switch (args[0].toLowerCase()) {
            case "explode" -> {
                Player player = getPlayer(sender);
                checkPermission(sender, "bow.explode");

                ItemStack boombow = new ItemStack(Material.BOW);
                ItemMeta boombowmeta = boombow.getItemMeta();
                boombowmeta.setDisplayName(ChatColor.GOLD + "Boom Bow");
                boombowmeta.setUnbreakable(true);
                boombow.setItemMeta(boombowmeta);
                boombow.addUnsafeEnchantment(Enchantment.WATER_WORKER, 124);
                boombow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                boombow.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                player.getInventory().addItem(boombow);
            }
            case "lightning" -> {
                Player player = getPlayer(sender);
                checkPermission(sender, "bow.explode");

                ItemStack lightningbow = new ItemStack(Material.BOW);
                ItemMeta lightningbowbowmeta = lightningbow.getItemMeta();
                lightningbowbowmeta.setDisplayName(ChatColor.GOLD + "Lightning Bow");
                lightningbowbowmeta.setUnbreakable(true);
                lightningbow.setItemMeta(lightningbowbowmeta);
                lightningbow.addUnsafeEnchantment(Enchantment.WATER_WORKER, 124);
                lightningbow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                lightningbow.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                player.getInventory().addItem(lightningbow);

            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Player p = (Player) sender;

        if (args.length == 0) return list;
        if (args.length == 1) {
            list.add("explode");
            list.add("lightning");
        }
        return list;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile entity = event.getEntity();
        ProjectileSource source = entity.getShooter();

        if (!(source instanceof Player player)) return;
        if (!(entity.getType() == EntityType.ARROW)) return;
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getType() != Material.BOW) return;
        ItemMeta itemMeta = item.getItemMeta();

        if (!item.hasItemFlag(ItemFlag.HIDE_ENCHANTS)) return;
        if (!itemMeta.hasEnchant(Enchantment.WATER_WORKER)) return;
        if (!(itemMeta.getEnchantLevel(Enchantment.WATER_WORKER) == 124)) return;

        if (player.hasPermission("activecraft.bow.explode.trigger") && itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Boom Bow")) {
            entity.getWorld().createExplosion(entity.getLocation(), 5F, false, true);
            entity.remove();
        }
        if (player.hasPermission("activecraft.bow.lightning.trigger") && itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Lightning Bow")) {
            entity.getWorld().strikeLightning(entity.getLocation());
            entity.remove();
        }
    }
}