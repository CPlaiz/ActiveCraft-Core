package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TableCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if(args.length < 1) {

            if (label.equalsIgnoreCase("craftingtable")) {
                if (sender.hasPermission("activecraft.workbench")) {
                    ((Player) sender).openWorkbench(null, true);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }

            if (label.equalsIgnoreCase("anvil")) {
                if (sender.hasPermission("activecraft.anvil")) {
                    ((Player) sender).openAnvil(null, true);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }

            if (label.equalsIgnoreCase("enchanttable")) {
                if (sender.hasPermission("activecraft.enchanttable")) {
                    ((Player) sender).openEnchanting(null, true);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }

            if (label.equalsIgnoreCase("cartographytable")) {
                if (sender.hasPermission("activecraft.cartographytable")) {
                    ((Player) sender).openCartographyTable(null, true);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }

            if (label.equalsIgnoreCase("grindstone")) {
                if (sender.hasPermission("activecraft.grindstone")) {
                    ((Player) sender).openGrindstone(null, true);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }

            if (label.equalsIgnoreCase("loom")) {
                if (sender.hasPermission("activecraft.loom")) {
                    ((Player) sender).openLoom(null, true);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }

            if (label.equalsIgnoreCase("smithingtable")) {
                if (sender.hasPermission("activecraft.smithingtable")) {
                    ((Player) sender).openSmithingTable(null, true);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }

            if (label.equalsIgnoreCase("stonecutter")) {
                if (sender.hasPermission("activecraft.stonecutter")) {
                    ((Player) sender).openStonecutter(null, true);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }

        } else sender.sendMessage(Errors.INVALID_ARGUMENTS);

        return true;
    }
}
