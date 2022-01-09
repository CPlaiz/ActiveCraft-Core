package de.silencio.activecraftcore.messages;

import de.silencio.activecraftcore.ActiveCraftCore;

public class Reasons {

    static ActiveCraftMessage acm = ActiveCraftCore.getActiveCraftMessage();

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

    public static String MODERATOR_BANNED() {
        return acm.getMessage(MessageType.REASON, "moderator-banned");
    }

    public static String MODERATOR_WARNED() {
        return acm.getMessage(MessageType.REASON, "moderator-warned");
    }

    public static String MODERATOR_KICKED() {
        return acm.getMessage(MessageType.REASON, "moderator-kicked");
    }
}
