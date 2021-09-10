package de.silencio.activecraftcore.events;

import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerAfkEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private boolean isAfk;
    private boolean cancelled;

    public PlayerAfkEvent(Player player, boolean isAfk) {
        this.player = player;
        this.isAfk = isAfk;
    }


    public boolean isCancelled() {
        return cancelled;
    }

    public boolean isAfk() {
        return isAfk;
    }

    public void setAfk(boolean afk) {
        isAfk = afk;
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

    public Player getPlayer() {
        return player;
    }
}
