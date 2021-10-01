package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import java.util.List;

public class ButcherCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            World world = player.getWorld();

            if (sender.hasPermission("activecraft.butcher")) {

                if (args.length == 0) {

                    List<Entity> enemies = player.getNearbyEntities(200, 500, 200);
                    int amount = enemies.size();

                    player.sendMessage(CommandMessages.KILLED_MOBS(amount + ""));

                    if(amount == 0) {
                        sender.sendMessage(Errors.WARNING() + CommandMessages.NO_MOBS());
                    }
                    for (int i = 0; i < amount; i++) {
                        Entity e = enemies.get(i);
                        if (e instanceof Monster) {
                            if(e != null) {
                                ((Monster) e).setHealth(0);
                            }
                        } else if (e instanceof Flying) {
                            if(e != null) {
                                ((Flying) e).setHealth(0);
                            }
                        } else if (e instanceof Slime) {
                            if(e != null) {
                                ((Slime) e).setHealth(0);
                            }
                        }
                    }
                } else sender.sendMessage(Errors.TOO_MANY_ARGUMENTS());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }
}