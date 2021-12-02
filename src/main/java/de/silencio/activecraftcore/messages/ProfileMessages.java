package de.silencio.activecraftcore.messages;

import de.silencio.activecraftcore.ActiveCraftCore;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ProfileMessages {

    static ActiveCraftMessage acm = ActiveCraftCore.getPlugin().getActiveCraftMessage();

    public static String MAINPROFILE_TITLE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "mainprofile.title");
        return msg;
    }

    public static String MAINPROFILE_EMPTY_SLOT() {

        String msg = ChatColor.RED + acm.getMessage(MessageType.PROFILE, "mainprofile.empty-slot");
        return msg;
    }

    public static String MAINPROFILE_CONNECTION_TITLE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "mainprofile.connection.connection-title");
        return msg;
    }

    public static String MAINPROFILE_CONNECTION_IP(String ip) {

        String msg = ChatColor.DARK_AQUA + acm.getMessage(MessageType.PROFILE, "mainprofile.connection.connection-ip")
                .replace("%ip%",ChatColor.GRAY +  ip + ChatColor.DARK_AQUA);
        return msg;
    }

    public static String MAINPROFILE_CONNECTION_PORT(int port) {

        String msg = ChatColor.DARK_AQUA + acm.getMessage(MessageType.PROFILE, "mainprofile.connection.connection-port")
                .replace("%port%",ChatColor.GRAY + "" + port + ChatColor.DARK_AQUA);
        return msg;
    }

    public static String MAINPROFILE_CONNECTION_PING(int ping) {

        String msg = ChatColor.DARK_AQUA + acm.getMessage(MessageType.PROFILE, "mainprofile.connection.connection-ping")
                .replace("%ping%",ChatColor.GRAY + "" + ping + ChatColor.DARK_AQUA);
        return msg;
    }

    public static String MAINPROFILE_PLAYER_TITLE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "mainprofile.player.player-title");
        return msg;
    }

    public static String MAINPROFILE_PLAYER_HEALTH(String health) {

        String msg = ChatColor.DARK_AQUA + acm.getMessage(MessageType.PROFILE, "mainprofile.player.player-health")
                .replace("%health%",ChatColor.GRAY + "" + health + ChatColor.DARK_AQUA);
        return msg;
    }

    public static String MAINPROFILE_PLAYER_FOOD(int food) {

        String msg = ChatColor.DARK_AQUA + acm.getMessage(MessageType.PROFILE, "mainprofile.player.player-food")
                .replace("%food%", ChatColor.GRAY + "" + food + ChatColor.DARK_AQUA);
        return msg;
    }

    public static String MAINPROFILE_PLAYER_EXP(float exp) {

        String msg = ChatColor.DARK_AQUA + acm.getMessage(MessageType.PROFILE, "mainprofile.player.player-exp")
                .replace("%xp%", ChatColor.GRAY + "" + exp + ChatColor.DARK_AQUA);
        return msg;
    }

    public static String MAINPROFILE_GAMEMODE(String gamemode) {

        String msg = ChatColor.DARK_AQUA + acm.getMessage(MessageType.PROFILE, "mainprofile.player.gamemode")
                .replace("%gamemode%", ChatColor.GRAY + gamemode + ChatColor.DARK_AQUA);
        return msg;
    }

    public static String MAINPROFILE_VIOLATIONS_TITLE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "mainprofile.violations.violations-title");
        return msg;
    }

    public static String MAINPROFILE_VIOLATIONS_BANS(int bans) {

        String msg = ChatColor.DARK_AQUA + acm.getMessage(MessageType.PROFILE, "mainprofile.violations.violations-bans")
                .replace("%bans%", ChatColor.GRAY + "" + bans + ChatColor.DARK_AQUA);
        return msg;
    }

    public static String MAINPROFILE_VIOLATIONS_IP_BANS(int ipbans) {

        String msg = ChatColor.DARK_AQUA + acm.getMessage(MessageType.PROFILE, "mainprofile.violations.violations-ip-bans")
                .replace("%ipbans%", ChatColor.GRAY + "" + ipbans + ChatColor.DARK_AQUA);
        return msg;
    }

    public static String MAINPROFILE_VIOLATIONS_WARNS(int warns) {

        String msg = ChatColor.DARK_AQUA + acm.getMessage(MessageType.PROFILE, "mainprofile.violations.violations-warns")
                .replace("%warns%", ChatColor.GRAY + "" + warns + ChatColor.DARK_AQUA);
        return msg;
    }

    public static String MAINPROFILE_VIOLATIONS_MUTES(int mutes) {

        String msg = ChatColor.DARK_AQUA + acm.getMessage(MessageType.PROFILE, "mainprofile.violations.violations-mutes")
                .replace("%mutes%", ChatColor.GRAY + "" + mutes + ChatColor.DARK_AQUA);
        return msg;
    }

    public static String MAINPROFILE_ACTIVE_EFFECTS() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "mainprofile.active-effects");
        return msg;
    }

    public static String MAINPROFILE_PLAYER_LOCATION() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "mainprofile.player-location");
        return msg;
    }

    public static String MAINPROFILE_PLAYTIME() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "mainprofile.playtime");
        return msg;
    }

    public static String MAINPROFILE_PLAYTIME_LORE(int hours, int minutes) {

        String msg = ChatColor.DARK_AQUA + acm.getMessage(MessageType.PROFILE, "mainprofile.playtime-lore")
                .replace("%hours%", ChatColor.DARK_AQUA + "" + hours + "" + ChatColor.GRAY + "")
                .replace("%minutes%", ChatColor.DARK_AQUA + "" + minutes + "" + ChatColor.GRAY + "");
        return msg;
    }

    public static String MAINPROFILE_VIOLATIONS_GUI() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "mainprofile.violations-gui");
        return msg;
    }

    public static String MAINPROFILE_GAMEMODE_SWITCHER_GUI() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "mainprofile.gamemode-switcher-gui");
        return msg;
    }

    public static String MAINPROFILE_STORAGE_GUI() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "mainprofile.storage-gui");
        return msg;
    }

    public static String MAINPROFILE_ACTION_GUI() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "mainprofile.action-gui");
        return msg;
    }

    public static String VIOLATIONS_GUI_TITLE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "violations-gui.title");
        return msg;
    }

    public static String VIOLATIONS_GUI_BAN(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "violations-gui.ban")
                .replace("%t_playername%", target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String VIOLATIONS_GUI_WARN(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "violations-gui.warn")
                .replace("%t_playername%", target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String VIOLATIONS_GUI_KICK(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "violations-gui.kick")
                .replace("%t_playername%", target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String VIOLATIONS_GUI_MUTE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "violations-gui.mute")
                .replace("%t_playername%", target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String VIOLATIONS_GUI_MUTE_LORE(Player target) {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.PROFILE, "violations-gui.mute-lore")
                .replace("%t_playername%", target.getName() + ChatColor.GRAY)
                .replace("%t_displayname%", target.getDisplayName() + ChatColor.GRAY);
        return msg;
    }

    public static String VIOLATIONS_GUI_UNMUTE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "violations-gui.unmute")
                .replace("%t_playername%", target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String VIOLATIONS_GUI_UNMUTE_LORE(Player target) {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.PROFILE, "violations-gui.unmute-lore")
                .replace("%t_playername%", target.getName() + ChatColor.GRAY)
                .replace("%t_displayname%", target.getDisplayName() + ChatColor.GRAY);
        return msg;
    }

    public static String VIOLATIONS_GUI_BAN_IP(Player target, String ip) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "violations-gui.ban-ip")
                .replace("%t_playername%", target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", target.getDisplayName() + ChatColor.GOLD)
                .replace("%ip%", ChatColor.AQUA + ip + ChatColor.GOLD);
        return msg;
    }

    public static String GAMEMODE_SWITCHER_GUI_TITLE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "gamemode-switcher-gui.title");
        return msg;
    }

    public static String GAMEMODE_SWITCHER_GUI_CREATIVE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "gamemode-switcher-gui.creative")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String GAMEMODE_SWITCHER_GUI_SURVIVAL(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "gamemode-switcher-gui.survival")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String GAMEMODE_SWITCHER_GUI_SPECTATOR(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "gamemode-switcher-gui.spectator")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String GAMEMODE_SWITCHER_GUI_ADVENTURE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "gamemode-switcher-gui.adventure")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String GAMEMODE_SWITCHER_GUI_CURRENT_GAMEMODE_CREATIVE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "gamemode-switcher-gui.current-gamemode-creative")
                .replace(":", ":" + ChatColor.AQUA);
        return msg;
    }

    public static String GAMEMODE_SWITCHER_GUI_CURRENT_GAMEMODE_SURVIVAL() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "gamemode-switcher-gui.current-gamemode-survival")
                .replace(":", ":" + ChatColor.AQUA);
        return msg;
    }

    public static String GAMEMODE_SWITCHER_GUI_CURRENT_GAMEMODE_SPECTATOR() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "gamemode-switcher-gui.current-gamemode-spectator")
                .replace(":", ":" + ChatColor.AQUA);
        return msg;
    }

    public static String GAMEMODE_SWITCHER_GUI_CURRENT_GAMEMODE_ADVENTURE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "gamemode-switcher-gui.current-gamemode-adventure")
                .replace(":", ":" + ChatColor.AQUA);
        return msg;
    }

    public static String STORAGE_GUI_TITLE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "storage-gui.title");
        return msg;
    }

    public static String STORAGE_GUI_OPEN_ENDERCHEST(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "storage-gui.open-enderchest")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String STORAGE_GUI_OPEN_INVENTORY(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "storage-gui.open-inventory")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String STORAGE_GUI_OPEN_ARMORINV(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "storage-gui.open-armorinv")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String STORAGE_GUI_OPEN_ARMORINV_LORE() {

        String msg = ChatColor.RED + acm.getMessage(MessageType.PROFILE, "storage-gui.open-armorinv-lore");
        return msg;
    }

    public static String ACTION_GUI_TITLE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "action-gui.title");
        return msg;
    }

    public static String ACTION_GUI_VANISH_UNVANISH(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "action-gui.vanish.unvanish")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String ACTION_GUI_VANISH_UNVANISH_LORE(Player target) {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.PROFILE, "action-gui.vanish.unvanish-lore")
                .replace("%t_playername%", ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY)
                .replace("%t_displayname%", ChatColor.DARK_AQUA + target.getDisplayName() + ChatColor.GRAY);
        return msg;
    }

    public static String ACTION_GUI_VANISH_VANISH(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "action-gui.vanish.vanish")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String ACTION_GUI_VANISH_VANISH_LORE(Player target) {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.PROFILE, "action-gui.vanish.vanish-lore")
                .replace("%t_playername%", ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY)
                .replace("%t_displayname%", ChatColor.DARK_AQUA + target.getDisplayName() + ChatColor.GRAY);
        return msg;
    }

    public static String ACTION_GUI_GOD_DISABLE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "action-gui.god.disable")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String ACTION_GUI_GOD_DISABLE_LORE(Player target) {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.PROFILE, "action-gui.god.disable-lore")
                .replace("%t_playername%", ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY)
                .replace("%t_displayname%", ChatColor.DARK_AQUA + target.getDisplayName() + ChatColor.GRAY);
        return msg;
    }

    public static String ACTION_GUI_GOD_ENABLE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "action-gui.god.enable")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String ACTION_GUI_GOD_ENABLE_LORE(Player target) {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.PROFILE, "action-gui.god.enable-lore")
                .replace("%t_playername%", ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY)
                .replace("%t_displayname%", ChatColor.DARK_AQUA + target.getDisplayName() + ChatColor.GRAY);
        return msg;
    }

    public static String ACTION_GUI_FLY_DISABLE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "action-gui.fly.disable")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String ACTION_GUI_FLY_DISABLE_LORE(Player target) {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.PROFILE, "action-gui.fly.disable-lore")
                .replace("%t_playername%", ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY)
                .replace("%t_displayname%", ChatColor.DARK_AQUA + target.getDisplayName() + ChatColor.GRAY);
        return msg;
    }

    public static String ACTION_GUI_FLY_ENABLE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "action-gui.fly.enable")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String ACTION_GUI_FLY_ENABLE_LORE(Player target) {

        String msg = ChatColor.GRAY + acm.getMessage(MessageType.PROFILE, "action-gui.fly.enable-lore")
                .replace("%t_playername%", ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY)
                .replace("%t_displayname%", ChatColor.DARK_AQUA + target.getDisplayName() + ChatColor.GRAY);
        return msg;
    }

    public static String ACTION_GUI_CLEAR(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "action-gui.clear")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String ACTION_GUI_TELEPORT_THERE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "action-gui.teleport-there")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String ACTION_GUI_TELEPORT_HERE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "action-gui.teleport-here")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String ACTION_GUI_HOMES(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "action-gui.homes")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String ACTION_GUI_HEAL(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "action-gui.heal")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String ACTION_GUI_FEED(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "action-gui.feed")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String ACTION_GUI_STRIKE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "action-gui.strike")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String ACTION_GUI_EXPLODE(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "action-gui.explode")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String ACTION_GUI_KILL(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "action-gui.kill")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }

    public static String HOMELIST_TITLE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "homelist.title");
        return msg;
    }

    public static String HOMELIST_NEXT_PAGE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "homelist.next-page");
        return msg;
    }

    public static String HOMELIST_PREVIOUS_PAGE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "homelist.previous-page");
        return msg;
    }

    public static String HOMELIST_NO_HOMES(Player target) {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.PROFILE, "homelist.no-homes")
                .replace("%t_playername%", ChatColor.AQUA + target.getName() + ChatColor.GOLD)
                .replace("%t_displayname%", ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD);
        return msg;
    }
}
