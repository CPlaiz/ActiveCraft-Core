package de.silencio.activecraftcore.events;

import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerWarnEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private CommandSender sender;
    private Player target;
    private String reason;
    private boolean cancelled;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public PlayerWarnEvent(CommandSender sender, Player target, String reason) {
        this.sender = sender;
        this.target = target;
        this.reason = reason;
    }

    public CommandSender getSender() {
        return sender;
    }

    public Player getTarget() {
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
