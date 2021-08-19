package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.MessageUtils;
import org.bukkit.Bukkit;
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

        if(sender.hasPermission("activecraft.broadcast")) {

            if(label.equalsIgnoreCase("broadcast") || label.equalsIgnoreCase("bc")) {
                if (args.length >= 1) {
                    String msg = "";
                    for (int i = 0; i < args.length; i++) {
                        msg = msg + args[i] + " ";
                    }
                    msg = MessageUtils.replaceColor(msg);
                    msg = MessageUtils.replaceFormat(msg);

                    Bukkit.broadcastMessage("§6[BroadCast]§r " + msg);

                } else sender.sendMessage(Errors.WARNING + "Please include a message!");
            }

            if(label.equalsIgnoreCase("broadcastworld")) {
                if(sender instanceof Player) {
                    if (args.length >= 1) {
                        String msg = "";
                        for (int i = 0; i < args.length; i++) {
                            msg = msg + args[i] + " ";
                        }
                        msg = MessageUtils.replaceColor(msg);
                        msg = MessageUtils.replaceFormat(msg);

                        for (Player player : Bukkit.getOnlinePlayers()) {
                            Player senderplayer = (Player) sender;
                            if (player.getWorld() == senderplayer.getWorld()) {
                                player.sendMessage("§6[BroadCast]§r " + msg);
                            }
                        }
                    } else sender.sendMessage(Errors.WARNING + "Please include a message!");
                } else sender.sendMessage(Errors.NOT_A_PLAYER);
            }
        } else sender.sendMessage(Errors.NO_PERMISSION);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        ArrayList<String> completerList = new ArrayList<>();
        return completerList;
    }
}
