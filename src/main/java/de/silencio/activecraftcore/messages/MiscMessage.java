package de.silencio.activecraftcore.messages;

import de.silencio.activecraftcore.ActiveCraftCore;

public class MiscMessage {

    static ActiveCraftMessage acm = ActiveCraftCore.getPlugin().getActiveCraftMessage();

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
