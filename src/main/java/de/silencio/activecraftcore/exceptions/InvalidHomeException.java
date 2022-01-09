package de.silencio.activecraftcore.exceptions;

import org.bukkit.entity.Player;

public class InvalidHomeException extends ActiveCraftException {

    private final String invalidString;
    private final Player player;

    public InvalidHomeException(String message, String invalidString, Player player) {
        super(message);
        this.invalidString = invalidString;
        this.player = player;
    }

    public InvalidHomeException(String invalidString, Player player) {
        this(invalidString + " is not a home.", invalidString, player);
    }

    public InvalidHomeException(String invalidString) {
        this(invalidString + " is not a home.", invalidString, null);
    }
    
    public String getInvalidString() {
        return invalidString;
    }

    public Player getPlayer() {
        return player;
    }
}
