package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class WhereAmICommand extends ActiveCraftCommand {

    public WhereAmICommand() {
        super("whereami");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        if (args.length == 0) {
            checkPermission(sender, "whereami.self");
            Player player = getPlayer(sender);
            sendMessage(sender, CommandMessages.WHEREAMI(  ChatColor.GOLD + "x" + ChatColor.AQUA + player.getLocation().getBlockX() + ChatColor.GOLD
                    + " y" + ChatColor.AQUA + player.getLocation().getBlockY() + ChatColor.GOLD +
                    " z" + ChatColor.AQUA + player.getLocation().getBlockZ(), player.getWorld().getName()));
        } else if (args.length == 1) {
            checkPermission(sender, "whereami.others");
            Player target = getPlayer(args[0]);
            checkTargetSelf(sender, target, "whereami.self");
            sendMessage(sender, CommandMessages.WHEREAMI_OTHERS(target,
                    ChatColor.GOLD + "x" + ChatColor.AQUA + target.getLocation().getBlockX() + ChatColor.GOLD
                            + " y" + ChatColor.AQUA + target.getLocation().getBlockY() + ChatColor.GOLD +
                            " z" + ChatColor.AQUA + target.getLocation().getBlockZ(), target.getWorld().getName()));
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}