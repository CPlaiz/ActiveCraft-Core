package de.silencio.activecraftcore.guis.profilemenu.inventories;

import de.silencio.activecraftcore.guicreator.*;
import de.silencio.activecraftcore.guis.ProfileMenu;
import de.silencio.activecraftcore.manager.WarnManager;
import de.silencio.activecraftcore.messages.ProfileMessages;
import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class GamemodeSwitcherProfile extends GuiCreator {

    private ProfileMenu profileMenu;
    private Player player;
    private Player target;
    private Profile profile;
    private WarnManager warnManager;
    private GuiPlayerHead playerHead;

    private GuiItem survivalStack;
    private GuiItem creativeStack;
    private GuiItem spectatorStack;
    private GuiItem adventureStack;

    public GamemodeSwitcherProfile(ProfileMenu profileMenu) {
        super("gamemode_switcher_profile", 3, ProfileMessages.GAMEMODE_SWITCHER_GUI_TITLE());
        this.profileMenu = profileMenu;
        this.player = profileMenu.getPlayer();
        this.target = profileMenu.getTarget();
        warnManager = profileMenu.getWarnManager();
        profile = profileMenu.getProfile();
        refresh();
        profileMenu.setGamemodeSwitcherProfile(this);
    }

    @Override
    public void refresh() {

        fillBackground(true);
        setBackItem(new GuiBackItem(21));
        setCloseItem(new GuiCloseItem(22));
        setPlayerHead(profileMenu.getPlayerHead());

        {
            creativeStack = new GuiItem(Material.GRASS_BLOCK)
                    .setDisplayName(ProfileMessages.GAMEMODE_SWITCHER_GUI_CREATIVE(target));
            if (player.hasPermission("activecraft.gamemode.creative.others")) {
                setItemInSlot(creativeStack, 11);
            } else setItemInSlot(new GuiNoPermissionItem(), 11);
        }
        {
            survivalStack = new GuiItem(Material.IRON_SWORD)
                    .setDisplayName(ProfileMessages.GAMEMODE_SWITCHER_GUI_SURVIVAL(target));
            if (player.hasPermission("activecraft.gamemode.survival.others")) {
                setItemInSlot(survivalStack, 12);
            } else setItemInSlot(new GuiNoPermissionItem(), 12);
        }
        {
            GuiItem currentGamemodeStack = new GuiItem(Material.WHITE_STAINED_GLASS_PANE);

            switch (target.getGameMode()) {
                case CREATIVE -> currentGamemodeStack.setDisplayName(ProfileMessages.GAMEMODE_SWITCHER_GUI_CURRENT_GAMEMODE_CREATIVE());
                case SURVIVAL -> currentGamemodeStack.setDisplayName(ProfileMessages.GAMEMODE_SWITCHER_GUI_CURRENT_GAMEMODE_SURVIVAL());
                case SPECTATOR -> currentGamemodeStack.setDisplayName(ProfileMessages.GAMEMODE_SWITCHER_GUI_CURRENT_GAMEMODE_SPECTATOR());
                case ADVENTURE -> currentGamemodeStack.setDisplayName(ProfileMessages.GAMEMODE_SWITCHER_GUI_CURRENT_GAMEMODE_ADVENTURE());
            }

            setItemInSlot(currentGamemodeStack, 13);
        }
        {
            spectatorStack = new GuiItem(Material.ENDER_EYE)
                    .setDisplayName(ProfileMessages.GAMEMODE_SWITCHER_GUI_SPECTATOR(target));
            if (player.hasPermission("activecraft.gamemode.spectator.others")) {
                setItemInSlot(spectatorStack, 15);
            } else setItemInSlot(new GuiNoPermissionItem(), 15);
        }
        {
            adventureStack = new GuiItem(Material.MAP)
                    .setDisplayName(ProfileMessages.GAMEMODE_SWITCHER_GUI_ADVENTURE(target));
            if (player.hasPermission("activecraft.gamemode.adventure.others")) {
                setItemInSlot(adventureStack, 14);
            } else setItemInSlot(new GuiNoPermissionItem(), 14);
        }
    }


    public GuiPlayerHead getPlayerHead() {
        return playerHead;
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
