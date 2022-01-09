package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SocialSpyToggleCommand extends ActiveCraftCommand {

    public SocialSpyToggleCommand() {
        super("togglesocialspy");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        Player player = getPlayer(sender);
        Profile profile = getProfile(player);
        if (profile.canReceiveSocialspy()) {
            profile.set(Profile.Value.RECEIVE_SOCIALSPY, false);
            sendMessage(sender, CommandMessages.SOCIALSPY_OFF());
        } else {
            profile.set(Profile.Value.RECEIVE_SOCIALSPY, true);
            sendMessage(sender, CommandMessages.SOCIALSPY_ON());
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? List.of("true", "false") : null;
    }

}