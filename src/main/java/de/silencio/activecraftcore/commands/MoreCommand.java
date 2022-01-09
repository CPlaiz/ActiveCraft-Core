package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MoreCommand extends ActiveCraftCommand {

    public MoreCommand() {
        super("more");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "more");
        Player player = getPlayer(sender);
        if(args.length == 0) {
            ItemStack is = player.getInventory().getItemInMainHand();
            if (is.getType() != Material.AIR) {
                is.setAmount(is.getMaxStackSize());
            } else sendMessage(sender, Errors.NOT_HOLDING_ITEM());
        } else if(args.length == 1) {
            if(Integer.parseInt(args[0]) < 128) {
                ItemStack is = player.getInventory().getItemInMainHand();
                if (is.getType() != Material.AIR) {
                    is.setAmount(Integer.parseInt(args[0]));
                } else sendMessage(sender, Errors.NOT_HOLDING_ITEM());
            } else sendMessage(sender, Errors.WARNING() + CommandMessages.CANNOT_STACK());
        } else sendMessage(sender, Errors.TOO_MANY_ARGUMENTS());
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}