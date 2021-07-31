package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.BanManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.Console;
import java.util.HashMap;

public class MsgCommand implements CommandExecutor {

    public static HashMap<Player, Player> playerStoring = new HashMap<>();

    String message = "";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("activecraft.msg")) {

                if (args.length >= 1) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if (target != null) {
                        if (target != player) {
                            if (args.length > 1) {
                                for (int i = 1; i < args.length; i++) {
                                    message = message + args[i] + " ";
                                }
                                player.sendMessage("§6[me -> " + target.getDisplayName() + "§6]§r " + message);
                                target.sendMessage("§6[" + player.getDisplayName() + "§6 -> me]§r " + message);
                                target.playSound(target.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1f, 1f);

                                playerStoring.put(target, player);

                                //socialspy
                                Bukkit.broadcast(ChatColor.GOLD + "[" + player.getDisplayName() + ChatColor.GOLD + " -> " + target.getDisplayName() + ChatColor.GOLD + "] " + ChatColor.RESET + message, "activecraft.msg.spy");
                                message = "";
                            } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
                        } else
                            sender.sendMessage(ChatColor.RED + "Warning!" + ChatColor.GRAY + " You can't message yourself!");
                    } else player.sendMessage(ChatColor.GOLD + "Player not online.");
                } else player.sendMessage(ChatColor.GOLD + "Usage: /msg <Player>.");



            } else sender.sendMessage(Errors.NO_PERMISSION);
        } else if (args.length >= 1) {
            Player target = Bukkit.getPlayer(args[0]);

            if (target != null) {
                if (args.length > 1) {
                    for (int i = 1; i < args.length; i++) {
                        message = message + args[i] + " ";
                    }
                    target.sendMessage(ChatColor.GOLD + "[Console -> me] " + ChatColor.RESET + message);
                    target.playSound(target.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1f, 1f);
                    message = "";
                } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
            } else sender.sendMessage(ChatColor.GOLD + "Player not online.");
        }
        return true;
    }
}