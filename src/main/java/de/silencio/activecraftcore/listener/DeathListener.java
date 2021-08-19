package de.silencio.activecraftcore.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent event) {
        //if(event.getDeathMessage().toLowerCase().contains(event.getEntity() + "died")) {
         //   event.setDeathMessage(ChatColor.AQUA + event.getEntity().getName() + ChatColor.GOLD + " committed suicide.");
        //}



    }
}
