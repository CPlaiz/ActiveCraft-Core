package de.silencio.activecraftcore.dialogue;

import de.silencio.activecraftcore.listener.DialogueListener;

import java.util.ArrayList;
import java.util.List;

public class DialogueListenerList {

    private List<DialogueListener> dialogueListenerList = new ArrayList<DialogueListener>();

    public void addListener(DialogueListener dialogueListener) {
        dialogueListenerList.add(dialogueListener);
    }

    public List<DialogueListener> getDialogueListenerList() {
        return dialogueListenerList;
    }

}
