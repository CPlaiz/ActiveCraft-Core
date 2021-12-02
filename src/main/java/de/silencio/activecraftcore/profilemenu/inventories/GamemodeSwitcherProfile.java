package de.silencio.activecraftcore.profilemenu.inventories;

import de.silencio.activecraftcore.gui.*;
import de.silencio.activecraftcore.manager.BanManager;
import de.silencio.activecraftcore.manager.WarnManager;
import de.silencio.activecraftcore.messages.ProfileMessages;
import de.silencio.activecraftcore.profilemenu.ProfileMenu;
import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class GamemodeSwitcherProfile {

    private ProfileMenu profileMenu;
    private Player player;
    private Player target;
    private Profile profile;
    private BanManager nameBanManager;
    private BanManager ipBanManager;
    private WarnManager warnManager;
    private GuiPlayerHead playerHead;
    private GuiCreator guiCreator;

    private GuiItem survivalStack;
    private GuiItem creativeStack;
    private GuiItem spectatorStack;
    private GuiItem adventureStack;

    public GamemodeSwitcherProfile(ProfileMenu profileMenu) {
        this.profileMenu = profileMenu;
        this.player = profileMenu.getPlayer();
        this.target = profileMenu.getTarget();
        nameBanManager = profileMenu.getNameBanManager();
        ipBanManager = profileMenu.getIpBanManager();
        warnManager = profileMenu.getWarnManager();
        guiCreator = new GuiCreator("gamemode_switcher_profile", 3, ProfileMessages.GAMEMODE_SWITCHER_GUI_TITLE());
        profile = profileMenu.getProfile();
        renew();
        profileMenu.setGamemodeSwitcherProfile(this);
    }

    public void renew() {

        guiCreator.fillBackground(true);
        guiCreator.setBackItem(new GuiBackItem(21));
        guiCreator.setCloseItem(new GuiCloseItem(22));
        guiCreator.setPlayerHead(profileMenu.getPlayerHead());

        {
            creativeStack = new GuiItem(Material.GRASS_BLOCK)
                    .setDisplayName(ProfileMessages.GAMEMODE_SWITCHER_GUI_CREATIVE(target));
            if (player.hasPermission("activecraft.gamemode.creative.others")) {
                guiCreator.setItemInSlot(creativeStack, 11);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 11);
        }
        {
            survivalStack = new GuiItem(Material.IRON_SWORD)
                    .setDisplayName(ProfileMessages.GAMEMODE_SWITCHER_GUI_SURVIVAL(target));
            if (player.hasPermission("activecraft.gamemode.survival.others")) {
                guiCreator.setItemInSlot(survivalStack, 12);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 12);
        }
        {
            GuiItem currentGamemodeStack = new GuiItem(Material.WHITE_STAINED_GLASS_PANE);

            switch (target.getGameMode()) {
                case CREATIVE:
                    currentGamemodeStack.setDisplayName(ProfileMessages.GAMEMODE_SWITCHER_GUI_CURRENT_GAMEMODE_CREATIVE());
                    break;
                case SURVIVAL:
                    currentGamemodeStack.setDisplayName(ProfileMessages.GAMEMODE_SWITCHER_GUI_CURRENT_GAMEMODE_SURVIVAL());
                    break;
                case SPECTATOR:
                    currentGamemodeStack.setDisplayName(ProfileMessages.GAMEMODE_SWITCHER_GUI_CURRENT_GAMEMODE_SPECTATOR());
                    break;
                case ADVENTURE:
                    currentGamemodeStack.setDisplayName(ProfileMessages.GAMEMODE_SWITCHER_GUI_CURRENT_GAMEMODE_ADVENTURE());
                    break;
            }

            guiCreator.setItemInSlot(currentGamemodeStack, 13);
        }
        {
            spectatorStack = new GuiItem(Material.ENDER_EYE)
                    .setDisplayName(ProfileMessages.GAMEMODE_SWITCHER_GUI_SPECTATOR(target));
            if (player.hasPermission("activecraft.gamemode.spectator.others")) {
                guiCreator.setItemInSlot(spectatorStack, 15);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 15);
        }
        {
            adventureStack = new GuiItem(Material.MAP)
                    .setDisplayName(ProfileMessages.GAMEMODE_SWITCHER_GUI_ADVENTURE(target));
            if (player.hasPermission("activecraft.gamemode.adventure.others")) {
                guiCreator.setItemInSlot(adventureStack, 14);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 14);
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

    public GuiItem getSurvivalStack() {
        return survivalStack;
    }

    public void setSurvivalStack(GuiItem survivalStack) {
        this.survivalStack = survivalStack;
    }

    public GuiItem getCreativeStack() {
        return creativeStack;
    }

    public void setCreativeStack(GuiItem creativeStack) {
        this.creativeStack = creativeStack;
    }

    public GuiItem getSpectatorStack() {
        return spectatorStack;
    }

    public void setSpectatorStack(GuiItem spectatorStack) {
        this.spectatorStack = spectatorStack;
    }

    public GuiItem getAdventureStack() {
        return adventureStack;
    }

    public void setAdventureStack(GuiItem adventureStack) {
        this.adventureStack = adventureStack;
    }
}
