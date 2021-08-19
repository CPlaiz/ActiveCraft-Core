package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.messages.Dialogue.DialogueList;
import de.silencio.activecraftcore.messages.Dialogue.DialogueManager;
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

        String message = MessageUtils.replaceColor(event.getMessage());
        message = MessageUtils.replaceFormat(message);
        Player player = event.getPlayer();

        if (!dialogueList.contains(player)) {

            FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");

            boolean muted = playerdataConfig.getBoolean("muted");

            boolean defaultMuted = playerdataConfig.getBoolean("default-mute");

            if (!muted && !defaultMuted) {
                FileConfig fileConfig = new FileConfig("config.yml");
                Bukkit.broadcastMessage(fileConfig.getString("chat-format").replace("%displayname%", player.getDisplayName()).replace("%message%", message));
                event.setCancelled(true);
            } else {
                if (muted) {
                    player.sendMessage(ChatColor.GOLD + "You are muted!");
                    Bukkit.broadcast(ChatColor.GOLD + "[Mute] " + ChatColor.AQUA + player.getDisplayName() + ChatColor.GOLD + " tried to talk, but is muted. (" + ChatColor.AQUA + message + ChatColor.GOLD + ")", "activecraft.muted.see");
                    event.setCancelled(true);

                } else {
                    player.sendMessage(ChatColor.GOLD + "You are new to this server so you cannot write in chat. Please contact a staff member to verify you.");
                    Bukkit.broadcast(ChatColor.GOLD + "[Default-Mute] " + ChatColor.AQUA + player.getDisplayName() + ChatColor.GOLD + " is default-muted. (" + ChatColor.AQUA + message + ChatColor.GOLD + ")", "activecraft.muted.see");
                    event.setCancelled(true);
                }
            }
        } else {

            DialogueManager dialogueManager = Main.getPlugin().dialogueManagerList.getDialogueManager(player);
            dialogueManager.answer(event.getMessage());
            event.setCancelled(true);
        }
    }
}