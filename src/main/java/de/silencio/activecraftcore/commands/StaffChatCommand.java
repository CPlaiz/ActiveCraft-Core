package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.events.StaffChatMessageEvent;
import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;


public class StaffChatCommand extends ActiveCraftCommand {

    public StaffChatCommand() {
        super("staffchat", "sc");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "staffchat");
        String message = combineArray(args);
        message = ColorUtils.replaceColor(message);
        message = ColorUtils.replaceFormat(message);
        StaffChatMessageEvent event = new StaffChatMessageEvent(sender, message);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        if (sender instanceof Player) Bukkit.broadcast(CommandMessages.STAFFCHAT_PREFIX(sender, message), "staffchat");
        else Bukkit.broadcast(CommandMessages.STAFFCHAT_FROM_CONSOLE_PREFIX(message), "staffchat");
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}