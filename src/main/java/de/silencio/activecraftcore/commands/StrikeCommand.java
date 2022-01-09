package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.utils.ComparisonType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class StrikeCommand extends ActiveCraftCommand {

    public StrikeCommand() {
        super("strike");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "strike");
        checkArgsLength(args, ComparisonType.EQUAL, 1);
        Player target = getPlayer(args[0]);
        target.getWorld().strikeLightning(target.getLocation());
        sendSilentMessage(target, CommandMessages.STRIKE());
        sendMessage(sender, CommandMessages.STRIKE_OTHERS(target));
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}