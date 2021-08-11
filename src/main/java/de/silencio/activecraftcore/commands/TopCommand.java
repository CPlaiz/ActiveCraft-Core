package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            Player player = (Player) sender;

            if (sender.hasPermission("activecraft.top")) {
                if (args.length == 0) {
                    double x = player.getLocation().getX();
                    double z = player.getLocation().getZ();
                    Location loc = new Location(player.getWorld(), x, player.getWorld().getHighestBlockYAt((int) x, (int) z), z);
                    if (loc.getBlock().getType() != Material.LAVA) {
                        loc.setY(loc.getBlockY() + 1);
                        player.teleport(loc);
                        player.sendMessage(ChatColor.GOLD + "Teleported to the top.");
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                    } else sender.sendMessage(Errors.WARNING + "Teleport is not safe!");
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    double x = player.getLocation().getX();
                    double z = player.getLocation().getZ();
                    Location loc = new Location(target.getWorld(), x, target.getWorld().getHighestBlockYAt((int) x, (int) z), z);
                    if (loc.getBlock().getType() != Material.LAVA) {
                        loc.setY(loc.getBlockY() + 1);
                        target.teleport(loc);
                        target.sendMessage(ChatColor.GOLD + "Teleported to the top.");
                        sender.sendMessage(ChatColor.GOLD + "Teleported " + ChatColor.AQUA + target.getName() + ChatColor.GOLD + " to the top.");
                        target.playSound(target.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                    } else sender.sendMessage(Errors.WARNING + "Teleport is not safe!");
                }
            } else sender.sendMessage(Errors.NO_PERMISSION);

        return true;
    }
}
