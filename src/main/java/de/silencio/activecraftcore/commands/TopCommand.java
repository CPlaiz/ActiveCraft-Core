package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TopCommand extends ActiveCraftCommand {

    public TopCommand() {
        super("top");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        if (args.length == 0) {
            checkPermission(sender, "top.self");
            Player player = getPlayer(sender);
            int xBlock = player.getLocation().getBlockX();
            int zBlock = player.getLocation().getBlockZ();
            double x = player.getLocation().getX();
            double z = player.getLocation().getZ();
            Location loc = new Location(player.getWorld(), x, player.getWorld().getHighestBlockYAt(xBlock, zBlock), z, player.getLocation().getYaw(), player.getLocation().getPitch());
            if (loc.getBlock().getType() != Material.LAVA) {
                loc.setY(loc.getBlockY() + 1);
                player.teleport(loc);
                sendMessage(sender, CommandMessages.TOP_TELEPORT());
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
            } else sendMessage(sender, Errors.WARNING() + CommandMessages.TOP_NOT_SAFE());
        } else if (args.length == 1) {
            checkPermission(sender, "top.others");
            Player target = getPlayer(args[0]);
            if (!checkTargetSelf(sender, target, "top.self")) sendSilentMessage(target, CommandMessages.TOP_TELEPORT_OTHERS_MESSAGE(sender));
            int xBlock = target.getLocation().getBlockX();
            int zBlock = target.getLocation().getBlockZ();
            double x = target.getLocation().getX();
            double z = target.getLocation().getZ();
            Location loc = new Location(target.getWorld(), x, target.getWorld().getHighestBlockYAt(xBlock, zBlock), z, target.getLocation().getYaw(), target.getLocation().getPitch());
            if (loc.getBlock().getType() != Material.LAVA) {
                loc.setY(loc.getBlockY() + 1);
                target.teleport(loc);
                sendMessage(sender, CommandMessages.TOP_TELEPORT_OTHERS(target));
                target.playSound(target.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
            } else sendMessage(sender, Errors.WARNING() + CommandMessages.TOP_NOT_SAFE());
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}