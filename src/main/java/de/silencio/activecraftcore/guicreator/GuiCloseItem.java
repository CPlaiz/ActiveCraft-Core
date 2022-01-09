package de.silencio.activecraftcore.guicreator;

import de.silencio.activecraftcore.messages.GuiMessages;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiCloseItem extends GuiItem {

    private int position;

    public GuiCloseItem(int position) {
        super(Material.BARRIER);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + GuiMessages.CLOSE_ITEM());
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
