package de.silencio.activecraftcore.manager;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.events.DialogueAnswerEvent;
import de.silencio.activecraftcore.events.DialogueCancelEvent;
import de.silencio.activecraftcore.events.DialogueCompleteEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class DialogueManager implements Listener {

    private List<String> dialogueSteps = new ArrayList<>();
    private String[] answers;
    private CommandSender sender;
    private int activeStep;
    private int steps;
    private boolean dialogueActive;
    private String header;
    private String completedMessage;
    private String cancelledMessage;
    private boolean answerPassing;
    private String tempAnswer;

    public DialogueManager(CommandSender sender) {
        this.sender = sender;
        activeStep = 0;
        dialogueActive = false;
        ActiveCraftCore.getDialogueManagerList().put(sender, this);
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
                Bukkit.getScheduler().runTask(ActiveCraftCore.getPlugin(), () -> {
                    DialogueCancelEvent event = new DialogueCancelEvent(this);
                    Bukkit.getPluginManager().callEvent(event);
                });
                sender.sendMessage(cancelledMessage);
                exit();
                return;
            }
            this.tempAnswer = answer;

            //send to listener
            Bukkit.getScheduler().runTask(ActiveCraftCore.getPlugin(), () -> {
                    DialogueAnswerEvent event = new DialogueAnswerEvent(this);
                    Bukkit.getPluginManager().callEvent(event);
            });


            if (answerPassing) {
                sender.sendMessage(ChatColor.GREEN + "> " + answer);
                this.answers[activeStep] = answer;
                this.activeStep += 1;

            } else {
                sender.sendMessage(ChatColor.RED + "Invalid Answer!");
            }
            next();
        }
    }

    public void next() {
        if (activeStep >= steps) {
            sender.sendMessage(completedMessage);
            exit();
            //send to listener
            Bukkit.getScheduler().runTask(ActiveCraftCore.getPlugin(), () -> {
                DialogueCompleteEvent event = new DialogueCompleteEvent(this);
                Bukkit.getPluginManager().callEvent(event);
            });
            ActiveCraftCore.getDialogueManagerList().remove(sender);
            return;
        }

        sender.sendMessage(dialogueSteps.get(activeStep));
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
            sender.sendMessage(cancelledMessage);
        }

        this.answers = new String[steps];
        sender.sendMessage(header);
        dialogueActive = true;
        next();
    }

    public boolean isDialogueActive() {
        return dialogueActive;
    }

    public void exit() {
        dialogueActive = false;
        ActiveCraftCore.getDialogueManagerList().remove(sender);
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
