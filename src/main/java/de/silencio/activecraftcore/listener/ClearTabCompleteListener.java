package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

public class ClearTabCompleteListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerClickTab(PlayerCommandSendEvent e) {

        FileConfig mainConfig = new FileConfig("config.yml");
        List<String> exceptionList = mainConfig.getStringList("hide-commands-after-plugin-name.except");

        if (!mainConfig.getBoolean("hide-commands-after-plugin-name.enable")) return;

        List<String> pluginNames = new ArrayList<>();
        pluginNames.add("minecraft");
        pluginNames.add("bukkit");
        pluginNames.add("spigot");
        pluginNames.add("paper");
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            pluginNames.add(plugin.getName().toLowerCase());
        }

        List<String> toBeRemoved = new ArrayList<>();

        for (String command : e.getCommands()) {
            for (String pluginName : pluginNames) {
                if (command.startsWith(pluginName + ":")) {
                    if (!(exceptionList.contains(command) || exceptionList.contains(pluginName.replace(":", "")))) {
                       toBeRemoved.add(command);
                    }
                }
            }
        }

        for (String s : toBeRemoved) {
            e.getCommands().remove(s);
        }

        if (!e.getPlayer().hasPermission("activecraft.showvanilla")) {
            e.getCommands().remove("pl");
            e.getCommands().remove("plugins");
            e.getCommands().remove("?");
            e.getCommands().remove("icanhasbukkit");
            e.getCommands().remove("list");
            e.getCommands().remove("me");
            e.getCommands().remove("teammsg");
            e.getCommands().remove("tell");
            e.getCommands().remove("tm");
            e.getCommands().remove("trigger");
            e.getCommands().remove("ver");
            e.getCommands().remove("version");
            e.getCommands().remove("w");
            e.getCommands().remove("about");
        }
    }
}