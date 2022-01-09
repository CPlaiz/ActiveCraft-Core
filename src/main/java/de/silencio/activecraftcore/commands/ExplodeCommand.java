package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ExplodeCommand implements CommandExecutor, TabCompleter {

    private static final float DEFAULT_POWER = 4f;
    private static final boolean DEFAULT_FIRE = true;
    private static final boolean DEFAULT_BREAK_BLOCKS = true;

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        if (args.length == 0) {
            checkPermission(sender, "explode.self");
            Player player = getPlayer(sender);
            player.getWorld().createExplosion(player.getLocation(), DEFAULT_POWER, DEFAULT_FIRE, DEFAULT_BREAK_BLOCKS);
        } else if (Bukkit.getPlayer(args[0]) == null) {
            checkPermission(sender, "explode.self");
            Player player = getPlayer(sender);
            switch (args.length) {
                case 1 -> player.getWorld().createExplosion(player.getLocation(), parseFloat(args[0]), DEFAULT_FIRE, DEFAULT_BREAK_BLOCKS);
                case 2 -> player.getWorld().createExplosion(player.getLocation(), parseFloat(args[0]), Boolean.parseBoolean(args[1]), DEFAULT_BREAK_BLOCKS);
                case 3 -> player.getWorld().createExplosion(player.getLocation(), parseFloat(args[0]), Boolean.parseBoolean(args[1]), Boolean.parseBoolean(args[2]));
            }
        } else {
            checkPermission(sender, "explode.others");
            Player target = getPlayer(args[0]);
            checkTargetSelf(sender, target, "explode.self");
            switch (args.length) {
                case 1 -> target.getWorld().createExplosion(target.getLocation(), DEFAULT_POWER, DEFAULT_FIRE, DEFAULT_BREAK_BLOCKS);
                case 2 -> target.getWorld().createExplosion(target.getLocation(), parseFloat(args[1]), DEFAULT_FIRE, DEFAULT_BREAK_BLOCKS);
                case 3 -> target.getWorld().createExplosion(target.getLocation(), parseFloat(args[1]), Boolean.parseBoolean(args[2]), DEFAULT_BREAK_BLOCKS);
                case 4 -> target.getWorld().createExplosion(target.getLocation(), parseFloat(args[1]), Boolean.parseBoolean(args[2]), Boolean.parseBoolean(args[3]));
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 1)
            list.addAll(getBukkitPlayernames());

        if (Bukkit.getPlayer(args[0]) != null) {
            switch (args.length) {
                case 3, 4 -> {
                    list.add("true");
                    list.add("false");
                }
            }
        } else {
            switch (args.length) {
                case 2, 3 -> {
                    list.add("true");
                    list.add("false");
                }
            }
        }
        return  list;
    }
}

// power:
// fire: true/false
// breakblocks: true/false