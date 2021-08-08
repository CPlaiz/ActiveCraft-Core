package de.silencio.activecraftcore.utils;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.commands.DialogueList;
import de.silencio.activecraftcore.ownlisteners.DialogueListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DialogueManager implements DialogueList, Listener {

    private List<String> dialogueSteps = new ArrayList<>();
    private String[] answers;
    private Player player;
    private int activeStep;
    private int steps;
    private boolean dialogueActive;



    public DialogueManager(Player player) {

        dialogueList.add(player);
        this.player = player;
        activeStep = 0;
        dialogueActive = false;
    }

    public void add(String message) {
        if (!dialogueActive) {
            this.dialogueSteps.add(message);
            this.steps += 1;
        }
    }

    public void answer(String answer) {
        System.out.println("active step " + activeStep);
        player.sendMessage(ChatColor.GREEN + answer);
        System.out.println(Arrays.toString(answers));
        this.answers[activeStep] = answer;

        next();
    }

    public void next() {
        if (activeStep >= dialogueSteps.toArray().length) {
            this.dialogueList.remove(player);
            return;
        }
        player.sendMessage(dialogueSteps.get(activeStep));
        this.activeStep += 1;
    }

    public String getAnswer(int position) {
        return answers[position];
    }

    public int getActiveStep() {
        return activeStep;
    }

    public void initialize() {
        System.out.println(steps);
        this.answers = new String[steps];
        //System.out.println(answers[steps - 1]);
        dialogueActive = true;
    }



}
