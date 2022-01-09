package de.silencio.activecraftcore.exceptions;

import org.bukkit.entity.Player;

public class NotHoldingItemException extends ActiveCraftException {

    private final Player player;
    private final ExpectedItem expectedItem;

    public NotHoldingItemException(String message, Player player, ExpectedItem expectedItem) {
        super(message);
        this.player = player;
        this.expectedItem = expectedItem;
    }

    public NotHoldingItemException(Player player, ExpectedItem expectedItem) {
        this(player + " is not holding the right item.", player, expectedItem);
    }

    public Player getInvalidString() {
        return player;
    }

    public ExpectedItem getExpectedItem() {
        return expectedItem;
    }

    public enum ExpectedItem {
        WRITTEN_BOOK,
        LEATHER_ITEM,
        ANY
    }
}
