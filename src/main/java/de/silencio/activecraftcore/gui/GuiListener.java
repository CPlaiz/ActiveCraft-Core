package de.silencio.activecraftcore.gui;

import de.silencio.activecraftcore.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class GuiListener implements Listener {

    @EventHandler
    public void onItemClick(InventoryClickEvent event) {

        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
        ItemStack itemStack = event.getCurrentItem();
        if (!Main.getPlugin().getGuiData().getGuiList().containsKey(event.getClickedInventory())) return;
        GuiItem guiItem = Main.getPlugin().getGuiData().getFromCorrespondingGuiItem(itemStack);

        //call GuiClickEvent
        GuiClickEvent guiClickEvent = new GuiClickEvent(guiItem, event);
        Bukkit.getPluginManager().callEvent(guiClickEvent);
        if (guiClickEvent.isCancelled()) event.setCancelled(true);
    }

    @EventHandler
    public void onGuiClick(GuiClickEvent event) {
        GuiItem item = event.getCurrentItem();
        InventoryView view = event.getView();
        Player player = (Player) view.getPlayer();
        if (event.getCurrentItem().hasClickSound()) {
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1f, 1f);
        }
        if (item instanceof GuiCloseItem) {
            view.close();
        } else if (item instanceof GuiBackItem) {
            if (Main.getPlugin().getGuiHistoryMap().getGuiStack((Player) view.getPlayer()) != null) {
                if (Main.getPlugin().getGuiHistoryMap().getGuiStack((Player) view.getPlayer()).size() >= 1) {
                    view.getPlayer().openInventory(Main.getPlugin().getGuiHistoryMap().getGuiStack((Player) view.getPlayer()).pop());
                }
            }
        }
        event.setCancelled(!item.isMovable());
    }
}
