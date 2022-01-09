package de.silencio.activecraftcore.listener;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.commands.SuicideCommand;
import de.silencio.activecraftcore.playermanagement.Profile;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        FileConfig mainConfig = new FileConfig("config.yml");

        Player died = e.getEntity(); //in 1.17 gibt get player nen error deshalb IMMER get entity
        Profile profile = ActiveCraftCore.getProfile(died);
        Player killer = died.getKiller();

        ActiveCraftCore.setLastLocationForPlayer(died, died.getLocation());

        if (died.hasPermission("activecraft.keepexp")) {
            e.setKeepLevel(true);
            e.setShouldDropExperience(false);
        }

        if (died.hasPermission("keepinventory")) {
            e.setKeepInventory(true);
            e.getDrops().clear();
        }

        if (mainConfig.getBoolean("drop-all-exp-on-death")) {
            e.setDroppedExp(died.getTotalExperience());
        }

        if (SuicideCommand.getSuiciders().contains(died)) {
            e.setDeathMessage(null);
            SuicideCommand.getSuiciders().remove(died);
            return;
        }

        if (profile.isVanished()) {
            e.setDeathMessage(null);
            return;
        }

        String deathmessage = e.getDeathMessage();
        if (killer != null) {
            String beforeBrackets = deathmessage.split("\\[")[0];
            beforeBrackets = beforeBrackets.replace(died.getName(), died.getDisplayName()
                            .replace(" " + mainConfig.getString("vanish-format"), "")
                            .replace(" " + mainConfig.getString("afk-format"), "")
                            + ChatColor.WHITE)
                    .replace(killer.getName(), killer.getDisplayName()
                            .replace(" " + mainConfig.getString("vanish-format"), "")
                            .replace(" " + mainConfig.getString("afk-format"), "")
                            + ChatColor.RESET);
            e.setDeathMessage(ChatColor.RED + "☠ " + beforeBrackets + killer.getInventory().getItemInMainHand().getItemMeta().getDisplayName());
        } else {
            deathmessage = deathmessage.replace(died.getName(), died.getDisplayName() + ChatColor.RESET);
            e.setDeathMessage(ChatColor.RED + "☠ " + deathmessage);
        }
    }
}