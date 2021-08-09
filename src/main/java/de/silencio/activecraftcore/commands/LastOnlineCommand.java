package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class LastOnlineCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if(target != null) {
                if (sender.hasPermission("activecraft.lastonline")) {
                    FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + args[0] + ".yml");

                    String lastonline = playerdataConfig.getString("last-online");
                    if (lastonline.equalsIgnoreCase("online")) {
                        sender.sendMessage(ChatColor.AQUA + playerdataConfig.getString("name") + ChatColor.GOLD + " is " + ChatColor.GREEN + lastonline);
                    } else
                        sender.sendMessage(ChatColor.AQUA + playerdataConfig.getString("name") + ChatColor.GOLD + " was last online: " + ChatColor.GREEN + lastonline);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            } else sender.sendMessage(Errors.INVALID_PLAYER);
        } else sender.sendMessage(Errors.INVALID_ARGUMENTS);

        return true;
    }
}
