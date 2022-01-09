package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayTimeCommand extends ActiveCraftCommand {

    public PlayTimeCommand() {
        super("playtime");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        if (args.length == 0) {
            checkPermission(sender, "playtime.self");
            Player player = getPlayer(sender);
            Profile profile = getProfile(player);
            int hours = profile.getPlaytimeHours();
            int minutes = profile.getPlaytimeMinutes();
            sendMessage(sender, CommandMessages.PLAYTIME(hours + "", minutes + ""));
        } else if (args.length == 1) {
            checkPermission(sender, "playtime.others");
            Profile profile = getProfile(args[0]);
            checkTargetSelf(sender, profile.getName(), "playtime.self");
            if (!ActiveCraftCore.getPlayerlist().containsKey(args[0].toLowerCase()))
                sendMessage(sender, Errors.INVALID_PLAYER());
            int hours = profile.getPlaytimeHours();
            int minutes = profile.getPlaytimeMinutes();
            sendMessage(sender, CommandMessages.PLAYTIME_OTHERS(args[0], profile.getNickname(), hours + "", minutes + ""));
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? new ArrayList<>(getProfileNames()) : null;
    }
}