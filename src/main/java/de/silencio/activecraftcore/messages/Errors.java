package de.silencio.activecraftcore.messages;

public class Errors extends ActiveCraftMessage {

    public Errors(String input) {
        super(input);
    }

    public static String WARNING() {
        return getMessage("");
    }

    public static String NO_PERMISSION() {
        return getMessage("");
    }

    public static String INVALID_PLAYER() {
        return getMessage("");
    }

    public static String INVALID_NUMBER() {
        return getMessage("");
    }

    public static String INVALID_ARGUMENTS() {
        return getMessage("");
    }

    public static String TOO_MANY_ARGUMENTS() {
        return getMessage("");
    }

    public static String CANNOT_TARGET_SELF() {
        return getMessage("");
    }

    public static String NOT_A_PLAYER() {
        return getMessage("");
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
