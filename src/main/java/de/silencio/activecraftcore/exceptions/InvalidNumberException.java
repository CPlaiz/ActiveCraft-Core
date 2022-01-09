package de.silencio.activecraftcore.exceptions;

public class InvalidNumberException extends ActiveCraftException {

    private final String invalidString;

    public InvalidNumberException(String message, String invalidString) {
        super(message);
        this.invalidString = invalidString;
    }

    public InvalidNumberException(String invalidString) {
        this(invalidString + " cannot be converted into an Integer.", invalidString);
    }


    public String getInvalidString() {
        return invalidString;
    }

}
