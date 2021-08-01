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




            List<String> playerlist = fileConfigPlayers.getStringList("players");
            if (!playerlist.contains(p.getUniqueId().toString())) {
                playerlist.add(p.getUniqueId().toString());
            }
            fileConfigPlayers.set("players", playerlist);
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

                fileConfig.set("name", p.getName());
                fileConfig.set("nickname", p.getName());
                fileConfig.set("uuid", p.getUniqueId().toString());
                fileConfig.set("taskid", 0);
                fileConfig.set("afk", false);
                fileConfig.set("op", p.isOp());
                fileConfig.set("colornick", "WHITE");
                fileConfig.set("whitelisted", p.isWhitelisted());
                fileConfig.set("godmode", false);
                fileConfig.set("muted", false);
                fileConfig.set("vanished", false);
                fileConfig.set("on-duty", false);
                fileConfig.set("stats.blocks.broken", 0);
                fileConfig.set("stats.blocks.placed", 0);
                fileConfig.set("stats.killed.players", 0);
                fileConfig.set("stats.killed.monsters", 0);
                fileConfig.set("stats.killed.animals", 0);
                fileConfig.set("last-online", null);
                fileConfig.set("last-coords", null);

                fileConfig.saveConfig();
        }

        Player player = event.getPlayer();
        //player.performCommand("spawn");


        playerdataConfig.set("last-online", "Online");

        player.setPlayerListHeader(ChatColor.GOLD + "Plugin Testserver");
        player.setPlayerListFooter(ChatColor.GOLD + "Plugin Testserver");

        FileConfig mainConfig = new FileConfig("config.yml");
        event.setJoinMessage(mainConfig.getString("join-format").replace("%displayname%", p.getDisplayName()));

        playerdataConfig.saveConfig();

        setDisplaynameFromConfig(p, playerdataConfig.getString("colornick"), playerdataConfig.getString("nickname"));

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