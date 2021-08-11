package de.silencio.activecraftcore.messages.Dialogue;

import de.silencio.activecraftcore.messages.Dialogue.DialogueManager;

public interface DialogueListener {

    void onDialogueAnswer(DialogueManager dialogueManager);

    void onDialogueCancel(DialogueManager dialogueManager);

    void onDialogueComplete(DialogueManager dialogueManager);

}
