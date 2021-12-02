package de.silencio.activecraftcore.messages;

import de.silencio.activecraftcore.ActiveCraftCore;
import org.bukkit.ChatColor;

public class DialogueMessages {

    static ActiveCraftMessage acm = ActiveCraftCore.getPlugin().getActiveCraftMessage();

    public static String WARNING() {
        return acm.getMessage(MessageType.ERROR, "general-warning");
    }

    public static String HEADER() {
        return ChatColor.RED + WARNING() + " " + ChatColor.GRAY + acm.getMessage(MessageType.ERROR, "no-permission");
    }

    public static String DEFAULT_COMPLETE_MESSAGE() {
        return ChatColor.RED + WARNING() + " " + ChatColor.GRAY + acm.getMessage(MessageType.ERROR, "invalid-player");
    }

    public static String DEFAULT_CANCELLED_MESSAGE() {
        return ChatColor.RED + WARNING() + " " + ChatColor.GRAY + acm.getMessage(MessageType.ERROR, "invalid-player");
    }

    public static String DEFAULT_INVALID() {
        return ChatColor.RED + WARNING() + " " + ChatColor.GRAY + acm.getMessage(MessageType.ERROR, "invalid-player");
    }
}
