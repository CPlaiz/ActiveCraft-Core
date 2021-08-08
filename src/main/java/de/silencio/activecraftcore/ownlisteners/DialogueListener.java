package de.silencio.activecraftcore.ownlisteners;

import de.silencio.activecraftcore.utils.DialogueManager;

public interface DialogueListener {

    void onDialogueNext(DialogueManager dialogueManager);

    void onDialogueCancel(DialogueManager dialogueManager);

    void onDialogueComplete(DialogueManager dialogueManager);

}
