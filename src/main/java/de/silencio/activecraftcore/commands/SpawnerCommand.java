package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.ArrayList;
import java.util.List;

public class SpawnerCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1 ) {
            String mobName = args[0];
            EntityType value = null;
            try {
                value = EntityType.valueOf(mobName);
            } catch (IllegalArgumentException exp) {
                sender.sendMessage(Errors.WARNING + "Invalid Entity!");
                return false;
            }
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (sender.hasPermission("activecraft.spawner.self")) {

                    ItemStack spawner = new ItemStack(Material.SPAWNER);
                    BlockStateMeta spawnermeta = (BlockStateMeta) spawner.getItemMeta();
                    CreatureSpawner spawnerblock = (CreatureSpawner) spawnermeta.getBlockState();

                    //spawnerblock.setSpawnedType(EntityType.valueOf(mobName));
                    //spawnerblock.setDelay(delay);
                    //spawnerblock.setSpawnCount(spawncount);
                    //spawnerblock.setMaxNearbyEntities(maxnearbyentities);
                    //spawnerblock.setSpawnRange(range);
                    player.sendMessage(ChatColor.GOLD + "Gave yourself a " + ChatColor.AQUA + mobName.toLowerCase() + ChatColor.GOLD + " spawner.");

                    spawnerblock.setSpawnedType(EntityType.valueOf(mobName));
                    spawnermeta.setDisplayName(ChatColor.AQUA + mobName.toLowerCase().replace("_", " "));
                    spawnermeta.setBlockState(spawnerblock);
                    spawner.setItemMeta(spawnermeta);
                    player.getInventory().addItem(spawner);

                } else sender.sendMessage(Errors.NO_PERMISSION);
            } else sender.sendMessage(Errors.NOT_A_PLAYER);
        } else {
            if (args.length == 2) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER);
                    return false;
                }
                String mobName = args[1];
                EntityType value = null;
                try {
                    value = EntityType.valueOf(mobName);
                } catch (IllegalArgumentException ignored) {
                }
                if (value == null || value.name().equals("UNKNOWN")) {
                    sender.sendMessage(Errors.WARNING + "Invalid Entity!");
                    return false;
                }

                if (sender.hasPermission("activecraft.spawner.others")) {

                    Player target = Bukkit.getPlayer(args[0]);
                    if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                        if (!sender.hasPermission("activecraft.spawner.self")) {
                            sender.sendMessage(Errors.CANNOT_TARGET_SELF);
                            return false;
                        }
                    }


                    ItemStack spawner = new ItemStack(Material.SPAWNER);
                    BlockStateMeta spawnermeta = (BlockStateMeta) spawner.getItemMeta();
                    CreatureSpawner spawnerblock = (CreatureSpawner) spawnermeta.getBlockState();

                    spawnerblock.setSpawnedType(EntityType.valueOf(mobName));
                    spawnermeta.setDisplayName(ChatColor.AQUA + mobName.toLowerCase().replace("_", " "));
                    sender.sendMessage(ChatColor.GOLD + "Gave " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " a " + ChatColor.AQUA + mobName.toLowerCase() + ChatColor.GOLD + " spawner.");
                    target.sendMessage(ChatColor.AQUA + sender.getName() + ChatColor.GOLD + " gave you a " + ChatColor.AQUA + mobName.toLowerCase() + ChatColor.GOLD + " spawner.");

                    spawnermeta.setBlockState(spawnerblock);
                    spawner.setItemMeta(spawnermeta);
                    target.getInventory().addItem(spawner);


                } else sender.sendMessage(Errors.NO_PERMISSION);
            } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Player p = (Player) sender;

        if (args.length == 0) return list;
        if (args.length == 1) {
            for (EntityType entityType : EntityType.values()) {
                list.add(entityType.name());
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                list.add(player.getName());
            }
        }
        if (args.length == 2) {
            if (Bukkit.getPlayer(args[0]) != null) {
                for (EntityType entityType : EntityType.values()) {
                    if (!entityType.name().equals("UNKNOWN")) {
                        list.add(entityType.name());
                    }
                }
            }
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
