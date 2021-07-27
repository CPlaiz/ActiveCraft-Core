package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;


        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (label.equalsIgnoreCase("su") || label.equalsIgnoreCase("survival")) {
                if (sender.hasPermission("activecraft.gamemode.survival")) {
                    target.setGameMode(GameMode.SURVIVAL);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }

            if (label.equalsIgnoreCase("cr") || label.equalsIgnoreCase("creative")) {
                if (sender.hasPermission("activecraft.gamemode.creative")) {
                    target.setGameMode(GameMode.CREATIVE);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }

            if (label.equalsIgnoreCase("sp") || label.equalsIgnoreCase("spectator")) {
                if (sender.hasPermission("activecraft.gamemode.spectator")) {
                    target.setGameMode(GameMode.SPECTATOR);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }

            if (label.equalsIgnoreCase("ad") || label.equalsIgnoreCase("adventure")) {
                if (sender.hasPermission("activecraft.gamemode.adventure")) {
                    target.setGameMode(GameMode.ADVENTURE);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }
        } else if (sender instanceof Player){
            if (label.equalsIgnoreCase("su") || label.equalsIgnoreCase("survival")) {
                if (sender.hasPermission("activecraft.gamemode.survival")) {
                    player.setGameMode(GameMode.SURVIVAL);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }

            if (label.equalsIgnoreCase("cr") || label.equalsIgnoreCase("creative")) {
                if (sender.hasPermission("activecraft.gamemode.creative")) {
                    player.setGameMode(GameMode.CREATIVE);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }

            if (label.equalsIgnoreCase("sp") || label.equalsIgnoreCase("spectator")) {
                if (sender.hasPermission("activecraft.gamemode.spectator")) {
                    player.setGameMode(GameMode.SPECTATOR);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }

            if (label.equalsIgnoreCase("ad") || label.equalsIgnoreCase("adventure")) {
                if (sender.hasPermission("activecraft.gamemode.adventure")) {
                    player.setGameMode(GameMode.ADVENTURE);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            }
        }
        return true;
    }
}
