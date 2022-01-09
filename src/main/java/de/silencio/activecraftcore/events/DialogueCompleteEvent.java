package de.silencio.activecraftcore.events;

import de.silencio.activecraftcore.manager.DialogueManager;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DialogueCompleteEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private DialogueManager dialogueManager;

    public DialogueCompleteEvent(DialogueManager dialogueManager) {
        this.dialogueManager = dialogueManager;
    }

    public void setDialogueManager(DialogueManager dialogueManager) {
        this.dialogueManager = dialogueManager;
    }

    public DialogueManager getDialogueManager() {
        return dialogueManager;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
