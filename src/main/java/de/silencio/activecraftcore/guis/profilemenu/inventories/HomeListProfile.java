package de.silencio.activecraftcore.guis.profilemenu.inventories;

import de.silencio.activecraftcore.guicreator.*;
import de.silencio.activecraftcore.guis.ProfileMenu;
import de.silencio.activecraftcore.manager.WarnManager;
import de.silencio.activecraftcore.messages.ProfileMessages;
import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeListProfile {

    private ProfileMenu profileMenu;
    private Player player;
    private Player target;
    private Profile profile;
    private WarnManager warnManager;
    private GuiPlayerHead playerHead;

    private GuiItem nextArrow;
    private GuiItem lastArrow;
    private int currentPage;

    private List<GuiCreator> pages;

    public HomeListProfile(ProfileMenu profileMenu) {
        this.profileMenu = profileMenu;
        this.player = profileMenu.getPlayer();
        this.target = profileMenu.getTarget();
        warnManager = profileMenu.getWarnManager();
        profile = profileMenu.getProfile();
        pages = new ArrayList<>();
        renew();
        profileMenu.setHomeListProfile(this);
    }

    private class Page extends GuiCreator {

        public Page() {
            super("home_list_profile", 6, "Homes");
        }

        @Override
        public void refresh() {

        }
    }

    public void renew() {

        currentPage = 0;
        pages.clear();
        profile.refresh();
        profileMenu.getProfile().refresh();

        HashMap<String, Location> homes = profile.getHomeList();

        if (homes.size() == 0) {
            Page page = new Page();
            page.fillBackground(true);
            page.setCloseItem(new GuiCloseItem(49));
            page.setPlayerHead(profileMenu.getPlayerHead());
            page.setBackItem(new GuiBackItem(48));

            page.setItemInSlot(new GuiItem(Material.BARRIER).setDisplayName(ProfileMessages.HOMELIST_NO_HOMES(target)), 22);

            pages.add(page);
        }

        while (homes.size() > 0) {

            List<String> toBeRemoved = new ArrayList<>();

            Page page = new Page();

            page.fillBackground(true);
            page.setCloseItem(new GuiCloseItem(49));
            page.setPlayerHead(profileMenu.getPlayerHead());
            page.setBackItem(new GuiBackItem(48));

            int i = 10;
            for (String homeName : homes.keySet()) {
                Location loc = homes.get(homeName);
                World.Environment environment = loc.getWorld().getEnvironment();
                switch (environment.getId()) {
                    case 0 -> page.setItemInSlot(new GuiItem(Material.GRASS_BLOCK).setDisplayName(homeName).setLore(
                            ChatColor.AQUA + loc.getWorld().getName() + ChatColor.GOLD
                                    + ", " + ChatColor.AQUA + loc.getBlockX() + ChatColor.GOLD
                                    + ", " + ChatColor.AQUA + loc.getBlockY() + ChatColor.GOLD
                                    + ", " + ChatColor.AQUA + loc.getBlockZ()), i);
                    case 1 -> page.setItemInSlot(new GuiItem(Material.END_STONE).setDisplayName(homeName).setLore(
                            ChatColor.AQUA + loc.getWorld().getName() + ChatColor.GOLD
                                    + ", " + ChatColor.AQUA + loc.getBlockX() + ChatColor.GOLD
                                    + ", " + ChatColor.AQUA + loc.getBlockY() + ChatColor.GOLD
                                    + ", " + ChatColor.AQUA + loc.getBlockZ()), i);
                    case -1 -> page.setItemInSlot(new GuiItem(Material.NETHERRACK).setDisplayName(homeName).setLore(
                            ChatColor.AQUA + loc.getWorld().getName() + ChatColor.GOLD
                                    + ", " + ChatColor.AQUA + loc.getBlockX() + ChatColor.GOLD
                                    + ", " + ChatColor.AQUA + loc.getBlockY() + ChatColor.GOLD
                                    + ", " + ChatColor.AQUA + loc.getBlockZ()), i);
                }
                toBeRemoved.add(homeName);
                if (i % 9 != 7 && i % 9 != 0) i++;
                else i += 3;
                if (i >= 44) break;
            }
            for (String s : toBeRemoved)
                homes.remove(s);

            pages.add(page);
        }

        nextArrow = new GuiItem(Material.SPECTRAL_ARROW).setDisplayName(ProfileMessages.HOMELIST_NEXT_PAGE());

        lastArrow = new GuiItem(Material.SPECTRAL_ARROW).setDisplayName(ProfileMessages.HOMELIST_PREVIOUS_PAGE());

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
