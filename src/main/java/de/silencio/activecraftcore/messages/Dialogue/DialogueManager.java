package de.silencio.activecraftcore.messages.Dialogue;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.ownlisteners.DialogueListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class DialogueManager implements DialogueList, Listener {

    private List<String> dialogueSteps = new ArrayList<>();
    private String[] answers;
    private Player player;
    private int activeStep;
    private int steps;
    private boolean dialogueActive;
    private DialogueManagerList dialogueManagerList;
    private DialogueListenerList dialogueListenerList;
    private String header;
    private String completedMessage;
    private String cancelledMessage;
    private boolean answerPassing;
    private String tempAnswer;

    public DialogueManager(Player player) {
        if (dialogueList.contains(player)) {
            dialogueList.remove(player);
        }
        dialogueManagerList = Main.getPlugin().getDialogueManagerList();
        dialogueList.add(player);
        this.player = player;
        activeStep = 0;
        dialogueActive = false;
        dialogueManagerList.addDialogueManager(player, this);
        dialogueListenerList = Main.getPlugin().getDialogueListenerList();
        header = ChatColor.GOLD + "-- Dialogue --";
        completedMessage = ChatColor.GOLD + "Exiting dialogue...";
        cancelledMessage = ChatColor.GOLD + "Cancelling dialogue...";

    }

    public void add(String message) {
        if (!dialogueActive) {
            this.dialogueSteps.add(message);
            this.steps += 1;
        }
    }

    public void answer(String answer) {
        if (dialogueActive) {
            if (answer.equals("exit") || answer.equals("cancel")) {
                player.sendMessage(cancelledMessage);
                exit();
                //send to listener
                for (DialogueListener dialogueListener : dialogueListenerList.getDialogueListenerList()) {
                    dialogueListener.onDialogueCancel(this);
                }
                return;
            }
            this.tempAnswer = answer;
            for (DialogueListener dialogueListener : dialogueListenerList.getDialogueListenerList()) {
                dialogueListener.onDialogueAnswer(this);
            }
            if (answerPassing) {
                player.sendMessage(ChatColor.GREEN + "> " + answer);
                this.answers[activeStep] = answer;
                this.activeStep += 1;
            } else {
                player.sendMessage(ChatColor.RED + "Invalid Answer!");
            }
            next();
        }
    }

    public void next() {
        if (activeStep >= steps) {
            player.sendMessage(completedMessage);
            exit();
            //send to listener
            for (DialogueListener dialogueListener : dialogueListenerList.getDialogueListenerList()) {
                dialogueListener.onDialogueComplete(this);
            }
            return;
        }

        player.sendMessage(dialogueSteps.get(activeStep));
        this.answerPassing = true;

    }

    public String getAnswer(int position) {
        return answers[position];
    }

    public int getActiveStep() {
        return activeStep;
    }

    public void initialize() {

        if (dialogueActive) {
            exit();
            player.sendMessage(cancelledMessage);
        }

        this.answers = new String[steps];
        player.sendMessage(header);
        dialogueActive = true;
        next();
    }

    public boolean isDialogueActive() {
        return dialogueActive;
    }

    public void exit() {
        dialogueActive = false;
        this.dialogueList.remove(player);
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setCancelledMessage(String cancelledMessage) {
        this.cancelledMessage = cancelledMessage;
    }

    public void setCompletedMessage(String completedMessage) {
        this.completedMessage = completedMessage;
    }

    public void goBack() {
        this.answerPassing = false;
    }

    public String getTempAnswer() {
        return tempAnswer;
    }
}
