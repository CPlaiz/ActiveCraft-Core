package de.silencio.activecraftcore.profilemenu.inventories;

import de.silencio.activecraftcore.gui.*;
import de.silencio.activecraftcore.manager.BanManager;
import de.silencio.activecraftcore.manager.WarnManager;
import de.silencio.activecraftcore.profilemenu.ProfileMenu2;
import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.PotionMeta;

public class ActionProfile {

    private Player player;
    private Player target;
    private Profile profile;
    private BanManager nameBanManager;
    private BanManager ipBanManager;
    private WarnManager warnManager;
    private GuiPlayerHead playerHead;
    private GuiCreator guiCreator;

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

    private ProfileMenu2 profileMenu;

    public ActionProfile(ProfileMenu2 profileMenu) {
        this.profileMenu = profileMenu;
        this.player = profileMenu.getPlayer();
        this.target = profileMenu.getTarget();
        nameBanManager = profileMenu.getNameBanManager();
        ipBanManager = profileMenu.getIpBanManager();
        warnManager = profileMenu.getWarnManager();
        guiCreator = new GuiCreator("action_profile", 6, ProfileMessages.ACTION_GUI_TITLE());
        profile = profileMenu.getProfile();
        renew();
        profileMenu.setActionProfile(this);
    }

    public void renew() {
        guiCreator.fillBackground(true);
        guiCreator.setCloseItem(new GuiCloseItem(49));
        guiCreator.setBackItem(new GuiBackItem(48));
        guiCreator.setPlayerHead(profileMenu.getPlayerHead());

        {
            vanishItem = new GuiItem(Material.GLASS_BOTTLE);
            if (profile.isVanished()) {
                vanishItem.setDisplayName(ChatColor.GOLD + "Unvanish " + ChatColor.AQUA + target.getName())
                        .setLore(ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY + " is currently vanished.");
            } else vanishItem.setDisplayName(ChatColor.GOLD + "Vanish " + ChatColor.AQUA + target.getName())
                    .setLore(ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY + " is not vanished.");

            if (player.hasPermission("activecraft.vanish.others")) {
                guiCreator.setItemInSlot(vanishItem, 28);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 28);
        }

        {
            godModeItem = new GuiItem(Material.ENCHANTED_GOLDEN_APPLE);
            if (profile.isGodmode()) {
                godModeItem.setDisplayName(ChatColor.GOLD + "Disable God Mode for " + ChatColor.AQUA + target.getName())
                        .setLore(ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY + " is currently in God Mode.");
            } else godModeItem.setDisplayName(ChatColor.GOLD + "Enable God Mode for " + ChatColor.AQUA + target.getName())
                        .setLore(ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY + " is not in God Mode.");

            if (player.hasPermission("activecraft.god.others")) {
                guiCreator.setItemInSlot(godModeItem, 10);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 10);
        }

        {
            flyItem = new GuiItem(Material.FEATHER);
            if (profile.isFly()) {
                flyItem.setDisplayName(ChatColor.GOLD + "Disable Flight for " + ChatColor.AQUA + target.getName())
                        .setLore(ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY + " is currently in flight mode.");
            } else flyItem.setDisplayName(ChatColor.GOLD + "Enable Flight for " + ChatColor.AQUA + target.getName())
                    .setLore(ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY + " is not in flight mode.");

            if (player.hasPermission("activecraft.fly.others")) {
                guiCreator.setItemInSlot(flyItem, 19);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 19);
        }

        {
            clearInvItem = new GuiItem(Material.STRUCTURE_VOID)
                    .setDisplayName(ChatColor.GOLD + "Clear " + ChatColor.AQUA + target.getName() + "'s " + ChatColor.GOLD + "inventory");
            if (player.hasPermission("activecraft.clearinv")) {
                guiCreator.setItemInSlot(clearInvItem, 30);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 30);
        }

        {
            tpToPlayerItem = new GuiItem(Material.ENDER_PEARL)
                    .setDisplayName(ChatColor.GOLD + "Teleport to " + ChatColor.AQUA + target.getName());
            if (player.hasPermission("activecraft.tp")) {
                guiCreator.setItemInSlot(tpToPlayerItem, 23);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 23);
        }
        {
            tpherePlayerItem = new GuiItem(Material.ENDER_EYE)
                    .setDisplayName(ChatColor.GOLD + "Teleport " + ChatColor.AQUA + target.getName() + ChatColor.GOLD + " to you");
            if (player.hasPermission("activecraft.tphere")) {
                guiCreator.setItemInSlot(tpherePlayerItem, 32);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 32);
        }
        {
            homeItem = new GuiItem(Material.RED_BED)
                    .setDisplayName(ChatColor.GOLD + "Teleport to " + ChatColor.AQUA + target.getName() + "'s" + ChatColor.GOLD + " Home");
            if (player.hasPermission("activecraft.home.others")) {
                guiCreator.setItemInSlot(homeItem, 14);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 14);
        }
        {
            healItem = new GuiItem(Material.POTION)
                    .setDisplayName(ChatColor.GOLD + "Heal " + ChatColor.AQUA + target.getName());
            PotionMeta potionMeta = (PotionMeta) healItem.getItemMeta();
            potionMeta.setColor(Color.FUCHSIA);
            potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            healItem.setItemMeta(potionMeta);
            if (player.hasPermission("activecraft.heal")) {
                guiCreator.setItemInSlot(healItem, 21);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 21);
        }
        {
            feedItem = new GuiItem(Material.COOKED_BEEF)
                    .setDisplayName(ChatColor.GOLD + "Feed " + ChatColor.AQUA + target.getName());
            if (player.hasPermission("activecraft.feed")) {
                guiCreator.setItemInSlot(feedItem, 12);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 12);
        }
        {
            strikeItem = new GuiItem(Material.LIGHTNING_ROD)
                    .setDisplayName(ChatColor.GOLD + "Strike " + ChatColor.AQUA + target.getName());
            if (player.hasPermission("activecraft.strike")) {
                guiCreator.setItemInSlot(strikeItem, 16);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 16);
        }
        {
            explodeItem = new GuiItem(Material.TNT)
                    .setDisplayName(ChatColor.GOLD + "Blow " + ChatColor.AQUA + target.getName() + ChatColor.GOLD + " up");
            if (player.hasPermission("activecraft.explode")) {
                guiCreator.setItemInSlot(explodeItem, 25);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 25);
        }
        {
            killItem = new GuiItem(Material.SKELETON_SKULL)
                    .setDisplayName(ChatColor.GOLD + "Kill " + ChatColor.AQUA + target.getName());
            if (player.hasPermission("activecraft.kill")) {
                guiCreator.setItemInSlot(killItem, 34);
            } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 34);
        }
    }

    public GuiCreator getGuiCreator() {
        return guiCreator;
    }

    public void setGuiCreator(GuiCreator guiCreator) {
        this.guiCreator = guiCreator;
    }

    public GuiPlayerHead getPlayerHead() {
        return playerHead;
    }

    public void setPlayerHead(GuiPlayerHead playerHead) {
        this.playerHead = playerHead;
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