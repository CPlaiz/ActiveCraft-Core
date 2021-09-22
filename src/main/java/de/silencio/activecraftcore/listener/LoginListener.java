package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.manager.BanManager;
import de.silencio.activecraftcore.utils.IntegerUtils;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.Date;

public class LoginListener implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        BanList ipBanList = Bukkit.getBanList(BanList.Type.IP);
        BanList nameBanList = Bukkit.getBanList(BanList.Type.NAME);
        if (e.getPlayer().isBanned()) {
            e.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            BanEntry banEntry = nameBanList.getBanEntry(e.getPlayer().getName());
            Date expirationDate = banEntry.getExpiration();
            long expirationTime = expirationDate.getTime();
            Date nowDate = new Date();
            expirationTime = expirationTime - nowDate.getTime();
            expirationTime = expirationTime / 1000;
            String expirationString;
            if (expirationTime < 86400) {
                expirationString = IntegerUtils.shortInteger((int) expirationTime);
            } else {
                expirationString = expirationTime / 86400 + " days";
            }
            e.disallow(e.getResult(), ChatColor.RED + "You are banned from this server!"
                    + ChatColor.GOLD + "\n \nYou can join the server in " + ChatColor.AQUA + expirationString
                    + ChatColor.GOLD + "\n Reason: " + ChatColor.AQUA + banEntry.getReason() + "\n ");
        } else if (ipBanList.isBanned(e.getAddress().toString())) {
            e.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            BanEntry banEntry = ipBanList.getBanEntry(e.getPlayer().getName());
            Date expirationDate = banEntry.getExpiration();
            long expirationTime = expirationDate.getTime();
            Date nowDate = new Date();
            expirationTime = expirationTime - nowDate.getTime();
            expirationTime = expirationTime / 1000;
            String expirationString;
            if (expirationTime < 86400) {
                expirationString = IntegerUtils.shortInteger((int) expirationTime);
            } else {
                expirationString = expirationTime / 86400 + " days";
            }
            e.disallow(e.getResult(), ChatColor.RED + "Your IP is banned from this server!"
                    + ChatColor.GOLD + "\n \nYou can join the server in " + ChatColor.AQUA + expirationString
                    + ChatColor.GOLD + "\n Reason: " + ChatColor.AQUA + banEntry.getReason() + "\n ");
        }
    }
}
