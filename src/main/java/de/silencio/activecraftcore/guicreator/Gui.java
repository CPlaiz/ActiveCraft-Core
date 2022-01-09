package de.silencio.activecraftcore.guicreator;

import de.silencio.activecraftcore.ActiveCraftCore;
import org.bukkit.inventory.Inventory;

import java.util.Random;

public class Gui {

    private String name;
    private int id;
    private GuiCreator associatedGuiCreator;
    private Inventory inventory;

    public Gui(Inventory inventory, GuiCreator guiCreator) {
        this.inventory = inventory;
        this.associatedGuiCreator = guiCreator;
        int randInt = newRandom(10000);
        while (true) {
            if (ActiveCraftCore.getGuiList().containsKey(randInt)) {
                randInt = newRandom(10000);
            } else {
                ActiveCraftCore.getGuiList().put(randInt, this);
                break;
            }
        }
        this.id = randInt;
    }

    private int newRandom(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        update();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        update();
    }

    public GuiCreator getAssociatedGuiCreator() {
        return associatedGuiCreator;
    }

    public void setAssociatedGuiCreator(GuiCreator associatedGuiCreator) {
        this.associatedGuiCreator = associatedGuiCreator;
        update();
    }

    public Gui rebuild() {
        return getAssociatedGuiCreator().build();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
        update();
    }

    public void update() {
        ActiveCraftCore.getGuiList().put(id, this);
    }

}