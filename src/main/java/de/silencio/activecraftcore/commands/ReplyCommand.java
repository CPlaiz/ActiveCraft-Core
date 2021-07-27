package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCommand extends MsgCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            if (sender.hasPermission("activecraft.reply")) {

                Player player = (Player) sender;

                Player answerTarget = playerStoring.get(player);

                if (args.length > 0) {
                    for (String arg : args) {
                        message = message + arg + " ";
                    }
                    player.sendMessage("§6[me -> " + answerTarget.getDisplayName() + "§6]§r " + message);
                    answerTarget.sendMessage("§6[" + player.getDisplayName() + "§6 -> me]§r " + message);
                    answerTarget.playSound(answerTarget.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1f, 1f);

                    //socialspy
                    Bukkit.broadcast(ChatColor.GOLD + "[" + player.getDisplayName() + ChatColor.GOLD + " -> " + answerTarget.getDisplayName() + ChatColor.GOLD + "] " + ChatColor.RESET + message, "activecraft.msg.spy");
                    message = "";
                } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
            } else sender.sendMessage(Errors.NO_PERMISSION);
        }
        return true;
    }


}
