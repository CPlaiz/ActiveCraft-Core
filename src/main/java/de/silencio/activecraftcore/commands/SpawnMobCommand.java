package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpawnMobCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("activecraft.summon.self")) {
                    World world = player.getWorld();

                    EntityType value = null;
                    try {
                        value = EntityType.valueOf(args[0]);
                    } catch (IllegalArgumentException ignored) {
                    }
                    if (value != null) {
                        world.spawnEntity(player.getLocation(), EntityType.valueOf(args[0]));

                        player.sendMessage(CommandMessages.SUMMON(args[0]));
                    } else sender.sendMessage(Errors.INVALID_ENTITY());
                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else if (args.length == 2) {
            if (Bukkit.getPlayer(args[0]) == null) {
                if (sender instanceof Player) {
                    if (sender.hasPermission("activecraft.summon.self.multiple")) {
                        Player player = (Player) sender;
                        World world = player.getWorld();

                        Integer num = null;
                        try {
                            num = Integer.valueOf(args[1]);
                        } catch (NumberFormatException ignored) {
                        }
                        if (num == null) {
                            sender.sendMessage(Errors.INVALID_NUMBER());
                            return false;
                        }
                        EntityType value = null;
                        try {
                            value = EntityType.valueOf(args[0]);
                        } catch (IllegalArgumentException ignored) {
                        }
                        if (value != null) {
                            player.sendMessage(CommandMessages.SUMMON_MULTIPLE(num + "", args[0]));
                            for (int i = 0; i < Integer.parseInt(args[1]); i++) {
                                world.spawnEntity(player.getLocation(), value);
                            }
                        } else sender.sendMessage(Errors.INVALID_ENTITY());
                    } else sender.sendMessage(Errors.NO_PERMISSION());
                } else sender.sendMessage(Errors.NOT_A_PLAYER());
            } else {
                if (sender.hasPermission("activecraft.summon.others")) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                        if (!sender.hasPermission("activecraft.summon.self")) {
                            sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                            return false;
                        }
                    }
                    World world = target.getWorld();

                    EntityType value = null;
                    try {
                        value = EntityType.valueOf(args[1]);
                    } catch (IllegalArgumentException ignored) {
                    }
                    if (value != null) {
                        world.spawnEntity(target.getLocation(), value);
                        sender.sendMessage(CommandMessages.SUMMON_OTHERS(target, args[0]));
                    } else sender.sendMessage(Errors.INVALID_ENTITY());
                }
            }
        } else if (args.length == 3) {
            if (Bukkit.getPlayer(args[0]) != null) {
                
                Player target = Bukkit.getPlayer(args[0]);
                if (sender.hasPermission("activecraft.summon.others.multiple")) {
                    Integer num = null;
                    try {
                        num = Integer.valueOf(args[2]);
                    } catch (NumberFormatException ignored) {
                    }
                    if (num == null) {
                        sender.sendMessage(Errors.INVALID_NUMBER());
                        return false;
                    }
                    if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                        if (!sender.hasPermission("activecraft.summon.self.multiple")) {
                            sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                            return false;
                        }
                    }

                    EntityType value = null;
                    try {
                        value = EntityType.valueOf(args[1]);
                    } catch (IllegalArgumentException ignored) {
                    }
                    if (value != null) {
                        sender.sendMessage(CommandMessages.SUMMON_OTHERS_MULTIPLE(target, num + "", value.name()));
                        for (int i = 0; i < Integer.parseInt(args[2]); i++) {
                            target.getWorld().spawnEntity(target.getLocation(), value);
                        }
                    } else sender.sendMessage(Errors.INVALID_ENTITY());
                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.INVALID_PLAYER());
        } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Player p = (Player) sender;

        if (args.length == 0) return list;
        if (args.length == 1) {
            for (EntityType entityType : EntityType.values()) {
                list.add(entityType.name());
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                list.add(player.getName());
            }
        }
        if (args.length == 2) {
            if (Bukkit.getPlayer(args[0]) != null) {
                for (EntityType entityType : EntityType.values()) {
                    if (!entityType.name().equals("UNKNOWN")) {
                        list.add(entityType.name());
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