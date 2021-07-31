package de.silencio.activecraftcore.utils;

import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Date;

public class BanManager {

    BanList banList;

    public BanManager(BanList.Type type) {
        this.banList = Bukkit.getBanList(type);
    }

    public boolean isBanned(String target) {
        return banList.isBanned(target);
    }

    public void ban(String target, String reason, Date expires, String source) {
        banList.addBan(target, reason, expires, source);
    }

    public void ban(Player target, String reason, Date expires, String source) {
        banList.addBan(target.getName(), reason, expires, source);
    }

    public BanList getBanList() {
        return banList;
    }

    public void unban(String target) {
        banList.pardon(target);
    }
    public void unban(Player target) {
        banList.pardon(target.getName());
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