package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.File;

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
                FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");
                if (playerdataConfig.getBoolean("edit-sign")) {
                    event.setCancelled(true);
                    Sign signBlock = (Sign) event.getClickedBlock().getState();
                    player.openSign(signBlock);
                }
            }
        }
    }

}
