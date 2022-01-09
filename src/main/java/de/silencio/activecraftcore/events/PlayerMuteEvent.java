package de.silencio.activecraftcore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerMuteEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    
    private Player target;
    private boolean cancelled;

    public PlayerMuteEvent(Player target) {
        this.target = target;
    }

    public Player getTarget() {
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
