package de.silencio.activecraftcore.gui;

import de.silencio.activecraftcore.gui.*;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class GuiCreateEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private GuiCreator guiCreator;
    private InventoryHolder holder;
    private int rows;
    private String title;
    private GuiPlayerHead playerHead;
    private GuiBackItem backItem;
    private GuiCloseItem closeItem;
    private boolean backgroundFilled;
    private GuiItem[] itemInSlot;

    public GuiCreateEvent(GuiCreator guiCreator, InventoryHolder holder, int rows, String title,
                          GuiPlayerHead playerHead, GuiBackItem backItem, GuiCloseItem closeItem,
                          boolean backgroundFilled, GuiItem[] itemInSlot) {
        this.holder = holder;
        this.rows = rows;
        this.title = title;
        this.playerHead = playerHead;
        this.backItem = backItem;
        this.closeItem = closeItem;
        this.backgroundFilled = backgroundFilled;
        this.itemInSlot = itemInSlot;
        this.guiCreator = guiCreator;
    }

    public GuiCreator getGuiCreator() {
        return guiCreator;
    }

    public void setGuiCreator(GuiCreator guiCreator) {
        this.guiCreator = guiCreator;
    }

    public InventoryHolder getHolder() {
        return holder;
    }

    public void setHolder(InventoryHolder holder) {
        this.holder = holder;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GuiPlayerHead getPlayerHead() {
        return playerHead;
    }

    public void setPlayerHead(GuiPlayerHead playerHead) {
        this.playerHead = playerHead;
    }

    public GuiBackItem getBackItem() {
        return backItem;
    }

    public void setBackItem(GuiBackItem backItem) {
        this.backItem = backItem;
    }

    public GuiCloseItem getCloseItem() {
        return closeItem;
    }

    public void setCloseItem(GuiCloseItem closeItem) {
        this.closeItem = closeItem;
    }

    public boolean isBackgroundFilled() {
        return backgroundFilled;
    }

    public void setBackgroundFilled(boolean backgroundFilled) {
        this.backgroundFilled = backgroundFilled;
    }

    public GuiItem[] getItemInSlot() {
        return itemInSlot;
    }

    public void setItemInSlot(GuiItem[] itemInSlot) {
        this.itemInSlot = itemInSlot;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
