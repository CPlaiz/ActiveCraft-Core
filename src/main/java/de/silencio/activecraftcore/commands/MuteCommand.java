package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.manager.MuteManager;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.playermanagement.Profile;
import de.silencio.activecraftcore.utils.ComparisonType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MuteCommand extends ActiveCraftCommand {

    public MuteCommand() {
        super("mute");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "mute");
        checkArgsLength(args, ComparisonType.EQUAL, 1);
        Player target = getPlayer(args[0]);
        Profile profile = getProfile(target);
        if (label.equalsIgnoreCase("mute")) {
            if (!profile.isMuted()) {
                MuteManager.mutePlayer(target);
                sendMessage(sender, CommandMessages.MUTE(target));
                sendSilentMessage(target, CommandMessages.MUTE_MESSAGE());
            } else sendMessage(sender, Errors.WARNING() + CommandMessages.ALREADY_MUTED());
        }
        if (label.equalsIgnoreCase("unmute")) {
            if (profile.isMuted()) {
                MuteManager.unmutePlayer(target);
                sendMessage(sender, CommandMessages.UNMUTE(target));
                sendSilentMessage(target, CommandMessages.UNMUTE_MESSAGE());
            } else sendMessage(sender, Errors.WARNING() + CommandMessages.NOT_MUTED());
        }
        if (label.equalsIgnoreCase("forcemute")) {
            if (!profile.isForcemuted()) {
                profile.set(Profile.Value.FORCE_MUTED, true);
                sendMessage(sender, CommandMessages.FORCEMUTE(target));
            } else {
                profile.set(Profile.Value.FORCE_MUTED, false);
                sendMessage(sender, CommandMessages.FORCEUNMUTE(target));
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? new ArrayList<>(getProfileNames()) : null;
    }
}