package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.ColorUtils;
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
                            if (sender.hasPermission("activecraft.leathercolor.vanilla")) {
                                color = ColorUtils.bukkitColorFromString(args[0]);
                                if (color == null) {
                                    sender.sendMessage(Errors.WARNING() + "This is not a valid color!");
                                    return false;
                                }
                            } else sender.sendMessage(Errors.NO_PERMISSION());
                        } else {
                            if (sender.hasPermission("activecraft.leathercolor.hex")) {
                                if (args[0].length() == 7) {
                                    if (args[0].replace("#", "").toLowerCase().matches("(\\d|[a-f])(\\d|[a-f])(\\d|[a-f])(\\d|[a-f])(\\d|[a-f])(\\d|[a-f])")) {
                                        int[] rgbArray = ColorUtils.getRGB(args[0]);
                                        color = Color.fromRGB(rgbArray[0], rgbArray[1], rgbArray[2]);
                                    } else {
                                        sender.sendMessage(Errors.WARNING() + "This is not a valid hex code!");
                                        return false;
                                    }
                                } else {
                                    sender.sendMessage(Errors.INVALID_ARGUMENTS());
                                    return false;
                                }
                            } else sender.sendMessage(Errors.NO_PERMISSION());
                        }
                        itemmeta.setColor(color);
                        mainhanditem.setItemMeta(itemmeta);
                        player.getInventory().setItemInMainHand(mainhanditem);

                    } else sender.sendMessage(Errors.WARNING() + "No leather item in main hand!");
                } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        if (args.length == 1) {
            list.add("green");
            list.add("black");
            list.add("blue");
            list.add("lime");
            list.add("cyan");
            list.add("red");
            list.add("magenta");
            list.add("pink");
            list.add("orange");
            list.add("light_gray");
            list.add("gray");
            list.add("light_blue");
            list.add("purple");
            list.add("yellow");
            list.add("white");
            list.add("brown");
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
