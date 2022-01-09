package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TpCommand extends ActiveCraftCommand {

    public TpCommand() {
        super("tp");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        if (args[0].equalsIgnoreCase("@s")) {
            String[] newArray = new String[args.length-1];
            System.arraycopy(args, 1, newArray, 0, args.length - 1);
            args = newArray;
        }

        switch (args.length) {
            case 0 -> sendMessage(sender, Errors.INVALID_ARGUMENTS());
            case 1 -> {
                checkPermission(sender, "tp.self");
                Player player = getPlayer(sender);
                Player target = getPlayer(args[0]);
                checkTargetSelf(sender, target);
                player.teleport(target.getLocation());
                sendMessage(sender, CommandMessages.TELEPORT_TO_PLAYER(target));
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
            }
            case 2 -> {
                checkPermission(sender, "tp.others");
                Player target1 = getPlayer(args[0]);
                Player target2 = getPlayer(args[1]);
                checkTargetSelf(target1, target2);
                target1.teleport(target2.getLocation());
                sendMessage(sender, CommandMessages.TELEPORT_PLAYER_TO_PLAYER(target1, target2));
                target1.playSound(target1.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
            }
            case 3 -> {
                checkPermission(sender, "tp.self");
                Player player = getPlayer(sender);
                double finalNumX;
                double finalNumY;
                double finalNumZ;
                if (args[0].startsWith("~")) {
                    if (args[0].length() == 1) {
                        finalNumX = player.getLocation().getX();
                    } else finalNumX = player.getLocation().getX() + parseDouble(args[0]);
                } else finalNumX = parseDouble(args[0]);
                if (args[1].startsWith("~")) {
                    if (args[1].length() == 1) {
                        finalNumY = player.getLocation().getY();
                    } else finalNumY = player.getLocation().getY() + parseDouble(args[1]);
                } else finalNumY = parseDouble(args[1]);
                if (args[2].startsWith("~")) {
                    if (args[2].length() == 1) {
                        finalNumZ = player.getLocation().getZ();
                    } else finalNumZ = player.getLocation().getZ() + parseDouble(args[2]);
                } else finalNumZ = parseDouble(args[2]);
                player.teleport(new Location(player.getWorld(), finalNumX, finalNumY, finalNumZ));
                sendMessage(sender, CommandMessages.TELEPORT_TO_COORDS(args[0] + ", " + args[1] + ", " + args[2]));
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
            }
            case 4 -> {
                checkPermission(sender, "tp.others");
                Player target = getPlayer(args[0]);
                checkTargetSelf(sender, target, "tp.self");
                double finalNumX;
                double finalNumY;
                double finalNumZ;
                if (args[0].startsWith("~")) {
                    if (args[0].length() == 1) {
                        finalNumX = target.getLocation().getX();
                    } else finalNumX = target.getLocation().getX() + parseDouble(args[0]);
                } else finalNumX = parseDouble(args[0]);
                if (args[1].startsWith("~")) {
                    if (args[1].length() == 1) {
                        finalNumY = target.getLocation().getY();
                    } else finalNumY = target.getLocation().getY() + parseDouble(args[1]);
                } else finalNumY = parseDouble(args[1]);
                if (args[2].startsWith("~")) {
                    if (args[2].length() == 1) {
                        finalNumZ = target.getLocation().getZ();
                    } else finalNumZ = target.getLocation().getZ() + parseDouble(args[2]);
                } else finalNumZ = parseDouble(args[2]);
                target.teleport(new Location(target.getWorld(), finalNumX, finalNumY, finalNumZ));
                sender.sendMessage(CommandMessages.TELEPORT_PLAYER_TO_COORDS(target, finalNumX + finalNumY + finalNumZ + ""));
                target.playSound(target.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Player p = (Player) sender;

        if (args[0].equalsIgnoreCase("@s")) {
            String[] newArray = new String[args.length-1];
            System.arraycopy(args, 1, newArray, 0, args.length - 1);
            args = newArray;
        }

        if (args.length == 0) return null;

        if (args.length == 1) {
            if (p.getTargetBlock(10) != null && !(p.getTargetBlock(10).getBlockData().getMaterial().equals(Material.AIR))) {
                int targetblockX = p.getTargetBlock(10).getLocation().getBlockX();
                list.add(targetblockX + "");
            } else list.add("~");
            list.addAll(getBukkitPlayernames());
        }
        if (Bukkit.getPlayer(args[0]) == null) {
            if (args.length == 2) {
                if (p.getTargetBlock(10) != null && !(p.getTargetBlock(10).getBlockData().getMaterial().equals(Material.AIR))) {
                    int targetblockY = p.getTargetBlock(10).getLocation().getBlockY();
                    list.add(targetblockY + "");
                } else list.add("~");
            } else if (args.length == 3) {
                if (p.getTargetBlock(10) != null && !(p.getTargetBlock(10).getBlockData().getMaterial().equals(Material.AIR))) {
                    int targetblockZ = p.getTargetBlock(10).getLocation().getBlockZ();
                    list.add(targetblockZ + "");
                } else list.add("~");
            }
        } else if (sender.hasPermission("tp.others")) {
            if (Bukkit.getPlayer(args[0]) != null) {
                if (args.length == 2) {
                    if (p.getTargetBlock(10) != null && !(p.getTargetBlock(10).getBlockData().getMaterial().equals(Material.AIR))) {
                        int targetblockX = p.getTargetBlock(10).getLocation().getBlockX();
                        list.add(targetblockX + "");
                    } else list.add("~");
                    list.addAll(getBukkitPlayernames());
                } else if (args.length == 3) {
                    if (p.getTargetBlock(10) != null && !(p.getTargetBlock(10).getBlockData().getMaterial().equals(Material.AIR))) {
                        int targetblockY = p.getTargetBlock(10).getLocation().getBlockY();
                        list.add(targetblockY + "");
                    } else list.add("~");
                } else if (args.length == 4) {
                    if (p.getTargetBlock(10) != null && !(p.getTargetBlock(10).getBlockData().getMaterial().equals(Material.AIR))) {
                        int targetblockZ = p.getTargetBlock(10).getLocation().getBlockZ();
                        list.add(targetblockZ + "");
                    } else list.add("~");
                }
            }
        }
        return list;
    }
}