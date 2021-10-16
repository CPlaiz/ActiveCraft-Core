package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class SudoCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("activecraft.sudo")) {

            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(Errors.INVALID_PLAYER());
                return false;
            }
            Player target = Bukkit.getPlayer(args[0]);

            boolean isValidCommand = false;
            for (String registeredCommand : Bukkit.getCommandMap().getKnownCommands().keySet()) {
                if (args[1].replace("/", "").equals(registeredCommand)) {
                    isValidCommand = true;
                    break;
                }
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                stringBuilder.append(args[i] + " ");
            }

            if (isValidCommand) {
                target.performCommand(String.valueOf(stringBuilder));
            } else sender.sendMessage(Errors.WARNING() + "Invalid Command!");

        } else sender.sendMessage(Errors.NO_PERMISSION());
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        if (args.length == 1) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                list.add(player.getName());
            }
        }
        else if (args.length == 2) {


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
