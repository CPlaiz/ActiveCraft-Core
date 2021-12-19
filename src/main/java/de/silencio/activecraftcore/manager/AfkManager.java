package de.silencio.activecraftcore.manager;

import de.silencio.activecraftcore.events.PlayerAfkEvent;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.utils.*;
import net.minecraft.server.commands.CommandMe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
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
            ConfigUtils.setDisplaynameFromConfig(player, profile.getColorNick().name(), profile.getNickname() + ChatColor.GRAY + " " + CommandMessages.AFK_TAG());
            profile.set(Profile.Value.AFK, event.isAfk());

            //send messages
            player.sendMessage(CommandMessages.NOW_AFK_TARGET());
            if(mainConfig.getBoolean("announce-afk")) {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    if (onlinePlayer != player) {
                        onlinePlayer.sendMessage(ChatColor.GRAY + "" + ColorUtils.removeColorAndFormat(CommandMessages.NOW_AFK(player)));
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
            player.sendMessage(CommandMessages.NOT_AFK_TARGET());
            if (mainConfig.getBoolean("announce-afk")) {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    if (onlinePlayer != player) {
                        onlinePlayer.sendMessage(ChatColor.GRAY + "" + ColorUtils.removeColorAndFormat(CommandMessages.NOT_AFK(player)));
                    }
                }
            }
        }
    }

}
