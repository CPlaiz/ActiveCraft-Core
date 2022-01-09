package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.manager.VanishManager;
import de.silencio.activecraftcore.playermanagement.Profile;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.Placeholder;
import de.silencio.activecraftcore.utils.StringUtils;
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
        Profile profile = ActiveCraftCore.getProfile(player);

        profile.set(Profile.Value.LAST_LOCATION, event.getFrom().getWorld().getName(), playerLocation);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        Placeholder placeholder = new Placeholder(player);

        FileConfig playerlistConfig = new FileConfig("playerlist.yml");
        FileConfig mainConfig = new FileConfig("config.yml");
        FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");

        VanishManager vanishManager = ActiveCraftCore.getVanishManager();

        List<String> playerlist = playerlistConfig.getStringList("players");

        if (!playerlist.contains(player.getName().toLowerCase() + "," + player.getUniqueId())) {
            playerlist.add(player.getName().toLowerCase() + "," + player.getUniqueId());
            playerlistConfig.set("players", playerlist);
            playerlistConfig.saveConfig();
        }

        File file = new File(ActiveCraftCore.getPlugin().getDataFolder() + File.separator + "playerdata" + File.separator);

        FileConfig config;

        if (!file.exists()) file.mkdir();

        config = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");

        if (config.getKeys(true).size() == 0) {
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
            playerdataConfig.set("log-enabled", false);
            playerdataConfig.set("lockdown-bypass", false);
            playerdataConfig.set("edit-sign", false);
            playerdataConfig.set("receive-socialspy", true);
            playerdataConfig.set("violations.warns", 0);
            playerdataConfig.set("violations.mutes", 0);
            playerdataConfig.set("violations.bans", 0);
            playerdataConfig.set("violations.ip-bans", 0);
            playerdataConfig.set("times-joined", 0);

            playerdataConfig.saveConfig();

            ActiveCraftCore.getProfiles().put(player.getName().toLowerCase(), new Profile(player));
        }

        playerdataConfig.set("last-online", "Online");
        playerdataConfig.set("times-joined", playerdataConfig.getInt("times-joined") + 1);


        Profile profile = ActiveCraftCore.getProfile(player.getName());

        List<String> knownIps = profile.getKnownIps();
        if (!knownIps.contains(player.getAddress().getAddress().toString().replace("/", ""))) {
            knownIps.add(player.getAddress().getAddress().toString().replace("/", ""));
            profile.set(Profile.Value.KNOWN_IPS, knownIps);
        }

        if (mainConfig.getBoolean("check-for-matching-ips")) {
            playerlist.remove(player.getName());
            StringBuilder strBuilder = new StringBuilder();
            for (Profile otherProfile : ActiveCraftCore.getProfiles().values()) {
                if (otherProfile == profile) continue;
                for (int i = 0; i < knownIps.size(); i++)
                    if (otherProfile.getKnownIps().contains(knownIps.get(i))) {
                        if (i != 0) strBuilder.append(", ");
                        strBuilder.append(knownIps.get(i));
                    }
                if (!strBuilder.toString().equals("")) {
                    Bukkit.broadcast(ChatColor.DARK_AQUA + player.getName() + ChatColor.GRAY + " shares the IP " +
                            ChatColor.DARK_GRAY + strBuilder + ChatColor.GRAY + " with " + ChatColor.DARK_AQUA +
                            otherProfile.getName(), "matchingip.notify");
                }
            }
        }
        playerdataConfig.saveConfig();

        if (profile.getLastLocationBeforeQuit() != null) player.teleport(profile.getLastLocationBeforeQuit());

        // vanish stuff
        if (profile.isVanished()) {
            vanishManager.setVanished(player, true);
            event.setJoinMessage(null);
            Bukkit.broadcast((mainConfig.getString("join-format") + ChatColor.GOLD + " (vanished)").replace("%displayname%", profile.getNickname()), "activecraft.vanish.see");
        } else event.setJoinMessage(mainConfig.getString("join-format").replace("%displayname%", profile.getNickname()));
        if (!player.hasPermission("vanish.see")) vanishManager.hideAll(player);

        //fly
        if (profile.canFly()) player.setAllowFlight(true);

        // tag stuff
        StringUtils.setDisplaynameFromConfig(player, profile.getColorNick(), profile.getNickname());
        profile.applyTags();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Location playerLocation = player.getLocation();

        Profile profile = ActiveCraftCore.getProfile(player);

        OffsetDateTime now = OffsetDateTime.now();
        profile.set(Profile.Value.LAST_ONLINE, dtf.format(now));
        profile.set(Profile.Value.LAST_LOCATION, playerLocation.getWorld().getName(), playerLocation);

        if (player.hasPermission("lockdown.bypass")) profile.set(Profile.Value.BYPASS_LOCKDOWN, true);
        else profile.set(Profile.Value.BYPASS_LOCKDOWN, false);

        profile.set(Profile.Value.LAST_LOCATION, "BEFORE_QUIT", playerLocation);

        FileConfig mainConfig = new FileConfig("config.yml");

        if (!profile.isVanished()) {
            event.setQuitMessage(mainConfig.getString("quit-format").replace("%displayname%", profile.getNickname()));
        } else {
            VanishManager vanishManager = ActiveCraftCore.getVanishManager();
            List<Player> vanishedList = vanishManager.getVanished();
            vanishedList.remove(player);
            vanishManager.setVanishedList(vanishedList);
            event.setQuitMessage(null);
            Bukkit.broadcast((mainConfig.getString("quit-format") + ChatColor.GOLD + " (vanished)").replace("%displayname%", player.getDisplayName()), "vanish.see");
        }
    }
}