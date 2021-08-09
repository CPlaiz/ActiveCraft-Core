package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.ownlisteners.StaffChatListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class StaffChatCommand implements CommandExecutor {

    String message = "";
    private List<StaffChatListener> staffChatListeners = new ArrayList<StaffChatListener>();

    public void addListener(StaffChatListener toAdd) {
        staffChatListeners.add(toAdd);
    }

    private void triggerListener() {
        for (StaffChatListener scl : staffChatListeners) {
            scl.onStaffChatMessage();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            for (int i = 0; i < args.length; i++) {
                message = message + args[i] + " ";
            }
                Bukkit.broadcast(ChatColor.GOLD + "[StaffChat] " + ChatColor.AQUA + sender.getName() + ChatColor.RESET + ": " + message, "activecraft.staffchat");
                triggerListener();
            message = "";
        return true;
    }
}