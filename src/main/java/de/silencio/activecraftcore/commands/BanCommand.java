package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.ownlisteners.DialogueListener;
import de.silencio.activecraftcore.utils.BanManager;
import de.silencio.activecraftcore.utils.DialogueManager;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.*;
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
import org.bukkit.event.Listener;

import java.util.*;
import java.util.List;

public class BanCommand implements CommandExecutor, DialogueList, Listener, DialogueListener, TabCompleter {

    private Map<Player, List<String>> banDialogueList = new HashMap<>();

    private DialogueManager dialogueManager;
    private Player target;
    private CommandSender commandSender;
    private BanManager nameBanManager = new BanManager(BanList.Type.NAME);
    private BanManager ipBanManager = new BanManager(BanList.Type.IP);
    private BanList.Type type;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //System.out.println(Main.getPlugin().getDialogueListenerList().toString());


        if (sender.hasPermission("activecraft.ban")) {
            if (label.equalsIgnoreCase("ban")) {

                type = BanList.Type.NAME;

                this.target = Bukkit.getPlayer(args[0]);
                if (!nameBanManager.isBanned(args[0])) {

                    this.commandSender = sender;

                    this.dialogueManager = new DialogueManager((Player) sender);
                    this.dialogueManager.setHeader(ChatColor.GOLD + "-- Ban Dialogue for " + ChatColor.AQUA + target.getName() + ChatColor.GOLD + " --");
                    this.dialogueManager.setCompletedMessage(ChatColor.GOLD + "Banned " + ChatColor.AQUA + target.getName() + ChatColor.GOLD + ".");
                    this.dialogueManager.setCancelledMessage(ChatColor.GOLD + "Cancelled ban dialogue for " + ChatColor.AQUA + target.getName() + ChatColor.GOLD + ".");
                    this.dialogueManager.add(ChatColor.GOLD + "Please enter a reason: ");
                    this.dialogueManager.add(ChatColor.GOLD + "Please enter the ban duration: ");
                    this.dialogueManager.initialize();

                    Date date = new Date();
                } else sender.sendMessage(Errors.WARNING + "This player is already banned.");
            } else if (label.equalsIgnoreCase("unban")) {
                if (nameBanManager.isBanned(target.getName())) {
                    nameBanManager.unban(target);
                    sender.sendMessage(ChatColor.GOLD + "Unbanned " + ChatColor.AQUA + target.getName());
                } else sender.sendMessage(Errors.WARNING + "This player is not banned.");
            } else if (label.equalsIgnoreCase("banlist")) {
                if (sender.hasPermission("activecraft.banlist")) {

                    if (!nameBanManager.getBans().isEmpty()) {

                        List<String> tempBanList = new ArrayList<>();
                        List<TextComponent> textComponentList = new ArrayList<>();

                        ComponentBuilder componentBuilder = new ComponentBuilder();

                        for (BanEntry banEntry : nameBanManager.getBans()) {
                            tempBanList.add(banEntry.getTarget());
                        }
                        Collections.sort(tempBanList);

                        for (String s : tempBanList) {
                            TextComponent textComponent = new TextComponent();
                            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(ChatColor.GOLD + "Unban " + ChatColor.AQUA + s)));
                            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/unban " + s));
                            textComponent.setText(s + ", ");
                            //textComponentList.add(textComponent);
                            componentBuilder.append(textComponent);
                        }

                        sender.sendMessage(ChatColor.GOLD + "-- Ban List --");
                        sender.sendMessage(componentBuilder.create());
                    } else sender.sendMessage(ChatColor.GOLD + "There are no bans to be listed here!");
                }
            } else if (label.equalsIgnoreCase("ban-ip")) {

                type = BanList.Type.IP;

                this.target = Bukkit.getPlayer(args[0]);
                if (!ipBanManager.isBanned(args[0])) {

                    this.commandSender = sender;

                    this.dialogueManager = new DialogueManager((Player) sender);
                    this.dialogueManager.setHeader(ChatColor.GOLD + "-- IP Ban Dialogue for " + ChatColor.AQUA + target.getName() + ChatColor.GOLD + " --");
                    this.dialogueManager.setCompletedMessage(ChatColor.GOLD + "Banned " + ChatColor.AQUA + target.getName() + ChatColor.GOLD + "(" + target.getAddress().getAddress() + ").");
                    this.dialogueManager.setCancelledMessage(ChatColor.GOLD + "Cancelled ban dialogue for " + ChatColor.AQUA + target.getName() + ChatColor.GOLD + ".");
                    this.dialogueManager.add(ChatColor.GOLD + "Please enter a reason: ");
                    this.dialogueManager.add(ChatColor.GOLD + "Please enter the ban duration: ");
                    this.dialogueManager.initialize();

                    Date date = new Date();
                } else sender.sendMessage(Errors.WARNING + "This player is already banned.");
            } else if (label.equalsIgnoreCase("unban-ip")) {
                if (ipBanManager.isBanned(target.getName())) {
                    ipBanManager.unban(target);
                    sender.sendMessage(ChatColor.GOLD + "Unbanned " + ChatColor.AQUA + target.getName());
                } else sender.sendMessage(Errors.WARNING + "This player is not banned.");
            }
        } else sender.sendMessage(Errors.NO_PERMISSION);
        return true;
    }

    @Override
    public void onDialogueNext(DialogueManager dialogueManager) {
        return;
    }

    @Override
    public void onDialogueCancel(DialogueManager dialogueManager) {
        return;
    }

    @Override
    public void onDialogueComplete(DialogueManager dialogueManager) {
        if (this.dialogueManager == dialogueManager) {
            if (type == BanList.Type.NAME) {
                nameBanManager.ban(target, this.dialogueManager.getAnswer(0), null, commandSender.getName());
                Bukkit.getScheduler().runTask(Main.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        target.kickPlayer(dialogueManager.getAnswer(0));
                    }
                });
            } else if (type == BanList.Type.IP) {
                ipBanManager.ban(target.getAddress().getAddress().toString(), this.dialogueManager.getAnswer(0), null, commandSender.getName());
                Bukkit.getScheduler().runTask(Main.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        target.kickPlayer(dialogueManager.getAnswer(0));
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

// 1s = 1 second
// 1m = 1 minute
// 1h = 1 hour
// 1d = 1 day

// 1d5h = 1 day + 5 hours