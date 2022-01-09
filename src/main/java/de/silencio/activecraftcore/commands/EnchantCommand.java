package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.exceptions.NotHoldingItemException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.ComparisonType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnchantCommand extends ActiveCraftCommand {

    public EnchantCommand() {
        super("enchant");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {

        checkPermission(sender, "enchant");
        Player player = getPlayer(sender);
        checkArgsLength(args, ComparisonType.NOT_EQUAL, 0);
        if (player.getInventory().getItemInMainHand().getType().equals(Material.AIR))
            throw new NotHoldingItemException(player, NotHoldingItemException.ExpectedItem.ANY);

        Material[] armorMaterials = new Material[]{
                Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET,
                Material.IRON_BOOTS, Material.IRON_LEGGINGS, Material.IRON_CHESTPLATE, Material.IRON_HELMET,
                Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET,
                Material.GOLDEN_BOOTS, Material.GOLDEN_LEGGINGS, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_HELMET,
                Material.DIAMOND_BOOTS, Material.DIAMOND_LEGGINGS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET,
                Material.NETHERITE_BOOTS, Material.NETHERITE_LEGGINGS, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_HELMET,
                Material.ELYTRA, Material.CARVED_PUMPKIN, Material.DRAGON_HEAD, Material.CREEPER_HEAD,
                Material.SKELETON_SKULL, Material.WITHER_SKELETON_SKULL, Material.PLAYER_HEAD, Material.ZOMBIE_HEAD};

        switch (args[0].toLowerCase()) {
            case "clear" -> {
                if (player.getInventory().getItemInMainHand().getEnchantments().size() > 0) {
                    for (Enchantment enchantment : Enchantment.values())
                        if (player.getInventory().getItemInMainHand().containsEnchantment(enchantment))
                            player.getInventory().getItemInMainHand().removeEnchantment(enchantment);
                    sendMessage(sender, CommandMessages.CLEARED_ALL_ENCHANTMENTS());
                    player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);
                } else sendMessage(sender, Errors.WARNING() + CommandMessages.NOT_ENCHANTED());
            }
            case "glint" -> {
                checkArgsLength(args, ComparisonType.GREATER_EQUAL, 2);
                if (args[1].equalsIgnoreCase("true")) {

                    ItemStack eitem = player.getInventory().getItemInMainHand();

                    eitem.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
                    eitem.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                    sendMessage(sender, CommandMessages.GLINT_TRUE());
                    player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);

                } else if (args[1].equalsIgnoreCase("false")) {

                    ItemStack eitem = player.getInventory().getItemInMainHand();

                    eitem.removeEnchantment(Enchantment.WATER_WORKER);
                    eitem.removeItemFlags(ItemFlag.HIDE_ENCHANTS);

                    sendMessage(sender, CommandMessages.GLINT_FALSE());
                    player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);
                } else sendMessage(sender, Errors.NOT_TRUE_FALSE());
            }
            case "curse_of_vanishing" -> {
                ItemStack item = player.getInventory().getItemInMainHand();
                item.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
                sendMessage(sender, CommandMessages.APPLIED_ENCHANTMENT(Enchantment.VANISHING_CURSE.getName(), 1 + "").replace("_", " "));
                player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);
            }
            case "curse_of_binding" -> {
                ItemStack item = player.getInventory().getItemInMainHand();
                if (Arrays.stream(armorMaterials).toList().contains(item.getType()))
                    sendMessage(sender, Errors.WARNING() + CommandMessages.CANNOT_BE_APPLIED());
                item.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
                sendMessage(sender, CommandMessages.APPLIED_ENCHANTMENT(Enchantment.BINDING_CURSE.getName(), 1 + "").replace("_", " "));
                player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);
            }
            default -> {
                Enchantment enchantment = switch (args[0].toLowerCase()) {
                    case "aqua_affinity" -> Enchantment.WATER_WORKER;
                    case "bane_of_arthropods" -> Enchantment.DAMAGE_ARTHROPODS;
                    case "blast_protection" -> Enchantment.PROTECTION_EXPLOSIONS;
                    case "channeling" -> Enchantment.CHANNELING;
                    case "depth_strider" -> Enchantment.DEPTH_STRIDER;
                    case "efficiency" -> Enchantment.DIG_SPEED;
                    case "feather_falling" -> Enchantment.PROTECTION_FALL;
                    case "fire_aspect" -> Enchantment.FIRE_ASPECT;
                    case "fire_protection" -> Enchantment.PROTECTION_FIRE;
                    case "flame" -> Enchantment.ARROW_FIRE;
                    case "fortune" -> Enchantment.LOOT_BONUS_BLOCKS;
                    case "frost_walker" -> Enchantment.FROST_WALKER;
                    case "impaling" -> Enchantment.IMPALING;
                    case "infinity" -> Enchantment.ARROW_INFINITE;
                    case "knockback" -> Enchantment.KNOCKBACK;
                    case "looting" -> Enchantment.LOOT_BONUS_MOBS;
                    case "loyalty" -> Enchantment.LOYALTY;
                    case "luck_of_the_sea" -> Enchantment.LUCK;
                    case "lure" -> Enchantment.LURE;
                    case "mending" -> Enchantment.MENDING;
                    case "multishot" -> Enchantment.MULTISHOT;
                    case "piercing" -> Enchantment.PIERCING;
                    case "power" -> Enchantment.ARROW_DAMAGE;
                    case "projectile_protection" -> Enchantment.PROTECTION_PROJECTILE;
                    case "punch" -> Enchantment.PROTECTION_ENVIRONMENTAL;
                    case "quick_charge" -> Enchantment.ARROW_KNOCKBACK;
                    case "respiration" -> Enchantment.QUICK_CHARGE;
                    case "riptide" -> Enchantment.OXYGEN;
                    case "protection" -> Enchantment.RIPTIDE;
                    case "sharpness" -> Enchantment.DAMAGE_ALL;
                    case "silk_touch" -> Enchantment.SILK_TOUCH;
                    case "smite" -> Enchantment.DAMAGE_UNDEAD;
                    case "soul_speed" -> Enchantment.SOUL_SPEED;
                    case "sweeping_edge" -> Enchantment.SWEEPING_EDGE;
                    case "thorns" -> Enchantment.THORNS;
                    case "unbreaking" -> Enchantment.DURABILITY;
                    default -> null;
                };


                if (enchantment == null) {
                    sendMessage(sender, Errors.WARNING() + ChatColor.GRAY + "Invalid enchantment!");
                    return;
                }
                ItemStack item = player.getInventory().getItemInMainHand();
                int maxlvl = args.length == 1 ? enchantment.getMaxLevel() : parseInt(args[1]);
                item.addUnsafeEnchantment(enchantment, maxlvl);
                sendMessage(sender, CommandMessages.APPLIED_ENCHANTMENT(args[0], maxlvl + "").replace("_", " "));
                player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);
            }
        }

    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 1) {
            for (Enchantment enchantment : Enchantment.values())
                list.add(enchantment.getName());
            list.add("clear");
            list.add("glint");
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("glint")) {
            list.add("true");
            list.add("false");
        }
        return list;
    }
}