package de.silencio.activecraftcore.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GuiData {

    private GuiItem[] guiList;

    private HashMap<ItemStack, GuiItem> correspondingGuiItem;

    public GuiData() {
        correspondingGuiItem = new HashMap<>();
        guiList = new GuiItem[53];
    }

    public GuiItem[] getGuiList() {
        return guiList;
    }

    public void setGuiList(GuiItem[] guiList) {
        this.guiList = guiList;
    }

    public void addToGuiList(GuiItem guiItem, int slot) {
        this.guiList[slot] = guiItem;
    }

    public void removeFromGuiList(GuiItem guiItem, int slot) {
        this.guiList[slot] = null;
    }

    public GuiItem getFromGuiList(int slot) {
        return this.guiList[slot];
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
