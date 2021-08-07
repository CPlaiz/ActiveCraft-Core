package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.WarpManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WarpCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {

            Player player = (Player) sender;

            if(args.length == 1) {
                    if (label.equalsIgnoreCase("warp")) {
                        if(WarpManager.getWarp(args[0]) != null) {
                        if (player.hasPermission("activecraft.warp." + args[0])) {

                            player.teleport(WarpManager.getWarp(args[0]));
                            player.sendMessage(ChatColor.GOLD + "You warped to " + ChatColor.AQUA + args[0] + ChatColor.GOLD + ".");
                            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);

                        } else sender.sendMessage(Errors.NO_PERMISSION);
                    } else sender.sendMessage(Errors.WARNING + "This warp does not exist!");
                }

                if (label.equalsIgnoreCase("setwarp")) {
                    if(player.hasPermission("activecraft.setwarp")) {
                        if (WarpManager.getWarp(args[0]) == null) {
                            FileConfig warpListConfig = new FileConfig("warplist.yml");
                            FileConfig warpsConfig = new FileConfig("warps.yml");
                            List<String> warpList = warpListConfig.getStringList("warplist");
                            if (!warpList.contains(args[0])) {
                                warpList.add(args[0]);
                                System.out.println(warpList);
                            }
                            warpListConfig.set("warplist", warpList);
                            warpListConfig.saveConfig();

                            player.sendMessage(ChatColor.GOLD + "Created the warp " + ChatColor.AQUA + args[0] + ChatColor.GOLD + ".");
                            WarpManager.createWarp(args[0], player.getLocation());
                        } else sender.sendMessage(Errors.WARNING + "This warp already exists!");
                    } else sender.sendMessage(Errors.NO_PERMISSION);

                } else if (label.equalsIgnoreCase("delwarp")) {
                    if (player.hasPermission("activecraft.deletewarp")) {
                        if (WarpManager.getWarp(args[0]) != null) {
                            FileConfig warpListConfig = new FileConfig("warplist.yml");
                            FileConfig warpsConfig = new FileConfig("warps.yml");
                            List<String> warpList = warpListConfig.getStringList("warplist");

                            warpList.remove(args[0]);

                            warpListConfig.set("warplist", warpList);
                            warpListConfig.saveConfig();

                            player.sendMessage(ChatColor.GOLD + "Deleted the warp " + ChatColor.AQUA + args[0] + ChatColor.GOLD + ".");
                            WarpManager.deleteWarp(args[0]);
                        } else sender.sendMessage(Errors.WARNING + "This warp does not exist!");
                    } else sender.sendMessage(Errors.NO_PERMISSION);
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
                            } else sender.sendMessage(Errors.WARNING + "There are no portals to be listed!");
                    } else sender.sendMessage(Errors.NO_PERMISSION);
                }


        } else sender.sendMessage(Errors.NOT_A_PLAYER);
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
                list.addAll(warpListConfig.getStringList("warplist"));
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
