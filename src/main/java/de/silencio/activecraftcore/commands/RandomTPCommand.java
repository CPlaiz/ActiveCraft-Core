package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Random;

public class RandomTPCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            if (sender.hasPermission("activecraft.randomtp.self")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    Location tpLoc = randomLocation(player, 3000000);
                    player.teleport(tpLoc);
                } else sender.sendMessage(Errors.NOT_A_PLAYER);
            } else sender.sendMessage(Errors.NO_PERMISSION);
        } else if (args.length == 1) {
            if (sender.hasPermission("activecraft.randomtp.others")) {
                if (Bukkit.getPlayer(args[0]) != null) {
                    Player player = (Player) sender;
                    Player target = Bukkit.getPlayer(args[0]);
                    if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                        sender.sendMessage(Errors.CANNOT_TARGET_SELF);
                        return false;
                    }
                    Location tpLoc = randomLocation(target, 3000000);
                    target.teleport(tpLoc);
                } else {
                    Player player = (Player) sender;
                    Integer num = null;
                    try {
                        num = Integer.valueOf(args[0]);
                    } catch (NumberFormatException ignored) {
                    }
                    if (num == null || num == 0) {
                        sender.sendMessage(Errors.INVALID_NUMBER);
                        return false;
                    }

                    Location tpLoc = randomLocation(player, num);
                    player.teleport(tpLoc);
                }
            } else sender.sendMessage(Errors.NO_PERMISSION);
        } else {
            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(Errors.INVALID_PLAYER);
                return false;
            }
            Player player = (Player) sender;
            Player target = Bukkit.getPlayer(args[0]);
            Integer num = null;
            try {
                num = Integer.valueOf(args[1]);
            } catch (NumberFormatException ignored) {
            }
            if (num == null || num == 0) {
                sender.sendMessage(Errors.INVALID_NUMBER);
                return false;
            }

            Location tpLoc = randomLocation(target, num);
            target.teleport(tpLoc);

        }
        return true;
    }

    private Location randomLocation(Player player, int range) {
        Random random = new Random();
        int randomNumX = random.nextInt(range);
        int randomNumZ = random.nextInt(range);

        int isNegative = random.nextInt(4);
        switch (isNegative) {
            case 1:
                randomNumX *= -1;
                break;
            case 2:
                randomNumZ *= -1;
                break;
            case 3:
                randomNumX *= -1;
                randomNumZ *= -1;
                break;
        }
        Location newLoc = new Location(player.getWorld(), randomNumX, player.getWorld().getHighestBlockYAt(randomNumX, randomNumZ) + 1, randomNumZ,
                player.getLocation().getYaw(), player.getLocation().getPitch());
        return newLoc;
    }
}