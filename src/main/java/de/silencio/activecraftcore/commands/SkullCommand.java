package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SkullCommand extends ActiveCraftCommand {

    public SkullCommand() {
        super("skull");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        Player player = getPlayer(sender);
        switch (args.length) {
            case 0 -> {
                checkPermission(sender, "skull.self");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " minecraft:player_head{SkullOwner:\"" + player.getName() + "\"}");
                sendMessage(sender, CommandMessages.GIVE_SKULL());
            }
            case 1 -> {
                checkPermission(sender, "skull.others");
                checkTargetSelf(sender, getPlayer(args[0]), "skull.self");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " minecraft:player_head{SkullOwner:\"" + args[0] + "\"}");
                sendMessage(sender, CommandMessages.GIVE_SKULL_OTHERS(args[0]));
            }
            default -> {
                checkPermission(sender, "skull.multiple");
                checkTargetSelf(sender, getPlayer(args[0]), "skull.self");
                for (int i = parseInt(args[1]); i > 0; i--)
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + sender.getName() + " minecraft:player_head{SkullOwner:\"" + args[0] + "\"}");
                sendMessage(sender, CommandMessages.GIVE_SKULL_OTHERS_MULTIPLE(args[0], parseInt(args[1]) + ""));
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}