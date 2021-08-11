package de.silencio.activecraftcore;

import de.silencio.activecraftcore.commands.*;
import de.silencio.activecraftcore.listener.EnderPealCooldown;
import de.silencio.activecraftcore.listener.JoinQuitListener;
import de.silencio.activecraftcore.listener.LockdownListener;
import de.silencio.activecraftcore.listener.MessageManager;
import de.silencio.activecraftcore.listener.inventory.Navigator;
import de.silencio.activecraftcore.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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

        this.register();
        startTimer();

        saveDefaultConfig();

        config = new Config("config.yml" , getDataFolder());
        warpsConfig = new Config("warps.yml", getDataFolder());
        playtimeConfig = new Config("playtime.yml" , getDataFolder());
        locationsConfig = new Config("locations.yml" , getDataFolder());
        homeconfig = new Config("homes.yml", getDataFolder());

        log("Plugin loaded.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        log("Plugin unloaded.");
    }

    public void log (String text) {
        Bukkit.getConsoleSender().sendMessage(PREFIX + text);
    }

    private void register() {

        // listener
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new JoinQuitListener(), this);
        pluginManager.registerEvents(new Navigator(), this);
        pluginManager.registerEvents(new OffInvSeeCommand(), this);
        pluginManager.registerEvents(new MessageManager(), this);
        pluginManager.registerEvents(new EnderPealCooldown(), this);
        pluginManager.registerEvents(new LogCommand(), this);
        pluginManager.registerEvents(new LockdownListener(), this);
        pluginManager.registerEvents(new LockdownCommand(), this);

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
        Bukkit.getPluginCommand("realname").setExecutor(new RealNameCommand());
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
        Bukkit.getPluginCommand("butcher").setExecutor(new ButcherCommand());
        Bukkit.getPluginCommand("item").setExecutor(new ItemCommand());
        Bukkit.getPluginCommand("ban").setExecutor(new BanCommand());
        Bukkit.getPluginCommand("whereami").setExecutor(new WhereAmICommand());
        Bukkit.getPluginCommand("weather").setExecutor(new WeatherCommand());
        Bukkit.getPluginCommand("more").setExecutor(new MoreCommand());
        Bukkit.getPluginCommand("rehstart").setExecutor(new RestartCommand());
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

                for(Player players : Bukkit.getOnlinePlayers()) {

                    FileConfig fileConfig = new FileConfig("playtime.yml");

                    int hours = fileConfig.getInt(players.getName() + ".hours");
                    int minutes = fileConfig.getInt(players.getName() + ".minutes");

                    minutes++;

                    fileConfig.set(players.getName() + ".minutes", minutes);
                    fileConfig.saveConfig();

                    if(minutes == 60) {

                        fileConfig.set(players.getName() + ".minutes", 0);

                        hours++;

                        fileConfig.set(players.getName() + ".hours", hours);
                        fileConfig.saveConfig();
                    }
                }
            }
        }, 20*60, 20*60);
    }

    public DialogueManagerList getDialogueManagerList() {
        return dialogueManagerList;
    }

    public DialogueListenerList getDialogueListenerList() {
        return dialogueListenerList;
    }

}
