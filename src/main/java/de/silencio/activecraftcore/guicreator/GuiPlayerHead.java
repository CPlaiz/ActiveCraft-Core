package de.silencio.activecraftcore.guicreator;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.SkullMeta;

public class GuiPlayerHead extends GuiItem {

    private int position;

    public GuiPlayerHead(int position) {
        super(Material.PLAYER_HEAD);
        this.position = position;
    }

    public GuiPlayerHead(OfflinePlayer owningPlayer, int position) {
        super(Material.PLAYER_HEAD);
        this.position = position;
        this.setOwner(owningPlayer);
    }
    
    public GuiPlayerHead setOwner(OfflinePlayer player) {
        SkullMeta skullMeta = (SkullMeta) this.getItemMeta();
        skullMeta.setOwningPlayer(player);
        this.setItemMeta(skullMeta);
        return this;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}