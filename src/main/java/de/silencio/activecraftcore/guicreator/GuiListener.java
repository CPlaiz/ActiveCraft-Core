package de.silencio.activecraftcore.guicreator;

import de.silencio.activecraftcore.ActiveCraftCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class GuiListener implements Listener {

    @EventHandler
    public void onInvClose(InventoryCloseEvent event) {
        GuiCreator guiCreator = null;
        for (GuiCreator guiCreatorFromList : ActiveCraftCore.getGuiDataMap().keySet()) {
            if (guiCreatorFromList.getInventory() == event.getInventory()) {
                guiCreator = guiCreatorFromList;
            }
        }
        if (guiCreator == null) return;

        if (guiCreator.getInternalName().startsWith("confirmation_")) {
            GuiCancelEvent guiConfirmEvent = new GuiCancelEvent(event.getInventory(), guiCreator.getInternalName().replace("confirmation_", ""),
                    (Player) event.getPlayer());
            Bukkit.getPluginManager().callEvent(guiConfirmEvent);
        }
    }

    @EventHandler
    public void onItemClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
        ItemStack itemStack = event.getCurrentItem();
        GuiItem guiItem = null;
        GuiCreator guiCreator = null;
        for (GuiCreator guiCreatorFromList : ActiveCraftCore.getGuiDataMap().keySet()) {
            if (guiCreatorFromList.getInventory() == event.getClickedInventory()) {
                guiCreator = guiCreatorFromList;
                GuiData guiData = ActiveCraftCore.getFromGuiDataMap(guiCreatorFromList);
                guiItem = guiData.getFromCorrespondingGuiItem(itemStack);
            }
        }

        if (guiItem == null) return;

        //call GuiClickEvent
        GuiClickEvent guiClickEvent = new GuiClickEvent(guiItem, event);
        Bukkit.getPluginManager().callEvent(guiClickEvent);
        if (guiClickEvent.isCancelled()) event.setCancelled(true);

        //call GuiConfirmEvent
        if (guiCreator.getInternalName().startsWith("confirmation_")) {
            if (guiItem.getType() == Material.LIME_CONCRETE) {
                GuiConfirmEvent guiConfirmEvent = new GuiConfirmEvent(event.getClickedInventory(), guiCreator.getInternalName().replace("confirmation_", ""), (Player) event.getView().getPlayer());
                Bukkit.getPluginManager().callEvent(guiConfirmEvent);
                if (guiConfirmEvent.isCancelled()) event.setCancelled(true);

                if (GuiNavigator.getGuiStack((Player) event.getView().getPlayer()) != null) {
                    if (GuiNavigator.getGuiStack((Player) event.getView().getPlayer()).size() >= 1) {
                        event.getView().getPlayer().openInventory(GuiNavigator.pop((Player) event.getView().getPlayer()));
                    }
                }
            } else if (guiItem.getType() == Material.RED_CONCRETE  ) {
                GuiCancelEvent guiConfirmEvent = new GuiCancelEvent(event.getClickedInventory(), guiCreator.getInternalName().replace("confirmation_", ""),
                        (Player) event.getView().getPlayer());
                Bukkit.getPluginManager().callEvent(guiConfirmEvent);
                if (guiConfirmEvent.isCancelled()) event.setCancelled(true);

                if (GuiNavigator.getGuiStack((Player) event.getView().getPlayer()) != null) {
                    if (GuiNavigator.getGuiStack((Player) event.getView().getPlayer()).size() >= 1) {
                        event.getView().getPlayer().openInventory(GuiNavigator.pop((Player) event.getView().getPlayer()));
                    }
                }
            }
        }
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
            if (GuiNavigator.getGuiStack((Player) view.getPlayer()) != null && GuiNavigator.getGuiStack((Player) view.getPlayer()).size() >= 1) {
                view.getPlayer().openInventory(GuiNavigator.pop((Player) view.getPlayer()));
            }
        }
        event.setCancelled(!item.isMovable());
    }
}
