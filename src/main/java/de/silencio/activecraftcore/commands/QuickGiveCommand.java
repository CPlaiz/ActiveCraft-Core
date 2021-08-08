package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.templates.DefaultCommandExecutor;
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

import java.util.ArrayList;
import java.util.List;

public class QuickGiveCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(sender.hasPermission("activecraft.quickgive")) {
                if(args.length == 0) {
                    sender.sendMessage(Errors.INVALID_ARGUMENTS);
                }
                if(args.length == 1) {
                    Material material = Material.getMaterial(args[0].toUpperCase());
                    ItemStack itemStack = new ItemStack(material);
                    player.getInventory().addItem(itemStack);
                    sender.sendMessage(ChatColor.GOLD + "Gave you " + ChatColor.AQUA + itemStack.getType().name().toLowerCase().replace("_", " "));
                    player.playSound(player.getLocation(), Sound.valueOf("BLOCK_AMETHYST_BLOCK_BREAK"), 1f, 1f);
                }
                if(args.length == 2) {
                    Material material = Material.getMaterial(args[0].toUpperCase());
                    ItemStack itemStack = new ItemStack(material);
                    itemStack.setAmount(Integer.parseInt(args[1]));
                    player.getInventory().addItem(itemStack);
                    sender.sendMessage(ChatColor.GOLD + "Gave you " + ChatColor.AQUA + args[1] + ChatColor.GOLD + "x " + ChatColor.AQUA + itemStack.getType().name().toLowerCase().replace("_", " "));
                    player.playSound(player.getLocation(), Sound.valueOf("BLOCK_AMETHYST_BLOCK_BREAK"), 1f, 1f);
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
            for (Material material : Material.values()) {
                list.add(material.name());
            }
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
