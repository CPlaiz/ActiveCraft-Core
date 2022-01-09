package de.silencio.activecraftcore.events;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class StaffChatMessageEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private CommandSender sender;
    private Player target;
    private String message;
    private boolean cancelled;

    public StaffChatMessageEvent(CommandSender sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public CommandSender getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
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

    public void setMessage(String message) {
        this.message = message;
    }
}
