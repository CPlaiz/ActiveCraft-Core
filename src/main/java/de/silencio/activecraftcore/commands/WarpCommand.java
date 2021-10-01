package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.manager.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarpCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (label.equalsIgnoreCase("warp")) {

                if (args.length == 1) {
                    if (player.hasPermission("activecraft.warp.self." + args[0])) {
                        if (WarpManager.getWarp(args[0]) != null) {
                            WarpManager.warp(player, args[0]);
                            player.sendMessage(CommandMessages.WARP_TELEPORT(args[0]));
                            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                        } else sender.sendMessage(Errors.WARNING() + CommandMessages.WARP_DOESNT_EXIST());
                    } else sender.sendMessage(Errors.NO_PERMISSION());
                } else if (args.length == 2) {
                    if (player.hasPermission("activecraft.warp.others." + args[1])) {
                        if (WarpManager.getWarp(args[1]) != null) {
                            if (Bukkit.getPlayer(args[0]) == null) {
                                sender.sendMessage(Errors.INVALID_PLAYER());
                                return false;
                            }
                            Player target = Bukkit.getPlayer(args[0]);

                            if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                                if (!sender.hasPermission("activecraft.warp.self." + args[1])) {
                                    sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                                    return false;
                                }
                            }
                            WarpManager.warp(target, args[1]);
                            player.sendMessage(CommandMessages.WARP_TELEPORT_OTHERS(target, args[1]));
                            target.sendMessage(CommandMessages.WARP_TELEPORT_OTHERS_MESSAGE(target, args[1]));
                            target.playSound(target.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                        } else sender.sendMessage(Errors.WARNING() + CommandMessages.WARP_DOESNT_EXIST());
                    } else sender.sendMessage(Errors.NO_PERMISSION());
                }
            }
            if (args.length == 1) {
                if (label.equalsIgnoreCase("setwarp")) {
                    if (player.hasPermission("activecraft.setwarp")) {
                        if (WarpManager.getWarp(args[0]) == null) {
                            WarpManager.createWarp(args[0], player.getLocation());
                            player.sendMessage(CommandMessages.WARP_SET(args[0]));
                        } else sender.sendMessage(Errors.WARNING() + CommandMessages.WARP_ALREADY_EXISTS());
                    } else sender.sendMessage(Errors.NO_PERMISSION());

                } else if (label.equalsIgnoreCase("delwarp")) {
                    if (player.hasPermission("activecraft.deletewarp")) {
                        if (WarpManager.getWarp(args[0]) != null) {
                            WarpManager.deleteWarp(args[0]);
                            player.sendMessage(CommandMessages.WARP_DELETED(args[0]));
                        } else sender.sendMessage(Errors.WARNING() + CommandMessages.WARP_DOESNT_EXIST());
                    } else sender.sendMessage(Errors.NO_PERMISSION());
                }
            }
            if (label.equalsIgnoreCase("warps")) {
                if (player.hasPermission("activecraft.listwarps")) {
                    FileConfig warpListConfig = new FileConfig("warplist.yml");
                    FileConfig warpsConfig = new FileConfig("warps.yml");
                    List<String> warpList = warpListConfig.getStringList("warplist");
                    if (!warpList.isEmpty()) {
                        sender.sendMessage(ChatColor.GOLD + "Warps:");
                        for (String s : warpList) {
                            Location loc = warpsConfig.getLocation(s);
                            sender.sendMessage(ChatColor.GOLD + s + ": " + ChatColor.GRAY + loc.getWorld().getName() + "; " + loc.getBlockX() + "," + loc.getBlockY() + ", " + loc.getBlockZ());
                        }
                    } else sender.sendMessage(CommandMessages.NO_WARPS());
                } else sender.sendMessage(Errors.NO_PERMISSION());
            }


        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();

        Player p = (Player) sender;

        if (args.length == 0) return list;


        if (alias.equals("delwarp")) {
            if (args.length == 1) {
                FileConfig warpListConfig = new FileConfig("warplist.yml");
                list.addAll(warpListConfig.getStringList("warplist"));
            }
        }
        if (alias.equals("warp")) {
            if (args.length == 1) {
                FileConfig warpListConfig = new FileConfig("warplist.yml");
                for (String s : warpListConfig.getStringList("warplist")) {
                    if (sender.hasPermission("activecraft.warp.self." + s))
                        list.add(s);
                }
                for (Player player : Bukkit.getOnlinePlayers()) {
                    list.add(player.getName());
                }
            } else if (args.length == 2) {
                FileConfig warpListConfig = new FileConfig("warplist.yml");
                for (String s : warpListConfig.getStringList("warplist")) {
                    if (sender.hasPermission("activecraft.warp.others." + s))
                        list.add(s);
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
