package de.silencio.activecraftcore.guis.profilemenu.inventories;

import de.silencio.activecraftcore.guicreator.*;
import de.silencio.activecraftcore.guis.ProfileMenu;
import de.silencio.activecraftcore.manager.WarnManager;
import de.silencio.activecraftcore.messages.ProfileMessages;
import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ViolationsProfile extends GuiCreator {

    private ProfileMenu profileMenu;
    private Player player;
    private Player target;
    private Profile profile;
    private WarnManager warnManager;
    private GuiPlayerHead playerHead;

    private GuiItem warnStack;
    private GuiItem banStack;
    private GuiItem ipBanStack;
    private GuiItem muteStack;
    private GuiItem kickStack;

    public ViolationsProfile(ProfileMenu profileMenu) {
        super("violations_profile", 3, ProfileMessages.VIOLATIONS_GUI_TITLE());
        this.profileMenu = profileMenu;
        this.player = profileMenu.getPlayer();
        this.target = profileMenu.getTarget();
        warnManager = profileMenu.getWarnManager();
        profile = profileMenu.getProfile();
        refresh();
        profileMenu.setViolationsProfile(this);
    }

    @Override
    public void refresh() {

        profileMenu.getProfile().refresh();
        profile = profileMenu.getProfile();

        setPlayerHead(profileMenu.getPlayerHead());
        setBackItem(new GuiBackItem(21));
        setCloseItem(new GuiCloseItem(22));
        fillBackground(true);

        {
            banStack = new GuiItem(Material.CHAIN_COMMAND_BLOCK)
                    .setDisplayName(ProfileMessages.VIOLATIONS_GUI_BAN(target));
            if (player.hasPermission("activecraft.ban")) {
                setItemInSlot(banStack, 14);
            } else setItemInSlot(new GuiNoPermissionItem(), 14);
        }
        {
            warnStack = new GuiItem(Material.REDSTONE_BLOCK)
                    .setDisplayName(ProfileMessages.VIOLATIONS_GUI_WARN(target));
            if (player.hasPermission("activecraft.warn.add")) {
                setItemInSlot(warnStack, 11);
            } else setItemInSlot(new GuiNoPermissionItem(), 11);
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
            setItemInSlot(muteStack, 12);
        } else setItemInSlot(new GuiNoPermissionItem(), 12);
        {
            ipBanStack = new GuiItem(Material.REPEATING_COMMAND_BLOCK)
                    .setDisplayName(ProfileMessages.VIOLATIONS_GUI_BAN_IP(target, target.getAddress().getAddress().toString().replace("/", "")));
            if (player.hasPermission("activecraft.ban")) {
                setItemInSlot(ipBanStack, 15);
            } else setItemInSlot(new GuiNoPermissionItem(), 15);
        }
        {
            kickStack = new GuiItem(Material.COMMAND_BLOCK)
                    .setDisplayName(ProfileMessages.VIOLATIONS_GUI_KICK(target));
            if (player.hasPermission("activecraft.kick")) {
                setItemInSlot(kickStack, 13);
            } else setItemInSlot(new GuiNoPermissionItem(), 13);
        }
    }

    public GuiPlayerHead getPlayerHead() {
        return playerHead;
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
