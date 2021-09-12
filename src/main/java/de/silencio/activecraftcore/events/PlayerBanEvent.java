package de.silencio.activecraftcore.events;

import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Date;

public class PlayerBanEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private String target;
    private String reason;
    private Date expirationDate;
    private boolean cancelled;

    public PlayerBanEvent(Player target, String reason, Date expirationDate, String source) {
        this.target = target.getName();
        this.reason = reason;
        this.expirationDate = expirationDate;
    }

    public PlayerBanEvent(String target, String reason, Date expirationDate, String source) {
        this.target = target;
        this.reason = reason;
        this.expirationDate = expirationDate;
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
