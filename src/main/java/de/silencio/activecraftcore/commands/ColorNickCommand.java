package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColorNickCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (args.length == 1) {
                if(sender instanceof Player) {
                    Player player = (Player) sender;
                    FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");
                        if (sender.hasPermission("activecraft.colornick.self")) {

                            if(args[0].equalsIgnoreCase("random")) {
                                ChatColor[] colors = {ChatColor.GREEN, ChatColor.AQUA, ChatColor.BLUE,
                                        ChatColor.GRAY, ChatColor.GOLD, ChatColor.RED, ChatColor.WHITE,
                                        ChatColor.BLACK, ChatColor.DARK_AQUA, ChatColor.DARK_GRAY, ChatColor.DARK_GRAY,
                                        ChatColor.DARK_BLUE, ChatColor.DARK_GREEN, ChatColor.DARK_RED,
                                        ChatColor.DARK_PURPLE, ChatColor.LIGHT_PURPLE, ChatColor.YELLOW};
                                int idx = new Random().nextInt(colors.length);
                                ChatColor randomColor = colors[idx];

                                    player.setPlayerListName(randomColor + playerdataConfig.getString("nickname"));
                                    player.setDisplayName(randomColor + playerdataConfig.getString("nickname"));

                                    playerdataConfig.set("colornick", randomColor.name());
                                    playerdataConfig.saveConfig();

                                    player.sendMessage(ChatColor.GOLD + "Name color changed to " + randomColor + randomColor.name());
                            }

                            for (ChatColor color : ChatColor.values()) {
                                if (args[0].toLowerCase().equals(color.name().toLowerCase())) {
                                    if (!args[0].equalsIgnoreCase("BOLD") && !args[0].equalsIgnoreCase("MAGIC") && !args[0].equalsIgnoreCase("STRIKETHROUGH") &&
                                            !args[0].equalsIgnoreCase("ITALIC") && !args[0].equalsIgnoreCase("UNDERLINE") && !args[0].equalsIgnoreCase("RESET")) {

                                        player.setPlayerListName(color + playerdataConfig.getString("nickname"));
                                        player.setDisplayName(color + playerdataConfig.getString("nickname"));
                                        playerdataConfig.set("colornick", color.name());
                                        playerdataConfig.saveConfig();
                                        player.sendMessage(CommandMessages.COLORNICK_SELF(color.name()));
                                    }
                                }
                            }
                        } else sender.sendMessage(Errors.NO_PERMISSION());
                } else sender.sendMessage(Errors.NOT_A_PLAYER());
            }

            if (args.length == 2) {
                if(Bukkit.getPlayer(args[0]) != null) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Errors.INVALID_PLAYER());
                        return false;
                    }
                    Player target = Bukkit.getPlayer(args[0]);

                    if (sender.hasPermission("activecraft.colornick.others")) {
                        if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                            if (!sender.hasPermission("activecraft.colornick.self")) {
                                sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                                return false;
                            }
                        }
                        FileConfig targetdataConfig = new FileConfig("playerdata" + File.separator + target.getName().toLowerCase() + ".yml");

                        if(args[0].equalsIgnoreCase("random")) {
                            ChatColor[] colors = {ChatColor.GREEN, ChatColor.AQUA, ChatColor.BLUE,
                                    ChatColor.GRAY, ChatColor.GOLD, ChatColor.RED, ChatColor.WHITE,
                                    ChatColor.BLACK, ChatColor.DARK_AQUA, ChatColor.DARK_GRAY, ChatColor.DARK_GRAY,
                                    ChatColor.DARK_BLUE, ChatColor.DARK_GREEN, ChatColor.DARK_RED,
                                    ChatColor.DARK_PURPLE, ChatColor.LIGHT_PURPLE, ChatColor.YELLOW};
                            int idx = new Random().nextInt(colors.length);
                            ChatColor randomColor = colors[idx];

                            target.setPlayerListName(randomColor + targetdataConfig.getString("nickname"));
                            target.setDisplayName(randomColor + targetdataConfig.getString("nickname"));

                            targetdataConfig.set("colornick", randomColor.name());
                            targetdataConfig.saveConfig();

                            sender.sendMessage(CommandMessages.COLORNICK_OTHERS(target, randomColor.name()));
                            target.sendMessage(CommandMessages.COLORNICK_OTHERS_MESSAGE(sender, randomColor.name()));
                        }

                        for (ChatColor color : ChatColor.values()) {
                            if (args[1].toLowerCase().equals(color.name().toLowerCase())) {
                                if (!args[1].equalsIgnoreCase("BOLD") && !args[1].equalsIgnoreCase("MAGIC") && !args[1].equalsIgnoreCase("STRIKETHROUGH") &&
                                        !args[1].equalsIgnoreCase("ITALIC") && !args[1].equalsIgnoreCase("UNDERLINE") && !args[1].equalsIgnoreCase("RESET")) {

                                    target.setPlayerListName(color + targetdataConfig.getString("nickname"));
                                    target.setDisplayName(color + targetdataConfig.getString("nickname"));
                                    targetdataConfig.set("colornick", color.name());
                                    targetdataConfig.saveConfig();
                                    sender.sendMessage(CommandMessages.COLORNICK_OTHERS(target, color.name()));
                                    target.sendMessage(CommandMessages.COLORNICK_OTHERS_MESSAGE(sender, color.name()));
                                }
                            }
                        }
                    } else sender.sendMessage(Errors.NO_PERMISSION());
                } else sender.sendMessage(Errors.INVALID_PLAYER());
            }

            if(args.length == 0) {
                sender.sendMessage(Errors.INVALID_ARGUMENTS());
            }
            if(args.length > 2) {
                sender.sendMessage(Errors.TOO_MANY_ARGUMENTS());
            }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        if (args.length == 1) {
            list.add("random");
            for (Player p : Main.getPlugin().getServer().getOnlinePlayers()) {
                list.add(p.getName());
            }
            for (ChatColor color : ChatColor.values()) {
                if (color != ChatColor.BOLD && color != ChatColor.MAGIC && color != ChatColor.UNDERLINE && color != ChatColor.ITALIC && color != ChatColor.STRIKETHROUGH) {
                    list.add(color.name().toLowerCase().replace("_", " "));
                }
            }
        } else if (args.length == 2) {
            for (ChatColor color : ChatColor.values()) {
                if (color != ChatColor.BOLD && color != ChatColor.MAGIC && color != ChatColor.UNDERLINE && color != ChatColor.ITALIC && color != ChatColor.STRIKETHROUGH && color != ChatColor.RESET) {
                    list.add(color.name().toLowerCase().replace("_", " "));
                }
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