package de.silencio.activecraftcore.messages;

import com.sun.jna.platform.unix.solaris.LibKstat;
import de.silencio.activecraftcore.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.junit.Assert;

@SuppressWarnings("ALL")
public class CommandMessages {

    static ActiveCraftMessage acm = Main.getPlugin().getActiveCraftMessage();

    private boolean isNull(Player target) {
        if (target == null) {
            return true;
        } else return false;
    }

    // BAN
    public static String BAN_HEADER(Player target, CommandSender sender) {

        String msg = "";

        if (target != null) {
            msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BAN, "ban-header")
                    .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                    .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                    .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        } else {
            msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BAN, "ban-header")
                    .replace("%t_playername%", ChatColor.RED + "%t_playername% cannot be used here!")
                    .replace("%t_displayname%", ChatColor.RED + "%t_playername% cannot be used here!")
                    .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        }
        if (sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String BAN_COMPLETED_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BAN, "ban-completed-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String BAN_CANCELLED_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BAN, "ban-cancelled-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String BAN_ENTER_REASON(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BAN, "ban-enter-reason")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String BAN_ENTER_TIME(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BAN, "ban-enter-time")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String ALREAEDY_BANNED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BAN, "already-banned")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // UNBAN
    public static String NOT_BANNED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.UNBAN, "not-banned")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String UNBANNED_PLAYER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.UNBAN, "unbanned-player")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // BANLIST
    public static String BANLIST_HEADER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BANLIST, "banlist-header")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String BAN_ON_HOVER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BANLIST, "ban-on-hover")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String IP_BAN_ON_HOVER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BANLIST, "ip-ban-on-hover")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String NO_BANS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BANLIST, "no-bans")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // IPBAN
    public static String IPBAN_HEADER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.IPBAN, "ipban-header")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String IPBAN_COMPLETED_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.IPBAN, "ipban-completed-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String IPBAN_CANCELLED_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.IPBAN, "ipban-cancelled-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String IPBAN_ENTER_REASON(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.IPBAN, "ipban-enter-reason")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String IPBAN_ENTER_TIME(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.IPBAN, "ipban-enter-time")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String INVALID_IP(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.IPBAN, "invalid-ip")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String IP_ALREADY_BANNED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.IPBAN, "ip-already-banned")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // UNBANIP
    public static String UNBANNED_IP(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.UNBAN_IP, "unbanned-ip")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String IP_NOT_BANNED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.UNBAN_IP, "ip-not-banned")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // BOOK
    public static String CHANGE_TITLE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "changed-title")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String CHANGED_AUTHOR(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "changed-author")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String CHANGED_PAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "changed-page")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String CHANGED_GENERATION(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "changed-generation")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String ORIGINAL(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "original")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String COPY_ORIGINAL(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "copy-original")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String COPY_COPY(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "copy-copy")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TATTERED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "tattered")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String ADDED_PAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "added-page")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String NOT_HOLDING_BOOK(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "not-holding-book")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // BREAK
    public static String BREAK_SELF(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BREAK, "break-self")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String BREAK_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BREAK, "break-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // BROADCAST
    public static String BROADCAST_PREFIX(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BROADCAST, "broadcast-prefix")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String INCLUDE_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BROADCAST, "include-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // BUTCHER
    public static String NO_MOBS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BUTCHER, "no-mobs")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String KILLED_MOBS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BUTCHER, "killed-mobs")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // CLEARINVENTORY
    public static String CLEARED_SELF(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.CLEARINVENTORY, "cleared-self")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String CLEARED_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.CLEARINVENTORY, "cleared-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String CLEADED_OTHERS_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.CLEARINVENTORY, "cleared-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // COLORNICK
    public static String COLORNICK_COMPLETE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.COLORNICK, "colornick-complete")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String COLORNICK_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.COLORNICK, "colornick-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String COLORNICK_OTHERS_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.COLORNICK, "colornick-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // HOME
    public static String TELEPORT_HOME_COMPLETE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.HOME, "teleport-home-complete")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String HOME_NOT_SET(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.HOME, "home-not-set")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TELEPORT_HOME_OTHERS_COMPETE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.HOME, "teleport-home-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String HOME_OTHERS_NOT_SET(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.HOME, "home-others-not-set")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // SETHOME
    public static String HOME_SET(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SETHOME, "home-set")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String HOME_OTHERS_SET(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SETHOME, "home-others-set")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // DELETEHOME
    public static String HOME_DELETED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.DELETEHOME, "home-deleted")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String NO_HOME(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.DELETEHOME, "no-home")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String HOME_OTHERS_DELETED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.DELETEHOME, "home-others-deleted")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // EDITSIGN
    public static String ENABLED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.EDITSIGN, "enabled")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String DISABLED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.EDITSIGN, "disabled")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String ENABLED_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.EDITSIGN, "enabled-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String ENABLED_OTHERS_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.EDITSIGN, "enabled-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String DISABLED_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.EDITSIGN, "disabled-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String DISABLED_OTHERS_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.EDITSIGN, "disabled-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // ENCHANT
    public static String APPLIED_ENCHANTMENT(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "applied-enchantment")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String APPLIED_ENCHANTMENT_LEVEL(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "applied-enchantment-level")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String CANNOT_BE_APPLIED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "cannot-be-applied")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String CLEARED_ALL_ENCHANTMENTS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "cleared-all-enchantments")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String NOT_ENCHANTED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "not-enchanted")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String GLINT_TRUE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "glint-true")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String GLINT_FALSE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "glint-false")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // ENDERCHEST
    public static String ENDERCHEST_OPEN(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENDERCHEST, "enderchest-open")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String ENDERCHEST_OPEN_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENDERCHEST, "enderchest-open-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // FEED
    public static String FEED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FEED, "feed")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String FEED_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FEED, "feed-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String FEED_OTHERS_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FEED, "feed-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // FLY
    public static String ENABLE_FLY(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLY, "enable-fly")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String ENABLE_FLY_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLY, "enable-fly-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String ENABLE_FLY_OTHERS_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLY, "enable-fly-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String DISABLE_FLY(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLY, "disable-fly")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String DISABLE_FLY_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLY, "disable-fly-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String DISABLED_FLY_OTHERS_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLY, "disable-fly-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // FLYSPEED
    public static String FLYSPEED_SET(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLYSPEED, "flyspeed-set")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String FLYSPEED_SET_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLYSPEED, "flyspeed-set-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String FLYSPEED_SET_OTHERS_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLYSPEED, "flyspeed-set-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // GOD
    public static String ENABLE_GOD(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.GOD, "enable-god")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String ENABLE_GOD_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.GOD, "enable-god-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String ENABLE_GOD_OTHERS_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.GOD, "enable-god-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String DISABLE_GOD(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.GOD, "disable-god")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String DISABLE_GOD_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.GOD, "disable-god-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String DISABLE_GOD_OTHERS_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.GOD, "disable-god-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // HOT
    public static String HAT_SUCCESS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.HAT, "hat-success")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // HEAL
    public static String HEAL(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.HEAL, "heal")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String HEAL_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.HEAL, "heal-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String HEAL_OTHERS_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.HEAL, "heal-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // ITEM
    public static String ITEM_RENAMED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ITEM, "item-renamed")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // KICKALL
    public static String DEFAULT_KICKALL(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICKALL, "default-kickall")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String DEFAULT_KICKALL_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICKALL, "default-kickall-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String CUSTOM_KICKALL(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICKALL, "custom-kickall")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String CUSTOM_KICKALL_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICKALL, "custom-kickall-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // KICK
    public static String DEFAULT_KICK(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICK, "default-kick")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String DEFAULT_KICK_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICK, "default-kick-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String CUSTOM_KICK(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICK, "custom-kick")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String CUSTOM_KICK_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICK, "custom-kick-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // KNOCKBACKSTICK
    public static String KNOCKBACKSTICK(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KNOCKBACKSTICK, "knockbackstick")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String KNOCKBACKSTICK_LORE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KNOCKBACKSTICK, "knockbackstick-lore")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String KNOCKBACKSTICK_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KNOCKBACKSTICK, "knockbackstick-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String KNOCKBACKSTICK_OTHERS_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KNOCKBACKSTICK, "knockbackstick-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // KNOWNIPS
    public static String KNOWNIPS_HEADER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KNOWNIPS, "knownips-header")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // LASTCOORDS
    public static String NEVER_ENTERED_WORLD(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LASTCOORDS, "never-entered-world")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String NEVER_QUIT_SERVER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LASTCOORDS, "never-quit-server")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String LASTCOORDS_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LASTCOORDS, "lastcoords-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // LASTONLINE
    public static String LASTONLINE_ONLINE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LASTONLINE, "lastonline-online")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String LASTONLINE_OFFLINE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LASTONLINE, "lastonline-offline")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // LEATHERCOLOR
    public static String NO_LEATHER_ITEM(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LEATHERCOLOR, "no-leather-item")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // LOCKDOWN
    public static String LOCKDOWN_ENABLED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "lockdown-enabled")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String LOCKDOWN_ALREADY_ENABLED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "lockdown-already-enabled")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String LOCKDOWN_DISABLED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "lockdown-disabled")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String LOCKDOWN_NOT_ENABLED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "lockdown-not-enabled")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String ALLOW_PLAYER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "allow-player")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String ALLOW_PLAYER_EXTRA(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "allow-player-extra")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String ALLOW_PLAYER_ALREADY_ENABLED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "allow-player-already-enabled")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String DISALLOW_PLAYER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "disallow-player")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String DISALLOW_PLAYER_EXTRA(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "disallow-player-extra")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String DISALLOW_PLAYER_ALREADY_DISABLED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "disallow-player-already-disabled")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // LOG
    public static String LOG_PREFIX(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOG, "log-prefix")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String ENABLE_LOG(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOG, "enable-log")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String DISABLE_LOG(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOG, "disable-log")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // MORE
    public static String CANNOT_STACK(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MORE, "cannot-stack")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // MSG
    public static String MSG_PREFIX_TO(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MSG, "msg-prefix-to")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String MSG_PREFIX_FROM(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MSG, "msg-prefix-from")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String SOCIALSPY_PREFIX_TO(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MSG, "socialspy-prefix-to")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String SOCIALSPY_PREFIX_FROM(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MSG, "socialspy-prefix-from")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String CONSOLE_MSG_PREFIX(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MSG, "console-msg-prefix")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String CANNOT_MESSAGE_SELF(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MSG, "cannot-message-self")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String INCLUDE_PLAYER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MSG, "include-player")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // MUTE
    public static String MUTE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MUTE, "mute")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String MUTE_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MUTE, "mute-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String ALREADY_MUTED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MUTE, "already-muted")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // UNMUTE
    public static String UNMUTE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.UNMUTE, "unmute")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String UNMUTE_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.UNMUTE, "unmute-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String NOT_MUTED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.UNMUTE, "not-muted")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // NICK
    public static String NICK_SET(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.NICK, "nick-set")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String NICK_SET_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.NICK, "nick-set-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String NICK_SET_OTHERS_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.NICK, "nick-set-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // PING
    public static String PING_PLAYER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PING, "ping-player")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String PING_CONSOLE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PING, "ping-console")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // PLAYERLIST
    public static String PLAYERLIST_HEADER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PLAYERLIST, "playerlist-header")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // PLAYTIME
    public static String PLAYTIME(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PLAYTIME, "playtime")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String PLAYTIME_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PLAYTIME, "playtime-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // PORTAL
    public static String PORTAL_CREATED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PORTAL, "portal-created")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String PORTAL_DESTROYED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PORTAL, "portal-destroyed")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String PORTAL_LIST(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PORTAL, "portal-list")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String PORTAL_NO_LIST(Player target, CommandSender sender) {

    String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PORTAL, "portal-no-list")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // QUICKGIVE
    public static String QUICKGIVE_GIVE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.QUICKGIVE, "quickgive-give")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String QUICKGIVE_AMOUNT(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.QUICKGIVE, "quickgive-amount")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // RAM
    public static String RAM(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.RAM, "ram")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // REALNAME
    public static String REALNAME_HEADER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.REALNAME, "realname-header")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // REPAIR
    public static String REPAIR_SUCCESS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.REPAIR, "repair-success")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String CANNOT_BE_REPAIRED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.REPAIR, "cannot-be-repaired")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // RESTARTSERVER
    public static String RESTART_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.RESTARTSERVER, "restart-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String RESTART_TITLE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.RESTARTSERVER, "restart-title")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // SKULL
    public static String GIVE_SKULL(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SKULL, "give-skull")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String GIVE_SKULL_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SKULL, "give-skull-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String GIVE_SKULL_OTHERS_MULTIPLE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SKULL, "give-skull-others-multiple")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // SPAWN
    public static String SPAWN_TELEPORT(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SPAWN, "spawn-teleport")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String SPAWN_TELEPORT_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SPAWN, "spawn-teleport-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String SPAWN_TELEPORT_OTHERS_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SPAWN, "spawn-teleport-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // SUMMON
    public static String SUMMON(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUMMON, "summon")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String SUMMON_MULTIPLE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUMMON, "summon-multiple")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String SUMMON_OTHERS_MULTIPLE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUMMON, "summon-others-multiple")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // STAFFCHAT
    public static String STAFFCHAT_PREFIX(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.STAFFCHAT, "staffchat-prefix")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String STAFFCHAT_FROM_CONSOLE_PREFIX(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.STAFFCHAT, "staffchat-from-console-prefix")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // STRIKE
    public static String STRIKE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.STRIKE, "strike")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if (sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String STRIKE_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.STRIKE, "strike-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // SUICIDE
    public static String SUICIDE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUICIDE, "suicide")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String BROADCAST_SUICIDE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUICIDE, "broadcast-suicide")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String SUICIDE_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUICIDE, "suicide-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // TOP
    public static String TOP_TELEPORT(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TOP, "top-teleport")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TOP_NOT_SAFE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TOP, "top-not-safe")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TOP_TELEPORT_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TOP, "top-teleport-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // TPA
    public static String TPA_REQUEST_TO(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPA, "tpa-request-to")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TPA_REQUEST_FROM(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPA, "tpa-request-from")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TPA_ACCEPT(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPA, "tpa-accept")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TPA_ACCEPT_HOVER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPA, "tpa-accept-hover")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TPA_DENY(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPA, "tpa-deny")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TPA_DENY_HOVER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPA, "tpa-deny-hover")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // TPACCEPT
    public static String TPACCEPT_ACTIONBAR(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPACCEPT, "tpaccept-actionbar")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TPACCEPT_RECIEVER_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPACCEPT, "tpaccept-reciever-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TPACCEPT_SENDER_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPACCEPT, "tpaccept-sender-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String NO_REQUESTS_ACCEPT(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPACCEPT, "no-requests-accept")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // TPADENY
    public static String TPADENY_RECIEVER_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPADENY, "tpadeny-reciever-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TPADENY_SENDER_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPADENY, "tpadeny-sender-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String NO_REQUESTS_DENY(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPADENY, "no-requests-deny")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    //TPALL
    public static String TPALL(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPALL, "tpall")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TPALL_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPALL, "tpall-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TPALL_EXEPT(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPALL, "tpall-exept")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // TELEPORT
    public static String CANNOT_TP_TO_SELF_TELEPORT(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TELEPORT, "cannot-tp-to-self-teleport")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String CANNOT_TP_OTHERS_TO_THEMSELF(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TELEPORT, "cannot-tp-others-to-themself")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TELEPORT_TO_PLAYER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TELEPORT, "teleport-to-player")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TELEPORT_TO_COORDS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TELEPORT, "teleport-to-coords")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TELEPORT_PLAYER_TO_PLAYER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TELEPORT, "teleport-player-to-player")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TELEPORT_PLAYER_TO_COORDS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TELEPORT, "teleport-player-to-coords")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // TPHERE
    public static String CANNOT_TP_TO_SELF_TPHERE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPHERE, "cannot-tp-to-self-tphere")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TELEPORT_PLAYER_TO_YOU(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPHERE, "teleport-player-to-you")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // VANISH
    public static String NOW_VISIBLE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.VANISH, "now-visible")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String NOW_INVISIBLE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.VANISH, "now-invisible")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String NOW_INVISIBLE_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.VANISH, "now-invisible-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String NOW_VISIBLE_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.VANISH, "now-visible-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // VERIFY
    public static String DEFAULT_MUTE_REMOVE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.VERIFY, "default-mute-remove")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String DEFAULT_MUTE_REMOVE_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.VERIFY, "default-mute-remove-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String NOT_DEFAULT_MUTED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.VERIFY, "not-default-muted")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // WALKSPEED
    public static String WALKSPEED_SET(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WALKSPEED, "walkspeed-set")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String WALKSPEED_SET_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WALKSPEED, "walkspeed-set-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String WALKSPEED_SET_OTHERS_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WALKSPEED, "walkspeed-set-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // WARN
    public static String DEFAULT_WARN_REASON(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "default-warn-reason")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String WARN_ADD(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warn-add")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String WARN_REMOVE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warn-remove")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String WARM_GET_HEADER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warn-get-header")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String WARN_GET(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warn-get")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String WARNED_HEADER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warned-header")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String WARNED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warned")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String WARNED_REMOVE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warned-remove")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    //WARP
    public static String WARP_DOESNT_EXIST(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARP, "warp-doesnt-exist")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String WARP_TELEPORT(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARP, "warp-teleport")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String WARP_TELEPORT_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARP, "warp-teleport-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String WARP_TELEPORT_OTHERS_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARP, "warp-teleport-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // SETWARP
    public static String ALREADY_EXISTS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SETWARP, "already-exists")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String WARP_SET(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SETWARP, "warp-set")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // DELETEWARP
    public static String DOESNT_EXIST(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.DELETEWARP, "doesnt-exist")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String WARP_DELETED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.DELETEWARP, "warp-deleted")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // WARPS
    public static String WARPS_HEADER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARPS, "warps-header")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String NO_WARPS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARPS, "no-warps")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // WEATHER
    public static String TO_THUNDER(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WEATHER, "to-thunder")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TO_RAIN(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WEATHER, "to-rain")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String TO_CLEAR(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WEATHER, "to-clear")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // WHEREAMI
    public static String WHEREAMI(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHEREAMI, "whereami")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String WHEREAMI_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHEREAMI, "whereami_others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // WHOIS
    public static String NAME(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "name")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String NICKNAME(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "nickname")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String COLORNICK(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "colornick")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String UUID(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "uuid")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String IS_OP(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "is-op")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String HEALTH(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "health")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String FOOD(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "food")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String WORLD(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "world")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String COORDS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "coords")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String AFK(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "afk")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String CLIENT(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "client")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String ADDRESS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "address")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String GAMEMODE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "gamemode")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String MUTED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "muted")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String WHITELISTED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "whitelisted")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String GOD(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "god")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String VANISHED(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "vanished")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }

    // XP
    public static String XP_LEVELS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-levels")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String XP_XP(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-xp")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String XP_LEVELS_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-levels-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String XP_LEVELS_OTHERS_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-levels-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String XP_XP_OTHERS(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-xp-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
    public static String XP_XP_OTHERS_MESSAGE(Player target, CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-xp-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                        .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                                .replace("%s_playername%", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;

        if(sender instanceof Player) {
            msg = msg.replace("s_displayname", ChatColor.AQUA + ((Player) sender).getDisplayName()) + ChatColor.GOLD;
        } else msg = msg.replace("s_displayname", ChatColor.AQUA + sender.getName()) + ChatColor.GOLD;
        return msg;
    }
}