package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.utils.ComparisonType;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class XpCommand extends ActiveCraftCommand {

    public XpCommand() {
        super("xp");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkArgsLength(args, ComparisonType.GREATER_EQUAL, 2);
        switch (args[0].toLowerCase()) {
            case "add" -> {
                if (args.length == 2) {
                    checkPermission(sender, "xp.self");
                    Player player = getPlayer(sender);

                    if (args[1].endsWith("l")) {
                        player.giveExpLevels(parseInt(args[1].replace("l", "")));
                        sendMessage(sender, CommandMessages.XP_ADD_LEVELS(args[1].replace("l", "")));
                    } else {
                        player.giveExp(parseInt(args[1]));
                        sendMessage(sender, CommandMessages.XP_ADD_XP(args[1]));
                    }

                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                } else if (args.length >= 2) {
                    checkPermission(sender, "xp.others");
                    Player target = getPlayer(args[1]);

                    if (args[2].endsWith("l")) {
                        if (!checkTargetSelf(sender, target, "xp.self")) sendSilentMessage(target, CommandMessages.XP_ADD_LEVELS_OTHERS_MESSAGE(sender, args[2].replace("l", "")));
                        target.giveExpLevels(parseInt(args[2].replace("l", "")));
                        sendMessage(sender, CommandMessages.XP_ADD_LEVELS_OTHERS(target, args[2].replace("l", "")));
                    } else {
                        if (!checkTargetSelf(sender, target, "xp.self")) sendSilentMessage(target, CommandMessages.XP_ADD_XP_OTHERS_MESSAGE(sender, args[2]));
                        target.giveExp(parseInt(args[2]));
                        sendMessage(sender, CommandMessages.XP_ADD_XP_OTHERS(target, args[2]));
                    }
                    target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                }
            }
            case "set" -> {
                if (args.length == 2) {
                    checkPermission(sender, "xp.self");
                    Player player = getPlayer(sender);

                    if (args[1].endsWith("l")) {
                        player.setTotalExperience(0);
                        player.giveExpLevels(parseInt(args[1].replace("l", "")));
                        sendMessage(sender, CommandMessages.XP_SET_LEVELS(args[1].replace("l", "")));
                    } else {
                        player.setTotalExperience(0);
                        player.giveExp(parseInt(args[1]));
                        sendMessage(sender, CommandMessages.XP_SET_XP(args[1]));
                    }

                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                } else if (args.length >= 2) {
                    checkPermission(sender, "xp.others");
                    Player target = getPlayer(args[1]);

                    if (args[2].endsWith("l")) {
                        if (!checkTargetSelf(sender, target, "xp.self")) sendSilentMessage(target, CommandMessages.XP_SET_LEVELS_OTHERS_MESSAGE(sender, args[2].replace("l", "")));
                        target.setTotalExperience(0);
                        target.giveExpLevels(parseInt(args[2].replace("l", "")));
                        sendMessage(sender, CommandMessages.XP_SET_LEVELS_OTHERS(target, args[2].replace("l", "")));
                    } else {
                        if (!checkTargetSelf(sender, target, "xp.self")) sendSilentMessage(target, CommandMessages.XP_SET_XP_OTHERS_MESSAGE(sender, args[2]));
                        target.setTotalExperience(0);
                        target.giveExp(parseInt(args[2]));
                        sendMessage(sender, CommandMessages.XP_SET_XP_OTHERS(target, args[2].replace("l", "")));
                    }

                    target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                }
            }
            case "clear" -> {
                if (args.length == 1) {
                    checkPermission(sender, "xp.self");
                    Player player = getPlayer(sender);

                    player.setTotalExperience(0);
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                    sendMessage(sender, CommandMessages.XP_CLEAR());
                } else {
                    checkPermission(sender, "xp.others");
                    Player target = getPlayer(args[1]);

                    if (!checkTargetSelf(sender, target, "xp.self")) sendSilentMessage(target, CommandMessages.XP_CLEAR_OTHERS_MESSAGE(sender));
                    target.setTotalExperience(0);
                    target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                    sendMessage(sender, CommandMessages.XP_CLEAR_OTHERS(target));
                }
            }
        }

    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            list.add("add");
            list.add("set");
            list.add("clear");
        }
        if (args.length == 2) list.addAll(getBukkitPlayernames());
        return list;
    }
}