package de.silencio.activecraftcore.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class GuiNoPermissionItem extends GuiItem {

    public GuiNoPermissionItem() {
        super(Material.BARRIER);
        this.setDisplayName(ChatColor.RED + "No Permission");
    }
}