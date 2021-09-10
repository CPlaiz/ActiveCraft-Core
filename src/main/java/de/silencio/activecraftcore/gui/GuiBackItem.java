package de.silencio.activecraftcore.gui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiBackItem extends ItemStack {
    
    public GuiBackItem() {
        super(Material.ARROW);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName("Go back");
    }
    
}
