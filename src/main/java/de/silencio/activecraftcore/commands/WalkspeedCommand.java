package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class WalkspeedCommand extends ActiveCraftCommand {

    public WalkspeedCommand() {
        super("walkspeed");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        if (args.length == 1) {
            checkPermission(sender, "walkspeed.self");
            Player player = getPlayer(sender);
            if (Integer.parseInt(args[0]) <= 5) {
                player.setWalkSpeed((float) parseInt(args[0]) / 5);
                sendMessage(sender, CommandMessages.WALKSPEED_SET(args[0]));
            } else sendMessage(sender, Errors.NUMBER_TOO_LARGE());
        } else if (args.length == 2) {
            checkPermission(sender, "walkspeed.others");
            Player target = getPlayer(args[0]);
            if (!checkTargetSelf(sender, target, "walkspeed.self")) sendSilentMessage(target, CommandMessages.WALKSPEED_SET_OTHERS_MESSAGE(sender, args[1]));
            if (Integer.parseInt(args[1]) <= 5) {
                target.setWalkSpeed((float) parseInt(args[1]) / 5);
                sendMessage(sender, CommandMessages.WALKSPEED_SET_OTHERS(target, args[1]));
            } else sendMessage(sender, Errors.NUMBER_TOO_LARGE());
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}