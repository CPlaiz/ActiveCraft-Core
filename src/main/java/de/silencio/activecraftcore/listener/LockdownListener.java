package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.io.File;

public class LockdownListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        FileConfig fileConfig = new FileConfig("config.yml");
        boolean isLockedDown = fileConfig.getBoolean("lockdown");

        if (isLockedDown) {
            String playername = event.getPlayerProfile().getName();
            FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + playername + ".yml");
            System.out.println(playername);
            if(!playerdataConfig.getBoolean("lockdown-bypass")) {
                event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
                event.setKickMessage(fileConfig.getString("lockdown-kick-message"));
            }
        }
    }
}