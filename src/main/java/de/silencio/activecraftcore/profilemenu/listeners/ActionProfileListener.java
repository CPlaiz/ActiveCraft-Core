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

public class ActionProfileListener implements Listener {

    @EventHandler
    public void onGuiClick(GuiClickEvent event) {
        if (!Main.getPlugin().getProfileMenuList().containsKey((Player) event.getView().getPlayer())) return;
        Player player = (Player) event.getView().getPlayer();
        ProfileMenu2 profileMenu = Main.getPlugin().getFromProfileMenuList(player);
        Gui gui = event.getGui();

        if (!event.getGui().getAssociatedGuiCreator().getInternalName().equals("action_profile")) return;

        if (event.getCurrentItem() == profileMenu.getActionProfile().getGodModeItem()) {
            if (!player.hasPermission("activecraft.god.others")) return;
            player.performCommand("god " + profileMenu.getTarget().getName());
            profileMenu.getActionProfile().renew();
            player.openInventory(profileMenu.getActionProfile().getGuiCreator().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getFlyItem()) {
            if (!player.hasPermission("activecraft.fly.others")) return;
            player.performCommand("fly " + profileMenu.getTarget().getName());
            profileMenu.getActionProfile().renew();
            player.openInventory(profileMenu.getActionProfile().getGuiCreator().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getVanishItem()) {
            if (!player.hasPermission("activecraft.vanish.others")) return;
            player.performCommand("vanish " + profileMenu.getTarget().getName());
            profileMenu.getActionProfile().renew();
            player.openInventory(profileMenu.getActionProfile().getGuiCreator().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getFeedItem()) {
            if (!player.hasPermission("activecraft.feed.others")) return;
            player.performCommand("feed " + profileMenu.getTarget().getName());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getHealItem()) {
            if (!player.hasPermission("activecraft.heal.others")) return;
            player.performCommand("heal " + profileMenu.getTarget().getName());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getClearInvItem()) {
            if (!player.hasPermission("activecraft.clearinv.others")) return;
            Main.getPlugin().getGuiHistoryMap().add(player, event.getClickedInventory());
            player.openInventory(new GuiConfirmation("action_profile.clearinv").getGuiCreator().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getHomeItem()) {
            if (!player.hasPermission("activecraft.home.others")) return;
            Main.getPlugin().getGuiHistoryMap().add(player, event.getClickedInventory());
            profileMenu.getHomeListProfile().renew();
            player.openInventory(profileMenu.getHomeListProfile().getPage(0).build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getTpherePlayerItem()) {
            if (!player.hasPermission("activecraft.tphere")) return;
            Main.getPlugin().getGuiHistoryMap().add(player, event.getClickedInventory());
            player.openInventory(new GuiConfirmation("action_profile.tp_here").getGuiCreator().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getTpToPlayerItem()) {
            if (!player.hasPermission("activecraft.tp.self")) return;
            Main.getPlugin().getGuiHistoryMap().add(player, event.getClickedInventory());
            player.openInventory(new GuiConfirmation("action_profile.tp_to").getGuiCreator().build().getInventory());
        } else if (event.getCurrentItem() == profileMenu.getActionProfile().getKillItem()) {
            if (!player.hasPermission("activecraft.kill")) return;
            Main.getPlugin().getGuiHistoryMap().add(player, event.getClickedInventory());
            player.openInventory(new GuiConfirmation("action_profile.kill").getGuiCreator().build().getInventory());
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
        if (!Main.getPlugin().getProfileMenuList().containsKey(event.getPlayer())) return;
        Player player = event.getPlayer();
        ProfileMenu2 profileMenu = Main.getPlugin().getFromProfileMenuList(player);
        Gui gui = event.getGui();

        if (!event.getGui().getAssociatedGuiCreator().getInternalName().startsWith("confirmation_action_profile.")) return;
        String[] typeArray = event.getIdentifier().split("\\.");
        String type = typeArray[typeArray.length-1];

        if (type.equals("clearinv")) {
            player.performCommand("clear " + profileMenu.getTarget().getName());
        } else if (type.equals("tp_here")) {
            player.performCommand("tphere " + profileMenu.getTarget().getName());
        } else if (type.equals("tp_to")) {
            player.performCommand("tp " + profileMenu.getTarget().getName());
        } else if (type.equals("kill")) {
            player.performCommand("kill " + profileMenu.getTarget().getName());
        }
    }
}
