package de.silencio.activecraftcore.messages;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMessages {

    static ActiveCraftMessage acm = Main.getPlugin().getActiveCraftMessage();

    // BANSCREEN
    public static String BAN_TITLE() {

        String msg = ChatColor.RED + acm.getMessage(MessageType.COMMAND, CommandType.BANSCREEN, "ban-title");
        return msg;
    }

    public static String BAN_IP_TITLE() {

        String msg = ChatColor.RED + acm.getMessage(MessageType.COMMAND, CommandType.BANSCREEN, "ban-ip-title");
        return msg;
    }

    public static String BAN_EXPIRATION(String time) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BANSCREEN, "ban-expiration")
                .replace("%time%", ChatColor.AQUA + time + ChatColor.GOLD);
        return msg;
    }

    public static String BAN_EXPIRATION_PERMANENT() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BANSCREEN, "ban-expiration-permanent");
        return msg;
    }

    public static String BAN_REASON(String reason) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BANSCREEN, "ban-reason")
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD);
        return msg;
    }

    // BAN
    public static String BAN_HEADER(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BAN, "ban-header")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String BAN_COMPLETED_MESSAGE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BAN, "ban-completed-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String BAN_CANCELLED_MESSAGE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BAN, "ban-cancelled-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String BAN_ENTER_REASON() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BAN, "ban-enter-reason");
        return msg;
    }

    public static String BAN_ENTER_TIME() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BAN, "ban-enter-time");
        return msg;
    }

    public static String ALREAEDY_BANNED() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BAN, "already-banned");
        return msg;
    }

    // UNBAN
    public static String NOT_BANNED() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.UNBAN, "not-banned");
        return msg;
    }

    public static String UNBANNED_PLAYER(String target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.UNBAN, "unbanned-player")
                .replace("%t_playername%", ChatColor.AQUA + target + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target + ChatColor.GOLD);
        return msg;
    }

    // BANLIST
    public static String BANLIST_HEADER() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BANLIST, "banlist-header");
        return msg;
    }

    public static String UNBAN_ON_HOVER(String target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BANLIST, "unban-on-hover")
                .replace("%t_playername%", ChatColor.AQUA + target + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target + ChatColor.GOLD);
        return msg;
    }

    public static String UNBAN_IP_ON_HOVER(String ip) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BANLIST, "unban-ip-on-hover")
                .replace("%ip%", ChatColor.AQUA + ip + ChatColor.GOLD);
        return msg;
    }

    public static String NO_BANS() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BANLIST, "no-bans");
        return msg;
    }

    // IPBAN
    public static String IPBAN_HEADER(Player target, String ip) {

            String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.IPBAN, "ipban-header")
                    .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                    .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                    .replace("%ip%", ChatColor.AQUA + ip + ChatColor.GOLD);
        return msg;
    }

    public static String IPBAN_COMPLETED_MESSAGE(Player target, String ip) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.IPBAN, "ipban-completed-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%ip%", ChatColor.AQUA + ip + ChatColor.GOLD);
        return msg;
    }

    public static String IPBAN_CANCELLED_MESSAGE(Player target, String ip) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.IPBAN, "ipban-cancelled-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%ip%", ChatColor.AQUA + ip + ChatColor.GOLD);
        return msg;
    }

    public static String IPBAN_ENTER_REASON() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.IPBAN, "ipban-enter-reason");
        return msg;
    }

    public static String IPBAN_ENTER_TIME() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.IPBAN, "ipban-enter-time");
        return msg;
    }

    public static String INVALID_IP() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.IPBAN, "invalid-ip");
        return msg;
    }

    public static String IP_ALREADY_BANNED() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.IPBAN, "ip-already-banned");
        return msg;
    }

    // UNBANIP
    public static String UNBANNED_IP(String ip) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.UNBAN_IP, "unbanned-ip")
                .replace("%ip%", ChatColor.AQUA + ip + ChatColor.GOLD);
        return msg;
    }

    public static String IP_NOT_BANNED() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.UNBAN_IP, "ip-not-banned");
        return msg;
    }

    // BACK
    public static String TELEPORTED_BACK() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BACK, "teleported-back");
        return msg;
    }

    public static String NO_RETURN_LOCATION() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BACK, "no-return-location");
        return msg;
    }

    public static String TELEPORTED_BACK_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BACK, "teleported-back")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String NO_RETURN_LOCATION_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BACK, "no-return-location-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    // BOOK
    public static String CHANGED_TITLE(String title) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "changed-title")
                .replace("%title%", ChatColor.AQUA + title + ChatColor.GOLD);
        return msg;
    }

    public static String CHANGED_AUTHOR(String author) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "changed-author")
                .replace("%author%", ChatColor.AQUA + author + ChatColor.GOLD);
        return msg;
    }

    public static String CHANGED_PAGE(String page) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "changed-page")
                .replace("%author%", ChatColor.AQUA + page + ChatColor.GOLD);
        return msg;
    }

    public static String CHANGED_GENERATION(String generation) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "changed-generation")
                .replace("%generation%", ChatColor.AQUA + generation + ChatColor.GOLD);
        return msg;
    }

    public static String ORIGINAL() {

        String msg = ChatColor.AQUA + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "original");
        return msg;
    }

    public static String COPY_ORIGINAL() {

        String msg = ChatColor.AQUA + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "copy-original");
        return msg;
    }

    public static String COPY_COPY() {

        String msg = ChatColor.AQUA + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "copy-copy");
        return msg;
    }

    public static String TATTERED() {

        String msg = ChatColor.AQUA + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "tattered");
        return msg;
    }

    public static String ADDED_PAGE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "added-page");
        return msg;
    }

    public static String NOT_HOLDING_BOOK() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "not-holding-book");
        return msg;
    }

    // BREAK
    public static String BREAK_SELF() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BREAK, "break-self");
        return msg;
    }

    public static String BREAK_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BREAK, "break-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    // BROADCAST
    public static String BROADCAST_PREFIX() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BROADCAST, "broadcast-prefix");
        return msg;
    }

    public static String INCLUDE_MESSAGE() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.BROADCAST, "inlcude-message");
        return msg;
    }

    // BUTCHER
    public static String NO_MOBS() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.BUTCHER, "no-mobs");
        return msg;
    }

    public static String KILLED_MOBS(String amount) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BUTCHER, "killed-mobs")
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
        return msg;
    }

    // CLEARINVENTORY
    public static String CLEARED_SELF() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.CLEARINVENTORY, "cleared-self");
        return msg;
    }

    public static String CLEARED_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.CLEARINVENTORY, "cleared-others")
                .replace("%t_displayname%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String CLEARED_OTHERS_MESSAGE(CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.CLEARINVENTORY, "cleared-others-message");

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    // COLORNICK
    public static String COLORNICK_SELF(String color) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.COLORNICK, "colornick-complete")
                .replace("%color%", color + ChatColor.GOLD);
        return msg;
    }

    public static String COLORNICK_OTHERS(Player target, String color) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.COLORNICK, "colornick-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%color%", color + ChatColor.GOLD);
        return msg;
    }

    public static String COLORNICK_OTHERS_MESSAGE(CommandSender sender, String color) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.COLORNICK, "colornick-others-message")
                .replace("%color%", color + ChatColor.GOLD);

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    // HOME
    public static String TELEPORT_HOME_COMPLETE(String home) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.HOME, "teleport-home-complete")
                .replace("%home%", ChatColor.AQUA + home + ChatColor.GOLD);
        return msg;
    }

    public static String HOME_NOT_SET(String home) {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.HOME, "home-not-set")
                .replace("%home%", ChatColor.AQUA + home + ChatColor.GRAY);
        return msg;
    }

    public static String TELEPORT_HOME_OTHERS_COMPLETE(Player target, String home) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.HOME, "teleport-home-others-complete")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%home%", ChatColor.AQUA + home + ChatColor.GOLD);
        return msg;
    }

    public static String HOME_OTHERS_NOT_SET(Player target, String home) {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.HOME, "home-others-not-set")
                .replace("%t_playername%", ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY)
                .replace("%t_displayname%", ChatColor.DARK_AQUA + target.getDisplayName() + ChatColor.GRAY)
                .replace("%home%", ChatColor.AQUA + home + ChatColor.GRAY);
        return msg;
    }

    // SETHOME
    public static String HOME_SET(String home) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SETHOME, "home-set")
                .replace("%home%", ChatColor.AQUA + home + ChatColor.GOLD);
        return msg;
    }

    public static String HOME_OTHERS_SET(Player target, String home) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SETHOME, "home-others-set")
                .replace("%t_displayname%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%home%", ChatColor.AQUA + home + ChatColor.GOLD);
        return msg;
    }

    public static String MAX_HOMES() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.SETHOME, "max-homes");
        return msg;
    }

    public static String MAX_HOMES_OTHERS() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.SETHOME, "max-homes-others");
        return msg;
    }

    // DELETEHOME
    public static String HOME_DELETED(String home) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.DELETEHOME, "home-deleted")
                .replace("%home%", ChatColor.AQUA + home + ChatColor.GOLD);
        return msg;
    }

    public static String NO_HOME(String home) {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.DELETEHOME, "no-home")
                .replace("%home%", ChatColor.AQUA + home + ChatColor.GRAY);
        return msg;
    }

    public static String HOME_OTHERS_DELETED(Player target, String home) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.DELETEHOME, "home-others-deleted")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%home%", ChatColor.AQUA + home + ChatColor.GOLD);
        return msg;
    }

    public static String NO_HOME_OTHERS(Player target, String home) {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.DELETEHOME, "no-home-others")
                .replace("%t_playername%", ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY)
                .replace("%t_displayname%", ChatColor.DARK_AQUA + target.getDisplayName() + ChatColor.GRAY)
                .replace("%home%", ChatColor.AQUA + home + ChatColor.GRAY);
        return msg;
    }

    // EDITSIGN
    public static String ENABLED() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.EDITSIGN, "enabled");
        return msg;
    }

    public static String DISABLED() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.EDITSIGN, "disabled");
        return msg;
    }

    public static String ENABLED_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.EDITSIGN, "enabled-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String ENABLED_OTHERS_MESSAGE(CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.EDITSIGN, "enabled-others-message");

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String DISABLED_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.EDITSIGN, "disabled-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String DISABLED_OTHERS_MESSAGE(CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.EDITSIGN, "disabled-others-message");

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    // ENCHANT
    public static String APPLIED_ENCHANTMENT(String enchantment, String maxlevel) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "applied-enchantment")
                .replace("%enchantment%", ChatColor.AQUA + enchantment + ChatColor.GOLD)
                .replace("%maxlevel%", ChatColor.AQUA + maxlevel + ChatColor.GOLD);
        return msg;
    }

    public static String APPLIED_ENCHANTMENT_LEVEL(String enchantment, String level) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "applied-enchantment-level")
                .replace("%enchantment%", ChatColor.AQUA + enchantment + ChatColor.GOLD)
                .replace("%level%", ChatColor.AQUA + level + ChatColor.GOLD);
        return msg;
    }

    public static String CANNOT_BE_APPLIED() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "cannot-be-applied");
        return msg;
    }

    public static String CLEARED_ALL_ENCHANTMENTS() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "cleared-all-enchantments");
        return msg;
    }

    public static String NOT_ENCHANTED() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "not-enchanted");
        return msg;
    }

    public static String GLINT_TRUE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "glint-true");
        return msg;
    }

    public static String GLINT_FALSE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "glint-false");
        return msg;
    }

    // INVSEE
    public static String INVSEE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.INVSEE, "invsee")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    // ENDERCHEST
    public static String ENDERCHEST_OPEN() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENDERCHEST, "enderchest-open");
        return msg;
    }

    public static String ENDERCHEST_OPEN_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENDERCHEST, "enderchest-open-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    // FEED
    public static String FEED() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FEED, "feed");
        return msg;
    }

    public static String FEED_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FEED, "feed-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String FEED_OTHERS_MESSAGE(CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FEED, "feed-others-message");

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    // FLY
    public static String ENABLE_FLY() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLY, "enable-fly");
        return msg;
    }

    public static String ENABLE_FLY_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLY, "enable-fly-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String ENABLE_FLY_OTHERS_MESSAGE(CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLY, "enable-fly-others-message");

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String DISABLE_FLY() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLY, "disable-fly");
        return msg;
    }

    public static String DISABLE_FLY_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLY, "disable-fly-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String DISABLED_FLY_OTHERS_MESSAGE(CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLY, "disable-fly-others-message");

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    // FLYSPEED
    public static String FLYSPEED_SET(String amount) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLYSPEED, "flyspeed-set")
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
        return msg;
    }

    public static String FLYSPEED_SET_OTHERS(Player target, String amount) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLYSPEED, "flyspeed-set-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
        return msg;
    }

    public static String FLYSPEED_SET_OTHERS_MESSAGE(CommandSender sender, String amount) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLYSPEED, "flyspeed-set-others-message")
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    // GOD
    public static String ENABLE_GOD() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.GOD, "enable-god");
        return msg;
    }

    public static String ENABLE_GOD_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.GOD, "enable-god-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String ENABLE_GOD_OTHERS_MESSAGE(CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.GOD, "enable-god-others-message");

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String DISABLE_GOD() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.GOD, "disable-god");
        return msg;
    }

    public static String DISABLE_GOD_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.GOD, "disable-god-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String DISABLE_GOD_OTHERS_MESSAGE(CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.GOD, "disable-god-others-message");

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    // HAT
    public static String HAT_SUCCESS() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.HAT, "hat-success");
        return msg;
    }

    // HEAL
    public static String HEAL() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.HEAL, "heal");
        return msg;
    }

    public static String HEAL_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.HEAL, "heal-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String HEAL_OTHERS_MESSAGE(CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.HEAL, "heal-others-message");

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    // ITEM
    public static String ITEM_GIVE(String item) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ITEM, "item-give")
                .replace("%item%", ChatColor.AQUA + item + ChatColor.GOLD);
        return msg;
    }

    public static String ITEM_GIVE_MULTIPLE(String item, String amount) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ITEM, "item-give-multiple")
                .replace("%item%", ChatColor.AQUA + item + ChatColor.GOLD)
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
        return msg;
    }

    public static String ITEM_RENAMED() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ITEM, "item-renamed");
        return msg;
    }

    public static String ITEM_LORE_ADD() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ITEM, "item-lore-add");
        return msg;
    }

    public static String ITEM_LORE_CLEAR() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ITEM, "item-lore-clear");
        return msg;
    }

    public static String ITEM_LORE_SET() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ITEM, "item-lore-set");
        return msg;
    }

    // KICKALL
    public static String DEFAULT_KICKALL() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICKALL, "default-kickall");
        return msg;
    }

    public static String DEFAULT_KICKALL_MESSAGE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICKALL, "default-kickall-message");
        return msg;
    }

    public static String CUSTOM_KICKALL(String reason) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICKALL, "custom-kickall")
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD);
        return msg;
    }

    public static String CUSTOM_KICKALL_MESSAGE(String reason) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICKALL, "custom-kickall-message")
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD);
        return msg;
    }

    // KICK
    public static String DEFAULT_KICK(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICK, "default-kick")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String DEFAULT_KICK_MESSAGE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICK, "default-kick-message");
        return msg;
    }

    public static String CUSTOM_KICK(Player target, String reason) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICK, "custom-kick")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD);
        return msg;
    }

    public static String CUSTOM_KICK_MESSAGE(String reason) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICK, "custom-kick-message")
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD);
        return msg;
    }

    // KNOCKBACKSTICK
    public static String KNOCKBACKSTICK() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KNOCKBACKSTICK, "knockbackstick");
        return msg;
    }

    public static String KNOCKBACKSTICK_LORE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KNOCKBACKSTICK, "knockbackstick-lore");
        return msg;
    }

    public static String KNOCKBACKSTICK_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KNOCKBACKSTICK, "knockbackstick-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String KNOCKBACKSTICK_OTHERS_MESSAGE(CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KNOCKBACKSTICK, "knockbackstick-others-message");

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    // KNOWNIPS
    public static String KNOWNIPS_HEADER(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KNOWNIPS, "knownips-header")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    // LASTCOORDS
    public static String NEVER_ENTERED_WORLD() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.LASTCOORDS, "never-entered-world");
        return msg;
    }

    public static String NEVER_QUIT_SERVER() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.LASTCOORDS, "never-quit-server");
        return msg;
    }

    public static String LASTCOORDS_MESSAGE(String playername, String displayname, String coords, String world) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LASTCOORDS, "lastcoords-message")
                .replace("%t_playername%", ChatColor.AQUA + playername + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + displayname + ChatColor.GOLD)
                .replace("%world%", ChatColor.AQUA + world + ChatColor.GOLD)
                .replace("%coords%", ChatColor.AQUA + coords + ChatColor.GOLD);
        return msg;
    }

    // LASTONLINE
    public static String LASTONLINE_ONLINE(Player target, String lastonline) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LASTONLINE, "lastonline-online")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%lastonline%", ChatColor.GREEN + lastonline + ChatColor.GOLD);
        return msg;
    }

    public static String LASTONLINE_OFFLINE(String target, String lastonline) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LASTONLINE, "lastonline-offline")
                .replace("%t_playername%", ChatColor.AQUA + target + ChatColor.GOLD)
                .replace("%lastonline%", ChatColor.AQUA + lastonline + ChatColor.GOLD);
        return msg;
    }

    // LEATHERCOLOR
    public static String NO_LEATHER_ITEM() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.LEATHERCOLOR, "no-leather-item");
        return msg;
    }

    // LOCKDOWN
    public static String LOCKDOWN_ENABLED() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "lockdown-enabled");
        return msg;
    }

    public static String LOCKDOWN_ALREADY_ENABLED() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "lockdown-already-enabled");
        return msg;
    }

    public static String LOCKDOWN_DISABLED() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "lockdown-disabled");
        return msg;
    }

    public static String LOCKDOWN_NOT_ENABLED() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "lockdown-not-enabled");
        return msg;
    }

    public static String ALLOW_PLAYER(String target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "allow-player")
                .replace("%t_playername%", ChatColor.AQUA + target + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target + ChatColor.GOLD);
        return msg;
    }

    public static String ALLOW_PLAYER_EXTRA() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "allow-player-extra");
        return msg;
    }

    public static String ALLOW_PLAYER_ALREADY_ENABLED(String target) {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "allow-player-already-enabled")
                .replace("%t_playername%", ChatColor.AQUA + target + ChatColor.GRAY)
                .replace("%t_displayname%", ChatColor.AQUA + target + ChatColor.GRAY);
        return msg;
    }

    public static String DISALLOW_PLAYER(String target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "disallow-player")
                .replace("%t_playername%", ChatColor.AQUA + target + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target + ChatColor.GOLD);
        return msg;
    }

    public static String DISALLOW_PLAYER_EXTRA() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "disallow-player-extra");
        return msg;
    }

    public static String DISALLOW_PLAYER_ALREADY_DISABLED(String target) {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "disallow-player-already-disabled")
                .replace("%t_playername%", ChatColor.AQUA + target + ChatColor.GRAY)
                .replace("%t_displayname%", ChatColor.AQUA + target + ChatColor.GRAY);
        return msg;
    }

    // LOG
    public static String LOG_PREFIX(Player target, String command) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOG, "log-prefix")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%command%", ChatColor.AQUA + command + ChatColor.GOLD);
        return msg;
    }

    public static String ENABLE_LOG() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOG, "enable-log");
        return msg;
    }

    public static String DISABLE_LOG() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOG, "disable-log");
        return msg;
    }

    // MORE
    public static String CANNOT_STACK() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.MORE, "cannot-stack");
        return msg;
    }

    // MSG
    public static String MSG_PREFIX_TO(Player target, String message) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MSG, "msg-prefix-to")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%message%", ChatColor.WHITE + message + ChatColor.GOLD);
        return msg;
    }

    public static String MSG_PREFIX_FROM(Player sender, String message) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MSG, "msg-prefix-from")
                .replace("%message%", ChatColor.WHITE + message + ChatColor.GOLD);

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String SOCIALSPY_PREFIX_TO(Player target, Player sender, String message) {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.MSG, "socialspy-prefix-to")
                .replace("%message%", ChatColor.WHITE + message + ChatColor.GRAY)
                .replace("%t_playername%", ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY)
                .replace("%t_displayname%", ChatColor.DARK_AQUA + target.getDisplayName() + ChatColor.GRAY);

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.DARK_AQUA + sender.getDisplayName() + ChatColor.GRAY);
        } else msg = msg.replace("%s_displayname%", ChatColor.DARK_AQUA + sender.getName() + ChatColor.GRAY);
        return msg;
    }

    public static String CONSOLE_MSG_PREFIX(String message) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MSG, "console-msg-prefix")
                .replace("%message%", ChatColor.AQUA + message + ChatColor.GOLD);
        return msg;
    }

    public static String CANNOT_MESSAGE_SELF() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.MSG, "cannot-message-self");
        return msg;
    }

    public static String INCLUDE_PLAYER() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.MSG, "include-player");
        return msg;
    }

    // MUTE
    public static String MUTE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MUTE, "mute")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String MUTE_MESSAGE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MUTE, "mute-message");
        return msg;
    }

    public static String ALREADY_MUTED() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.MUTE, "already-muted");
        return msg;
    }

    // UNMUTE
    public static String UNMUTE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.UNMUTE, "unmute")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String UNMUTE_MESSAGE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.UNMUTE, "unmute-message");
        return msg;
    }

    public static String NOT_MUTED() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.UNMUTE, "not-muted");
        return msg;
    }

    // NICK
    public static String NICK_SET(String nickname) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.NICK, "nick-set")
                .replace("%nickname%", ChatColor.AQUA + nickname + ChatColor.GOLD);
        return msg;
    }

    public static String NICK_SET_OTHERS(Player target, String nickname) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.NICK, "nick-set-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%nickname%", ChatColor.AQUA + nickname + ChatColor.GOLD);
        return msg;
    }

    public static String NICK_SET_OTHERS_MESSAGE(CommandSender sender, String nickname) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.NICK, "nick-set-others-message")
                .replace("%nickname%", ChatColor.AQUA + nickname + ChatColor.GOLD);

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    // PING
    public static String PING_PLAYER(String ping) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PING, "ping-player")
                .replace("%ping%", ChatColor.AQUA + ping + ChatColor.GOLD);
        return msg;
    }

    public static String PING_CONSOLE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PING, "ping-console");
        return msg;
    }

    // PLAYERLIST
    public static String PLAYERLIST_HEADER() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PLAYERLIST, "playerlist-header");
        return msg;
    }

    // PLAYTIME
    public static String PLAYTIME(String hours, String minutes) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PLAYTIME, "playtime")
                .replace("%hours%", ChatColor.AQUA + hours + ChatColor.GOLD)
                .replace("%minutes%", ChatColor.AQUA + minutes + ChatColor.GOLD);
        return msg;
    }

    public static String PLAYTIME_OTHERS(String playername, String displayname, String hours, String minutes) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PLAYTIME, "playtime-others")
                .replace("%t_playername%", ChatColor.AQUA + playername + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + displayname + ChatColor.GOLD)
                .replace("%hours%", ChatColor.AQUA + hours + ChatColor.GOLD)
                .replace("%minutes%", ChatColor.AQUA + minutes + ChatColor.GOLD);
        return msg;
    }

    // PORTAL
    public static String PORTAL_CREATED() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PORTAL, "portal-created");
        return msg;
    }

    public static String PORTAL_DESTROYED(String name, String coords) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PORTAL, "portal-destroyed")
                .replace("%name%", ChatColor.AQUA + name + ChatColor.GOLD)
                .replace("%coords%", ChatColor.AQUA + coords + ChatColor.GOLD);
        return msg;
    }

    public static String PORTAL_DOESNT_EXIST() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.PORTAL, "portal-doesnt-exist");
        return msg;
    }

    public static String PORTAL_LIST() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PORTAL, "portal-list");
        return msg;
    }

    public static String PORTAL_NO_LIST() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.PORTAL, "portal-no-list");
        return msg;
    }

    // RAM
    public static String RAM(String freememory, String usedmemory, String maxmemory) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.RAM, "ram")
                .replace("%freememory%", ChatColor.AQUA + freememory + ChatColor.GOLD)
                .replace("%usedmemory%", ChatColor.AQUA + usedmemory + ChatColor.GOLD)
                .replace("%maxmemory%", ChatColor.AQUA + maxmemory + ChatColor.GOLD);
        return msg;
    }

    // REALNAME
    public static String REALNAME_HEADER(Player players, String nickname) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.REALNAME, "realname-header")
                .replace("%players%", ChatColor.AQUA + players.getName() + ChatColor.GOLD)
                .replace("%nickname%", ChatColor.AQUA + nickname + ChatColor.GOLD);
        return msg;
    }

    // REPAIR
    public static String REPAIR_SUCCESS(String itemdisplayname) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.REPAIR, "repair-success")
                .replace("%item-displayname%", ChatColor.AQUA + itemdisplayname + ChatColor.GOLD);
        return msg;
    }

    public static String CANNOT_BE_REPAIRED() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.REPAIR, "cannot-be-repaired");
        return msg;
    }

    // RESTARTSERVER
    public static String RESTART_MESSAGE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.RESTARTSERVER, "restart-message");
        return msg;
    }

    public static String RESTART_TITLE(String time) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.RESTARTSERVER, "restart-title")
                .replace("%time%", ChatColor.AQUA + time + ChatColor.GOLD);
        return msg;
    }

    // SKULL
    public static String GIVE_SKULL() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SKULL, "give-skull");
        return msg;
    }

    public static String GIVE_SKULL_OTHERS(String target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SKULL, "give-skull-others")
                .replace("%t_playername%", ChatColor.AQUA + target + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target + ChatColor.GOLD);
        return msg;
    }

    public static String GIVE_SKULL_OTHERS_MULTIPLE(String target, String amount) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SKULL, "give-skull-others-multiple")
                .replace("%t_playername%", ChatColor.AQUA + target + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target + ChatColor.GOLD)
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);

        return msg;
    }

    // SPAWN
    public static String SPAWN_SET() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SPAWN, "spawn-set");
        return msg;
    }

    public static String SPAWN_TELEPORT() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SPAWN, "spawn-teleport");
        return msg;
    }

    public static String SPAWN_TELEPORT_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SPAWN, "spawn-teleport-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String SPAWN_TELEPORT_OTHERS_MESSAGE(CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SPAWN, "spawn-teleport-others-message");

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String NO_SPAWN_SET() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.SPAWN, "no-spawn-set");
        return msg;
    }

    // SPAWNER
    public static String SPAWNER_DISPLAYNAME(String spawner) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SPAWNER, "spawner-displayname")
                .replace("%spawner%", ChatColor.AQUA + spawner + ChatColor.GOLD);
        return msg;
    }

    public static String SPAWNER_GIVE(String spawner) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SPAWNER, "spawner-give")
                .replace("%spawner%", ChatColor.AQUA + spawner + ChatColor.GOLD);
        return msg;
    }

    public static String SPAWNER_GIVE_OTHERS(Player target, String spawner) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SPAWNER, "spawner-give-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%spawner%", ChatColor.AQUA + spawner + ChatColor.GOLD);
        return msg;
    }

    public static String SPAWNER_GIVE_OTHERS_MESSAGE(CommandSender sender, String spawner) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SPAWNER, "spawner-give-others-message")
                .replace("%spawner%", ChatColor.AQUA + spawner + ChatColor.GOLD);

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    // SUMMON
    public static String SUMMON(String mob) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUMMON, "summon")
                .replace("%mob%", ChatColor.AQUA + mob + ChatColor.GOLD);
        return msg;
    }

    public static String SUMMON_MULTIPLE(String amount, String mob) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUMMON, "summon-multiple")
                .replace("%mob%", ChatColor.AQUA + mob + ChatColor.GOLD)
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
        return msg;
    }

    public static String SUMMON_OTHERS(Player target, String mob) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUMMON, "summon-multiple")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%mob%", ChatColor.AQUA + mob + ChatColor.GOLD);
        return msg;
    }

    public static String SUMMON_OTHERS_MULTIPLE(Player target, String amount, String mob) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUMMON, "summon-others-multiple")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD)
                .replace("%mob%", ChatColor.AQUA + mob + ChatColor.GOLD);
        return msg;
    }

    // STAFFCHAT
    public static String STAFFCHAT_PREFIX(CommandSender sender, String message) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.STAFFCHAT, "staffchat-prefix")
                .replace("%message%", ChatColor.AQUA + message + ChatColor.GOLD);

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String STAFFCHAT_FROM_CONSOLE_PREFIX(String message) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.STAFFCHAT, "staffchat-from-console-prefix")
                .replace("%message%", ChatColor.AQUA + message + ChatColor.GOLD);
        return msg;
    }

    // STRIKE
    public static String STRIKE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.STRIKE, "strike");
        return msg;
    }

    public static String STRIKE_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.STRIKE, "strike-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    // SUICIDE
    public static String SUICIDE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUICIDE, "suicide");
        return msg;
    }

    public static String BROADCAST_SUICIDE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUICIDE, "broadcast-suicide")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String SUICIDE_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUICIDE, "suicide-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    // TOP
    public static String TOP_TELEPORT() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TOP, "top-teleport");
        return msg;
    }

    public static String TOP_NOT_SAFE() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.TOP, "top-not-safe");
        return msg;
    }

    public static String TOP_TELEPORT_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TOP, "top-teleport-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String TOP_TELEPORT_OTHERS_MESSAGE(CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TOP, "top-teleport-others");

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    // TPA
    public static String TPA_REQUEST_TO(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPA, "tpa-request-to")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String TPA_REQUEST_FROM(CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPA, "tpa-request-from");

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String TPA_ACCEPT() {

        String msg = ChatColor.GREEN + acm.getMessage(MessageType.COMMAND, CommandType.TPA, "tpa-accept");
        return msg;
    }

    public static String TPA_ACCEPT_HOVER() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPA, "tpa-accept-hover");
        return msg;
    }

    public static String TPA_DENY() {

        String msg = ChatColor.RED + acm.getMessage(MessageType.COMMAND, CommandType.TPA, "tpa-deny");
        return msg;
    }

    public static String TPA_DENY_HOVER() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPA, "tpa-deny-hover");
        return msg;
    }

    // TPACCEPT
    public static String TPACCEPT_ACTIONBAR() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPACCEPT, "tpaccept-actionbar");
        return msg;
    }

    public static String TPACCEPT_RECIEVER_MESSAGE(CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPACCEPT, "tpaccept-reciever-message");

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String TPACCEPT_SENDER_MESSAGE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPACCEPT, "tpaccept-sender-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String NO_REQUESTS_ACCEPT() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.TPACCEPT, "no-requests-accept");
        return msg;
    }

    // TPADENY
    public static String TPADENY_RECIEVER_MESSAGE(CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPADENY, "tpadeny-reciever-message");

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String TPADENY_SENDER_MESSAGE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPADENY, "tpadeny-sender-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String NO_REQUESTS_DENY() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.TPADENY, "no-requests-deny");
        return msg;
    }

    //TPALL
    public static String TPALL() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPALL, "tpall");
        return msg;
    }

    public static String TPALL_MESSAGE(CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPALL, "tpall-message");

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String TPALL_EXEPT(CommandSender sender) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPALL, "tpall-exept");

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    // TELEPORT
    public static String CANNOT_TP_TO_SELF_TELEPORT() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.TELEPORT, "cannot-tp-to-self-teleport");
        return msg;
    }

    public static String CANNOT_TP_OTHERS_TO_THEMSELF() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.TELEPORT, "cannot-tp-others-to-themself");
        return msg;
    }

    public static String TELEPORT_TO_PLAYER(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TELEPORT, "teleport-to-player")
          .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName())
                .replace("%t_playername%", ChatColor.AQUA + target.getDisplayName()) + ChatColor.GOLD;
        return msg;
    }

    public static String TELEPORT_TO_COORDS(String coords) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TELEPORT, "teleport-to-coords")
                .replace("%coords%", ChatColor.AQUA + coords + ChatColor.GOLD);
        return msg;
    }

    public static String TELEPORT_PLAYER_TO_PLAYER(Player target1, Player target2) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TELEPORT, "teleport-player-to-player")
                .replace("%t1_playername%", ChatColor.AQUA + target1.getName() + ChatColor.GOLD)
                .replace("%t1_displayname%", ChatColor.AQUA + target1.getDisplayName() + ChatColor.GOLD)
                .replace("%t2_playername%", ChatColor.AQUA + target2.getName() + ChatColor.GOLD)
                .replace("%t2_displayname%", ChatColor.AQUA + target2.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String TELEPORT_PLAYER_TO_COORDS(Player target, String coords) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TELEPORT, "teleport-player-to-coords")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%coords%", ChatColor.AQUA + coords + ChatColor.GOLD);
        return msg;
    }

    // TPHERE
    public static String CANNOT_TP_TO_SELF_TPHERE() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.TPHERE, "cannot-tp-to-self-tphere");
        return msg;
    }

    public static String TELEPORT_PLAYER_TO_YOU(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPHERE, "teleport-player-to-you")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    // VANISH
    public static String NOW_VISIBLE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.VANISH, "now-visible");
        return msg;
    }

    public static String NOW_INVISIBLE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.VANISH, "now-invisible");
        return msg;
    }

    public static String NOW_INVISIBLE_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.VANISH, "now-invisible-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String NOW_VISIBLE_OTHERS(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.VANISH, "now-visible-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    // VERIFY
    public static String DEFAULT_MUTE_REMOVE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.VERIFY, "default-mute-remove");
                return msg;
    }

    public static String DEFAULT_MUTE_REMOVE_MESSAGE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.VERIFY, "default-mute-remove-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String NOT_DEFAULT_MUTED() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.VERIFY, "not-default-muted");
        return msg;
    }

    // WALKSPEED
    public static String WALKSPEED_SET(String amount) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WALKSPEED, "walkspeed-set")
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
        return msg;
    }

    public static String WALKSPEED_SET_OTHERS(Player target, String amount) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WALKSPEED, "walkspeed-set-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
        return msg;
    }

    public static String WALKSPEED_SET_OTHERS_MESSAGE(CommandSender sender, String amount) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WALKSPEED, "walkspeed-set-others-message")
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    // WARN
    public static String DEFAULT_WARN_REASON() {

        String msg = acm.getMessage(MessageType.COMMAND, CommandType.WARN, "default-warn-reason");
        return msg;
    }

    public static String WARN_ADD(Player target, String reason) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warn-add")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD);
        return msg;
    }

    public static String WARN_REMOVE(Player target, String reason) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warn-remove")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD);
        return msg;
    }

    public static String WARN_GET_HEADER(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warn-get-header")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String WARN_GET(String source, String reason, String datecreated, String warnid) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warn-get")
                .replace("%date-created%", ChatColor.AQUA + datecreated + ChatColor.GOLD)
                .replace("%warn-id%", ChatColor.AQUA + warnid + ChatColor.GOLD)
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD);

            msg = msg.replace("%s_displayname%", ChatColor.AQUA + source + ChatColor.GOLD);
            msg = msg.replace("%s_playername%", ChatColor.AQUA + source + ChatColor.GOLD);
        return msg;
    }

    public static String WARNED_HEADER() {

        String msg = ChatColor.RED + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warned-header");
        return msg;
    }

    public static String WARNED(String source, String reason) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warned")
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD)
                .replace("\n", "\n");

            msg = msg.replace("%s_displayname%", ChatColor.AQUA + source + ChatColor.GOLD);
            msg = msg.replace("%s_playername%", ChatColor.AQUA + source + ChatColor.GOLD);
        return msg;
    }

    public static String WARNED_REMOVE(String reason) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warned-remove")
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD);
        return msg;
    }

    //WARP
    public static String WARP_DOESNT_EXIST() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.WARP, "warp-doesnt-exist");
        return msg;
    }

    public static String WARP_TELEPORT(String warp) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARP, "warp-teleport")
                .replace("%warp%", ChatColor.AQUA + warp + ChatColor.GOLD);
        return msg;
    }

    public static String WARP_TELEPORT_OTHERS(Player target, String warp) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARP, "warp-teleport-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%warp%", ChatColor.AQUA + warp + ChatColor.GOLD);
        return msg;
    }

    public static String WARP_TELEPORT_OTHERS_MESSAGE(CommandSender sender, String warp) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARP, "warp-teleport-others-message")
                .replace("%warp%", ChatColor.AQUA + warp + ChatColor.GOLD);

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    // SETWARP
    public static String WARP_ALREADY_EXISTS() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.SETWARP, "warp-already-exists");
        return msg;
    }

    public static String WARP_SET(String warp) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SETWARP, "warp-set")
                .replace("%warp%", ChatColor.AQUA + warp + ChatColor.GOLD);
        return msg;
    }

    // DELETEWARP
    public static String DOESNT_EXIST() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.DELETEWARP, "doesnt-exist");
        return msg;
    }

    public static String WARP_DELETED(String warp) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.DELETEWARP, "warp-deleted")
                .replace("%warp%", ChatColor.AQUA + warp + ChatColor.GOLD);
        return msg;
    }

    // WARPS
    public static String WARPS_HEADER() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARPS, "warps-header");
        return msg;
    }

    public static String NO_WARPS() {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.WARPS, "no-warps");
        return msg;
    }

    // WEATHER
    public static String TO_THUNDER(String world) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WEATHER, "to-thunder")
                .replace("%world%", ChatColor.AQUA + world + ChatColor.GOLD);
        return msg;
    }

    public static String TO_RAIN(String world) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WEATHER, "to-rain")
                .replace("%world%", ChatColor.AQUA + world + ChatColor.GOLD);
        return msg;
    }

    public static String TO_CLEAR(String world) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WEATHER, "to-clear")
                .replace("%world%", ChatColor.AQUA + world + ChatColor.GOLD);
        return msg;
    }

    // WHEREAMI
    public static String WHEREAMI(String coords, String world) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHEREAMI, "whereami")
                .replace("%coords%", ChatColor.AQUA + coords + ChatColor.GOLD)
                .replace("%world%", ChatColor.AQUA + world + ChatColor.GOLD);
        return msg;
    }

    public static String WHEREAMI_OTHERS(Player target, String coords, String world) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHEREAMI, "whereami-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%coords%", ChatColor.AQUA + coords + ChatColor.GOLD)
                .replace("%world%", ChatColor.AQUA + world + ChatColor.GOLD);
        return msg;
    }

    // WHOIS
    public static String NAME(String name) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "name")
                .replace("%playername%", ChatColor.AQUA + name + ChatColor.GOLD);
        return msg;
    }

    public static String NICKNAME(String nickname) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "nickname")
                .replace("%displayname%", ChatColor.AQUA + nickname + ChatColor.GOLD);
        return msg;
    }

    public static String COLORNICK(String colornick) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "colornick")
                .replace("%colornick%", ChatColor.AQUA + colornick + ChatColor.GOLD);
        return msg;
    }

    public static String UUID(String uuid) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "uuid")
                .replace("%uuid%", ChatColor.AQUA + uuid + ChatColor.GOLD);
        return msg;
    }

    public static String IS_OP(String op) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "is-op")
                .replace("%op%", ChatColor.AQUA + op + ChatColor.GOLD);
        return msg;
    }

    public static String HEALTH(String health) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "health")
                .replace("%playerhealth%", ChatColor.AQUA + health + ChatColor.GOLD);
        return msg;
    }

    public static String FOOD(String food) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "food")
                .replace("%playerfood%", ChatColor.AQUA + food + ChatColor.GOLD);
        return msg;
    }

    public static String WORLD(String world) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "world")
                .replace("%world%", ChatColor.AQUA + world + ChatColor.GOLD);
        return msg;
    }

    public static String COORDS(String coords) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "coords")
                .replace("%coords%", ChatColor.AQUA + coords + ChatColor.GOLD);
        return msg;
    }

    public static String AFK(String afk) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "afk")
                .replace("%afk%", ChatColor.AQUA + afk + ChatColor.GOLD);
        return msg;
    }

    public static String CLIENT(String client) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "client")
                .replace("%client%", ChatColor.AQUA + client + ChatColor.GOLD);
        return msg;
    }

    public static String ADDRESS(String address) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "address")
                .replace("%ip%", ChatColor.AQUA + address + ChatColor.GOLD);
        return msg;
    }

    public static String GAMEMODE(String gamemode) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "gamemode")
                .replace("%gamemode%", ChatColor.AQUA + gamemode + ChatColor.GOLD);
        return msg;
    }

    public static String MUTED(String muted) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "muted")
                .replace("%muted%", ChatColor.AQUA + muted + ChatColor.GOLD);
        return msg;
    }

    public static String WHITELISTED(String whitelisted) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "whitelisted")
                .replace("%whitelisted%", ChatColor.AQUA + whitelisted + ChatColor.GOLD);
        return msg;
    }

    public static String GOD(String god) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "god")
                .replace("%god%", ChatColor.AQUA + god + ChatColor.GOLD);
        return msg;
    }

    public static String VANISHED(String vanished) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "vanished")
                .replace("%vanished%", ChatColor.AQUA + vanished + ChatColor.GOLD);
        return msg;
    }

    // XP
    public static String XP_LEVELS(String amount) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-levels")
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
        return msg;
    }

    public static String XP_XP(String amount) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-xp")
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
        return msg;
    }

    public static String XP_LEVELS_OTHERS(Player target, String amount) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-levels-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
        return msg;
    }

    public static String XP_LEVELS_OTHERS_MESSAGE(CommandSender sender, String amount) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-levels-others-message")
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String XP_XP_OTHERS(Player target, String amount) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-xp-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
        return msg;
    }

    public static String XP_XP_OTHERS_MESSAGE(CommandSender sender, String amount) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-xp-others-message")
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);

        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String CURRENT_LANGUAGE(String language, String code) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LANGUAGE, "current-language")
                .replace("%language%", ChatColor.AQUA + language + ChatColor.GOLD)
                .replace("%code%", ChatColor.AQUA + code + ChatColor.GOLD);
        return msg;
    }

    public static String LANGUAGE_SET(String language, String code) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LANGUAGE, "language-set")
                .replace("%language%", ChatColor.AQUA + language + ChatColor.GOLD)
                .replace("%code%", ChatColor.AQUA + code + ChatColor.GOLD);
        return msg;
    }

    public static String AFK_TAG() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.AFK, "afk-tag");
        return msg;
    }

    public static String NOW_AFK(Player target) {

        Profile profile = new Profile(target);

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.AFK, "now-afk")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + profile.getNickname() + ChatColor.GOLD);

        return msg;
    }

    public static String NOT_AFK(Player target) {

        Profile profile = new Profile(target);

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.AFK, "not-afk")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + profile.getNickname() + ChatColor.GOLD);

        return msg;
    }

    public static String NOW_AFK_TARGET() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.AFK, "now-afk-target");
        return msg;
    }

    public static String NOT_AFK_TARGET() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.AFK, "not-afk-target");
        return msg;
    }
}