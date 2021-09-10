package de.silencio.activecraftcore.events;

import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MsgEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private CommandSender sender;
    private Player target;
    private String message;
    private boolean cancelled;

    public MsgEvent(CommandSender sender, Player target, String message) {
        this.sender = sender;
        this.target = target;
        this.message = message;
    }

    public CommandSender getSender() {
        return sender;
    }

    public Player getTarget() {
        return target;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
