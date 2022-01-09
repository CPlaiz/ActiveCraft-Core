package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class EditSignCommand implements CommandExecutor {

    String message = "";

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {

        switch (args.length) {
            case 0 -> {
                checkPermission(sender, "editsign.self");
                Player player = getPlayer(sender);
                Profile profile = getProfile(player);
                if (profile.canEditSign()) {
                    profile.set(Profile.Value.EDIT_SIGN, false);
                    sendMessage(sender, CommandMessages.DISABLED());
                } else {
                    profile.set(Profile.Value.EDIT_SIGN, true);
                    sendMessage(sender, CommandMessages.ENABLED());
                }
            }
            case 1 -> {
                checkPermission(sender, "editsign.others");
                Player target = getPlayer(args[0]);
                Profile profile = getProfile(target);
                checkTargetSelf(sender, target, "editsign.self");
                if (profile.canEditSign()) {
                    profile.set(Profile.Value.EDIT_SIGN, false);
                    sendMessage(sender, CommandMessages.DISABLED_OTHERS(target));
                    sendSilentMessage(target, CommandMessages.DISABLED_OTHERS_MESSAGE(sender));
                } else {
                    profile.set(Profile.Value.EDIT_SIGN, true);
                    sendMessage(sender, CommandMessages.ENABLED_OTHERS(target));
                    sendSilentMessage(target, CommandMessages.ENABLED_OTHERS_MESSAGE(sender));
                }
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String alias, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}