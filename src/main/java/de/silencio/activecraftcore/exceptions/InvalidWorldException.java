package de.silencio.activecraftcore.exceptions;

public class InvalidWorldException extends ActiveCraftException {

    private final String invalidWorldname;

    public InvalidWorldException(String message, String invalidWorldname) {
        super(message);
        this.invalidWorldname = invalidWorldname;
    }

    public InvalidWorldException(String invalidWorldname) {
        this("No player with the name " + invalidWorldname + " could be found.", invalidWorldname);
    }

    public String getInvalidWorldname() {
        return invalidWorldname;
    }

}
