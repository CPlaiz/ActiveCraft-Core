package de.silencio.activecraftcore.utils;

import de.silencio.activecraftcore.ownlisteners.DialogueListener;

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
