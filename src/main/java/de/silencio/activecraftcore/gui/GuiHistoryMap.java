package de.silencio.activecraftcore.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Stack;

public class GuiHistoryMap {

    private HashMap<Player, Stack<Inventory>> playerGuiHistory = new HashMap<>();

    public HashMap<Player, Stack<Inventory>> getPlayerGuiHistory() {
        return playerGuiHistory;
    }

    public void setPlayerGuiHistory(HashMap<Player, Stack<Inventory>> playerGuiHistory) {
        this.playerGuiHistory = playerGuiHistory;
    }

    public void add(Player player, Inventory inventory) {
        if (getGuiStack(player) == null) {
            Stack<Inventory> inventoryStack = new Stack<>();
            inventoryStack.add(inventory);
            playerGuiHistory.put(player, inventoryStack);
        } else {
            Stack<Inventory> inventoryStack = getGuiStack(player);
            inventoryStack.add(inventory);
            playerGuiHistory.put(player, inventoryStack);
        }
    }

    public void remove(Player player) {
        playerGuiHistory.remove(player);
    }

    public void clear(Player player) {
        Stack<Inventory> playerGuiStack = getGuiStack(player);
        playerGuiStack.clear();
        playerGuiHistory.put(player, playerGuiStack);
    }

    public Stack<Inventory> getGuiStack(Player player) {
        return playerGuiHistory.get(player);
    }
}
