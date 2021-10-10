package de.silencio.activecraftcore.gui;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GuiItem extends ItemStack {

    private boolean clickSound;
    private boolean movable;

    public GuiItem(Material material) {
        super(material);
    }

    public GuiItem(ItemStack itemStack) {
        super(itemStack.getType());
        this.setItemMeta(itemStack.getItemMeta());
        this.setAmount(itemStack.getAmount());
        for (ItemFlag itemFlag : itemStack.getItemFlags()) {
            this.addItemFlags(itemFlag);
        }
    }

    public GuiItem setDisplayName(String displayName) {
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(displayName);
        this.setItemMeta(itemMeta);
        return this;
    }

    public GuiItem setLore(String... lore) {
        List<String> stringList = new ArrayList<>(List.of(lore));
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setLore(stringList);
        this.setItemMeta(itemMeta);
        return this;
    }

    public String getDisplayName() {
        return this.getItemMeta().getDisplayName();
    }

    public List<String> getLore() {
        return this.getItemMeta().getLore();
    }

    public boolean hasClickSound() {
        return clickSound;
    }

    public GuiItem setClickSound(boolean clickSound) {
        this.clickSound = clickSound;
        return this;
    }

    public boolean isMovable() {
        return movable;
    }

    public GuiItem setMovable(boolean movable) {
        this.movable = movable;
        return this;
    }

    public ItemStack toItemStack() {
        return this;
    }


}
