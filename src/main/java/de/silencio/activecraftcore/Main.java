package de.silencio.activecraftcore;

import de.silencio.activecraftcore.commands.*;
import de.silencio.activecraftcore.gui.Gui;
import de.silencio.activecraftcore.gui.GuiData;
import de.silencio.activecraftcore.gui.GuiHistoryMap;
import de.silencio.activecraftcore.listener.*;
import de.silencio.activecraftcore.listener.inventory.ProfileListener;
import de.silencio.activecraftcore.messages.ActiveCraftMessage;
import de.silencio.activecraftcore.dialogue.DialogueListenerList;
import de.silencio.activecraftcore.dialogue.DialogueManagerList;
import de.silencio.activecraftcore.messages.Language;
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

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class Main extends JavaPlugin {

    public static String PREFIX = "§6[ActiveCraft-Core] ";
    public static Main instance;
    private static Main plugin;
    private static VanishManager vanishManager;

    private Language language;
    private ActiveCraftMessage activeCraftMessage;

    private FileConfig playtimeConfig;
    private FileConfig locationsConfig;
    private FileConfig config;
    private FileConfig homeconfig;
    private FileConfig warpsConfig;

    private GuiData guiData;
    private GuiHistoryMap guiHistoryMap;
    private HashMap<Integer, Gui> guiList;

    private HashMap<Player, Location> lastLocMap = new HashMap<>();

    public DialogueManagerList dialogueManagerList;
    public DialogueListenerList dialogueListenerList;

    public Main() {
        instance = this;
        plugin = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.dialogueManagerList = new DialogueManagerList();
        this.dialogueListenerList = new DialogueListenerList();
        this.vanishManager = new VanishManager(this);

        //gui creator stuff
        guiData = new GuiData();
        guiList = new HashMap<>();
        guiHistoryMap = new GuiHistoryMap();

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

        pluginManager.registerEvents(new JoinQuitListener(), this);
        pluginManager.registerEvents(new ProfileListener(), this);
        pluginManager.registerEvents(new OffInvSeeCommand(), this);
        pluginManager.registerEvents(new MessageListener(), this);
        pluginManager.registerEvents(new EnderPealCooldown(), this);
        pluginManager.registerEvents(new LogCommand(), this);
        pluginManager.registerEvents(new LockdownListener(), this);
        pluginManager.registerEvents(new LockdownCommand(), this);
        pluginManager.registerEvents(new SignListener(), this);
        pluginManager.registerEvents(new SignInteractListener(), this);
        //pluginManager.registerEvents(new ProfileCommand(), this);
        pluginManager.registerEvents(new ClearTabCompleteListener(), this);
        pluginManager.registerEvents(new CommandStickCommand(), this);
        pluginManager.registerEvents(new TeleportListener(), this);
        pluginManager.registerEvents(new DeathListener(), this);
        pluginManager.registerEvents(new LoginListener(), this);

        //custom Listeners
        dialogueListenerList = new DialogueListenerList();


        // commands

        Bukkit.getPluginCommand("heal").setExecutor(new HealCommand());
        Bukkit.getPluginCommand("spawn").setExecutor(new SpawnCommand());
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
        Bukkit.getPluginCommand("offinvsee").setExecutor(new OffInvSeeCommand());
        Bukkit.getPluginCommand("nick").setExecutor(new NickCommand());
        Bukkit.getPluginCommand("god").setExecutor(new GodCommand());
        Bukkit.getPluginCommand("enderchest").setExecutor(new EnderchestCommand());
        Bukkit.getPluginCommand("tpa").setExecutor(new TpaCommand());
        Bukkit.getPluginCommand("tpaccept").setExecutor(new TpAcceptCommand());
        Bukkit.getPluginCommand("warp").setExecutor(new WarpCommand());
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
        Bukkit.getPluginCommand("i").setExecutor(new QuickGiveCommand());
        //Bukkit.getPluginCommand("butcher").setExecutor(new ButcherCommand());
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
        Bukkit.getPluginCommand("summon").setExecutor(new SpawnMobCommand());
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
    }

    public static Main getPlugin() {
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

    public DialogueListenerList getDialogueListenerList() {
        return dialogueListenerList;
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

    public GuiData getGuiData() {
        return guiData;
    }

    public void setGuiData(GuiData guiData) {
        this.guiData = guiData;
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

    public GuiHistoryMap getGuiHistoryMap() {
        return guiHistoryMap;
    }

    public void setGuiHistoryMap(GuiHistoryMap guiHistoryMap) {
        this.guiHistoryMap = guiHistoryMap;
    }
}
