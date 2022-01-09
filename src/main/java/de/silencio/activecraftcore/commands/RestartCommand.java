package de.silencio.activecraftcore.commands;

import com.destroystokyo.paper.Title;
import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class RestartCommand extends ActiveCraftCommand {

    public RestartCommand() {
        super("restart-server");
    }

    private BukkitRunnable runnable;
    private int time;

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "restart");
        if (args.length == 0) {
            time = 30;
        } else {
            if (args[0].equalsIgnoreCase("cancel")) {
                cancelTimer(sender);
                return;
            }
            this.time = parseInt(args[0]);
        }
        if (runnable != null) if (!runnable.isCancelled()) runnable.cancel();

        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player target : Bukkit.getOnlinePlayers()) {
                    if (time == 0) {
                        target.kickPlayer(CommandMessages.RESTART_MESSAGE());
                        cancel();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spigot:restart");
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
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? List.of("cancel") : null;
    }

    private void cancelTimer(CommandSender sender) {

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
    }
}
