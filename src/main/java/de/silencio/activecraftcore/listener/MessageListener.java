package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.manager.DialogueManager;
import de.silencio.activecraftcore.playermanagement.Profile;
import de.silencio.activecraftcore.utils.ColorUtils;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MessageListener implements Listener {

    @EventHandler (priority = EventPriority.LOWEST)
    public void onChatMessage(AsyncPlayerChatEvent event) {

        String message = ColorUtils.replaceColor(event.getMessage());
        message = ColorUtils.replaceFormat(message);
        Player player = event.getPlayer();

        if (ActiveCraftCore.getDialogueManagerList().containsKey(player)) {
            DialogueManager dialogueManager = ActiveCraftCore.getDialogueManagerList().get(player);
            dialogueManager.answer(event.getMessage());
            event.setCancelled(true);
            return;
        }

            Profile profile = ActiveCraftCore.getProfile(player);

            boolean muted = profile.isMuted();
            boolean forcemuted = profile.isForcemuted();
            boolean defaultMuted = profile.isDefaultmuted();

            if (!muted && !defaultMuted) {
                FileConfig fileConfig = new FileConfig("config.yml");
                //Bukkit.broadcastMessage(fileConfig.getString("chat-format").replace("%displayname%", player.getDisplayName()).replace("%message%", message));
                Bukkit.broadcastMessage(fileConfig.getString("chat-format")
                        .replace("%displayname%", StringUtils.messageWithColor(player, profile.getNickname(), profile.getColorNick().name()))
                        .replace("%message%", message));
                event.setCancelled(true);
            } else {
                if (muted) {
                    player.sendMessage(ChatColor.GOLD + "You are muted!");

                    if (!forcemuted) {
                        Bukkit.broadcast(ChatColor.GOLD + "[Mute] " + ChatColor.AQUA + player.getDisplayName() + ChatColor.GOLD + " tried to talk, but is muted. (" + ChatColor.AQUA + message + ChatColor.GOLD + ")", "activecraft.muted.see");
                    }
                    event.setCancelled(true);


                } else {
                    player.sendMessage(ChatColor.GOLD + "You are new to this server so you cannot write in chat. Please contact a staff member to verify you.");

                    if (!forcemuted) {
                        Bukkit.broadcast(ChatColor.GOLD + "[Mute] " + ChatColor.AQUA + player.getDisplayName() + ChatColor.GOLD + " tried to talk, but is default muted. (" + ChatColor.AQUA + message + ChatColor.GOLD + ")", "activecraft.muted.see");
                    }
                    event.setCancelled(true);
                }
            }
        }
    }