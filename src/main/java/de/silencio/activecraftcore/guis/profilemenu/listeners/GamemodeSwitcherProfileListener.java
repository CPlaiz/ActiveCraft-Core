package de.silencio.activecraftcore.guis.profilemenu.listeners;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.guicreator.Gui;
import de.silencio.activecraftcore.guicreator.GuiClickEvent;
import de.silencio.activecraftcore.guis.ProfileMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GamemodeSwitcherProfileListener implements Listener {

    @EventHandler
    public void onGuiClick(GuiClickEvent event) {
        if (!ActiveCraftCore.getProfileMenuList().containsKey((Player) event.getView().getPlayer())) return;
        Player player = (Player) event.getView().getPlayer();
        ProfileMenu profileMenu = ActiveCraftCore.getFromProfileMenuList(player);
        Gui gui = event.getGui();

        if (!event.getGui().getAssociatedGuiCreator().getInternalName().equals("gamemode_switcher_profile")) return;

        if (event.getCurrentItem() == profileMenu.getGamemodeSwitcherProfile().getCreativeStack()) {
            if (!player.hasPermission("activecraft.gamemode.creative.others")) return;
            player.performCommand("cr " + profileMenu.getTarget().getName());
            profileMenu.getGamemodeSwitcherProfile().refresh();
            player.openInventory(profileMenu.getGamemodeSwitcherProfile().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getGamemodeSwitcherProfile().getSurvivalStack()) {
            if (!player.hasPermission("activecraft.gamemode.survival.others")) return;
            player.performCommand("su " + profileMenu.getTarget().getName());
            profileMenu.getGamemodeSwitcherProfile().refresh();
            player.openInventory(profileMenu.getGamemodeSwitcherProfile().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getGamemodeSwitcherProfile().getSpectatorStack()) {
            if (!player.hasPermission("activecraft.gamemode.spectator.others")) return;
            player.performCommand("sp " + profileMenu.getTarget().getName());
            profileMenu.getGamemodeSwitcherProfile().refresh();
            player.openInventory(profileMenu.getGamemodeSwitcherProfile().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getGamemodeSwitcherProfile().getAdventureStack()) {
            if (!player.hasPermission("activecraft.gamemode.adventure.others")) return;
            player.performCommand("ad " + profileMenu.getTarget().getName());
            profileMenu.getGamemodeSwitcherProfile().refresh();
            player.openInventory(profileMenu.getGamemodeSwitcherProfile().build().getInventory());
        }
    }
}
