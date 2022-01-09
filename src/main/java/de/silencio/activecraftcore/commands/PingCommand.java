package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class PingCommand extends ActiveCraftCommand {

    public PingCommand() {
        super("ping");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        if (sender instanceof Player) {
            Player player = getPlayer(sender);
            checkPermission(sender, "ping");
            sendMessage(sender, CommandMessages.PING_PLAYER(player.getPing() + ""));
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 1f);
        } else sendMessage(sender, CommandMessages.PING_CONSOLE());
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}