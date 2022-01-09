package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GamemodeCommand extends ActiveCraftCommand {

    public GamemodeCommand() {
        super("survival", "creative", "adventure", "spectator");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        if (args.length == 0) {
            Player player = getPlayer(sender);
            switch (label) {
                case "su", "survival" -> {
                    checkPermission(sender, "gamemode.survival.self");
                    player.setGameMode(GameMode.SURVIVAL);
                }
                case "ad", "adventure" -> {
                    checkPermission(sender, "gamemode.adventure.self");
                    player.setGameMode(GameMode.ADVENTURE);
                }
                case "cr", "creative" -> {
                    checkPermission(sender, "gamemode.creative.self");
                    player.setGameMode(GameMode.CREATIVE);
                }
                case "sp", "spectator" -> {
                    checkPermission(sender, "gamemode.spectator.self");
                    player.setGameMode(GameMode.SPECTATOR);
                }
            }
        } else {
            Player target = getPlayer(args[0]);
            switch (label) {
                case "su", "survival" -> {
                    checkPermission(sender, "gamemode.survival.others");
                    checkTargetSelf(sender, target, "gamemode.survival.self");
                    target.setGameMode(GameMode.SURVIVAL);
                }
                case "ad", "adventure" -> {
                    checkPermission(sender, "gamemode.adventure.others");
                    checkTargetSelf(sender, target, "gamemode.adventure.self");
                    target.setGameMode(GameMode.ADVENTURE);
                }
                case "cr", "creative" -> {
                    checkPermission(sender, "gamemode.creative.others");
                    checkTargetSelf(sender, target, "gamemode.creative.self");
                    target.setGameMode(GameMode.CREATIVE);
                }
                case "sp", "spectator" -> {
                    checkPermission(sender, "gamemode.spectator.others");
                    checkTargetSelf(sender, target, "gamemode.spectator.self");
                    target.setGameMode(GameMode.SPECTATOR);
                }
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}
