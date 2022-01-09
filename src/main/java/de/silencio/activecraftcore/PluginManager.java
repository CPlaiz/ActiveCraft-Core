package de.silencio.activecraftcore;

import de.silencio.activecraftcore.commands.*;
import de.silencio.activecraftcore.guicreator.GuiListener;
import de.silencio.activecraftcore.guis.profilemenu.listeners.*;
import de.silencio.activecraftcore.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PluginManager {

    private static HashMap<String, CommandExecutor> commands = new HashMap<>();
    private static List<Listener> listeners = new ArrayList<>();


    public static void init() {
        // general listeners
        addListeners(
                new JoinQuitListener(), new MessageListener(), new CommandListener(),
                new LockdownListener(), new SignListener(), new SignInteractListener(),
                new ClearTabCompleteListener(), new CommandStickCommand(), new TeleportListener(),
                new DeathListener(), new LoginListener(), new TableMenuListener(),
                new BowCommand(), new VanillaCommandListener(), new ServerPingListener(),
                new RespawnListener()
                );

        // ProfileMenu listeners
        addListeners(
                new ViolationsProfileListener(), new HomeListProfileListener(),
                new StorageProfileListener(), new ReasonsProfileListener(),
                new ActionProfileListener(), new MainProfileListener(),
                new GamemodeSwitcherProfileListener()
                );

        // gui creator
        addListeners(new GuiListener());


        //register ac commands
        registerCommands(
                // Sortiert
                new ACVersionCommand(),
                new AfkCommand(),
                new BackCommand(),
                new BanCommand(),
                new BookCommand(),
                new BowCommand(),
                new BreakCommand(),
                new BroadCastCommand(),
                new ButcherCommand(),
                new ClearInvCommand(),
                new ColorNickCommand(),
                new CommandStickCommand(),
                new DrainCommand(),
                new EditSignCommand(),
                new EnchantCommand(),
                new EnderchestCommand(),
                new ExplodeCommand(),
                new FeedCommand(),
                new FireBallCommand(),
                new FireWorkCommand(),
                new FlyCommand(),
                new GamemodeCommand(),
                new GodCommand(),
                new HatCommand(),
                new HealCommand(),
                new HomeCommand(),
                new InvseeCommand(),
                new ItemCommand(),
                new KickAllCommand(),
                new KickCommand(),
                new KnockbackStickCommand(),
                new KnownIpsCommand(),
                new LanguageCommand(),
                new LastCoordsCommand(),
                new LastOnlineCommand(),
                new LeatherColorCommand(),
                new LockdownCommand(),
                new LogCommand(),
                new MoreCommand(),
                new MsgCommand(),
                new MuteCommand(),
                new NickCommand(),
                new OfflineTpCommand(),
                new OpItemsCommand(),
                //new OffInvSeeCommand(),
                new PingCommand(),
                new PlayerlistCommand(),
                new PlayTimeCommand(),
                new PortalCommand(),
                new ProfileCommand(),
                new RamCommand(),
                new RandomTPCommand(),
                new RealNameCommand(),
                new RepairCommand(),
                new ReplyCommand(),
                new RestartCommand(),
                new SkullCommand(),
                new SocialSpyToggleCommand(),
                new SpawnCommand(),
                new SpawnerCommand(),
                new SummonCommand(),
                new StaffChatCommand(),
                new StrikeCommand(),
                new SudoCommand(),
                new SuicideCommand(),
                new TableCommands(),
                new TableMenuCommand(),
                new TopCommand(),
                new TpaCommand(),
                new TpAllCommand(),
                new TpCommand(),
                new TphereCommand(),
                new VanishCommand(),
                new VerifyCommand(),
                new WalkspeedCommand(),
                new WarnCommand(),
                new WarpCommand(),
                new WeatherCommand(),
                new WhereAmICommand(),
                new WhoIsCommand(),
                new XpCommand()
        );

        register();
    }

    private static void registerCommands(ActiveCraftCommand... activeCraftCommands) {
        for (ActiveCraftCommand activeCraftCommand : activeCraftCommands)
            for (String command : activeCraftCommand.getCommands())
                commands.put(command, activeCraftCommand);
    }

    private static void addListeners(Listener... listeners) {
        PluginManager.listeners.addAll(Arrays.asList(listeners));
    }

    private static void register() {
        for (Listener listener : listeners)
            Bukkit.getPluginManager().registerEvents(listener, ActiveCraftCore.getPlugin());
        for (String cmd : commands.keySet()) {
            try {
                Bukkit.getPluginCommand(cmd).setExecutor(commands.get(cmd));
            } catch (NullPointerException e) {
                Bukkit.getLogger().severe("Error loading ActiveCraft-Command \"" + cmd + "\".");
                Bukkit.getLogger().severe("Please contact the developers about this issue.");
            }
        }
    }
}
