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
                if (args.length == 1) {
                    int x = player.getLocation().getBlockX();
                    int z = player.getLocation().getBlockZ();
                    Location loc = new Location(player.getWorld(), x - 0.5, player.getWorld().getHighestBlockYAt(x, z), z - 0.5);
                    if (loc.getBlock().getType() != Material.LAVA) {
                        loc.setY(loc.getBlockY() + 1);
                        player.teleport(loc);
                        player.sendMessage(ChatColor.GOLD + "Teleported to the top.");
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                    } else sender.sendMessage(Errors.WARNING + "Teleport is not safe!");
                } else if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[0]);
                    int x = target.getLocation().getBlockX();
                    int z = target.getLocation().getBlockZ();
                    Location loc = new Location(target.getWorld(), x - 0.5, target.getWorld().getHighestBlockYAt(x, z), z - 0.5);
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
