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
import java.util.List;

public class KnownIpsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("activecraft.listips")) {
            if (args.length == 1) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER());
                    return false;
                }
                Player target = Bukkit.getPlayer(args[0]);

                FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + target.getName().toLowerCase() + ".yml");
                List<String> ipList = playerdataConfig.getStringList("known-ips");

                StringBuilder stringBuilder = new StringBuilder();
                for (String s : ipList) {
                    stringBuilder.append(s);
                    if (!s.equals(ipList.get(ipList.size() - 1))) {
                        stringBuilder.append(",");
                    }
                }
                sender.sendMessage(ChatColor.GOLD + "-- Known IPs for " + target.getName() + "\n"
                                + ChatColor.WHITE + stringBuilder.toString()
                        );
            } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
        } else sender.sendMessage(Errors.NO_PERMISSION());

        return true;
    }
}
