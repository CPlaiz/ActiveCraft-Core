package de.silencio.activecraftcore.profilemenu.listeners;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.gui.Gui;
import de.silencio.activecraftcore.gui.GuiClickEvent;
import de.silencio.activecraftcore.gui.GuiNavigator;
import de.silencio.activecraftcore.profilemenu.ProfileMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MainProfileListener implements Listener {

    @EventHandler
    public void onGuiClick(GuiClickEvent event) {
        if (!ActiveCraftCore.getPlugin().getProfileMenuList().containsKey((Player) event.getView().getPlayer())) return;
        Player player = (Player) event.getView().getPlayer();
        ProfileMenu profileMenu = ActiveCraftCore.getPlugin().getFromProfileMenuList(player);
        Gui gui = event.getGui();

        if (!event.getGui().getAssociatedGuiCreator().getInternalName().equals("main_profile")) return;

        if (event.getCurrentItem() == profileMenu.getMainProfile().getActionMenuStack()) {
            GuiNavigator.push(player, gui.rebuild().getInventory());
            profileMenu.getActionProfile().renew();
            player.openInventory(profileMenu.getActionProfile().getGuiCreator().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getMainProfile().getViolationStack()) {
            GuiNavigator.push(player, gui.rebuild().getInventory());
            profileMenu.getViolationsProfile().renew();
            player.openInventory(profileMenu.getViolationsProfile().getGuiCreator().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getMainProfile().getGamemodeSwitcherStack()) {
            GuiNavigator.push(player, gui.rebuild().getInventory());
            profileMenu.getGamemodeSwitcherProfile().renew();
            player.openInventory(profileMenu.getGamemodeSwitcherProfile().getGuiCreator().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getMainProfile().getStorageMenuStack()) {
            GuiNavigator.push(player, gui.rebuild().getInventory());
            profileMenu.getStorageProfile().renew();
            player.openInventory(profileMenu.getStorageProfile().getGuiCreator().build().getInventory());
        }

    }
}
