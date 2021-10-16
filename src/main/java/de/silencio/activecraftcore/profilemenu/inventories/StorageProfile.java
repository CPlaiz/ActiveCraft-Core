package de.silencio.activecraftcore.profilemenu.inventories;

import de.silencio.activecraftcore.gui.*;
import de.silencio.activecraftcore.manager.BanManager;
import de.silencio.activecraftcore.manager.WarnManager;
import de.silencio.activecraftcore.messages.ProfileMessages;
import de.silencio.activecraftcore.profilemenu.ProfileMenu2;
import de.silencio.activecraftcore.utils.ItemBuilder;
import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class StorageProfile {

    private Player player;
    private Player target;
    private Profile profile;
    private BanManager nameBanManager;
    private BanManager ipBanManager;
    private WarnManager warnManager;
    private GuiPlayerHead playerHead;
    private GuiCreator guiCreator;

    private GuiItem invSeeStack;
    private GuiItem offInvStack;
    private GuiItem enderchestStack;

    private ProfileMenu2 profileMenu;

    public StorageProfile(ProfileMenu2 profileMenu) {
        this.profileMenu = profileMenu;
        this.player = profileMenu.getPlayer();
        this.target = profileMenu.getTarget();
        nameBanManager = profileMenu.getNameBanManager();
        ipBanManager = profileMenu.getIpBanManager();
        warnManager = profileMenu.getWarnManager();
        guiCreator = new GuiCreator("storage_profile", 3, ProfileMessages.STORAGE_GUI_TITLE());
        profile = profileMenu.getProfile();
        renew();
        profileMenu.setStorageProfile(this);
    }

    public void renew(){

        guiCreator.fillBackground(true);
        guiCreator.setCloseItem(new GuiCloseItem(22));
        guiCreator.setBackItem(new GuiBackItem(21));
        guiCreator.setPlayerHead(profileMenu.getPlayerHead());

        {
            invSeeStack = new GuiItem(Material.CHEST)
                    .setDisplayName(ProfileMessages.STORAGE_GUI_OPEN_INVENTORY(target));
            if (player.hasPermission("activecraft.invsee")) {
                guiCreator.setItemInSlot(invSeeStack, 12);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 12);
        }
        {
            enderchestStack = new GuiItem(Material.ENDER_CHEST)
                    .setDisplayName(ProfileMessages.STORAGE_GUI_OPEN_ENDERCHEST(target));
            if (player.hasPermission("activecraft.enderchest.others")) {
                guiCreator.setItemInSlot(enderchestStack, 14);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 14);
        }
        {
            offInvStack = new GuiItem(Material.SHIELD)
                    .setDisplayName(ProfileMessages.STORAGE_GUI_OPEN_ARMORINV(target))
                    .setLore(ProfileMessages.STORAGE_GUI_OPEN_ARMORINV_LORE());
            if (player.hasPermission("activecraft.enderchest.others")) {
                guiCreator.setItemInSlot(offInvStack, 13);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 13);
        }
    }

    public GuiCreator getGuiCreator() {
        return guiCreator;
    }

    public void setGuiCreator(GuiCreator guiCreator) {
        this.guiCreator = guiCreator;
    }

    public void setEnderchestStack(GuiItem enderchestStack) {
        this.enderchestStack = enderchestStack;
    }

    public GuiItem getEnderchestStack() {
        return enderchestStack;
    }

    public void setInvSeeStack(GuiItem invSeeStack) {
        this.invSeeStack = invSeeStack;
    }

    public GuiItem getInvSeeStack() {
        return invSeeStack;
    }
}
