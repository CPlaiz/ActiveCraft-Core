package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MessageManager implements Listener {

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent event) {

        MessageUtils messageUtils = new MessageUtils();
        String message = messageUtils.replaceColor(event.getMessage());
        Player player = event.getPlayer();

        FileConfig fileConfig = new FileConfig("config.yml");
        Bukkit.broadcastMessage(fileConfig.getString("chat-format").replace("%displayname%", player.getDisplayName()).replace("%message%", message));
        event.setCancelled(true);
    }
}