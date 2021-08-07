package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class GodCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName() + ".yml");

            if(sender.hasPermission("activecraft.god")) {

                if(args.length == 1) {
                    switch(args[0]) {
                        case "on":
                        case "true":
                            player.setInvulnerable(true);
                            sender.sendMessage(ChatColor.GOLD + "God mode activated.");

                            playerdataConfig.set("godmode", "true");
                            playerdataConfig.saveConfig();
                            break;
                        case "off":
                        case "false":
                            player.setInvulnerable(false);
                            sender.sendMessage(ChatColor.GOLD + "God mode deactivated.");

                            playerdataConfig.set("godmode", "false");
                            playerdataConfig.saveConfig();
                            break;
                    }
                }

            } else sender.sendMessage(Errors.NO_PERMISSION);

            if(sender.hasPermission("activecraft.god.others")) {
                if (args.length == 2) {

                    Player target = Bukkit.getPlayer(args[0]);

                    FileConfig targetdataConfig = new FileConfig("playerdata" + File.separator + target.getName() + ".yml");

                    switch (args[1]) {
                        case "on":
                        case "true":
                            target.setInvulnerable(true);
                            player.sendMessage(ChatColor.GOLD + "God mode activated for " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + ".");
                            target.sendMessage(ChatColor.GOLD + "God mode activated by " + ChatColor.AQUA + player.getDisplayName() + ChatColor.GOLD + ".");

                            targetdataConfig.set("godmode", "true");
                            targetdataConfig.saveConfig();
                            break;
                        case "off":
                        case "false":
                            target.setInvulnerable(false);
                            player.sendMessage(ChatColor.GOLD + "God mode deactivated for " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + ".");
                            target.sendMessage(ChatColor.GOLD + "God mode deactivated by " + ChatColor.AQUA + player.getDisplayName() + ChatColor.GOLD + ".");

                            targetdataConfig.set("godmode", "true");
                            targetdataConfig.saveConfig();
                            break;
                    }
                }
            } else sender.sendMessage(Errors.NO_PERMISSION);
        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
