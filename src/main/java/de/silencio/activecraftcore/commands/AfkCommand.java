package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.manager.AfkManager;
import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class AfkCommand extends ActiveCraftCommand {

    public AfkCommand() {
        super("afk");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        if (args.length == 0) {
            checkPermission(sender, "afk.self");
            Player player = getPlayer(sender);
            Profile profile = getProfile(player);
            AfkManager.setAfk(player, !profile.isAfk());
        } else {
            checkPermission(sender, "afk.others");
            Player target = getPlayer(args[0]);
            Profile profile = getProfile(target);
            checkTargetSelf(sender, target, "afk.self");
            AfkManager.setAfk(target, !profile.isAfk());
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String alias, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}