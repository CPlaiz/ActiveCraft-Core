package de.silencio.activecraftcore.profilemenu.inventories;

import de.silencio.activecraftcore.gui.*;
import de.silencio.activecraftcore.manager.BanManager;
import de.silencio.activecraftcore.manager.WarnManager;
import de.silencio.activecraftcore.messages.ProfileMessages;
import de.silencio.activecraftcore.profilemenu.ProfileMenu;
import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ViolationsProfile {

    private ProfileMenu profileMenu;
    private Player player;
    private Player target;
    private Profile profile;
    private BanManager nameBanManager;
    private BanManager ipBanManager;
    private WarnManager warnManager;
    private GuiPlayerHead playerHead;
    private GuiCreator guiCreator;

    private GuiItem warnStack;
    private GuiItem banStack;
    private GuiItem ipBanStack;
    private GuiItem muteStack;
    private GuiItem kickStack;

    public ViolationsProfile(ProfileMenu profileMenu) {
        this.profileMenu = profileMenu;
        this.player = profileMenu.getPlayer();
        this.target = profileMenu.getTarget();
        nameBanManager = profileMenu.getNameBanManager();
        ipBanManager = profileMenu.getIpBanManager();
        warnManager = profileMenu.getWarnManager();
        guiCreator = new GuiCreator("violations_profile", 3, ProfileMessages.VIOLATIONS_GUI_TITLE());
        profile = profileMenu.getProfile();
        renew();
        profileMenu.setViolationsProfile(this);
    }

    public void renew() {

        profileMenu.getProfile().refresh();
        profile = profileMenu.getProfile();

        guiCreator.setPlayerHead(profileMenu.getPlayerHead());
        guiCreator.setBackItem(new GuiBackItem(21));
        guiCreator.setCloseItem(new GuiCloseItem(22));
        guiCreator.fillBackground(true);

        {
            banStack = new GuiItem(Material.CHAIN_COMMAND_BLOCK)
                    .setDisplayName(ProfileMessages.VIOLATIONS_GUI_BAN(target));
            if (player.hasPermission("activecraft.ban")) {
                guiCreator.setItemInSlot(banStack, 14);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 14);
        }
        {
            warnStack = new GuiItem(Material.REDSTONE_BLOCK)
                    .setDisplayName(ProfileMessages.VIOLATIONS_GUI_WARN(target));
            if (player.hasPermission("activecraft.warn.add")) {
                guiCreator.setItemInSlot(warnStack, 11);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 11);
        }
        if (!profile.isMuted()) {
            muteStack = new GuiItem(Material.NETHERITE_BLOCK)
                    .setDisplayName(ProfileMessages.VIOLATIONS_GUI_MUTE(target))
                    .setLore(ProfileMessages.VIOLATIONS_GUI_MUTE_LORE(target));
        } else {
            muteStack = new GuiItem(Material.NETHERITE_BLOCK)
                    .setDisplayName(ProfileMessages.VIOLATIONS_GUI_UNMUTE(target))
                    .setLore(ProfileMessages.VIOLATIONS_GUI_UNMUTE_LORE(target));
        }
        if (player.hasPermission("activecraft.mute")) {
            guiCreator.setItemInSlot(muteStack, 12);
        } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 12);
        {
            ipBanStack = new GuiItem(Material.REPEATING_COMMAND_BLOCK)
                    .setDisplayName(ProfileMessages.VIOLATIONS_GUI_BAN_IP(target, target.getAddress().getAddress().toString().replace("/", "")));
            if (player.hasPermission("activecraft.ban")) {
                guiCreator.setItemInSlot(ipBanStack, 15);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 15);
        }
        {
            kickStack = new GuiItem(Material.COMMAND_BLOCK)
                    .setDisplayName(ProfileMessages.VIOLATIONS_GUI_KICK(target));
            if (player.hasPermission("activecraft.kick")) {
                guiCreator.setItemInSlot(kickStack, 13);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 13);
        }
    }

    public GuiPlayerHead getPlayerHead() {
        return playerHead;
    }

    public void setPlayerHead(GuiPlayerHead playerHead) {
        this.playerHead = playerHead;
    }

    public GuiCreator getGuiCreator() {
        return guiCreator;
    }

    public void setGuiCreator(GuiCreator guiCreator) {
        this.guiCreator = guiCreator;
    }

    public GuiItem getWarnStack() {
        return warnStack;
    }

    public void setWarnStack(GuiItem warnStack) {
        this.warnStack = warnStack;
    }

    public GuiItem getBanStack() {
        return banStack;
    }

    public void setBanStack(GuiItem banStack) {
        this.banStack = banStack;
    }

    public GuiItem getIpBanStack() {
        return ipBanStack;
    }

    public void setIpBanStack(GuiItem ipBanStack) {
        this.ipBanStack = ipBanStack;
    }

    public GuiItem getMuteStack() {
        return muteStack;
    }

    public void setMuteStack(GuiItem muteStack) {
        this.muteStack = muteStack;
    }

    public GuiItem getKickStack() {
        return kickStack;
    }

    public void setKickStack(GuiItem kickStack) {
        this.kickStack = kickStack;
    }
}
