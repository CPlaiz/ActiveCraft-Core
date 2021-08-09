package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.utils.Config;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.VanishManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class JoinQuitListener implements Listener {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss");

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

            Player p = event.getPlayer();

            FileConfig fileConfigPlayers = new FileConfig("playerlist.yml");
            FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + p.getName() + ".yml");

            List<String> playerlistUUID = fileConfigPlayers.getStringList("uuids");
            List<String> playerlistName = fileConfigPlayers.getStringList("players");
            if (!playerlistUUID.contains(p.getUniqueId().toString())) {
                playerlistUUID.add(p.getUniqueId().toString());
            }
            if (!playerlistName.contains(p.getName())) {
                playerlistName.add(p.getName());
            }
            fileConfigPlayers.set("uuids", playerlistUUID);
            fileConfigPlayers.set("players", playerlistName);
            fileConfigPlayers.saveConfig();


            FileConfig fileConfigUUIDName = new FileConfig("nameuuidlist.yml");

            fileConfigUUIDName.set(p.getName().toLowerCase(), p.getUniqueId().toString());
            fileConfigUUIDName.saveConfig();


            File file = new File(Main.getPlugin().getDataFolder() + File.separator + "playerdata" + File.separator);

            Config config;

            if (!file.exists()) {
                file.mkdir();
            }

            config = new Config("playerdata" + File.separator + p.getName() + ".yml", Main.getPlugin().getDataFolder());

            if (config.toFileConfiguration().getKeys(true).size() == 0) {

                FileConfig fileConfig = new FileConfig("playerdata" + File.separator + p.getName() + ".yml");
                FileConfig mainConfig = new FileConfig("config.yml");

                playerdataConfig.set("name", p.getName());
                playerdataConfig.set("nickname", p.getName());
                playerdataConfig.set("uuid", p.getUniqueId().toString());
                playerdataConfig.set("taskid", 0);
                playerdataConfig.set("afk", false);
                playerdataConfig.set("op", p.isOp());
                playerdataConfig.set("colornick", "WHITE");
                playerdataConfig.set("whitelisted", p.isWhitelisted());
                playerdataConfig.set("godmode", false);
                playerdataConfig.set("muted", false);
                playerdataConfig.set("default-mute", mainConfig.getString("mute-new-players"));
                playerdataConfig.set("vanished", false);
                playerdataConfig.set("on-duty", false);
                playerdataConfig.set("stats.blocks.broken", 0);
                playerdataConfig.set("stats.blocks.placed", 0);
                playerdataConfig.set("stats.killed.players", 0);
                playerdataConfig.set("stats.killed.monsters", 0);
                playerdataConfig.set("stats.killed.animals", 0);
                playerdataConfig.set("last-online", null);
                playerdataConfig.set("last-coords", null);
                playerdataConfig.set("log-enabled", false);
                playerdataConfig.set("lockdown-bypass", false);

                playerdataConfig.saveConfig();
        }

        Player player = event.getPlayer();
        //player.performCommand("spawn");

        playerdataConfig.set("last-online", "Online");

        FileConfig mainConfig = new FileConfig("config.yml");
        

        playerdataConfig.saveConfig();

        setDisplaynameFromConfig(p, playerdataConfig.getString("colornick"), playerdataConfig.getString("nickname"));
        event.setJoinMessage(mainConfig.getString("join-format").replace("%displayname%", p.getDisplayName()));

        if(!player.hasPermission("vanish.see")) {
            VanishManager vanishManager = Main.getVanishManager();
            vanishManager.hideAll(player);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName() + ".yml");

        OffsetDateTime now = OffsetDateTime.now();
        playerdataConfig.set("last-online", dtf.format(now));

        if(player.hasPermission("activecraft.lockdown.bypass")) {
            playerdataConfig.set("lockdown-bypass", true);
        } else {
            playerdataConfig.set("lockdown-bypass", false);
        }

        playerdataConfig.saveConfig();

        FileConfig mainConfig = new FileConfig("config.yml");
        event.setQuitMessage(mainConfig.getString("quit-format").replace("%displayname%", player.getDisplayName()));

        playerdataConfig.saveConfig();
    }

    public void setDisplaynameFromConfig(Player p, String colorname, String displayname) {
        for (ChatColor color : ChatColor.values()) {
            if (colorname.toLowerCase().equals(color.name().toLowerCase())) {
                if (!colorname.equals("BOLD") && !colorname.equals("MAGIC") && !colorname.equals("STRIKETHROUGH") &&
                        !colorname.equals("ITALIC") && !colorname.equals("UNDERLINE") && !colorname.equals("RESET")) {
                    p.setDisplayName(color + displayname);
                    p.setPlayerListName(color + displayname);
                }
            }
        }
    }
}