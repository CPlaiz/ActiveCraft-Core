package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
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

public class VerifyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("activecraft.verify")) {
            if (args.length == 1) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER());
                    return false;
                }
                Player target = Bukkit.getPlayer(args[0]);

                FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + target.getName().toLowerCase() + ".yml");
                boolean defaultmute = playerdataConfig.getBoolean("default-mute");

                if (defaultmute) {
                    playerdataConfig.set("default-mute", false);
                    playerdataConfig.saveConfig();
                    target.sendMessage(CommandMessages.DEFAULT_MUTE_REMOVE());
                    sender.sendMessage(CommandMessages.DEFAULT_MUTE_REMOVE_MESSAGE(target));
                } else sender.sendMessage(Errors.WARNING() + CommandMessages.NOT_DEFAULT_MUTED());
            } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
        } else sender.sendMessage(Errors.NO_PERMISSION());

        return true;
    }
}
