package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.manager.LockdownManager;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.playermanagement.Profile;
import de.silencio.activecraftcore.utils.ComparisonType;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LockdownCommand extends ActiveCraftCommand {

    public LockdownCommand() {
        super("lockdown", "lockdownbypass");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        if (label.equalsIgnoreCase("lockdown")) {
            checkPermission(sender, "lockdown");
            checkArgsLength(args, ComparisonType.EQUAL, 1);
            FileConfig fileConfig = new FileConfig("config.yml");
            if (args[0].equalsIgnoreCase("enable")) {
                if (!fileConfig.getBoolean("lockdown")) {
                    LockdownManager.lockdown(true);
                    sendMessage(sender, CommandMessages.LOCKDOWN_ENABLED());
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (!player.hasPermission("activecraft.lockdown")) {
                            player.kickPlayer(fileConfig.getString("lockdown-kick-message"));
                        }
                    }
                } else sendMessage(sender, Errors.WARNING() + CommandMessages.LOCKDOWN_ALREADY_ENABLED());
            } else if (args[0].equalsIgnoreCase("disable")) {
                if (fileConfig.getBoolean("lockdown")) {
                    LockdownManager.lockdown(false);
                    sendMessage(sender, CommandMessages.LOCKDOWN_DISABLED());
                } else sendMessage(sender, Errors.WARNING() + CommandMessages.LOCKDOWN_NOT_ENABLED());
            }
        }
        if (label.equalsIgnoreCase("lockdownbypass")) {
            checkPermission(sender, "lockdown.allowbypass");
            checkArgsLength(args, ComparisonType.EQUAL, 2);
            Profile profile = getProfile(args[0]);
            if (profile != null) {
                if (args[1].equalsIgnoreCase("true")) {
                    if (!profile.canBypassLockdown()) {
                        profile.set(Profile.Value.BYPASS_LOCKDOWN, true);
                        sendMessage(sender, CommandMessages.ALLOW_PLAYER(profile.getName()));
                        sendMessage(sender, CommandMessages.ALLOW_PLAYER_EXTRA());
                    } else sendMessage(sender, Errors.WARNING() + CommandMessages.ALLOW_PLAYER_ALREADY_ENABLED(profile.getName()));
                } else if (args[1].equalsIgnoreCase("false")) {
                    if (profile.canBypassLockdown()) {
                        profile.set(Profile.Value.BYPASS_LOCKDOWN, false);
                        sendMessage(sender, CommandMessages.DISALLOW_PLAYER(profile.getName()));
                        sendMessage(sender, CommandMessages.DISALLOW_PLAYER_EXTRA());
                    } else sendMessage(sender, Errors.WARNING() + CommandMessages.DISALLOW_PLAYER_ALREADY_DISABLED(profile.getName()));
                } else sendMessage(sender, Errors.INVALID_ARGUMENTS());
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();

        if (label.equalsIgnoreCase("lockdown")) {
            if (args.length == 1) {
                list.add("enable");
                list.add("disable");
            }
        } else if (label.equalsIgnoreCase("lockdownbypass")) {
            if (args.length == 1) {
                list.addAll(getBukkitPlayernames());
            }
            if (args.length == 2) {
                list.add("true");
                list.add("false");
            }
        }
        return list;
    }
}
