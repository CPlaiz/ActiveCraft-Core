package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SuicideCommand extends ActiveCraftCommand {

    public SuicideCommand() {
        super("suicide");
    }

    private static final List<Player> suiciders = new ArrayList<>();

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        if (args.length == 0 ) {
            checkPermission(sender, "suicide.self");
            Player playerdied = getPlayer(sender);
            suiciders.add(playerdied);
            playerdied.setHealth(0);
            sendMessage(sender, CommandMessages. SUICIDE());
            for (Player player : Bukkit.getOnlinePlayers())
                if (playerdied != player)
                    sendMessage(player, CommandMessages.BROADCAST_SUICIDE(playerdied));
        } else if (args.length == 1) {
            checkPermission(sender, "suicide.others");
            Player target = getPlayer(args[0]);
            checkTargetSelf(sender, target, "suicide.self");
            suiciders.add(target);
            target.setHealth(0);
            sendMessage(sender, CommandMessages.SUICIDE_OTHERS(target));
            for (Player player : Bukkit.getOnlinePlayers())
                if (player != target)
                    player.sendMessage(CommandMessages.BROADCAST_SUICIDE(target));
        }
    }

    public static List<Player> getSuiciders() {
        return suiciders;
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}