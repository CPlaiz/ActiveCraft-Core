package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.List;

public class SetHomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfig homeconfig = new FileConfig("homes.yml");

        if (args.length == 1) {
            if (sender.hasPermission("activecraft.sethome.self")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;

                    Location loc = player.getLocation();
                    String playerName = player.getName();

                    if (args[0].equalsIgnoreCase("home_list")) {
                        sender.sendMessage(Errors.INVALID_ARGUMENTS());
                        return false;
                    }

                    List<String> homeList = homeconfig.getStringList(playerName + ".home_list");
                    int maxHomes = 1;
                    for (PermissionAttachmentInfo perm : player.getEffectivePermissions()) {
                        if (perm.getPermission().startsWith("activecraft.maxhomes.")) {
                            if (Bukkit.getPluginManager().getPermission(perm.getPermission()) == null) Bukkit.getPluginManager().addPermission(new Permission(perm.getPermission()));
                        }
                    }
                    for (Permission perm : Bukkit.getPluginManager().getPermissions()) {
                        if (perm.getName().startsWith("activecraft.maxhomes.")) {
                            String[] permParts = perm.getName().split("\\.");
                            maxHomes = Integer.parseInt(permParts[2]);
                        }
                    }
                    if (!(homeList.size() < maxHomes || player.isOp())) {
                        player.sendMessage(Errors.WARNING() + CommandMessages.MAX_HOMES());
                        return false;
                    }
                    homeList.add(args[0]);
                    homeconfig.set(playerName + ".home_list", homeList);
                    homeconfig.set(playerName + "." + args[0], loc);
                    homeconfig.saveConfig();

                    sender.sendMessage(CommandMessages.HOME_SET(args[0].toString()));
                } else sender.sendMessage(Errors.NOT_A_PLAYER());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        }

        if (args.length == 2) {
            if (sender.hasPermission("activecraft.sethome.others")) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER());
                    return false;
                }
                Player player = (Player) sender;
                Player target = Bukkit.getPlayer(args[0]);
                if (sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                    if (!sender.hasPermission("activecraft.sethome.self")) {
                        sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                        return false;
                    }
                }
                if (args[1].equalsIgnoreCase("home_list")) {
                    sender.sendMessage(Errors.INVALID_ARGUMENTS());
                    return false;
                }
                String targetName = target.getName();

                Location loc = ((Player) sender).getLocation();

                List<String> homeList = homeconfig.getStringList(targetName + ".home_list");
                int maxHomes = 1;
                for (Permission perm : Bukkit.getPluginManager().getPermissions()) {
                    if (perm.getName().startsWith("activecraft.maxhomes.")) {
                        String[] permParts = perm.getName().split(".");
                        maxHomes = Integer.parseInt(permParts[2]);
                    }
                }
                if (!(homeList.size() < maxHomes)) {
                    player.sendMessage(Errors.WARNING() + CommandMessages.MAX_HOMES_OTHERS());
                    return false;
                }
                homeList.add(args[1]);
                homeconfig.set(targetName + ".home_list", homeList);
                homeconfig.set(targetName + "." + args[1], loc);
                homeconfig.saveConfig();
                sender.sendMessage(CommandMessages.HOME_OTHERS_SET(target, args[1].toString()));
            } else sender.sendMessage(Errors.NO_PERMISSION());
        }
        if (args.length >= 3) {
            sender.sendMessage(Errors.TOO_MANY_ARGUMENTS());
        }
        if (args.length == 0) {
            sender.sendMessage(Errors.INVALID_ARGUMENTS());
        }
        return true;
    }
}