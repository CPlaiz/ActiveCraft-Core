package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BackCommand extends ActiveCraftCommand {

    public BackCommand() {
        super("back");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        Player player = getPlayer(sender);

        if (args.length == 0) {
            checkPermission(sender, "back.self");
            Location lastLoc = ActiveCraftCore.getLastLocationForPlayer(player);
            if (lastLoc != null) {
                player.teleport(lastLoc);
                sendMessage(sender, CommandMessages.TELEPORTED_BACK());
            } else sendMessage(sender, Errors.WARNING() + CommandMessages.NO_RETURN_LOCATION());
        } else {
            checkPermission(sender, "back.others");
            Player target = getPlayer(args[0]);
            Location lastLoc = ActiveCraftCore.getLastLocationForPlayer(target);

            if (lastLoc != null) {
                target.teleport(lastLoc);
                if (checkTargetSelf(sender, target, "back.self")) sendSilentMessage(target, CommandMessages.TELEPORTED_BACK_OTHERS_MESSAGE(sender));
                sendMessage(sender, CommandMessages.TELEPORTED_BACK_OTHERS(target));
            } else sendMessage(sender, Errors.WARNING() + CommandMessages.NO_RETURN_LOCATION_OTHERS(target));
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String alias, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}