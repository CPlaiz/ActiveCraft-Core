package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            FileConfiguration homeconfig = new FileConfig("homes.yml");

                if (sender.hasPermission("activecraft.sethome")) {
                    if (args.length == 0) {
                        if(sender instanceof Player) {

                            Location loc = ((Player) sender).getLocation();

                            homeconfig.set(((Player) sender).getUniqueId().toString(), loc);
                            ((FileConfig) homeconfig).saveConfig();

                            sender.sendMessage(ChatColor.GOLD + "Home set!");
                        } else sender.sendMessage(Errors.NOT_A_PLAYER);
                    }

                } else sender.sendMessage(Errors.NO_PERMISSION);

            if(args.length == 1) {
                if(sender.hasPermission("activecraft.sethome.others")) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Errors.INVALID_PLAYER);
                        return false;
                    }
                    Player player = (Player) sender;
                    Player target = Bukkit.getPlayer(args[0]);
                    if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                        sender.sendMessage(Errors.CANNOT_TARGET_SELF);
                        return false;
                    }
                    String targetUUID = target.getUniqueId().toString();

                    if(target != null) {
                        Location loc = ((Player) sender).getLocation();

                        homeconfig.set(targetUUID, loc);
                        ((FileConfig) homeconfig).saveConfig();
                        sender.sendMessage(ChatColor.GOLD + "Set home of player " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " to current location.");
                    } else sender.sendMessage(Errors.INVALID_PLAYER);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }
        return true;
    }
}