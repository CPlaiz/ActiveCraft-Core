package de.silencio.activecraftcore.events;

import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerUnvanishEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private boolean cancelled;

    public PlayerUnvanishEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
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
