package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TpAcceptCommand extends TpaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfig fileConfig = new FileConfig("config.yml");
        if(args.length == 0) {
            if (sender instanceof Player) {
                if (sender.hasPermission("activecraft.tpaccept")) {
                    if (tpaList.containsKey(sender)) {

                        Player player = (Player) sender;
                        Player target = tpaList.get(sender);
                        Location loc = player.getLocation();
                        if (!fileConfig.getBoolean("use-timer-on-tpa")) {
                            tpaList.get(sender).sendActionBar(CommandMessages.TPACCEPT_ACTIONBAR());
                            tpaList.get(sender).teleport(loc);
                            tpaList.remove(sender);
                        } else {
                            BukkitRunnable runnable = new BukkitRunnable() {
                                int time = 3;

                                @Override
                                public void run() {

                                    if (time == 0) {
                                        tpaList.get(sender).sendActionBar(CommandMessages.TPACCEPT_ACTIONBAR());
                                        tpaList.get(sender).teleport(loc);
                                        target.sendMessage(CommandMessages.TPACCEPT_RECIEVER_MESSAGE(sender));
                                        target.playSound(target.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                                        sender.sendMessage(CommandMessages.TPACCEPT_SENDER_MESSAGE(target));
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


                    } else sender.sendMessage(Errors.WARNING() + CommandMessages.NO_REQUESTS_ACCEPT());
                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.NOT_A_PLAYER());
        } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
        return true;
    }
}