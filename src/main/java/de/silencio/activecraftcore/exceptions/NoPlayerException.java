package de.silencio.activecraftcore.exceptions;

public class NoPlayerException extends ActiveCraftException {

    private final String wannabePlayer;

    public NoPlayerException(String message, String wannabePlayer) {
        super(message);
        this.wannabePlayer = wannabePlayer;
    }

    public NoPlayerException(String wannabePlayer) {
        this(wannabePlayer + " is not an instance of a player.", wannabePlayer);
    }

    public String getWannabePlayer() {
        return wannabePlayer;
    }

}
