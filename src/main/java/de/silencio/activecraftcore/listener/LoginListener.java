package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.utils.TimeUtils;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.Date;

public class LoginListener implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        String ipAddress = e.getAddress().toString().replace("/", "");
        boolean ipBanned = false;
        BanList ipBanList = Bukkit.getBanList(BanList.Type.IP);
        for (BanEntry banEntry : ipBanList.getBanEntries()) {
            if (banEntry.getTarget().equals(ipAddress)) {
                ipBanned = true;
                break;
            }
        }
        BanList nameBanList = Bukkit.getBanList(BanList.Type.NAME);
        if (e.getPlayer().isBanned()) {
            e.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            BanEntry banEntry = nameBanList.getBanEntry(e.getPlayer().getName());
            Date expirationDate = banEntry.getExpiration();
            String expirationString = TimeUtils.getRemainingAsString(expirationDate);
            if(!expirationString.equals("never")) {
                e.disallow(e.getResult(), CommandMessages.BAN_TITLE()
                        + "\n \n" + CommandMessages.BAN_EXPIRATION(expirationString)
                        + "\n" + CommandMessages.BAN_REASON(banEntry.getReason()));
            } else {
                e.disallow(e.getResult(), CommandMessages.BAN_TITLE()
                        + "\n \n" + CommandMessages.BAN_EXPIRATION_PERMANENT()
                        + "\n" + CommandMessages.BAN_REASON(banEntry.getReason()));
            }
        } else if (ipBanned) {
            e.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            BanEntry banEntry = ipBanList.getBanEntry(ipAddress);
            Date expirationDate = banEntry.getExpiration();
            String expirationString = TimeUtils.getRemainingAsString(expirationDate);
            if(!expirationString.equals("never")) {
                e.disallow(e.getResult(), CommandMessages.BAN_IP_TITLE()
                        + "\n \n" + CommandMessages.BAN_EXPIRATION(expirationString)
                        + "\n" + CommandMessages.BAN_REASON(banEntry.getReason()));
            } else {
                e.disallow(e.getResult(), CommandMessages.BAN_IP_TITLE()
                        + "\n \n" + CommandMessages.BAN_EXPIRATION_PERMANENT()
                        + "\n" + CommandMessages.BAN_REASON(banEntry.getReason()));
            }
        }
    }
}
