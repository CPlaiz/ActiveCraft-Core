package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.utils.ComparisonType;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OpItemsCommand extends ActiveCraftCommand {

    public OpItemsCommand() {
        super("opitems");
    }

    public static class OpItem extends ItemStack {

        enum Class {
            WEAPON,
            ARMOR,
            TOOL,
            NONE
        }

        OpItem(Material material, String name, Class... c) {
            super(material);
            ItemMeta meta = getItemMeta();
            meta.setDisplayName("§r§b§kO§r §6" + name + " §b§kO");
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            setItemMeta(meta);
            addEnchant(Enchantment.VANISHING_CURSE);
            addEnchant(Enchantment.MENDING);
            addEnchant(Enchantment.DURABILITY);
            for (Class cl : c)
                switch (cl) {
                    case ARMOR -> {
                        addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
                        addEnchant(Enchantment.PROTECTION_EXPLOSIONS);
                        addEnchant(Enchantment.PROTECTION_FIRE);
                        addEnchant(Enchantment.PROTECTION_PROJECTILE);
                    }
                    case WEAPON -> {
                        addEnchant(Enchantment.DAMAGE_ALL);
                        addEnchant(Enchantment.DAMAGE_ARTHROPODS);
                        addEnchant(Enchantment.DAMAGE_UNDEAD);
                    }
                    case TOOL -> {
                        addEnchant(Enchantment.LOOT_BONUS_BLOCKS);
                        addEnchant(Enchantment.DIG_SPEED);
                    }
                }
        }

        public OpItem addEnchant(Enchantment enchantment) {
            ItemMeta meta = getItemMeta();
            meta.addEnchant(enchantment, 32767, true);
            setItemMeta(meta);
            return this;
        }

        public OpItem addEnchant(Enchantment enchantment, int i) {
            ItemMeta meta = getItemMeta();
            meta.addEnchant(enchantment, i, true);
            setItemMeta(meta);
            return this;
        }
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        Player player = getPlayer(sender);
        checkArgsLength(args, ComparisonType.GREATER_EQUAL, 1);
        checkPermission(sender, "opitems");
        OpItem sword = new OpItem(Material.NETHERITE_SWORD,  "OP Sword", OpItem.Class.WEAPON)
                .addEnchant(Enchantment.LOOT_BONUS_MOBS)
                .addEnchant(Enchantment.SWEEPING_EDGE)
                .addEnchant(Enchantment.FIRE_ASPECT);
        OpItem axe = new OpItem(Material.NETHERITE_AXE, "OP Axe", OpItem.Class.TOOL, OpItem.Class.WEAPON);
        OpItem pickaxe = new OpItem(Material.NETHERITE_PICKAXE, "OP Pickaxe", OpItem.Class.TOOL);
        OpItem hoe = new OpItem(Material.NETHERITE_HOE, "OP Hoe", OpItem.Class.NONE);
        OpItem shovel = new OpItem(Material.NETHERITE_SHOVEL, "OP Shovel", OpItem.Class.TOOL);
        OpItem bow = new OpItem(Material.BOW, "OP Bow", OpItem.Class.NONE)
                .addEnchant(Enchantment.ARROW_INFINITE)
                .addEnchant(Enchantment.ARROW_FIRE)
                .addEnchant(Enchantment.ARROW_DAMAGE);
        OpItem crossbow = new OpItem(Material.CROSSBOW, "OP Crossbow", OpItem.Class.NONE)
                .addEnchant(Enchantment.MULTISHOT)
                .addEnchant(Enchantment.QUICK_CHARGE, 5);
        OpItem trident = new OpItem(Material.TRIDENT, "OP Trident", OpItem.Class.NONE)
                .addEnchant(Enchantment.LOYALTY, 3)
                .addEnchant(Enchantment.IMPALING)
                .addEnchant(Enchantment.CHANNELING);
        OpItem helmet = new OpItem(Material.NETHERITE_HELMET, "OP Helmet", OpItem.Class.ARMOR)
                .addEnchant(Enchantment.WATER_WORKER)
                .addEnchant(Enchantment.OXYGEN);
        OpItem chestplate = new OpItem(Material.NETHERITE_CHESTPLATE, "OP Chestplate", OpItem.Class.ARMOR);
        OpItem leggins = new OpItem(Material.NETHERITE_LEGGINGS, "OP Leggins", OpItem.Class.ARMOR);
        OpItem boots = new OpItem(Material.NETHERITE_BOOTS, "OP Boots", OpItem.Class.ARMOR)
                .addEnchant(Enchantment.PROTECTION_FALL)
                .addEnchant(Enchantment.SOUL_SPEED, 5)
                .addEnchant(Enchantment.DEPTH_STRIDER);
        OpItem[] itemList;
        switch (args[0].toLowerCase()) {
            case "all" -> itemList = new OpItem[]{sword, bow, crossbow, trident, pickaxe, axe, shovel, hoe, helmet, chestplate, leggins, boots};
            case "tools" -> itemList = new OpItem[]{pickaxe, axe, shovel, hoe};
            case "armor" -> itemList = new OpItem[]{helmet, chestplate, leggins, boots};
            case "weapons" -> itemList = new OpItem[]{sword, bow, crossbow, trident};
            case "sword" -> itemList = new OpItem[]{sword};
            case "bow" -> itemList = new OpItem[]{bow};
            case "crossbow" -> itemList = new OpItem[]{crossbow};
            case "trident" -> itemList = new OpItem[]{trident};
            case "axe" -> itemList = new OpItem[]{axe};
            case "pickaxe" -> itemList = new OpItem[]{pickaxe};
            case "hoe" -> itemList = new OpItem[]{hoe};
            case "shovel" -> itemList = new OpItem[]{shovel};
            case "helmet" -> itemList = new OpItem[]{helmet};
            case "chestplate" -> itemList = new OpItem[]{chestplate};
            case "leggins" -> itemList = new OpItem[]{leggins};
            case "boots" -> itemList = new OpItem[]{boots};
            default -> itemList = new OpItem[]{};
        }
        for (OpItem item : itemList) {
            player.getInventory().addItem(item);
            player.playSound(player.getLocation(), Sound.valueOf("BLOCK_AMETHYST_BLOCK_BREAK"), 1f, 1f);
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>(Arrays.stream(new String[]{
                "sword", "bow", "crossbow", "pickaxe", "axe", "shovel", "hoe", "helmet", "chestplate", "leggins", "boots",
                "tools", "tools", "armor", "weapons", "all"}).toList());
    }
}