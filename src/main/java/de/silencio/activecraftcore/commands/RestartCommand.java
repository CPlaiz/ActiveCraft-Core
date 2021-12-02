package de.silencio.activecraftcore.commands;

import com.destroystokyo.paper.Title;
import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class RestartCommand implements CommandExecutor, TabCompleter {

    private BukkitRunnable runnable;
    private int time = 30;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
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


                    runnable = new BukkitRunnable() {

                        @Override
                        public void run() {

                            for (Player target : Bukkit.getOnlinePlayers()) {

                                if (time == 1) {
                                    target.kickPlayer(CommandMessages.RESTART_MESSAGE());
                                    Bukkit.shutdown();
                                    return;
                                }

                                if (time == 0) {
                                    cancel();
                                }

                                target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 0.5f);
                                Title title = new Title(CommandMessages.RESTART_TITLE(time + ""));
                                target.sendTitle(title);
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
                    runnable.runTaskTimer(ActiveCraftCore.getPlugin(), 0, 20);
                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else if (args[0].equalsIgnoreCase("cancel")) {
                if (runnable != null) {
                    if (!runnable.isCancelled()) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            Title title = new Title(CommandMessages.RESTART_TITLE(ChatColor.RED + "--"));
                            p.sendTitle(title);
                        }
                        runnable.cancel();
                        sender.sendMessage(CommandMessages.RESTART_CANCEL());
                    }
                }
            } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Player p = (Player) sender;

        if (args.length == 0) return list;
        if (args.length == 1) {
            list.add("cancel");
        }

        ArrayList<String> completerList = new ArrayList<>();
        String currentarg = args[args.length - 1].toLowerCase();
        for (String s : list) {
            String s1 = s.toLowerCase();
            if (s1.startsWith(currentarg)) {
                completerList.add(s);
            }
        }
        return completerList;
    }

}
