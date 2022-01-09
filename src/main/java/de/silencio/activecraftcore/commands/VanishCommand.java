package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.manager.VanishManager;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.playermanagement.Profile;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class VanishCommand extends ActiveCraftCommand {

    public VanishCommand() {
        super("vanish");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        VanishManager vanishManager = ActiveCraftCore.getVanishManager();
        FileConfig fileConfig = new FileConfig("config.yml");
        String joinFormat = fileConfig.getString("join-format");
        String quitFormat = fileConfig.getString("quit-format");
        if (args.length == 0) {
            checkPermission(sender, "vanish.self");
            Player player = getPlayer(sender);
            Profile profile = getProfile(player);
            if (profile.isVanished()) {
                vanishManager.setVanished(player, false);
                sendMessage(sender, CommandMessages.NOW_VISIBLE());
                for (Player forPlayer : Bukkit.getOnlinePlayers())
                    if (forPlayer.hasPermission("activecraft.vanish.see")) {
                        if (forPlayer != sender)
                            forPlayer.sendMessage(CommandMessages.NOW_VISIBLE_OTHERS(player));
                    } else if (forPlayer != sender)
                        forPlayer.sendMessage(joinFormat.replace("%displayname%", StringUtils.joinQuitWithColor(player, profile.getNickname(), profile.getColorNick().name())));
            } else {
                vanishManager.setVanished(player, true);
                sendMessage(sender, CommandMessages.NOW_INVISIBLE());
                for (Player forPlayer : Bukkit.getOnlinePlayers())
                    if (forPlayer.hasPermission("activecraft.vanish.see")) {
                        if (forPlayer != sender)
                            forPlayer.sendMessage(CommandMessages.NOW_INVISIBLE_OTHERS(player));
                    } else if (forPlayer != sender)
                        forPlayer.sendMessage(quitFormat.replace("%displayname%", StringUtils.joinQuitWithColor(player, profile.getNickname(), profile.getColorNick().name())));
            }
        } else if (args.length == 1) {
            checkPermission(sender, "vanish.others");
            Player target = getPlayer(args[0]);
            Profile profile = getProfile(target);
            if (profile.isVanished()) {
                if (!checkTargetSelf(sender, target, "vanish.self")) target.sendMessage(CommandMessages.NOW_VISIBLE());
                sendMessage(sender, CommandMessages.NOW_VISIBLE_OTHERS(target));
                vanishManager.setVanished(target, false);
                for (Player forPlayer : Bukkit.getOnlinePlayers())
                    if (forPlayer.hasPermission("activecraft.vanish.see")) {
                        if (forPlayer != sender && forPlayer != target)
                            forPlayer.sendMessage(CommandMessages.NOW_VISIBLE_OTHERS(target));
                    } else if (forPlayer != sender && forPlayer != target)
                        forPlayer.sendMessage(joinFormat.replace("%displayname%", StringUtils.joinQuitWithColor(target, profile.getNickname(), profile.getColorNick().name())));
            } else {
                if (!checkTargetSelf(sender, target, "vanish.self")) target.sendMessage(CommandMessages.NOW_INVISIBLE());
                sendMessage(sender, CommandMessages.NOW_INVISIBLE_OTHERS(target));
                vanishManager.setVanished(target, true);
                for (Player forPlayer : Bukkit.getOnlinePlayers())
                    if (forPlayer.hasPermission("activecraft.vanish.see")) {
                        if (forPlayer != sender && forPlayer != target)
                            forPlayer.sendMessage(CommandMessages.NOW_INVISIBLE_OTHERS(target));
                    } else if (forPlayer != sender && forPlayer != target)
                        forPlayer.sendMessage(quitFormat.replace("%displayname%", StringUtils.joinQuitWithColor(target, profile.getNickname(), profile.getColorNick().name())));
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}