package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class FeedCommand implements CommandExecutor {

    public FeedCommand() {
        super("feed");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        switch (args.length) {
            case 0 -> {
                checkPermission(sender, "feed.self");
                Player player = getPlayer(sender);
                player.setFoodLevel(20);
                sendMessage(sender, CommandMessages.FEED());
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_BURP, 1f, 1f);
            }
            case 1 -> {
                checkPermission(sender, "feed.others");
                Player target = getPlayer(args[0]);
                if (checkTargetSelf(sender, target, "feed.self")) sendSilentMessage(target, CommandMessages.FEED_OTHERS_MESSAGE(sender));
                target.setFoodLevel(20);
                sendMessage(sender, CommandMessages.FEED_OTHERS(target));
                target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_BURP, 1f, 1f);
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String alias, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}