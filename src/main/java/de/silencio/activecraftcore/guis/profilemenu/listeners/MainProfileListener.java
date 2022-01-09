package de.silencio.activecraftcore.guis.profilemenu.listeners;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.guicreator.Gui;
import de.silencio.activecraftcore.guicreator.GuiClickEvent;
import de.silencio.activecraftcore.guicreator.GuiNavigator;
import de.silencio.activecraftcore.guis.ProfileMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MainProfileListener implements Listener {

    @EventHandler
    public void onGuiClick(GuiClickEvent event) {
        if (!ActiveCraftCore.getProfileMenuList().containsKey((Player) event.getView().getPlayer())) return;
        Player player = (Player) event.getView().getPlayer();
        ProfileMenu profileMenu = ActiveCraftCore.getFromProfileMenuList(player);
        Gui gui = event.getGui();

        if (!event.getGui().getAssociatedGuiCreator().getInternalName().equals("main_profile")) return;

        if (event.getCurrentItem() == profileMenu.getMainProfile().getActionMenuStack()) {
            GuiNavigator.push(player, gui.rebuild().getInventory());
            profileMenu.getActionProfile().refresh();
            player.openInventory(profileMenu.getActionProfile().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getMainProfile().getViolationStack()) {
            GuiNavigator.push(player, gui.rebuild().getInventory());
            profileMenu.getViolationsProfile().refresh();
            player.openInventory(profileMenu.getViolationsProfile().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getMainProfile().getGamemodeSwitcherStack()) {
            GuiNavigator.push(player, gui.rebuild().getInventory());
            profileMenu.getGamemodeSwitcherProfile().refresh();
            player.openInventory(profileMenu.getGamemodeSwitcherProfile().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getMainProfile().getStorageMenuStack()) {
            GuiNavigator.push(player, gui.rebuild().getInventory());
            profileMenu.getStorageProfile().refresh();
            player.openInventory(profileMenu.getStorageProfile().build().getInventory());
        }

    }
}
