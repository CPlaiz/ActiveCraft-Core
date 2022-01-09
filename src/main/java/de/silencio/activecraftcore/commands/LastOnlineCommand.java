package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class LastOnlineCommand extends ActiveCraftCommand {

    public LastOnlineCommand() {
        super("lastonline");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "lastonline");
        if (ActiveCraftCore.getPlayerlist().containsKey(args[0].toLowerCase())) {
            Profile profile = getProfile(args[0]);
            String lastonline = profile.getLastOnline();
            if (lastonline.equalsIgnoreCase("online")) sendMessage(sender, CommandMessages.LASTONLINE_ONLINE(profile, lastonline));
            else sendMessage(sender, CommandMessages.LASTONLINE_OFFLINE(
                    profile.getName(), lastonline).replace("%t_displayname%", ChatColor.AQUA + profile.getNickname() + ChatColor.GOLD)
            );
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? new ArrayList<>(getProfileNames()) : null;
    }
}