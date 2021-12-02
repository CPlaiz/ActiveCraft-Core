package de.silencio.activecraftcore.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Stack;

public class GuiNavigator {

    private static HashMap<Player, Stack<Inventory>> playerGuiStack = new HashMap<>();

    public static void push(Player player, Inventory inventory) {
        if (getGuiStack(player) == null) {
            Stack<Inventory> inventoryStack = new Stack<>();
            inventoryStack.add(inventory);
            playerGuiStack.put(player, inventoryStack);
        } else {
            Stack<Inventory> inventoryStack = getGuiStack(player);
            inventoryStack.add(inventory);
            playerGuiStack.put(player, inventoryStack);
        }
    }

    public static void pushReplacement(Player player, Inventory inventory) {
        if (getGuiStack(player) == null) {
            Stack<Inventory> inventoryStack = new Stack<>();
            inventoryStack.push(inventory);
            playerGuiStack.put(player, inventoryStack);
        } else {
            Stack<Inventory> inventoryStack = getGuiStack(player);
            inventoryStack.pop();
            inventoryStack.push(inventory);
            playerGuiStack.put(player, inventoryStack);
        }
    }

    public static void pushAndRemoveUntil(Player player, Inventory inventory) {
        if (getGuiStack(player) == null) {
            Stack<Inventory> inventoryStack = new Stack<>();
            inventoryStack.push(inventory);
            playerGuiStack.put(player, inventoryStack);
        } else {
            Stack<Inventory> inventoryStack = getGuiStack(player);
            inventoryStack.clear();
            inventoryStack.push(inventory);
            playerGuiStack.put(player, inventoryStack);
        }
    }

    public static Inventory pop(Player player) {
        Inventory topInv = null;
        if (getGuiStack(player) != null) {
            Stack<Inventory> inventoryStack = getGuiStack(player);
            topInv = inventoryStack.pop();
            playerGuiStack.put(player, inventoryStack);
        }
        return topInv;
    }

    public void remove(Player player) {
        playerGuiStack.remove(player);
    }

    public void clear(Player player) {
        Stack<Inventory> playerGuiStack = getGuiStack(player);
        playerGuiStack.clear();
        GuiNavigator.playerGuiStack.put(player, playerGuiStack);
    }

    public static Stack<Inventory> getGuiStack(Player player) {
        return playerGuiStack.get(player);
    }
}
