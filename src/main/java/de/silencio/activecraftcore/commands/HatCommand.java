package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class HatCommand extends ActiveCraftCommand {

    public HatCommand() {
        super("hat");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {

        checkPermission(sender, "hat");
        Player player = getPlayer(sender);
        ItemStack handitem = player.getInventory().getItemInMainHand();
        ItemStack helmetitem = player.getInventory().getHelmet();
        if (helmetitem == null) {
            helmetitem = new ItemStack(Material.AIR);
        }
        ItemStack emptyHand = new ItemStack(Material.AIR);
        if (!(handitem.getType() == Material.AIR && helmetitem.getType() == Material.AIR)) {
            player.getInventory().setHelmet(handitem);
            player.getInventory().setItemInMainHand(emptyHand);
            player.getInventory().addItem(helmetitem);
            sendMessage(sender, CommandMessages.HAT_SUCCESS());
        } else if (handitem.getType() != Material.AIR) {
            player.getInventory().setHelmet(handitem);
            player.getInventory().setItemInMainHand(emptyHand);
            sendMessage(sender, CommandMessages.HAT_SUCCESS());
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}