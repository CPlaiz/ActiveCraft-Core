package de.silencio.activecraftcore.commands;

import com.destroystokyo.paper.Title;
import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class RestartCommand implements CommandExecutor, TabCompleter {

    private int time = 30;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (sender.hasPermission("activecraft.restart")) {

                if (args.length == 0) {
                    time = 30;
                } else {
                    Integer num = null;
                    try {
                        num = Integer.valueOf(args[0]);
                    } catch (NumberFormatException ignored) {
                    }
                    if (num == null) {
                        sender.sendMessage(Errors.INVALID_NUMBER());
                        return false;
                    }
                    this.time = Integer.parseInt(args[0]);
                }


                BukkitRunnable runnable = new BukkitRunnable() {

                    @Override
                    public void run() {

                        for (Player target : Bukkit.getOnlinePlayers()) {

                            if (time == 1) {
                                target.kickPlayer(CommandMessages.RESTART_MESSAGE());
                                Bukkit.shutdown();
                                return;
                            }

                            if(time == 0) {
                                cancel();
                            }

                            target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 0.5f);
                            try {
                                Thread.sleep(250);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 0.5f);
                        }
                        time--;
                    }
                };
                runnable.runTaskTimer(Main.getPlugin(), 0, 20);

                BukkitRunnable titletimer = new BukkitRunnable() {

                    @Override
                    public void run() {

                        for (Player target : Bukkit.getOnlinePlayers()) {
                            Title title = new Title(CommandMessages.RESTART_TITLE(time + ""));
                            target.sendTitle(title);
                        }
                    }
                };
                titletimer.runTaskTimer(Main.getPlugin(), 0, 20);
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        ArrayList<String> completerList = new ArrayList<>();
        return completerList;
    }

}
