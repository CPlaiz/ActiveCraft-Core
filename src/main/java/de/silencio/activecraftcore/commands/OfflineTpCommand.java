package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class OfflineTpCommand extends ActiveCraftCommand {

    public OfflineTpCommand() {
        super("offlinetp", "offlinetphere");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {

        switch (label.toLowerCase()) {
            case "offlinetp" -> {
                Player player = getPlayer(sender);
                checkPermission(sender, "offlinetp");
                Profile profile = getProfile(args[0]);
                Location lastLoc = profile.getLastLocationBeforeQuit();
                if (lastLoc == null) {
                    sendMessage(sender, Errors.WARNING() + CommandMessages.NEVER_QUIT_SERVER());
                    return;
                }
                player.teleport(lastLoc);
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                sendMessage(sender,ChatColor.GOLD + "Teleported to " + ChatColor.AQUA + args[0] + ChatColor.GOLD + "'s last location.");
            }
            case "offlinetphere" -> {
                Player player = getPlayer(sender);
                checkPermission(sender, "offlinetphere");
                Profile profile = getProfile(args[0]);
                profile.set(Profile.Value.LAST_LOCATION, "BEFORE_QUIT", player.getLocation());
                sendMessage(sender, ChatColor.GOLD + "Set " + ChatColor.AQUA + args[0] + ChatColor.GOLD + "'s login point to current location.");
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String alias, String[] args) {
        return args.length == 1 ? new ArrayList<>(getProfileNames()) : null;
    }
}