package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.gui.GuiClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TableMenuListener implements Listener {

    @EventHandler
    public void onTableMenuClick(GuiClickEvent event) {
        Player player = (Player) event.getView().getPlayer();
        switch (event.getSlot()) {
            case 13:
                player.performCommand("craftingtable");
                break;
            case 20:
                player.performCommand("cartographytable");
                break;
            case 21:
                player.performCommand("stonecutter");
                break;
            case 22:
                player.performCommand("anvil");
                break;
            case 23:
                player.performCommand("grindstone");
                break;
            case 24:
                player.performCommand("loom");
                break;
            case 31:
                player.performCommand("smithingtable");
                break;
        }
    }
}