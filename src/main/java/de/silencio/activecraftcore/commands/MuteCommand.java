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

public class MuteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if(sender.hasPermission("activecraft.mute")) {

                if(args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(label.equalsIgnoreCase("mute")) {
                        FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + args[0] + ".yml");
                        if (!playerdataConfig.getBoolean("muted")) {

                            playerdataConfig.set("muted", "true");
                        playerdataConfig.set("vialations.mutes", playerdataConfig.getInt("vialations.mutes") + 1);
                        playerdataConfig.saveConfig();

                        sender.sendMessage(ChatColor.GOLD + "Muted " + ChatColor.AQUA + args[0] + ChatColor.GOLD + ".");
                        target.sendMessage(ChatColor.GOLD + "You have been muted.");
                    } else sender.sendMessage(Errors.WARNING + "This player is already muted.");
                    }

                    if(label.equalsIgnoreCase("unmute")) {
                        FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + args[0] + ".yml");
                        if (playerdataConfig.getBoolean("muted")) {

                            playerdataConfig.set("muted", "false");
                            playerdataConfig.set("default-mute", "false");
                            playerdataConfig.saveConfig();

                            sender.sendMessage(ChatColor.GOLD + "Unmuted " + ChatColor.AQUA + args[0] + ChatColor.GOLD + ".");
                            target.sendMessage(ChatColor.GOLD + "You have been unmuted.");
                        } else sender.sendMessage(Errors.WARNING + "This player is not muted.");
                    }
                } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
            } else sender.sendMessage(Errors.NO_PERMISSION);
        return true;
    }
}