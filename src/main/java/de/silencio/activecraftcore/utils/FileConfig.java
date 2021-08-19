package de.silencio.activecraftcore.utils;

import de.silencio.activecraftcore.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileConfig extends YamlConfiguration {

    private String path;

    public FileConfig(String folder, String filename) {
        this.path = "plugins" + File.separator + folder + File.separator + filename;

        try {
            load(this.path);
        } catch (InvalidConfigurationException | IOException ex) {
            Main.getPlugin().getLogger().severe("File config " + filename + " doesn't exist. Creating a new one.");
        }
    }

    public FileConfig(String filename) {
        this(Main.getPlugin().getDataFolder().getName(), filename);
    }

    public void saveConfig() {
        try {
            save(this.path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
