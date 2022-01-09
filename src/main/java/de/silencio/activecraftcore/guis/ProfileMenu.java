package de.silencio.activecraftcore.guis;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.guicreator.GuiPlayerHead;
import de.silencio.activecraftcore.guis.profilemenu.inventories.*;
import de.silencio.activecraftcore.manager.WarnManager;
import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ProfileMenu {

    private Player player;
    private Player target;
    private Profile profile;
    private WarnManager warnManager;
    private GuiPlayerHead playerHead;
    private MainProfile mainProfile;
    private ActionProfile actionProfile;
    private ReasonsProfile reasonsProfile;
    private ViolationsProfile violationsProfile;
    private GamemodeSwitcherProfile gamemodeSwitcherProfile;
    private HomeListProfile homeListProfile;
    private StorageProfile storageProfile;

    public ProfileMenu(Player player, Player target) {
        this.player = player;
        this.target = target;
        profile = ActiveCraftCore.getProfile(target);
        //playerhead
        playerHead = new GuiPlayerHead(4);
        playerHead.setOwner(target);
        playerHead.setLore(ChatColor.GRAY + "aka " + profile.getNickname(), ChatColor.AQUA + profile.getUuid().toString());
        playerHead.setDisplayName(ChatColor.GOLD + target.getName());

        warnManager = new WarnManager(target);
        mainProfile = new MainProfile(this);
        actionProfile = new ActionProfile(this);
        reasonsProfile = new ReasonsProfile(this);
        violationsProfile = new ViolationsProfile(this);
        gamemodeSwitcherProfile = new GamemodeSwitcherProfile(this);
        homeListProfile = new HomeListProfile(this);
        storageProfile = new StorageProfile(this);
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getTarget() {
        return target;
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public WarnManager getWarnManager() {
        return warnManager;
    }

    public void setWarnManager(WarnManager warnManager) {
        this.warnManager = warnManager;
    }

    public GuiPlayerHead getPlayerHead() {
        return playerHead;
    }

    public void setPlayerHead(GuiPlayerHead playerHead) {
        this.playerHead = playerHead;
    }

    public MainProfile getMainProfile() {
        return mainProfile;
    }

    public void setMainProfile(MainProfile mainProfile) {
        this.mainProfile = mainProfile;
    }

    public ActionProfile getActionProfile() {
        return actionProfile;
    }

    public void setActionProfile(ActionProfile actionProfile) {
        this.actionProfile = actionProfile;
    }

    public ReasonsProfile getReasonsProfile() {
        return reasonsProfile;
    }

    public void setReasonsProfile(ReasonsProfile reasonsProfile) {
        this.reasonsProfile = reasonsProfile;
    }

    public ViolationsProfile getViolationsProfile() {
        return violationsProfile;
    }

    public void setViolationsProfile(ViolationsProfile violationsProfile) {
        this.violationsProfile = violationsProfile;
    }

    public GamemodeSwitcherProfile getGamemodeSwitcherProfile() {
        return gamemodeSwitcherProfile;
    }

    public void setGamemodeSwitcherProfile(GamemodeSwitcherProfile gamemodeSwitcherProfile) {
        this.gamemodeSwitcherProfile = gamemodeSwitcherProfile;
    }

    public HomeListProfile getHomeListProfile() {
        return homeListProfile;
    }

    public void setHomeListProfile(HomeListProfile homeListProfile) {
        this.homeListProfile = homeListProfile;
    }

    public void setStorageProfile(StorageProfile storageProfile) {
        this.storageProfile = storageProfile;
    }

    public StorageProfile getStorageProfile() {
        return storageProfile;
    }
}
