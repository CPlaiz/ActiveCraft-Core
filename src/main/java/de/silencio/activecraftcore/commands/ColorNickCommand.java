package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.events.ColornickEvent;
import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.exceptions.InvalidArgumentException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.playermanagement.Profile;
import de.silencio.activecraftcore.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ColorNickCommand extends ActiveCraftCommand {

    public ColorNickCommand() {
        super("colornick");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        if (args.length == 1) {
            checkPermission(sender, "colornick.self");
            Player player = getPlayer(sender);
            Profile profile = getProfile(player);
            ChatColor color = args[0].equalsIgnoreCase("random") ? ColorUtils.getRandomColor() : getChatColor(args[0]);
            //call event
            ColornickEvent event = new ColornickEvent(profile, color);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) return;

            profile.set(Profile.Value.COLOR_NICK, color.name());
            profile.reloadDisplayname();
            sendMessage(sender, CommandMessages.COLORNICK_SELF(color + color.name()));
        } else if (args.length >= 2) {
            checkPermission(sender, "colornick.self");
            Player target = getPlayer(args[0]);
            Profile profile = getProfile(target);
            ChatColor color = args[1].equalsIgnoreCase("random") ? ColorUtils.getRandomColor() : getChatColor(args[1]);
            checkTargetSelf(sender, target, "colornick.self");
            //call event
            ColornickEvent event = new ColornickEvent(profile, color);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) return;

            sendMessage(sender, CommandMessages.COLORNICK_OTHERS(target, color + color.name()));
            profile.set(Profile.Value.COLOR_NICK, color.name());
            profile.reloadDisplayname();
            sendSilentMessage(target, CommandMessages.COLORNICK_OTHERS_MESSAGE(sender, color + color.name()));
        } else throw new InvalidArgumentException();
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        switch (args.length) {
            case 1 -> {
                list.add("random");
                list.addAll(getBukkitPlayernames());
                for (ChatColor color : ColorUtils.getColorsOnly()) list.add(color.name().toLowerCase());
            }
            case 2 -> {
                for (ChatColor color : ColorUtils.getColorsOnly()) list.add(color.name().toLowerCase());
            }
        }
        return list;
    }
}