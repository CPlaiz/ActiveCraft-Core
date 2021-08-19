package de.silencio.activecraftcore.utils;

public class MessageUtils {

    public static String replaceColor(String message) {
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

    public static String replaceFormat(String message) {
        String editedMessage = message.replace("&0", "§0")
                .replace("&k", "§k")
                .replace("&l", "§l")
                .replace("&m", "§m")
                .replace("&n", "§n")
                .replace("&o", "§o")
                .replace("&r", "§r");
        return editedMessage;
    }

    public static String removeColorAndFormat(String string) {
        String edited = string.replace("§0", "")
                .replace("§1", "")
                .replace("§2", "")
                .replace("§3", "")
                .replace("§4", "")
                .replace("§5", "")
                .replace("§6", "")
                .replace("§7", "")
                .replace("§8", "")
                .replace("§9", "")
                .replace("§a", "")
                .replace("§b", "")
                .replace("§c", "")
                .replace("§d", "")
                .replace("§e", "")
                .replace("§f", "")
                .replace("§k", "")
                .replace("§l", "")
                .replace("§m", "")
                .replace("§n", "")
                .replace("§o", "")
                .replace("§r", "");

        return edited;
    }

}
