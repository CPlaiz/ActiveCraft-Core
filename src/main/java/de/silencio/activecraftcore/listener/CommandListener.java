package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCommandSent(PlayerCommandPreprocessEvent event) {
        Player executingPlayer = event.getPlayer();
        String eventMessage = event.getMessage();

        for (Player player : Bukkit.getOnlinePlayers()) {
            Profile profile = ActiveCraftCore.getProfile(player);
            if (profile.hasLogEnabled()) {
                if (player.hasPermission("activecraft.log")) {
                    player.sendMessage(CommandMessages.LOG_PREFIX(Bukkit.getPlayer(executingPlayer.getName()), eventMessage));
                }
            }
        }
    }

}
