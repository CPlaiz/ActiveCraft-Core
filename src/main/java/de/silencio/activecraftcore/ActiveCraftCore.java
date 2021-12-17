package de.silencio.activecraftcore;

import de.silencio.activecraftcore.commands.*;
import de.silencio.activecraftcore.gui.*;
import de.silencio.activecraftcore.listener.*;
import de.silencio.activecraftcore.messages.ActiveCraftMessage;
import de.silencio.activecraftcore.dialogue.DialogueManagerList;
import de.silencio.activecraftcore.messages.Language;
import de.silencio.activecraftcore.profilemenu.ProfileMenu;
import de.silencio.activecraftcore.profilemenu.listeners.*;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.manager.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.*;

public final class ActiveCraftCore extends JavaPlugin {

    public static String PREFIX = "§6[ActiveCraft-Core] ";
    public static ActiveCraftCore instance;
    private static ActiveCraftCore plugin;
    private static VanishManager vanishManager;

    private Language language;
    private ActiveCraftMessage activeCraftMessage;

    private FileConfig playtimeConfig;
    private FileConfig config;
    private FileConfig homeconfig;
    private FileConfig warpsConfig;

    private HashMap<Player, ProfileMenu> profileMenuList;

    private HashMap<Player, Player> tpaList;

    private HashMap<GuiCreator, GuiData> guiDataMap;
    private HashMap<Integer, Gui> guiList;

    private HashMap<Player, Location> lastLocMap = new HashMap<>();

    private DialogueManagerList dialogueManagerList;
    private List<Player> dialogueList;

    public ActiveCraftCore() {
        instance = this;
        plugin = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.dialogueManagerList = new DialogueManagerList();
        this.dialogueList = new ArrayList<Player>();
        this.vanishManager = new VanishManager(this);

        profileMenuList = new HashMap<>();

        tpaList = new HashMap<>();

        //gui creator stuff
        guiDataMap = new HashMap<>();
        guiList = new HashMap<>();

        int pluginId = 12627;
        Metrics metrics = new Metrics(this, pluginId);

        this.register();
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
            Bukkit.getPluginManager().addPermission(new Permission("activecraft.warp.self." + s, "Permission to warp yourself to a specific warp.", PermissionDefault.OP, childMap));
            childMap.clear();
            childMap.put("activecraft.warp.others", true);
            childMap.put("activecraft.warp", true);
            Bukkit.getPluginManager().addPermission(new Permission("activecraft.warp.others." + s, "Permission to warp another player to a specific warp.", PermissionDefault.OP, childMap));
        }

        log("Plugin loaded.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        log("Plugin unloaded.");
    }

    public void log(String text) {
        Bukkit.getConsoleSender().sendMessage(PREFIX + text);
    }

    private void register() {

        // listener
        PluginManager pluginManager = Bukkit.getPluginManager();

        //loadMaxHomePermissions();

        pluginManager.registerEvents(new JoinQuitListener(), this);
        pluginManager.registerEvents(new OffInvSeeCommand(), this);
        pluginManager.registerEvents(new MessageListener(), this);
        pluginManager.registerEvents(new LogCommand(), this);
        pluginManager.registerEvents(new LockdownListener(), this);
        pluginManager.registerEvents(new LockdownCommand(), this);
        pluginManager.registerEvents(new SignListener(), this);
        pluginManager.registerEvents(new SignInteractListener(), this);
        pluginManager.registerEvents(new ClearTabCompleteListener(), this);
        pluginManager.registerEvents(new CommandStickCommand(), this);
        pluginManager.registerEvents(new TeleportListener(), this);
        pluginManager.registerEvents(new DeathListener(), this);
        pluginManager.registerEvents(new LoginListener(), this);
        pluginManager.registerEvents(new TableMenuListener(), this);
        pluginManager.registerEvents(new BowCommand(), this);
        pluginManager.registerEvents(new VanillaCommandListener(), this);

        //profile menu
        pluginManager.registerEvents(new MainProfileListener(), this);
        pluginManager.registerEvents(new ActionProfileListener(), this);
        pluginManager.registerEvents(new HomeListProfileListener(), this);
        pluginManager.registerEvents(new GamemodeSwitcherProfileListener(), this);
        pluginManager.registerEvents(new StorageProfileListener(), this);
        pluginManager.registerEvents(new ViolationsProfileListener(), this);
        pluginManager.registerEvents(new ReasonsProfileListener(), this);

        //gui
        pluginManager.registerEvents(new GuiListener(), this);


        // commands
        Bukkit.getPluginCommand("heal").setExecutor(new HealCommand());
        Bukkit.getPluginCommand("spawn").setExecutor(new SpawnCommand());
        Bukkit.getPluginCommand("setspawn").setExecutor(new SpawnCommand());
        Bukkit.getPluginCommand("ping").setExecutor(new PingCommand());
        Bukkit.getPluginCommand("broadcast").setExecutor(new BroadCastCommand());
        Bukkit.getPluginCommand("msg").setExecutor(new MsgCommand());
        Bukkit.getPluginCommand("invsee").setExecutor(new InvseeCommand());
        Bukkit.getPluginCommand("vanish").setExecutor(new VanishCommand());
        Bukkit.getPluginCommand("creative").setExecutor(new GamemodeCommand());
        Bukkit.getPluginCommand("survival").setExecutor(new GamemodeCommand());
        Bukkit.getPluginCommand("spectator").setExecutor(new GamemodeCommand());
        Bukkit.getPluginCommand("adventure").setExecutor(new GamemodeCommand());
        Bukkit.getPluginCommand("clearinventory").setExecutor(new ClearInvCommand());
        Bukkit.getPluginCommand("feed").setExecutor(new FeedCommand());
        Bukkit.getPluginCommand("craftingtable").setExecutor(new TableCommands());
        Bukkit.getPluginCommand("anvil").setExecutor(new TableCommands());
        Bukkit.getPluginCommand("enchanttable").setExecutor(new TableCommands());
        Bukkit.getPluginCommand("cartographytable").setExecutor(new TableCommands());
        Bukkit.getPluginCommand("grindstone").setExecutor(new TableCommands());
        Bukkit.getPluginCommand("loom").setExecutor(new TableCommands());
        Bukkit.getPluginCommand("smithingtable").setExecutor(new TableCommands());
        Bukkit.getPluginCommand("stonecutter").setExecutor(new TableCommands());
        Bukkit.getPluginCommand("playtime").setExecutor(new PlayTimeCommand());
        Bukkit.getPluginCommand("repair").setExecutor(new RepairCommand());
        Bukkit.getPluginCommand("colornick").setExecutor(new ColorNickCommand());
        Bukkit.getPluginCommand("fly").setExecutor(new FlyCommand());
        //Bukkit.getPluginCommand("offinvsee").setExecutor(new OffInvSeeCommand());
        Bukkit.getPluginCommand("nick").setExecutor(new NickCommand());
        Bukkit.getPluginCommand("god").setExecutor(new GodCommand());
        Bukkit.getPluginCommand("enderchest").setExecutor(new EnderchestCommand());
        Bukkit.getPluginCommand("tpa").setExecutor(new TpaCommand());
        Bukkit.getPluginCommand("tpaccept").setExecutor(new TpAcceptCommand());
        Bukkit.getPluginCommand("tpadeny").setExecutor(new TpaDenyCommand());
        Bukkit.getPluginCommand("warp").setExecutor(new WarpCommand());
        Bukkit.getPluginCommand("warps").setExecutor(new WarpCommand());
        Bukkit.getPluginCommand("setwarp").setExecutor(new WarpCommand());
        Bukkit.getPluginCommand("delwarp").setExecutor(new WarpCommand());
        Bukkit.getPluginCommand("home").setExecutor(new HomeCommand());
        Bukkit.getPluginCommand("sethome").setExecutor(new SetHomeCommand());
        Bukkit.getPluginCommand("delhome").setExecutor(new DelHomeCommand());
        Bukkit.getPluginCommand("suicide").setExecutor(new SuicideCommand());
        Bukkit.getPluginCommand("tphere").setExecutor(new TphereCommand());
        Bukkit.getPluginCommand("opitems").setExecutor(new OpItemsCommand());
        Bukkit.getPluginCommand("kickall").setExecutor(new KickAllCommand());
        Bukkit.getPluginCommand("ram").setExecutor(new RamCommand());
        Bukkit.getPluginCommand("tp").setExecutor(new TpCommand());
        Bukkit.getPluginCommand("staffchat").setExecutor(new StaffChatCommand());
        Bukkit.getPluginCommand("reply").setExecutor(new ReplyCommand());
        Bukkit.getPluginCommand("portal").setExecutor(new PortalCommand());
        Bukkit.getPluginCommand("lastonline").setExecutor(new LastOnlineCommand());
        Bukkit.getPluginCommand("whois").setExecutor(new WhoIsCommand());
        Bukkit.getPluginCommand("butcher").setExecutor(new ButcherCommand());
        Bukkit.getPluginCommand("item").setExecutor(new ItemCommand());
        Bukkit.getPluginCommand("ban").setExecutor(new BanCommand());
        Bukkit.getPluginCommand("whereami").setExecutor(new WhereAmICommand());
        Bukkit.getPluginCommand("weather").setExecutor(new WeatherCommand());
        Bukkit.getPluginCommand("more").setExecutor(new MoreCommand());
        Bukkit.getPluginCommand("restart-server").setExecutor(new RestartCommand());
        Bukkit.getPluginCommand("mute").setExecutor(new MuteCommand());
        Bukkit.getPluginCommand("hat").setExecutor(new HatCommand());
        Bukkit.getPluginCommand("log").setExecutor(new LogCommand());
        Bukkit.getPluginCommand("skull").setExecutor(new SkullCommand());
        Bukkit.getPluginCommand("strike").setExecutor(new StrikeCommand());
        Bukkit.getPluginCommand("break").setExecutor(new BreakCommand());
        Bukkit.getPluginCommand("firework").setExecutor(new FireWorkCommand());
        Bukkit.getPluginCommand("book").setExecutor(new BookCommand());
        Bukkit.getPluginCommand("xp").setExecutor(new XpCommand());
        Bukkit.getPluginCommand("enchant").setExecutor(new EnchantCommand());
        Bukkit.getPluginCommand("lockdown").setExecutor(new LockdownCommand());
        Bukkit.getPluginCommand("afk").setExecutor(new AfkCommand());
        Bukkit.getPluginCommand("top").setExecutor(new TopCommand());
        Bukkit.getPluginCommand("explode").setExecutor(new ExplodeCommand());
        Bukkit.getPluginCommand("warn").setExecutor(new WarnCommand());
        Bukkit.getPluginCommand("known-ips").setExecutor(new KnownIpsCommand());
        Bukkit.getPluginCommand("profile").setExecutor(new ProfileCommand());
        Bukkit.getPluginCommand("lastcoords").setExecutor(new LastCoordsCommand());
        Bukkit.getPluginCommand("verify").setExecutor(new VerifyCommand());
        Bukkit.getPluginCommand("tpall").setExecutor(new TpAllCommand());
        Bukkit.getPluginCommand("summon").setExecutor(new SpawnMobCommand());
        Bukkit.getPluginCommand("spawner").setExecutor(new SpawnerCommand());
        Bukkit.getPluginCommand("edit-sign").setExecutor(new EditSignCommand());
        Bukkit.getPluginCommand("randomtp").setExecutor(new RandomTPCommand());
        Bukkit.getPluginCommand("walkspeed").setExecutor(new WalkspeedCommand());
        Bukkit.getPluginCommand("leathercolor").setExecutor(new LeatherColorCommand());
        Bukkit.getPluginCommand("knockbackstick").setExecutor(new KnockbackStickCommand());
        Bukkit.getPluginCommand("fireball").setExecutor(new FireBallCommand());
        Bukkit.getPluginCommand("playerlist").setExecutor(new PlayerlistCommand());
        Bukkit.getPluginCommand("realname").setExecutor(new RealNameCommand());
        Bukkit.getPluginCommand("kick").setExecutor(new KickCommand());
        Bukkit.getPluginCommand("commandstick").setExecutor(new CommandStickCommand());
        Bukkit.getPluginCommand("back").setExecutor(new BackCommand());
        Bukkit.getPluginCommand("tablemenu").setExecutor(new TableMenuCommand());
        Bukkit.getPluginCommand("language").setExecutor(new LanguageCommand());
        Bukkit.getPluginCommand("bow").setExecutor(new BowCommand());
        Bukkit.getPluginCommand("sudo").setExecutor(new SudoCommand());
    }

    public static ActiveCraftCore getPlugin() {
        return plugin;
    }

    public FileConfig getWarpsConfig() {
        return warpsConfig;
    }

    public static VanishManager getVanishManager() {
        return vanishManager;
    }

    public void startTimer() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            @Override
            public void run() {

                for (Player player : Bukkit.getOnlinePlayers()) {

                    FileConfig fileConfig = new FileConfig("playtime.yml");
                    FileConfig mainConfig = new FileConfig("config.yml");

                    int hours = fileConfig.getInt(player.getName() + ".hours");
                    int minutes = fileConfig.getInt(player.getName() + ".minutes");


                    minutes++;

                    fileConfig.set(player.getName() + ".minutes", minutes);
                    fileConfig.saveConfig();

                    if (minutes == 60) {


                        fileConfig.set(player.getName() + ".minutes", 0);

                        hours++;

                        fileConfig.set(player.getName() + ".hours", hours);
                        fileConfig.saveConfig();
                    }
                    FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");
                    if (minutes + hours * 60 >= mainConfig.getInt("remove-default-mute-after") && mainConfig.getInt("remove-default-mute-after") >= 0) {
                        if (playerdataConfig.getBoolean("default-mute")) {
                            player.sendMessage(ChatColor.GOLD + "Your default-mute has been removed. You are now able to talk.");
                            playerdataConfig.set("default-mute", false);
                            playerdataConfig.saveConfig();
                        }
                    }
                }
            }
        }, 20 * 60, 20 * 60);
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        FileConfig mainConfig = new FileConfig("config.yml");
        mainConfig.set("language", language.getCode().toLowerCase());
        mainConfig.saveConfig();
        this.language = language;
    }

    public ActiveCraftMessage getActiveCraftMessage() {
        return activeCraftMessage;
    }

    public void setActiveCraftMessage(ActiveCraftMessage activeCraftMessage) {
        this.activeCraftMessage = activeCraftMessage;
    }

    public DialogueManagerList getDialogueManagerList() {
        return dialogueManagerList;
    }

    public void setLastLocationForPlayer(Player player, Location loc) {
        lastLocMap.put(player, loc);
    }

    public Location getLastLocationForPlayer(Player player) {
        return lastLocMap.get(player);
    }

    public HashMap<Player, Location> getLastLocMap() {
        return lastLocMap;
    }

    public HashMap<GuiCreator, GuiData> getGuiDataMap() {
        return guiDataMap;
    }

    public void addToGuiDataMap(GuiCreator guiCreator, GuiData guiData) {
        guiDataMap.put(guiCreator, guiData);
    }

    public void removeFromGuiDataMap(GuiCreator guiCreator) {
        guiDataMap.remove(guiCreator);
    }

    public GuiData getFromGuiDataMap(GuiCreator guiCreator) {
        return guiDataMap.get(guiCreator);
    }

    public HashMap<Integer, Gui> getGuiList() {
        return guiList;
    }

    public void setGuiList(HashMap<Integer, Gui> guiList) {
        this.guiList = guiList;
    }

    public Gui getGuiById(int id) {
        return this.guiList.get(id);
    }

    public List<Player> getDialogueList() {
        return dialogueList;
    }

    public void setDialogueList(List<Player> dialogueList) {
        this.dialogueList = dialogueList;
    }

    public void addToDialogueList(Player player) {
        this.dialogueList.add(player);
    }

    public void removeFromDialogueList(Player player) {
        this.dialogueList.remove(player);
    }

    public HashMap<Player, Player> getTpaList() {
        return tpaList;
    }

    public void setTpaList(HashMap<Player, Player> tpaList) {
        this.tpaList = tpaList;
    }

    public void addToTpaList(Player p1, Player p2) {
        this.tpaList.put(p1, p2);
    }

    public void removeFromTpaList(Player player) {
        this.tpaList.remove(player);
    }

    public HashMap<Player, ProfileMenu> getProfileMenuList() {
        return profileMenuList;
    }

    public void setProfileMenuList(HashMap<Player, ProfileMenu> profileMenuList) {
        this.profileMenuList = profileMenuList;
    }

    public void addToProfileMenuList(Player player, ProfileMenu profileMenu) {
        this.profileMenuList.put(player, profileMenu);
    }

    public void removeFromProfileMenuList(Player player) {
        this.profileMenuList.remove(player);
    }

    public ProfileMenu getFromProfileMenuList(Player player) {
        return this.profileMenuList.get(player);
    }

    public HashMap<String, UUID> getPlayerlist() {
        FileConfig playerlistConfig = new FileConfig("playerlist.yml");
        HashMap<String, UUID> playerlist = new HashMap<>();
        for (String combinedString : playerlistConfig.getStringList("players")) {
            playerlist.put(combinedString.split(",")[0], UUID.fromString(combinedString.split(",")[1]));
        }
        return playerlist;
    }

    public String getPlayernameByUUID(String uuid) {
        FileConfig playerlistConfig = new FileConfig("playerlist.yml");
        for (String combinedString : playerlistConfig.getStringList("players")) {
            if (combinedString.split(",")[1].equals(uuid)) {
                return combinedString.split(",")[0];
            }
        }
        return null;
    }

    public String getPlayernameByUUID(UUID uuid) {
        return getPlayernameByUUID(uuid.toString());
    }

    public String getUUIDByPlayername(String playername) {
        FileConfig playerlistConfig = new FileConfig("playerlist.yml");
        for (String combinedString : playerlistConfig.getStringList("players")) {
            if (combinedString.split(",")[0].equals(playername)) {
                return combinedString.split(",")[1];
            }
        }
        return null;
    }
}
