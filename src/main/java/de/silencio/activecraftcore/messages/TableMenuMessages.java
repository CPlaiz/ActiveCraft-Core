package de.silencio.activecraftcore.messages;

import de.silencio.activecraftcore.Main;
import org.bukkit.ChatColor;

public class TableMenuMessages {

    static ActiveCraftMessage acm = Main.getPlugin().getActiveCraftMessage();

    public static String TABLEMENU_TITLE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.TABLEMENU, "title");
        return msg;
    }

    public static String TABLEMENU_CRAFTING_TABLE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.TABLEMENU, "crafting-table");
        return msg;
    }

    public static String TABLEMENU_CARTOGRAPHY_TABLE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.TABLEMENU, "cartography-table");
        return msg;
    }

    public static String TABLEMENU_STONECUTTER() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.TABLEMENU, "stonecutter");
        return msg;
    }

    public static String TABLEMENU_ANVIL() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.TABLEMENU, "anvil");
        return msg;
    }

    public static String TABLEMENU_GRINDSTONE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.TABLEMENU, "grindstone");
        return msg;
    }

    public static String TABLEMENU_LOOM() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.TABLEMENU, "loom");
        return msg;
    }

    public static String TABLEMENU_SMITHING_TABLE() {

        String msg = ChatColor.GOLD + acm.getMessage(MessageType.TABLEMENU, "smithing-table");
        return msg;
    }
}
