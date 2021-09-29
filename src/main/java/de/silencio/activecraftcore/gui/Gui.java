package de.silencio.activecraftcore.gui;

import de.silencio.activecraftcore.Main;
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
            if (Main.getPlugin().getGuiList().containsKey(randInt)) {
                randInt = newRandom(10000);
            } else {
                Main.getPlugin().getGuiList().put(randInt, this);
                break;
            }
        }
        this.id = randInt;
    }

    private int newRandom(int bound) {
        Random random = new Random();
        int randInt = random.nextInt(bound);
        return randInt;
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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
        update();
    }

    public void update() {
        Main.getPlugin().getGuiList().put(id, this);
    }

}