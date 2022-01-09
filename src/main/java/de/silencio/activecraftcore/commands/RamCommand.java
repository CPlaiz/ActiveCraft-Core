package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class RamCommand extends ActiveCraftCommand {

    public RamCommand() {
        super("ram");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "ram");
        Runtime runtime = Runtime.getRuntime();
        int durch = 1024*1024;
        sendMessage(sender, CommandMessages.RAM(runtime.freeMemory()/durch + "", runtime.totalMemory()/durch - runtime.freeMemory()/durch + "", runtime.maxMemory()/durch + ""));
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}