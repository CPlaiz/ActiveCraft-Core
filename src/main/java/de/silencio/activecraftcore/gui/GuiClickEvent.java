package de.silencio.activecraftcore.gui;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;

public class GuiClickEvent extends Event {

    private Type type;
    private Inventory inventory;
    private int position;
    private boolean cancelled;

    private static final HandlerList handlers = new HandlerList();

    public GuiClickEvent(Type type, Inventory inventory, int position) {
        this.type = type;
        this.inventory = inventory;
        this.position = position;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = true;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    enum Type {
        OPEN_INVENTORY;
        Type() {
        }
    }
}
