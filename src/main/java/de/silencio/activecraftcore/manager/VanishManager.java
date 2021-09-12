package de.silencio.activecraftcore.manager;

import de.silencio.activecraftcore.events.PlayerUnvanishEvent;
import de.silencio.activecraftcore.events.PlayerVanishEvent;
import de.silencio.activecraftcore.utils.ConfigUtils;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class VanishManager {

    private Plugin plugin;
    private List<Player> vanished;

    public VanishManager(Plugin plugin) {
        this.plugin = plugin;
        this.vanished = new ArrayList<>();
    }

    public List<Player> getVanished() {
        return vanished;
    }

    public boolean isVanished(Player player) {
        return vanished.contains(player);
    }

    public void setVanished(Player player, boolean hide) {
        Profile profile = new Profile(player);
        FileConfig mainConfig = new FileConfig("config.yml");
        if (hide) {
            PlayerVanishEvent event = new PlayerVanishEvent(player);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) return;

            ConfigUtils.setDisplaynameFromConfig(player, profile.getColorNick().name(), profile.getNickname() + ChatColor.GRAY + " " + mainConfig.getString("vanish-format"));
            profile.set(Profile.Value.VANISHED, true);

            vanished.add(player);
        } else {
            PlayerUnvanishEvent event = new PlayerUnvanishEvent(player);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) return;

            ConfigUtils.setDisplaynameFromConfig(player, profile.getColorNick().name(), profile.getNickname());
            profile.set(Profile.Value.VANISHED, false);

            vanished.remove(player);
        }


        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (player.equals(onlinePlayer)) continue;
            if (!onlinePlayer.hasPermission("activecraft.vanish.see")) {
                if (hide) {
                    onlinePlayer.hidePlayer(plugin, player);
                } else {
                    onlinePlayer.showPlayer(plugin, player);
                }
            }
        }
    }

    public void hideAll(Player player) {
        vanished.forEach(player1 -> player.hidePlayer(plugin, player1));
    }

    public void setVanishedList(List<Player> vanishedList) {
        vanished = vanishedList;
    }

}
