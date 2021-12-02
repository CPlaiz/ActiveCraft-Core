package de.silencio.activecraftcore.gui;

import de.silencio.activecraftcore.ActiveCraftCore;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import java.util.List;

public class GuiClickEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private boolean cancelled;
    private GuiItem currentItem;
    private ClickType click;
    private int slot;
    private Inventory clickedInventory;
    private InventoryAction action;
    private GuiItem cursor;
    private int hotbarButton;
    private int rawSlot;
    private InventoryType.SlotType slotType;
    private List<HumanEntity> viewers;
    private InventoryView view;
    private InventoryClickEvent invClickEvent;

    public GuiClickEvent(GuiItem guiItem, InventoryClickEvent event) {
        this.invClickEvent = event;
        this.currentItem = guiItem;
        this.click = event.getClick();
        this.slot = event.getSlot();
        this.clickedInventory = event.getClickedInventory();
        this.action = event.getAction();
        this.rawSlot = event.getRawSlot();
        this.slotType = event.getSlotType();
        this.viewers = event.getViewers();
        this.view = event.getView();
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
        invClickEvent.setCancelled(cancel);
    }

    public Gui getGui() {
        for (int id : ActiveCraftCore.getPlugin().getGuiList().keySet()) {
            Gui gui = ActiveCraftCore.getPlugin().getGuiById(id);
            if (clickedInventory == gui.getInventory()) {
                return gui;
            }
        }
        return null;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public GuiItem getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(GuiItem currentItem) {
        this.currentItem = currentItem;
    }

    public ClickType getClick() {
        return click;
    }

    public int getSlot() {
        return slot;
    }

    public Inventory getClickedInventory() {
        return clickedInventory;
    }

    public InventoryAction getAction() {
        return action;
    }

    public GuiItem getCursor() {
        return cursor;
    }

    public void setCursor(GuiItem cursor) {
        this.cursor = cursor;
    }

    public int getHotbarButton() {
        return hotbarButton;
    }

    public int getRawSlot() {
        return rawSlot;
    }

    public InventoryType.SlotType getSlotType() {
        return slotType;
    }

    public List<HumanEntity> getViewers() {
        return viewers;
    }

    public InventoryView getView() {
        return view;
    }
}
