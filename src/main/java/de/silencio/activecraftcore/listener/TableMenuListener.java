package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.guicreator.GuiClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TableMenuListener implements Listener {

    @EventHandler
    public void onTableMenuClick(GuiClickEvent event) {
        Player player = (Player) event.getView().getPlayer();

        if (!event.getGui().getAssociatedGuiCreator().getInternalName().equals("table_menu")) return;

        switch (event.getSlot()) {
            case 13 -> player.performCommand("craftingtable");
            case 20 -> player.performCommand("cartographytable");
            case 21 -> player.performCommand("stonecutter");
            case 22 -> player.performCommand("anvil");
            case 23 -> player.performCommand("grindstone");
            case 24 -> player.performCommand("loom");
            case 31 -> player.performCommand("smithingtable");
        }
    }
}