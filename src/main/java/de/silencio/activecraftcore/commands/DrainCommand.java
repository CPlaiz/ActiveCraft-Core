package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.utils.ComparisonType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DrainCommand extends ActiveCraftCommand {

    public DrainCommand() {
        super("drain");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "drain");
        Player player = getPlayer(sender);
        checkArgsLength(args, ComparisonType.NOT_EQUAL, 0);
        int drainedBlocks;
        switch (args.length) {
            case 1 -> {
                drainedBlocks = drain(player.getLocation().getBlock(), parseInt(args[0]), false, true);
                sendMessage(sender, ChatColor.GOLD + CommandMessages.DRAIN_COMPLETE(drainedBlocks));
            }
            case 2 -> {
                drainedBlocks = drain(player.getLocation().getBlock(), parseInt(args[0]), Boolean.parseBoolean(args[1]), true);
                sendMessage(sender, ChatColor.GOLD + CommandMessages.DRAIN_COMPLETE(drainedBlocks));
            }
            default -> {
                drainedBlocks = drain(player.getLocation().getBlock(), parseInt(args[0]), Boolean.parseBoolean(args[1]), Boolean.parseBoolean(args[2]));
                sendMessage(sender, ChatColor.GOLD + CommandMessages.DRAIN_COMPLETE(drainedBlocks));
            }
        }
    }


    private int drain(Block startBlock, int range, boolean removeWaterlogged, boolean applyPhysics) {
        List<Material> types = List.of(new Material[]{Material.LAVA, Material.WATER, Material.SEAGRASS, Material.TALL_SEAGRASS, Material.KELP_PLANT, Material.KELP, Material.BUBBLE_COLUMN});
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
                Block[] neighbourBlocks = new Block[]{xplus1, xminus1, yplus1, yminus1, zplus1, zminus1};
                for (Block neighbourBlock : neighbourBlocks)
                    if ((types.contains(neighbourBlock.getType())) && !toBeAdded.contains(neighbourBlock))
                        toBeAdded.add(neighbourBlock);
                if (!removeWaterlogged) continue;
                for (Block neighbourBlock : neighbourBlocks)
                    if (neighbourBlock.getBlockData() instanceof Waterlogged)
                        if (((Waterlogged) neighbourBlock.getBlockData()).isWaterlogged()) {
                            Waterlogged wl = (Waterlogged) neighbourBlock.getBlockData();
                            wl.setWaterlogged(false);
                            neighbourBlock.setBlockData(wl, applyPhysics);
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
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 2 || args.length == 3) {
            list.add("true");
            list.add("false");
        }
        return list;
    }

}