package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.ownlisteners.DialogueListener;
import de.silencio.activecraftcore.utils.BanManager;
import de.silencio.activecraftcore.utils.DialogueManager;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.awt.*;
import java.util.*;
import java.util.List;

public class BanCommand implements CommandExecutor, DialogueList, Listener, DialogueListener {

    private Map<Player, List<String>> banDialogueList = new HashMap<>();
    private List<DialogueListener> dialogueListenerList = new ArrayList<DialogueListener>();

    private DialogueManager dialogueManager;
    private BanManager banManager;
    private Player target;
    private CommandSender commandSender;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("activecraft.ban")) {
            if (label.equalsIgnoreCase("ban")) {
                banManager = new BanManager(BanList.Type.NAME);

                if (!banManager.isBanned(args[0])) {

                    this.commandSender = sender;

                    this.target = Bukkit.getPlayer(args[0]);

                    dialogueList.add(target);

                    this.dialogueManager = new DialogueManager((Player) sender);
                    sender.sendMessage(ChatColor.GOLD + "-- Ban Dialogue --");
                    this.dialogueManager.add(ChatColor.GOLD + "Please enter a reason: ");
                    this.dialogueManager.add(ChatColor.GOLD + "Please enter the ban duration: ");
                    this.dialogueManager.initialize();

                    Date date = new Date();
                } else sender.sendMessage(Errors.WARNING + "This player is already banned.");
            } else if (label.equalsIgnoreCase("unban")) {
                banManager.unban(target);
            } else if (label.equalsIgnoreCase("banlist")) {

            }
        } else sender.sendMessage(Errors.NO_PERMISSION);
        return true;
    }

    @Override
    public void onDialogueNext() {
        if (dialogueManager.getActiveStep() == 1) {
            banManager.ban(target, dialogueManager.getAnswer(0), null, commandSender.getName());

        }
    }
}

// 1s = 1 second
// 1m = 1 minute
// 1h = 1 hour
// 1d = 1 day

// 1d5h = 1 day + 5 hours