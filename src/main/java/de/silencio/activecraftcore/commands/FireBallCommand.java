package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FireBallCommand extends ActiveCraftCommand {

    private static final float DEFAULT_POWER = 4f;
    private static final boolean DEFAULT_FIRE = true;

    public FireBallCommand() {
        super("fireball");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "fireball");
        Player player = getPlayer(sender);
        float power;
        boolean fire;
        switch (args.length) {
            case 0 -> {
                power = DEFAULT_POWER;
                fire = DEFAULT_FIRE;
            }
            case 1 -> {
                power = parseFloat(args[0]);
                fire = DEFAULT_FIRE;
            }
            default -> {
                power = parseFloat(args[0]);
                fire = Boolean.parseBoolean(args[1]);
            }
        }
        Fireball fireball = (Fireball) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREBALL);
        fireball.setYield(power);
        fireball.setIsIncendiary(fire);
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 2) {
            list.add("true");
            list.add("false");
        }
        return list;
    }
}