package de.silencio.activecraftcore.guis.profilemenu.inventories;

import de.silencio.activecraftcore.guicreator.*;
import de.silencio.activecraftcore.guis.ProfileMenu;
import de.silencio.activecraftcore.manager.WarnManager;
import de.silencio.activecraftcore.messages.ProfileMessages;
import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.PotionMeta;

public class ActionProfile extends GuiCreator {

    private Player player;
    private Player target;
    private Profile profile;
    private WarnManager warnManager;
    private GuiPlayerHead playerHead;

    private GuiItem tpToPlayerItem;
    private GuiItem tpherePlayerItem;
    private GuiItem clearInvItem;
    private GuiItem flyItem;
    private GuiItem godModeItem;
    private GuiItem vanishItem;
    private GuiItem feedItem;
    private GuiItem healItem;
    private GuiItem homeItem;
    private GuiItem strikeItem;
    private GuiItem suicideItem;
    private GuiItem killItem;
    private GuiItem explodeItem;

    private ProfileMenu profileMenu;

    public ActionProfile(ProfileMenu profileMenu) {
        super("action_profile", 6, ProfileMessages.ACTION_GUI_TITLE());
        this.profileMenu = profileMenu;
        this.player = profileMenu.getPlayer();
        this.target = profileMenu.getTarget();
        warnManager = profileMenu.getWarnManager();
        profile = profileMenu.getProfile();
        refresh();
        profileMenu.setActionProfile(this);
    }

    @Override
    public void refresh() {
        profileMenu.getProfile().refresh();
        profile = profileMenu.getProfile();

        fillBackground(true);
        setCloseItem(new GuiCloseItem(49));
        setBackItem(new GuiBackItem(48));
        setPlayerHead(profileMenu.getPlayerHead());

        {
            vanishItem = new GuiItem(Material.GLASS_BOTTLE);
            if (profile.isVanished()) {
                vanishItem.setDisplayName(ProfileMessages.ACTION_GUI_VANISH_UNVANISH(target))
                        .setLore(ProfileMessages.ACTION_GUI_VANISH_UNVANISH_LORE(target));
            } else vanishItem.setDisplayName(ProfileMessages.ACTION_GUI_VANISH_VANISH(target))
                    .setLore(ProfileMessages.ACTION_GUI_VANISH_VANISH_LORE(target));

            if (player.hasPermission("activecraft.vanish.others")) {
                setItemInSlot(vanishItem, 28);
            } else setItemInSlot(new GuiNoPermissionItem(), 28);
        }

        {
            godModeItem = new GuiItem(Material.ENCHANTED_GOLDEN_APPLE);
            if (profile.isGodmode()) {
                godModeItem.setDisplayName(ProfileMessages.ACTION_GUI_GOD_DISABLE(target))
                        .setLore(ProfileMessages.ACTION_GUI_GOD_DISABLE_LORE(target));
            } else godModeItem.setDisplayName(ProfileMessages.ACTION_GUI_GOD_ENABLE(target))
                        .setLore(ProfileMessages.ACTION_GUI_GOD_ENABLE_LORE(target));

            if (player.hasPermission("activecraft.god.others")) {
                setItemInSlot(godModeItem, 10);
            } else setItemInSlot(new GuiNoPermissionItem(), 10);
        }

        {
            flyItem = new GuiItem(Material.FEATHER);
            if (profile.canFly()) {
                flyItem.setDisplayName(ProfileMessages.ACTION_GUI_FLY_DISABLE(target))
                        .setLore(ProfileMessages.ACTION_GUI_FLY_DISABLE_LORE(target));
            } else flyItem.setDisplayName(ProfileMessages.ACTION_GUI_FLY_ENABLE(target))
                    .setLore(ProfileMessages.ACTION_GUI_FLY_ENABLE_LORE(target));

            if (player.hasPermission("activecraft.fly.others")) {
                setItemInSlot(flyItem, 19);
            } else setItemInSlot(new GuiNoPermissionItem(), 19);
        }

        {
            clearInvItem = new GuiItem(Material.STRUCTURE_VOID)
                    .setDisplayName(ProfileMessages.ACTION_GUI_CLEAR(target));
            if (player.hasPermission("activecraft.clearinv")) {
                setItemInSlot(clearInvItem, 30);
            } else setItemInSlot(new GuiNoPermissionItem(), 30);
        }

        {
            tpToPlayerItem = new GuiItem(Material.ENDER_PEARL)
                    .setDisplayName(ProfileMessages.ACTION_GUI_TELEPORT_THERE(target));
            if (player.hasPermission("activecraft.tp")) {
                setItemInSlot(tpToPlayerItem, 23);
            } else setItemInSlot(new GuiNoPermissionItem(), 23);
        }
        {
            tpherePlayerItem = new GuiItem(Material.ENDER_EYE)
                    .setDisplayName(ProfileMessages.ACTION_GUI_TELEPORT_HERE(target));
            if (player.hasPermission("activecraft.tphere")) {
                setItemInSlot(tpherePlayerItem, 32);
            } else setItemInSlot(new GuiNoPermissionItem(), 32);
        }
        {
            homeItem = new GuiItem(Material.RED_BED)
                    .setDisplayName(ProfileMessages.ACTION_GUI_HOMES(target));
            if (player.hasPermission("activecraft.home.others")) {
                setItemInSlot(homeItem, 14);
            } else setItemInSlot(new GuiNoPermissionItem(), 14);
        }
        {
            healItem = new GuiItem(Material.POTION)
                    .setDisplayName(ProfileMessages.ACTION_GUI_HEAL(target));
            PotionMeta potionMeta = (PotionMeta) healItem.getItemMeta();
            potionMeta.setColor(Color.FUCHSIA);
            potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            healItem.setItemMeta(potionMeta);
            if (player.hasPermission("activecraft.heal.others")) {
                setItemInSlot(healItem, 21);
            } else setItemInSlot(new GuiNoPermissionItem(), 21);
        }
        {
            feedItem = new GuiItem(Material.COOKED_BEEF)
                    .setDisplayName(ProfileMessages.ACTION_GUI_FEED(target));
            if (player.hasPermission("activecraft.feed.others")) {
                setItemInSlot(feedItem, 12);
            } else setItemInSlot(new GuiNoPermissionItem(), 12);
        }
        {
            strikeItem = new GuiItem(Material.LIGHTNING_ROD)
                    .setDisplayName(ProfileMessages.ACTION_GUI_STRIKE(target));
            if (player.hasPermission("activecraft.strike.others")) {
                setItemInSlot(strikeItem, 16);
            } else setItemInSlot(new GuiNoPermissionItem(), 16);
        }
        {
            explodeItem = new GuiItem(Material.TNT)
                    .setDisplayName(ProfileMessages.ACTION_GUI_EXPLODE(target));
            if (player.hasPermission("activecraft.explode.others")) {
                setItemInSlot(explodeItem, 25);
            } else setItemInSlot(new GuiNoPermissionItem(), 25);
        }
        {
            killItem = new GuiItem(Material.SKELETON_SKULL)
                    .setDisplayName(ProfileMessages.ACTION_GUI_KILL(target));
            if (player.hasPermission("activecraft.kill")) {
                setItemInSlot(killItem, 34);
            } else setItemInSlot(new GuiNoPermissionItem(), 34);
        }
    }

    public GuiPlayerHead getPlayerHead() {
        return playerHead;
    }

    public GuiItem getTpToPlayerItem() {
        return tpToPlayerItem;
    }

    public void setTpToPlayerItem(GuiItem tpToPlayerItem) {
        this.tpToPlayerItem = tpToPlayerItem;
    }

    public GuiItem getTpherePlayerItem() {
        return tpherePlayerItem;
    }

    public void setTpherePlayerItem(GuiItem tpherePlayerItem) {
        this.tpherePlayerItem = tpherePlayerItem;
    }

    public GuiItem getClearInvItem() {
        return clearInvItem;
    }

    public void setClearInvItem(GuiItem clearInvItem) {
        this.clearInvItem = clearInvItem;
    }

    public GuiItem getFlyItem() {
        return flyItem;
    }

    public void setFlyItem(GuiItem flyItem) {
        this.flyItem = flyItem;
    }

    public GuiItem getGodModeItem() {
        return godModeItem;
    }

    public void setGodModeItem(GuiItem godModeItem) {
        this.godModeItem = godModeItem;
    }

    public GuiItem getVanishItem() {
        return vanishItem;
    }

    public void setVanishItem(GuiItem vanishItem) {
        this.vanishItem = vanishItem;
    }

    public GuiItem getFeedItem() {
        return feedItem;
    }

    public void setFeedItem(GuiItem feedItem) {
        this.feedItem = feedItem;
    }

    public GuiItem getHealItem() {
        return healItem;
    }

    public void setHealItem(GuiItem healItem) {
        this.healItem = healItem;
    }

    public GuiItem getHomeItem() {
        return homeItem;
    }

    public void setHomeItem(GuiItem homeItem) {
        this.homeItem = homeItem;
    }

    public GuiItem getStrikeItem() {
        return strikeItem;
    }

    public void setStrikeItem(GuiItem strikeItem) {
        this.strikeItem = strikeItem;
    }

    public GuiItem getSuicideItem() {
        return suicideItem;
    }

    public void setSuicideItem(GuiItem suicideItem) {
        this.suicideItem = suicideItem;
    }

    public GuiItem getKillItem() {
        return killItem;
    }

    public void setKillItem(GuiItem killItem) {
        this.killItem = killItem;
    }

    public GuiItem getExplodeItem() {
        return explodeItem;
    }

    public void setExplodeItem(GuiItem explodeItem) {
        this.explodeItem = explodeItem;
    }
}