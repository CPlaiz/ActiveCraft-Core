package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EnchantCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length > 0) {
                if (sender.hasPermission("activecraft.enchant")) {
                    if (!player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {

                        if (args.length == 1 && !args[0].equalsIgnoreCase("clear") && !args[0].equalsIgnoreCase("glint") && !args[0].equalsIgnoreCase("curse_of_binding") && !args[0].equalsIgnoreCase("curse_of_vanishing")) {
                            args[0].replace("aqua_affinity", "Enchantment.WATER_WORKER");
                            args[0].replace("bane_of_arthropods", "Enchantment.DAMAGE_ARTHROPODS");
                            args[0].replace("blast_protection", "Enchantment.PROTECTION_EXPLOSIONS");
                            args[0].replace("channeling", "Enchantment.CHANNELING");
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

                            if (Enchantment.getByKey(NamespacedKey.minecraft(args[0])) == null) {
                                sender.sendMessage(Errors.WARNING() + ChatColor.GRAY + "Invalid enchantment!");
                                return false;
                            }
                            int maxlevel = Enchantment.getByKey(NamespacedKey.minecraft(args[0])).getMaxLevel();

                            ItemStack eitem = player.getInventory().getItemInMainHand();
                            eitem.addUnsafeEnchantment(Enchantment.getByKey(NamespacedKey.minecraft(args[0])), maxlevel);

                            player.sendMessage(CommandMessages.APPLIED_ENCHANTMENT(args[0], maxlevel + "").replace("_", " "));
                            player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);
                        }

                        if (args[0].equalsIgnoreCase("curse_of_binding") && args.length == 1) {
                            ItemStack eitem = player.getInventory().getItemInMainHand();
                            if (eitem.getType().equals(Material.LEATHER_BOOTS) || eitem.getType().equals(Material.LEATHER_LEGGINGS) || eitem.getType().equals(Material.LEATHER_CHESTPLATE) || eitem.getType().equals(Material.LEATHER_HELMET) ||
                                    eitem.getType().equals(Material.IRON_BOOTS) || eitem.getType().equals(Material.IRON_LEGGINGS) || eitem.getType().equals(Material.IRON_CHESTPLATE) || eitem.getType().equals(Material.IRON_HELMET) ||
                                    eitem.getType().equals(Material.CHAINMAIL_BOOTS) || eitem.getType().equals(Material.CHAINMAIL_LEGGINGS) || eitem.getType().equals(Material.CHAINMAIL_CHESTPLATE) || eitem.getType().equals(Material.CHAINMAIL_HELMET) ||
                                    eitem.getType().equals(Material.GOLDEN_BOOTS) || eitem.getType().equals(Material.GOLDEN_LEGGINGS) || eitem.getType().equals(Material.GOLDEN_CHESTPLATE) || eitem.getType().equals(Material.GOLDEN_HELMET) ||
                                    eitem.getType().equals(Material.DIAMOND_BOOTS) || eitem.getType().equals(Material.DIAMOND_LEGGINGS) || eitem.getType().equals(Material.DIAMOND_CHESTPLATE) || eitem.getType().equals(Material.DIAMOND_HELMET) ||
                                    eitem.getType().equals(Material.NETHERITE_BOOTS) || eitem.getType().equals(Material.NETHERITE_LEGGINGS) || eitem.getType().equals(Material.NETHERITE_CHESTPLATE) || eitem.getType().equals(Material.NETHERITE_HELMET) ||
                                    eitem.getType().equals(Material.ELYTRA) || eitem.getType().equals(Material.CARVED_PUMPKIN) || eitem.getType().equals(Material.DRAGON_HEAD) || eitem.getType().equals(Material.CREEPER_HEAD) ||
                                    eitem.getType().equals(Material.SKELETON_SKULL) || eitem.getType().equals(Material.WITHER_SKELETON_SKULL) || eitem.getType().equals(Material.PLAYER_HEAD) || eitem.getType().equals(Material.ZOMBIE_HEAD)) {
                                eitem.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
                                player.sendMessage(CommandMessages.APPLIED_ENCHANTMENT(args[0].toString(), 1 + "").replace("_", " "));
                                player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);
                            } else sender.sendMessage(Errors.WARNING() + CommandMessages.CANNOT_BE_APPLIED());
                        }

                        if (args[0].equalsIgnoreCase("curse_of_vanishing") && args.length == 1) {
                            ItemStack eitem = player.getInventory().getItemInMainHand();
                            eitem.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
                            player.sendMessage(CommandMessages.APPLIED_ENCHANTMENT(args[0].toString(), 1 + "").replace("_", " "));
                            player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);
                        }

                        if (args[0].equalsIgnoreCase("clear")) {
                            if (args.length == 1) {
                                ItemStack eitem = player.getInventory().getItemInMainHand();

                                if (player.getInventory().getItemInMainHand().getEnchantments().size() > 0) {
                                    for (Enchantment enchantment : Enchantment.values()) {
                                        if (player.getInventory().getItemInMainHand().containsEnchantment(enchantment)) {
                                            player.getInventory().getItemInMainHand().removeEnchantment(enchantment);
                                        }
                                    }
                                    player.sendMessage(CommandMessages.CLEARED_ALL_ENCHANTMENTS());
                                    player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);
                                } else sender.sendMessage(Errors.WARNING() + CommandMessages.NOT_ENCHANTED());
                            } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
                        }

                        if (args.length == 2 && args[0].equalsIgnoreCase("glint")) {
                            if (args[1].equalsIgnoreCase("true")) {

                                ItemStack eitem = player.getInventory().getItemInMainHand();

                                eitem.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
                                eitem.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                                player.sendMessage(CommandMessages.GLINT_TRUE());
                                player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);

                            } else if (args[1].equalsIgnoreCase("false")) {

                                ItemStack eitem = player.getInventory().getItemInMainHand();

                                eitem.removeEnchantment(Enchantment.WATER_WORKER);
                                eitem.removeItemFlags(ItemFlag.HIDE_ENCHANTS);

                                player.sendMessage(CommandMessages.GLINT_FALSE());
                                player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);
                            } else sender.sendMessage(Errors.NOT_TRUE_FALSE());
                        }

                        if (args.length == 2 && !args[0].equalsIgnoreCase("glint") && !args[0].equalsIgnoreCase("curse_of_binding") && !args[0].equalsIgnoreCase("curse_of_vanishing")) {
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

                                Integer num = null;
                                try {
                                    num = Integer.valueOf(args[1]);
                                } catch (NumberFormatException ignored) {
                                }
                                if (num == null) {
                                    sender.sendMessage(Errors.INVALID_NUMBER());
                                    return false;
                                }
                                int level = Integer.parseInt(args[1]);

                                ItemStack eitem = player.getInventory().getItemInMainHand();
                                eitem.addUnsafeEnchantment(Objects.requireNonNull(Objects.requireNonNull(Enchantment.getByKey(NamespacedKey.minecraft(args[0])))), level);

                                player.sendMessage(CommandMessages.APPLIED_ENCHANTMENT_LEVEL(args[0].toString(), level + "").replace("_", " "));
                                player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);
                            } else sender.sendMessage(Errors.NOT_HOLDING_ITEM());
                        } else if (args.length > 2) {
                            sender.sendMessage(Errors.TOO_MANY_ARGUMENTS());
                        }
                    } else sender.sendMessage(Errors.NOT_HOLDING_ITEM());
                } else sender.sendMessage(Errors.NO_PERMISSION());
            }
            if(args.length == 0) {
                sender.sendMessage(Errors.INVALID_ARGUMENTS());
            }
        } else sender.sendMessage(Errors.NOT_A_PLAYER());
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