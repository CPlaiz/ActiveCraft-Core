package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BreakCommand extends ActiveCraftCommand {

    public BreakCommand() { super("break"); }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        if (args.length == 0) {
            checkPermission(sender, "break.self");
            Player player = getPlayer(sender);
            player.playSound(player.getLocation(), Sound.BLOCK_STONE_BREAK, 1f, 1f);
            player.getTargetBlock(null, 9999).setType(Material.AIR);
        } else {
            checkPermission(sender, "break.others");
            Player target = getPlayer(args[0]);
            checkTargetSelf(sender, target, "break.self");
            target.playSound(target.getLocation(), Sound.BLOCK_STONE_BREAK, 1f, 1f);
            target.getTargetBlock(null, 9999).setType(Material.AIR);
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String alias, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}