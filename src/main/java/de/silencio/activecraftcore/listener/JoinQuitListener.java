package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.Config;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.Placeholder;
import de.silencio.activecraftcore.utils.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
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

        Player player = event.getPlayer();

        Placeholder placeholder = new Placeholder(player);

        FileConfig fileConfigPlayerlist = new FileConfig("playerlist.yml");
        FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName() + ".yml");
        FileConfig fileConfigUUIDName = new FileConfig("nameuuidlist.yml");
        FileConfig mainConfig = new FileConfig("config.yml");

        VanishManager vanishManager = Main.getVanishManager();

        List<String> playerlistUUID = fileConfigPlayerlist.getStringList("uuids");
        List<String> playerlistName = fileConfigPlayerlist.getStringList("players");
        if (!playerlistUUID.contains(player.getUniqueId().toString())) {
            playerlistUUID.add(player.getUniqueId().toString());
        }
        if (!playerlistName.contains(player.getName())) {
            playerlistName.add(player.getName());
        }
        fileConfigPlayerlist.set("uuids", playerlistUUID);
        fileConfigPlayerlist.set("players", playerlistName);
        fileConfigPlayerlist.saveConfig();

        fileConfigUUIDName.set(player.getName().toLowerCase(), player.getUniqueId().toString());
        fileConfigUUIDName.saveConfig();

        File file = new File(Main.getPlugin().getDataFolder() + File.separator + "playerdata" + File.separator);

        Config config;

        if (!file.exists()) {
            file.mkdir();
        }

        config = new Config("playerdata" + File.separator + player.getName() + ".yml", Main.getPlugin().getDataFolder());

        if (config.toFileConfiguration().getKeys(true).size() == 0) {

            FileConfig fileConfig = new FileConfig("playerdata" + File.separator + player.getName() + ".yml");

            playerdataConfig.set("name", player.getName());
            playerdataConfig.set("nickname", player.getName());
            playerdataConfig.set("uuid", player.getUniqueId().toString());
            playerdataConfig.set("afk", false);
            playerdataConfig.set("op", player.isOp());
            playerdataConfig.set("colornick", "WHITE");
            playerdataConfig.set("whitelisted", player.isWhitelisted());
            playerdataConfig.set("godmode", false);
            playerdataConfig.set("fly", false);
            playerdataConfig.set("flyspeed", 1);
            playerdataConfig.set("muted", false);
            playerdataConfig.set("default-mute", mainConfig.getString("mute-new-players"));
            playerdataConfig.set("vanished", false);
            playerdataConfig.set("on-duty", false);
            playerdataConfig.set("last-online", null);
            playerdataConfig.set("last-coords", null);
            playerdataConfig.set("log-enabled", false);
            playerdataConfig.set("lockdown-bypass", false);
            playerdataConfig.set("violations.warns", 0);
            playerdataConfig.set("violations.mutes", 0);
            playerdataConfig.set("violations.bans", 0);
            playerdataConfig.set("violations.ip-bans", 0);

            playerdataConfig.saveConfig();
        }

        playerdataConfig.set("last-online", "Online");

        List<String> knownIps = playerdataConfig.getStringList("known-ips");
        if (!knownIps.contains(player.getAddress().getAddress().toString().replace("/", ""))) {
            knownIps.add(player.getAddress().getAddress().toString().replace("/", ""));
            playerdataConfig.set("known-ips", knownIps);
            playerdataConfig.saveConfig();
        }
        if (mainConfig.getBoolean("check-for-matching-ips")) {
            List<String> playerList = fileConfigPlayerlist.getStringList("players");
            playerList.remove(player.getName());
            for (String s : playerList) {
                FileConfig fileConfigIpAddressCheck = new FileConfig("playerdata" + File.separator + s + ".yml");
                List<String> knownIpsOthers = fileConfigIpAddressCheck.getStringList("known-ips");
                for (String knownIpPlayer : knownIps) {
                    if (knownIpsOthers.contains(knownIpPlayer)) {
                        Bukkit.broadcast(Errors.WARNING + ChatColor.AQUA + player.getName() + ChatColor.GOLD + " shares the IP " +
                                ChatColor.GRAY + knownIpPlayer + ChatColor.GOLD + " with " + ChatColor.AQUA +
                                fileConfigIpAddressCheck.getString("name"), "activecraft.matchingip.notify");
                    }
                }
            }
        }

        playerdataConfig.saveConfig();



        setDisplaynameFromConfig(player, playerdataConfig.getString("colornick"), playerdataConfig.getString("nickname"));

        if (!playerdataConfig.getBoolean("vanished")) {
            event.setJoinMessage(placeholder.replace(mainConfig.getString("join-format"), Placeholder.Type.DISPLAYNAME));
        } else {
            vanishManager.setVanished(player, true);
            event.setJoinMessage(null);
            Bukkit.broadcast((mainConfig.getString("join-format") + ChatColor.GOLD + " (vanished)").replace("%displayname%", player.getDisplayName()), "activecraft.vanish.see");
        }

        if (!player.hasPermission("activecraft.vanish.see")) {
            vanishManager.hideAll(player);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName() + ".yml");

        OffsetDateTime now = OffsetDateTime.now();
        playerdataConfig.set("last-online", dtf.format(now));

        if (player.hasPermission("activecraft.lockdown.bypass")) {
            playerdataConfig.set("lockdown-bypass", true);
        } else {
            playerdataConfig.set("lockdown-bypass", false);
        }

        playerdataConfig.saveConfig();

        FileConfig mainConfig = new FileConfig("config.yml");

        if (!playerdataConfig.getBoolean("vanished")) {
            event.setQuitMessage(mainConfig.getString("quit-format").replace("%displayname%", player.getDisplayName()));
        } else {
            VanishManager vanishManager = Main.getVanishManager();
            List<Player> vanishedList = vanishManager.getVanished();
            vanishedList.remove(player);
            vanishManager.setVanishedList(vanishedList);
            event.setQuitMessage(null);
            Bukkit.broadcast((mainConfig.getString("quit-format") + ChatColor.GOLD + " (vanished)").replace("%displayname%", player.getDisplayName()), "activecraft.vanish.see");
        }

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