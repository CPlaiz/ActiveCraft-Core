package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.playermanagement.Profile;
import de.silencio.activecraftcore.utils.ComparisonType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class VerifyCommand extends ActiveCraftCommand {

    public VerifyCommand() {
        super("verify");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "verify");
        checkArgsLength(args, ComparisonType.EQUAL, 1);
        Player target = getPlayer(args[0]);
        Profile profile = getProfile(target);
        if (profile.isDefaultmuted()) {
            profile.set(Profile.Value.DEFAULTMUTED, false);
            target.sendMessage(CommandMessages.DEFAULT_MUTE_REMOVE());
            sendMessage(sender, CommandMessages.DEFAULT_MUTE_REMOVE_MESSAGE(target));
        } else sendMessage(sender, Errors.WARNING() + CommandMessages.NOT_DEFAULT_MUTED());
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}
