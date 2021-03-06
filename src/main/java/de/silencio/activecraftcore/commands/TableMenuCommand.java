package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.guicreator.GuiCloseItem;
import de.silencio.activecraftcore.guicreator.GuiCreator;
import de.silencio.activecraftcore.guicreator.GuiItem;
import de.silencio.activecraftcore.messages.TableMenuMessages;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TableMenuCommand extends ActiveCraftCommand {

    public TableMenuCommand() {
        super("tablemenu");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "tablemenu");
        Player player = getPlayer(sender);
        GuiCreator guiCreator = new GuiCreator("table_menu", 6, TableMenuMessages.TABLEMENU_TITLE()) {
            @Override
            public void refresh() {
                GuiCloseItem guiCloseItem = new GuiCloseItem(49);
                setItemInSlot(new GuiItem(Material.CRAFTING_TABLE).setDisplayName(TableMenuMessages.TABLEMENU_CRAFTING_TABLE()).setMovable(false).setClickSound(true), 13);
                setItemInSlot(new GuiItem(Material.CARTOGRAPHY_TABLE).setDisplayName(TableMenuMessages.TABLEMENU_CARTOGRAPHY_TABLE()).setMovable(false).setClickSound(true), 20);
                setItemInSlot(new GuiItem(Material.STONECUTTER).setDisplayName(TableMenuMessages.TABLEMENU_STONECUTTER()).setMovable(false).setClickSound(true), 21);
                setItemInSlot(new GuiItem(Material.ANVIL).setDisplayName(TableMenuMessages.TABLEMENU_ANVIL()).setMovable(false).setClickSound(true), 22);
                setItemInSlot(new GuiItem(Material.GRINDSTONE).setDisplayName(TableMenuMessages.TABLEMENU_GRINDSTONE()).setMovable(false).setClickSound(true), 23);
                setItemInSlot(new GuiItem(Material.LOOM).setDisplayName(TableMenuMessages.TABLEMENU_LOOM()).setMovable(false).setClickSound(true), 24);
                setItemInSlot(new GuiItem(Material.SMITHING_TABLE).setDisplayName(TableMenuMessages.TABLEMENU_SMITHING_TABLE()).setMovable(false).setClickSound(true), 31);
                guiCloseItem.setClickSound(true);
                guiCloseItem.setMovable(false);
                fillBackground(true);
                setCloseItem(guiCloseItem);
            }
        };
        player.openInventory(guiCreator.build().getInventory());
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}