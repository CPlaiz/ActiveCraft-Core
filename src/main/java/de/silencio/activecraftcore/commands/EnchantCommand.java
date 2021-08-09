package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EnchantCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(sender.hasPermission("activecraft.enchant")) {

                if(args.length == 1 && !args[0].equalsIgnoreCase("clear") && !args[0].equalsIgnoreCase("glint")) {
                    if (!player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                        args[0].replace("aqua_affinity", "Enchantment.WATER_WORKER");
                        args[0].replace("bane_of_arthropods", "Enchantment.DAMAGE_ARTHROPODS");
                        args[0].replace("blast_protection", "Enchantment.PROTECTION_EXPLOSIONS");
                        args[0].replace("channeling", "Enchantment.CHANNELING");
                        args[0].replace("curse_of_binding", "Enchantment.BINDING_CURSE");
                        args[0].replace("curse_of_vanishing", "Enchantment.VANISHING_CURSE");
                        args[0].replace("depth_strider", "Enchantment.DEPTH_STRIDER");
                        args[0].replace("efficiency", "Enchantment.DIG_SPEED");
                        args[0].replace("feather_falling", "Enchantment.PROTECTION_FALL");
                        args[0].replace("fire_aspect", "Enchantment.FIRE_ASPECT");
                        args[0].replace("fire_protection", "Enchantment.PROTECTION_FIRE");
                        args[0].replace("flame", "Enchantment.ARROW_FIRE");
                        args[0].replace("fortune", "Enchantment.LOOT_BONUS_BLOCKS");
                        args[0].replace("frost_walker", "Enchantment.FROST_WALKER");
                        args[0].replace("impaling", "Enchantment.IMPALING");
                        args[0].replace("infinity", "Enchantment.ARROW_INFINITE");
                        args[0].replace("knockback", "Enchantment.KNOCKBACK");
                        args[0].replace("looting", "Enchantment.LOOT_BONUS_MOBS");
                        args[0].replace("loyalty", "Enchantment.LOYALTY");
                        args[0].replace("luck_of_the_sea", "Enchantment.LUCK");
                        args[0].replace("lure", "Enchantment.LURE");
                        args[0].replace("mending", "Enchantment.MENDING");
                        args[0].replace("multishot", "Enchantment.MULTISHOT");
                        args[0].replace("piercing", "Enchantment.PIERCING");
                        args[0].replace("power", "Enchantment.ARROW_DAMAGE");
                        args[0].replace("projectile_protection", "Enchantment.PROTECTION_PROJECTILE");
                        args[0].replace("protection", "Enchantment.PROTECTION_ENVIRONMENTAL");
                        args[0].replace("punch", "Enchantment.ARROW_KNOCKBACK");
                        args[0].replace("quick_charge", "Enchantment.QUICK_CHARGE");
                        args[0].replace("respiration", "Enchantment.OXYGEN");
                        args[0].replace("riptide", "Enchantment.RIPTIDE");
                        args[0].replace("sharpness", "Enchantment.DAMAGE_ALL");
                        args[0].replace("silk_touch", "Enchantment.SILK_TOUCH");
                        args[0].replace("smite", "Enchantment.DAMAGE_UNDEAD");
                        args[0].replace("soul_speed", "Enchantment.SOUL_SPEED");
                        args[0].replace("sweeping_edge", "Enchantment.SWEEPING_EDGE");
                        args[0].replace("thorns", "Enchantment.THORNS");
                        args[0].replace("unbreaking", "Enchantment.DURABILITY");

                        int maxlevel = Objects.requireNonNull(Enchantment.getByKey(NamespacedKey.minecraft(args[0]))).getMaxLevel();

                        ItemStack eitem = player.getInventory().getItemInMainHand();
                        eitem.addUnsafeEnchantment(Objects.requireNonNull(Objects.requireNonNull(Enchantment.getByKey(NamespacedKey.minecraft(args[0])))), maxlevel);

                        player.sendMessage(ChatColor.GOLD + "Applied enchantment " + ChatColor.AQUA + args[0] + " " + maxlevel + ChatColor.GOLD + " to your item.");
                        player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);
                    } else sender.sendMessage(Errors.WARNING + "You are not holding an item.");
                }

                if(args[0].equalsIgnoreCase("clear")) {
                    if(args.length == 1) {
                        ItemStack eitem = player.getInventory().getItemInMainHand();

                        if (player.getInventory().getItemInMainHand().getEnchantments().size() > 0) {
                            for (Enchantment enchantment : Enchantment.values()) {
                                if (player.getInventory().getItemInMainHand().containsEnchantment(enchantment)) {
                                    player.getInventory().getItemInMainHand().removeEnchantment(enchantment);
                                    player.sendMessage(ChatColor.GOLD + "Cleared all enchantments from this item.");
                                    player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);
                                }
                            }
                        } else sender.sendMessage(Errors.WARNING + "Item is not enchanted!");
                    } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
                }

                if(args.length == 2 && args[0].equalsIgnoreCase("glint")) {
                    if(args[1].equalsIgnoreCase("true")) {

                        ItemStack eitem = player.getInventory().getItemInMainHand();

                        eitem.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
                        eitem.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                        player.sendMessage(ChatColor.GOLD + "Added enchantment glint to your item.");
                        player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);

                    } else if(args[1].equalsIgnoreCase("false")) {

                        ItemStack eitem = player.getInventory().getItemInMainHand();

                        eitem.removeEnchantment(Enchantment.WATER_WORKER);
                        eitem.removeItemFlags(ItemFlag.HIDE_ENCHANTS);

                        player.sendMessage(ChatColor.GOLD + "Removed enchantment glint to your item.");
                        player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);
                    } else sender.sendMessage(Errors.WARNING + "Please use true / false!");
                }

                if(args.length == 2 && !args[0].equalsIgnoreCase("glint")) {
                    if(!player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                        args[0].replace("aqua_affinity", "Enchantment.WATER_WORKER");
                        args[0].replace("bane_of_arthropods", "Enchantment.DAMAGE_ARTHROPODS");
                        args[0].replace("blast_protection", "Enchantment.PROTECTION_EXPLOSIONS");
                        args[0].replace("channeling", "Enchantment.CHANNELING");
                        args[0].replace("curse_of_binding", "Enchantment.BINDING_CURSE");
                        args[0].replace("curse_of_vanishing", "Enchantment.VANISHING_CURSE");
                        args[0].replace("depth_strider", "Enchantment.DEPTH_STRIDER");
                        args[0].replace("efficiency", "Enchantment.DIG_SPEED");
                        args[0].replace("feather_falling", "Enchantment.PROTECTION_FALL");
                        args[0].replace("fire_aspect", "Enchantment.FIRE_ASPECT");
                        args[0].replace("fire_protection", "Enchantment.PROTECTION_FIRE");
                        args[0].replace("flame", "Enchantment.ARROW_FIRE");
                        args[0].replace("fortune", "Enchantment.LOOT_BONUS_BLOCKS");
                        args[0].replace("frost_walker", "Enchantment.FROST_WALKER");
                        args[0].replace("impaling", "Enchantment.IMPALING");
                        args[0].replace("infinity", "Enchantment.ARROW_INFINITE");
                        args[0].replace("knockback", "Enchantment.KNOCKBACK");
                        args[0].replace("looting", "Enchantment.LOOT_BONUS_MOBS");
                        args[0].replace("loyalty", "Enchantment.LOYALTY");
                        args[0].replace("luck_of_the_sea", "Enchantment.LUCK");
                        args[0].replace("lure", "Enchantment.LURE");
                        args[0].replace("mending", "Enchantment.MENDING");
                        args[0].replace("multishot", "Enchantment.MULTISHOT");
                        args[0].replace("piercing", "Enchantment.PIERCING");
                        args[0].replace("power", "Enchantment.ARROW_DAMAGE");
                        args[0].replace("projectile_protection", "Enchantment.PROTECTION_PROJECTILE");
                        args[0].replace("protection", "Enchantment.PROTECTION_ENVIRONMENTAL");
                        args[0].replace("punch", "Enchantment.ARROW_KNOCKBACK");
                        args[0].replace("quick_charge", "Enchantment.QUICK_CHARGE");
                        args[0].replace("respiration", "Enchantment.OXYGEN");
                        args[0].replace("riptide", "Enchantment.RIPTIDE");
                        args[0].replace("sharpness", "Enchantment.DAMAGE_ALL");
                        args[0].replace("silk_touch", "Enchantment.SILK_TOUCH");
                        args[0].replace("smite", "Enchantment.DAMAGE_UNDEAD");
                        args[0].replace("soul_speed", "Enchantment.SOUL_SPEED");
                        args[0].replace("sweeping_edge", "Enchantment.SWEEPING_EDGE");
                        args[0].replace("thorns", "Enchantment.THORNS");
                        args[0].replace("unbreaking", "Enchantment.DURABILITY");

                        int level = Integer.parseInt(args[1]);

                        ItemStack eitem = player.getInventory().getItemInMainHand();
                        eitem.addUnsafeEnchantment(Objects.requireNonNull(Objects.requireNonNull(Enchantment.getByKey(NamespacedKey.minecraft(args[0])))), level);

                        player.sendMessage(ChatColor.GOLD + "Applied enchantment " + ChatColor.AQUA + args[0] + " " + level + ChatColor.GOLD + " to your item.");
                        player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);
                    } else sender.sendMessage(Errors.WARNING + "You are not holding an item.");
                } else if(args.length > 2) {
                    sender.sendMessage(Errors.TOO_MANY_ARGUMENTS);
                } else if(args.length == 0) {
                    sender.sendMessage(Errors.INVALID_ARGUMENTS);
                }
            } else sender.sendMessage(Errors.NO_PERMISSION);
        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        if (args.length == 1) {
                list.add("aqua_affinity");
                list.add("bane_of_arthropods");
                list.add("blast_protection");
                list.add("channeling");
                list.add("curse_of_binding");
                list.add("curse_of_vanishing");
                list.add("depth_strider");
                list.add("efficiency");
                list.add("feather_falling");
                list.add("fire_aspect");
                list.add("fire_protection");
                list.add("flame");
                list.add("fortune");
                list.add("frost_walker");
                list.add("impaling");
                list.add("infinity");
                list.add("knockback");
                list.add("looting");
                list.add("loyalty");
                list.add("luck_of_the_sea");
                list.add("lure");
                list.add("mending");
                list.add("multishot");
                list.add("piercing");
                list.add("power");
                list.add("projectile_protection");
                list.add("protection");
                list.add("punch");
                list.add("quick_charge");
                list.add("respiration");
                list.add("riptide");
                list.add("sharpness");
                list.add("silk_touch");
                list.add("smite");
                list.add("soul_speed");
                list.add("sweeping_edge");
                list.add("thorns");
                list.add("unbreaking");
                list.add("clear");
                list.add("glint");
        }

        if (args.length == 0) return list;
        if (args.length == 2 && args[0].equalsIgnoreCase("glint")) {
            list.add("true");
            list.add("false");
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