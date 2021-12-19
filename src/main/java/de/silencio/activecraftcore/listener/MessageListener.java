package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.dialogue.DialogueManager;
import de.silencio.activecraftcore.utils.ColorUtils;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;

public class MessageListener implements Listener {

    @EventHandler (priority = EventPriority.LOWEST)
    public void onChatMessage(AsyncPlayerChatEvent event) {

        String message = ColorUtils.replaceColor(event.getMessage());
        message = ColorUtils.replaceFormat(message);
        Player player = event.getPlayer();

        if (!ActiveCraftCore.getPlugin().getDialogueList().contains(player)) {

            FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");

            boolean muted = playerdataConfig.getBoolean("muted");

            boolean defaultMuted = playerdataConfig.getBoolean("default-mute");

            if (!muted && !defaultMuted) {
                FileConfig fileConfig = new FileConfig("config.yml");
                //Bukkit.broadcastMessage(fileConfig.getString("chat-format").replace("%displayname%", player.getDisplayName()).replace("%message%", message));
                Bukkit.broadcastMessage(fileConfig.getString("chat-format")
                        .replace("%displayname%", messageWithColor(player, playerdataConfig.getString("nickname"), playerdataConfig.getString("colornick")))
                        .replace("%message%", message));
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

            DialogueManager dialogueManager = ActiveCraftCore.getPlugin().getDialogueManagerList().getDialogueManager(player);
            dialogueManager.answer(event.getMessage());
            event.setCancelled(true);
        }
    }

    public String messageWithColor(Player p, String displayname, String colorname) {
        String outputDisplayname = null;
        for (ChatColor color : ChatColor.values()) {
            if (colorname.toLowerCase().equals(color.name().toLowerCase())) {
                if (!colorname.equals("BOLD") && !colorname.equals("MAGIC") && !colorname.equals("STRIKETHROUGH") &&
                        !colorname.equals("ITALIC") && !colorname.equals("UNDERLINE") && !colorname.equals("RESET")) {
                    outputDisplayname = color + displayname;
                }
            }
        }
        return outputDisplayname;
    }
}