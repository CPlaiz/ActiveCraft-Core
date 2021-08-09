package de.silencio.activecraftcore.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class DialogueManagerList {

    public HashMap<Player, DialogueManager> dialogueManagers = new HashMap<>();

    public DialogueManager getDialogueManager(Player player) {
        return dialogueManagers.get(player);
    }

    public void addDialogueManager(Player player, DialogueManager dialogueManager) {
        dialogueManagers.put(player, dialogueManager);
    }

    public HashMap<Player, DialogueManager> getDialogueManagers() {
        return dialogueManagers;
    }

}