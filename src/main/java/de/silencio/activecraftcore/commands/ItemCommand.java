package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args[0].equalsIgnoreCase("give")) {
                if (sender.hasPermission("activecraft.item.give")) {

                    if (args.length == 2) {
                        if (Material.getMaterial(args[1]) == null) {
                            sender.sendMessage(Errors.INVALID_ARGUMENTS());
                            return false;
                        }
                        Material material = Material.getMaterial(args[1].toUpperCase());
                        ItemStack itemStack = new ItemStack(material);
                        player.getInventory().addItem(itemStack);
                        sender.sendMessage(ChatColor.GOLD + "Gave you " + ChatColor.AQUA + itemStack.getType().name().toLowerCase());
                        player.playSound(player.getLocation(), Sound.valueOf("BLOCK_AMETHYST_BLOCK_BREAK"), 1f, 1f);
                    }
                    if (args.length == 3) {
                        if (Material.getMaterial(args[1]) == null) {
                            sender.sendMessage(Errors.WARNING() + "You need to hold an item in your hand!");
                            return false;
                        }
                        Material material = Material.getMaterial(args[1].toUpperCase());
                        ItemStack itemStack = new ItemStack(material);
                        Integer num = null;
                        try {
                            num = Integer.valueOf(args[2]);
                        } catch (NumberFormatException ignored) {
                        }
                        if (num == null) {
                            sender.sendMessage(Errors.INVALID_NUMBER());
                            return false;
                        }
                        itemStack.setAmount(Integer.parseInt(args[2]));
                        player.getInventory().addItem(itemStack);
                        sender.sendMessage(ChatColor.GOLD + "Gave you " + ChatColor.AQUA + args[2] + ChatColor.GOLD + "x " + ChatColor.AQUA + itemStack.getType().name().toLowerCase());
                        player.playSound(player.getLocation(), Sound.valueOf("BLOCK_AMETHYST_BLOCK_BREAK"), 1f, 1f);
                    }

                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else if (args[0].equalsIgnoreCase("name")) {
                if (player.hasPermission("activecraft.item.name")) {
                    if (args.length > 1) {
                        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                            sender.sendMessage(Errors.WARNING() + "You need to hold an item in your hand!");
                            return false;
                        }

                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            stringBuilder.append(args[i]);
                            stringBuilder.append(" ");
                        }

                        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                            sender.sendMessage(Errors.INVALID_ARGUMENTS());
                            return false;
                        }
                        ItemStack stack = player.getInventory().getItemInMainHand();
                        ItemMeta meta = stack.getItemMeta();
                        meta.setDisplayName(MessageUtils.replaceFormat(MessageUtils.replaceColor(stringBuilder.toString())));
                        stack.setItemMeta(meta);

                        sender.sendMessage(ChatColor.GOLD + "Item renamed");
                    } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else if (args[0].equalsIgnoreCase("lore")) {
                if (player.hasPermission("activecraft.item.lore")) {
                    if (args.length > 1) {
                        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                            sender.sendMessage(Errors.WARNING() + "You need to hold an item in your hand!");
                            return false;
                        }
                        ItemStack stack = player.getInventory().getItemInMainHand();
                        ItemMeta meta = stack.getItemMeta();
                        List<String> stringList = new ArrayList<>();
                        if (meta.getLore() != null) {
                            stringList.addAll(meta.getLore());
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i = 2; i < args.length; i++) {
                                stringBuilder.append(args[i]);
                                stringBuilder.append(" ");
                            }
                            stringList.add(MessageUtils.replaceFormat(MessageUtils.replaceColor(stringBuilder.toString())));
                            meta.setLore(stringList);
                            stack.setItemMeta(meta);
                            sender.sendMessage(ChatColor.GOLD + "Added to Lore");
                        } else if (args[1].equalsIgnoreCase("clear")) {
                            stringList.clear();
                            meta.setLore(stringList);
                            stack.setItemMeta(meta);
                            sender.sendMessage(ChatColor.GOLD + "Cleared Lore");
                        } else if (args[1].equalsIgnoreCase("set")) {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i = 2; i < args.length; i++) {
                                stringBuilder.append(args[i]);
                                stringBuilder.append(" ");
                            }
                            stringList.clear();
                            stringList.add(MessageUtils.replaceFormat(MessageUtils.replaceColor(stringBuilder.toString())));
                            meta.setLore(stringList);
                            stack.setItemMeta(meta);

                            sender.sendMessage(ChatColor.GOLD + "Set Lore");
                        }


                    } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else if (args[0].equalsIgnoreCase("tag")) {
                if (player.hasPermission("activecraft.item.tag")) {
                    if (args.length > 1) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            stringBuilder.append(args[i]);
                            stringBuilder.append(" ");
                        }

                        ItemStack stack = player.getInventory().getItemInMainHand();

                        ItemMeta meta = stack.getItemMeta();
                        MessageUtils messageUtils = new MessageUtils();

                        stack.setItemMeta(meta);

                        sender.sendMessage(ChatColor.GOLD + "Item renamed");
                    } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.INVALID_ARGUMENTS());

        } else sender.sendMessage(Errors.NOT_A_PLAYER());

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        if (args.length == 1) {
            list.add("name");
            list.add("lore");
            //list.add("tag");
            list.add("give");
        }
        if (args[0].equalsIgnoreCase("give")) {
            if (args.length == 2) {
                for (Material material : Material.values()) {
                    list.add(material.name());
                }
            }
        } else if (args[0].equalsIgnoreCase("lore")) {
            if (args.length == 2) {
                list.add("set");
                list.add("add");
                list.add("clear");
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
