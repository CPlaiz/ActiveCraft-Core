package de.silencio.activecraftcore.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.HashMap;

public class DoubleJump implements Listener {

    private HashMap<Player, Boolean> cooldown = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if(event.getPlayer().getGameMode() == GameMode.SURVIVAL || event.getPlayer().getGameMode() == GameMode.ADVENTURE) {
            event.getPlayer().setAllowFlight(true);
        }
        cooldown.put(event.getPlayer(), false);
    }


    @EventHandler
    public void onFly(PlayerToggleFlightEvent event) {

        if (event.getPlayer().hasPermission("activecraft.doublejump")) {
            if (!(event.getPlayer().getGameMode() == GameMode.CREATIVE) || !(event.getPlayer().getGameMode() == GameMode.SPECTATOR)) {
                if (event.getPlayer().getGameMode() == GameMode.SURVIVAL || event.getPlayer().getGameMode() == GameMode.ADVENTURE) {
                    event.setCancelled(true);
                    if (cooldown.get(event.getPlayer())) return;
                    event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().setY(1));
                    cooldown.put(event.getPlayer(), true);
                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if(event.getPlayer().isOnGround()) {
            cooldown.put(event.getPlayer(), false);
        }
    }

    @EventHandler
    public void onGamemodeChange(PlayerGameModeChangeEvent event) {
        if(event.getNewGameMode() == GameMode.SURVIVAL || event.getNewGameMode() == GameMode.ADVENTURE) {
            event.getPlayer().setAllowFlight(true);
        }
    }
}
