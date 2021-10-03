package de.silencio.activecraftcore.profilemenu;

import de.silencio.activecraftcore.gui.GuiCloseItem;
import de.silencio.activecraftcore.gui.GuiCreator;
import de.silencio.activecraftcore.gui.GuiPlayerHead;
import de.silencio.activecraftcore.manager.BanManager;
import de.silencio.activecraftcore.manager.WarnManager;
import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ProfileMenu2 {

    private Player player;
    private Player target;
    private Profile profile;
    private BanManager nameBanManager;
    private BanManager ipBanManager;
    private WarnManager warnManager;
    private GuiPlayerHead playerHead;
    private MainProfile mainProfile;
    private ActionProfile actionProfile;

    public ProfileMenu2(Player player, Player target) {
        this.player = player;
        this.target = target;
        nameBanManager = new BanManager(BanList.Type.NAME);
        ipBanManager = new BanManager(BanList.Type.IP);
        warnManager = new WarnManager(target);
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

    public BanManager getNameBanManager() {
        return nameBanManager;
    }

    public void setNameBanManager(BanManager nameBanManager) {
        this.nameBanManager = nameBanManager;
    }

    public BanManager getIpBanManager() {
        return ipBanManager;
    }

    public void setIpBanManager(BanManager ipBanManager) {
        this.ipBanManager = ipBanManager;
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
}
