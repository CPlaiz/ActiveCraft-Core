package de.silencio.activecraftcore.listener.inventory;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.Objects;

public class ProfileListener implements Listener {

    private Inventory profileInventory;

    public ProfileListener() {
    }

    @EventHandler
    private void onItemClick(InventoryClickEvent event) {
        if (Objects.equals(event.getClickedInventory(), this.profileInventory)) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) return;
            if (event.getCurrentItem().getType() == Material.GRASS_BLOCK) {

            }
        }
    }
}
