package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;
            FileConfig spawns = new FileConfig("spawn.yml");
            if (label.equalsIgnoreCase("setspawn")) {
                if (player.hasPermission("activecraft.setspawn")) {
                    spawns.set("spawn", LocationUtils.loc2Str(player.getLocation()));
                    spawns.saveConfig();
                    player.sendMessage(ChatColor.GOLD + "Set Spawn to current location.");
                    return true;
                } else player.sendMessage(Errors.NO_PERMISSION);
                return true;
            }

            if (player.hasPermission("activecraft.spawn")) {
                if (spawns.contains("spawn")) {

                    if(args.length == 0) {

                        LocationUtils.teleport(player, LocationUtils.str2Loc(spawns.getString("spawn")));
                        player.sendMessage(ChatColor.GOLD + "Teleported to " + ChatColor.AQUA + "Spawn" + ChatColor.GOLD + ".");
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);

                    } else if (args.length == 1) {

                        Player target = Bukkit.getPlayer(args[0]);

                        LocationUtils.teleport(target, LocationUtils.str2Loc(spawns.getString("spawn")));
                        target.sendMessage(ChatColor.GOLD + "You were teleported to " + ChatColor.AQUA + "Spawn" + ChatColor.GOLD + " by " + ChatColor.AQUA + player.getDisplayName());
                        player.sendMessage(ChatColor.GOLD + "Teleported " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " to " + ChatColor.AQUA + "Spawn" + ChatColor.GOLD + ".");
                        target.playSound(target.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                    }
                } else player.sendMessage(Errors.WARNING + "No Spawn set.");

            } else player.sendMessage(Errors.NO_PERMISSION);
            return true;

        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
