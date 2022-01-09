package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.utils.ComparisonType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class KickCommand extends ActiveCraftCommand {

    public KickCommand() {
        super("kick");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "kick");
        checkArgsLength(args, ComparisonType.GREATER_EQUAL, 1);
        Player target = getPlayer(args[0]);
        if (args.length == 1) {
            target.kickPlayer(CommandMessages.DEFAULT_KICK_MESSAGE());
            sendMessage(sender, CommandMessages.DEFAULT_KICK(target));
        } else {
            sendMessage(sender, CommandMessages.CUSTOM_KICK(target, combineArray(args)));
            target.kickPlayer(CommandMessages.CUSTOM_KICK_MESSAGE(combineArray(args)));
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}