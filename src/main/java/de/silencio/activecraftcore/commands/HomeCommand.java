package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.exceptions.InvalidArgumentException;
import de.silencio.activecraftcore.exceptions.InvalidHomeException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.ConfigUtils;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.ArrayList;
import java.util.List;

public class HomeCommand extends ActiveCraftCommand {

    public HomeCommand() {
        super("home", "delhome", "sethome");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        FileConfig homeConfig = ConfigUtils.getHomeConfig();

        switch (label.toLowerCase()) {
            case "home" -> {

                switch (args.length) {
                    case 1 -> {
                        checkPermission(sender, "home.self");
                        Player player = getPlayer(sender);
                        String playername = player.getName();
                        if (args[0].equalsIgnoreCase("home_list")) throw new InvalidArgumentException();
                        if (!homeConfig.contains(playername + "." + args[0]))
                            throw new InvalidHomeException(args[0]);
                        player.teleport(homeConfig.getLocation(playername + "." + args[0]));
                        sendMessage(sender, CommandMessages.TELEPORT_HOME_COMPLETE(args[0]));
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                    }
                    case 2 -> {
                        checkPermission(sender, "home.others");
                        Player target = getPlayer(args[0]);
                        checkTargetSelf(sender, target, "home.self");
                        String playername = target.getName();
                        if (args[1].equalsIgnoreCase("home_list")) throw new InvalidArgumentException();
                        if (!homeConfig.contains(playername + "." + args[1]))
                            throw new InvalidHomeException(args[1], target);
                        target.teleport(homeConfig.getLocation(playername + "." + args[1]));
                        sendMessage(sender, CommandMessages.TELEPORT_HOME_OTHERS_COMPLETE(target, args[1]));
                        target.playSound(target.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                    }
                }
            }
            case "sethome" -> {
                switch (args.length) {
                    case 1 -> {
                        checkPermission(sender, "sethome.self");
                        Player target = getPlayer(sender);
                        String playername = target.getName();
                        if (args[0].equalsIgnoreCase("home_list")) throw new InvalidArgumentException();
                        List<String> homeList = homeConfig.getStringList(playername + ".home_list");
                        int maxHomes = 1;
                        for (PermissionAttachmentInfo perm : target.getEffectivePermissions())
                            if (perm.getPermission().startsWith("activecraft.maxhomes."))
                                if (Bukkit.getPluginManager().getPermission(perm.getPermission()) == null) Bukkit.getPluginManager().addPermission(new Permission(perm.getPermission()));

                        for (Permission perm : Bukkit.getPluginManager().getPermissions())
                            if (perm.getName().startsWith("activecraft.maxhomes."))
                                maxHomes = Integer.parseInt(perm.getName().split("\\.")[2]);

                        if (!(homeList.size() < maxHomes || target.isOp())) {
                            target.sendMessage(Errors.WARNING() + CommandMessages.MAX_HOMES());
                            return;
                        }
                        if (!homeList.contains(args[0])) homeList.add(args[0]);
                        homeConfig.set(playername + ".home_list", homeList);
                        homeConfig.set(playername + "." + args[0], target.getLocation());
                        homeConfig.saveConfig();
                        sendMessage(sender, CommandMessages.HOME_SET(args[0]));
                    }
                    case 2 -> {
                        checkPermission(sender, "sethome.others");
                        Player target = getPlayer(args[0]);
                        checkTargetSelf(sender, target, "sethome.self");
                        String playername = target.getName();
                        if (args[1].equalsIgnoreCase("home_list")) throw new InvalidArgumentException();
                        List<String> homeList = homeConfig.getStringList(playername + ".home_list");
                        int maxHomes = 1;
                        for (PermissionAttachmentInfo perm : target.getEffectivePermissions())
                            if (perm.getPermission().startsWith("activecraft.maxhomes."))
                                if (Bukkit.getPluginManager().getPermission(perm.getPermission()) == null) Bukkit.getPluginManager().addPermission(new Permission(perm.getPermission()));

                        for (Permission perm : Bukkit.getPluginManager().getPermissions())
                            if (perm.getName().startsWith("activecraft.maxhomes."))
                                maxHomes = Integer.parseInt(perm.getName().split("\\.")[2]);

                        if (!(homeList.size() < maxHomes || target.isOp())) {
                            target.sendMessage(Errors.WARNING() + CommandMessages.MAX_HOMES_OTHERS());
                            return;
                        }
                        if (!homeList.contains(args[1])) homeList.add(args[1]);
                        homeConfig.set(playername + ".home_list", homeList);
                        homeConfig.set(playername + "." + args[1], target.getLocation());
                        homeConfig.saveConfig();
                        sendMessage(sender, CommandMessages.HOME_OTHERS_SET(target, args[1]));
                    }
                }
            }
            case "delhome" -> {
                switch (args.length) {
                    case 1 -> {
                        checkPermission(sender, "delhome.self");
                        Player player = getPlayer(sender);
                        String playername = player.getName();
                        if (args[0].equalsIgnoreCase("home_list")) throw new InvalidArgumentException();
                        if (homeConfig.contains(playername + "." + args[0])) throw new InvalidHomeException(args[0]);
                        List<String> homeList = homeConfig.getStringList(playername + ".home_list");
                        homeList.remove(args[0]);
                        homeConfig.set(playername + ".home_list", homeList);
                        homeConfig.set(playername + "." + args[0], null);
                        homeConfig.saveConfig();
                        sendMessage(sender, CommandMessages.HOME_DELETED(args[0]));
                    }
                    case 2 -> {
                        checkPermission(sender, "delhome.others");
                        Player target = getPlayer(args[0    ]);
                        checkTargetSelf(sender,target, "delhome.self");
                        String playername = target.getName();
                        if (args[1].equalsIgnoreCase("home_list")) throw new InvalidArgumentException();
                        if (homeConfig.contains(playername + "." + args[1])) throw new InvalidHomeException(args[1], target);
                        List<String> homeList = homeConfig.getStringList(playername + ".home_list");
                        homeList.remove(args[1]);
                        homeConfig.set(playername + ".home_list", homeList);
                        homeConfig.set(playername + "." + args[1], null);
                        homeConfig.saveConfig();
                        sendMessage(sender, CommandMessages.HOME_OTHERS_DELETED(target, args[1]));
                    }
                }
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        switch (label) {
            case "sethome" -> {
                if (args.length == 1) list.addAll(getProfileNames());
            }
            case "home", "delhome" -> {
                if (args.length == 1) {
                    list.addAll(ConfigUtils.getHomeConfig().getStringList(sender.getName() + ".home_list"));
                    list.addAll(getProfileNames());
                }
                if (args.length == 2 && Bukkit.getPlayer(args[0]) != null)
                    list.addAll(ConfigUtils.getHomeConfig().getStringList(Bukkit.getPlayer(args[0]).getName() + ".home_list"));
            }
        }
        return list;
    }
}