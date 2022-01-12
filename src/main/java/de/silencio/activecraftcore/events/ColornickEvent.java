package de.silencio.activecraftcore.events;

import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ColornickEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Profile profile;
    private ChatColor newColor;
    private ChatColor oldColor;
    private boolean cancelled;

    public ColornickEvent(Profile profile, ChatColor newColor) {
        this.profile = profile;
        this.newColor = newColor;
        this.oldColor = profile.getColorNick();
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

    public Profile getProfile() {
        return profile;
    }

    public ChatColor getNewColor() {
        return newColor;
    }

    public ChatColor getOldColor() {
        return oldColor;
    }
}
