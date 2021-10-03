package de.silencio.activecraftcore.profilemenu;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.gui.GuiClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MainProfileListener implements Listener {

    @EventHandler
    public void onGuiClick(GuiClickEvent event) {
        if (!Main.getPlugin().getProfileMenuList().containsKey((Player) event.getView().getPlayer())) return;
        Player player = (Player) event.getView().getPlayer();
        ProfileMenu2 profileMenu = Main.getPlugin().getFromProfileMenuList(player);

        if (event.getClickedInventory() == profileMenu.getMainProfile().getGuiCreator().build().getInventory()) {

        }
    }

}
