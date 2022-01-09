package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

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
}