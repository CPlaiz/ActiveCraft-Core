package de.silencio.activecraftcore.gui;

import org.bukkit.Material;

public class GuiConfirmation {

    private String identifier;
    private GuiCreator guiCreator;
    private String title;

    public GuiConfirmation(String identifier) {
        this.identifier = identifier;
        this.title = "Confirmation";
        this.guiCreator = new GuiCreator("confirmation_" + identifier, 3, title);
        guiCreator.fillBackground(true);
        guiCreator.setItemInSlot(new GuiItem(Material.LIME_CONCRETE).setDisplayName("Confirm"), 11);
        guiCreator.setItemInSlot(new GuiItem(Material.RED_CONCRETE).setDisplayName("Cancel"), 15);
    }

    public GuiConfirmation(String identifier, String title) {
        this.identifier = identifier;
        this.title = title;
        this.guiCreator = new GuiCreator("confirmation_" + identifier, 3, title);
        guiCreator.fillBackground(true);
        guiCreator.setItemInSlot(new GuiItem(Material.LIME_CONCRETE).setDisplayName("Confirm"), 11);
        guiCreator.setItemInSlot(new GuiItem(Material.RED_CONCRETE).setDisplayName("Cancel"), 15);
    }

    public String getIdentifier() {
        return identifier;
    }

    public GuiCreator getGuiCreator() {
        return guiCreator;
    }

    public String getTitle() {
        return title;
    }
}
