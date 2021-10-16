package de.silencio.activecraftcore.profilemenu.listeners;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.gui.Gui;
import de.silencio.activecraftcore.gui.GuiClickEvent;
import de.silencio.activecraftcore.gui.GuiConfirmEvent;
import de.silencio.activecraftcore.gui.GuiConfirmation;
import de.silencio.activecraftcore.profilemenu.ProfileMenu2;
import de.silencio.activecraftcore.profilemenu.inventories.HomeListProfile;
import net.kyori.adventure.audience.Audience;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class StorageProfileListener implements Listener {

    @EventHandler
    public void onGuiClick(GuiClickEvent event) {
        if (!Main.getPlugin().getProfileMenuList().containsKey((Player) event.getView().getPlayer())) return;
        Player player = (Player) event.getView().getPlayer();
        ProfileMenu2 profileMenu = Main.getPlugin().getFromProfileMenuList(player);
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
