package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.gui.*;
import de.silencio.activecraftcore.messages.Errors;
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

                GuiCreator guiCreator = new GuiCreator(6);
                GuiCloseItem guiCloseItem = new GuiCloseItem(49);

                ItemStack crafting = new ItemBuilder(Material.CRAFTING_TABLE).displayname(ChatColor.GOLD + "Crafting Table").build();


                ItemStack crafting = new ItemBuilder(Material.CARTOGRAPHY_TABLE).displayname(ChatColor.GOLD + "Cartography Table").setMovable(false).setClickSound(true), 20);
                ItemStack crafting = new ItemBuilder(Material.STONECUTTER).displayname(ChatColor.GOLD + "Stonecutter").setMovable(false).setClickSound(true), 21);
                ItemStack crafting = new ItemBuilder(Material.ANVIL).displayname(ChatColor.GOLD + "Anvil").setMovable(false).setClickSound(true), 22);
                ItemStack crafting = new ItemBuilder(Material.GRINDSTONE).displayname(ChatColor.GOLD + "Grindstone").setMovable(false).setClickSound(true), 23);
                ItemStack crafting = new ItemBuilder(Material.LOOM).displayname(ChatColor.GOLD + "Loom").setMovable(false).setClickSound(true), 24);
                ItemStack crafting = new ItemBuilder(Material.SMITHING_TABLE).displayname(ChatColor.GOLD + "Smithing Table").setMovable(false).setClickSound(true), 31);

                guiCreator.setItemInSlot(crafting, 13);
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