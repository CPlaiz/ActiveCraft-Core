package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.manager.WarpManager;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WarpCommand extends ActiveCraftCommand {

    public WarpCommand() {
        super("warp", "setwarp", "delwarp");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        switch (label) {
            case "warp" -> {
                Player player = getPlayer(sender);
                if (args.length == 1) {
                    checkPermission(sender, "warp.self." + args[0]);
                    if (WarpManager.getWarp(args[0]) != null) {
                        WarpManager.warp(player, args[0]);
                        sendMessage(sender, CommandMessages.WARP_TELEPORT(args[0]));
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                    } else sendMessage(sender, Errors.WARNING() + CommandMessages.WARP_DOESNT_EXIST());
                } else if (args.length == 2) {
                    checkPermission(sender, "warp.others." + args[1]);
                    Player target = getPlayer(args[0]);
                    if (WarpManager.getWarp(args[1]) != null) {
                        if (!checkTargetSelf(sender, target, "warp.self." + args[1])) sendSilentMessage(target, CommandMessages.WARP_TELEPORT_OTHERS_MESSAGE(sender, args[1]));
                        WarpManager.warp(target, args[1]);
                        sendMessage(sender, CommandMessages.WARP_TELEPORT_OTHERS(target, args[1]));
                        target.playSound(target.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                    } else sendMessage(sender, Errors.WARNING() + CommandMessages.WARP_DOESNT_EXIST());
                }
            }
            case "setwarp" -> {
                checkPermission(sender, "warp.set");
                Player player = getPlayer(sender);
                if (WarpManager.getWarp(args[0]) == null) {
                    WarpManager.createWarp(args[0], player.getLocation());
                    sendMessage(sender, CommandMessages.WARP_SET(args[0]));
                } else sendMessage(sender, Errors.WARNING() + CommandMessages.WARP_ALREADY_EXISTS());
            }
            case "delwarp" -> {
                checkPermission(sender, "warp.delete");
                if (WarpManager.getWarp(args[0]) != null) {
                    WarpManager.deleteWarp(args[0]);
                    sendMessage(sender, CommandMessages.WARP_DELETED(args[0]));
                } else sendMessage(sender, Errors.WARNING() + CommandMessages.DOESNT_EXIST());
            }
            case "warps" -> {
                checkPermission(sender, "warp.list");
                FileConfig warpListConfig = new FileConfig("warplist.yml");
                FileConfig warpsConfig = new FileConfig("warps.yml");
                List<String> warpList = warpListConfig.getStringList("warplist");
                if (!warpList.isEmpty()) {
                    StringBuilder message = new StringBuilder();
                    for (String s : warpList) {
                        Location loc = warpsConfig.getLocation(s);
                        if (sender.hasPermission("activecraft.warp.self." + s) || sender.hasPermission("activecraft.warp.others." + s)) {
                            message.append(ChatColor.GOLD + s + ": " + ChatColor.GRAY + loc.getWorld().getName() + "; " + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ());
                            message.append("\n");
                        }
                    }
                    if (message.toString().equals("")) {
                        sendMessage(sender, Errors.WARNING() + CommandMessages.NO_WARPS());
                    } else {
                        sendMessage(sender, CommandMessages.WARPS_HEADER());
                        sendMessage(sender, message.toString());
                    }
                } else sendMessage(sender, Errors.WARNING() + CommandMessages.NO_WARPS());
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        FileConfig warpListConfig = new FileConfig("warplist.yml");
        switch (label) {
            case "warp" -> {
                if (args.length == 1) {
                    for (String s : warpListConfig.getStringList("warplist"))
                        if (sender.hasPermission("activecraft.warp.self." + s))
                            list.add(s);
                    list.addAll(getBukkitPlayernames());
                } else if (args.length == 2) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (args[0].equalsIgnoreCase(player.getName())) {
                            for (String s : warpListConfig.getStringList("warplist"))
                                if (sender.hasPermission("activecraft.warp.others." + s))
                                    list.add(s);
                        }
                    }
                }
            }
            case "delwarp" -> list.addAll(warpListConfig.getStringList("warplist"));
        }
        return list;
    }
}
