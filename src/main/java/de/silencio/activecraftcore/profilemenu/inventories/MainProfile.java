package de.silencio.activecraftcore.profilemenu.inventories;

import de.silencio.activecraftcore.gui.*;
import de.silencio.activecraftcore.manager.BanManager;
import de.silencio.activecraftcore.manager.WarnManager;
import de.silencio.activecraftcore.messages.ProfileMessages;
import de.silencio.activecraftcore.profilemenu.ProfileMenu2;
import de.silencio.activecraftcore.utils.IntegerUtils;
import de.silencio.activecraftcore.utils.ItemBuilder;
import de.silencio.activecraftcore.utils.Profile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

public class MainProfile {

    private Player player;
    private Player target;
    private Profile profile;
    private BanManager nameBanManager;
    private BanManager ipBanManager;
    private WarnManager warnManager;
    private GuiPlayerHead playerHead;
    private GuiCreator guiCreator;

    private GuiItem connectionInfoStack;
    private GuiItem gameStats;
    private GuiItem violationStack;
    private GuiItem violationInfoStack;
    private ItemBuilder activeEffectsBuilder;
    private GuiItem activeEffectsStack;
    private GuiItem gamemodeSwitcherStack;
    private GuiItem actionMenuStack;
    private GuiItem storageMenuStack;
    private GuiItem playerLocationStack;
    private GuiItem playtimeStack;

    private ProfileMenu2 profileMenu;

    public MainProfile(ProfileMenu2 profileMenu) {
        this.profileMenu = profileMenu;
        this.player = profileMenu.getPlayer();
        this.target = profileMenu.getTarget();
        nameBanManager = profileMenu.getNameBanManager();
        ipBanManager = profileMenu.getIpBanManager();
        warnManager = profileMenu.getWarnManager();
        guiCreator = new GuiCreator("main_profile", 6, ProfileMessages.MAINPROFILE_TITLE());
        profile = profileMenu.getProfile();
        renew();
        profileMenu.setMainProfile(this);
    }

    public void renew() {
        // General Settings
        guiCreator.fillBackground(true);
        guiCreator.setCloseItem(new GuiCloseItem(49));
        playerHead = profileMenu.getPlayerHead();

        // Player Items
        GuiItem slotEmpty = new GuiItem(Material.RED_STAINED_GLASS_PANE)
                .setDisplayName(ProfileMessages.MAINPROFILE_EMPTY_SLOT());

        GuiItem offHand = new GuiItem(target.getInventory().getItemInOffHand());
        GuiItem mainHand = new GuiItem(target.getInventory().getItemInMainHand());
        if (mainHand.getType() != Material.AIR) {
            guiCreator.setItemInSlot(mainHand, 29);
        } else guiCreator.setItemInSlot(slotEmpty, 29);
        if (offHand.getType() != Material.AIR) {
            guiCreator.setItemInSlot(offHand, 20);
        } else guiCreator.setItemInSlot(slotEmpty, 20);
        if (target.getInventory().getHelmet() != null) {
            GuiItem helmet = new GuiItem(target.getInventory().getHelmet());
            guiCreator.setItemInSlot(helmet, 10);
        } else guiCreator.setItemInSlot(slotEmpty, 10);
        if (target.getInventory().getChestplate() != null) {
            GuiItem chestplate = new GuiItem(target.getInventory().getChestplate());
            guiCreator.setItemInSlot(chestplate, 19);
        } else guiCreator.setItemInSlot(slotEmpty, 19);
        if (target.getInventory().getLeggings() != null) {
            GuiItem leggins = new GuiItem(target.getInventory().getLeggings());
            guiCreator.setItemInSlot(leggins, 28);
        } else guiCreator.setItemInSlot(slotEmpty, 28);
        if (target.getInventory().getBoots() != null) {
            GuiItem boots = new GuiItem(target.getInventory().getBoots());
            guiCreator.setItemInSlot(boots, 37);
        } else guiCreator.setItemInSlot(slotEmpty, 37);

        // Player Connection Information
        if (player.hasPermission("activecraft.connection.info")) {
            connectionInfoStack = new GuiItem(Material.STRUCTURE_VOID)
                    .setDisplayName(ProfileMessages.MAINPROFILE_CONNECTION_TITLE())
                    .setLore(ProfileMessages.MAINPROFILE_CONNECTION_IP(target.getAddress().getAddress().toString().replace("/", "")),
                            ProfileMessages.MAINPROFILE_CONNECTION_PORT(target.getAddress().getPort()),
                            ProfileMessages.MAINPROFILE_CONNECTION_PING(target.getPing()));
            guiCreator.setItemInSlot(connectionInfoStack, 21);
        } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 21);

        //player Stats
        if (player.hasPermission("activecraft.stats.info")) {
            gameStats = new GuiItem(Material.GRASS_BLOCK)
                    .setDisplayName(ProfileMessages.MAINPROFILE_PLAYER_TITLE());
            switch (target.getGameMode()) {
                case CREATIVE:
                    gameStats.setLore(ProfileMessages.MAINPROFILE_PLAYER_HEALTH(target.getHealth()),
                            ProfileMessages.MAINPROFILE_PLAYER_FOOD(target.getFoodLevel()),
                            ProfileMessages.MAINPROFILE_PLAYER_EXP(target.getExp()),
                            ProfileMessages.MAINPROFILE_GAMEMODE("Creative"));
                    break;
                case SURVIVAL:
                    gameStats.setLore(ProfileMessages.MAINPROFILE_PLAYER_HEALTH(target.getHealth()),
                            ProfileMessages.MAINPROFILE_PLAYER_FOOD(target.getFoodLevel()),
                            ProfileMessages.MAINPROFILE_PLAYER_EXP(target.getExp()),
                            ProfileMessages.MAINPROFILE_GAMEMODE("Survival"));
                    break;
                case SPECTATOR:
                    gameStats.setLore(ProfileMessages.MAINPROFILE_PLAYER_HEALTH(target.getHealth()),
                            ProfileMessages.MAINPROFILE_PLAYER_FOOD(target.getFoodLevel()),
                            ProfileMessages.MAINPROFILE_PLAYER_EXP(target.getExp()),
                            ProfileMessages.MAINPROFILE_GAMEMODE("Spectator"));
                    break;
                case ADVENTURE:
                    gameStats.setLore(ProfileMessages.MAINPROFILE_PLAYER_HEALTH(target.getHealth()),
                            ProfileMessages.MAINPROFILE_PLAYER_FOOD(target.getFoodLevel()),
                            ProfileMessages.MAINPROFILE_PLAYER_EXP(target.getExp()),
                            ProfileMessages.MAINPROFILE_GAMEMODE("Adventure"));
                    break;
            }
            guiCreator.setItemInSlot(gameStats, 12);
        } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 12);

        // Player Violations Information
        if (player.hasPermission("activecraft.violations.info")) {
            violationInfoStack = new GuiItem(Material.COMMAND_BLOCK)
                    .setDisplayName(ProfileMessages.MAINPROFILE_VIOLATIONS_TITLE())
                    .setLore(ProfileMessages.MAINPROFILE_VIOLATIONS_BANS(profile.getBans()),
                            ProfileMessages.MAINPROFILE_VIOLATIONS_IP_BANS(profile.getIpBans()),
                            ProfileMessages.MAINPROFILE_VIOLATIONS_WARNS(profile.getWarns()),
                            ProfileMessages.MAINPROFILE_VIOLATIONS_MUTES(profile.getMutes()));
            guiCreator.setItemInSlot(violationInfoStack, 30);
        } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 30);

        activeEffectsBuilder = new ItemBuilder(Material.POTION)
                .displayname(ProfileMessages.MAINPROFILE_ACTIVE_EFFECTS());
        for (PotionEffect effect : target.getActivePotionEffects()) {
            activeEffectsBuilder.lore(effect.getType().getName() + "; " + effect.getAmplifier() + "; " + IntegerUtils.shortInteger(effect.getDuration() / 20));
        }
        activeEffectsStack = new GuiItem(activeEffectsBuilder.build());
        PotionMeta potionMeta = (PotionMeta) activeEffectsStack.getItemMeta();
        potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        activeEffectsStack.setItemMeta(potionMeta);
        if (player.hasPermission("activecraft.activeeffects")) {
            guiCreator.setItemInSlot(activeEffectsStack, 14);
        } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 14);

        // Player Location Information
        playerLocationStack = new GuiItem(Material.REDSTONE_TORCH)
                .setDisplayName(ProfileMessages.MAINPROFILE_PLAYER_LOCATION())
                .setLore(ChatColor.DARK_AQUA + target.getWorld().getName() + "; " + target.getLocation().getBlockX() + ", "
                        + target.getLocation().getBlockY() + ", " + target.getLocation().getBlockZ());
        if (player.hasPermission("activecraft.location")) {
            guiCreator.setItemInSlot(playerLocationStack, 16);
        } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 16);

        //Player Playtime
        playtimeStack = new GuiItem(Material.CLOCK)
                .setDisplayName(ProfileMessages.MAINPROFILE_PLAYTIME())
                .setLore(ProfileMessages.MAINPROFILE_PLAYTIME_LORE(profile.getPlaytimeHours(), profile.getPlaytimeMinutes()));
        if (player.hasPermission("activecraft.playtime")) {
            guiCreator.setItemInSlot(playtimeStack, 15);
        } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 15);


        // Other Menus
        // Player Punishment
            violationStack = new GuiItem(Material.COMMAND_BLOCK)
                  .setDisplayName(ProfileMessages.MAINPROFILE_VIOLATIONS_GUI());
            guiCreator.setItemInSlot(violationStack, 42);

        // Player Gamemode Switcher
        if (player.hasPermission("activecraft.punishment.info")) {
            gamemodeSwitcherStack = new GuiItem(Material.GRASS_BLOCK)
                    .setDisplayName(ProfileMessages.MAINPROFILE_GAMEMODE_SWITCHER_GUI());
            guiCreator.setItemInSlot(gamemodeSwitcherStack, 32);
        } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 32);

        // Player Storage Menu
        if (player.hasPermission("activecraft.punishment.info")) {
            storageMenuStack = new GuiItem(Material.CHEST)
                    .setDisplayName(ProfileMessages.MAINPROFILE_STORAGE_GUI());
            guiCreator.setItemInSlot(storageMenuStack, 33);
        } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 33);

        // Player Actions Menu
        if (player.hasPermission("activecraft.punishment.info")) {
            actionMenuStack = new GuiItem(Material.LEVER)
                    .setDisplayName(ProfileMessages.MAINPROFILE_ACTION_GUI());
            guiCreator.setItemInSlot(actionMenuStack, 34);
        } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 34);
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

    public GuiItem getConnectionInfoStack() {
        return connectionInfoStack;
    }

    public void setConnectionInfoStack(GuiItem connectionInfoStack) {
        this.connectionInfoStack = connectionInfoStack;
    }

    public GuiItem getGameStats() {
        return gameStats;
    }

    public void setGameStats(GuiItem gameStats) {
        this.gameStats = gameStats;
    }

    public GuiItem getViolationStack() {
        return violationStack;
    }

    public void setViolationStack(GuiItem punishmentStack) {
        this.violationStack = punishmentStack;
    }

    public GuiItem getViolationInfoStack() {
        return violationInfoStack;
    }

    public void setViolationInfoStack(GuiItem violationInfoStack) {
        this.violationInfoStack = violationInfoStack;
    }

    public ItemBuilder getActiveEffectsBuilder() {
        return activeEffectsBuilder;
    }

    public void setActiveEffectsBuilder(ItemBuilder activeEffectsBuilder) {
        this.activeEffectsBuilder = activeEffectsBuilder;
    }

    public GuiItem getActiveEffectsStack() {
        return activeEffectsStack;
    }

    public void setActiveEffectsStack(GuiItem activeEffectsStack) {
        this.activeEffectsStack = activeEffectsStack;
    }

    public GuiItem getGamemodeSwitcherStack() {
        return gamemodeSwitcherStack;
    }

    public void setGamemodeSwitcherStack(GuiItem gamemodeSwitcherStack) {
        this.gamemodeSwitcherStack = gamemodeSwitcherStack;
    }

    public GuiItem getActionMenuStack() {
        return actionMenuStack;
    }

    public void setActionMenuStack(GuiItem actionMenuStack) {
        this.actionMenuStack = actionMenuStack;
    }

    public GuiItem getStorageMenuStack() {
        return storageMenuStack;
    }

    public void setStorageMenuStack(GuiItem storageMenuStack) {
        this.storageMenuStack = storageMenuStack;
    }

    public GuiItem getPlayerLocationStack() {
        return playerLocationStack;
    }

    public void setPlayerLocationStack(GuiItem playerLocationStack) {
        this.playerLocationStack = playerLocationStack;
    }

    public GuiItem getPlaytimeStack() {
        return playtimeStack;
    }

    public void setPlaytimeStack(GuiItem playtimeStack) {
        this.playtimeStack = playtimeStack;
    }
}