package de.silencio.activecraftcore.gui;

import de.silencio.activecraftcore.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class GuiCreator {

    private Inventory inventory;

    private String title;
    private GuiPlayerHead playerHead;
    private GuiBackItem backItem;
    private GuiCloseItem closeItem;
    private boolean backgroundFilled;
    private int rows;
    private GuiItem[] itemInSlot = new GuiItem[55];
    private InventoryHolder holder;

    public GuiCreator(int rows) {
        this.setTitle("GUI");
        this.rows = rows;
    }

    public GuiCreator() {
        this.setTitle("GUI");
        this.rows = 1;
    }

    public String getTitle() {
        return title;
    }

    public GuiCreator setTitle(String title) {
        this.title = title;
        return this;
    }

    public GuiPlayerHead getPlayerHead() {
        return playerHead;
    }

    public boolean isBackgroundFilled() {
        return backgroundFilled;
    }

    public GuiCreator fillBackground(boolean backgroundFilled) {
        this.backgroundFilled = backgroundFilled;
        return this;
    }

    public GuiCloseItem getCloseItem() {
        return closeItem;
    }

    public GuiCreator setCloseItem(GuiCloseItem closeItem) {
        this.closeItem = closeItem;
        return this;
    }

    public GuiCreator setPlayerHead(GuiPlayerHead guiPlayerHead) {
        this.playerHead = guiPlayerHead;
        return this;
    }

    public GuiBackItem getBackItem() {
        return backItem;
    }

    public GuiCreator setBackItem(GuiBackItem guiBackItem) {
        this.backItem = guiBackItem;
        return this;
    }

    public int getRows() {
        return rows;
    }

    public GuiCreator setRows(int rows) {
        this.rows = rows;
        return this;
    }

    public GuiItem getItemInSlot(int slot) {
        return itemInSlot[slot];
    }

    public GuiCreator setItemInSlot(GuiItem itemInSlot, int slot) {
        this.itemInSlot[slot] = itemInSlot;
        return this;
    }

    public InventoryHolder getHolder() {
        return holder;
    }

    public GuiCreator setHolder(InventoryHolder holder) {
        this.holder = holder;
        return this;
    }

    public Gui build() {
        if (rows == 0) rows = 1;
        if (rows >= 6) rows = 6;
        inventory = Bukkit.createInventory(holder, 9 * rows, title);

        //set playerhead
        if (playerHead != null) setItemInSlot(playerHead, playerHead.getPosition());
        //set backItem
        if (backItem != null) setItemInSlot(backItem, backItem.getPosition());

        if (closeItem != null) setItemInSlot(closeItem, closeItem.getPosition());

        if (backgroundFilled) {
            for (int i = 0; i < itemInSlot.length; i++) {
                if (itemInSlot[i] == null) setItemInSlot(new GuiItem(Material.GRAY_STAINED_GLASS_PANE).setDisplayName(""), i);
            }
        }

        for (int i = 0; i < itemInSlot.length; i++) {
            if (i >= rows*9) break;
            GuiItem item = itemInSlot[i];
            inventory.setItem(i, item);
            if (item != null) {
                Main.getPlugin().getGuiData().addToCorrespondingGuiItem(inventory.getItem(i), item);
            }
        }

        Main.getPlugin().getGuiData().addToGuiList(inventory, itemInSlot);
        return new Gui(inventory, this);
    }
}