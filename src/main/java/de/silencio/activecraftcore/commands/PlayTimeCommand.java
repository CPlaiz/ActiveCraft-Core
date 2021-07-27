package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayTimeCommand implements CommandExecutor {

    private Main plugin;

    public PlayTimeCommand(Main main) {
        this.plugin = main;
    }

    public PlayTimeCommand() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if(sender.hasPermission("activecraft.playtime")) {
                FileConfig fileConfig = new FileConfig("playtime.yml");
                if(args.length == 0) {
                    if(sender instanceof Player) {
                        int hours = fileConfig.getInt(sender.getName() + ".hours");
                        int minutes = fileConfig.getInt(sender.getName() + ".minutes");
                        sender.sendMessage(ChatColor.GOLD + "Playtime: " + ChatColor.AQUA + hours + ChatColor.GOLD + " Hours and " + ChatColor.AQUA + minutes + ChatColor.GOLD + " Minutes");
                    } else sender.sendMessage(Errors.NOT_A_PLAYER);
                } else if(args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    int hours = fileConfig.getInt(target.getName() + ".hours");
                    int minutes = fileConfig.getInt(target.getName() + ".minutes");
                    sender.sendMessage(ChatColor.GOLD + "Playtime of " + ChatColor.AQUA + args[0] + ChatColor.GOLD + ": " + ChatColor.AQUA + hours + ChatColor.GOLD + " Hours and " + ChatColor.AQUA + minutes + ChatColor.GOLD + " Minutes");
                } else sender.sendMessage(Errors.INVALID_ARGUMENTS);

                if(args.length > 1) {
                    sender.sendMessage(Errors.TOO_MANY_ARGUMENTS);
                }

            } else sender.sendMessage(Errors.NO_PERMISSION);
        return true;
    }
}
 // Warum "Find why 'plugin' could be null"