package de.silencio.activecraftcore.profilemenu.listeners;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.gui.Gui;
import de.silencio.activecraftcore.gui.GuiClickEvent;
import de.silencio.activecraftcore.profilemenu.ProfileMenu2;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MainProfileListener implements Listener {

    @EventHandler
    public void onGuiClick(GuiClickEvent event) {
        if (!Main.getPlugin().getProfileMenuList().containsKey((Player) event.getView().getPlayer())) return;
        Player player = (Player) event.getView().getPlayer();
        ProfileMenu2 profileMenu = Main.getPlugin().getFromProfileMenuList(player);
        Gui gui = event.getGui();

        if (!event.getGui().getAssociatedGuiCreator().getInternalName().equals("main_profile")) return;

        if (event.getCurrentItem() == profileMenu.getMainProfile().getActionMenuStack()) {
            Main.getPlugin().getGuiHistoryMap().add(player, gui.getAssociatedGuiCreator().build().getInventory());
            profileMenu.getActionProfile().renew();
            player.openInventory(profileMenu.getActionProfile().getGuiCreator().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getMainProfile().getViolationStack()) {
            Main.getPlugin().getGuiHistoryMap().add(player, gui.getAssociatedGuiCreator().build().getInventory());
            profileMenu.getViolationsProfile().renew();
            player.openInventory(profileMenu.getViolationsProfile().getGuiCreator().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getMainProfile().getGamemodeSwitcherStack()) {
            Main.getPlugin().getGuiHistoryMap().add(player, gui.getAssociatedGuiCreator().build().getInventory());
            profileMenu.getGamemodeSwitcherProfile().renew();
            player.openInventory(profileMenu.getGamemodeSwitcherProfile().getGuiCreator().build().getInventory());
        }

    }
}
