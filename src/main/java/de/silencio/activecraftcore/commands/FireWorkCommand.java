package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class FireWorkCommand extends ActiveCraftCommand {

    public FireWorkCommand() {
        super("firework");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "firework");
        Player player = getPlayer(sender);
        BukkitRunnable runnable = new BukkitRunnable() {
            int counter = args.length == 2 ? parseInt(args[0]) : 1;
            final int amount = args.length == 1 ? parseInt(args[0]) : 1;
            @Override
            public void run() {
                for (int i = amount; i > 0; i--) {
                    int idx = new Random().nextInt(FireworkEffect.Type.values().length);
                    FireworkEffect.Type randomtype = FireworkEffect.Type.values()[idx];

                    Color[] colors = {Color.GREEN, Color.AQUA, Color.BLUE, Color.GRAY, Color.ORANGE, Color.RED, Color.WHITE, Color.BLACK, Color.FUCHSIA, Color.LIME, Color.MAROON, Color.NAVY, Color.OLIVE, Color.PURPLE, Color.SILVER, Color.TEAL, Color.YELLOW};
                    Color randomColor = colors[new Random().nextInt(colors.length)];
                    Color randomColor2 = colors[new Random().nextInt(colors.length)];

                    Random r = new Random();
                    FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).with(randomtype).withColor(randomColor).withFade(randomColor2).trail(r.nextBoolean()).build();

                    Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
                    FireworkMeta fwm = fw.getFireworkMeta();
                    fwm.addEffect(effect);
                    fwm.setPower(r.nextInt(2) + 1);
                    fw.setFireworkMeta(fwm);
                }
                counter--;
                if (counter == 0) cancel();
            }
        };
        runnable.runTaskTimer(ActiveCraftCore.getPlugin(), 0, args.length == 2 ? parseInt(args[1]) : 20);
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}