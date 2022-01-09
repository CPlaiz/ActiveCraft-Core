package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FireBallCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("activecraft.fireball")) {
                if (args.length == 2) {
                    Integer num = null;
                    try {
                        num = Integer.valueOf(args[0]);
                    } catch (NumberFormatException ignored) {
                    }
                    if (num == null) {
                        sender.sendMessage(Errors.INVALID_NUMBER());
                        return false;
                    }
                    Boolean bool = null;
                    try {
                        bool = Boolean.valueOf(args[1]);
                    } catch (NumberFormatException ignored) {
                    }
                    if (bool == null) {
                        sender.sendMessage(Errors.INVALID_ARGUMENTS());
                        return false;
                    }

                    Fireball fireball = (Fireball) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREBALL);
                    fireball.setYield(Float.parseFloat(args[0]));
                    fireball.setIsIncendiary(Boolean.parseBoolean(args[1]));
                } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }
}