package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class OpItemsCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            if (args.length == 1) {
                Player player = (Player) sender;
                if (sender.hasPermission("activecraft.opitems")) {

                    if (args[0].equalsIgnoreCase("sword")) {
                        ItemStack is = new ItemStack(Material.NETHERITE_SWORD, 1);
                        ItemMeta im = is.getItemMeta();

                        im.setDisplayName(ChatColor.GOLD + "OP Sword");
                        im.addEnchant(Enchantment.DAMAGE_ALL, 32767, true);
                        im.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 32767, true);
                        im.addEnchant(Enchantment.DAMAGE_UNDEAD, 32767, true);
                        im.addEnchant(Enchantment.FIRE_ASPECT, 32767, true);
                        im.addEnchant(Enchantment.LOOT_BONUS_MOBS, 32767, true);
                        im.addEnchant(Enchantment.SWEEPING_EDGE, 32767, true);
                        im.addEnchant(Enchantment.MENDING, 32767, true);
                        im.addEnchant(Enchantment.DURABILITY, 32767, true);
                        im.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        is.setItemMeta(im);

                        player.getInventory().addItem(is);
                    }
                    if (args[0].equalsIgnoreCase("bow")) {
                        ItemStack is = new ItemStack(Material.BOW, 1);
                        ItemMeta im = is.getItemMeta();

                        im.setDisplayName(ChatColor.GOLD + "OP Bow");
                        im.addEnchant(Enchantment.ARROW_DAMAGE, 32767, true);
                        im.addEnchant(Enchantment.ARROW_FIRE, 32767, true);
                        im.addEnchant(Enchantment.ARROW_INFINITE, 32767, true);
                        im.addEnchant(Enchantment.MENDING, 32767, true);
                        im.addEnchant(Enchantment.DURABILITY, 32767, true);
                        im.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        is.setItemMeta(im);

                        player.getInventory().addItem(is);
                    }
                    if (args[0].equalsIgnoreCase("pickaxe")) {
                        ItemStack is = new ItemStack(Material.NETHERITE_PICKAXE, 1);
                        ItemMeta im = is.getItemMeta();

                        im.setDisplayName(ChatColor.GOLD + "OP Pickaxe");
                        im.addEnchant(Enchantment.DIG_SPEED, 32767, true);
                        im.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 32767, true);
                        im.addEnchant(Enchantment.MENDING, 32767, true);
                        im.addEnchant(Enchantment.DURABILITY, 32767, true);
                        im.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        is.setItemMeta(im);

                        player.getInventory().addItem(is);
                    }
                    if (args[0].equalsIgnoreCase("axe")) {
                        ItemStack is = new ItemStack(Material.NETHERITE_AXE, 1);
                        ItemMeta im = is.getItemMeta();

                        im.setDisplayName(ChatColor.GOLD + "OP Axe");
                        im.addEnchant(Enchantment.DAMAGE_ALL, 32767, true);
                        im.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 32767, true);
                        im.addEnchant(Enchantment.DAMAGE_UNDEAD, 32767, true);
                        im.addEnchant(Enchantment.DIG_SPEED, 32767, true);
                        im.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 32767, true);
                        im.addEnchant(Enchantment.MENDING, 32767, true);
                        im.addEnchant(Enchantment.DURABILITY, 32767, true);
                        im.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        is.setItemMeta(im);

                        player.getInventory().addItem(is);
                    }
                    if (args[0].equalsIgnoreCase("shovel")) {
                        ItemStack is = new ItemStack(Material.NETHERITE_SHOVEL, 1);
                        ItemMeta im = is.getItemMeta();

                        im.setDisplayName(ChatColor.GOLD + "OP Shovel");
                        im.addEnchant(Enchantment.DIG_SPEED, 32767, true);
                        im.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 32767, true);
                        im.addEnchant(Enchantment.MENDING, 32767, true);
                        im.addEnchant(Enchantment.DURABILITY, 32767, true);
                        im.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        is.setItemMeta(im);

                        player.getInventory().addItem(is);
                    }
                    if (args[0].equalsIgnoreCase("hoe")) {
                        ItemStack is = new ItemStack(Material.NETHERITE_HOE, 1);
                        ItemMeta im = is.getItemMeta();

                        im.setDisplayName(ChatColor.GOLD + "OP Hoe");
                        im.addEnchant(Enchantment.MENDING, 32767, true);
                        im.addEnchant(Enchantment.DURABILITY, 32767, true);
                        im.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        is.setItemMeta(im);

                        player.getInventory().addItem(is);
                    }
                    if (args[0].equalsIgnoreCase("helmet")) {
                        ItemStack is = new ItemStack(Material.NETHERITE_HELMET, 1);
                        ItemMeta im = is.getItemMeta();

                        im.setDisplayName(ChatColor.GOLD + "OP Helmet");
                        im.addEnchant(Enchantment.OXYGEN, 32767, true);
                        im.addEnchant(Enchantment.WATER_WORKER, 32767, true);
                        im.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 32767, true);
                        im.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
                        im.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
                        im.addEnchant(Enchantment.PROTECTION_PROJECTILE, 32767, true);
                        im.addEnchant(Enchantment.MENDING, 32767, true);
                        im.addEnchant(Enchantment.DURABILITY, 32767, true);
                        im.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        is.setItemMeta(im);

                        player.getInventory().addItem(is);
                    }
                    if (args[0].equalsIgnoreCase("chestplate")) {
                        ItemStack is = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
                        ItemMeta im = is.getItemMeta();

                        im.setDisplayName(ChatColor.GOLD + "OP Chestplate");
                        im.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 32767, true);
                        im.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
                        im.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
                        im.addEnchant(Enchantment.PROTECTION_PROJECTILE, 32767, true);
                        im.addEnchant(Enchantment.MENDING, 32767, true);
                        im.addEnchant(Enchantment.DURABILITY, 32767, true);
                        im.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        is.setItemMeta(im);

                        player.getInventory().addItem(is);
                    }
                    if (args[0].equalsIgnoreCase("leggings")) {
                        ItemStack is = new ItemStack(Material.NETHERITE_LEGGINGS, 1);
                        ItemMeta im = is.getItemMeta();

                        im.setDisplayName(ChatColor.GOLD + "OP Leggings");
                        im.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 32767, true);
                        im.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
                        im.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
                        im.addEnchant(Enchantment.PROTECTION_PROJECTILE, 32767, true);
                        im.addEnchant(Enchantment.MENDING, 32767, true);
                        im.addEnchant(Enchantment.DURABILITY, 32767, true);
                        im.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        is.setItemMeta(im);

                        player.getInventory().addItem(is);
                    }
                    if (args[0].equalsIgnoreCase("boots")) {
                        ItemStack is = new ItemStack(Material.NETHERITE_BOOTS, 1);
                        ItemMeta im = is.getItemMeta();

                        im.setDisplayName(ChatColor.GOLD + "OP Boots");
                        im.addEnchant(Enchantment.DEPTH_STRIDER, 32767, true);
                        im.addEnchant(Enchantment.PROTECTION_FALL, 32767, true);
                        im.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 32767, true);
                        im.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
                        im.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
                        im.addEnchant(Enchantment.PROTECTION_PROJECTILE, 32767, true);
                        im.addEnchant(Enchantment.MENDING, 32767, true);
                        im.addEnchant(Enchantment.DURABILITY, 32767, true);
                        im.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        is.setItemMeta(im);

                        player.getInventory().addItem(is);
                    }
                    if (args[0].equalsIgnoreCase("crossbow")) {
                        ItemStack crossbow = new ItemStack(Material.CROSSBOW, 1);
                        ItemMeta crossbowmeta = crossbow.getItemMeta();

                        crossbowmeta.setDisplayName(ChatColor.GOLD + "OP Crossbow");
                        crossbowmeta.addEnchant(Enchantment.MULTISHOT, 3276, true);
                        crossbowmeta.addEnchant(Enchantment.QUICK_CHARGE, 5, true);
                        crossbowmeta.addEnchant(Enchantment.MENDING, 32767, true);
                        crossbowmeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        crossbowmeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        crossbowmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        crossbow.setItemMeta(crossbowmeta);

                        player.getInventory().addItem(crossbow);
                    }
                    if (args[0].equalsIgnoreCase("trident")) {
                        ItemStack is = new ItemStack(Material.TRIDENT, 1);
                        ItemMeta im = is.getItemMeta();

                        im.setDisplayName(ChatColor.GOLD + "OP Trident");
                        im.addEnchant(Enchantment.CHANNELING, 32767, true);
                        im.addEnchant(Enchantment.IMPALING, 32767, true);
                        im.addEnchant(Enchantment.LOYALTY, 3, true);
                        im.addEnchant(Enchantment.MENDING, 32767, true);
                        im.addEnchant(Enchantment.DURABILITY, 32767, true);
                        im.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        is.setItemMeta(im);

                        player.getInventory().addItem(is);
                    }

                    if (args[0].equalsIgnoreCase("armor")) {

                        ItemStack helmet = new ItemStack(Material.NETHERITE_HELMET, 1);
                        ItemMeta helmetmeta = helmet.getItemMeta();

                        helmetmeta.setDisplayName(ChatColor.GOLD + "OP Helmet");
                        helmetmeta.addEnchant(Enchantment.OXYGEN, 32767, true);
                        helmetmeta.addEnchant(Enchantment.WATER_WORKER, 32767, true);
                        helmetmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 32767, true);
                        helmetmeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
                        helmetmeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
                        helmetmeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 32767, true);
                        helmetmeta.addEnchant(Enchantment.MENDING, 32767, true);
                        helmetmeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        helmetmeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        helmetmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        helmet.setItemMeta(helmetmeta);
                        player.getInventory().addItem(helmet);


                        ItemStack chestplate = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
                        ItemMeta chestplatemeta = chestplate.getItemMeta();

                        chestplatemeta.setDisplayName(ChatColor.GOLD + "OP Chestplate");
                        chestplatemeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 32767, true);
                        chestplatemeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
                        chestplatemeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
                        chestplatemeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 32767, true);
                        chestplatemeta.addEnchant(Enchantment.MENDING, 32767, true);
                        chestplatemeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        chestplatemeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        chestplatemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        chestplate.setItemMeta(chestplatemeta);
                        player.getInventory().addItem(chestplate);


                        ItemStack leggings = new ItemStack(Material.NETHERITE_LEGGINGS, 1);
                        ItemMeta leggingsmeta = leggings.getItemMeta();

                        leggingsmeta.setDisplayName(ChatColor.GOLD + "OP Leggings");
                        leggingsmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 32767, true);
                        leggingsmeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
                        leggingsmeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
                        leggingsmeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 32767, true);
                        leggingsmeta.addEnchant(Enchantment.MENDING, 32767, true);
                        leggingsmeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        leggingsmeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        leggingsmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        leggings.setItemMeta(leggingsmeta);
                        player.getInventory().addItem(leggings);


                        ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS, 1);
                        ItemMeta bootsmeta = boots.getItemMeta();

                        bootsmeta.setDisplayName(ChatColor.GOLD + "OP Boots");
                        bootsmeta.addEnchant(Enchantment.DEPTH_STRIDER, 32767, true);
                        bootsmeta.addEnchant(Enchantment.PROTECTION_FALL, 32767, true);
                        bootsmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 32767, true);
                        bootsmeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
                        bootsmeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
                        bootsmeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 32767, true);
                        bootsmeta.addEnchant(Enchantment.MENDING, 32767, true);
                        bootsmeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        bootsmeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        bootsmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        boots.setItemMeta(bootsmeta);
                        player.getInventory().addItem(boots);
                    }

                    if (args[0].equalsIgnoreCase("weapons")) {

                        ItemStack sword = new ItemStack(Material.NETHERITE_SWORD, 1);
                        ItemMeta swordmeta = sword.getItemMeta();

                        swordmeta.setDisplayName(ChatColor.GOLD + "OP Sword");
                        swordmeta.addEnchant(Enchantment.DAMAGE_ALL, 32767, true);
                        swordmeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 32767, true);
                        swordmeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 32767, true);
                        swordmeta.addEnchant(Enchantment.FIRE_ASPECT, 32767, true);
                        swordmeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 32767, true);
                        swordmeta.addEnchant(Enchantment.SWEEPING_EDGE, 32767, true);
                        swordmeta.addEnchant(Enchantment.MENDING, 32767, true);
                        swordmeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        swordmeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        swordmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        sword.setItemMeta(swordmeta);
                        player.getInventory().addItem(sword);


                        ItemStack bow = new ItemStack(Material.BOW, 1);
                        ItemMeta bowmeta = bow.getItemMeta();

                        bowmeta.setDisplayName(ChatColor.GOLD + "OP Bow");
                        bowmeta.addEnchant(Enchantment.ARROW_DAMAGE, 32767, true);
                        bowmeta.addEnchant(Enchantment.ARROW_FIRE, 32767, true);
                        bowmeta.addEnchant(Enchantment.ARROW_INFINITE, 32767, true);
                        bowmeta.addEnchant(Enchantment.MENDING, 32767, true);
                        bowmeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        bowmeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        bowmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        bow.setItemMeta(bowmeta);
                        player.getInventory().addItem(bow);


                        ItemStack crossbow = new ItemStack(Material.CROSSBOW, 1);
                        ItemMeta crossbowmeta = crossbow.getItemMeta();

                        crossbowmeta.setDisplayName(ChatColor.GOLD + "OP Crossbow");
                        crossbowmeta.addEnchant(Enchantment.MULTISHOT, 3276, true);
                        crossbowmeta.addEnchant(Enchantment.QUICK_CHARGE, 5, true);
                        crossbowmeta.addEnchant(Enchantment.MENDING, 32767, true);
                        crossbowmeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        crossbowmeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        crossbowmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        crossbow.setItemMeta(crossbowmeta);
                        player.getInventory().addItem(crossbow);


                        ItemStack trident = new ItemStack(Material.TRIDENT, 1);
                        ItemMeta tridentmeta = trident.getItemMeta();

                        tridentmeta.setDisplayName(ChatColor.GOLD + "OP Trident");
                        tridentmeta.addEnchant(Enchantment.CHANNELING, 32767, true);
                        tridentmeta.addEnchant(Enchantment.IMPALING, 32767, true);
                        tridentmeta.addEnchant(Enchantment.LOYALTY, 3, true);
                        tridentmeta.addEnchant(Enchantment.MENDING, 32767, true);
                        tridentmeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        tridentmeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        tridentmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        trident.setItemMeta(tridentmeta);
                        player.getInventory().addItem(trident);
                    }

                    if (args[0].equalsIgnoreCase("tools")) {
                            ItemStack pickaxe = new ItemStack(Material.NETHERITE_PICKAXE, 1);
                            ItemMeta pickaxemeta = pickaxe.getItemMeta();

                            pickaxemeta.setDisplayName(ChatColor.GOLD + "OP Pickaxe");
                            pickaxemeta.addEnchant(Enchantment.DIG_SPEED, 32767, true);
                            pickaxemeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 32767, true);
                            pickaxemeta.addEnchant(Enchantment.MENDING, 32767, true);
                            pickaxemeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                            pickaxemeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                            pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            pickaxe.setItemMeta(pickaxemeta);
                            player.getInventory().addItem(pickaxe);


                            ItemStack axe = new ItemStack(Material.NETHERITE_AXE, 1);
                            ItemMeta axemeta = axe.getItemMeta();

                            axemeta.setDisplayName(ChatColor.GOLD + "OP Axe");
                            axemeta.addEnchant(Enchantment.DAMAGE_ALL, 32767, true);
                            axemeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 32767, true);
                            axemeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 32767, true);
                            axemeta.addEnchant(Enchantment.DIG_SPEED, 32767, true);
                            axemeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 32767, true);
                            axemeta.addEnchant(Enchantment.MENDING, 32767, true);
                            axemeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                            axemeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                            axemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            axe.setItemMeta(axemeta);
                            player.getInventory().addItem(axe);


                            ItemStack shovel = new ItemStack(Material.NETHERITE_SHOVEL, 1);
                            ItemMeta shovelmeta = shovel.getItemMeta();

                            shovelmeta.setDisplayName(ChatColor.GOLD + "OP Shovel");
                            shovelmeta.addEnchant(Enchantment.DIG_SPEED, 32767, true);
                            shovelmeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 32767, true);
                            shovelmeta.addEnchant(Enchantment.MENDING, 32767, true);
                            shovelmeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                            shovelmeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                            shovelmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            shovel.setItemMeta(shovelmeta);
                            player.getInventory().addItem(shovel);


                            ItemStack hoe = new ItemStack(Material.NETHERITE_HOE, 1);
                            ItemMeta hoemeta = hoe.getItemMeta();

                            hoemeta.setDisplayName(ChatColor.GOLD + "OP Hoe");
                            hoemeta.addEnchant(Enchantment.MENDING, 32767, true);
                            hoemeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                            hoemeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                            hoemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            hoe.setItemMeta(hoemeta);
                            player.getInventory().addItem(hoe);
                        }
                    }
                    if (args[0].equalsIgnoreCase("all")) {

                        ItemStack sword = new ItemStack(Material.NETHERITE_SWORD, 1);
                        ItemMeta swordmeta = sword.getItemMeta();

                        swordmeta.setDisplayName(ChatColor.GOLD + "OP Sword");
                        swordmeta.addEnchant(Enchantment.DAMAGE_ALL, 32767, true);
                        swordmeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 32767, true);
                        swordmeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 32767, true);
                        swordmeta.addEnchant(Enchantment.FIRE_ASPECT, 32767, true);
                        swordmeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 32767, true);
                        swordmeta.addEnchant(Enchantment.SWEEPING_EDGE, 32767, true);
                        swordmeta.addEnchant(Enchantment.MENDING, 32767, true);
                        swordmeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        swordmeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        swordmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        sword.setItemMeta(swordmeta);
                        player.getInventory().addItem(sword);


                        ItemStack bow = new ItemStack(Material.BOW, 1);
                        ItemMeta bowmeta = bow.getItemMeta();

                        bowmeta.setDisplayName(ChatColor.GOLD + "OP Bow");
                        bowmeta.addEnchant(Enchantment.ARROW_DAMAGE, 32767, true);
                        bowmeta.addEnchant(Enchantment.ARROW_FIRE, 32767, true);
                        bowmeta.addEnchant(Enchantment.ARROW_INFINITE, 32767, true);
                        bowmeta.addEnchant(Enchantment.MENDING, 32767, true);
                        bowmeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        bowmeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        bowmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        bow.setItemMeta(bowmeta);
                        player.getInventory().addItem(bow);


                        ItemStack pickaxe = new ItemStack(Material.NETHERITE_PICKAXE, 1);
                        ItemMeta pickaxemeta = pickaxe.getItemMeta();

                        pickaxemeta.setDisplayName(ChatColor.GOLD + "OP Pickaxe");
                        pickaxemeta.addEnchant(Enchantment.DIG_SPEED, 32767, true);
                        pickaxemeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 32767, true);
                        pickaxemeta.addEnchant(Enchantment.MENDING, 32767, true);
                        pickaxemeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        pickaxemeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        pickaxemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        pickaxe.setItemMeta(pickaxemeta);
                        player.getInventory().addItem(pickaxe);


                        ItemStack axe = new ItemStack(Material.NETHERITE_AXE, 1);
                        ItemMeta axemeta = axe.getItemMeta();

                        axemeta.setDisplayName(ChatColor.GOLD + "OP Axe");
                        axemeta.addEnchant(Enchantment.DAMAGE_ALL, 32767, true);
                        axemeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 32767, true);
                        axemeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 32767, true);
                        axemeta.addEnchant(Enchantment.DIG_SPEED, 32767, true);
                        axemeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 32767, true);
                        axemeta.addEnchant(Enchantment.MENDING, 32767, true);
                        axemeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        axemeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        axemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        axe.setItemMeta(axemeta);
                        player.getInventory().addItem(axe);


                        ItemStack shovel = new ItemStack(Material.NETHERITE_SHOVEL, 1);
                        ItemMeta shovelmeta = shovel.getItemMeta();

                        shovelmeta.setDisplayName(ChatColor.GOLD + "OP Shovel");
                        shovelmeta.addEnchant(Enchantment.DIG_SPEED, 32767, true);
                        shovelmeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 32767, true);
                        shovelmeta.addEnchant(Enchantment.MENDING, 32767, true);
                        shovelmeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        shovelmeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        shovelmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        shovel.setItemMeta(shovelmeta);
                        player.getInventory().addItem(shovel);


                        ItemStack helmet = new ItemStack(Material.NETHERITE_HELMET, 1);
                        ItemMeta helmetmeta = helmet.getItemMeta();

                        helmetmeta.setDisplayName(ChatColor.GOLD + "OP Helmet");
                        helmetmeta.addEnchant(Enchantment.OXYGEN, 32767, true);
                        helmetmeta.addEnchant(Enchantment.WATER_WORKER, 32767, true);
                        helmetmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 32767, true);
                        helmetmeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
                        helmetmeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
                        helmetmeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 32767, true);
                        helmetmeta.addEnchant(Enchantment.MENDING, 32767, true);
                        helmetmeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        helmetmeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        helmetmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        helmet.setItemMeta(helmetmeta);
                        player.getInventory().addItem(helmet);


                        ItemStack chestplate = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
                        ItemMeta chestplatemeta = chestplate.getItemMeta();

                        chestplatemeta.setDisplayName(ChatColor.GOLD + "OP Chestplate");
                        chestplatemeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 32767, true);
                        chestplatemeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
                        chestplatemeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
                        chestplatemeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 32767, true);
                        chestplatemeta.addEnchant(Enchantment.MENDING, 32767, true);
                        chestplatemeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        chestplatemeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        chestplatemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        chestplate.setItemMeta(chestplatemeta);
                        player.getInventory().addItem(chestplate);


                        ItemStack leggings = new ItemStack(Material.NETHERITE_LEGGINGS, 1);
                        ItemMeta leggingsmeta = leggings.getItemMeta();

                        leggingsmeta.setDisplayName(ChatColor.GOLD + "OP Leggings");
                        leggingsmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 32767, true);
                        leggingsmeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
                        leggingsmeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
                        leggingsmeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 32767, true);
                        leggingsmeta.addEnchant(Enchantment.MENDING, 32767, true);
                        leggingsmeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        leggingsmeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        leggingsmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        leggings.setItemMeta(leggingsmeta);
                        player.getInventory().addItem(leggings);


                        ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS, 1);
                        ItemMeta bootsmeta = boots.getItemMeta();

                        bootsmeta.setDisplayName(ChatColor.GOLD + "OP Boots");
                        bootsmeta.addEnchant(Enchantment.DEPTH_STRIDER, 32767, true);
                        bootsmeta.addEnchant(Enchantment.PROTECTION_FALL, 32767, true);
                        bootsmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 32767, true);
                        bootsmeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 32767, true);
                        bootsmeta.addEnchant(Enchantment.PROTECTION_FIRE, 32767, true);
                        bootsmeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 32767, true);
                        bootsmeta.addEnchant(Enchantment.MENDING, 32767, true);
                        bootsmeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        bootsmeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        bootsmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        boots.setItemMeta(bootsmeta);
                        player.getInventory().addItem(boots);


                        ItemStack crossbow = new ItemStack(Material.CROSSBOW, 1);
                        ItemMeta crossbowmeta = crossbow.getItemMeta();

                        crossbowmeta.setDisplayName(ChatColor.GOLD + "OP Crossbow");
                        crossbowmeta.addEnchant(Enchantment.MULTISHOT, 3276, true);
                        crossbowmeta.addEnchant(Enchantment.QUICK_CHARGE, 5, true);
                        crossbowmeta.addEnchant(Enchantment.MENDING, 32767, true);
                        crossbowmeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        crossbowmeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        crossbowmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        crossbow.setItemMeta(crossbowmeta);
                        player.getInventory().addItem(crossbow);


                        ItemStack trident = new ItemStack(Material.TRIDENT, 1);
                        ItemMeta tridentmeta = trident.getItemMeta();

                        tridentmeta.setDisplayName(ChatColor.GOLD + "OP Trident");
                        tridentmeta.addEnchant(Enchantment.CHANNELING, 32767, true);
                        tridentmeta.addEnchant(Enchantment.IMPALING, 32767, true);
                        tridentmeta.addEnchant(Enchantment.LOYALTY, 3, true);
                        tridentmeta.addEnchant(Enchantment.MENDING, 32767, true);
                        tridentmeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        tridentmeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        tridentmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        trident.setItemMeta(tridentmeta);
                        player.getInventory().addItem(trident);


                        ItemStack hoe = new ItemStack(Material.NETHERITE_HOE, 1);
                        ItemMeta hoemeta = hoe.getItemMeta();

                        hoemeta.setDisplayName(ChatColor.GOLD + "OP Hoe");
                        hoemeta.addEnchant(Enchantment.MENDING, 32767, true);
                        hoemeta.addEnchant(Enchantment.DURABILITY, 32767, true);
                        hoemeta.addEnchant(Enchantment.VANISHING_CURSE, 32767, true);
                        hoemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        hoe.setItemMeta(hoemeta);
                        player.getInventory().addItem(hoe);
                    }
                }else sender.sendMessage(Errors.INVALID_ARGUMENTS);
            } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
        }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        if (args.length == 1) {
            list.add("sword");
            list.add("bow");
            list.add("crossbow");
            list.add("pickaxe");
            list.add("axe");
            list.add("shovel");
            list.add("hoe");
            list.add("helmet");
            list.add("chestplate");
            list.add("leggings");
            list.add("boots");
            list.add("tools");
            list.add("armor");
            list.add("weapons");
            list.add("all");
        }
        ArrayList<String> completerList = new ArrayList<>();
        String currentarg = args[args.length-1].toLowerCase();
        for (String s : list) {
            String s1 = s.toLowerCase();
            if (s1.startsWith(currentarg)){
                completerList.add(s);
            }
        }

        return completerList;
    }
    }