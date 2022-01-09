package de.silencio.activecraftcore.events;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WarpDeleteEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Location location;
    private String warpName;
    private boolean cancelled;

    public WarpDeleteEvent(Location location, String warpName) {
        this.location = location;
        this.warpName = warpName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getWarpName() {
        return warpName;
    }

    public void setWarpName(String warpName) {
        this.warpName = warpName;
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
