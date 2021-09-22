package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.manager.WarnManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WarnCommand implements CommandExecutor, TabCompleter {

    private HashMap<Player, WarnManager> warnEntryMap = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {



        if (args.length >= 2) {
            if (Bukkit.getPlayer(args[1]) == null) {
                sender.sendMessage(Errors.INVALID_PLAYER());
                return false;
            }
            Player target = Bukkit.getPlayer(args[1]);

            WarnManager warnManager = new WarnManager(target);
            if (args[0].equalsIgnoreCase("add")) {
                StringBuilder stringBuilder = new StringBuilder();
                if(sender.hasPermission("activecraft.warn.add")) {
                    if (args.length >= 3) {
                        for (int i = 2; i < args.length; i++) {
                            if (!(i == 2)) stringBuilder.append(" ");
                            stringBuilder.append(args[i]);
                        }
                    } else stringBuilder.append("Warned by a moderator.");
                } else sender.sendMessage(Errors.NO_PERMISSION());

                String source = sender.getName();
                warnManager.add(stringBuilder.toString(), source);
                sender.sendMessage(ChatColor.GOLD + "Warned " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + "\nReason: " + ChatColor.AQUA + warnManager.getWarnEntry(stringBuilder.toString()).reason);
            } else if (args[0].equalsIgnoreCase("remove")) {
                StringBuilder stringBuilder = new StringBuilder();
                if(sender.hasPermission("activecraft.warn.remove")) {
                    if (args.length >= 3) {
                        for (int i = 2; i < args.length; i++) {
                            if (!(i == 2)) stringBuilder.append(" ");
                            stringBuilder.append(args[i]);
                        }
                        sender.sendMessage(ChatColor.GOLD + "Removed warn " + warnManager.getWarnEntry(stringBuilder.toString()).reason + " from " + ChatColor.AQUA + target.getDisplayName());
                        warnManager.remove(args[2]);
                    } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else if (args[0].equalsIgnoreCase("get")) {
                StringBuilder stringBuilder = new StringBuilder();
                if(sender.hasPermission("activecraft.warn.get")) {
                    if (args.length >= 3) {
                        for (int i = 2; i < args.length; i++) {
                            if (!(i == 2)) stringBuilder.append(" ");
                            stringBuilder.append(args[i]);
                        }
                        StringBuilder strBuilder = new StringBuilder();
                        strBuilder.append(ChatColor.GOLD + "-- " + target.getName() + "'s Warn Entry --\n")
                                .append(ChatColor.GOLD + "Reason: " + ChatColor.AQUA).append(warnManager.getWarnEntry(stringBuilder.toString()).reason)
                                .append(ChatColor.GOLD + "\nCreated: " + ChatColor.AQUA).append(warnManager.getWarnEntry(stringBuilder.toString()).created)
                                .append(ChatColor.GOLD + "\nSource: " + ChatColor.AQUA).append(warnManager.getWarnEntry(stringBuilder.toString()).source)
                                .append(ChatColor.GOLD + "\nID: " + ChatColor.AQUA).append(warnManager.getWarnEntry(stringBuilder.toString()).id);
                        sender.sendMessage(strBuilder.toString());
                    } else sender.sendMessage(Errors.INVALID_PLAYER());
                } else sender.sendMessage(Errors.NO_PERMISSION());
            }
        } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Player p = (Player) sender;
        FileConfig warnsConfig = new FileConfig("warns.yml");
        if (args.length == 0) return list;
        if (args.length == 1) {
            list.add("add");
            list.add("remove");
            list.add("get");
        }

        if (args[0].equalsIgnoreCase("add")) {
            if (args.length == 2) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    list.add(player.getName());
                }
            }

        } else if (args[0].equalsIgnoreCase("remove")) {
            if (args.length == 2) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    list.add(player.getName());
                }
            }
            if (args.length == 3) {
                if (Bukkit.getPlayer(args[1]) != null) {
                    Player target = Bukkit.getPlayer(args[1]);
                    for(String s :warnsConfig.getStringList(target.getName() + ".warn-list")) {
                        s = s.replace("%dot%", ".");
                        list.add(s);
                    }
                }
            }
        } else if (args[0].equalsIgnoreCase("get")) {
            if (args.length == 2) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    list.add(player.getName());
                }
            }
            if (args.length == 3) {
                if (Bukkit.getPlayer(args[1]) != null) {
                    Player target = Bukkit.getPlayer(args[1]);
                    for(String s :warnsConfig.getStringList(target.getName() + ".warn-list")) {
                        s = s.replace("%dot%", ".");
                        list.add(s);
                    }
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
