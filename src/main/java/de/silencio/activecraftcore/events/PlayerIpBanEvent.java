package de.silencio.activecraftcore.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Date;

public class PlayerIpBanEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private String target;
    private String reason;
    private Date expirationDate;
    private boolean cancelled;

    public PlayerIpBanEvent(String target, String reason, Date expirationDate, String source) {
        this.target = target;
        this.reason = reason;
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
