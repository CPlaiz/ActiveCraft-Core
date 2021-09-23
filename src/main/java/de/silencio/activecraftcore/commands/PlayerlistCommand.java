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

public class PlayerlistCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if(sender.hasPermission("activecraft.playerlist")) {

            StringBuilder stringBuilder = new StringBuilder();
            FileConfig mainConfig = new FileConfig("config.yml");
            boolean isFirst = true;
            for(Player player : Bukkit.getOnlinePlayers()) {
                FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");
                if (playerdataConfig.getBoolean("vanished")) {
                    if (sender.hasPermission("activecraft.vanish.see")) {
                        if (!isFirst) {
                            stringBuilder.append(ChatColor.WHITE + ", ");
                        } else isFirst = false;
                        stringBuilder.append(player.getName() + ChatColor.GRAY + " " + mainConfig.getString("vanish-format"));
                    }
                } else {
                    if (!isFirst) {
                        stringBuilder.append(ChatColor.WHITE + ", ");
                    } else isFirst = false;
                    stringBuilder.append(player.getDisplayName());
                }
            }
            sender.sendMessage(ChatColor.GOLD + "Online players:");
            sender.sendMessage(stringBuilder.toString());
        } else sender.sendMessage(Errors.NO_PERMISSION());
        return true;
    }
}
