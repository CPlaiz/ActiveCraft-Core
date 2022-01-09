package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.playermanagement.Profile;
import de.silencio.activecraftcore.utils.ComparisonType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LogCommand extends ActiveCraftCommand {

    public LogCommand() {
        super("log");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "log");
        Player player = getPlayer(sender);
        Profile profile = getProfile(player);
        checkArgsLength(args, ComparisonType.EQUAL, 1);
        if (args[0].equalsIgnoreCase("on")) {
            sendMessage(sender, CommandMessages.ENABLE_LOG());
            profile.set(Profile.Value.LOG_ENABLED, true);
        }
        if (args[0].equalsIgnoreCase("off")) {
            sendMessage(sender, CommandMessages.DISABLE_LOG());
            profile.set(Profile.Value.LOG_ENABLED, false);
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 1) {
            list.add("on");
            list.add("off");
        }
        return list;
    }
}