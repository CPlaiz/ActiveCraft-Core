package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.ActiveCraftCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler (priority = EventPriority.HIGH)
    public void PlayerDeathEvent(PlayerDeathEvent event) {
        Player player = event.getEntity();
        ActiveCraftCore.getPlugin().setLastLocationForPlayer(player, player.getLocation());
    }
}
