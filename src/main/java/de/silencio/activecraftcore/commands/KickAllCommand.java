package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class KickAllCommand extends ActiveCraftCommand {

    public KickAllCommand() {
        super("kickall");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        
        checkPermission(sender, "kickall");
        if (args.length == 0) {
            sendMessage(sender, CommandMessages.DEFAULT_KICKALL());
            for (Player player : Bukkit.getOnlinePlayers()) 
                if (!player.hasPermission("activecraft.kickall.bypass")) 
                    player.kickPlayer(CommandMessages.DEFAULT_KICKALL_MESSAGE());
        } else {
            sendMessage(sender, CommandMessages.CUSTOM_KICKALL(combineArray(args)));
            for (Player player : Bukkit.getOnlinePlayers()) 
                if (!player.hasPermission("activecraft.kickall.bypass")) 
                    player.kickPlayer(CommandMessages.CUSTOM_KICKALL_MESSAGE(combineArray(args)));
        }
    }
    
    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}