package de.silencio.activecraftcore.templates;

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

import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Date;

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

                } else sender.sendMessage(ChatColor.RED + "Warning! " + ChatColor.GOLD + "This player is already banned.");

            } else if (label.equalsIgnoreCase("unban")) {

                BanManager banManager = new BanManager(BanList.Type.NAME);
                if (args.length == 1) {
                    banManager.unban(args[0]);
                    sender.sendMessage(ChatColor.GOLD + "Unbanned " + ChatColor.AQUA + args[0]);
                } else sender.sendMessage(Errors.TOO_MANY_ARGUMENTS);
            } else if (label.equalsIgnoreCase("ban-ip")) {

                BanManager banManager = new BanManager(BanList.Type.IP);
                Player target = Bukkit.getPlayer(args[0]);

                switch (args.length) {
                    case 1:
                        banManager.ban(target.getAddress().toString(), ChatColor.RED + "** Banned by an Operator **", null, sender.getName());
                        break;
                    case 2:
                        banManager.ban(target.getAddress().toString(), args[1], null, sender.getName());
                        break;
                    case 3:
                        banManager.ban(target.getAddress().toString(), args[1], null, sender.getName());
                        break;
                    case 4:
                        banManager.ban(target.getAddress().toString(), args[1], null, args[3]);
                        break;
                }

                if (args.length > 4) {
                    sender.sendMessage(Errors.TOO_MANY_ARGUMENTS);

                } else sender.sendMessage(ChatColor.GOLD + "Banned " + ChatColor.AQUA + args[0]);

            } else if (label.equalsIgnoreCase("unban-ip")) {

                BanManager banManager = new BanManager(BanList.Type.IP);
                Player target = Bukkit.getPlayer(args[0]);
                if (args.length == 1) {
                    banManager.unban(target.getAddress().toString());
                    sender.sendMessage(ChatColor.GOLD + "Unbanned " + ChatColor.AQUA + args[0]);
                } else sender.sendMessage(Errors.TOO_MANY_ARGUMENTS);
            } else if (label.equalsIgnoreCase("banlist")) {
                BanManager banManagerIp = new BanManager(BanList.Type.IP);
                BanManager banManagerName = new BanManager(BanList.Type.NAME);


                if (!banManagerIp.getBans().isEmpty() || !banManagerName.getBans().isEmpty()) {
                    for (BanEntry banEntry : banManagerIp.getBans()) {
                        sender.sendMessage("------------------\n"
                                + "IP: " + banEntry.getTarget() + "\n"
                                + "Reason: " + banEntry.getReason() + "\n"
                                + "Created: " + banEntry.getCreated() + "\n"
                                + "Expires: " + banEntry.getExpiration() + "\n"
                                + "Source: " + banEntry.getSource() + "\n"
                                + "------------------"
                        );
                    }

                    for (BanEntry banEntry : banManagerName.getBans()) {
                        sender.sendMessage("------------------");
                        sender.sendMessage("Name: " + banEntry.getTarget());
                        sender.sendMessage("Reason: " + banEntry.getReason());
                        sender.sendMessage("Created: " + banEntry.getCreated());
                        sender.sendMessage("Expires: " + banEntry.getExpiration());
                        sender.sendMessage("Source: " + banEntry.getSource());
                        sender.sendMessage("------------------");
                    }
                } else sender.sendMessage(ChatColor.RED + "Warning!" + ChatColor.GOLD + " There are no bans to be listed!");
            }

        } else sender.sendMessage(Errors.NO_PERMISSION);

        return true;

    }

}
