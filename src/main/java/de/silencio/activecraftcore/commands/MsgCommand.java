package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.events.MsgEvent;
import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.exceptions.InvalidPlayerException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.playermanagement.PlayerQueue;
import de.silencio.activecraftcore.playermanagement.Profile;
import de.silencio.activecraftcore.utils.ColorUtils;
import de.silencio.activecraftcore.utils.ComparisonType;
import de.silencio.activecraftcore.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MsgCommand implements CommandExecutor, TabCompleter {

    public MsgCommand() {
        super("msg");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "msg");
        checkArgsLength(args, ComparisonType.GREATER , 1);
        Profile profile = getProfile(args[0]);
        checkTargetSelf(sender, profile.getName());

        String message = combineArray(args, 1);
        message = ColorUtils.replaceColor(message);
        message = ColorUtils.replaceFormat(message);
        String finalMessage = message;
        if (sender instanceof Player) {
            sendMessage(sender, CommandMessages.MSG_PREFIX_TO(profile, message));
            PlayerQueue.add(profile, () -> {
                Player target = Bukkit.getPlayer(profile.getName());
                MsgEvent event = new MsgEvent(sender, target, finalMessage);
                Bukkit.getPluginManager().callEvent(event);
                if (event.isCancelled()) return;
                sendMessage(target, CommandMessages.MSG_PREFIX_FROM(sender, event.getMessage()));
                target.playSound(target.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1f, 1f);
                try {
                    ActiveCraftCore.getMsgPlayerStoring().put(target, getProfile(sender.getName()));
                } catch (InvalidPlayerException ignored) {
                }
                for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    if (!onlinePlayer.hasPermission("activecraft.msg.spy")) continue;
                    if(!getProfile(onlinePlayer).canReceiveSocialspy()) continue;
                    if (onlinePlayer != sender && onlinePlayer != target)
                        sendMessage(onlinePlayer, CommandMessages.SOCIALSPY_PREFIX_TO(target, sender, finalMessage));
                }
                if (ConfigUtils.getMainConfig().getBoolean("socialspy-to-console"))
                    sendMessage(Bukkit.getConsoleSender(), CommandMessages.SOCIALSPY_PREFIX_TO(target, sender, event.getMessage()));
            });
        } else {
            PlayerQueue.add(profile, () -> {
                Player target = Bukkit.getPlayer(profile.getName());
                MsgEvent event = new MsgEvent(sender, target, finalMessage);
                Bukkit.getPluginManager().callEvent(event);
                if (event.isCancelled()) return;
                sendMessage(target, CommandMessages.CONSOLE_MSG_PREFIX(event.getMessage()));
                target.playSound(target.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1f, 1f);
            });
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? new ArrayList<>(getProfileNames()) : null;
    }
}