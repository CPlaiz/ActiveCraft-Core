package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ClearInvCommand extends ActiveCraftCommand {

    public ClearInvCommand() {
        super("clearinventory");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        if (args.length == 0) {
            checkPermission(sender, "clearinv.self");
            Player player = getPlayer(sender);
            sendMessage(sender, CommandMessages.CLEARED_SELF());
            player.getInventory().clear();
        } else {
            checkPermission(sender, "clearinv.others");
            Player target = getPlayer(args[0]);
            if (!checkTargetSelf(sender, target, "clearinv.self"))
                sendSilentMessage(target, CommandMessages.CLEARED_OTHERS_MESSAGE(sender));
            sendMessage(sender, CommandMessages.CLEARED_OTHERS(target));
            target.getInventory().clear();
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String alias, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}