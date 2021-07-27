package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RamCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {

            if(sender.hasPermission("activecraft.ram")) {

                Runtime runtime = Runtime.getRuntime();

                int durch = 1024*1024;

                sender.sendMessage(runtime.freeMemory()/durch + " MB free");
                sender.sendMessage(runtime.totalMemory()/durch - runtime.freeMemory()/durch + " MB used");
                sender.sendMessage(runtime.totalMemory()/durch + " MB total");
                sender.sendMessage(runtime.maxMemory()/durch + " MB max");

            } else sender.sendMessage(Errors.NO_PERMISSION);

        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
