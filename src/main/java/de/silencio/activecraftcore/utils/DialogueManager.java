package de.silencio.activecraftcore.utils;

import de.silencio.activecraftcore.commands.DialogueList;
import de.silencio.activecraftcore.ownlisteners.DialogueListener;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class DialogueManager implements DialogueList, Listener {

    private List<String> dialogueSteps = new ArrayList<>();
    private String[] answers;
    private Player player;
    private int activeStep;

    private List<DialogueListener> dialogueListenerList = new ArrayList<DialogueListener>();

    public DialogueManager(Player player) {

        this.dialogueList.add(player);
        this.player = player;
    }

    public void add(String message) {
        this.dialogueSteps.add(message);
    }

    public void answer(String answer) {
        this.answers[activeStep] = answer;
        next();
    }

    public void next() {
        if (activeStep >= dialogueSteps.toArray().length) {
            this.dialogueList.remove(player);
            return;
        }
        player.sendMessage(dialogueSteps.get(activeStep));
        this.activeStep++;
    }

    public String getAnswer(int position) {
        return answers[position];
    }

    public int getActiveStep() {
        return activeStep;
    }

}
