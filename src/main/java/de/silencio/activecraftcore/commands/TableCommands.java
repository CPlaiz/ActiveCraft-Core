package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TableCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {;

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {

                if (label.equalsIgnoreCase("craftingtable")) {
                    if (player.hasPermission("activecraft.workbench")) {
                        player.openWorkbench(null, true);
                    } else sender.sendMessage(Errors.NO_PERMISSION);
                }

                if (label.equalsIgnoreCase("anvil")) {
                    if (player.hasPermission("activecraft.anvil")) {
                        player.openAnvil(null, true);
                    } else sender.sendMessage(Errors.NO_PERMISSION);
                }

                if (label.equalsIgnoreCase("enchanttable")) {
                    if (player.hasPermission("activecraft.enchanttable")) {
                        player.openEnchanting(null, true);
                    } else sender.sendMessage(Errors.NO_PERMISSION);
                }

                if (label.equalsIgnoreCase("cartographytable")) {
                    if (player.hasPermission("activecraft.cartographytable")) {
                        player.openCartographyTable(null, true);
                    } else sender.sendMessage(Errors.NO_PERMISSION);
                }

                if (label.equalsIgnoreCase("grindstone")) {
                    if (player.hasPermission("activecraft.grindstone")) {
                        player.openGrindstone(null, true);
                    } else sender.sendMessage(Errors.NO_PERMISSION);
                }

                if (label.equalsIgnoreCase("loom")) {
                    if (player.hasPermission("activecraft.loom")) {
                        player.openLoom(null, true);
                    } else sender.sendMessage(Errors.NO_PERMISSION);
                }

                if (label.equalsIgnoreCase("smithingtable")) {
                    if (player.hasPermission("activecraft.smithingtable")) {
                        player.openSmithingTable(null, true);
                    } else sender.sendMessage(Errors.NO_PERMISSION);
                }

                if (label.equalsIgnoreCase("stonecutter")) {
                    if (player.hasPermission("activecraft.stonecutter")) {
                        player.openStonecutter(null, true);
                    } else sender.sendMessage(Errors.NO_PERMISSION);
                }
            } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
        }
        return true;
    }
}