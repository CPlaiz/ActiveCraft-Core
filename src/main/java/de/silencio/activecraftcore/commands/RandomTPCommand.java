package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class RandomTPCommand extends ActiveCraftCommand {

    public RandomTPCommand() {
        super("randomtp");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        Player player = getPlayer(sender);
        if (args.length == 0) {
            checkPermission(sender, "randomtp.self");
            Location tpLoc = randomLocation(player, (int) player.getWorld().getWorldBorder().getSize()/2);
            int save = 69;
            for (int i = 0; i < save; i++) {
                if (tpLoc.getWorld().getBlockAt(tpLoc.getBlockX(), tpLoc.getWorld().getHighestBlockYAt(tpLoc.getBlockX(), tpLoc.getBlockZ()), tpLoc.getBlockZ()).getType() != Material.LAVA) break;
                tpLoc = randomLocation(player, (int) player.getWorld().getWorldBorder().getSize()/2);
            }
            player.teleport(tpLoc);
        } else if (args.length == 1) {
            checkPermission(sender, "randomtp.others");
            Player target = getPlayer(args[0]);
            checkTargetSelf(sender, target, "randomtp.self");
            Location tpLoc = randomLocation(target, (int) target.getWorld().getWorldBorder().getSize()/2);
            int save = 69;
            for (int i = 0; i < save; i++) {
                if (tpLoc.getWorld().getBlockAt(tpLoc.getBlockX(), tpLoc.getWorld().getHighestBlockYAt(tpLoc.getBlockX(), tpLoc.getBlockZ()), tpLoc.getBlockZ()).getType() != Material.LAVA) break;
                tpLoc = randomLocation(player, (int) target.getWorld().getWorldBorder().getSize()/2);
            }
            target.teleport(tpLoc);
        } else {
            int num = parseInt(args[1]);
            if (parseInt(args[1]) > player.getWorld().getWorldBorder().getSize()/2) num = (int) player.getWorld().getWorldBorder().getSize()/2;
            Location tpLoc = randomLocation(player, num);
            int save = 69;
            for (int i = 0; i < save; i++) {
                if (tpLoc.getWorld().getBlockAt(tpLoc.getBlockX(), tpLoc.getWorld().getHighestBlockYAt(tpLoc.getBlockX(), tpLoc.getBlockZ()), tpLoc.getBlockZ()).getType() != Material.LAVA) break;
                tpLoc = randomLocation(player, parseInt(args[1]));
            }
            player.teleport(tpLoc);
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }

    private Location randomLocation(Player player, int range) {
        Random random = new Random();
        int randomNumX = random.nextInt(range);
        int randomNumZ = random.nextInt(range);

        int isNegative = random.nextInt(4);
        switch (isNegative) {
            case 1 -> randomNumX *= -1;
            case 2 -> randomNumZ *= -1;
            case 3 -> {
                randomNumX *= -1;
                randomNumZ *= -1;
            }
        }
        return new Location(player.getWorld(), randomNumX, player.getWorld().getHighestBlockYAt(randomNumX, randomNumZ) + 1, randomNumZ,
                player.getLocation().getYaw(), player.getLocation().getPitch());
    }
}