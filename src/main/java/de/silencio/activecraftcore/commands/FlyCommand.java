package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class FlyCommand extends ActiveCraftCommand {

    public FlyCommand() {
        super("fly", "flyspeed");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {

        switch (label) {
            case "fly" -> {
                switch (args.length) {
                    case 0 -> {
                        checkPermission(sender, "fly.self");
                        Player player = getPlayer(sender);
                        Profile profile = getProfile(player);
                        if (profile.canFly()) {
                            profile.set(Profile.Value.FLY, false);
                            player.setAllowFlight(false);
                            sendMessage(sender, CommandMessages.DISABLE_FLY());
                        } else if (!profile.canFly()) {
                            player.setAllowFlight(true);
                            profile.set(Profile.Value.FLY, true);
                            sendMessage(sender, CommandMessages.ENABLE_FLY());
                        }
                    }
                    case 1 -> {
                        checkPermission(sender, "fly.others");
                        Player target = getPlayer(args[0]);
                        Profile profile = getProfile(target);
                        if (profile.canFly()) {
                            if (!checkTargetSelf(sender, target, "fly.self")) sendSilentMessage(target, CommandMessages.DISABLED_FLY_OTHERS_MESSAGE(sender));
                            target.setAllowFlight(false);
                            sendMessage(sender, CommandMessages.DISABLE_FLY_OTHERS(target));
                            profile.set(Profile.Value.FLY, false);
                        } else if (!profile.canFly()) {
                            if (!checkTargetSelf(sender, target, "fly.self")) sendSilentMessage(target, CommandMessages.ENABLE_FLY_OTHERS_MESSAGE(sender));
                            target.setAllowFlight(true);
                            sendMessage(sender, CommandMessages.ENABLE_FLY_OTHERS(target));
                            profile.set(Profile.Value.FLY, true);
                        }
                    }
                }
            }
            case "flyspeed" -> {
                switch (args.length) {
                    case 1 -> {
                        checkPermission(sender, "flyspeed.self");
                        parseInt(args[0]);
                        Player player = getPlayer(sender);
                        Profile profile = getProfile(player);
                        if (Integer.parseInt(args[0]) <= 10) {
                            player.setFlySpeed((float) Integer.parseInt(args[0]) / 10);
                            sendMessage(sender, CommandMessages.FLYSPEED_SET(args[0]));
                            profile.set(Profile.Value.FLYSPEED, Integer.parseInt(args[0]));
                        } else sendMessage(sender, Errors.NUMBER_TOO_LARGE());
                    }
                    case 2 -> {
                        checkPermission(sender, "flyspeed.others");
                        Player target = getPlayer(args[0]);
                        if (!checkTargetSelf(sender, target, "flyspeed.self")) sendSilentMessage(target, CommandMessages.FLYSPEED_SET_OTHERS_MESSAGE(sender, args[1]));
                        Profile profile = getProfile(target);
                        if (Integer.parseInt(args[1]) <= 10) {
                            target.setFlySpeed((float) Integer.parseInt(args[1]) / 10);
                            sendSilentMessage(target, CommandMessages.FLYSPEED_SET_OTHERS_MESSAGE(sender, args[1]));
                            sendMessage(sender, CommandMessages.FLYSPEED_SET_OTHERS(target, args[1]));
                            profile.set(Profile.Value.FLYSPEED, Integer.parseInt(args[1]));
                        }
                    }
                }
            }
        }
    }
}