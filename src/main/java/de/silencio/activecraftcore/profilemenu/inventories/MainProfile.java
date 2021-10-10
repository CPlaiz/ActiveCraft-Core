package de.silencio.activecraftcore.profilemenu.inventories;

import de.silencio.activecraftcore.gui.*;
import de.silencio.activecraftcore.manager.BanManager;
import de.silencio.activecraftcore.manager.WarnManager;
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
        guiCreator = new GuiCreator("main_profile", 6, "Profile");
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
                .setDisplayName(ChatColor.RED + "Slot is empty");

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
                    .setDisplayName(ChatColor.GOLD + "Connection Information")
                    .setLore(ChatColor.DARK_AQUA + "IP: " + ChatColor.GRAY + target.getAddress().getAddress().toString().replace("/", ""),
                            ChatColor.DARK_AQUA + "Port: " + ChatColor.GRAY + target.getAddress().getPort(),
                            ChatColor.DARK_AQUA + "Ping: " + ChatColor.GRAY + target.getPing());
            guiCreator.setItemInSlot(connectionInfoStack, 21);
        } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 21);

        //player Stats
        if (player.hasPermission("activecraft.stats.info")) {
            gameStats = new GuiItem(Material.GRASS_BLOCK)
                    .setDisplayName(ChatColor.GOLD + "Player Information")
                    .setLore(ChatColor.DARK_AQUA + "Health: " + ChatColor.GRAY + target.getHealth(),
                            ChatColor.DARK_AQUA + "Food: " + ChatColor.GRAY + target.getFoodLevel(),
                            ChatColor.DARK_AQUA + "Exp: " + ChatColor.GRAY + target.getLevel());
            switch (target.getGameMode()) {
                case CREATIVE:
                    gameStats.setLore(ChatColor.DARK_AQUA + "Gamemode: " + ChatColor.GRAY + "Creative");
                    break;
                case SURVIVAL:
                    gameStats.setLore(ChatColor.DARK_AQUA + "Gamemode: " + ChatColor.GRAY + "Survival");
                    break;
                case SPECTATOR:
                    gameStats.setLore(ChatColor.DARK_AQUA + "Gamemode: " + ChatColor.GRAY + "Spectator");
                    break;
                case ADVENTURE:
                    gameStats.setLore(ChatColor.DARK_AQUA + "Gamemode: " + ChatColor.GRAY + "Adventure");
                    break;
            }
            guiCreator.setItemInSlot(gameStats, 12);
        } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 12);

        // Player Violations Information
        if (player.hasPermission("activecraft.violations.info")) {
            violationInfoStack = new GuiItem(Material.COMMAND_BLOCK)
                    .setDisplayName(ChatColor.GOLD + "Violations")
                    .setLore(ChatColor.DARK_AQUA + "Bans: " + ChatColor.GRAY + profile.getBans(),
                            ChatColor.DARK_AQUA + "IP-Bans: " + ChatColor.GRAY + profile.getIpBans(),
                            ChatColor.DARK_AQUA + "Warns: " + ChatColor.GRAY + profile.getWarns(),
                            ChatColor.DARK_AQUA + "Mutes: " + ChatColor.GRAY + profile.getMutes());
            guiCreator.setItemInSlot(violationInfoStack, 30);
        } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 30);

        activeEffectsBuilder = new ItemBuilder(Material.POTION)
                .displayname(ChatColor.GOLD + "Active Effects");
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
                .setDisplayName(ChatColor.GOLD + "Player Location")
                .setLore(ChatColor.DARK_AQUA + target.getWorld().getName() + "; " + target.getLocation().getBlockX() + ", "
                        + target.getLocation().getBlockY() + ", " + target.getLocation().getBlockZ());
        if (player.hasPermission("activecraft.location")) {
            guiCreator.setItemInSlot(playerLocationStack, 16);
        } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 16);

        //Player Playtime
        playtimeStack = new GuiItem(Material.CLOCK)
                .setDisplayName(ChatColor.GOLD + "Playtime")
                .setLore(ChatColor.DARK_AQUA + "" + profile.getPlaytimeHours() + " Hours" + ChatColor.GRAY + " and " + ChatColor.DARK_AQUA + profile.getPlaytimeMinutes() + " Minutes");
        if (player.hasPermission("activecraft.playtime")) {
            guiCreator.setItemInSlot(playtimeStack, 15);
        } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 15);

        // Other Menus
        // Player Punishment
        if (player.hasPermission("activecraft.punishment.info")) {
            violationStack = new GuiItem(Material.COMMAND_BLOCK)
                  .setDisplayName(ChatColor.GOLD + "Punishment Options");
            guiCreator.setItemInSlot(violationStack, 42);
        } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 42);

        // Player Gamemode Switcher
        if (player.hasPermission("activecraft.punishment.info")) {
            gamemodeSwitcherStack = new GuiItem(Material.GRASS_BLOCK)
                    .setDisplayName(ChatColor.GOLD + "Gamemode Switcher");
            guiCreator.setItemInSlot(gamemodeSwitcherStack, 32);
        } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 32);

        // Player Storage Menu
        if (player.hasPermission("activecraft.punishment.info")) {
            storageMenuStack = new GuiItem(Material.CHEST)
                    .setDisplayName(ChatColor.GOLD + "Storage Menu");
            guiCreator.setItemInSlot(storageMenuStack, 33);
        } else guiCreator.setItemInSlot(new GuiNoPermissionItem(), 33);

        // Player Actions Menu
        if (player.hasPermission("activecraft.punishment.info")) {
            actionMenuStack = new GuiItem(Material.LEVER)
                    .setDisplayName(ChatColor.GOLD + "Action Menu");
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