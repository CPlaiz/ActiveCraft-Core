package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Random;

public class FireWorkCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(sender.hasPermission("activecraft.firework")) {

                if(args.length == 1) {
                    for (int i = Integer.parseInt(args[0]); i > 0; i--) {

                        Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
                        FireworkMeta fwm = fw.getFireworkMeta();

                        FireworkEffect.Type[] explosiontype = {FireworkEffect.Type.BALL, FireworkEffect.Type.BALL_LARGE, FireworkEffect.Type.BURST, FireworkEffect.Type.CREEPER, FireworkEffect.Type.STAR};
                        int idxtype = new Random().nextInt(explosiontype.length);
                        FireworkEffect.Type randomtype = explosiontype[idxtype];

                        Color[] colors = {Color.GREEN, Color.AQUA, Color.BLUE, Color.GRAY, Color.ORANGE, Color.RED, Color.WHITE, Color.BLACK, Color.FUCHSIA, Color.LIME, Color.MAROON, Color.NAVY, Color.OLIVE, Color.PURPLE, Color.SILVER, Color.TEAL, Color.YELLOW};
                        int idx = new Random().nextInt(colors.length);
                        Color randomColor = colors[idx];

                        Color[] colors2 = {Color.GREEN, Color.AQUA, Color.BLUE, Color.GRAY, Color.ORANGE, Color.RED, Color.WHITE, Color.BLACK, Color.FUCHSIA, Color.LIME, Color.MAROON, Color.NAVY, Color.OLIVE, Color.PURPLE, Color.SILVER, Color.TEAL, Color.YELLOW};
                        int idx2 = new Random().nextInt(colors2.length);
                        Color randomColor2 = colors[idx2];

                        Random r = new Random();
                        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).with(randomtype).withColor(randomColor).withFade(randomColor2).trail(r.nextBoolean()).build();
                        fwm.addEffect(effect);

                        int rp = r.nextInt(2) + 1;
                        fwm.setPower(rp);

                        fw.setFireworkMeta(fwm);

                    }
                } else if (args.length == 2) {
                    for (int i = Integer.parseInt(args[0]); i > 0; i--) {

                        Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
                        FireworkMeta fwm = fw.getFireworkMeta();

                        FireworkEffect.Type[] explosiontype = {FireworkEffect.Type.BALL, FireworkEffect.Type.BALL_LARGE, FireworkEffect.Type.BURST, FireworkEffect.Type.CREEPER, FireworkEffect.Type.STAR};
                        int idxtype = new Random().nextInt(explosiontype.length);
                        FireworkEffect.Type randomtype = explosiontype[idxtype];

                        Color[] colors = {Color.GREEN, Color.AQUA, Color.BLUE, Color.GRAY, Color.ORANGE, Color.RED, Color.WHITE, Color.BLACK, Color.FUCHSIA, Color.LIME, Color.MAROON, Color.NAVY, Color.OLIVE, Color.PURPLE, Color.SILVER, Color.TEAL, Color.YELLOW};
                        int idx = new Random().nextInt(colors.length);
                        Color randomColor = colors[idx];

                        Color[] colors2 = {Color.GREEN, Color.AQUA, Color.BLUE, Color.GRAY, Color.ORANGE, Color.RED, Color.WHITE, Color.BLACK, Color.FUCHSIA, Color.LIME, Color.MAROON, Color.NAVY, Color.OLIVE, Color.PURPLE, Color.SILVER, Color.TEAL, Color.YELLOW};
                        int idx2 = new Random().nextInt(colors2.length);
                        Color randomColor2 = colors[idx2];

                        Random r = new Random();
                        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).with(randomtype).withColor(randomColor).withFade(randomColor2).trail(r.nextBoolean()).build();
                        fwm.addEffect(effect);

                        int rp = r.nextInt(2) + 1;
                        fwm.setPower(rp);

                        fw.setFireworkMeta(fwm);

                        try {
                            wait(Integer.parseInt(args[1]));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }

                if(args.length == 0) {
                    Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
                    FireworkMeta fwm = fw.getFireworkMeta();

                    FireworkEffect.Type[] explosiontype = {FireworkEffect.Type.BALL, FireworkEffect.Type.BALL_LARGE, FireworkEffect.Type.BURST, FireworkEffect.Type.CREEPER, FireworkEffect.Type.STAR};
                    int idxtype = new Random().nextInt(explosiontype.length);
                    FireworkEffect.Type randomtype = explosiontype[idxtype];

                    Color[] colors = {Color.GREEN, Color.AQUA, Color.BLUE, Color.GRAY, Color.ORANGE, Color.RED, Color.WHITE, Color.BLACK, Color.FUCHSIA, Color.LIME, Color.MAROON, Color.NAVY, Color.OLIVE, Color.PURPLE, Color.SILVER, Color.TEAL, Color.YELLOW};
                    int idx = new Random().nextInt(colors.length);
                    Color randomColor = colors[idx];

                    Color[] colors2 = {Color.GREEN, Color.AQUA, Color.BLUE, Color.GRAY, Color.ORANGE, Color.RED, Color.WHITE, Color.BLACK, Color.FUCHSIA, Color.LIME, Color.MAROON, Color.NAVY, Color.OLIVE, Color.PURPLE, Color.SILVER, Color.TEAL, Color.YELLOW};
                    int idx2 = new Random().nextInt(colors2.length);
                    Color randomColor2 = colors[idx2];

                    Random r = new Random();
                    FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).with(randomtype).withColor(randomColor).withFade(randomColor2).trail(r.nextBoolean()).build();
                    fwm.addEffect(effect);

                    int rp = r.nextInt(2) + 1;
                    fwm.setPower(rp);

                    fw.setFireworkMeta(fwm);
                }
            } else sender.sendMessage(Errors.NO_PERMISSION);
        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
