package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.events.DialogueCompleteEvent;
import de.silencio.activecraftcore.dialogue.DialogueManager;
import de.silencio.activecraftcore.messages.ActiveCraftMessage;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.manager.BanManager;
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
import org.bukkit.ChatColor;
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
    private StringUtils stringUtils = new StringUtils();

    public BanCommand() {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin());
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("activecraft.ban")) {
            if (label.equalsIgnoreCase("ban")) {

                value = BanList.Type.NAME;

                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER());
                    return false;
                }
                this.target = Bukkit.getPlayer(args[0]);
                if (!nameBanManager.isBanned(args[0])) {

                    this.commandSender = sender;

                    this.dialogueManager = new DialogueManager((Player) sender);
                    this.dialogueManager.setHeader(CommandMessages.BAN_HEADER(target));
                    this.dialogueManager.setCompletedMessage(CommandMessages.BAN_COMPLETED_MESSAGE(target));
                    this.dialogueManager.setCancelledMessage(CommandMessages.BAN_CANCELLED_MESSAGE(target));
                    this.dialogueManager.add(CommandMessages.BAN_ENTER_REASON());
                    this.dialogueManager.add(CommandMessages.BAN_ENTER_TIME());
                    this.dialogueManager.initialize();

                    Date date = new Date();
                } else sender.sendMessage(CommandMessages.ALREAEDY_BANNED());
            } else if (label.equalsIgnoreCase("unban")) {
                if (nameBanManager.isBanned(args[0])) {
                    nameBanManager.unban(args[0]);
                    sender.sendMessage(CommandMessages.UNBANNED_PLAYER(args[0]));
                } else sender.sendMessage(CommandMessages.NOT_BANNED());
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

                        for (String s : tempBanListName) {
                            TextComponent textComponent = new TextComponent();
                            Player target = Bukkit.getPlayer(s);
                            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(CommandMessages.UNBAN_ON_HOVER(s))));
                            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/unban " + s));
                            textComponent.setText(s + ", ");
                            //textComponentList.add(textComponent);
                            componentBuilder.append(textComponent);
                        }
                        for (String s : tempBanListIP) {
                            TextComponent textComponent = new TextComponent();
                            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(CommandMessages.UNBAN_IP_ON_HOVER(s.toString()))));
                            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/unban-ip " + s));
                            FileConfig playerlist = new FileConfig("playerlist.yml");
                            StringBuilder stringBuilder = new StringBuilder();
                            boolean isFirst = true;
                            for (String playername : playerlist.getStringList("players")) {
                                FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + playername + ".yml");
                                if (playerdataConfig.getStringList("known-ips").contains(s)) {
                                    if (!isFirst) {
                                        stringBuilder.append(", ");
                                    } else isFirst = false;
                                    stringBuilder.append(playerdataConfig.getString("name"));

                                }
                            }
                            if (!stringBuilder.toString().equalsIgnoreCase("")) {
                                textComponent.setText(s + " (" + stringBuilder.toString() + ")" + ", ");
                            } else {
                                textComponent.setText(s + ", ");
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

                if (args.length == 0) {
                    sender.sendMessage(Errors.INVALID_ARGUMENTS());
                    return false;
                }

                this.target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    if (!ipBanManager.isBanned(args[0])) {

                        this.commandSender = sender;

                        this.dialogueManager = new DialogueManager((Player) sender);
                        this.dialogueManager.setHeader(CommandMessages.IPBAN_HEADER(target, target.getAddress().getAddress().toString().replace("/", "")));
                        this.dialogueManager.setCompletedMessage(CommandMessages.IPBAN_COMPLETED_MESSAGE(target, target.getAddress().getAddress().toString().replace("/", "")));
                        this.dialogueManager.setCancelledMessage(CommandMessages.IPBAN_CANCELLED_MESSAGE(target, target.getAddress().getAddress().toString().replace("/", "")));
                        this.dialogueManager.add(CommandMessages.IPBAN_ENTER_REASON());
                        this.dialogueManager.add(CommandMessages.IPBAN_ENTER_TIME());
                        this.dialogueManager.initialize();

                        Date date = new Date();
                    } else sender.sendMessage(CommandMessages.IP_ALREADY_BANNED());
                } else if (stringUtils.isValidInet4Address(args[0])) {
                    if (!ipBanManager.isBanned(args[0])) {

                        String ip = args[0];

                        this.commandSender = sender;

                        this.dialogueManager = new DialogueManager((Player) sender);
                        this.dialogueManager.setHeader(CommandMessages.IPBAN_HEADER(target, target.getAddress().getAddress().getHostAddress().replace("/", "")));
                        this.dialogueManager.setCompletedMessage(CommandMessages.IPBAN_COMPLETED_MESSAGE(target, target.getAddress().getAddress().toString().replace("/", "")));
                        this.dialogueManager.setCancelledMessage(CommandMessages.IPBAN_CANCELLED_MESSAGE(target, target.getAddress().getAddress().toString().replace("/", "")));
                        this.dialogueManager.add(CommandMessages.IPBAN_ENTER_REASON());
                        this.dialogueManager.add(CommandMessages.IPBAN_ENTER_TIME());
                        this.dialogueManager.initialize();

                        Date date = new Date();
                    } else sender.sendMessage(CommandMessages.IP_ALREADY_BANNED());
                } else sender.sendMessage(Errors.WARNING() + CommandMessages.INVALID_IP());
            } else if (label.equalsIgnoreCase("unban-ip")) {
                boolean valid = false;
                for (BanEntry banEntry : ipBanManager.getBans()) {
                    if (banEntry.getTarget().equals(args[0])) {
                        valid = true;
                        System.out.println(args[0]);
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
                Bukkit.getScheduler().runTask(Main.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        target.kickPlayer(dialogueManager.getAnswer(0));
                    }
                });
            } else if (value == BanList.Type.IP) {
                ipBanManager.ban(target.getAddress().getAddress().toString().replace("/", ""), this.dialogueManager.getAnswer(0), TimeUtils.addFromStringToDate(this.dialogueManager.getAnswer(1)), commandSender.getName());
                FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + target.getName().toLowerCase() + ".yml");
                playerdataConfig.set("violations.ip-bans", playerdataConfig.getInt("violations.ip-bans") + 1);
                playerdataConfig.saveConfig();
                Bukkit.getScheduler().runTask(Main.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        target.kickPlayer(event.getDialogueManager().getAnswer(0));
                    }
                });
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