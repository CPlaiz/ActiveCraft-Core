package de.silencio.activecraftcore.manager;

import de.silencio.activecraftcore.events.PlayerWarpEvent;
import de.silencio.activecraftcore.events.WarpCreateEvent;
import de.silencio.activecraftcore.events.WarpDeleteEvent;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarpManager {

    public static Location getWarp(String name) {
        FileConfig warpsConfig = new FileConfig("warps.yml");
        return warpsConfig.getLocation(name);
    }

    public static void createWarp(String name, Location location) {
        FileConfig warpListConfig = new FileConfig("warplist.yml");
        FileConfig warpsConfig = new FileConfig("warps.yml");

        //call event
        WarpCreateEvent event = new WarpCreateEvent(location, name);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;

        //add permissions
        Map<String, Boolean> childMap = new HashMap<>();
        childMap.put("activecraft.warp.self", true);
        childMap.put("activecraft.warp", true);
        Bukkit.getPluginManager().addPermission(new Permission("activecraft.warp.self." + name,
                "Permission to warp yourself to a specific warp.", PermissionDefault.OP, childMap));
        childMap.clear();
        childMap.put("activecraft.warp.others", true);
        childMap.put("activecraft.warp", true);
        Bukkit.getPluginManager().addPermission(new Permission("activecraft.warp.others." + name,
                "Permission to warp another player to a specific warp.", PermissionDefault.OP, childMap));

        //add warp to config
        List<String> warpList = warpListConfig.getStringList("warplist");
        if (!warpList.contains(name)) {
            warpList.add(name);
        }
        warpListConfig.set("warplist", warpList);
        warpListConfig.saveConfig();
        warpsConfig.set(event.getWarpName(), event.getLocation());
        warpsConfig.saveConfig();
    }

    public static void deleteWarp(String name) {
        FileConfig warpListConfig = new FileConfig("warplist.yml");
        FileConfig warpsConfig = new FileConfig("warps.yml");

        //call event
        WarpDeleteEvent event = new WarpDeleteEvent(getWarp(name), name);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;

        //remove permissions
        Bukkit.getPluginManager().removePermission("activecraft.warp.self." + name);
        Bukkit.getPluginManager().removePermission("activecraft.warp.others." + name);

        //add warp to config
        List<String> warpList = warpListConfig.getStringList("warplist");
        warpList.remove(name);
        warpListConfig.set("warplist", warpList);
        warpListConfig.saveConfig();
        warpsConfig.set(event.getWarpName(), null);
        warpsConfig.saveConfig();
    }

    public static void warp(Player player, String warpName) {
        //call event
        PlayerWarpEvent event = new PlayerWarpEvent(player, getWarp(warpName), warpName);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        //teleport
        player.teleport(event.getLocation());
    }
}
