package de.silencio.activecraftcore.events;

import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Date;

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
