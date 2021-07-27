package de.silencio.activecraftcore.listener;

import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EnderPealCooldown implements Listener {

    @EventHandler
    public void onPlayerShoot(PlayerInteractEvent e) {

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) { //Check if right click air. So the player wont be shoot @ a block
            if (e.getItem().getType() == Material.ENDER_PEARL) { //Of course it must be an enderpearl
                e.setCancelled(true); //Don't shoot 2 enderpearls!
                EnderPearl ep = e.getPlayer().launchProjectile(EnderPearl.class); //Shoot the enderpearl
                ep.setVelocity(e.getPlayer().getEyeLocation().getDirection()); //Set the direction the player is looking
            }
        }
    }
}
