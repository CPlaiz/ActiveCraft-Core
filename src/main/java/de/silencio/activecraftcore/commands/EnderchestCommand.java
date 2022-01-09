package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EnderchestCommand implements CommandExecutor {

    public static ArrayList<UUID> enderchest = new ArrayList<>();

    public EnderchestCommand() {
        super("enderchest");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {

        if (args.length == 0) {
            checkPermission(sender, "enderchest.self");
            Player player = getPlayer(sender);
            player.openInventory(player.getEnderChest());
            player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1f, 1f);
            sendMessage(sender, CommandMessages.ENDERCHEST_OPEN());
        } else {
            checkPermission(sender, "enderchest.others");
            Player player = getPlayer(sender);
            Player target = getPlayer(args[0]);
            checkTargetSelf(sender, target, "enderchest.self");
            player.openInventory(target.getEnderChest());
            player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1f, 1f);
            sendMessage(sender, CommandMessages.ENDERCHEST_OPEN_OTHERS(target));
        }

    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}