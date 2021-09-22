package de.silencio.activecraftcore.gui;

import de.silencio.activecraftcore.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class GuiCreator {

    private Inventory inventory;

    private String title;
    private String internalName;
    private String previousMenu;
    private String onClickCommand;
    private String onClickGui;
    private GuiPlayerHead playerHead;
    private boolean backgroundFilled;
    private boolean hasCloseBarrier;
    private boolean hasPlayerHead;
    private boolean hasBackArrow;
    private boolean hasClickSound;
    private boolean[] hasItemInSlot;
    private int rows;
    private ItemStack[] itemInSlot;
    private InventoryHolder holder;

    public GuiCreator() {
        ItemBuilder itemBuilder = new ItemBuilder(Material.CROSSBOW);
    }

    public String getTitle() {
        return title;
    }

    public GuiCreator setTitle(String title) {
        this.title = title;
        playerHead = new GuiPlayerHead();
        return this;
    }

    public GuiPlayerHead getPlayerHead() {
        return playerHead;
    }

    public String getInternalName() {
        return internalName;
    }

    public GuiCreator setInternalName(String internalName) {
        this.internalName = internalName;
        return this;
    }

    public String getPreviousMenu() {
        return previousMenu;
    }

    public GuiCreator setPreviousMenu(String previousMenu) {
        this.previousMenu = previousMenu;
        return this;
    }
    
    public boolean isBackgroundFilled() {
        return backgroundFilled;
    }

    public GuiCreator setBackgroundFilled(boolean backgroundFilled) {
        this.backgroundFilled = backgroundFilled;
        return this;
    }

    public boolean hasCloseBarrier() {
        return hasCloseBarrier;
    }

    public GuiCreator setHasCloseBarrier(boolean hasCloseBarrier) {
        this.hasCloseBarrier = hasCloseBarrier;
        return this;
    }

    public boolean hasPlayerHead() {
        return hasPlayerHead;
    }

    public GuiCreator setHasPlayerHead(boolean hasPlayerHead) {
        this.hasPlayerHead = hasPlayerHead;
        return this;
    }
    public GuiCreator setPlayerHead(boolean hasPlayerHead, int position) {
        this.hasPlayerHead = hasPlayerHead;
        return this;
    }

    public boolean hasBackArrow() {
        return hasBackArrow;
    }

    public GuiCreator setHasBackArrow(boolean hasBackArrow) {
        this.hasBackArrow = hasBackArrow;
        return this;
    }

    public boolean isHasClickSound() {
        return hasClickSound;
    }

    public GuiCreator setHasClickSound(boolean hasClickSound) {
        this.hasClickSound = hasClickSound;
        return this;
    }

    public boolean[] getHasItemInSlot() {
        return hasItemInSlot;
    }

    public GuiCreator setHasItemInSlot(int slot, boolean hasItem) {
        this.hasItemInSlot[slot] = hasItem;
        return this;
    }

    public int getRows() {
        return rows;
    }

    public GuiCreator setRows(int rows) {
        this.rows = rows;
        return this;
    }

    public ItemStack getItemInSlot(int slot) {
        return itemInSlot[slot];
    }

    public GuiCreator setItemInSlot(int slot, ItemStack itemStack) {
        this.itemInSlot[slot] = itemStack;
        return this;
    }

    public InventoryHolder getHolder() {
        return holder;
    }

    public GuiCreator setHolder(InventoryHolder holder) {
        this.holder = holder;
        return this;
    }

    public Inventory build() {
        if (rows == 0) rows = 1;
        if (rows >= 6) rows = 6;
        inventory = Bukkit.createInventory(holder, 9 * rows, title);

        //set playerhead
        if (hasPlayerHead) setItemInSlot(4, playerHead);
        //set back item
        if (hasBackArrow) setItemInSlot(rows * 9 - 3, new GuiBackItem());
        //


        return inventory;
    }
}