package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TableCommands extends ActiveCraftCommand {

    public TableCommands() {
        super("craftingtable", "anvil", "enchanttable", "cartographytable", "grindstone", "loom", "smithingtable", "stonecutter");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        Player player = getPlayer(sender);
        switch (label) {
            case "craftingtable" -> {
                checkPermission(sender, "workbench");
                player.openWorkbench(null, true);
            }
            case "anvil" -> {
                checkPermission(sender, "anvil");
                player.openAnvil(null, true);
            }
            case "enchanttable" -> {
                checkPermission(sender, "enchanttable");
                player.sendMessage(ChatColor.RED + "This feature has been disabled.");
            }
            case "cartographytable" -> {
                checkPermission(sender, "cartographytable");
                player.openCartographyTable(null, true);
            }
            case "grindstone" -> {
                checkPermission(sender, "grindstone");
                player.openGrindstone(null, true);
            }
            case "loom" -> {
                checkPermission(sender, "loom");
                player.openLoom(null, true);
            }
            case "smithingtable" -> {
                checkPermission(sender, "smithingtable");
                player.openSmithingTable(null, true);
            }
            case "stonecutter" -> {
                checkPermission(sender, "stonecutter");
                player.openStonecutter(null, true);
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}