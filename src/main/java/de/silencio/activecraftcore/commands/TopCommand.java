package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;


        if (args.length == 0) {
            if (sender.hasPermission("activecraft.top.self")) {
                int xBlock = player.getLocation().getBlockX();
                int zBlock = player.getLocation().getBlockZ();
                double x = player.getLocation().getX();
                double z = player.getLocation().getZ();
                Location loc = new Location(player.getWorld(), x, player.getWorld().getHighestBlockYAt(xBlock, zBlock), z, player.getLocation().getYaw(), player.getLocation().getPitch());
                if (loc.getBlock().getType() != Material.LAVA) {
                    loc.setY(loc.getBlockY() + 1);
                    player.teleport(loc);
                    player.sendMessage(CommandMessages.TOP_TELEPORT());
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                } else sender.sendMessage(Errors.WARNING() + CommandMessages.TOP_NOT_SAFE());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else if (args.length == 1) {
            if (sender.hasPermission("activecraft.top.others")) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER());
                    return false;
                }
                Player target = Bukkit.getPlayer(args[0]);

                if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                    if (!sender.hasPermission("activecraft.top.self")) {
                        sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                        return false;
                    }
                }

                int xBlock = target.getLocation().getBlockX();
                int zBlock = target.getLocation().getBlockZ();
                double x = target.getLocation().getX();
                double z = target.getLocation().getZ();
                Location loc = new Location(target.getWorld(), x, target.getWorld().getHighestBlockYAt(xBlock, zBlock), z, player.getLocation().getYaw(), player.getLocation().getPitch());
                if (loc.getBlock().getType() != Material.LAVA) {
                    loc.setY(loc.getBlockY() + 1);
                    target.teleport(loc);
                    target.sendMessage(CommandMessages.TOP_TELEPORT_OTHERS_MESSAGE(sender));
                    sender.sendMessage(CommandMessages.TOP_TELEPORT_OTHERS(target));
                    target.playSound(target.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                } else sender.sendMessage(Errors.WARNING() + CommandMessages.TOP_NOT_SAFE());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        }
        return true;
    }
}