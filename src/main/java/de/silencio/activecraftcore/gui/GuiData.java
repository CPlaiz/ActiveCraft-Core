package de.silencio.activecraftcore.gui;

import it.unimi.dsi.fastutil.Hash;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GuiData {

    private HashMap<Inventory, GuiItem[]> guiList = new HashMap<>();

    private HashMap<ItemStack, GuiItem> correspondingGuiItem = new HashMap<>();

    public HashMap<Inventory, GuiItem[]> getGuiList() {
        return guiList;
    }

    public void setGuiList(HashMap<Inventory, GuiItem[]> guiList) {
        this.guiList = guiList;
    }

    public void addToGuiList(Inventory guiCreator, GuiItem[] items) {
        this.guiList.put(guiCreator, items);
    }

    public void removeFromGuiList(Inventory guiCreator) {
        this.guiList.remove(guiCreator);
    }

    public GuiItem[] getFromGuiList(Inventory inventory) {
        return this.guiList.get(inventory);
    }

    public GuiItem getFromGuiList(Inventory inventory, int slot) {
        return this.guiList.get(inventory)[slot];
    }

    public HashMap<ItemStack, GuiItem> getCorrespondingGuiItem() {
        return correspondingGuiItem;
    }

    public void setCorrespondingGuiItem(HashMap<ItemStack, GuiItem> itemStackGuiItemHashMap) {
        this.correspondingGuiItem = itemStackGuiItemHashMap;
    }

    public void addToCorrespondingGuiItem(ItemStack itemStack, GuiItem guiItem) {
        this.correspondingGuiItem.put(itemStack, guiItem);
    }

    public void removeFromCorrespondingGuiItem(ItemStack itemStack) {
        this.correspondingGuiItem.remove(itemStack);
    }

    public GuiItem getFromCorrespondingGuiItem(ItemStack itemStack) {
        return this.correspondingGuiItem.get(itemStack);
    }
}
