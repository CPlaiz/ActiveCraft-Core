package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {

            if(sender.hasPermission("activecraft.home")) {

                FileConfiguration homeconfig = new FileConfig("homes.yml");

                if(args.length == 0) {
                    if (homeconfig.contains(((Player) sender).getUniqueId().toString())) {

                        ((Player) sender).teleport(homeconfig.getLocation(((Player) sender).getUniqueId().toString()));
                        sender.sendMessage(ChatColor.GOLD + "Teleported to your home.");
                        ((Player) sender).playSound(((Player) sender).getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);

                    } else sender.sendMessage(ChatColor.RED + "Warning!" + ChatColor.GRAY + " No home set!");
                }

                if(args.length == 1) {
                    if(sender.hasPermission("activecraft.home.others")) {
                        Player player = (Player) sender;
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

                        if(!(homeconfig.getLocation(targetUUID) == null)) {

                            ((Player) sender).teleport(homeconfig.getLocation(targetUUID));
                            sender.sendMessage(ChatColor.GOLD + "Teleported to " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + "'s home.");

                        } else sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " has not set a home.");

                    } else sender.sendMessage(Errors.NO_PERMISSION);
                }
            } else sender.sendMessage(Errors.NO_PERMISSION);

        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
