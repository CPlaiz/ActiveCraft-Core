package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.BanManager;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class BanCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("activecraft.ban")) {

            if (label.equalsIgnoreCase("ban")) {

                BanManager banManager = new BanManager(BanList.Type.NAME);

                if (banManager.isBanned(args[0])) {

                    switch (args.length) {
                        case 1:
                            banManager.ban(args[0], ChatColor.RED + "** Banned by an Operator **", null, sender.getName());
                            Bukkit.getPlayer(args[0]).kickPlayer(ChatColor.RED + "You have been banned from this server.\n" + ChatColor.GOLD + "Reason: " + ChatColor.AQUA + "Banned by an operator.\n" + ChatColor.GOLD + "Until: Permanent");
                            break;
                        case 2:
                            banManager.ban(args[0], args[1], null, sender.getName());
                            Bukkit.getPlayer(args[0]).kickPlayer(ChatColor.RED + "You have been banned from this server.\n" + ChatColor.GOLD + "Reason: " + ChatColor.AQUA + args[1] + "\n" + ChatColor.GOLD + "Until: Permanent");
                            break;
                        case 3:
                            banManager.ban(args[0], args[1], null, sender.getName());
                            Bukkit.getPlayer(args[0]).kickPlayer(ChatColor.RED + "You have been banned from this server.\n" + ChatColor.GOLD + "Reason: " + ChatColor.AQUA + args[1] + "\n" + ChatColor.GOLD + "Until: Permanent");
                            break;
                        case 4:
                            banManager.ban(args[0], args[1], null, args[3]);
                            Bukkit.getPlayer(args[0]).kickPlayer(ChatColor.RED + "You have been banned from this server.\n" + ChatColor.GOLD + "Reason: " + ChatColor.AQUA + args[1] + "\n" + ChatColor.GOLD + "Until: Permanent");
                            break;
                    }

                    if (args.length > 4) {
                        sender.sendMessage(Errors.TOO_MANY_ARGUMENTS);
                    } else sender.sendMessage(ChatColor.GOLD + "Banned " + ChatColor.AQUA + args[0]);

                } else sender.sendMessage(Errors.WARNING + "This player is already banned.");


            }
        } else sender.sendMessage(Errors.NO_PERMISSION);
        return true;
    }

    @Override
    public void onDialogueNext() {
        if (dialogueManager.getActiveStep() == 1) {
            banManager.ban(target, dialogueManager.getAnswer(0), null, commandSender.getName());
        }
    }
}

// 1s = 1 second
// 1m = 1 minute
// 1h = 1 hour
// 1d = 1 day

// 1d5h = 1 day + 5 hours