package de.silencio.activecraftcore.guis.profilemenu.inventories;

import de.silencio.activecraftcore.guicreator.*;
import de.silencio.activecraftcore.guis.ProfileMenu;
import de.silencio.activecraftcore.manager.WarnManager;
import de.silencio.activecraftcore.messages.ProfileMessages;
import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class StorageProfile extends GuiCreator {

    private Player player;
    private Player target;
    private Profile profile;
    private WarnManager warnManager;
    private GuiPlayerHead playerHead;

    private GuiItem invSeeStack;
    private GuiItem offInvStack;
    private GuiItem enderchestStack;

    private ProfileMenu profileMenu;

    public StorageProfile(ProfileMenu profileMenu) {
        super("storage_profile", 3, ProfileMessages.STORAGE_GUI_TITLE());
        this.profileMenu = profileMenu;
        this.player = profileMenu.getPlayer();
        this.target = profileMenu.getTarget();
        warnManager = profileMenu.getWarnManager();
        profile = profileMenu.getProfile();
        refresh();
        profileMenu.setStorageProfile(this);
    }

    @Override
    public void refresh(){

        fillBackground(true);
        setCloseItem(new GuiCloseItem(22));
        setBackItem(new GuiBackItem(21));
        setPlayerHead(profileMenu.getPlayerHead());

        {
            invSeeStack = new GuiItem(Material.CHEST)
                    .setDisplayName(ProfileMessages.STORAGE_GUI_OPEN_INVENTORY(target));
            if (player.hasPermission("activecraft.invsee")) {
                setItemInSlot(invSeeStack, 12);
            } else setItemInSlot(new GuiNoPermissionItem(), 12);
        }
        {
            enderchestStack = new GuiItem(Material.ENDER_CHEST)
                    .setDisplayName(ProfileMessages.STORAGE_GUI_OPEN_ENDERCHEST(target));
            if (player.hasPermission("activecraft.enderchest.others")) {
                setItemInSlot(enderchestStack, 14);
            } else setItemInSlot(new GuiNoPermissionItem(), 14);
        }
        {
            offInvStack = new GuiItem(Material.SHIELD)
                    .setDisplayName(ProfileMessages.STORAGE_GUI_OPEN_ARMORINV(target))
                    .setLore(ProfileMessages.STORAGE_GUI_OPEN_ARMORINV_LORE());
            if (player.hasPermission("activecraft.enderchest.others")) {
                setItemInSlot(offInvStack, 13);
            } else setItemInSlot(new GuiNoPermissionItem(), 13);
        }
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
