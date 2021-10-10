package de.silencio.activecraftcore.profilemenu.inventories;

import de.silencio.activecraftcore.gui.*;
import de.silencio.activecraftcore.manager.BanManager;
import de.silencio.activecraftcore.manager.WarnManager;
import de.silencio.activecraftcore.profilemenu.ProfileMenu2;
import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeListProfile {

    private ProfileMenu2 profileMenu;
    private Player player;
    private Player target;
    private Profile profile;
    private BanManager nameBanManager;
    private BanManager ipBanManager;
    private WarnManager warnManager;
    private GuiPlayerHead playerHead;

    private GuiItem nextArrow;
    private GuiItem lastArrow;
    private int currentPage;

    private List<GuiCreator> pages;

    public HomeListProfile(ProfileMenu2 profileMenu) {
        this.profileMenu = profileMenu;
        this.player = profileMenu.getPlayer();
        this.target = profileMenu.getTarget();
        nameBanManager = profileMenu.getNameBanManager();
        ipBanManager = profileMenu.getIpBanManager();
        warnManager = profileMenu.getWarnManager();
        profile = profileMenu.getProfile();
        pages = new ArrayList<>();
        renew();
        profileMenu.setHomeListProfile(this);
    }

    public void renew() {

        currentPage = 0;
        pages.clear();
        profile.refresh();

        HashMap<String, Location> homes = profile.getHomeList();

        while (homes.size() > 0) {

            List<String> toBeRemoved = new ArrayList<>();

            GuiCreator guiCreator = new GuiCreator("home_list_profile", 6, "Homes");

            guiCreator.fillBackground(true);
            guiCreator.setCloseItem(new GuiCloseItem(49));
            guiCreator.setPlayerHead(profileMenu.getPlayerHead());
            guiCreator.setBackItem(new GuiBackItem(48));

            int i = 10;
            for (String homeName : homes.keySet()) {
                Location loc = homes.get(homeName);
                World.Environment environment = loc.getWorld().getEnvironment();
                switch (environment.getId()) {
                    case 0:
                        guiCreator.setItemInSlot(new GuiItem(Material.GRASS_BLOCK).setDisplayName(homeName).setLore(loc.getWorld().getName() + ", "
                                + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ()), i);
                        break;
                    case 1:
                        guiCreator.setItemInSlot(new GuiItem(Material.END_STONE).setDisplayName(homeName).setLore(loc.getWorld().getName() + ", "
                                + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ()), i);
                        break;
                    case -1:
                        guiCreator.setItemInSlot(new GuiItem(Material.NETHERRACK).setDisplayName(homeName).setLore(loc.getWorld().getName() + ", "
                                + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ()), i);
                        break;
                }
                toBeRemoved.add(homeName);
                if (i % 9 != 7 && i % 9 != 0) {
                    i++;
                } else i += 3;
                if (i >= 44) break;
            }
            for (String s : toBeRemoved) {
                homes.remove(s);
            }
            pages.add(guiCreator);
        }

        nextArrow = new GuiItem(Material.SPECTRAL_ARROW).setDisplayName(ChatColor.GOLD + "Go to next page");

        lastArrow = new GuiItem(Material.SPECTRAL_ARROW).setDisplayName(ChatColor.GOLD + "Go to last page");

        if (pages.size() == 2) {
            pages.get(0).setItemInSlot(nextArrow,53);
            pages.get(1).setItemInSlot(lastArrow,45);
        } else if (pages.size() >= 3) {
            for (int pos = 0; pos < pages.size(); pos++) {
                if (pos == 0) {
                    pages.get(pos).setItemInSlot(nextArrow, 53);
                } else if (pos == pages.size() - 1) {
                    pages.get(pos).setItemInSlot(lastArrow, 45);
                } else {
                    pages.get(pos).setItemInSlot(nextArrow, 53);
                    pages.get(pos).setItemInSlot(lastArrow, 45);
                }
            }
        }
    }

    public GuiPlayerHead getPlayerHead() {
        return playerHead;
    }

    public void setPlayerHead(GuiPlayerHead playerHead) {
        this.playerHead = playerHead;
    }

    public List<GuiCreator> getPages() {
        return pages;
    }

    public void setPages(List<GuiCreator> pages) {
        this.pages = pages;
    }

    public GuiCreator getPage(int index) {
        return pages.get(index);
    }

    public GuiItem getNextArrow() {
        return nextArrow;
    }

    public void setNextArrow(GuiItem nextArrow) {
        this.nextArrow = nextArrow;
    }

    public GuiItem getLastArrow() {
        return lastArrow;
    }

    public void setLastArrow(GuiItem lastArrow) {
        this.lastArrow = lastArrow;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
