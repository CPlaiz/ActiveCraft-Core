package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LogCommand implements CommandExecutor, Listener, TabCompleter{



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");

            if(sender.hasPermission("activecraft.log")) {

            if(args.length == 1) {
                if (args[0].equalsIgnoreCase("on")) {
                    player.sendMessage(CommandMessages.ENABLE_LOG());

                    playerdataConfig.set("log-enabled", true);
                    playerdataConfig.saveConfig();
                }

                if (args[0].equalsIgnoreCase("off")) {
                    player.sendMessage(CommandMessages.DISABLE_LOG());

                    playerdataConfig.set("log-enabled", false);
                    playerdataConfig.saveConfig();
                }
            } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCommandSent(PlayerCommandPreprocessEvent event) {
        Player executingPlayer = event.getPlayer();
        String eventMessage = event.getMessage();

        for (Player player : Bukkit.getOnlinePlayers()) {
            FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");
            if (playerdataConfig.getBoolean("log-enabled")) {
                if (player.hasPermission("activecraft.log")) {
                    player.sendMessage(CommandMessages.LOG_PREFIX(Bukkit.getPlayer(executingPlayer.getName()), eventMessage));
                }
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Player p = (Player) sender;

        if (args.length == 0) return list;
        if (args.length == 1) {
            list.add("on");
            list.add("off");
        }

        ArrayList<String> completerList = new ArrayList<>();
        String currentarg = args[args.length - 1].toLowerCase();
        for (String s : list) {
            String s1 = s.toLowerCase();
            if (s1.startsWith(currentarg)) {
                completerList.add(s);
            }
        }
        return completerList;
    }
}