package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.ColorUtils;
import de.silencio.activecraftcore.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BroadCastCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("activecraft.broadcast.self")) {

            if(label.equalsIgnoreCase("broadcast") || label.equalsIgnoreCase("bc")) {
                if (args.length >= 1) {
                    String msg = "";
                    for (int i = 0; i < args.length; i++) {
                        msg = msg + args[i] + " ";
                    }
                    msg = ColorUtils.replaceColor(msg);
                    msg = ColorUtils.replaceFormat(msg);

                    Bukkit.broadcastMessage(CommandMessages.BROADCAST_PREFIX() + ChatColor.RESET + " " + msg);

                } else sender.sendMessage(Errors.WARNING() + CommandMessages.INCLUDE_MESSAGE());
            }

            if(label.equalsIgnoreCase("broadcastworld") || label.equalsIgnoreCase("bcw")) {
                if(sender instanceof Player) {
                    if (args.length >= 1) {
                        String msg = "";
                        for (int i = 0; i < args.length; i++) {
                            msg = msg + args[i] + " ";
                        }

                        msg = ColorUtils.replaceColor(msg);
                        msg = ColorUtils.replaceFormat(msg);

                        for (Player player : Bukkit.getOnlinePlayers()) {
                            Player senderplayer = (Player) sender;
                            if (player.getWorld() == senderplayer.getWorld()) {
                                player.sendMessage(CommandMessages.BROADCAST_PREFIX() + ChatColor.RESET + " " + msg);
                            }
                        }
                    } else sender.sendMessage(Errors.WARNING() + CommandMessages.INCLUDE_MESSAGE());
                } else sender.sendMessage(Errors.NOT_A_PLAYER());
            }
        } else sender.sendMessage(Errors.NO_PERMISSION());
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        ArrayList<String> completerList = new ArrayList<>();
        return completerList;
    }
}
