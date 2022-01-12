package de.silencio.activecraftcore;

import de.silencio.activecraftcore.guicreator.Gui;
import de.silencio.activecraftcore.guicreator.GuiCreator;
import de.silencio.activecraftcore.guicreator.GuiData;
import de.silencio.activecraftcore.guis.ProfileMenu;
import de.silencio.activecraftcore.manager.DialogueManager;
import de.silencio.activecraftcore.manager.VanishManager;
import de.silencio.activecraftcore.messages.ActiveCraftMessage;
import de.silencio.activecraftcore.messages.Language;
import de.silencio.activecraftcore.playermanagement.PlayerQueue;
import de.silencio.activecraftcore.playermanagement.Profile;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class ActiveCraftCore extends JavaPlugin {

    public static String PREFIX = ChatColor.GOLD + "[ActiveCraft-Core] ";
    private static ActiveCraftCore plugin;
    private static VanishManager vanishManager;
    private static HashMap<Player, Profile> msgPlayerStoring = new HashMap<>();
    private static Language language;
    private static ActiveCraftMessage activeCraftMessage;
    private static HashMap<Player, ProfileMenu> profileMenuList;
    private static HashMap<String, Profile> profiles;
    private static HashMap<GuiCreator, GuiData> guiDataMap;
    private static HashMap<Integer, Gui> guiList;
    private static HashMap<Player, Location> lastLocMap = new HashMap<>();
    private static HashMap<CommandSender, DialogueManager> dialogueManagerList;

    public ActiveCraftCore() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        dialogueManagerList = new HashMap<>();
        vanishManager = new VanishManager(this);

        profileMenuList = new HashMap<>();

        profiles = new HashMap<>();
        createProfiles();

        PlayerQueue.initialize();

        //guicreator creator stuff
        guiDataMap = new HashMap<>();
        guiList = new HashMap<>();

        int pluginId = 12627;
        Metrics metrics = new Metrics(this, pluginId);

        PluginManager.init();
        startTimer();

        saveDefaultConfig();

        File file = new File(getDataFolder(), "messages.yml");
        if(!file.exists()) {
            saveResource("messages.yml", false);
        }

        language = Language.valueOf(new FileConfig("config.yml").getString("language").toUpperCase());
        activeCraftMessage = new ActiveCraftMessage();

        FileConfig warplistConfig = new FileConfig("warplist.yml");
        for (String s : warplistConfig.getStringList("warplist")) {
            Map<String, Boolean> childMap = new HashMap<>();
            childMap.put("activecraft.warp.self", true);
            childMap.put("activecraft.warp", true);
            if (Bukkit.getPluginManager().getPermission("activecraft.warp.self." + s) != null)
                Bukkit.getPluginManager().addPermission(new Permission("activecraft.warp.self." + s, "Permission to warp yourself to a specific warp.", PermissionDefault.OP, childMap));
            childMap.clear();
            childMap.put("activecraft.warp.others", true);

            if (Bukkit.getPluginManager().getPermission("activecraft.warp.others." + s) != null)childMap.put("activecraft.warp", true);
                Bukkit.getPluginManager().addPermission(new Permission("activecraft.warp.others." + s, "Permission to warp another player to a specific warp.", PermissionDefault.OP, childMap));
        }

        log("Plugin loaded.");

        new UpdateChecker(this, 95488).getVersion(version -> {
            if (!this.getDescription().getVersion().equals(version))
                getLogger().info("There is a new update available.");
        });
    }

    @Override
    public void onDisable() {
        log("Plugin unloaded.");
    }

    public void log(String text) {
        Bukkit.getConsoleSender().sendMessage(PREFIX + text);
    }

    public static ActiveCraftCore getPlugin() {
        return plugin;
    }

    public static VanishManager getVanishManager() {
        return vanishManager;
    }

    public void startTimer() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {

            for (Player player : Bukkit.getOnlinePlayers()) {

                Profile profile = getProfile(player);
                FileConfig mainConfig = new FileConfig("config.yml");

                int hours = profile.getPlaytimeHours();
                int minutes = profile.getPlaytimeMinutes();

                minutes++;
                profile.set(Profile.Value.PLAYTIME_MINUTES, minutes);

                if (minutes == 60) {
                    profile.set(Profile.Value.PLAYTIME_MINUTES, 0);
                    hours++;
                    profile.set(Profile.Value.PLAYTIME_HOURS, hours);
                }

                if (minutes + hours * 60 >= mainConfig.getInt("remove-default-mute-after") * 60 && mainConfig.getInt("remove-default-mute-after") >= 0) {
                    if (profile.isDefaultmuted()) {
                        player.sendMessage(ChatColor.GOLD + "Your default-mute has been removed. You are now able to talk.");
                        profile.set(Profile.Value.DEFAULTMUTED, false);
                    }
                }
            }
        }, 20 * 60, 20 * 60);
    }

    public static Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        FileConfig mainConfig = new FileConfig("config.yml");
        mainConfig.set("language", language.getCode().toLowerCase());
        mainConfig.saveConfig();
        ActiveCraftCore.language = language;
    }

    public static ActiveCraftMessage getActiveCraftMessage() {
        return activeCraftMessage;
    }

    public static void setActiveCraftMessage(ActiveCraftMessage activeCraftMessage) {
        ActiveCraftCore.activeCraftMessage = activeCraftMessage;
    }

    public static HashMap<CommandSender, DialogueManager> getDialogueManagerList() {
        return dialogueManagerList;
    }

    public static void setLastLocationForPlayer(Player player, Location loc) {
        lastLocMap.put(player, loc);
    }

    public static Location getLastLocationForPlayer(Player player) {
        return lastLocMap.get(player);
    }

    public static HashMap<Player, Location> getLastLocMap() {
        return lastLocMap;
    }

    public static HashMap<GuiCreator, GuiData> getGuiDataMap() {
        return guiDataMap;
    }

    public static void addToGuiDataMap(GuiCreator guiCreator, GuiData guiData) {
        guiDataMap.put(guiCreator, guiData);
    }

    public static void removeFromGuiDataMap(GuiCreator guiCreator) {
        guiDataMap.remove(guiCreator);
    }

    public static GuiData getFromGuiDataMap(GuiCreator guiCreator) {
        return guiDataMap.get(guiCreator);
    }

    public static HashMap<Integer, Gui> getGuiList() {
        return guiList;
    }

    public static void setGuiList(HashMap<Integer, Gui> guiList) {
        ActiveCraftCore.guiList = guiList;
    }

    public static Gui getGuiById(int id) {
        return ActiveCraftCore.guiList.get(id);
    }

    public static HashMap<Player, ProfileMenu> getProfileMenuList() {
        return profileMenuList;
    }

    public static void setProfileMenuList(HashMap<Player, ProfileMenu> profileMenuList) {
        ActiveCraftCore.profileMenuList = profileMenuList;
    }

    public static void addToProfileMenuList(Player player, ProfileMenu profileMenu) {
        profileMenuList.put(player, profileMenu);
    }

    public static void removeFromProfileMenuList(Player player) {
        ActiveCraftCore.profileMenuList.remove(player);
    }

    public static ProfileMenu getFromProfileMenuList(Player player) {
        return ActiveCraftCore.profileMenuList.get(player);
    }

    public static HashMap<String, UUID> getPlayerlist() {
        FileConfig playerlistConfig = new FileConfig("playerlist.yml");
        HashMap<String, UUID> playerlist = new HashMap<>();
        for (String combinedString : playerlistConfig.getStringList("players")) {
            playerlist.put(combinedString.split(",")[0], UUID.fromString(combinedString.split(",")[1]));
        }
        return playerlist;
    }

    public static String getPlayernameByUUID(String uuid) {
        FileConfig playerlistConfig = new FileConfig("playerlist.yml");
        for (String combinedString : playerlistConfig.getStringList("players")) {
            if (combinedString.split(",")[1].equals(uuid)) {
                return combinedString.split(",")[0];
            }
        }
        return null;
    }

    public static String getPlayernameByUUID(UUID uuid) {
        return getPlayernameByUUID(uuid.toString());
    }

    public static String getUUIDByPlayername(String playername) {
        FileConfig playerlistConfig = new FileConfig("playerlist.yml");
        for (String combinedString : playerlistConfig.getStringList("players")) {
            if (combinedString.split(",")[0].equals(playername)) {
                return combinedString.split(",")[1];
            }
        }
        return null;
    }

    public static HashMap<Player, Profile> getMsgPlayerStoring() {
        return msgPlayerStoring;
    }

    public static void setMsgPlayerStoring(HashMap<Player, Profile> msgPlayerStoring) {
        ActiveCraftCore.msgPlayerStoring = msgPlayerStoring;
    }

    public static void createProfiles() {
        profiles.clear();
        for (String playername : getPlayerlist().keySet()) {
            if (new File(ActiveCraftCore.getPlugin().getDataFolder() + File.separator + "playerdata" + File.separator + playername + ".yml").exists())
                profiles.put(playername, new Profile(playername));
        }
    }

    public static Profile getProfile(String playername) {
        Profile profile = profiles.get(playername.toLowerCase());
        if (profile == null) return null;
        profile.refresh();
        return profile;
    }

    public static Profile getProfile(Player player) {
        return getProfile(player.getName());
    }

    public static Profile getProfile(CommandSender sender) {
        return getProfile(sender.getName());
    }

    public static HashMap<String, Profile> getProfiles() {
        return profiles;
    }
}
