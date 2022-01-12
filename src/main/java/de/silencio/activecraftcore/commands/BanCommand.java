package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.manager.BanManager;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.messages.Reasons;
import de.silencio.activecraftcore.playermanagement.Profile;
import de.silencio.activecraftcore.utils.ComparisonType;
import de.silencio.activecraftcore.utils.StringUtils;
import de.silencio.activecraftcore.utils.TimeUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.BanEntry;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BanCommand extends ActiveCraftCommand {

    public BanCommand() {
        super("ban");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "ban");
        switch (label) {
            case "ban" -> {
                checkArgsLength(args, ComparisonType.GREATER_EQUAL, 1);
                Player target = getPlayer(args[0]);
                if (BanManager.Name.isBanned(args[0])) {
                    sendMessage(sender, CommandMessages.ALREADY_BANNED());
                    return;
                }
                BanManager.Name.ban(target, args.length >= 3 ? combineArray(args, 2) : Reasons.MODERATOR_BANNED(), TimeUtils.addFromStringToDate(args.length >= 2 ? args[1] : null), sender.getName());
                sendMessage(sender, CommandMessages.BAN_COMPLETED_MESSAGE(target));
            }
            case "unban" -> {
                checkArgsLength(args, ComparisonType.GREATER_EQUAL, 1);
                if (BanManager.Name.isBanned(args[0])) {
                    BanManager.Name.unban(args[0]);
                    sendMessage(sender, CommandMessages.UNBANNED_PLAYER(ActiveCraftCore.getProfile(args[0]) != null ? ActiveCraftCore.getProfile(args[0]).getName() : args[0]));
                } else sendMessage(sender, Errors.WARNING() + CommandMessages.NOT_BANNED());
            }
            case "ban-ip" -> {
                checkArgsLength(args, ComparisonType.GREATER_EQUAL, 1);
                if (Bukkit.getPlayer(args[0]) != null) {
                    Player target = getPlayer(args[0]);
                    if (BanManager.IP.isBanned(target.getAddress().getAddress().toString().replace("/", ""))) {
                        sendMessage(sender, CommandMessages.ALREADY_BANNED());
                        return;
                    }
                    BanManager.IP.ban(target.getAddress().getAddress().toString().replace("/", ""), args.length >= 3 ? combineArray(args, 2) : Reasons.MODERATOR_BANNED(), TimeUtils.addFromStringToDate(args.length >= 2 ? args[1] : null), sender.getName());
                    sendMessage(sender, CommandMessages.IPBAN_COMPLETED_MESSAGE(target, target.getAddress().getAddress().toString().replace("/", "")));
                } else if (StringUtils.isValidInet4Address(args[0])) {
                    {
                        if (BanManager.IP.isBanned(args[0])) {
                            sendMessage(sender, CommandMessages.IP_ALREADY_BANNED());
                            return;
                        }
                        BanManager.IP.ban(args[0], args.length >= 3 ? combineArray(args, 2) : Reasons.MODERATOR_BANNED(), TimeUtils.addFromStringToDate(args.length >= 2 ? args[1] : null), sender.getName());
                        sendMessage(sender, CommandMessages.IPBAN_COMPLETED_MESSAGE(args[0]));
                    }
                } else sendMessage(sender, Errors.WARNING() + CommandMessages.INVALID_IP());
            }
            case "unban-ip" -> {
                checkArgsLength(args, ComparisonType.GREATER_EQUAL, 1);
                if (BanManager.IP.isBanned(args[0])) {
                    BanManager.IP.unban(args[0]);
                    sendMessage(sender, CommandMessages.UNBANNED_IP(args[0]));
                } else sendMessage(sender, Errors.WARNING() + CommandMessages.IP_NOT_BANNED());
            }
            case "banlist" -> {
                if (!BanManager.Name.getBans().isEmpty() || !BanManager.IP.getBans().isEmpty()) {

                    ComponentBuilder componentBuilder = new ComponentBuilder();
                    List<String> tempBanListName = new ArrayList<>();
                    List<String> tempBanListIP = new ArrayList<>();
                    for (BanEntry banEntry : BanManager.Name.getBans()) tempBanListName.add(banEntry.getTarget());
                    for (BanEntry banEntry : BanManager.IP.getBans()) tempBanListIP.add(banEntry.getTarget());
                    Collections.sort(tempBanListName);
                    Collections.sort(tempBanListIP);

                    for (int i = 0; i < tempBanListName.size(); i++) {
                        String name = tempBanListName.get(i);
                        TextComponent textComponent = new TextComponent();
                        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(CommandMessages.UNBAN_ON_HOVER(name))));
                        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/unban " + name));
                        if (i != 0) textComponent.setText(", " + name);
                        else textComponent.setText(name);
                        componentBuilder.append(textComponent);
                    }
                    for (int i = 0; i < tempBanListIP.size(); i++) {
                        String ip = tempBanListIP.get(i);
                        TextComponent textComponent = new TextComponent();
                        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(CommandMessages.UNBAN_IP_ON_HOVER(ip))));
                        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/unban-ip " + ip));
                        if (i != 0) textComponent.setText(", " + ip);
                        else textComponent.setText(ip);
                        componentBuilder.append(textComponent);
                    }

                    sendMessage(sender, CommandMessages.BANLIST_HEADER());
                    sendMessage(sender, componentBuilder.create());
                } else sendMessage(sender, CommandMessages.NO_BANS());
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        switch (alias.toLowerCase()) {
            case "ban", "ban-ip" -> {
                if (args.length != 1) return null;
                list.addAll(getBukkitPlayernames());
            }
            case "unban" -> {
                if (args.length != 1) return null;
                if (BanManager.Name.getBans().isEmpty()) return null;
                for (BanEntry banEntry : BanManager.Name.getBans()) list.add(banEntry.getTarget());
            }
            case "unban-ip" -> {
                if (args.length != 1) return null;
                if (BanManager.IP.getBans().isEmpty()) return null;
                for (BanEntry banEntry : BanManager.IP.getBans()) list.add(banEntry.getTarget());
            }
        }
        return list;
    }
}