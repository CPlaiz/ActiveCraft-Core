package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.ComparisonType;
import de.silencio.activecraftcore.utils.ConfigUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;


public class TpaCommand extends ActiveCraftCommand {

    public TpaCommand() {
        super("tpa", "tpaccept", "tpdeny");
    }

    private static final HashMap<Player, Player> tpaList = new HashMap<>();
    private static HashMap<Player, BukkitRunnable> tpaTimerList;

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        Player player = getPlayer(sender);
        checkPermission(sender, "tpa");
        switch (label.toLowerCase()) {
            case "tpa" -> {
                checkArgsLength(args, ComparisonType.GREATER_EQUAL, 1);
                Player target = getPlayer(args[0]);
                if (target == player) {
                    sendMessage(sender, Errors.WARNING() + CommandMessages.CANNOT_TPA_SELF());
                    return;
                }

                TextComponent accept = new TextComponent(CommandMessages.TPA_ACCEPT() + " ");
                accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(CommandMessages.TPA_ACCEPT_HOVER()).create()));
                accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept"));

                TextComponent deny = new TextComponent(CommandMessages.TPA_DENY());
                deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(CommandMessages.TPA_DENY_HOVER()).create()));
                deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpadeny"));

                sendMessage(sender, CommandMessages.TPA_REQUEST_TO(target));
                target.sendMessage(" ");
                target.sendMessage(CommandMessages.TPA_REQUEST_FROM(sender));
                target.spigot().sendMessage(accept, deny);
                target.sendMessage(" ");
                tpaList.put(target, player);
            }
            case "tpaccept" -> {
                if (tpaList.containsKey(player)) {
                    Player target = tpaList.get(player);
                    Location loc = player.getLocation();
                    sendMessage(sender, CommandMessages.TPACCEPT_ACCEPTED());
                    if (!ConfigUtils.getMainConfig().getBoolean("use-timer-on-tpa")) {
                        tpaList.get(player).sendActionBar(CommandMessages.TPACCEPT_ACTIONBAR());
                        tpaList.get(player).teleport(loc);
                        tpaList.remove(player);
                    } else {
                        BukkitRunnable runnable = new BukkitRunnable() {
                            int time = 3;

                            @Override
                            public void run() {
                                if (time == 0) {
                                    target.sendActionBar(CommandMessages.TPACCEPT_ACTIONBAR());
                                    target.teleport(loc);
                                    target.sendMessage(CommandMessages.TPACCEPT_RECIEVER_MESSAGE(sender));
                                    target.playSound(target.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                                    sendMessage(sender, CommandMessages.TPACCEPT_SENDER_MESSAGE(target));
                                    cancel();
                                    tpaTimerList.put(tpaList.get(player), null);
                                    tpaList.remove((Player) sender);
                                    return;
                                }
                                target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                                target.sendActionBar(ChatColor.GOLD + "" + time);
                                time--;
                            }
                        };
                        if (tpaTimerList.get(target) != null)
                            tpaTimerList.get(target).cancel();
                        tpaTimerList.put(tpaList.get(player), runnable);
                        runnable.runTaskTimer(ActiveCraftCore.getPlugin(), 0, 20);
                    }
                } else sendMessage(sender, Errors.WARNING() + CommandMessages.NO_REQUESTS_ACCEPT());
            }
            case "tpdeny" -> {
                if (tpaList.containsKey(player)) {
                    Player target = tpaList.get(player);
                    sendMessage(sender, CommandMessages.TPADENY_DENIED());
                    sendMessage(sender, CommandMessages.TPADENY_RECIEVER_MESSAGE(target));
                    target.sendMessage(CommandMessages.TPADENY_SENDER_MESSAGE(player));
                    tpaList.remove((Player) sender);
                } else sendMessage(sender, Errors.WARNING() + CommandMessages.NO_REQUESTS_DENY());
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 && label.equalsIgnoreCase("tpa") ? getBukkitPlayernames() : null;
    }
}