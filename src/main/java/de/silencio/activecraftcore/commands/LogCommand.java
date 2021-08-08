package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.io.File;

public class LogCommand implements CommandExecutor, Listener {



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName() + ".yml");

            if(sender.hasPermission("activecraft.log")) {

            if(args.length == 1) {
                if (args[0].equalsIgnoreCase("on")) {
                    player.sendMessage(ChatColor.GOLD + "Log activated.");

                    playerdataConfig.set("log-enabled", true);
                    playerdataConfig.saveConfig();
                }

                if (args[0].equalsIgnoreCase("off")) {
                    player.sendMessage(ChatColor.GOLD + "Log deactivated.");

                    playerdataConfig.set("log-enabled", false);
                    playerdataConfig.saveConfig();
                }
            } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
            } else sender.sendMessage(Errors.NO_PERMISSION);
        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCommandSent(PlayerCommandPreprocessEvent event) {
        Player executingPlayer = event.getPlayer();
        String eventMessage = event.getMessage();

        for (Player player : Bukkit.getOnlinePlayers()) {
            FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName() + ".yml");
            if (playerdataConfig.getBoolean("log-enabled")) {
                if (player.hasPermission("activecraft.log")) {
                    player.sendMessage(ChatColor.GOLD + "[Log] " + ChatColor.AQUA + executingPlayer.getName() + ChatColor.GOLD + " executed command " + ChatColor.AQUA + eventMessage);
                }
            }
        }
    }
}

// /heal  [Log] Aqua + target + Gold + executed command + Aqua + heal
// /heal (no permission)  [Log] Aqua + target + Gold + tried to execute command + Aqua + heal