package de.silencio.activecraftcore.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerUnbanEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private String target;
    private boolean cancelled;

    public PlayerUnbanEvent(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
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
