package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignInteractListener implements Listener {

    public void log(String text) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + text);
    }

    @EventHandler
    public void onSignClick(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;

        Player player = event.getPlayer();
        BlockState blockState = event.getClickedBlock().getState();
        if (blockState instanceof Sign) {
            if (player.isSneaking()) {
                Profile profile = ActiveCraftCore.getProfile(player);
                if (profile.canEditSign()) {
                    event.setCancelled(true);
                    Sign signBlock = (Sign) event.getClickedBlock().getState();
                    player.openSign(signBlock);
                }
            }
        }
    }

}
