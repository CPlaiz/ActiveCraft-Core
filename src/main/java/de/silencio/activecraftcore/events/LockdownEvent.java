package de.silencio.activecraftcore.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class LockdownEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private boolean lockedDown;
    private boolean cancelled;

    public LockdownEvent(boolean lockedDown) {
        this.lockedDown = lockedDown;
    }

    public boolean isLockedDown() {
        return lockedDown;
    }

    public void setLockedDown(boolean lockedDown) {
        this.lockedDown = lockedDown;
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

}
