package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class RamCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if(sender.hasPermission("activecraft.ram")) {

                Runtime runtime = Runtime.getRuntime();

                int durch = 1024*1024;
                sender.sendMessage(CommandMessages.RAM(runtime.freeMemory()/durch + "", runtime.totalMemory()/durch - runtime.freeMemory()/durch + "", runtime.maxMemory()/durch + ""));
            } else sender.sendMessage(Errors.NO_PERMISSION());
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        ArrayList<String> completerList = new ArrayList<>();
        return completerList;
    }

}