package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.playermanagement.Profile;
import de.silencio.activecraftcore.utils.ComparisonType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class LastCoordsCommand extends ActiveCraftCommand {

    public LastCoordsCommand() {
        super("lastcoords");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {

        checkPermission(sender, "lastcoords");
        checkArgsLength(args, ComparisonType.GREATER_EQUAL, 1);
        Profile profile = getProfile(args[0]);
        if (args.length == 1) {
            if (ActiveCraftCore.getPlayerlist().containsKey(args[0].toLowerCase())) {
                Location lastLocation = profile.getLastLocation("BEFORE_QUIT");
                if (lastLocation == null) sendMessage(sender, Errors.WARNING() + CommandMessages.NEVER_QUIT_SERVER());
                World world = lastLocation.getWorld();
                sendMessage(sender, CommandMessages.LASTCOORDS_MESSAGE(profile.getName(), profile.getNickname(),
                        ChatColor.GOLD + "X: " + ChatColor.AQUA + lastLocation.getBlockX() + ChatColor.GOLD
                                + ", Y: " + ChatColor.AQUA + lastLocation.getBlockY() + ChatColor.GOLD
                                + ", Z: " + ChatColor.AQUA + lastLocation.getBlockZ() + ChatColor.GOLD
                                + ", Yaw: " + ChatColor.AQUA + (int) lastLocation.getYaw() + ChatColor.GOLD
                                + ", Pitch: " + ChatColor.AQUA + (int) lastLocation.getPitch(),
                        world.getName()));
            }
        } else if (args.length == 2) {
            if (ActiveCraftCore.getPlayerlist().containsKey(args[0].toLowerCase())) {
                World world = getWorld(args[1]);
                Location lastLocation = profile.getLastLocation(world.getName());
                if (lastLocation == null) sendMessage(sender, Errors.WARNING() + CommandMessages.NEVER_ENTERED_WORLD());
                sendMessage(sender, CommandMessages.LASTCOORDS_MESSAGE(profile.getName(), profile.getNickname(),
                        ChatColor.GOLD + "X: " + ChatColor.AQUA + lastLocation.getBlockX() + ChatColor.GOLD
                                + ", Y: " + ChatColor.AQUA + lastLocation.getBlockY() + ChatColor.GOLD
                                + ", Z: " + ChatColor.AQUA + lastLocation.getBlockZ() + ChatColor.GOLD
                                + ", Yaw: " + ChatColor.AQUA + (int) lastLocation.getYaw() + ChatColor.GOLD
                                + ", Pitch: " + ChatColor.AQUA + (int) lastLocation.getPitch(),
                        world.getName()));
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 1)
            list.addAll(getProfileNames());
        else if (args.length == 2)
            for (World world : Bukkit.getWorlds())
                list.add(world.getName());
        return list;
    }
}
