package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.gui.*;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.messages.TableMenuMessages;
import de.silencio.activecraftcore.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TableMenuCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(sender.hasPermission("activecraft.tablemenu")) {

                GuiCreator guiCreator = new GuiCreator("table_menu", 6, TableMenuMessages.TABLEMENU_TITLE());
                GuiCloseItem guiCloseItem = new GuiCloseItem(49);

                guiCreator.setItemInSlot(new GuiItem(Material.CRAFTING_TABLE).setDisplayName(TableMenuMessages.TABLEMENU_CRAFTING_TABLE()).setMovable(false).setClickSound(true), 13);
                guiCreator.setItemInSlot(new GuiItem(Material.CARTOGRAPHY_TABLE).setDisplayName(TableMenuMessages.TABLEMENU_CARTOGRAPHY_TABLE()).setMovable(false).setClickSound(true), 20);
                guiCreator.setItemInSlot(new GuiItem(Material.STONECUTTER).setDisplayName(TableMenuMessages.TABLEMENU_STONECUTTER()).setMovable(false).setClickSound(true), 21);
                guiCreator.setItemInSlot(new GuiItem(Material.ANVIL).setDisplayName(TableMenuMessages.TABLEMENU_ANVIL()).setMovable(false).setClickSound(true), 22);
                guiCreator.setItemInSlot(new GuiItem(Material.GRINDSTONE).setDisplayName(TableMenuMessages.TABLEMENU_GRINDSTONE()).setMovable(false).setClickSound(true), 23);
                guiCreator.setItemInSlot(new GuiItem(Material.LOOM).setDisplayName(TableMenuMessages.TABLEMENU_LOOM()).setMovable(false).setClickSound(true), 24);
                guiCreator.setItemInSlot(new GuiItem(Material.SMITHING_TABLE).setDisplayName(TableMenuMessages.TABLEMENU_SMITHING_TABLE()).setMovable(false).setClickSound(true), 31);
                guiCloseItem.setClickSound(true);
                guiCloseItem.setMovable(false);
                guiCreator.fillBackground(true);
                guiCreator.setCloseItem(guiCloseItem);
                player.openInventory(guiCreator.build().getInventory());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }
}