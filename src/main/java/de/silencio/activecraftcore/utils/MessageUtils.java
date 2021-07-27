package de.silencio.activecraftcore.utils;

import org.bukkit.ChatColor;

public class MessageUtils {

    public String replaceColor(String message) {
        String editedMessage = message.replace("&0", "§0")
                .replace("&1", "§1")
                .replace("&2", "§2")
                .replace("&3", "§3")
                .replace("&4", "§4")
                .replace("&5", "§5")
                .replace("&6", "§6")
                .replace("&7", "§7")
                .replace("&8", "§8")
                .replace("&9", "§9")
                .replace("&a", "§a")
                .replace("&b", "§b")
                .replace("&c", "§c")
                .replace("&d", "§d")
                .replace("&e", "§e")
                .replace("&f", "§f");
        return editedMessage;
    }

    public String replaceFormat(String message) {
        String editedMessage = message.replace("&0", "§0")
                .replace("&k", "§k")
                .replace("&l", "§l")
                .replace("&m", "§m")
                .replace("&n", "§n")
                .replace("&o", "§o")
                .replace("&r", "§r");
        return editedMessage;
    }

}
