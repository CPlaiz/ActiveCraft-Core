package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class HealCommand extends ActiveCraftCommand {

    public HealCommand() {
        super("heal");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        switch (args.length) {
            case 0 -> {
                checkPermission(sender, "heal.self");
                Player player = getPlayer(sender);
                player.setHealth(20);
                player.setFoodLevel(20);
                sendMessage(sender, CommandMessages.HEAL());
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.4f, 1f);
            }
            case 1 -> {
                checkPermission(sender, "heal.others");
                Player target = getPlayer(args[0]);
                if (!checkTargetSelf(sender, target, "heal.self")) sendSilentMessage(target, CommandMessages.HEAL_OTHERS_MESSAGE(sender));
                target.setHealth(20);
                target.setFoodLevel(20);
                sendMessage(sender, CommandMessages.HEAL_OTHERS(target));
                target.sendMessage(CommandMessages.HEAL_OTHERS_MESSAGE(sender));
                target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.4f, 1f);
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String alias, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}