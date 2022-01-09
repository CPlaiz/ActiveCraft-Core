package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GodCommand extends ActiveCraftCommand {

    public GodCommand() {
        super("god");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {

        if (args.length == 0) {
            checkPermission(sender, "god.self");
            Player player = getPlayer(sender);
            Profile profile = getProfile(player);
            if (!profile.isGodmode()) {
                player.setInvulnerable(true);
                sendMessage(sender, CommandMessages.ENABLE_GOD());
                profile.set(Profile.Value.GODMODE, true);
            } else if (profile.isGodmode()) {
                player.setInvulnerable(false);
                sendMessage(sender, CommandMessages.DISABLE_GOD());
                profile.set(Profile.Value.GODMODE, false);
            }
        } else {
            checkPermission(sender, "god.others");
            Player target = getPlayer(args[0]);
            checkTargetSelf(sender, target, "god.self");
            Profile profile = getProfile(target);
            if (!profile.isGodmode()) {
                if (!checkTargetSelf(sender, target, "god.self")) sendSilentMessage(target, CommandMessages.ENABLE_GOD_OTHERS_MESSAGE(sender));
                target.setInvulnerable(true);
                sendMessage(sender, CommandMessages.ENABLE_GOD_OTHERS(target));
                target.sendMessage(CommandMessages.ENABLE_GOD_OTHERS_MESSAGE(sender));
                profile.set(Profile.Value.GODMODE, true);
            } else if (profile.isGodmode()) {
                if (!checkTargetSelf(sender, target, "god.self")) sendSilentMessage(target, CommandMessages.DISABLE_GOD_OTHERS_MESSAGE(sender));
                target.setInvulnerable(false);
                sendMessage(sender, CommandMessages.DISABLE_GOD_OTHERS(target));
                profile.set(Profile.Value.GODMODE, false);
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}