package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.events.LockdownEvent;
import de.silencio.activecraftcore.events.PlayerAfkEvent;
import de.silencio.activecraftcore.manager.AfkManager;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;

import java.io.File;

public class AfkCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfig fileConfig = new FileConfig("config.yml");
            
            if(args.length == 0) {
                if(sender instanceof Player) {
                    Player player = (Player) sender;
                    FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");
                    if (sender.hasPermission("activecraft.afk.self")) {

                        if(!playerdataConfig.getBoolean("afk")) {
                            AfkManager.setAfk(player, true);
                        } else if (playerdataConfig.getBoolean("afk")) {
                            AfkManager.setAfk(player, false);
                        }
                    } else sender.sendMessage(Errors.NO_PERMISSION());
                } else sender.sendMessage(Errors.NOT_A_PLAYER());
            } else if(args.length == 1) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER());
                    return false;
                }
                Player target = Bukkit.getPlayer(args[0]);
                FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + target.getName().toLowerCase() + ".yml");
                if (sender.hasPermission("activecraft.afk.others")) {
                    if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                        if (!sender.hasPermission("activecraft.afk.self")) {
                            sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                            return false;
                        }
                    }

                    if(!playerdataConfig.getBoolean("afk")) {
                        AfkManager.setAfk(target, true);
                    } else if(playerdataConfig.getBoolean("afk")) {
                        AfkManager.setAfk(target, false);
                    }
                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
        return true;
    }
}