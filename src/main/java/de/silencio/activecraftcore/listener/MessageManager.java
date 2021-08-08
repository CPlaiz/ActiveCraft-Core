package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.commands.DialogueList;
import de.silencio.activecraftcore.utils.DialogueManager;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;

public class MessageManager implements Listener, DialogueList {

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent event) {


        MessageUtils messageUtils = new MessageUtils();
        String message = messageUtils.replaceColor(event.getMessage());
        Player player = event.getPlayer();

        if (!dialogueList.contains(player)) {

            FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName() + ".yml");

            String value = playerdataConfig.getString("muted", "true");
            boolean booleanvalue = Boolean.parseBoolean(value);

            String defaultvalue = playerdataConfig.getString("default-mute", "true");
            boolean defaultboolean = Boolean.parseBoolean(defaultvalue);

            if (playerdataConfig.getString("muted") == null) {
                FileConfig fileConfig = new FileConfig("config.yml");
                Bukkit.broadcastMessage(fileConfig.getString("chat-format").replace("%displayname%", player.getDisplayName()).replace("%message%", message));
                event.setCancelled(true);
            } else if (booleanvalue) {
                player.sendMessage(ChatColor.GOLD + "You are muted!");
                Bukkit.broadcast(ChatColor.GOLD + "[Mute] " + ChatColor.AQUA + player.getName() + ChatColor.GOLD + " tried to talk, but is muted. (" + ChatColor.AQUA + message + ChatColor.GOLD + ")", "activecraft.muted.see");
                event.setCancelled(true);
            } else if (playerdataConfig.getString("default-mute") == null) {
                FileConfig fileConfig = new FileConfig("config.yml");
                Bukkit.broadcastMessage(fileConfig.getString("chat-format").replace("%displayname%", player.getDisplayName()).replace("%message%", message));
                event.setCancelled(true);
            } else if (defaultboolean) {
                player.sendMessage(ChatColor.GOLD + "You are new to this server so you cannot write in chat. Please contact a staff member to verify you.");
                Bukkit.broadcast(ChatColor.GOLD + "[Default-Mute] " + ChatColor.AQUA + player.getName() + ChatColor.GOLD + " is default-muted. (" + ChatColor.AQUA + message + ChatColor.GOLD + ")", "activecraft.muted.see");
                event.setCancelled(true);
            } else {
                FileConfig fileConfig = new FileConfig("config.yml");
                Bukkit.broadcastMessage(fileConfig.getString("chat-format").replace("%displayname%", player.getDisplayName()).replace("%message%", message));
                event.setCancelled(true);
            }
        } else {
            DialogueManager dialogueManager = new DialogueManager(player);
            dialogueManager.answer(event.getMessage());
            event.setCancelled(true);
        }
    }
}