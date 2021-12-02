package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.events.DialogueCompleteEvent;
import de.silencio.activecraftcore.dialogue.DialogueManager;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.manager.BanManager;
import de.silencio.activecraftcore.messages.Reasons;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.StringUtils;
import de.silencio.activecraftcore.utils.TimeUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;
import java.util.*;

public class BanCommand implements CommandExecutor, Listener, TabCompleter {

    private Map<Player, List<String>> banDialogueList = new HashMap<>();

    private DialogueManager dialogueManager;
    private Player target;
    private CommandSender commandSender;
    private BanManager nameBanManager = new BanManager(BanList.Type.NAME);
    private BanManager ipBanManager = new BanManager(BanList.Type.IP);
    private BanList.Type value;
    private String targetIp;

    public BanCommand() {
        Bukkit.getPluginManager().registerEvents(this, ActiveCraftCore.getPlugin());
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("activecraft.ban")) {
            if (label.equalsIgnoreCase("ban")) {

                if (args.length < 1) {
                    sender.sendMessage(Errors.INVALID_ARGUMENTS());
                    return false;
                }

                if (args.length == 0) {
                    sender.sendMessage(Errors.INVALID_ARGUMENTS());
                    return false;
                }

                value = BanList.Type.NAME;

                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER());
                    return false;
                }
                this.target = Bukkit.getPlayer(args[0]);
                if (!nameBanManager.isBanned(args[0])) {

                    if (args.length > 1 && (
                            args[1].equalsIgnoreCase("-i")
                            || args[1].equalsIgnoreCase("-instant")
                            || args[1].equalsIgnoreCase("instant") )) {
                        nameBanManager.ban(target, Reasons.MODERATOR("Banned"), null, sender.getName());
                        FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + target.getName().toLowerCase() + ".yml");
                        playerdataConfig.set("violations.bans", playerdataConfig.getInt("violations.bans") + 1);
                        playerdataConfig.saveConfig();
                        Bukkit.getScheduler().runTask(ActiveCraftCore.getPlugin(), new Runnable() {
                            @Override
                            public void run() {
                                target.kickPlayer(Reasons.MODERATOR("Kicked"));
                            }
                        });
                    } else {

                        this.commandSender = sender;

                        this.dialogueManager = new DialogueManager((Player) sender);
                        this.dialogueManager.setHeader(CommandMessages.BAN_HEADER(target));
                        this.dialogueManager.setCompletedMessage(CommandMessages.BAN_COMPLETED_MESSAGE(target));
                        this.dialogueManager.setCancelledMessage(CommandMessages.BAN_CANCELLED_MESSAGE(target));
                        this.dialogueManager.add(CommandMessages.BAN_ENTER_REASON());
                        this.dialogueManager.add(CommandMessages.BAN_ENTER_TIME());
                        this.dialogueManager.initialize();
                    }
                } else sender.sendMessage(CommandMessages.ALREAEDY_BANNED());
            } else if (label.equalsIgnoreCase("unban")) {
                if (args.length < 1) {
                    sender.sendMessage(Errors.INVALID_ARGUMENTS());
                    return false;
                }
                if (nameBanManager.isBanned(args[0])) {
                    nameBanManager.unban(args[0]);
                    sender.sendMessage(CommandMessages.UNBANNED_PLAYER(args[0]));
                } else sender.sendMessage(Errors.WARNING() + CommandMessages.NOT_BANNED());
            } else if (label.equalsIgnoreCase("banlist")) {
                if (sender.hasPermission("activecraft.banlist")) {

                    if (!nameBanManager.getBans().isEmpty() || !ipBanManager.getBans().isEmpty()) {

                        List<String> tempBanListName = new ArrayList<>();
                        List<String> tempBanListIP = new ArrayList<>();
                        List<TextComponent> textComponentList = new ArrayList<>();

                        ComponentBuilder componentBuilder = new ComponentBuilder();

                        for (BanEntry banEntry : nameBanManager.getBans()) {
                            tempBanListName.add(banEntry.getTarget());
                        }
                        for (BanEntry banEntry : ipBanManager.getBans()) {
                            tempBanListIP.add(banEntry.getTarget());
                        }
                        Collections.sort(tempBanListName);
                        Collections.sort(tempBanListIP);
                        boolean isFirst = true;
                        for (String s : tempBanListName) {
                            TextComponent textComponent = new TextComponent();
                            Player target = Bukkit.getPlayer(s);
                            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(CommandMessages.UNBAN_ON_HOVER(s))));
                            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/unban " + s));
                            if (!isFirst) {
                                textComponent.setText(", " + s);
                            } else {
                                textComponent.setText(s);
                                isFirst = false;
                            }
                            //textComponentList.add(textComponent);
                            componentBuilder.append(textComponent);
                        }
                        for (String s : tempBanListIP) {
                            TextComponent textComponent = new TextComponent();
                            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(CommandMessages.UNBAN_IP_ON_HOVER(s.toString()))));
                            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/unban-ip " + s));
                            StringBuilder stringBuilder = new StringBuilder();
                            for (String playername : ActiveCraftCore.getPlugin().getPlayerlist().keySet()) {
                                FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + playername + ".yml");
                                boolean isFirst1 = true;
                                if (playerdataConfig.getStringList("known-ips").contains(s)) {
                                    if (!isFirst1) {
                                        stringBuilder.append(", ").append(playerdataConfig.getString("name"));
                                    } else {
                                        stringBuilder.append(playerdataConfig.getString("name"));
                                        isFirst1 = false;
                                    }
                                }
                            }
                            if (!stringBuilder.toString().equalsIgnoreCase("")) {
                                if (!isFirst) {
                                    textComponent.setText(", " + s + " (" + stringBuilder.toString() + ")");
                                } else {
                                    textComponent.setText(s + " (" + stringBuilder.toString() + ")");
                                    isFirst = false;
                                }
                            } else {
                                if (!isFirst) {
                                    textComponent.setText(", " + s);
                                } else {
                                    textComponent.setText(s);
                                    isFirst = false;
                                }
                            }
                            //textComponentList.add(textComponent);
                            componentBuilder.append(textComponent);
                        }

                        sender.sendMessage(CommandMessages.BANLIST_HEADER());
                        sender.sendMessage(componentBuilder.create());
                    } else sender.sendMessage(CommandMessages.NO_BANS());
                }
            } else if (label.equalsIgnoreCase("ban-ip")) {

                value = BanList.Type.IP;

                if (args.length < 1) {
                    sender.sendMessage(Errors.INVALID_ARGUMENTS());
                    return false;
                }

                if (Bukkit.getPlayer(args[0]) != null) {
                    this.target = Bukkit.getPlayer(args[0]);
                    if (!ipBanManager.isBanned(target.getAddress().getAddress().toString().replace("/", ""))) {

                        if (args.length > 1 && (
                                args[1].equalsIgnoreCase("-i")
                                        || args[1].equalsIgnoreCase("-instant")
                                        || args[1].equalsIgnoreCase("instant") )) {
                            ipBanManager.ban(target.getAddress().getAddress().toString().replace("/", ""), Reasons.MODERATOR("Banned"), null, sender.getName());
                            FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + target.getName().toLowerCase() + ".yml");
                            playerdataConfig.set("violations.bans", playerdataConfig.getInt("violations.bans") + 1);
                            playerdataConfig.saveConfig();
                            Bukkit.getScheduler().runTask(ActiveCraftCore.getPlugin(), new Runnable() {
                                @Override
                                public void run() {
                                    target.kickPlayer(Reasons.MODERATOR("Kicked"));
                                }
                            });
                        } else {

                            this.commandSender = sender;

                            this.targetIp = target.getAddress().getAddress().toString().replace("/", "");

                            this.dialogueManager = new DialogueManager((Player) sender);
                            this.dialogueManager.setHeader(CommandMessages.IPBAN_HEADER(target, target.getAddress().getAddress().toString().replace("/", "")));
                            this.dialogueManager.setCompletedMessage(CommandMessages.IPBAN_COMPLETED_MESSAGE(target, target.getAddress().getAddress().toString().replace("/", "")));
                            this.dialogueManager.setCancelledMessage(CommandMessages.IPBAN_CANCELLED_MESSAGE(target, target.getAddress().getAddress().toString().replace("/", "")));
                            this.dialogueManager.add(CommandMessages.IPBAN_ENTER_REASON());
                            this.dialogueManager.add(CommandMessages.IPBAN_ENTER_TIME());
                            this.dialogueManager.initialize();

                        }
                    } else sender.sendMessage(CommandMessages.IP_ALREADY_BANNED());
                } else if (StringUtils.isValidInet4Address(args[0])) {
                    if (!ipBanManager.isBanned(args[0])) {

                         targetIp = args[0];

                        this.commandSender = sender;

                        this.dialogueManager = new DialogueManager((Player) sender);
                        this.dialogueManager.setHeader(CommandMessages.IPBAN_HEADER(targetIp));
                        this.dialogueManager.setCompletedMessage(CommandMessages.IPBAN_COMPLETED_MESSAGE(targetIp));
                        this.dialogueManager.setCancelledMessage(CommandMessages.IPBAN_CANCELLED_MESSAGE(targetIp));
                        this.dialogueManager.add(CommandMessages.IPBAN_ENTER_REASON());
                        this.dialogueManager.add(CommandMessages.IPBAN_ENTER_TIME());
                        this.dialogueManager.initialize();

                        Date date = new Date();
                    } else sender.sendMessage(CommandMessages.IP_ALREADY_BANNED());
                } else sender.sendMessage(Errors.WARNING() + CommandMessages.INVALID_IP());
            } else if (label.equalsIgnoreCase("unban-ip")) {
                if (args.length < 1) {
                    sender.sendMessage(Errors.INVALID_ARGUMENTS());
                    return false;
                }
                boolean valid = false;
                for (BanEntry banEntry : ipBanManager.getBans()) {
                    if (banEntry.getTarget().equals(args[0])) {
                        valid = true;
                        ipBanManager.unban(args[0]);
                        sender.sendMessage(CommandMessages.UNBANNED_IP(args[0]));
                        break;
                    }
                }
                if (!valid) sender.sendMessage(Errors.WARNING() + CommandMessages.IP_NOT_BANNED());
            }
        } else sender.sendMessage(Errors.NO_PERMISSION());
        return true;
    }

    @EventHandler
    public void onDialogueComplete(DialogueCompleteEvent event) {

        if (this.dialogueManager == event.getDialogueManager()) {
            if (value == BanList.Type.NAME) {
                nameBanManager.ban(target, this.dialogueManager.getAnswer(0), TimeUtils.addFromStringToDate(this.dialogueManager.getAnswer(1)), commandSender.getName());
                FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + target.getName().toLowerCase() + ".yml");
                playerdataConfig.set("violations.bans", playerdataConfig.getInt("violations.bans") + 1);
                playerdataConfig.saveConfig();
                Bukkit.getScheduler().runTask(ActiveCraftCore.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        target.kickPlayer(dialogueManager.getAnswer(0));
                    }
                });
            } else if (value == BanList.Type.IP) {
                ipBanManager.ban(targetIp, this.dialogueManager.getAnswer(0), TimeUtils.addFromStringToDate(this.dialogueManager.getAnswer(1)), commandSender.getName());
                if (!StringUtils.isValidInet4Address(targetIp)) {
                    FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + target.getName().toLowerCase() + ".yml");
                    playerdataConfig.set("violations.ip-bans", playerdataConfig.getInt("violations.ip-bans") + 1);
                    playerdataConfig.saveConfig();
                    Bukkit.getScheduler().runTask(ActiveCraftCore.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            target.kickPlayer(event.getDialogueManager().getAnswer(0));
                        }
                    });
                }
            }
        }
    }

    public DialogueManager getDialogueManager() {
        return dialogueManager;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Player p = (Player) sender;

        if (alias.equalsIgnoreCase("unban")) {
            if (args.length == 0) return list;
            if (args.length == 1) {
                if (!nameBanManager.getBans().isEmpty()) {
                    for (BanEntry banEntry : nameBanManager.getBans()) {
                        list.add(banEntry.getTarget());
                    }
                }
            }

        } else if (alias.equalsIgnoreCase("ban")) {
            if (args.length == 0) return list;
            if (args.length == 1) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    list.add(player.getName());
                }
            }
        } else if (alias.equalsIgnoreCase("unban-ip")) {

            if (args.length == 0) return list;
            if (args.length == 1) {
                if (!ipBanManager.getBans().isEmpty()) {
                    for (BanEntry banEntry : ipBanManager.getBans()) {
                        list.add(banEntry.getTarget());
                    }
                }
            }
        } else if (alias.equalsIgnoreCase("ban-ip")) {
            if (args.length == 0) return list;
            if (args.length == 1) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    list.add(player.getName());
                }
            }
        }

        ArrayList<String> completerList = new ArrayList<>();
        String currentarg = args[args.length - 1].toLowerCase();
        for (String s : list) {
            String s1 = s.toLowerCase();
            if (s1.startsWith(currentarg)) {
                completerList.add(s);
            }
        }
        return completerList;
    }

}

// 1m = 1 minute
// 1h = 1 hour
// 1d = 1 day
// 1M = 1 month
// 1y = 1 year

// 1d5h = 1 day + 5 hours