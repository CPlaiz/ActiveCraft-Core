package de.silencio.activecraftcore.events;

import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Date;

public class PlayerIpBanEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private CommandSender sender;
    private String target;
    private String reason;
    private Date expirationDate;
    private boolean cancelled;


    public PlayerIpBanEvent(CommandSender sender, String target, String reason, Date expirationDate) {
        this.sender = sender;
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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public CommandSender getSender() {
        return sender;
    }

    public String getTarget() {
        return target;
    }

    public String getMessage() {
        return reason;
    }

    public void setMessage(String message) {
        this.reason = message;
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
