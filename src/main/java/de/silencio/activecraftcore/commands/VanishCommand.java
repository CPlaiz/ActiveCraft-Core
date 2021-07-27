package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        VanishManager vanishManager = Main.getVanishManager();
        Player p = (Player) sender;


        if (p.hasPermission("activecraft.vanish")) {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                if (vanishManager.isVanished(target)) {
                    vanishManager.setVanished(target, false);
                    target.sendMessage(ChatColor.GOLD + "You are now " + ChatColor.GREEN + "invisible!");
                    sender.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " is now " + ChatColor.GREEN + "visible!");
                } else {
                    vanishManager.setVanished(target, true);
                    target.sendMessage(ChatColor.GOLD + "You are now " + ChatColor.GREEN + "invisible!");
                    sender.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " is now " + ChatColor.GREEN + "invisible!");
                    }
                } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
            } else if (sender instanceof Player) {
                if (vanishManager.isVanished(p)) {
                    vanishManager.setVanished(p, false);
                    p.sendMessage(ChatColor.GOLD + "You are now " + ChatColor.AQUA + "visible!");
                    Bukkit.broadcastMessage(ChatColor.AQUA + "» " + ChatColor.GOLD  + p.getDisplayName());


                } else {
                    vanishManager.setVanished(p, true);
                    p.sendMessage(ChatColor.GOLD + "You are now " + ChatColor.AQUA + "invisible!");
                    Bukkit.broadcastMessage(ChatColor.RED + "« " + ChatColor.GOLD + p.getDisplayName());
                }
            }
        } else sender.sendMessage(Errors.NO_PERMISSION);

        return true;
    }
}
