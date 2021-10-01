package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;

public class EditSignCommand implements CommandExecutor {

    String message = "";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            if (sender instanceof Player) {
                if (sender.hasPermission("activecraft.editsign.self")) {
                    Player player = (Player) sender;

                    FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");
                    if (playerdataConfig.getBoolean("edit-sign")) {
                        playerdataConfig.set("edit-sign", false);
                        playerdataConfig.saveConfig();
                        sender.sendMessage(CommandMessages.DISABLED());
                    } else {
                        playerdataConfig.set("edit-sign", true);
                        playerdataConfig.saveConfig();
                        sender.sendMessage(CommandMessages.ENABLED());
                    }
                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.NOT_A_PLAYER());
        } else {
            if (sender.hasPermission("activecraft.editsign.others")) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER());
                    return false;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if (sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                    if (!sender.hasPermission("activecraft.editsign.self")) {
                        sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                        return false;
                    }
                }
                FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + target.getName().toLowerCase() + ".yml");
                if (playerdataConfig.getBoolean("edit-sign")) {
                    playerdataConfig.set("edit-sign", false);
                    playerdataConfig.saveConfig();
                    sender.sendMessage(CommandMessages.DISABLED_OTHERS(target));
                    target.sendMessage(CommandMessages.DISABLED_OTHERS_MESSAGE(sender));
                } else {
                    playerdataConfig.set("edit-sign", true);
                    playerdataConfig.saveConfig();
                    sender.sendMessage(CommandMessages.ENABLED_OTHERS(target));
                    target.sendMessage(CommandMessages.ENABLED_OTHERS_MESSAGE(sender));
                }
            } else sender.sendMessage(Errors.NO_PERMISSION());
        }
        return true;
    }
}