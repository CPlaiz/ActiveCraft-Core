package de.silencio.activecraftcore.messages;

import de.silencio.activecraftcore.ActiveCraftCore;
import org.bukkit.ChatColor;

public class GuiMessages {

    static ActiveCraftMessage acm = ActiveCraftCore.getActiveCraftMessage();

    public static String BACK_ARROW() {

        return ChatColor.GOLD + acm.getMessage(MessageType.GUI, "back-arrow");
    }

    public static String CLOSE_ITEM() {

        return ChatColor.GOLD + acm.getMessage(MessageType.GUI, "close-item");
    }

    public static String CONFIRM_ITEM() {

        return ChatColor.GOLD + acm.getMessage(MessageType.GUI, "confirm-item");
    }

    public static String CANCEL_ITEM() {

        return ChatColor.GOLD + acm.getMessage(MessageType.GUI, "cancel-item");
    }

    public static String CONFIRMATION_TITLE() {

        return ChatColor.GOLD + acm.getMessage(MessageType.GUI, "confirmation-title");
    }

    public static String DEFAULT_GUI_TITLE() {

        return ChatColor.GOLD + acm.getMessage(MessageType.GUI, "default-guicreator-title");
    }

    public static String NO_PERMISSION_ITEM() {

        return ChatColor.GOLD + acm.getMessage(MessageType.GUI, "no-permission-item");
    }
}