package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TpAcceptCommand extends TpaCommand implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfig fileConfig = new FileConfig("config.yml");
        if (sender instanceof Player) {

            if (sender.hasPermission("activecraft.tpaccept")) {
                if (tpaList.containsKey(sender)) {

                    Player player = (Player) sender;
                    Player target = tpaList.get(sender);
                    Location loc = player.getLocation();
                    if (!fileConfig.getBoolean("use-timer-on-tpa")) {
                        tpaList.get(sender).sendActionBar(ChatColor.GOLD + "Teleporting...");
                        tpaList.get(sender).teleport(loc);
                        tpaList.remove(sender);
                    } else {
                        BukkitRunnable runnable = new BukkitRunnable() {
                            int time = 3;

                            @Override
                            public void run() {

                                if (time == 0) {
                                    tpaList.get(sender).sendActionBar(ChatColor.GOLD + "Teleporting...");
                                    tpaList.get(sender).teleport(loc);
                                    target.sendMessage(ChatColor.GOLD + "Teleported to " + ChatColor.AQUA + player.getDisplayName() + ChatColor.GOLD + ".");
                                    sender.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " teleported to you.");
                                    cancel();
                                    tpaList.remove(sender);
                                    return;
                                }

                                tpaList.get(sender).sendActionBar(ChatColor.GOLD + "" + time);
                                time--;

                            }
                        };
                        runnable.runTaskTimer(Main.getPlugin(), 0, 20);
                    }


                } else sender.sendMessage(Errors.WARNING + "You don't have any pending TPA requests!");
            } else sender.sendMessage(Errors.NO_PERMISSION);

        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
