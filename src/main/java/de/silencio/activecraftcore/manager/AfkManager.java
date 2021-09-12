package de.silencio.activecraftcore.manager;

import de.silencio.activecraftcore.events.PlayerAfkEvent;
import de.silencio.activecraftcore.utils.ConfigUtils;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.MessageUtils;
import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AfkManager {

    public static void setAfk(Player player, boolean afk) {
        Profile profile = new Profile(player);
        FileConfig mainConfig = new FileConfig("config.yml");
        if (afk) {
            //call event
            PlayerAfkEvent event = new PlayerAfkEvent(player, true);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) return;

            //set stuff
            ConfigUtils.setDisplaynameFromConfig(player, profile.getColorNick().name(), profile.getNickname() + ChatColor.GRAY + " " + mainConfig.getString("afk-format"));
            profile.set(Profile.Value.AFK, event.isAfk());

            //send messages
            player.sendMessage(ChatColor.GOLD + "You are now afk.");
            if(mainConfig.getBoolean("announce-afk")) {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    if (onlinePlayer != player) {
                        player.sendMessage(ChatColor.GRAY + "" + MessageUtils.removeColorAndFormat(mainConfig.getString("afk-format-yes").replace("%displayname%", player.getDisplayName())));
                    }
                }
            }
        } else {
            //call event
            PlayerAfkEvent event = new PlayerAfkEvent(player, false);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) return;

            //set stuff
            ConfigUtils.setDisplaynameFromConfig(player, profile.getColorNick().name(), profile.getNickname());
            profile.set(Profile.Value.AFK, event.isAfk());

            //send messages
            player.sendMessage(ChatColor.GOLD + "You are no longer afk.");
            if (mainConfig.getBoolean("announce-afk")) {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    if (onlinePlayer != player) {
                        player.sendMessage(ChatColor.GRAY + "" + MessageUtils.removeColorAndFormat(mainConfig.getString("afk-format-no").replace("%displayname%", player.getDisplayName())));
                    }
                }
            }
        }
    }

}
