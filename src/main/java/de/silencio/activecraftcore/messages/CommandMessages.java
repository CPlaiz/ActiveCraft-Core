package de.silencio.activecraftcore.messages;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.playermanagement.Profile;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMessages {

    static ActiveCraftMessage acm = ActiveCraftCore.getActiveCraftMessage();
    static FileConfig fileConfig = new FileConfig("config.yml");

    // BANSCREEN
    public static String BAN_TITLE() {
        return ChatColor.RED + acm.getMessage(MessageType.COMMAND, CommandType.BANSCREEN, "ban-title");
    }

    public static String BAN_IP_TITLE() {
        return ChatColor.RED + acm.getMessage(MessageType.COMMAND, CommandType.BANSCREEN, "ban-ip-title");
    }

    public static String BAN_EXPIRATION(String time) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BANSCREEN, "ban-expiration")
                .replace("%time%", ChatColor.AQUA + time + ChatColor.GOLD);
    }

    public static String BAN_EXPIRATION_PERMANENT() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BANSCREEN, "ban-expiration-permanent");
    }

    public static String BAN_REASON(String reason) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BANSCREEN, "ban-reason")
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD);
    }

    // BAN
    public static String BAN_COMPLETED_MESSAGE(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BAN, "ban-completed-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    public static String ALREADY_BANNED() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BAN, "already-banned");
    }

    // UNBAN
    public static String NOT_BANNED() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.UNBAN, "not-banned");
    }

    public static String UNBANNED_PLAYER(String target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.UNBAN, "unbanned-player")
                .replace("%t_playername%", ChatColor.AQUA + target + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target + ChatColor.GOLD);
    }

    // BANLIST
    public static String BANLIST_HEADER() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BANLIST, "banlist-header");
    }

    public static String UNBAN_ON_HOVER(String target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BANLIST, "unban-on-hover")
                .replace("%t_playername%", ChatColor.AQUA + target + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target + ChatColor.GOLD);
    }

    public static String UNBAN_IP_ON_HOVER(String ip) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BANLIST, "unban-ip-on-hover")
                .replace("%ip%", ChatColor.AQUA + ip + ChatColor.GOLD);
    }

    public static String NO_BANS() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BANLIST, "no-bans");
    }

    // IPBAN
    public static String IPBAN_COMPLETED_MESSAGE(Player target, String ip) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.IPBAN, "ipban-completed-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%ip%", ChatColor.AQUA + ip + ChatColor.GOLD);
    }

    public static String IPBAN_COMPLETED_MESSAGE(String ip) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.IPBAN, "ipban-completed-message")
                .replace("%t_playername%", ChatColor.AQUA + ip + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + ip + ChatColor.GOLD)
                .replace("%ip%", ChatColor.AQUA + ip + ChatColor.GOLD);
    }

    public static String INVALID_IP() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.IPBAN, "invalid-ip");
    }

    public static String IP_ALREADY_BANNED() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.IPBAN, "ip-already-banned");
    }

    // UNBANIP
    public static String UNBANNED_IP(String ip) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.UNBAN_IP, "unbanned-ip")
                .replace("%ip%", ChatColor.AQUA + ip + ChatColor.GOLD);
    }

    public static String IP_NOT_BANNED() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.UNBAN_IP, "ip-not-banned");
    }

    // BACK
    public static String TELEPORTED_BACK() {

        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BACK, "teleported-back");
    }

    public static String NO_RETURN_LOCATION() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.BACK, "no-return-location");
    }

    public static String TELEPORTED_BACK_OTHERS(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BACK, "teleported-back-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }


    public static String TELEPORTED_BACK_OTHERS_MESSAGE(CommandSender sender) {
        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BACK, "teleported-back-others-message")
                .replace("%s_playername%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String NO_RETURN_LOCATION_OTHERS(Player target) {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.BACK, "no-return-location-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GRAY)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GRAY);
    }

    // BOOK
    public static String CHANGED_TITLE(String title) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "changed-title")
                .replace("%title%", ChatColor.AQUA + title + ChatColor.GOLD);
    }

    public static String CHANGED_AUTHOR(String author) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "changed-author")
                .replace("%author%", ChatColor.AQUA + author + ChatColor.GOLD);
    }

    public static String CHANGED_PAGE(String page) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "changed-page")
                .replace("%author%", ChatColor.AQUA + page + ChatColor.GOLD);
    }

    public static String CHANGED_GENERATION(String generation) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "changed-generation")
                .replace("%generation%", ChatColor.AQUA + generation + ChatColor.GOLD);
    }

    public static String ORIGINAL() {
        return ChatColor.AQUA + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "original");
    }

    public static String COPY_ORIGINAL() {
        return ChatColor.AQUA + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "copy-original");
    }

    public static String COPY_COPY() {
        return ChatColor.AQUA + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "copy-copy");
    }

    public static String TATTERED() {
        return ChatColor.AQUA + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "tattered");
    }

    public static String ADDED_PAGE() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "added-page");
    }

    public static String NOT_HOLDING_BOOK() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.BOOK, "not-holding-book");
    }

    // BROADCAST
    public static String BROADCAST_PREFIX() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BROADCAST, "broadcast-prefix");
    }

    public static String INCLUDE_MESSAGE() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.BROADCAST, "include-message");
    }

    // BUTCHER
    public static String NO_MOBS() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.BUTCHER, "no-mobs");
    }

    public static String KILLED_MOBS(int amount) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.BUTCHER, "killed-mobs")
                .replace("%amount%", ChatColor.AQUA + "" + amount + ChatColor.GOLD);
    }

    // CLEARINVENTORY
    public static String CLEARED_SELF() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.CLEARINVENTORY, "cleared-self");
    }

    public static String CLEARED_OTHERS(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.CLEARINVENTORY, "cleared-others")
                .replace("%t_displayname%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
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
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.COLORNICK, "colornick-self")
                .replace("%color%", color + ChatColor.GOLD);
    }

    public static String COLORNICK_OTHERS(Player target, String color) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.COLORNICK, "colornick-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%color%", color + ChatColor.GOLD);
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
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.HOME, "teleport-home-complete")
                .replace("%home%", ChatColor.AQUA + home + ChatColor.GOLD);
    }

    public static String HOME_NOT_SET(String home) {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.HOME, "home-not-set")
                .replace("%home%", ChatColor.AQUA + home + ChatColor.GRAY);
    }

    public static String TELEPORT_HOME_OTHERS_COMPLETE(Player target, String home) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.HOME, "teleport-home-others-complete")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%home%", ChatColor.AQUA + home + ChatColor.GOLD);
    }

    public static String HOME_OTHERS_NOT_SET(Player target, String home) {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.HOME, "home-others-not-set")
                .replace("%t_playername%", ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY)
                .replace("%t_displayname%", ChatColor.DARK_AQUA + target.getDisplayName() + ChatColor.GRAY)
                .replace("%home%", ChatColor.AQUA + home + ChatColor.GRAY);
    }

    // SETHOME
    public static String HOME_SET(String home) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SETHOME, "home-set")
                .replace("%home%", ChatColor.AQUA + home + ChatColor.GOLD);
    }

    public static String HOME_OTHERS_SET(Player target, String home) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SETHOME, "home-others-set")
                .replace("%t_displayname%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%home%", ChatColor.AQUA + home + ChatColor.GOLD);
    }

    public static String MAX_HOMES() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.SETHOME, "max-homes");
    }

    public static String MAX_HOMES_OTHERS() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.SETHOME, "max-homes-others");
    }

    // DELETEHOME
    public static String HOME_DELETED(String home) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.DELETEHOME, "home-deleted")
                .replace("%home%", ChatColor.AQUA + home + ChatColor.GOLD);
    }

    public static String HOME_OTHERS_DELETED(Player target, String home) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.DELETEHOME, "home-others-deleted")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%home%", ChatColor.AQUA + home + ChatColor.GOLD);
    }

    // EDITSIGN
    public static String ENABLED() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.EDITSIGN, "enabled");
    }

    public static String DISABLED() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.EDITSIGN, "disabled");
    }

    public static String ENABLED_OTHERS(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.EDITSIGN, "enabled-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    public static String ENABLED_OTHERS_MESSAGE(CommandSender sender) {
        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.EDITSIGN, "enabled-others-message");
        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String DISABLED_OTHERS(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.EDITSIGN, "disabled-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
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
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "applied-enchantment")
                .replace("%enchantment%", ChatColor.AQUA + enchantment + ChatColor.GOLD)
                .replace("%maxlevel%", ChatColor.AQUA + maxlevel + ChatColor.GOLD);
    }

    public static String CANNOT_BE_APPLIED() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "cannot-be-applied");
    }

    public static String CLEARED_ALL_ENCHANTMENTS() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "cleared-all-enchantments");
    }

    public static String NOT_ENCHANTED() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "not-enchanted");
    }

    public static String GLINT_TRUE() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "glint-true");
    }

    public static String GLINT_FALSE() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENCHANT, "glint-false");
    }

    // INVSEE
    public static String INVSEE(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.INVSEE, "invsee")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    // ENDERCHEST
    public static String ENDERCHEST_OPEN() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENDERCHEST, "enderchest-open");
    }

    public static String ENDERCHEST_OPEN_OTHERS(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ENDERCHEST, "enderchest-open-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    // FEED
    public static String FEED() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FEED, "feed");
    }

    public static String FEED_OTHERS(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FEED, "feed-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
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
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLY, "enable-fly");
    }

    public static String ENABLE_FLY_OTHERS(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLY, "enable-fly-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    public static String ENABLE_FLY_OTHERS_MESSAGE(CommandSender sender) {
        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLY, "enable-fly-others-message");
        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String DISABLE_FLY() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLY, "disable-fly");
    }

    public static String DISABLE_FLY_OTHERS(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLY, "disable-fly-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
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
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLYSPEED, "flyspeed-set")
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
    }

    public static String FLYSPEED_SET_OTHERS(Player target, String amount) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FLYSPEED, "flyspeed-set-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
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
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.GOD, "enable-god");
    }

    public static String ENABLE_GOD_OTHERS(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.GOD, "enable-god-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    public static String ENABLE_GOD_OTHERS_MESSAGE(CommandSender sender) {
        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.GOD, "enable-god-others-message");
        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String DISABLE_GOD() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.GOD, "disable-god");
    }

    public static String DISABLE_GOD_OTHERS(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.GOD, "disable-god-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
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
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.HAT, "hat-success");
    }

    // HEAL
    public static String HEAL() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.HEAL, "heal");
    }

    public static String HEAL_OTHERS(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.HEAL, "heal-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
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
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ITEM, "item-give")
                .replace("%item%", ChatColor.AQUA + item + ChatColor.GOLD);
    }

    public static String ITEM_GIVE_MULTIPLE(String item, String amount) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ITEM, "item-give-multiple")
                .replace("%item%", ChatColor.AQUA + item + ChatColor.GOLD)
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
    }

    public static String ITEM_RENAMED() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ITEM, "item-renamed");
    }

    public static String ITEM_LORE_ADD() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ITEM, "item-lore-add");
    }

    public static String ITEM_LORE_CLEAR() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ITEM, "item-lore-clear");
    }

    public static String ITEM_LORE_SET() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.ITEM, "item-lore-set");
    }

    // KICKALL
    public static String DEFAULT_KICKALL() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICKALL, "default-kickall");
    }

    public static String DEFAULT_KICKALL_MESSAGE() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICKALL, "default-kickall-message");
    }

    public static String CUSTOM_KICKALL(String reason) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICKALL, "custom-kickall")
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD);
    }

    public static String CUSTOM_KICKALL_MESSAGE(String reason) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICKALL, "custom-kickall-message")
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD);
    }

    // KICK
    public static String DEFAULT_KICK(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICK, "default-kick")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    public static String DEFAULT_KICK_MESSAGE() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICK, "default-kick-message");
    }

    public static String CUSTOM_KICK(Player target, String reason) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICK, "custom-kick")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD);
    }

    public static String CUSTOM_KICK_MESSAGE(String reason) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KICK, "custom-kick-message")
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD);
    }

    // KNOCKBACKSTICK
    public static String KNOCKBACKSTICK() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KNOCKBACKSTICK, "knockbackstick");
    }

    public static String KNOCKBACKSTICK_LORE() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KNOCKBACKSTICK, "knockbackstick-lore");
    }

    public static String KNOCKBACKSTICK_OTHERS(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KNOCKBACKSTICK, "knockbackstick-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
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
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.KNOWNIPS, "knownips-header")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    // LASTCOORDS
    public static String NEVER_ENTERED_WORLD() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.LASTCOORDS, "never-entered-world");
    }

    public static String NEVER_QUIT_SERVER() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.LASTCOORDS, "never-quit-server");
    }

    public static String LASTCOORDS_MESSAGE(String playername, String displayname, String coords, String world) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LASTCOORDS, "lastcoords-message")
                .replace("%t_playername%", ChatColor.AQUA + playername + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + displayname + ChatColor.GOLD)
                .replace("%world%", ChatColor.AQUA + world + ChatColor.GOLD)
                .replace("%coords%", ChatColor.AQUA + coords + ChatColor.GOLD);
    }

    // LASTONLINE
    public static String LASTONLINE_ONLINE(Profile target, String lastonline) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LASTONLINE, "lastonline-online")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getFullNickname() + ChatColor.GOLD)
                .replace("%lastonline%", ChatColor.GREEN + lastonline + ChatColor.GOLD);
    }

    public static String LASTONLINE_OFFLINE(String target, String lastonline) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LASTONLINE, "lastonline-offline")
                .replace("%t_playername%", ChatColor.AQUA + target + ChatColor.GOLD)
                .replace("%lastonline%", ChatColor.AQUA + lastonline + ChatColor.GOLD);
    }

    // LEATHERCOLOR
    public static String NO_LEATHER_ITEM() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.LEATHERCOLOR, "no-leather-item");
    }

    // LOCKDOWN
    public static String LOCKDOWN_ENABLED() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "lockdown-enabled");
    }

    public static String LOCKDOWN_ALREADY_ENABLED() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "lockdown-already-enabled");
    }

    public static String LOCKDOWN_DISABLED() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "lockdown-disabled");
    }

    public static String LOCKDOWN_NOT_ENABLED() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "lockdown-not-enabled");
    }

    public static String ALLOW_PLAYER(String target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "allow-player")
                .replace("%t_playername%", ChatColor.AQUA + target + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target + ChatColor.GOLD);
    }

    public static String ALLOW_PLAYER_EXTRA() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "allow-player-extra");
    }

    public static String ALLOW_PLAYER_ALREADY_ENABLED(String target) {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "allow-player-already-enabled")
                .replace("%t_playername%", ChatColor.AQUA + target + ChatColor.GRAY)
                .replace("%t_displayname%", ChatColor.AQUA + target + ChatColor.GRAY);
    }

    public static String DISALLOW_PLAYER(String target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "disallow-player")
                .replace("%t_playername%", ChatColor.AQUA + target + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target + ChatColor.GOLD);
    }

    public static String DISALLOW_PLAYER_EXTRA() {

        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "disallow-player-extra");
    }

    public static String DISALLOW_PLAYER_ALREADY_DISABLED(String target) {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.LOCKDOWN, "disallow-player-already-disabled")
                .replace("%t_playername%", ChatColor.AQUA + target + ChatColor.GRAY)
                .replace("%t_displayname%", ChatColor.AQUA + target + ChatColor.GRAY);
    }

    // LOG
    public static String LOG_PREFIX(Player target, String command) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOG, "log-prefix")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%command%", ChatColor.AQUA + command + ChatColor.GOLD);
    }

    public static String ENABLE_LOG() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOG, "enable-log");
    }

    public static String DISABLE_LOG() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LOG, "disable-log");
    }

    // MORE
    public static String CANNOT_STACK() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.MORE, "cannot-stack");
    }

    // MSG
    public static String MSG_PREFIX_TO(Profile profile, String message) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MSG, "msg-prefix-to")
                .replace("%t_playername%", ChatColor.AQUA + profile.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + profile.getFullNickname() + ChatColor.GOLD)
                .replace("%message%", ChatColor.WHITE + message + ChatColor.GOLD);
    }

    public static String MSG_PREFIX_FROM(CommandSender sender, String message) {
        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MSG, "msg-prefix-from")
                .replace("%message%", ChatColor.WHITE + message + ChatColor.GOLD);
        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String SOCIALSPY_PREFIX_TO(Player target, CommandSender sender, String message) {
        String msg = ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.MSG, "socialspy-prefix-to")
                .replace("%message%", ChatColor.WHITE + message + ChatColor.GRAY)
                .replace("%t_playername%", ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY)
                .replace("%t_displayname%", ChatColor.DARK_AQUA + target.getDisplayName() + ChatColor.GRAY);
        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.DARK_AQUA + ((Player) sender).getDisplayName() + ChatColor.GRAY);
        } else msg = msg.replace("%s_displayname%", ChatColor.DARK_AQUA + sender.getName() + ChatColor.GRAY);
        return msg;
    }


    public static String SOCIALSPY_ON() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MSG, "socialspy-on");
    }

    public static String SOCIALSPY_OFF() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MSG, "socialspy-off");
    }

    public static String CONSOLE_MSG_PREFIX(String message) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MSG, "console-msg-prefix")
                .replace("%message%", ChatColor.AQUA + message + ChatColor.GOLD);
    }

    // MUTE
    public static String MUTE(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MUTE, "mute")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    public static String MUTE_MESSAGE() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.MUTE, "mute-message");
    }

    public static String ALREADY_MUTED() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.MUTE, "already-muted");
    }

    // UNMUTE
    public static String UNMUTE(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.UNMUTE, "unmute")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    public static String UNMUTE_MESSAGE() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.UNMUTE, "unmute-message");
    }

    public static String NOT_MUTED() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.UNMUTE, "not-muted");
    }

    // FORCEMUTE
    public static String FORCEMUTE(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FORCEMUTE, "forcemute")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    public static String FORCEUNMUTE(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.FORCEMUTE, "forceunmute")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    // NICK
    public static String NICK_SET(String nickname) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.NICK, "nick-set")
                .replace("%nickname%", ChatColor.AQUA + nickname + ChatColor.GOLD);
    }

    public static String NICK_SET_OTHERS(Player target, String nickname) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.NICK, "nick-set-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%nickname%", ChatColor.AQUA + nickname + ChatColor.GOLD);
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
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PING, "ping-player")
                .replace("%ping%", ChatColor.AQUA + ping + ChatColor.GOLD);
    }

    public static String PING_CONSOLE() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PING, "ping-console");
    }

    // PLAYERLIST
    public static String PLAYERLIST_HEADER() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PLAYERLIST, "playerlist-header");
    }

    // PLAYTIME
    public static String PLAYTIME(String hours, String minutes) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PLAYTIME, "playtime")
                .replace("%hours%", ChatColor.AQUA + hours + ChatColor.GOLD)
                .replace("%minutes%", ChatColor.AQUA + minutes + ChatColor.GOLD);
    }

    public static String PLAYTIME_OTHERS(String playername, String displayname, String hours, String minutes) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PLAYTIME, "playtime-others")
                .replace("%t_playername%", ChatColor.AQUA + playername + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + displayname + ChatColor.GOLD)
                .replace("%hours%", ChatColor.AQUA + hours + ChatColor.GOLD)
                .replace("%minutes%", ChatColor.AQUA + minutes + ChatColor.GOLD);
    }

    // PORTAL
    public static String PORTAL_CREATED() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PORTAL, "portal-created");
    }

    public static String PORTAL_DESTROYED(String name, String coords) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PORTAL, "portal-destroyed")
                .replace("%name%", ChatColor.AQUA + name + ChatColor.GOLD)
                .replace("%coords%", ChatColor.AQUA + coords + ChatColor.GOLD);
    }

    public static String PORTAL_DOESNT_EXIST() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.PORTAL, "portal-doesnt-exist");
    }

    public static String PORTAL_LIST() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.PORTAL, "portal-list");
    }

    public static String PORTAL_NO_LIST() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.PORTAL, "portal-no-list");
    }

    // RAM
    public static String RAM(String freememory, String usedmemory, String maxmemory) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.RAM, "ram")
                .replace("%freememory%", ChatColor.AQUA + freememory + ChatColor.GOLD)
                .replace("%usedmemory%", ChatColor.AQUA + usedmemory + ChatColor.GOLD)
                .replace("%maxmemory%", ChatColor.AQUA + maxmemory + ChatColor.GOLD);
    }

    // REALNAME
    public static String REALNAME_HEADER(String players, String nickname) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.REALNAME, "realname-header")
                .replace("%players%", ChatColor.AQUA + players + ChatColor.GOLD)
                .replace("%nickname%", ChatColor.AQUA + nickname + ChatColor.GOLD);
    }

    // REPAIR
    public static String REPAIR_SUCCESS(String itemdisplayname) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.REPAIR, "repair-success")
                .replace("%item-displayname%", ChatColor.AQUA + itemdisplayname + ChatColor.GOLD);
    }

    public static String CANNOT_BE_REPAIRED() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.REPAIR, "cannot-be-repaired");
    }

    // RESTARTSERVER
    public static String RESTART_MESSAGE() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.RESTARTSERVER, "restart-message");
    }

    public static String RESTART_TITLE(String time) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.RESTARTSERVER, "restart-title")
                .replace("%time%", ChatColor.AQUA + time + ChatColor.GOLD);
    }

    public static String RESTART_CANCEL() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.RESTARTSERVER, "restart-cancel");
    }

    // SKULL
    public static String GIVE_SKULL() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SKULL, "give-skull");
    }

    public static String GIVE_SKULL_OTHERS(String target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SKULL, "give-skull-others")
                .replace("%t_playername%", ChatColor.AQUA + target + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target + ChatColor.GOLD);
    }

    public static String GIVE_SKULL_OTHERS_MULTIPLE(String target, String amount) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SKULL, "give-skull-others-multiple")
                .replace("%t_playername%", ChatColor.AQUA + target + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target + ChatColor.GOLD)
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
    }

    // SPAWN
    public static String SPAWN_SET() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SPAWN, "spawn-set");
    }

    public static String SPAWN_TELEPORT() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SPAWN, "spawn-teleport");
    }

    public static String SPAWN_TELEPORT_OTHERS(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SPAWN, "spawn-teleport-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    public static String SPAWN_TELEPORT_OTHERS_MESSAGE(CommandSender sender) {
        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SPAWN, "spawn-teleport-others-message");
        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String NO_SPAWN_SET() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.SPAWN, "no-spawn-set");
    }

    // SPAWNER
    public static String SPAWNER_DISPLAYNAME(String spawner) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SPAWNER, "spawner-displayname")
                .replace("%spawner%", ChatColor.AQUA + spawner + ChatColor.GOLD);
    }

    public static String SPAWNER_GIVE(String spawner) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SPAWNER, "spawner-give")
                .replace("%spawner%", ChatColor.AQUA + spawner + ChatColor.GOLD);
    }

    public static String SPAWNER_GIVE_OTHERS(Player target, String spawner) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SPAWNER, "spawner-give-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%spawner%", ChatColor.AQUA + spawner + ChatColor.GOLD);
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
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUMMON, "summon")
                .replace("%mob%", ChatColor.AQUA + mob + ChatColor.GOLD);
    }

    public static String SUMMON_MULTIPLE(String amount, String mob) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUMMON, "summon-multiple")
                .replace("%mob%", ChatColor.AQUA + mob + ChatColor.GOLD)
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
    }

    public static String SUMMON_OTHERS(Player target, String mob) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUMMON, "summon-multiple")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%mob%", ChatColor.AQUA + mob + ChatColor.GOLD);
    }

    public static String SUMMON_OTHERS_MULTIPLE(Player target, String amount, String mob) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUMMON, "summon-others-multiple")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD)
                .replace("%mob%", ChatColor.AQUA + mob + ChatColor.GOLD);
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
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.STAFFCHAT, "staffchat-from-console-prefix")
                .replace("%message%", ChatColor.AQUA + message + ChatColor.GOLD);
    }

    // STRIKE
    public static String STRIKE() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.STRIKE, "strike");
    }

    public static String STRIKE_OTHERS(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.STRIKE, "strike-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    // SUICIDE
    public static String SUICIDE() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUICIDE, "suicide");
    }

    public static String BROADCAST_SUICIDE(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUICIDE, "broadcast-suicide")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName()
                        .replace(" " + fileConfig.getString(" " + "vanish-format"), "")
                        .replace(" " + fileConfig.getString(" " + "afk-format"), "") + ChatColor.GOLD);
    }

    public static String SUICIDE_OTHERS(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SUICIDE, "suicide-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    // TOP
    public static String TOP_TELEPORT() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TOP, "top-teleport");
    }

    public static String TOP_NOT_SAFE() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.TOP, "top-not-safe");
    }

    public static String TOP_TELEPORT_OTHERS(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TOP, "top-teleport-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
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
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPA, "tpa-request-to")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    public static String TPA_REQUEST_FROM(CommandSender sender) {
        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPA, "tpa-request-from");
        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String TPA_ACCEPT() {
        return ChatColor.GREEN + acm.getMessage(MessageType.COMMAND, CommandType.TPA, "tpa-accept");
    }

    public static String TPA_ACCEPT_HOVER() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPA, "tpa-accept-hover");
    }

    public static String TPA_DENY() {
        return ChatColor.RED + acm.getMessage(MessageType.COMMAND, CommandType.TPA, "tpa-deny");
    }

    public static String TPA_DENY_HOVER() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPA, "tpa-deny-hover");
    }

    public static String CANNOT_TPA_SELF() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.TPA, "cannot-tpa-self");
    }

    // TPACCEPT
    public static String TPACCEPT_ACCEPTED() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPACCEPT, "tpaccept-accepted");
    }

    public static String TPACCEPT_ACTIONBAR() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPACCEPT, "tpaccept-actionbar");
    }

    public static String TPACCEPT_RECIEVER_MESSAGE(CommandSender sender) {
        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPACCEPT, "tpaccept-reciever-message");
        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String TPACCEPT_SENDER_MESSAGE(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPACCEPT, "tpaccept-sender-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    public static String NO_REQUESTS_ACCEPT() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.TPACCEPT, "no-requests-accept");
    }

    // TPADENY

    public static String TPADENY_DENIED() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPACCEPT, "tpadeny-denied");
    }

    public static String TPADENY_RECIEVER_MESSAGE(CommandSender sender) {
        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPADENY, "tpadeny-reciever-message");
        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String TPADENY_SENDER_MESSAGE(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPADENY, "tpadeny-sender-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    public static String NO_REQUESTS_DENY() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.TPADENY, "no-requests-deny");
    }

    //TPALL
    public static String TPALL() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPALL, "tpall");
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
    public static String TELEPORT_TO_PLAYER(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TELEPORT, "teleport-to-player")
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%t_playername%", ChatColor.AQUA + target.getName()) + ChatColor.GOLD;
    }

    public static String TELEPORT_TO_COORDS(String coords) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TELEPORT, "teleport-to-coords")
                .replace("%coords%", ChatColor.AQUA + coords + ChatColor.GOLD);
    }

    public static String TELEPORT_PLAYER_TO_PLAYER(Player target1, Player target2) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TELEPORT, "teleport-player-to-player")
                .replace("%t1_playername%", ChatColor.AQUA + target1.getName() + ChatColor.GOLD)
                .replace("%t1_displayname%", ChatColor.AQUA + target1.getDisplayName() + ChatColor.GOLD)
                .replace("%t2_playername%", ChatColor.AQUA + target2.getName() + ChatColor.GOLD)
                .replace("%t2_displayname%", ChatColor.AQUA + target2.getDisplayName() + ChatColor.GOLD);
    }

    public static String TELEPORT_PLAYER_TO_COORDS(Player target, String coords) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TELEPORT, "teleport-player-to-coords")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%coords%", ChatColor.AQUA + coords + ChatColor.GOLD);
    }

    // TPHERE
    public static String TELEPORT_PLAYER_TO_YOU(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.TPHERE, "teleport-player-to-you")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    // VANISH
    public static String NOW_VISIBLE() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.VANISH, "now-visible");
    }

    public static String NOW_INVISIBLE() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.VANISH, "now-invisible");
    }

    public static String NOW_INVISIBLE_OTHERS(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.VANISH, "now-invisible-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    public static String NOW_VISIBLE_OTHERS(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.VANISH, "now-visible-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    // VERIFY
    public static String DEFAULT_MUTE_REMOVE() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.VERIFY, "default-mute-remove");
    }

    public static String DEFAULT_MUTE_REMOVE_MESSAGE(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.VERIFY, "default-mute-remove-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    public static String NOT_DEFAULT_MUTED() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.VERIFY, "not-default-muted");
    }

    // WALKSPEED
    public static String WALKSPEED_SET(String amount) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WALKSPEED, "walkspeed-set")
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
    }

    public static String WALKSPEED_SET_OTHERS(Player target, String amount) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WALKSPEED, "walkspeed-set-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
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
        return acm.getMessage(MessageType.COMMAND, CommandType.WARN, "default-warn-reason");
    }

    public static String WARN_ADD(Player target, String reason) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warn-add")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD);
    }

    public static String WARN_REMOVE(Player target, String reason) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warn-remove")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD);
    }

    public static String WARN_GET_HEADER(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warn-get-header")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    public static String WARN_GET(String source, String reason, String datecreated, String warnid) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warn-get")
                .replace("%date-created%", ChatColor.AQUA + datecreated + ChatColor.GOLD)
                .replace("%warn-id%", ChatColor.AQUA + warnid + ChatColor.GOLD)
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD)
                .replace("%s_displayname%", ChatColor.AQUA + source + ChatColor.GOLD)
                .replace("%s_playername%", ChatColor.AQUA + source + ChatColor.GOLD);
    }

    public static String WARNED_HEADER() {
        return ChatColor.RED + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warned-header");
    }

    public static String WARNED(String source, String reason) {
        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warned")
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD);
        msg = msg.replace("%s_displayname%", ChatColor.AQUA + source + ChatColor.GOLD);
        msg = msg.replace("%s_playername%", ChatColor.AQUA + source + ChatColor.GOLD);
        return msg;
    }

    public static String WARNED_REMOVE(String reason) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARN, "warned-remove")
                .replace("%reason%", ChatColor.AQUA + reason + ChatColor.GOLD);
    }

    //WARP
    public static String WARP_DOESNT_EXIST() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.WARP, "warp-doesnt-exist");
    }

    public static String WARP_TELEPORT(String warp) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARP, "warp-teleport")
                .replace("%warp%", ChatColor.AQUA + warp + ChatColor.GOLD);
    }

    public static String WARP_TELEPORT_OTHERS(Player target, String warp) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARP, "warp-teleport-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%warp%", ChatColor.AQUA + warp + ChatColor.GOLD);
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
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.SETWARP, "warp-already-exists");
    }

    public static String WARP_SET(String warp) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.SETWARP, "warp-set")
                .replace("%warp%", ChatColor.AQUA + warp + ChatColor.GOLD);
    }

    // DELETEWARP
    public static String DOESNT_EXIST() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.DELETEWARP, "doesnt-exist");
    }

    public static String WARP_DELETED(String warp) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.DELETEWARP, "warp-deleted")
                .replace("%warp%", ChatColor.AQUA + warp + ChatColor.GOLD);
    }

    // WARPS
    public static String WARPS_HEADER() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WARPS, "warps-header");
    }

    public static String NO_WARPS() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.WARPS, "no-warps");
    }

    // WEATHER
    public static String TO_THUNDER(String world) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WEATHER, "to-thunder")
                .replace("%world%", ChatColor.AQUA + world + ChatColor.GOLD);
    }

    public static String TO_RAIN(String world) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WEATHER, "to-rain")
                .replace("%world%", ChatColor.AQUA + world + ChatColor.GOLD);
    }

    public static String TO_CLEAR(String world) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WEATHER, "to-clear")
                .replace("%world%", ChatColor.AQUA + world + ChatColor.GOLD);
    }

    // WHEREAMI
    public static String WHEREAMI(String coords, String world) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHEREAMI, "whereami")
                .replace("%coords%", ChatColor.AQUA + coords + ChatColor.GOLD)
                .replace("%world%", ChatColor.AQUA + world + ChatColor.GOLD);
    }

    public static String WHEREAMI_OTHERS(Player target, String coords, String world) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHEREAMI, "whereami-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%coords%", ChatColor.AQUA + coords + ChatColor.GOLD)
                .replace("%world%", ChatColor.AQUA + world + ChatColor.GOLD);
    }

    // WHOIS
    public static String NAME(String name) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "name")
                .replace("%playername%", ChatColor.AQUA + name + ChatColor.GOLD);
    }

    public static String NICKNAME(String nickname) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "nickname")
                .replace("%displayname%", ChatColor.AQUA + nickname + ChatColor.GOLD);
    }

    public static String COLORNICK(String colornick) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "colornick")
                .replace("%colornick%", ChatColor.AQUA + colornick + ChatColor.GOLD);
    }

    public static String UUID(String uuid) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "uuid")
                .replace("%uuid%", ChatColor.AQUA + uuid + ChatColor.GOLD);
    }

    public static String IS_OP(String op) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "is-op")
                .replace("%op%", ChatColor.AQUA + op + ChatColor.GOLD);
    }

    public static String HEALTH(String health) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "health")
                .replace("%playerhealth%", ChatColor.AQUA + health + ChatColor.GOLD);
    }

    public static String FOOD(String food) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "food")
                .replace("%playerfood%", ChatColor.AQUA + food + ChatColor.GOLD);
    }

    public static String WORLD(String world) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "world")
                .replace("%world%", ChatColor.AQUA + world + ChatColor.GOLD);
    }

    public static String COORDS(String coords) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "coords")
                .replace("%coords%", ChatColor.AQUA + coords + ChatColor.GOLD);
    }

    public static String AFK(String afk) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "afk")
                .replace("%afk%", ChatColor.AQUA + afk + ChatColor.GOLD);
    }

    public static String CLIENT(String client) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "client")
                .replace("%client%", ChatColor.AQUA + client + ChatColor.GOLD);
    }

    public static String ADDRESS(String address) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "address")
                .replace("%ip%", ChatColor.AQUA + address + ChatColor.GOLD);
    }

    public static String GAMEMODE(String gamemode) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "gamemode")
                .replace("%gamemode%", ChatColor.AQUA + gamemode + ChatColor.GOLD);
    }

    public static String MUTED(String muted) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "muted")
                .replace("%muted%", ChatColor.AQUA + muted + ChatColor.GOLD);
    }

    public static String WHITELISTED(String whitelisted) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "whitelisted")
                .replace("%whitelisted%", ChatColor.AQUA + whitelisted + ChatColor.GOLD);
    }

    public static String GOD(String god) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "god")
                .replace("%god%", ChatColor.AQUA + god + ChatColor.GOLD);
    }

    public static String VANISHED(String vanished) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.WHOIS, "vanished")
                .replace("%vanished%", ChatColor.AQUA + vanished + ChatColor.GOLD);
    }

    // XP
    /// ADD
    public static String XP_ADD_LEVELS(String amount) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-add-levels")
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
    }

    public static String XP_ADD_XP(String amount) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-add-xp")
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
    }

    public static String XP_ADD_LEVELS_OTHERS(Player target, String amount) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-add-levels-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
    }

    public static String XP_ADD_LEVELS_OTHERS_MESSAGE(CommandSender sender, String amount) {
        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-add-levels-others-message")
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String XP_ADD_XP_OTHERS(Player target, String amount) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-add-xp-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
    }

    public static String XP_ADD_XP_OTHERS_MESSAGE(CommandSender sender, String amount) {
        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-add-xp-others-message")
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    /// SET
    public static String XP_SET_LEVELS(String amount) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-set-levels")
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
    }

    public static String XP_SET_XP(String amount) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-set-xp")
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
    }

    public static String XP_SET_LEVELS_OTHERS(Player target, String amount) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-set-levels-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
    }

    public static String XP_SET_LEVELS_OTHERS_MESSAGE(CommandSender sender, String amount) {
        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-set-levels-others-message")
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    public static String XP_SET_XP_OTHERS(Player target, String amount) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-set-xp-others")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD)
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
    }

    public static String XP_SET_XP_OTHERS_MESSAGE(CommandSender sender, String amount) {
        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-set-xp-others-message")
                .replace("%amount%", ChatColor.AQUA + amount + ChatColor.GOLD);
        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    /// CLEAR
    public static String XP_CLEAR() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-set-xp");
    }

    public static String XP_CLEAR_OTHERS(Player target) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-set-xp-others-message")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
    }

    public static String XP_CLEAR_OTHERS_MESSAGE(CommandSender sender) {
        String msg = ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.XP, "xp-set-xp-others-message");
        if (sender instanceof Player) {
            msg = msg.replace("%s_displayname%", ChatColor.AQUA + ((Player) sender).getDisplayName() + ChatColor.GOLD);
        } else msg = msg.replace("%s_displayname%", ChatColor.AQUA + sender.getName() + ChatColor.GOLD);
        return msg;
    }

    // LANGUAGE
    public static String CURRENT_LANGUAGE(String language, String code) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LANGUAGE, "current-language")
                .replace("%language%", ChatColor.AQUA + language + ChatColor.GOLD)
                .replace("%code%", ChatColor.AQUA + code + ChatColor.GOLD);
    }

    public static String LANGUAGE_SET(String language, String code) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.LANGUAGE, "language-set")
                .replace("%language%", ChatColor.AQUA + language + ChatColor.GOLD)
                .replace("%code%", ChatColor.AQUA + code + ChatColor.GOLD);
    }

    // AFK
    public static String AFK_TAG() {
        return ChatColor.GRAY + acm.getMessage(MessageType.COMMAND, CommandType.AFK, "afk-tag");
    }

    public static String NOW_AFK(Player target) {
        Profile profile = ActiveCraftCore.getProfile(target);
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.AFK, "now-afk")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + profile.getFullNickname() + ChatColor.GOLD);
    }

    public static String NOT_AFK(Player target) {
        Profile profile = ActiveCraftCore.getProfile(target);
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.AFK, "not-afk")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + profile.getFullNickname() + ChatColor.GOLD);
    }

    public static String NOW_AFK_TARGET() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.AFK, "now-afk-target");
    }

    public static String NOT_AFK_TARGET() {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.AFK, "not-afk-target");
    }

    // DRAIN
    public static String DRAIN_COMPLETE(int amount) {
        return ChatColor.GOLD + acm.getMessage(MessageType.COMMAND, CommandType.DRAIN, "drain-complete")
                .replace("%amount%", ChatColor.AQUA + "" + amount + ChatColor.GOLD);
    }
}