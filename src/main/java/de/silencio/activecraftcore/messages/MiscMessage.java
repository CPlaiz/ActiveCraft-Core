package de.silencio.activecraftcore.messages;

import de.silencio.activecraftcore.Main;
import org.bukkit.ChatColor;

public class MiscMessage {

    static ActiveCraftMessage acm = Main.getPlugin().getActiveCraftMessage();

    public static String DEFAULT_MUTE_REMOVE() {
        return acm.getMessage(MessageType.MISC, "default-mute-remove");
    }

    public static String FILE_NOT_FOUND() {
        return acm.getMessage(MessageType.MISC, "file-not-found");
    }

    public static String SAME_IP() {
        return acm.getMessage(MessageType.MISC, "same-ip");
    }

}
