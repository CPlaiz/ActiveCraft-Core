package de.silencio.activecraftcore.guis.profilemenu.listeners;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.guicreator.*;
import de.silencio.activecraftcore.guis.ProfileMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ActionProfileListener implements Listener {

    @EventHandler
    public void onGuiClick(GuiClickEvent event) {
        if (!ActiveCraftCore.getProfileMenuList().containsKey((Player) event.getView().getPlayer())) return;
        Player player = (Player) event.getView().getPlayer();
        ProfileMenu profileMenu = ActiveCraftCore.getFromProfileMenuList(player);
        Gui gui = event.getGui();

        if (!event.getGui().getAssociatedGuiCreator().getInternalName().equals("action_profile")) return;

        if (event.getCurrentItem() == profileMenu.getActionProfile().getGodModeItem()) {
            if (!player.hasPermission("activecraft.god.others")) return;
            player.performCommand("god " + profileMenu.getTarget().getName());
            profileMenu.getActionProfile().refresh();
            player.openInventory(profileMenu.getActionProfile().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getFlyItem()) {
            if (!player.hasPermission("activecraft.fly.others")) return;
            player.performCommand("fly " + profileMenu.getTarget().getName());
            profileMenu.getActionProfile().refresh();
            player.openInventory(profileMenu.getActionProfile().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getVanishItem()) {
            if (!player.hasPermission("activecraft.vanish.others")) return;
            player.performCommand("vanish " + profileMenu.getTarget().getName());
            profileMenu.getActionProfile().refresh();
            player.openInventory(profileMenu.getActionProfile().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getFeedItem()) {
            if (!player.hasPermission("activecraft.feed.others")) return;
            player.performCommand("feed " + profileMenu.getTarget().getName());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getHealItem()) {
            if (!player.hasPermission("activecraft.heal.others")) return;
            player.performCommand("heal " + profileMenu.getTarget().getName());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getClearInvItem()) {
            if (!player.hasPermission("activecraft.clearinv.others")) return;
            GuiNavigator.push(player, event.getClickedInventory());
            player.openInventory(new GuiConfirmation("action_profile.clearinv").build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getHomeItem()) {
            if (!player.hasPermission("activecraft.home.others")) return;
            GuiNavigator.push(player, event.getClickedInventory());
            profileMenu.getHomeListProfile().renew();
            player.openInventory(profileMenu.getHomeListProfile().getPage(0).build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getTpherePlayerItem()) {
            if (!player.hasPermission("activecraft.tphere")) return;
            GuiNavigator.push(player, event.getClickedInventory());
            player.openInventory(new GuiConfirmation("action_profile.tp_here").build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getTpToPlayerItem()) {
            if (!player.hasPermission("activecraft.tp.self")) return;
            GuiNavigator.push(player, event.getClickedInventory());
            player.openInventory(new GuiConfirmation("action_profile.tp_to").build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getKillItem()) {
            if (!player.hasPermission("activecraft.kill")) return;
            GuiNavigator.push(player, event.getClickedInventory());
            player.openInventory(new GuiConfirmation("action_profile.kill").build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getStrikeItem()) {
            if (!player.hasPermission("activecraft.strike.others")) return;
           player.performCommand("strike " + profileMenu.getTarget().getName());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getExplodeItem()) {
            if (!player.hasPermission("activecraft.explode.others")) return;
            player.performCommand("explode " + profileMenu.getTarget().getName());
        }
    }

    @EventHandler
    public void onConfirm(GuiConfirmEvent event) {
        if (!ActiveCraftCore.getProfileMenuList().containsKey(event.getPlayer())) return;
        Player player = event.getPlayer();
        ProfileMenu profileMenu = ActiveCraftCore.getFromProfileMenuList(player);
        Gui gui = event.getGui();

        if (!event.getGui().getAssociatedGuiCreator().getInternalName().startsWith("confirmation_action_profile.")) return;
        String[] typeArray = event.getIdentifier().split("\\.");
        String type = typeArray[typeArray.length-1];

        switch (type) {
            case "clearinv" -> player.performCommand("clear " + profileMenu.getTarget().getName());
            case "tp_here" -> player.performCommand("tphere " + profileMenu.getTarget().getName());
            case "tp_to" -> player.performCommand("tp " + profileMenu.getTarget().getName());
            case "kill" -> player.performCommand("kill " + profileMenu.getTarget().getName());
        }
    }
}
