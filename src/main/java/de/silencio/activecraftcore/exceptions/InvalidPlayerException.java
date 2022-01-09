package de.silencio.activecraftcore.exceptions;

public class InvalidPlayerException extends ActiveCraftException {

    private final String invalidPlayername;

    public InvalidPlayerException(String message, String invalidPlayername) {
        super(message);
        this.invalidPlayername = invalidPlayername;
    }

    public InvalidPlayerException(String invalidPlayername) {
        this("No player with the name " + invalidPlayername + " could be found.", invalidPlayername);
    }

    public String getInvalidPlayername() {
        return invalidPlayername;
    }

}
