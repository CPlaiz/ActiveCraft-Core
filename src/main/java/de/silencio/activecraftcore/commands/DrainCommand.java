package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DrainCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!sender.hasPermission("activecraft.drain")) {
                sender.sendMessage(Errors.NO_PERMISSION());
                return false;
            }
            if (args.length == 1) {
                Integer num = null;
                try {
                    num = Integer.valueOf(args[0]);
                } catch (NumberFormatException ignored) {
                }
                drain(player.getLocation().getBlock(), num, false, true);
                sender.sendMessage(ChatColor.GOLD + "Drained " + num + " blocks.");
            } else if (args.length == 2) {
                Integer num = null;
                try {
                    num = Integer.valueOf(args[0]);
                } catch (NumberFormatException ignored) {
                }
                drain(player.getLocation().getBlock(), num, Boolean.parseBoolean(args[1]), true);
                sender.sendMessage(ChatColor.GOLD + "Drained " + num + " blocks.");
            } else if (args.length == 3) {
                Integer num = null;
                try {
                    num = Integer.valueOf(args[0]);
                } catch (NumberFormatException ignored) {
                }
                int drainedBlocks = drain(player.getLocation().getBlock(), num, Boolean.parseBoolean(args[1]), Boolean.parseBoolean(args[2]));
                sender.sendMessage(CommandMessages.DRAIN_COMPLETE(drainedBlocks));
            } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }

    private int drain(Block startBlock, int range, boolean destroyWaterlogged, boolean applyPhysics) {
        List<Material> types = List.of(new Material[]{Material.LAVA, Material.WATER, Material.SEAGRASS, Material.TALL_SEAGRASS, Material.KELP_PLANT, Material.KELP});
        World world = startBlock.getWorld();
        List<Block> blocks = new ArrayList<>();
        List<Block> toBeAdded = new ArrayList<>();
        int totalDrainedBlocks = 0;
        if (!types.contains(startBlock.getType())) return 0;
        blocks.add(startBlock);
        for (int i = 0; i < range; i++) {
            for (Block block : blocks) {
                Block xplus1 = world.getBlockAt(block.getX() + 1, block.getY(), block.getZ());
                Block xminus1 = world.getBlockAt(block.getX() - 1, block.getY(), block.getZ());
                Block yplus1 = world.getBlockAt(block.getX(), block.getY() + 1, block.getZ());
                Block yminus1 = world.getBlockAt(block.getX(), block.getY() - 1, block.getZ());
                Block zplus1 = world.getBlockAt(block.getX(), block.getY(), block.getZ() + 1);
                Block zminus1 = world.getBlockAt(block.getX(), block.getY(), block.getZ() - 1);
                if ((types.contains(xplus1.getType())) && !toBeAdded.contains(xplus1))
                    toBeAdded.add(xplus1);
                if ((types.contains(xminus1.getType())) && !toBeAdded.contains(xminus1))
                    toBeAdded.add(xminus1);
                if ((types.contains(yplus1.getType())) && !toBeAdded.contains(yplus1))
                    toBeAdded.add(yplus1);
                if ((types.contains(yminus1.getType())) && !toBeAdded.contains(yminus1))
                    toBeAdded.add(yminus1);
                if ((types.contains(zplus1.getType())) && !toBeAdded.contains(zplus1))
                    toBeAdded.add(zplus1);
                if ((types.contains(zminus1.getType())) && !toBeAdded.contains(zminus1))
                    toBeAdded.add(zminus1);
                if (!destroyWaterlogged) continue;
                if (xplus1.getBlockData() instanceof Waterlogged)
                    if (((Waterlogged) xplus1.getBlockData()).isWaterlogged()) {
                        Waterlogged wl = (Waterlogged) xplus1.getBlockData();
                        wl.setWaterlogged(false);
                        xplus1.setBlockData(wl, applyPhysics);
                        totalDrainedBlocks++;
                    }
                if (xminus1.getBlockData() instanceof Waterlogged)
                    if (((Waterlogged) xminus1.getBlockData()).isWaterlogged()) {
                        Waterlogged wl = (Waterlogged) xminus1.getBlockData();
                        wl.setWaterlogged(false);
                        xminus1.setBlockData(wl, applyPhysics);
                        totalDrainedBlocks++;
                    }
                if (yplus1.getBlockData() instanceof Waterlogged)
                    if (((Waterlogged) yplus1.getBlockData()).isWaterlogged()) {
                        Waterlogged wl = (Waterlogged) yplus1.getBlockData();
                        wl.setWaterlogged(false);
                        yplus1.setBlockData(wl, applyPhysics);
                        totalDrainedBlocks++;
                    }
                if (yminus1.getBlockData() instanceof Waterlogged)
                    if (((Waterlogged) yminus1.getBlockData()).isWaterlogged()) {
                        Waterlogged wl = (Waterlogged) yminus1.getBlockData();
                        wl.setWaterlogged(false);
                        yminus1.setBlockData(wl, applyPhysics);
                        totalDrainedBlocks++;
                    }
                if (zplus1.getBlockData() instanceof Waterlogged)
                    if (((Waterlogged) zplus1.getBlockData()).isWaterlogged()) {
                        Waterlogged wl = (Waterlogged) zplus1.getBlockData();
                        wl.setWaterlogged(false);
                        zplus1.setBlockData(wl, applyPhysics);
                        totalDrainedBlocks++;
                    }
                if (zminus1.getBlockData() instanceof Waterlogged)
                    if (((Waterlogged) zminus1.getBlockData()).isWaterlogged()) {
                        Waterlogged wl = (Waterlogged) zminus1.getBlockData();
                        wl.setWaterlogged(false);
                        zminus1.setBlockData(wl, applyPhysics);
                        totalDrainedBlocks++;
                    }
            }
            for (Block block : blocks) {
                block.setType(Material.AIR, applyPhysics);
                totalDrainedBlocks++;
            }
            blocks.clear();
            blocks.addAll(toBeAdded);
            toBeAdded.clear();
        }
        return totalDrainedBlocks;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();

        Player p = (Player) sender;

        if (args.length == 0) return list;
        if (args.length == 2) {
            list.add("true");
            list.add("false");
        }
        if (args.length == 3) {
            list.add("true");
            list.add("false");
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