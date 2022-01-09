package de.silencio.activecraftcore.guicreator;

import de.silencio.activecraftcore.messages.GuiMessages;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiBackItem extends GuiItem {

    private int position;

    public GuiBackItem(int position) {
        super(Material.ARROW);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + GuiMessages.BACK_ARROW());
        this.setItemMeta(itemMeta);
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
