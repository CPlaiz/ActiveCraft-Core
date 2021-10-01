package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.gui.*;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TableMenuCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(sender.hasPermission("activecraft.tablemenu")) {

                GuiCreator guiCreator = new GuiCreator(6);
                GuiCloseItem guiCloseItem = new GuiCloseItem(49);

                guiCreator.setItemInSlot(new GuiItem(Material.CRAFTING_TABLE).setDisplayName(ChatColor.GOLD + "Crafting Table").setMovable(false).setClickSound(true), 13);
                guiCreator.setItemInSlot(new GuiItem(Material.CARTOGRAPHY_TABLE).setDisplayName(ChatColor.GOLD + "Cartography Table").setMovable(false).setClickSound(true), 20);
                guiCreator.setItemInSlot(new GuiItem(Material.STONECUTTER).setDisplayName(ChatColor.GOLD + "Stonecutter").setMovable(false).setClickSound(true), 21);
                guiCreator.setItemInSlot(new GuiItem(Material.ANVIL).setDisplayName(ChatColor.GOLD + "Anvil").setMovable(false).setClickSound(true), 22);
                guiCreator.setItemInSlot(new GuiItem(Material.GRINDSTONE).setDisplayName(ChatColor.GOLD + "Grindstone").setMovable(false).setClickSound(true), 23);
                guiCreator.setItemInSlot(new GuiItem(Material.LOOM).setDisplayName(ChatColor.GOLD + "Loom").setMovable(false).setClickSound(true), 24);
                guiCreator.setItemInSlot(new GuiItem(Material.SMITHING_TABLE).setDisplayName(ChatColor.GOLD + "Smithing Table").setMovable(false).setClickSound(true), 31);
                guiCloseItem.setClickSound(true);
                guiCloseItem.setMovable(false);
                guiCreator.setTitle("Table Menu");
                guiCreator.fillBackground(true);
                guiCreator.setCloseItem(guiCloseItem);
                player.openInventory(guiCreator.build().getInventory());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }
}