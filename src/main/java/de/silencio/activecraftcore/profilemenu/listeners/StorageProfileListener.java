package de.silencio.activecraftcore.profilemenu.listeners;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.gui.Gui;
import de.silencio.activecraftcore.gui.GuiClickEvent;
import de.silencio.activecraftcore.profilemenu.ProfileMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class StorageProfileListener implements Listener {

    @EventHandler
    public void onGuiClick(GuiClickEvent event) {
        if (!ActiveCraftCore.getPlugin().getProfileMenuList().containsKey((Player) event.getView().getPlayer())) return;
        Player player = (Player) event.getView().getPlayer();
        ProfileMenu profileMenu = ActiveCraftCore.getPlugin().getFromProfileMenuList(player);
        Gui gui = event.getGui();

        if (!event.getGui().getAssociatedGuiCreator().getInternalName().equals("storage_profile")) return;

        if (event.getCurrentItem() == profileMenu.getStorageProfile().getInvSeeStack()) {
            if (!player.hasPermission("activecraft.invsee")) return;
            player.performCommand("invsee " + profileMenu.getTarget().getName());
        } else if (event.getCurrentItem() == profileMenu.getStorageProfile().getEnderchestStack()) {
            if (!player.hasPermission("activecraft.enderchest.others")) return;
            player.performCommand("ec " + profileMenu.getTarget().getName());
        }
    }
}
