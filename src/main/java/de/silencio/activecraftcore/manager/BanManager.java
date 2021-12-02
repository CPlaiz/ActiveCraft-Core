package de.silencio.activecraftcore.manager;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.events.*;
import de.silencio.activecraftcore.utils.StringUtils;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Date;

public class BanManager {

    BanList banList;
    BanList.Type banListType;

    public BanManager(BanList.Type type) {
        this.banList = Bukkit.getBanList(type);
        this.banListType = type;
    }

    public boolean isBanned(String target) {
        return banList.isBanned(target);
    }

    public void ban(String target, String reason, Date expires, String source) {

        if (banListType == BanList.Type.NAME) {
            Bukkit.getScheduler().runTask(ActiveCraftCore.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    PlayerBanEvent event = new PlayerBanEvent(target, reason, expires, source);
                    Bukkit.getPluginManager().callEvent(event);
                    if (event.isCancelled()) return;
                }
            });
        } else if (banListType == BanList.Type.IP) {
            if (!StringUtils.isValidInet4Address(target)) {
                return;
            }
            PlayerIpBanEvent event = new PlayerIpBanEvent(target, reason, expires, source);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) return;
        }
        banList.addBan(target, reason, expires, source);
    }

    public void ban(Player target, String reason, Date expires, String source) {
        ban(target.getName(), reason, expires, source);
    }


    public BanList getBanList() {
        return banList;
    }

    public void unban(String target) {
        if (banListType == BanList.Type.NAME) {
            PlayerUnbanEvent event = new PlayerUnbanEvent(target);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) return;
        } else if (banListType == BanList.Type.IP) {
            if (!StringUtils.isValidInet4Address(target)) {
                return;
            }
            PlayerIpUnbanEvent event = new PlayerIpUnbanEvent(target);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) return;
        }
        banList.pardon(target);
    }

    public void unban(Player target) {
        unban(target.getName());
    }

    public java.util.Set<org.bukkit.BanEntry> getBans() {
        return banList.getBanEntries();
    }

    public BanEntry getBanEntry(String target) {
        return banList.getBanEntry(target);
    }

    public BanEntry getBanEntry(Player target) {
        return banList.getBanEntry(target.getName());
    }
}