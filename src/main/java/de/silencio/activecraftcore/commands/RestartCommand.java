package de.silencio.activecraftcore.commands;

import com.destroystokyo.paper.Title;
import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RestartCommand implements CommandExecutor {

    private int time;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (sender.hasPermission("activecraft.restart")) {

                if (args.length == 0) {
                    time = 30;
                } else time = Integer.parseInt(args[0]);


                BukkitRunnable runnable = new BukkitRunnable() {

                    @Override
                    public void run() {

                        for (Player target : Bukkit.getOnlinePlayers()) {

                            if (time == 1) {
                                Bukkit.shutdown();
                                return;
                            }

                            if(time == 1) {
                                target.kickPlayer("The server is restarting.");
                            }

                            if(time == 0) {
                                cancel();
                            }

                            time--;

                            target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 0.5f);
                            try {
                                Thread.sleep(250);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 0.5f);
                        }
                    }
                };
                runnable.runTaskTimer(Main.getPlugin(), 0, 20);

                BukkitRunnable titletimer = new BukkitRunnable() {

                    @Override
                    public void run() {

                        for (Player target : Bukkit.getOnlinePlayers()) {
                            Title title = new Title(ChatColor.RED + "Server Restart in " + time);
                            target.sendTitle(title);
                        }
                    }
                };
                titletimer.runTaskTimer(Main.getPlugin(), 0, 20);
            } else sender.sendMessage(Errors.NO_PERMISSION);
        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
