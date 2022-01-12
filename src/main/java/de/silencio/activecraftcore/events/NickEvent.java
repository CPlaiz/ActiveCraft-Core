package de.silencio.activecraftcore.events;

import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class NickEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Profile profile;
    private String newName;
    private String oldName;
    private boolean cancelled;

    public NickEvent(Profile profile, String newName) {
        this.profile = profile;
        this.newName = newName;
        this.oldName = profile.getNickname();
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

    public String getNewName() {
        return newName;
    }

    public String getOldName() {
        return oldName;
    }
}
