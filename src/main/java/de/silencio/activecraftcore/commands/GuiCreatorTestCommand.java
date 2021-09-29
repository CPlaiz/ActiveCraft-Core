package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.gui.*;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GuiCreatorTestCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        GuiCreator guiCreator = new GuiCreator(5);
        guiCreator.setItemInSlot(new GuiItem(Material.NETHERITE_AXE).setDisplayName("pepega").setLore("1. pepega", "2. pepega", "HUUUGE pepega"), 3);
        guiCreator.setBackItem(new GuiBackItem(guiCreator.getRows()*9 - 6));
        guiCreator.setPlayerHead(new GuiPlayerHead(player, 4));
        GuiCloseItem guiCloseItem = new GuiCloseItem(5);
        guiCloseItem.setClickSound(true);
        guiCloseItem.setMovable(false);
        guiCreator.setCloseItem(guiCloseItem);

        //liste in main (id, name)

        player.openInventory(guiCreator.build().getInventory());

        return false;
    }
}
