package de.silencio.activecraftcore;

import de.silencio.activecraftcore.commands.*;
import de.silencio.activecraftcore.listener.*;
import de.silencio.activecraftcore.listener.inventory.ProfileListener;
import de.silencio.activecraftcore.messages.Dialogue.DialogueListenerList;
import de.silencio.activecraftcore.messages.Dialogue.DialogueManagerList;
import de.silencio.activecraftcore.ownlisteners.ListenerManager;
import de.silencio.activecraftcore.utils.Config;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {

    public static String PREFIX = "§6[ActiveCraft-Core] ";
    public static Main instance;
    private static Main plugin;
    private static VanishManager vanishManager;

    private Config playtimeConfig;
    private Config locationsConfig;
    private Config config;
    private Config homeconfig;
    private static Config warpsConfig;

    public ListenerManager listenerManager;

    public DialogueManagerList dialogueManagerList;
    public DialogueListenerList dialogueListenerList;

    public Main() {
        instance = this;
        plugin = this;
    }

    public ListenerManager getListenerManager() {
        return listenerManager;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.listenerManager = new ListenerManager();
        this.dialogueManagerList = new DialogueManagerList();
        this.dialogueListenerList = new DialogueListenerList();
        this.vanishManager = new VanishManager(this);

        this.register();
        startTimer();

        saveDefaultConfig();

        config = new Config("config.yml", getDataFolder());
        warpsConfig = new Config("warps.yml", getDataFolder());
        playtimeConfig = new Config("playtime.yml", getDataFolder());
        locationsConfig = new Config("locations.yml", getDataFolder());
        homeconfig = new Config("homes.yml", getDataFolder());

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
        pluginManager.registerEvents(new MessageManager(), this);
        pluginManager.registerEvents(new EnderPealCooldown(), this);
        pluginManager.registerEvents(new LogCommand(), this);
        pluginManager.registerEvents(new LockdownListener(), this);
        pluginManager.registerEvents(new LockdownCommand(), this);
        pluginManager.registerEvents(new SignListener(), this);
        pluginManager.registerEvents(new SignInteractListener(), this);
        //pluginManager.registerEvents(new ProfileCommand(), this);

        //custom Listeners
        dialogueListenerList = new DialogueListenerList();
        listenerManager.addListener(new StaffChatListenerTest());


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
        //Bukkit.getPluginCommand("tp").setExecutor(new TpCommand());
        Bukkit.getPluginCommand("staffchat").setExecutor(new StaffChatCommand());
        Bukkit.getPluginCommand("reply").setExecutor(new ReplyCommand());
        Bukkit.getPluginCommand("portal").setExecutor(new PortalCommand());
        Bukkit.getPluginCommand("lastonline").setExecutor(new LastOnlineCommand());
        Bukkit.getPluginCommand("whois").setExecutor(new WhoIsCommand());
        Bukkit.getPluginCommand("i").setExecutor(new QuickGiveCommand());
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
        Bukkit.getPluginCommand("edit-sign").setExecutor(new EditSignCommand());
    }

    public static Main getPlugin() {
        return plugin;
    }

    public static Config getWarpsConfig() {
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

    public DialogueManagerList getDialogueManagerList() {
        return dialogueManagerList;
    }

    public DialogueListenerList getDialogueListenerList() {
        return dialogueListenerList;
    }

}