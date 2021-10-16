package de.silencio.activecraftcore.profilemenu.listeners;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.gui.*;
import de.silencio.activecraftcore.profilemenu.ProfileMenu2;
import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class HomeListProfileListener implements Listener {

    @EventHandler
    public void onHomeClick(GuiClickEvent event) {
        if (!Main.getPlugin().getProfileMenuList().containsKey((Player) event.getView().getPlayer())) return;
        Player player = (Player) event.getView().getPlayer();
        ProfileMenu2 profileMenu = Main.getPlugin().getFromProfileMenuList(player);
        Gui gui = event.getGui();

        if (event.getCurrentItem() instanceof GuiBackItem) {
            profileMenu.getHomeListProfile().setCurrentPage(0);
        }

        if (!event.getGui().getAssociatedGuiCreator().getInternalName().equals("home_list_profile")) return;

        if (event.getCurrentItem() == profileMenu.getHomeListProfile().getNextArrow()) {
             player.openInventory(profileMenu.getHomeListProfile().getPage(profileMenu.getHomeListProfile().getCurrentPage() + 1).build().getInventory());
             profileMenu.getHomeListProfile().setCurrentPage(profileMenu.getHomeListProfile().getCurrentPage() + 1);
        } else if (event.getCurrentItem() == profileMenu.getHomeListProfile().getLastArrow()) {
             player.openInventory(profileMenu.getHomeListProfile().getPage(profileMenu.getHomeListProfile().getCurrentPage() - 1).build().getInventory());
            profileMenu.getHomeListProfile().setCurrentPage(profileMenu.getHomeListProfile().getCurrentPage() - 1);
        }

        Profile profile = new Profile(profileMenu.getTarget());
        if (event.getCurrentItem().getLore() == null) return;
        for (String homeName : profile.getHomeList().keySet()) {
            Location loc = profile.getHomeList().get(homeName);
            if (event.getCurrentItem().getDisplayName().equals(homeName)) {
                boolean shouldBreak = false;
                for (String lore : event.getCurrentItem().getLore()) {
                    if (lore.equals(ChatColor.AQUA + loc.getWorld().getName() + ChatColor.GOLD
                            + ", " + ChatColor.AQUA + loc.getBlockX() + ChatColor.GOLD
                            + ", " + ChatColor.AQUA + loc.getBlockY() + ChatColor.GOLD
                            + ", " + ChatColor.AQUA + loc.getBlockZ())) {
                        player.performCommand("home " + profileMenu.getTarget().getName() + " " + homeName);
                        shouldBreak = true;
                        break;
                    }
                }
                if (shouldBreak) break;
            }
        }
    }

}
