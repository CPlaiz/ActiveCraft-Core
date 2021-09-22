package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FormatNickCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (sender.hasPermission("activecraft.formatnick.self")) {
                    for (ChatColor color : ChatColor.values()) {
                        if (args[0].toLowerCase().equals(color.name().toLowerCase())) {
                            if (!args[0].equals("BOLD") && !args[0].equals("MAGIC") && !args[0].equals("STRIKETHROUGH") &&
                                    !args[0].equals("ITALIC") && !args[0].equals("UNDERLINE") && !args[0].equals("RESET")) {

                                FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");

                                player.setPlayerListName(color + playerdataConfig.getString("nickname"));
                                player.setDisplayName(color + playerdataConfig.getString("nickname"));
                                playerdataConfig.set("colornick", color.name());
                                playerdataConfig.saveConfig();
                                player.sendMessage(ChatColor.GOLD + "Name color changed to " + color + color.name());
                            }
                        }
                    }
                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.NOT_A_PLAYER());
        }

        if (args.length == 2) {
            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(Errors.INVALID_PLAYER());
                return false;
            }
            Player target = Bukkit.getPlayer(args[0]);

            if (sender.hasPermission("activecraft.formatnick.others")) {
                if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                    if (!sender.hasPermission("activecraft.formatnick.self")) {
                        sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                        return false;
                    }
                }
                for (ChatColor color : ChatColor.values()) {
                    if (args[1].toLowerCase().equals(color.name().toLowerCase())) {
                        if (!args[1].equals("BOLD") && !args[1].equals("MAGIC") && !args[1].equals("STRIKETHROUGH") &&
                                !args[1].equals("ITALIC") && !args[1].equals("UNDERLINE") && !args[1].equals("RESET")) {

                            FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + target.getName().toLowerCase() + ".yml");

                            target.setPlayerListName(color + playerdataConfig.getString("nickname"));
                            target.setDisplayName(color + playerdataConfig.getString("nickname"));
                            playerdataConfig.set("colornick", color.name());
                            playerdataConfig.saveConfig();
                            sender.sendMessage(ChatColor.GOLD + "Name color for " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " changed to " + color + color.name() + ChatColor.GOLD + ".");
                            if (sender instanceof Player) {
                                target.sendMessage(ChatColor.GOLD + "Name color changed to " + color + color.name() + ChatColor.GOLD + " by " + ChatColor.AQUA + ((Player) sender).getName() + ChatColor.GOLD + ".");
                            }
                            if (sender instanceof Player) {
                                target.sendMessage(ChatColor.GOLD + "Name color changed to " + color + color.name() + ChatColor.GOLD + " by " + ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD + ".");
                            } else target.sendMessage(ChatColor.GOLD + "Name color changed to " + color + color.name() + ChatColor.GOLD + " by " + ChatColor.AQUA + sender.getName() + ChatColor.GOLD + ".");
                        }
                    }
                }
            } else sender.sendMessage(Errors.NO_PERMISSION());
        }

        if (args.length == 0) {
            sender.sendMessage(Errors.INVALID_ARGUMENTS());
        }
        if (args.length > 2) {
            sender.sendMessage(Errors.TOO_MANY_ARGUMENTS());
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        if (args.length == 1) {
            for (Player p : Main.getPlugin().getServer().getOnlinePlayers()) {
                list.add(p.getName());
            }
            for (ChatColor color : ChatColor.values()) {
                if (color != ChatColor.BOLD && color != ChatColor.MAGIC && color != ChatColor.UNDERLINE && color != ChatColor.ITALIC && color != ChatColor.STRIKETHROUGH) {
                    list.add(color.name());
                }
            }
        } else if (args.length == 2) {
            for (ChatColor color : ChatColor.values()) {
                if (color != ChatColor.BOLD && color != ChatColor.MAGIC && color != ChatColor.UNDERLINE && color != ChatColor.ITALIC && color != ChatColor.STRIKETHROUGH && color != ChatColor.RESET) {
                    list.add(color.name());
                }
            }
        }
        ArrayList<String> completerList = new ArrayList<>();
        String currentarg = args[args.length - 1].toLowerCase();
        for (String s : list) {
            String s1 = s.toLowerCase();
            if (s1.startsWith(currentarg)) {
                completerList.add(s);
            }
        }

        return completerList;
    }
}