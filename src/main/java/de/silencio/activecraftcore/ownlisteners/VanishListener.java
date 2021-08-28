package de.silencio.activecraftcore.ownlisteners;

import org.bukkit.entity.Player;

public interface VanishListener {

    void onVanish(Player player);

    void onUnvanish(Player player);
}
