package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.ColorUtils;
import de.silencio.activecraftcore.utils.ComparisonType;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemCommand extends ActiveCraftCommand {

    public ItemCommand() {
        super("item", "i");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        Player player = getPlayer(sender);
        if (label.equalsIgnoreCase("i")) {
            checkPermission(sender, "item.give");
            checkArgsLength(args, ComparisonType.GREATER_EQUAL, 1);
            Material material = getMaterial(args[0]);
            ItemStack itemStack = new ItemStack(material);
            itemStack.setAmount(args.length >= 2 ? parseInt(args[1]) : 1);
            player.getInventory().addItem(itemStack);
            if (args.length == 1) sendMessage(sender, CommandMessages.ITEM_GIVE(itemStack.getType().name().toLowerCase()));
            else sendMessage(sender, CommandMessages.ITEM_GIVE_MULTIPLE(itemStack.getType().name().toLowerCase(), parseInt(args[1]) + ""));
            player.playSound(player.getLocation(), Sound.valueOf("BLOCK_AMETHYST_BLOCK_BREAK"), 1f, 1f);
        } else {
            checkArgsLength(args, ComparisonType.GREATER_EQUAL, 2);
            switch (args[0].toLowerCase()) {
                case "give" -> {
                    checkPermission(sender, "item.give");
                    Material material = getMaterial(args[1]);
                    ItemStack itemStack = new ItemStack(material);
                    itemStack.setAmount(args.length >= 3 ? parseInt(args[2]) : 1);
                    player.getInventory().addItem(itemStack);
                    if (args.length == 2) sendMessage(sender, CommandMessages.ITEM_GIVE(itemStack.getType().name().toLowerCase()));
                    else sendMessage(sender, CommandMessages.ITEM_GIVE_MULTIPLE(itemStack.getType().name().toLowerCase(), parseInt(args[2]) + ""));
                    player.playSound(player.getLocation(), Sound.valueOf("BLOCK_AMETHYST_BLOCK_BREAK"), 1f, 1f);
                }
                case "name" -> {
                    checkPermission(sender, "item.name");
                    if (player.getInventory().getItemInMainHand().getType() == Material.AIR)
                        sendMessage(sender, Errors.NOT_HOLDING_ITEM());
                    ItemStack stack = player.getInventory().getItemInMainHand();
                    ItemMeta meta = stack.getItemMeta();
                    meta.setDisplayName(ColorUtils.replaceFormat(ColorUtils.replaceColor(combineArray(args, 1))));
                    stack.setItemMeta(meta);
                    sendMessage(sender, CommandMessages.ITEM_RENAMED());
                }
                case "lore" -> {
                    checkPermission(sender, "item.lore");
                    checkArgsLength(args, ComparisonType.GREATER, 2);
                    if (player.getInventory().getItemInMainHand().getType() == Material.AIR)
                        sendMessage(sender, Errors.NOT_HOLDING_ITEM());
                    ItemStack stack = player.getInventory().getItemInMainHand();
                    ItemMeta meta = stack.getItemMeta();
                    List<String> stringList = new ArrayList<>();
                    if (meta.getLore() != null) stringList.addAll(meta.getLore());
                    switch (args[1]) {
                        case "add" -> {
                            stringList.add(ColorUtils.replaceFormat(ColorUtils.replaceColor(combineArray(args, 2))));
                            sendMessage(sender, CommandMessages.ITEM_LORE_ADD());
                        }
                        case "clear" -> {
                            stringList.clear();
                            sendMessage(sender, CommandMessages.ITEM_LORE_CLEAR());
                        }
                        case "set" -> {
                            stringList.clear();
                            stringList.add(ColorUtils.replaceFormat(ColorUtils.replaceColor(combineArray(args, 2))));
                            sendMessage(sender, CommandMessages.ITEM_LORE_SET());
                        }
                        case "remove" -> {
                            checkArgsLength(args, ComparisonType.GREATER_EQUAL, 3);
                            stringList.remove(parseInt(args[2]).intValue());
                            sendMessage(sender, CommandMessages.ITEM_LORE_SET());
                        }
                    }
                    meta.setLore(stringList);
                    stack.setItemMeta(meta);
                }
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (label.equalsIgnoreCase("item")) {
            if (args.length == 1) {
                if (sender.hasPermission("item.name")) list.add("name");
                if (sender.hasPermission("item.lore")) list.add("lore");
                if (sender.hasPermission("item.give")) list.add("give");
            }
            if (args[0].equalsIgnoreCase("give")) {
                if (sender.hasPermission("item.give") && args.length == 2)
                    for (Material material : Material.values()) list.add(material.name().toLowerCase());
            } else if (args[0].equalsIgnoreCase("lore"))
                if (sender.hasPermission("item.lore") && args.length == 2) {
                    list.add("set");
                    list.add("add");
                    list.add("clear");
                    list.add("remove");
                }
        } else if (label.equalsIgnoreCase("i"))
            if (sender.hasPermission("item.give") && args.length == 1)
                for (Material material : Material.values()) list.add(material.name().toLowerCase());
        return list;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        if (alias.equalsIgnoreCase("item")) {
            if (args.length == 1) {
                list.add("name");
                list.add("lore");
                list.add("give");
            }
            if (args[0].equalsIgnoreCase("give")) {
                if (args.length == 2) {
                    for (Material material : Material.values()) {
                        list.add(material.name().toLowerCase());
                    }
                }
            } else if (args[0].equalsIgnoreCase("lore")) {
                if (args.length == 2) {
                    list.add("set");
                    list.add("add");
                    list.add("clear");
                }
            }
        } else if (alias.equalsIgnoreCase("i")) {
            if (args.length == 1) {
                for (Material material : Material.values()) {
                    list.add(material.name().toLowerCase());
                }
            }
        }


        ArrayList<String> completerList = new ArrayList<>();
        String currentarg = args[args.length - 1].toLowerCase();
        for (String s : list) {
            String s1 = s.toLowerCase();
            if (s1.startsWith(currentarg)) {
                completerList.add(s);
            }
        }

        return completerList;
    }
}
