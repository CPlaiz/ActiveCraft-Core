package de.silencio.activecraftcore.guicreator;

import de.silencio.activecraftcore.messages.GuiMessages;
import org.bukkit.Material;

public class GuiConfirmation extends GuiCreator {

    private String identifier;
    private String title;

    public GuiConfirmation(String identifier) {
        super("confirmation_" + identifier, 3, GuiMessages.CONFIRMATION_TITLE());
        this.identifier = identifier;
        this.title = GuiMessages.CONFIRMATION_TITLE();
        fillBackground(true);
        setItemInSlot(new GuiItem(Material.LIME_CONCRETE).setDisplayName(GuiMessages.CONFIRM_ITEM()), 11);
        setItemInSlot(new GuiItem(Material.RED_CONCRETE).setDisplayName(GuiMessages.CANCEL_ITEM()), 15);
    }

    public GuiConfirmation(String identifier, String title) {
        super("confirmation_" + identifier, 3, title);
        this.identifier = identifier;
        this.title = title;
        fillBackground(true);
        setItemInSlot(new GuiItem(Material.LIME_CONCRETE).setDisplayName(GuiMessages.CONFIRM_ITEM()), 11);
        setItemInSlot(new GuiItem(Material.RED_CONCRETE).setDisplayName(GuiMessages.CANCEL_ITEM()), 15);
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void refresh() {
    }

    public String getTitle() {
        return title;
    }
}

