package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.manager.VanishManager;
import de.silencio.activecraftcore.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.io.File;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class JoinQuitListener implements Listener {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss");



    @EventHandler
    public void onPlayerWorldChange(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        Location playerLocation = player.getLocation();
        FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");

        playerdataConfig.set("last-location." + event.getFrom().getWorld().getName(), playerLocation);
        playerdataConfig.saveConfig();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        Placeholder placeholder = new Placeholder(player);

        FileConfig fileConfigPlayerlist = new FileConfig("playerlist.yml");
        FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");
        FileConfig mainConfig = new FileConfig("config.yml");

        VanishManager vanishManager = ActiveCraftCore.getVanishManager();

        List<String> playerlist = fileConfigPlayerlist.getStringList("players");

        if (!playerlist.contains(player.getName().toLowerCase() + "," + player.getUniqueId())) {
            playerlist.add(player.getName().toLowerCase() + "," + player.getUniqueId());
        }
        fileConfigPlayerlist.set("players", playerlist);
        fileConfigPlayerlist.saveConfig();

        File file = new File(ActiveCraftCore.getPlugin().getDataFolder() + File.separator + "playerdata" + File.separator);

        FileConfig config;

        if (!file.exists()) {
            file.mkdir();
        }

        config = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");

        if (config.getKeys(true).size() == 0) {

            FileConfig fileConfig = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");

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
            playerdataConfig.set("default-mute", mainConfig.getBoolean("mute-new-players"));    
            playerdataConfig.set("vanished", false);
            playerdataConfig.set("on-duty", false);
            playerdataConfig.set("last-online", null);
            playerdataConfig.set("last-coords", null);
            playerdataConfig.set("log-enabled", false);
            playerdataConfig.set("lockdown-bypass", false);
            playerdataConfig.set("edit-sign", false);
            playerdataConfig.set("violations.warns", 0);
            playerdataConfig.set("violations.mutes", 0);
            playerdataConfig.set("violations.bans", 0);
            playerdataConfig.set("violations.ip-bans", 0);
            playerdataConfig.set("times-joined", 0);

            playerdataConfig.saveConfig();
        }

        playerdataConfig.set("last-online", "Online");
        playerdataConfig.set("times-joined", playerdataConfig.getInt("times-joined") + 1);

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
                FileConfig fileConfigIpAddressCheck = new FileConfig("playerdata" + File.separator + s.toLowerCase() + ".yml");
                List<String> knownIpsOthers = fileConfigIpAddressCheck.getStringList("known-ips");
                StringBuilder strBuilder = new StringBuilder();
                boolean isFirst = true;
                for (String knownIpPlayer : knownIps) {
                    if (knownIpsOthers.contains(knownIpPlayer)) {
                        if (!isFirst) {
                            strBuilder.append(", ");
                        } else isFirst = false;
                        strBuilder.append(knownIpPlayer);
                    }
                }
                if (!strBuilder.toString().equals("")) {
                    //Bukkit.broadcast(Errors.WARNING() + ChatColor.AQUA + player.getName() + ChatColor.GOLD + " shares the IP " +
                    //        ChatColor.GRAY + strBuilder + ChatColor.GOLD + " with " + ChatColor.AQUA +
                    //        fileConfigIpAddressCheck.getString("name"), "activecraft.matchingip.notify");
                    Bukkit.broadcast(ChatColor.DARK_AQUA + player.getName() + ChatColor.GRAY + " shares the IP " +
                            ChatColor.DARK_GRAY + strBuilder + ChatColor.GRAY + " with " + ChatColor.DARK_AQUA +
                            fileConfigIpAddressCheck.getString("name"), "activecraft.matchingip.notify");
                }
            }
        }

        playerdataConfig.saveConfig();





        if (!playerdataConfig.getBoolean("vanished")) {
                setDisplaynameFromConfig(player, playerdataConfig.getString("colornick"), playerdataConfig.getString("nickname"));
            event.setJoinMessage(placeholder.replace(mainConfig.getString("join-format"), Placeholder.Type.DISPLAYNAME));
        } else {
            vanishManager.setVanished(player, true);
            setDisplaynameFromConfig(player, playerdataConfig.getString("colornick"), playerdataConfig.getString("nickname") + ChatColor.GRAY + " " + mainConfig.getString("vanish-format"));
            //player.setPlayerListName(playerdataConfig.getString("nickname") + ChatColor.GRAY + " " + mainConfig.getString("vanish-format"));
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
        Location playerLocation = player.getLocation();

        FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");

        OffsetDateTime now = OffsetDateTime.now();
        playerdataConfig.set("last-online", dtf.format(now));
        playerdataConfig.set("last-location." + playerLocation.getWorld().getName(), playerLocation);

        if (player.hasPermission("activecraft.lockdown.bypass")) {
            playerdataConfig.set("lockdown-bypass", true);
        } else {
            playerdataConfig.set("lockdown-bypass", false);
        }

        playerdataConfig.set("last-location.BEFORE_QUIT", playerLocation);
        playerdataConfig.saveConfig();

        FileConfig mainConfig = new FileConfig("config.yml");

        if (!playerdataConfig.getBoolean("vanished")) {
            event.setQuitMessage(mainConfig.getString("quit-format").replace("%displayname%", player.getDisplayName()));
        } else {
            VanishManager vanishManager = ActiveCraftCore.getVanishManager();
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