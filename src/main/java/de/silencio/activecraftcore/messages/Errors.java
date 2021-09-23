package de.silencio.activecraftcore.messages;

import de.silencio.activecraftcore.Main;
import org.bukkit.ChatColor;

public class Errors {

    static ActiveCraftMessage acm = Main.getPlugin().getActiveCraftMessage();

    public static String WARNING() {
        return acm.getMessage(MessageType.ERROR, "general-warning");
    }

    public static String NO_PERMISSION() {
        return ChatColor.RED + WARNING() + ChatColor.GRAY + acm.getMessage(MessageType.ERROR, "no-permission");
    }

    public static String INVALID_PLAYER() {
        return ChatColor.RED + WARNING() + ChatColor.GRAY + acm.getMessage(MessageType.ERROR, "invalid-player");
    }

    public static String INVALID_NUMBER() {
        return ChatColor.RED + WARNING() + ChatColor.GRAY + acm.getMessage(MessageType.ERROR, "invalid-number");
    }

    public static String INVALID_ARGUMENTS() {
        return ChatColor.RED + WARNING() + ChatColor.GRAY + acm.getMessage(MessageType.ERROR, "invalid-arguments");
    }

    public static String TOO_MANY_ARGUMENTS() {
        return ChatColor.RED + WARNING() + ChatColor.GRAY + acm.getMessage(MessageType.ERROR, "too-many-arguments");
    }

    public static String CANNOT_TARGET_SELF() {
        return ChatColor.RED + WARNING() + ChatColor.GRAY + acm.getMessage(MessageType.ERROR, "cannot-target-self");
    }

    public static String INVALID_COLOR() {
        return ChatColor.RED + WARNING() + ChatColor.GRAY + acm.getMessage(MessageType.ERROR, "invalid-color");
    }

    public static String INVALID_HEX() {
        return ChatColor.RED + WARNING() + ChatColor.GRAY + acm.getMessage(MessageType.ERROR, "invalid-hex");
    }

    public static String INVALID_ENTITY() {
        return ChatColor.RED + WARNING() + ChatColor.GRAY + acm.getMessage(MessageType.ERROR, "invalid-entity");
    }

    public static String NUMBER_TOO_LARGE() {
        return ChatColor.RED + WARNING() + ChatColor.GRAY + acm.getMessage(MessageType.ERROR, "number-too-large");
    }

    public static String NOT_A_PLAYER() {
        return ChatColor.RED + WARNING() + ChatColor.GRAY + acm.getMessage(MessageType.ERROR, "not-a-player");
    }

    public static String NOT_TRUE_FALSE() {
        return ChatColor.RED + WARNING() + ChatColor.GRAY + acm.getMessage(MessageType.ERROR, "not-true-false");
    }

    public static String NOT_HOLDING_ITEM() {
        return ChatColor.RED + WARNING() + ChatColor.GRAY + acm.getMessage(MessageType.ERROR, "not-holding-item");
    }
}
