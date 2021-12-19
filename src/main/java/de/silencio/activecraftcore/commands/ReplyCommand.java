package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.events.MsgEvent;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.ColorUtils;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ReplyCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            if (sender.hasPermission("activecraft.reply")) {

                String message = "";

                Player player = (Player) sender;
                Player answerTarget = ActiveCraftCore.getMsgPlayerStoring().get(player);
                FileConfig mainConfig = new FileConfig("config.yml");

                if (answerTarget != null) {
                    if (args.length > 0) {
                        for (String arg : args) {
                            message = message + arg + " ";
                        }

                        message = ColorUtils.replaceColor(message);
                        message = ColorUtils.replaceFormat(message);

                        MsgEvent event = new MsgEvent(sender, answerTarget, message);
                        Bukkit.getPluginManager().callEvent(event);
                        if (event.isCancelled()) return false;

                        player.sendMessage(CommandMessages.MSG_PREFIX_TO(answerTarget, message));
                        answerTarget.sendMessage(CommandMessages.MSG_PREFIX_FROM(player, message));
                        answerTarget.playSound(answerTarget.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1f, 1f);

                        ActiveCraftCore.getMsgPlayerStoring().put(answerTarget, player);

                        //socialspy
                        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                            if (!onlinePlayer.hasPermission("activecraft.msg.spy")) continue;
                            if (!new Profile(onlinePlayer).canReceiveSocialspy()) continue;
                            if (onlinePlayer != player && onlinePlayer != answerTarget)
                                onlinePlayer.sendMessage(CommandMessages.SOCIALSPY_PREFIX_TO(player, answerTarget, message));
                        }
                        if (mainConfig.getBoolean("socialspy-to-console")) {
                            Bukkit.getConsoleSender().sendMessage(CommandMessages.CONSOLE_MSG_PREFIX(message));
                        }
                        message = "";
                    } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
                } else sender.sendMessage(Errors.INVALID_PLAYER());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        ArrayList<String> completerList = new ArrayList<>();
        return completerList;
    }
}