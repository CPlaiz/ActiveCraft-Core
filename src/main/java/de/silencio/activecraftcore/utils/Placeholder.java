package de.silencio.activecraftcore.utils;

import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.File;

public class Placeholder {

    public enum Type {
        PLAYERNAME,
        PING,
        CAN_PICKUP_ITEMS,
        EXP,
        FLYSPEED,
        WALKSPEED,
        GAMEMODE,
        FOOD_LEVEL,
        HEALTH_LEVEL,
        ALLOW_FLIGHT,
        IS_WHITELISTED,
        IS_BANNED,
        DISPLAYNAME,
        PLAYERS_IN_WORLD,
        IP,
        IS_IP_BANNED,
        IS_FLYING,
        IS_OP,
        UUID,
        WORLD_NAME,
        COORDS,
        FACING,
        MAX_PLAYERS,
        UPTIME,
        UNIQUE_JOINS,
        RAM_USED,
        RAM_FREE,
        RAM_TOTAL,
        TPS_1M,
        TPS_5M,
        TPS_15M,
        WHITELIST_ENABLED,
        WORLD_SEED,
        PVP_ENABLED,
        IS_AFK,
        IS_GOD,
        IS_MUTED,
        IS_VANISHED,
        PLAYTIME,
        TIMES_JOINED,
        TIMES_LEFT,
        DIFFICULTY;

        Type() {
        }
    }

    private Player player;
    private String edited = "";


    public Placeholder(Player player) {
        this.player = player;
    }

    public String replace(String target, Type type) {

        FileConfig playerdataconfig = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");
        Server server = player.getServer();
        World world = player.getWorld();
        Location location = player.getLocation();
        Runtime runtime = Runtime.getRuntime();
        int durch = 1024*1024;

        switch (type) {
            case DISPLAYNAME:
                edited = target.replace("%displayname%", player.getDisplayName());
                break;
            case PLAYERNAME:
                edited = target.replace("%playername%", player.getName());
                break;
            case PING:
                edited = target.replace("%ping%", player.getPing() + ""); 
                break;
            case PLAYERS_IN_WORLD:
                edited = target.replace("%players_in_world%", world.getPlayerCount() + "");
                break;
            case ALLOW_FLIGHT:
                edited = target.replace("%allow_flight%", player.getAllowFlight() + "");
                break;
            case CAN_PICKUP_ITEMS:
                edited = target.replace("%can_pickup_items%", player.getCanPickupItems() + "");
                break;
            case EXP:
                edited = target.replace("%exp%", player.getExp() + "");
                break;
            case FLYSPEED:
                edited = target.replace("%flyspeed%", player.getFlySpeed() + "");
                break;
            case WALKSPEED:
                edited = target.replace("%walkspeed%", player.getWalkSpeed() + "");
                break;
            case GAMEMODE:
                edited = target.replace("%gamemode%", player.getGameMode().name());
                break;
            case FOOD_LEVEL:
                edited = target.replace("%food_level%", player.getFoodLevel() + "");
                break;
            case HEALTH_LEVEL:
                edited = target.replace("%health_level%", player.getHealthScale() + "");
                break;
            case IP:
                edited = target.replace("%ip%", player.getAddress().toString().replace("/", ""));
                break;
            case IS_WHITELISTED:
                edited = target.replace("%is_whitelisted%", player.isWhitelisted() + "");
                break;
            case IS_BANNED:
                edited = target.replace("%is_banned%", player.isBanned() + "");
                break;
            case IS_IP_BANNED:
                edited = target.replace("%is_ip_banned%", Bukkit.getBanList(BanList.Type.IP).isBanned(player.getAddress().toString().replace("/", "")) + "");
                break;
            case IS_FLYING:
                edited = target.replace("%is_flying%", player.isFlying() + "");
                break;
            case IS_OP:
                edited = target.replace("%is_op%", player.isOp() + "");
                break;
            case UUID:
                edited = target.replace("%uuid%", player.getUniqueId() + "");
                break;
            case WORLD_NAME:
                edited = target.replace("%world_name%", world.getName());
                break;
            case COORDS:
                edited = target.replace("%coords%", location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ());
                break;
            case FACING:
                edited = target.replace("%facing%", player.getLocation() + ""); // TODO: 11.08.2021
                break;
            case MAX_PLAYERS:
                edited = target.replace("%max_players%", server.getMaxPlayers() + "");
                break;
            case UPTIME:
                edited = target.replace("%uptime%", ""); // TODO: 11.08.2021
                break;
            case UNIQUE_JOINS:
                edited = target.replace("%unique_joins%", ""); // TODO: 11.08.2021
                break;
            case RAM_USED:
                edited = target.replace("%ram_used%", (runtime.totalMemory()/durch - runtime.freeMemory()/durch) + "");
                break;
            case RAM_FREE:
                edited = target.replace("%ram_free%", runtime.freeMemory()/durch + "");
                break;
            case RAM_TOTAL:
                edited = target.replace("%ram_total%", runtime.maxMemory()/durch + "");
                break;
            case TPS_1M:
                edited = target.replace("%tps_1%", Bukkit.getTPS() + "");
                break;
            case TPS_5M:
                edited = target.replace("%tps_5%", Bukkit.getTPS() + "");
                break;
            case TPS_15M:
                edited = target.replace("%tps_15%", Bukkit.getTPS() + "");
                break;
            case WHITELIST_ENABLED:
                edited = target.replace("%whitelist_enabled%", Bukkit.hasWhitelist() + "");
                break;
            case WORLD_SEED:
                edited = target.replace("%world_seed%", world.getSeed() + "");
                break;
            case PVP_ENABLED:
                edited = target.replace("%pvp_enabled%", world.getPVP() + "");
                break;
            case IS_AFK:
                edited = target.replace("%is_afk%", playerdataconfig.get("afk").toString());
                break;
            case IS_GOD:
                edited = target.replace("%is_god%", playerdataconfig.get("godmode").toString());
                break;
            case IS_MUTED:
                edited = target.replace("%is_muted%", playerdataconfig.get("muted").toString());
                break;
            case IS_VANISHED:
                edited = target.replace("%is_vanished%", playerdataconfig.get("vanished").toString());
                break;
            case PLAYTIME:
                FileConfig playtimeConfig = new FileConfig("playtime");
                edited = target.replace("%playtime%", playtimeConfig.getInt(player.getName() + ".minutes") + playtimeConfig.getInt(player.getName() + ".hours") + "");
                break;
            case TIMES_JOINED:
                edited = target.replace("%times_joined%", playerdataconfig.getInt("times-joined") + "");
                break;
            case TIMES_LEFT:
                edited = target.replace("%times_left%", player.getDisplayName()); // TODO: 19.08.2021
                break;
            case DIFFICULTY:
                edited = target.replace("%difficulty%", world.getDifficulty().toString());
                break;
        }
        return edited;
    }
}
