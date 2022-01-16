package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.events.NickEvent;
import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.playermanagement.Profile;
import de.silencio.activecraftcore.utils.ColorUtils;
import de.silencio.activecraftcore.utils.ComparisonType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class NickCommand extends ActiveCraftCommand {

    public NickCommand() {
        super("nick");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkArgsLength(args, ComparisonType.GREATER, 0);
        if (Bukkit.getPlayer(args[0]) == null) {
            checkPermission(sender, "nick.self");
            Player player = getPlayer(sender);
            Profile profile = getProfile(player);
            String nickname = combineArray(args);
            nickname = ColorUtils.replaceColorAndFormat(nickname);
            //call event
            NickEvent event = new NickEvent(profile, nickname);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) return;

            profile.set(Profile.Value.NICKNAME, nickname);
            sendMessage(sender, CommandMessages.NICK_SET(nickname));
            profile.reloadDisplayname();
        } else {
            checkArgsLength(args, ComparisonType.GREATER_EQUAL, 2);
            checkPermission(sender, "nick.others");
            Player target = getPlayer(args[0]);
            Profile profile = getProfile(target);
            String nickname = combineArray(args, 1);
            nickname = ColorUtils.replaceColorAndFormat(nickname);
            // call event
            NickEvent event = new NickEvent(profile, nickname);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) return;

            if (!checkTargetSelf(sender, target, "nick.self")) sendSilentMessage(target, CommandMessages.NICK_SET_OTHERS_MESSAGE(sender, nickname));
            profile.set(Profile.Value.NICKNAME, nickname);
            sendMessage(sender, CommandMessages.NICK_SET_OTHERS(target, nickname));
            profile.reloadDisplayname();
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? new ArrayList<>(getProfileNames()) : null;
    }
}