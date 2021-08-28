package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.ColorUtils;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.*;

public class LeatherColorCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(sender.hasPermission("activecraft.leathercolor")) {
                if(args.length == 1) {
                    ItemStack mainhanditem = player.getInventory().getItemInMainHand();
                    if(mainhanditem.getType().equals(Material.LEATHER_HELMET) || mainhanditem.getType().equals(Material.LEATHER_CHESTPLATE) ||
                            mainhanditem.getType().equals(Material.LEATHER_LEGGINGS) || mainhanditem.getType().equals(Material.LEATHER_BOOTS) ||
                            mainhanditem.getType().equals(Material.LEATHER_HORSE_ARMOR)) {
                        LeatherArmorMeta itemmeta = (LeatherArmorMeta) mainhanditem.getItemMeta();
                        Color color = null;
                        if (!args[0].startsWith("#")) {
                            color = ColorUtils.bukkitColorFromString(args[0]);
                            if (color == null) {
                                sender.sendMessage(Errors.WARNING + "This is not a valid color!");
                                return false;
                            }
                        } else {
                            int[] rgbArray = ColorUtils.getRGB(args[0]);
                            color = Color.fromRGB(rgbArray[0], rgbArray[1], rgbArray[2]);
                        }
                        itemmeta.setColor(color);
                        mainhanditem.setItemMeta(itemmeta);
                        player.getInventory().setItemInMainHand(mainhanditem);

                    } else sender.sendMessage(Errors.WARNING + "No leather armor in main hand!");
                } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
            } else sender.sendMessage(Errors.NO_PERMISSION);
        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        if (args.length == 1) {
            list.add("black");
            list.add("green");
            list.add("dark_blue");
            list.add("dark_green");
            list.add("dark_red");
            list.add("dark_aqua");
            list.add("dark_purple");
            list.add("light_purple");
            list.add("gold");
            list.add("gray");
            list.add("blue");
            list.add("aqua");
            list.add("red");
            list.add("dark_gray");
            list.add("yellow");
            list.add("white");
            list.add("pepega_green");
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
