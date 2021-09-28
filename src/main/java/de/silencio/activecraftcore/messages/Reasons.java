package de.silencio.activecraftcore.messages;

import de.silencio.activecraftcore.Main;
import org.bukkit.ChatColor;

public class Reasons {

    static ActiveCraftMessage acm = Main.getPlugin().getActiveCraftMessage();

    public static String WARNING() {
        return acm.getMessage(MessageType.REASON, "general-warning");
    }

    public static String HACKING() {
        return acm.getMessage(MessageType.REASON, "no-permission");
    }

    public static String BOTTING() {
        return acm.getMessage(MessageType.REASON, "invalid-player");
    }

    public static String UNAUTHORIZED_ALTERNATE_ACCOUNT() {
        return acm.getMessage(MessageType.REASON, "invalid-number");
    }

    public static String SPAM() {
        return acm.getMessage(MessageType.REASON, "invalid-arguments");
    }

    public static String ABUSIVE_LANGUAGE() {
        return acm.getMessage(MessageType.REASON, "too-many-arguments");
    }

    public static String STEALING() {
        return acm.getMessage(MessageType.REASON, "cannot-target-self");
    }

    public static String GRIEFING() {
        return acm.getMessage(MessageType.REASON, "invalid-color");
    }
}
