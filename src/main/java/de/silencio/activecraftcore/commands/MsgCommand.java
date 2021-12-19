package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.events.MsgEvent;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.ColorUtils;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MsgCommand implements CommandExecutor, TabCompleter {



    String message = "";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("activecraft.msg")) {

                if (args.length >= 1) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Errors.INVALID_PLAYER());
                        return false;
                    }
                    Player target = Bukkit.getPlayer(args[0]);

                    if (target != null) {
                        if (target != player) {
                            if (args.length > 1) {
                                for (int i = 1; i < args.length; i++) {
                                    message = message + args[i] + " ";
                                }

                                message = ColorUtils.replaceColor(message);
                                message = ColorUtils.replaceFormat(message);

                                MsgEvent event = new MsgEvent(sender, target, message);
                                Bukkit.getPluginManager().callEvent(event);
                                if (event.isCancelled()) return false;

                                player.sendMessage(CommandMessages.MSG_PREFIX_TO(target, event.getMessage()));
                                target.sendMessage(CommandMessages.MSG_PREFIX_FROM(player, event.getMessage()));
                                target.playSound(target.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1f, 1f);

                                FileConfig mainConfig = new FileConfig("config.yml");

                                ActiveCraftCore.getMsgPlayerStoring().put(target, player);

                                //socialspy
                                for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                                    if (!onlinePlayer.hasPermission("activecraft.msg.spy")) continue;
                                    if(!new Profile(onlinePlayer).canReceiveSocialspy()) continue;
                                    if (onlinePlayer != player && onlinePlayer != target)
                                        onlinePlayer.sendMessage(CommandMessages.SOCIALSPY_PREFIX_TO(player, target, message));
                                }
                                if (mainConfig.getBoolean("socialspy-to-console")) {
                                    Bukkit.getConsoleSender().sendMessage(CommandMessages.SOCIALSPY_PREFIX_TO(target, player, event.getMessage()));
                                }
                                message = "";

                            } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
                        } else sender.sendMessage(Errors.WARNING() + CommandMessages.CANNOT_MESSAGE_SELF());
                    } else player.sendMessage(Errors.INVALID_PLAYER());
                } else player.sendMessage(Errors.WARNING() + CommandMessages.INCLUDE_PLAYER());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else if (args.length >= 1) {
            Player target = Bukkit.getPlayer(args[0]);

            if (target != null) {
                if (args.length > 1) {
                    for (int i = 1; i < args.length; i++) {
                        message = message + args[i] + " ";
                    }

                    message = ColorUtils.replaceColor(message);
                    message = ColorUtils.replaceFormat(message);

                    MsgEvent event = new MsgEvent(sender, target, message);
                    Bukkit.getPluginManager().callEvent(event);
                    if (event.isCancelled()) return false;

                    target.sendMessage(CommandMessages.CONSOLE_MSG_PREFIX(event.getMessage()));
                    target.playSound(target.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1f, 1f);
                    message = "";
                } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
            } else sender.sendMessage(Errors.INVALID_PLAYER());
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        if (args.length == 1) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                list.add(player.getName());
            }
        }
        ArrayList<String> completerList = new ArrayList<>();
        String currentarg = args[args.length-1].toLowerCase();
        for (String s : list) {
            String s1 = s.toLowerCase();
            if (s1.startsWith(currentarg)){
                completerList.add(s);
            }
        }

        return completerList;
    }
}