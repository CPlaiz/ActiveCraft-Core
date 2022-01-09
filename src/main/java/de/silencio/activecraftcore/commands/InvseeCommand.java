package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class InvseeCommand extends ActiveCraftCommand {

    public InvseeCommand() {
        super("invsee");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        if (args.length == 1) {
            checkPermission(sender, "invsee");
            Player target = getPlayer(args[0]);
            Player player = getPlayer(sender);
            player.openInventory(target.getInventory());
            player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1f, 1f);
            sendMessage(sender, CommandMessages.INVSEE(target));
        } else sendMessage(sender, Errors.INVALID_ARGUMENTS());
    }
    
    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}