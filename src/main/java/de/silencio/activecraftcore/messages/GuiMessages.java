package de.silencio.activecraftcore.messages;

import de.silencio.activecraftcore.ActiveCraftCore;
import org.bukkit.ChatColor;

public class GuiMessages {

    static ActiveCraftMessage acm = ActiveCraftCore.getPlugin().getActiveCraftMessage();

    public static String BACK_ARROW() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.GUI, "back-arrow");
        return msg;
    }

    public static String CLOSE_ITEM() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.GUI, "close-item");
        return msg;
    }

    public static String CONFIRM_ITEM() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.GUI, "confirm-item");
        return msg;
    }

    public static String CANCEL_ITEM() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.GUI, "cancel-item");
        return msg;
    }

    public static String CONFIRMATION_TITLE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.GUI, "confirmation-title");
        return msg;
    }

    public static String DEFAULT_GUI_TITLE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.GUI, "default-gui-title");
        return msg;
    }

    public static String NO_PERMISSION_ITEM() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.GUI, "no-permission-item");
        return msg;
    }
}