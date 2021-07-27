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

public class Navigator implements Listener {

    private final Inventory navigatorInventory;

    public Navigator() {
        this.navigatorInventory = Bukkit.createInventory(null,54, "§6Navigator");

        this.navigatorInventory.setItem(4, new ItemBuilder(Material.GRASS_BLOCK)
                .displayname("§6Survival")
                .lore("§7Die Surivival Welt")
                .build());
    }

    @EventHandler
    private void onInteract(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (event.getItem().getType() == Material.COMPASS) {
            event.getPlayer().openInventory(this.navigatorInventory);
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onItemClick(InventoryClickEvent event) {
        if (Objects.equals(event.getClickedInventory(), this.navigatorInventory)) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) return;
            if (event.getCurrentItem().getType() == Material.GRASS_BLOCK) {
                event.getWhoClicked().sendMessage(Main.PREFIX + "Warp Survival");
            }
        }
    }
}
