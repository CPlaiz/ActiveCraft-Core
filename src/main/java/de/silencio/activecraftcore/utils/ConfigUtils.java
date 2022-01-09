package de.silencio.activecraftcore.utils;

import de.silencio.activecraftcore.ActiveCraftCore;
import org.bukkit.plugin.Plugin;

public class ConfigUtils {

    public static FileConfig getMainConfig() {
        return getMainConfig(ActiveCraftCore.getPlugin());
    }

    public static FileConfig getMainConfig(Plugin plugin) {
        return new FileConfig("config.yml", plugin);
    }

    public static FileConfig getHomeConfig() {
        return new FileConfig("homes.yml");
    }

}
