package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.exceptions.InvalidArgumentException;
import de.silencio.activecraftcore.manager.WarnManager;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.ComparisonType;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WarnCommand extends ActiveCraftCommand {

    public WarnCommand() {
        super("warn");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkArgsLength(args, ComparisonType.GREATER_EQUAL, 2);
        Player target = getPlayer(args[1]);
        WarnManager warnManager = new WarnManager(target);
        switch (args[0]) {
            case "add" -> {
                checkPermission(sender, "warn.add");
                String warnToAdd = args.length >= 3 ? combineArray(args, 2) : CommandMessages.DEFAULT_WARN_REASON();
                String source = sender.getName();
                warnManager.add(warnToAdd, source);
                sendMessage(sender, CommandMessages.WARN_ADD(target, warnToAdd));
            }
            case "remove" -> {
                checkPermission(sender, "warn.remove");
                if (args.length >= 3) {
                    sendMessage(sender, CommandMessages.WARN_REMOVE(target, warnManager.getWarnEntry(combineArray(args, 2)).reason));
                    warnManager.remove(args[2]);
                } else throw new InvalidArgumentException();
            }
            case "get" -> {
                if (args.length >= 3) {
                    String warnToGet = combineArray(args, 2);
                    sendMessage(sender,
                            CommandMessages.WARN_GET_HEADER(target) + "\n" +
                            CommandMessages.WARN_GET(
                                    warnManager.getWarnEntry(warnToGet).source,
                                    warnManager.getWarnEntry(warnToGet).reason,
                                    warnManager.getWarnEntry(warnToGet).created,
                                    warnManager.getWarnEntry(warnToGet).id + ""));
                } else sendMessage(sender, Errors.INVALID_ARGUMENTS());
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        FileConfig warnsConfig = new FileConfig("warns.yml");

        switch (args.length) {
            case 1 -> {
                list.add("add");
                list.add("remove");
                list.add("get");
            }
            case 2 -> {
                switch (args[0].toLowerCase()) {
                    case "add", "get", "remove" -> list.addAll(getBukkitPlayernames());
                }
            }
            case 3 -> {
                switch (args[0].toLowerCase()) {
                    case "get", "remove" -> {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target != null) {
                            for (String s : warnsConfig.getStringList(target.getName() + ".warn-list")) {
                                s = s.replace("%dot%", ".");
                                list.add(s);
                            }
                        }
                    }
                }
            }
        }
        return list;
    }
}
