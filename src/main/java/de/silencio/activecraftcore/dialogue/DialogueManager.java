package de.silencio.activecraftcore.dialogue;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.events.DialogueAnswerEvent;
import de.silencio.activecraftcore.events.DialogueCancelEvent;
import de.silencio.activecraftcore.events.DialogueCompleteEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class DialogueManager implements Listener {

    private List<String> dialogueSteps = new ArrayList<>();
    private String[] answers;
    private Player player;
    private int activeStep;
    private int steps;
    private boolean dialogueActive;
    private DialogueManagerList dialogueManagerList;
    private String header;
    private String completedMessage;
    private String cancelledMessage;
    private boolean answerPassing;
    private String tempAnswer;

    public DialogueManager(Player player) {
        if (ActiveCraftCore.getPlugin().getDialogueList().contains(player)) {
            ActiveCraftCore.getPlugin().addToDialogueList(player);
        }
        dialogueManagerList = ActiveCraftCore.getPlugin().getDialogueManagerList();
        ActiveCraftCore.getPlugin().addToDialogueList(player);
        this.player = player;
        activeStep = 0;
        dialogueActive = false;
        dialogueManagerList.addDialogueManager(player, this);
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

                //send to listener
                DialogueManager dialogueManager = this;
                Bukkit.getScheduler().runTask(ActiveCraftCore.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        DialogueCancelEvent event = new DialogueCancelEvent(dialogueManager);
                        Bukkit.getPluginManager().callEvent(event);
                        if (event.isCancelled()) return;
                    }
                });


                player.sendMessage(cancelledMessage);
                exit();
                return;
            }
            this.tempAnswer = answer;

            //send to listener
            DialogueManager dialogueManager = this;
            Bukkit.getScheduler().runTask(ActiveCraftCore.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    DialogueAnswerEvent event = new DialogueAnswerEvent(dialogueManager);
                    Bukkit.getPluginManager().callEvent(event);
                    if (event.isCancelled()) return;
                }
            });


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
            DialogueManager dialogueManager = this;
            Bukkit.getScheduler().runTask(ActiveCraftCore.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    DialogueCompleteEvent event = new DialogueCompleteEvent(dialogueManager);
                    Bukkit.getPluginManager().callEvent(event);
                    if (event.isCancelled()) return;
                }
            });

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
        ActiveCraftCore.getPlugin().removeFromDialogueList(player);
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
