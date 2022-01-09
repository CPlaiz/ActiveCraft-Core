package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TpAllCommand extends ActiveCraftCommand {

    public TpAllCommand() {
        super("tpall");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "tpall");
        Player player = getPlayer(sender);
        for (Player target : Bukkit.getOnlinePlayers()) {
            if (!target.hasPermission("activecraft.tpall.exept")) {
                target.teleport(player.getLocation());
                sendSilentMessage(target, CommandMessages.TPALL_MESSAGE(sender));
            } else {
                if(target != sender) {
                    sendSilentMessage(target, CommandMessages.TPALL_EXEPT(sender));
                }
            }
        }
        sendMessage(sender, CommandMessages.TPALL());
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}