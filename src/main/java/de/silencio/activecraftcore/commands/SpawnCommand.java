package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SpawnCommand extends ActiveCraftCommand {

    public SpawnCommand() {
        super("spawn", "setspawn");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        FileConfig spawns = new FileConfig("spawn.yml");
        if (label.equalsIgnoreCase("setspawn")) {
            Player player = getPlayer(sender);
            checkPermission(sender, "spawn.set");
            spawns.set("spawn", LocationUtils.loc2Str(player.getLocation()));
            spawns.saveConfig();
            sendMessage(sender, CommandMessages.SPAWN_SET());
        } else if (label.equalsIgnoreCase("spawn")) {
            if (spawns.contains("spawn")) {
                if (args.length == 0) {
                    Player player = getPlayer(sender);
                    checkPermission(sender, "spawn.self");
                    LocationUtils.teleport(player, LocationUtils.str2Loc(spawns.getString("spawn")));
                    sendMessage(sender, CommandMessages.SPAWN_TELEPORT());
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                } else if (args.length == 1) {
                    checkPermission(sender, "spawn.others");
                    Player target = getPlayer(args[0]);
                    if (!checkTargetSelf(sender, target, "spawn.self")) sendSilentMessage(target, CommandMessages.SPAWN_TELEPORT_OTHERS_MESSAGE(sender));
                    LocationUtils.teleport(target, LocationUtils.str2Loc(spawns.getString("spawn")));
                    target.sendMessage(CommandMessages.SPAWN_TELEPORT_OTHERS_MESSAGE(sender));
                    sendMessage(sender, CommandMessages.SPAWN_TELEPORT_OTHERS(target));
                    target.playSound(target.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                } else sendMessage(sender, Errors.TOO_MANY_ARGUMENTS());
            } else sendMessage(sender, CommandMessages.NO_SPAWN_SET());
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}