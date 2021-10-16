package de.silencio.activecraftcore.messages;

import de.silencio.activecraftcore.Main;
import org.bukkit.ChatColor;

public class Reasons {

    static ActiveCraftMessage acm = Main.getPlugin().getActiveCraftMessage();

    public static String HACKING() {
        return acm.getMessage(MessageType.REASON, "hacking");
    }

    public static String BOTTING() {
        return acm.getMessage(MessageType.REASON, "botting");
    }

    public static String UNAUTHORIZED_ALTERNATE_ACCOUNT() {
        return acm.getMessage(MessageType.REASON, "unauthorized-alt-account");
    }

    public static String SPAM() {
        return acm.getMessage(MessageType.REASON, "spam");
    }

    public static String ABUSIVE_LANGUAGE() {
        return acm.getMessage(MessageType.REASON, "abusive-language");
    }

    public static String STEALING() {
        return acm.getMessage(MessageType.REASON, "stealing");
    }

    public static String GRIEFING() {
        return acm.getMessage(MessageType.REASON, "griefing");
    }
}
