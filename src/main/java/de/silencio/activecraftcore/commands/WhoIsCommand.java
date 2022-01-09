package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class WhoIsCommand extends ActiveCraftCommand {

    public WhoIsCommand() {
        super("whois");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        if (args.length == 0) {
            checkPermission(sender, "whois.self");
            Player player = getPlayer(sender);
            Profile profile = getProfile(player);
            sendMessage(sender, (
                        CommandMessages.NAME(player.getName()) + "\n"
                            + CommandMessages.NICKNAME(player.getDisplayName()) + "\n"
                            + CommandMessages.COLORNICK(profile.getColorNick().name().toLowerCase()) + "\n"
                            + CommandMessages.UUID(player.getUniqueId().toString()) + "\n"
                            + CommandMessages.IS_OP(player.isOp() + "") + "\n"
                            + CommandMessages.HEALTH(Math.round(player.getHealth()) + "") + "\n"
                            + CommandMessages.FOOD(player.getFoodLevel() + "") + "\n"
                            + CommandMessages.WORLD(player.getWorld().getName()) + "\n"
                            + CommandMessages.COORDS(ChatColor.GOLD + "X: "
                            + ChatColor.AQUA + player.getLocation().getBlockX() + ChatColor.GOLD + ", Y: "
                            + ChatColor.AQUA + player.getLocation().getBlockY() + ChatColor.GOLD + ", Z: "
                            + ChatColor.AQUA + player.getLocation().getBlockZ())+ "\n"
                            + CommandMessages.AFK(String.valueOf(profile.isAfk())) + "\n"
                            + CommandMessages.CLIENT(player.getClientBrandName()) + "\n"
                            + CommandMessages.ADDRESS(player.getAddress().toString().replace("/", "")) + "\n"
                            + CommandMessages.GAMEMODE(player.getGameMode().name().toLowerCase()) + "\n"
                            + CommandMessages.MUTED(String.valueOf(profile.isMuted())) + "\n"
                            + CommandMessages.WHITELISTED(player.isWhitelisted() + "") + "\n"
                            + CommandMessages.GOD(String.valueOf(profile.isGodmode())) + "\n"
                            + CommandMessages.VANISHED(String.valueOf(profile.isVanished()))
            ));
        } else if (args.length == 1) {
            checkPermission(sender, "whois.others");
            Player target = getPlayer(args[0]);
            checkTargetSelf(sender, target, "whois.self");
            Profile profile = getProfile(target);
            sendMessage(sender, (
                        CommandMessages.NAME(target.getName()) + "\n"
                            + CommandMessages.NICKNAME(target.getDisplayName()) + "\n"
                            + CommandMessages.COLORNICK(profile.getColorNick().name().toLowerCase()) + "\n"
                            + CommandMessages.UUID(target.getUniqueId().toString()) + "\n"
                            + CommandMessages.IS_OP(target.isOp() + "") + "\n"
                            + CommandMessages.HEALTH(Math.round(target.getHealth()) + "") + "\n"
                            + CommandMessages.FOOD(target.getFoodLevel() + "") + "\n"
                            + CommandMessages.COORDS(ChatColor.GOLD + "X: "
                            + ChatColor.AQUA + target.getLocation().getBlockX() + ChatColor.GOLD + ", Y: "
                            + ChatColor.AQUA + target.getLocation().getBlockY() + ChatColor.GOLD + ", Z: "
                            + ChatColor.AQUA + target.getLocation().getBlockZ()) + "\n"
                            + CommandMessages.AFK(String.valueOf(profile.isAfk())) + "\n"
                            + CommandMessages.CLIENT(target.getClientBrandName()) + "\n"
                            + CommandMessages.ADDRESS(target.getAddress().toString().replace("/", "")) + "\n"
                            + CommandMessages.GAMEMODE(target.getGameMode().name().toLowerCase()) + "\n"
                            + CommandMessages.MUTED(String.valueOf(profile.isMuted())) + "\n"
                            + CommandMessages.WHITELISTED(target.isWhitelisted() + "") + "\n"
                            + CommandMessages.GOD(String.valueOf(profile.isGodmode())) + "\n"
                            + CommandMessages.VANISHED(String.valueOf(profile.isVanished()))
            ));
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}