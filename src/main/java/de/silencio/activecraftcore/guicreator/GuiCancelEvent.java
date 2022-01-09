package de.silencio.activecraftcore.guicreator;

import de.silencio.activecraftcore.ActiveCraftCore;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class GuiCancelEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private boolean cancelled;
    private Player player;
    private Gui gui;
    private String identifier;
    private Inventory clickedInventory;
    private InventoryClickEvent invClickEvent;

    public GuiCancelEvent(Inventory inventory, String identifier, Player player) {
        this.identifier = identifier;
        this.clickedInventory = inventory;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
        invClickEvent.setCancelled(cancel);
    }

    public Gui getGui() {
        for (int id : ActiveCraftCore.getGuiList().keySet()) {
            Gui gui = ActiveCraftCore.getGuiById(id);
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

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Inventory getClickedInventory() {
        return clickedInventory;
    }

    public void setClickedInventory(Inventory clickedInventory) {
        this.clickedInventory = clickedInventory;
    }

    public InventoryClickEvent getInvClickEvent() {
        return invClickEvent;
    }

    public void setInvClickEvent(InventoryClickEvent invClickEvent) {
        this.invClickEvent = invClickEvent;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
