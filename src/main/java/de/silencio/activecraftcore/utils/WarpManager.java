package de.silencio.activecraftcore.utils;

import de.silencio.activecraftcore.Main;
import org.bukkit.Location;

public class WarpManager {



    public static Location getWarp(String name) {
        return Main.getWarpsConfig().toFileConfiguration().getLocation(name);
    }

    public static void createWarp(String name, Location location) {
        Main.getWarpsConfig().toFileConfiguration().set(name, location);
        Main.getWarpsConfig().save();
    }

    public static void deleteWarp(String name) {
        Main.getWarpsConfig().toFileConfiguration().set(name, null);
        Main.getWarpsConfig().save();
    }
}
