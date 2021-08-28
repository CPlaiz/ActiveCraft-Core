package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class DelHomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            FileConfiguration homeconfig = new FileConfig("homes.yml");

            if(sender.hasPermission("activecraft.delhome.self")) {
                if(args.length == 0) {

                    homeconfig.set(((Player) sender).getUniqueId().toString(), null);
                    ((FileConfig) homeconfig).saveConfig();

                    sender.sendMessage(ChatColor.GOLD + "Home deleted.");
                }
            } else sender.sendMessage(Errors.NO_PERMISSION);

            if(args.length == 1) {
                if (sender.hasPermission("activecraft.delhome.others")) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Errors.INVALID_PLAYER);
                        return false;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                        sender.sendMessage(Errors.CANNOT_TARGET_SELF);
                        return false;
                    }
                    String targetUUID = target.getUniqueId().toString();

                    homeconfig.set(targetUUID, null);
                    ((FileConfig) homeconfig).saveConfig();

                    sender.sendMessage(ChatColor.GOLD + "Deleted the home of player " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + ".");
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }
        return true;
    }
}