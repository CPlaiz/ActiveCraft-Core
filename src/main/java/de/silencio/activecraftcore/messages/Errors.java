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
}
