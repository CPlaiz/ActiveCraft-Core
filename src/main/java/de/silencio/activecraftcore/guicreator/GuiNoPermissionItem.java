package de.silencio.activecraftcore.guicreator;

import de.silencio.activecraftcore.messages.GuiMessages;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class GuiNoPermissionItem extends GuiItem {

    public GuiNoPermissionItem() {
        super(Material.BARRIER);
        this.setDisplayName(ChatColor.RED + GuiMessages.NO_PERMISSION_ITEM());
    }
}