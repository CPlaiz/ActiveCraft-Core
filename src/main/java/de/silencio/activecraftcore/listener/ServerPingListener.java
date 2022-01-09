package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.Objects;

public class ServerPingListener implements Listener {

    @EventHandler
    public void on(ServerListPingEvent event) {
        //set modt when locked down
        FileConfig fileConfig = new FileConfig("config.yml");
        if (fileConfig.getBoolean("lockdown")) {
            fileConfig.set("old-modt", event.getMotd());
            fileConfig.saveConfig();
            event.setMotd(Objects.requireNonNull(fileConfig.getString("lockdown-modt")));
        }

        if (!fileConfig.getBoolean("lockdown") && fileConfig.get("lockdown") != null) {
            event.setMotd(Objects.requireNonNull(fileConfig.getString("old-modt")));
        }
    }

}
