package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.ColorUtils;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class CommandStickCommand implements CommandExecutor, Listener, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            if (args.length == 0) return false;
            Player player = (Player) sender;
            if (sender.hasPermission("activecraft.commandstick")) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    // code

                    // Command Stick (Displayname)
                    // Bound Command: <command> (Lore)
                    boolean isValidCommand = false;
                    for (String registeredCommand : Bukkit.getCommandMap().getKnownCommands().keySet()) {
                        if (args[0].replace("/", "").equals(registeredCommand)) {
                            isValidCommand = true;
                            break;
                        }
                    }
                    if (isValidCommand) {
                        ItemStack commandStick = new ItemStack(Material.STICK);
                        ItemMeta commandStickMeta = commandStick.getItemMeta();
                        commandStickMeta.setDisplayName(ChatColor.GOLD + "Command Stick");
                        commandStickMeta.setUnbreakable(true);
                        commandStickMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                        commandStickMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                        StringBuilder stringBuilder = new StringBuilder();
                        for (String s : args) {
                            stringBuilder.append(s + " ");
                        }
                        List<String> lore = new ArrayList<>();
                        lore.add(ChatColor.GOLD + "Bound Command: /" + ChatColor.AQUA + stringBuilder.toString());
                        commandStickMeta.setLore(lore);
                        commandStick.setItemMeta(commandStickMeta);
                        player.getInventory().addItem(commandStick);
                    } else sender.sendMessage(Errors.WARNING() + "Invalid Command!");
                }
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onCommandStickInteract(PlayerInteractEntityEvent event) {

        if (event.getRightClicked().getType() == EntityType.PLAYER) {
            Player player = event.getPlayer();
            Entity target = event.getRightClicked();
            ItemStack eventItem = player.getInventory().getItemInHand();
            if (eventItem == null) return;
            if (eventItem.getType() != Material.STICK) return;
            ItemMeta itemMeta = eventItem.getItemMeta();
            if (!(itemMeta.getDisplayName().equals("§6Command Stick"))) return;
            if (!(itemMeta.getItemFlags().contains(ItemFlag.HIDE_ENCHANTS) && itemMeta.getItemFlags().contains(ItemFlag.HIDE_UNBREAKABLE))) return;
            if (!itemMeta.isUnbreakable()) return;
            if (itemMeta.lore() == null) return;
            for (String rawLore : itemMeta.getLore()) {
                String command;
                command = ColorUtils.removeColorAndFormat(rawLore);
                command = command.replace("Bound Command: /", "");
                command = command.replace("/", "");
                command = command.replace("@p", target.getName());

                player.performCommand(command);
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onCommandStickInteract(EntityDamageByEntityEvent event) {

        if (event.getDamager().getType() == EntityType.PLAYER && event.getEntityType() == EntityType.PLAYER) {
            Player player = (Player) event.getDamager();
            Player target = (Player) event.getEntity();
            ItemStack eventItem = player.getInventory().getItemInHand();
            if (eventItem == null) return;
            if (eventItem.getType() != Material.STICK) return;
            ItemMeta itemMeta = eventItem.getItemMeta();
            if (!(itemMeta.getDisplayName().equals("§6Command Stick"))) return;
            if (!(itemMeta.getItemFlags().contains(ItemFlag.HIDE_ENCHANTS) && itemMeta.getItemFlags().contains(ItemFlag.HIDE_UNBREAKABLE))) return;
            if (!itemMeta.isUnbreakable()) return;
            if (itemMeta.lore() == null) return;
            for (String rawLore : itemMeta.getLore()) {
                String command;
                command = ColorUtils.removeColorAndFormat(rawLore);
                command = command.replace("Bound Command: /", "");
                command = command.replace("/", "");
                command = command.replace("@p", target.getName());

                player.performCommand(command);
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onCommandStickInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        ItemStack eventItem = event.getItem();
        if (event.getAction() == Action.PHYSICAL) return;
        if (eventItem == null) return;
        if (eventItem.getType() != Material.STICK) return;
        ItemMeta itemMeta = eventItem.getItemMeta();
        if (!(itemMeta.getDisplayName().equals("§6Command Stick"))) return;
        if (!(itemMeta.getItemFlags().contains(ItemFlag.HIDE_ENCHANTS) && itemMeta.getItemFlags().contains(ItemFlag.HIDE_UNBREAKABLE)))
            return;
        if (!itemMeta.isUnbreakable()) return;
        if (itemMeta.lore() == null) return;
        for (String rawLore : itemMeta.getLore()) {
            String command;
            command = ColorUtils.removeColorAndFormat(rawLore);
            command = command.replace("Bound Command: /", "");
            command = command.replace("/", "");

            player.performCommand(command);
            event.setCancelled(true);
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        if (args.length == 1) {


            FileConfig mainConfig = new FileConfig("config.yml");

            if (mainConfig.getBoolean("hide-commands-after-plugin-name.enable")) {

                List<String> pluginNames = new ArrayList<>();
                pluginNames.add("minecraft");
                pluginNames.add("bukkit");
                pluginNames.add("spigot");
                pluginNames.add("paper");
                for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                    pluginNames.add(plugin.getName().toLowerCase());
                }

                List<String> toBeRemoved = new ArrayList<>();

                for (String cmd : Bukkit.getCommandMap().getKnownCommands().keySet()) {
                    for (String pluginName : pluginNames) {
                        if (cmd.startsWith(pluginName + ":")) {
                            toBeRemoved.add(cmd);
                        }
                    }
                }
                for (String cmd : Bukkit.getCommandMap().getKnownCommands().keySet()) {
                    if (!toBeRemoved.contains(cmd)) {
                        list.add(cmd);
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
