package de.silencio.activecraftcore.playermanagement;

import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.StringUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class Profile {

    public enum Value {
        NAME,
        NICKNAME,
        LAST_ONLINE,
        UUID,
        COLOR_NICK,
        FLYSPEED,
        TIMES_JOINED,
        WARNS,
        MUTES,
        BANS,
        IP_BANS,
        PLAYTIME_MINUTES,
        PLAYTIME_HOURS,
        AFK,
        OP,
        WHITELISTED,
        GODMODE,
        FLY,
        MUTED,
        DEFAULTMUTED,
        VANISHED,
        LOG_ENABLED,
        BYPASS_LOCKDOWN,
        EDIT_SIGN,
        HOME_LIST,
        RECEIVE_SOCIALSPY,
        LAST_LOCATION,
        EFFECTS,
        FORCE_MUTED;

        Value() {
        }
    }


    private FileConfig playerdataConfig;
    private FileConfig playtimeConfig;
    private FileConfig homeConfig;
    private String name;
    private String nickname;
    private String last_online;
    private UUID uuid;
    private ChatColor color_nick;
    private float flyspeed;
    private int times_joined;
    private int warns;
    private int mutes;
    private int bans;
    private int ip_bans;
    private int playtime_minutes;
    private int playtime_hours;
    private boolean afk;
    private boolean op;
    private boolean whitelisted;
    private boolean godmode;
    private boolean fly;
    private boolean muted;
    private boolean forcemuted;
    private boolean defaultmuted;
    private boolean vanished;
    private boolean log_enabled;
    private boolean bypass_lockdown;
    private boolean edit_sign;
    private boolean receive_socialspy;
    private List<String> tags;

    private HashMap<String, Location> homeList;
    private HashMap<String, Location> lastLocations;
    private HashMap<Effect, Boolean> effects;

    public Profile(Player player) {
        this(player.getName());
    }

    public Profile(String playername) {
        name = playername;
        refresh();
    }

    public void refresh() {
        this.playerdataConfig = new FileConfig("playerdata" + File.separator + name.toLowerCase() + ".yml");
        this.playtimeConfig = new FileConfig("playtime.yml");
        this.homeConfig = new FileConfig("homes.yml");
        loadFromConfig(playerdataConfig);
    }

    private void loadFromConfig(FileConfig fileConfig) {
        name = fileConfig.getString("name");
        nickname = fileConfig.getString("nickname");
        last_online = fileConfig.getString("last-online");
        uuid = UUID.fromString(fileConfig.getString("uuid"));
        color_nick = ChatColor.valueOf(fileConfig.getString("colornick"));
        flyspeed = (float) fileConfig.getDouble("flyspeed");
        warns = fileConfig.getInt("violations.warns");
        mutes = fileConfig.getInt("violations.mutes");
        bans = fileConfig.getInt("violations.bans");
        ip_bans = fileConfig.getInt("violations.ip-bans");
        times_joined = fileConfig.getInt("times-joined");
        afk = fileConfig.getBoolean("afk");
        op = fileConfig.getBoolean("op");
        whitelisted = fileConfig.getBoolean("whitelisted");
        godmode = fileConfig.getBoolean("godmode");
        fly = fileConfig.getBoolean("fly");
        muted = fileConfig.getBoolean("muted");
        forcemuted = fileConfig.getBoolean("forcemuted");
        defaultmuted = fileConfig.getBoolean("default-mute");
        vanished = fileConfig.getBoolean("vanished");
        log_enabled = fileConfig.getBoolean("log-enabled");
        bypass_lockdown = fileConfig.getBoolean("lockdown-bypass");
        edit_sign = fileConfig.getBoolean("edit-sign");
        tags = fileConfig.getStringList("tags");
        receive_socialspy = fileConfig.getBoolean("receive-socialspy");

        playtime_minutes = playtimeConfig.getInt(name + ".minutes");
        playtime_hours = playtimeConfig.getInt(name + ".hours");

        lastLocations = new HashMap<>();
        for (World world : Bukkit.getWorlds())
            lastLocations.put(world.getName(), fileConfig.getLocation("last-location." + world.getName()));

        homeList = new HashMap<>();
        for (String homeName : homeConfig.getStringList(name + ".home_list"))
            homeList.put(homeName, homeConfig.getLocation(name + "." + homeName));

        effects = new HashMap<>();
        for (Effect effect : Effect.values())
            effects.put(effect, playerdataConfig.getBoolean("effects." + effect.name().toLowerCase()));

    }

    public void set(Value value, Object object) {
        switch (value) {
            case NAME -> playerdataConfig.set("name", object);
            case NICKNAME -> playerdataConfig.set("nickname", object);
            case LAST_ONLINE -> playerdataConfig.set("last-online", object);
            case UUID -> playerdataConfig.set("uuid", object);
            case COLOR_NICK -> playerdataConfig.set("colornick", object);
            case FLYSPEED -> playerdataConfig.set("flyspeed", object);
            case TIMES_JOINED -> playerdataConfig.set("times_joined", object);
            case WARNS -> playerdataConfig.set("violations.warns", object);
            case MUTES -> playerdataConfig.set("violations.mutes", object);
            case BANS -> playerdataConfig.set("violations.bans", object);
            case IP_BANS -> playerdataConfig.set("violations.ip-bans", object);
            case PLAYTIME_MINUTES -> playtimeConfig.set(name + ".minutes", object);
            case PLAYTIME_HOURS -> playtimeConfig.set(name + ".hours", object);
            case AFK -> playerdataConfig.set("afk", object);
            case OP -> playerdataConfig.set("op", object);
            case WHITELISTED -> playerdataConfig.set("whitelisted", object);
            case GODMODE -> playerdataConfig.set("godmode", object);
            case FLY -> playerdataConfig.set("fly", object);
            case MUTED -> playerdataConfig.set("muted", object);
            case DEFAULTMUTED -> playerdataConfig.set("default-mute", object);
            case VANISHED -> playerdataConfig.set("vanished", object);
            case LOG_ENABLED -> playerdataConfig.set("log-enabled", object);
            case BYPASS_LOCKDOWN -> playerdataConfig.set("lockdown-bypass", object);
            case EDIT_SIGN -> playerdataConfig.set("edit-sign", object);
            case FORCE_MUTED -> playerdataConfig.set("forcemuted", object);
            case RECEIVE_SOCIALSPY -> playerdataConfig.set("receive-socialspy", object);
        }

        playerdataConfig.saveConfig();
        playtimeConfig.saveConfig();

        refresh();
    }

    public void set(Value value, String deepPath, Object object) {
        switch (value) {
            case EFFECTS -> playerdataConfig.set("effects." + deepPath, object);
            case LAST_LOCATION -> playerdataConfig.set("last-location." + deepPath, object);
            case HOME_LIST -> homeConfig.set(name + "." + deepPath, object);
        }
        playerdataConfig.saveConfig();
        homeConfig.saveConfig();
    }

    public void clearTags() {
        tags.clear();
        playerdataConfig.set("tags", this.tags);
        playerdataConfig.saveConfig();
        applyTags();
    }

    public void addTag(String... tags) {
        for (String tag : tags)
            if (!this.tags.contains(tag)) this.tags.add(tag);
        playerdataConfig.set("tags", this.tags);
        playerdataConfig.saveConfig();
        applyTags();
    }

    public void removeTag(String... tags) {
        for (String tag : tags)
            this.tags.remove(tag);
        playerdataConfig.set("tags", this.tags);
        playerdataConfig.saveConfig();
        applyTags();
    }

    public void setTags(String... tags) {
        this.tags.clear();
        addTag(tags);
    }

    public void applyTags() {
        Player player = Bukkit.getPlayer(name);
        if (player == null) return;
        List<String> appliedTags = tags;
        Collections.sort(appliedTags);
        Collections.reverse(appliedTags);
        StringUtils.setDisplaynameFromConfig(player, color_nick, nickname + (tags.size() > 0 ? " " : "") + StringUtils.combineList(appliedTags));
    }

    public String getProfileOwner() {
        return name;
    }

    public FileConfig getPlayerdataConfig() {
        return playerdataConfig;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getLastOnline() {
        return last_online;
    }

    public UUID getUuid() {
        return uuid;
    }

    public ChatColor getColorNick() {
        return color_nick;
    }

    public float getFlyspeed() {
        return flyspeed;
    }

    public int getWarns() {
        return warns;
    }

    public int getMutes() {
        return mutes;
    }

    public int getBans() {
        return bans;
    }

    public int getIpBans() {
        return ip_bans;
    }

    public int getPlaytimeHours() {
        return playtime_hours;
    }

    public int getPlaytimeMinutes() {
        return playtime_minutes;
    }

    public boolean isAfk() {
        return afk;
    }

    public boolean isOp() {
        return op;
    }

    public boolean isWhitelisted() {
        return whitelisted;
    }

    public boolean isGodmode() {
        return godmode;
    }

    public boolean canFly() {
        return fly;
    }

    public boolean isMuted() {
        return muted;
    }

    public boolean isDefaultmuted() {
        return defaultmuted;
    }

    public boolean isVanished() {
        return vanished;
    }

    public boolean hasLogEnabled() {
        return log_enabled;
    }

    public boolean canBypassLockdown() {
        return bypass_lockdown;
    }

    public boolean canEditSign() {
        return edit_sign;
    }

    public HashMap<String, Location> getHomeList() {
        return homeList;
    }

    public boolean isForcemuted() {
        return forcemuted;
    }

    public boolean canReceiveSocialspy() {
        return receive_socialspy;
    }

    public Location getLastLocation(String world) {
        return playerdataConfig.getLocation("last-location." + world);
    }

    public Location getLastLocationBeforeQuit() {
        return playerdataConfig.getLocation("last-location.BEFORE_QUIT");
    }

    public int getTimesJoined() {
        return times_joined;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
