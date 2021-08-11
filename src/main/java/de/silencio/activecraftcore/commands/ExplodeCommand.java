package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ExplodeCommand implements CommandExecutor, TabCompleter {

    private float DEFAULT_POWER = 4f;
    private boolean DEFAULT_FIRE = true;
    private boolean DEFAULT_BREAK_BLOCKS = true;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (Bukkit.getPlayer(args[0]) == null) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("activecraft.explode")) {
                    switch (args.length) {
                        case 0:
                            player.getWorld().createExplosion(player.getLocation(), DEFAULT_POWER, DEFAULT_FIRE, DEFAULT_BREAK_BLOCKS);
                        case 1:
                            player.getWorld().createExplosion(player.getLocation(), Float.parseFloat(args[0]), DEFAULT_FIRE, DEFAULT_BREAK_BLOCKS);
                            break;
                        case 2:
                            player.getWorld().createExplosion(player.getLocation(), Float.parseFloat(args[0]), Boolean.parseBoolean(args[1]), DEFAULT_BREAK_BLOCKS);
                            break;
                        case 3:
                            player.getWorld().createExplosion(player.getLocation(), Float.parseFloat(args[0]), Boolean.parseBoolean(args[1]), Boolean.parseBoolean(args[2]));
                            break;
                    }
                } else sender.sendMessage(Errors.NO_PERMISSION);
            } else sender.sendMessage(Errors.NOT_A_PLAYER);
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            if (sender.hasPermission("activecraft.explode.other")) {
                switch (args.length) {
                    case 2:
                        target.getWorld().createExplosion(target.getLocation(), Float.parseFloat(args[1]), DEFAULT_FIRE, DEFAULT_BREAK_BLOCKS);
                        break;
                    case 3:
                        target.getWorld().createExplosion(target.getLocation(), Float.parseFloat(args[1]), Boolean.parseBoolean(args[2]), DEFAULT_BREAK_BLOCKS);
                        break;
                    case 4:
                        target.getWorld().createExplosion(target.getLocation(), Float.parseFloat(args[1]), Boolean.parseBoolean(args[2]), Boolean.parseBoolean(args[3]));
                        break;
                }
            } else sender.sendMessage(Errors.NO_PERMISSION);
        }
        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Player p = (Player) sender;

        if (args.length == 0) return list;
        if (args.length == 1) list.add("power:");
        if (Bukkit.getPlayer(args[0]) != null) {
        if (args.length == 2) {
            list.add("power:");
        } else if (args.length == 3) {
            list.add("fire true/false:");
        } else if (args.length == 4) {
            list.add("break blocks true/false");
        }
        } else {
            if (args.length == 2) {
                list.add("fire true/false:");
            } else if (args.length == 3) {
                list.add("break blocks true/false");
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

// power:
// fire: true/false
// breakblocks: true/false