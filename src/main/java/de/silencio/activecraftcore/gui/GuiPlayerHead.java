package de.silencio.activecraftcore.gui;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class GuiPlayerHead extends ItemStack {

    public GuiPlayerHead() {
        super(Material.PLAYER_HEAD);
    }

    public GuiPlayerHead setDisplayName(String displayName) {
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(displayName);
        this.setItemMeta(itemMeta);
        return this;
    }

    public GuiPlayerHead setLore(String... lore) {
        List<String> stringList = new ArrayList<>(List.of(lore));
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setLore(stringList);
        this.setItemMeta(itemMeta);
        return this;
    }
    
    public GuiPlayerHead setOwner(OfflinePlayer player) {
        SkullMeta skullMeta = (SkullMeta) this.getItemMeta();
        skullMeta.setOwningPlayer(player);
        this.setItemMeta(skullMeta);
        return this;
    }
}