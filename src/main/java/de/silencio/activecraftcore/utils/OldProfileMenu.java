package de.silencio.activecraftcore.utils;

import de.silencio.activecraftcore.manager.BanManager;
import de.silencio.activecraftcore.manager.WarnManager;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.messages.Reasons;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OldProfileMenu implements Listener {

    private Player player;
    private Player target;
    private ItemBuilder backStack;
    private ItemStack closeStack;
    private ItemStack noPermissionStack;
    private ItemStack glassFillItem;
    private Inventory lastInv;

    //main inv
    Inventory profileInventory;
    private ItemStack playerhead;
    private ItemStack gamemodeSwitcherStack;
    private ItemStack punishmentStack;
    private ItemStack violationInfoStack;
    private ItemStack kickStack;
    private ItemStack invSeeStack;
    private ItemStack connectionInfoStack;
    private ItemStack activeEffectsStack;
    private ItemStack playtimeStack;
    private ItemStack playerLocationStack;
    private ItemStack storageMenuStack;
    private ItemStack actionMenuStack;

    private ItemBuilder activeEffectsBuilder;

    //violation inv
    private Inventory violationInv;
    private ItemStack warnStack;
    private ItemStack banStack;
    private ItemStack ipBanStack;
    private ItemStack muteStack;

    //reasons inv
    private Inventory reasonsTimeInv;
    private Inventory reasonsInv;
    private Reason activeReason;
    private String violationReason;
    private int banTime;
    private ItemStack reasonStack_hacking;
    private ItemStack reasonStack_botting;
    private ItemStack reasonStack_unauthorized_alt_acc;
    private ItemStack reasonStack_spam;
    private ItemStack reasonStack_abusive_lang;
    private ItemStack reasonStack_scamming;
    private ItemStack reasonStack_griefing;
    private ItemStack not_selectedStack;
    private ItemStack selectedStack;
    private ItemStack m15Stack;
    private ItemStack h1Stack;
    private ItemStack h8Stack;
    private ItemStack d1Stack;
    private ItemStack d7Stack;
    private ItemStack M1Stack;
    private ItemStack permanentStack;
    private ItemStack verificationStack;

    //action inv
    private Inventory actionMenuInv;
    private ItemStack tpToPlayerStack;
    private ItemStack tpherePlayerStack;
    private ItemStack clearInvStack;
    private ItemStack flyStack;
    private ItemStack godModeStack;
    private ItemStack vanishStack;
    private ItemStack feedStack;
    private ItemStack healStack;
    private ItemStack homeStack;
    private ItemStack strikeStack;
    private ItemStack suicideStack;
    private ItemStack killStack;
    private ItemStack explodeStack;
    private ItemBuilder godModeItemBuilder;
    private ItemBuilder vanishItemBuilder;
    private ItemBuilder flyItemBuilder;


    //gamemode inv
    private Inventory gamemodeSwitcherInv;
    private ItemStack survivalStack;
    private ItemStack creativeStack;
    private ItemStack spectatorStack;
    private ItemStack adventureStack;

    //storage inv
    private Inventory storageMenuInv;
    private ItemStack inventoryStack;
    private ItemStack enderchestStack;
    private ItemStack offInvStack;

    //confirmation
    private Inventory confirmationInv;
    private Confirmable activeConfirmation;
    private ItemStack cancelStack;
    private ItemStack confirmStack;
    private ItemStack stackToBeConfirmed;

    private Profile profile;
    private BanManager nameBanManager;
    private BanManager ipBanManager;
    private WarnManager warnManager;

    public enum Confirmable {
        BAN,
        BAN_IP,
        WARN,
        KICK,
        MUTE,
        GOD,
        VANISH,
        CLEAR_INV,
        TP,
        TP_HERE,
        KILL,
        FLY;
    }

    public enum Reason {
        HACKING,
        BOTTING,
        UNAUTHORIZED_ALTERNATE_ACCOUNT,
        CHATFILL_SPAM,
        ABUSIVE_LANGUAGE,
        SCAMMING_STEALING,
        GRIEFING_STEALING;
    }

    public OldProfileMenu(Player player, Player target) {
        this.player = player;
        this.target = target;
        nameBanManager = new BanManager(BanList.Type.NAME);
        ipBanManager = new BanManager(BanList.Type.IP);
        warnManager = new WarnManager(target);
        newBackItem();

        closeStack = new ItemBuilder(Material.BARRIER).displayname(ChatColor.GOLD + "Close").build();
        noPermissionStack = new ItemBuilder(Material.BARRIER).displayname(ChatColor.RED + "No Permission").build();
    }

    public void openProfile() {
        this.profileInventory = Bukkit.createInventory(null, 9 * 6, target.getName() + "'s Profile");
        //borderWithGlass(6, this.profileInventory);
        renewProfileInventory();
        player.openInventory(profileInventory);
    }

    private void renewViolationsInventory() {
        violationInv = Bukkit.createInventory(null, 3 * 9, "Ban Options for " + target.getName());
        fillWithGlass(violationInv);
        {
            violationInv.setItem(4, playerhead);
        }
        {
            banStack = new ItemBuilder(Material.GRASS_BLOCK)
                    .displayname(ChatColor.GOLD + "Ban " + target.getName())
                    .build();
            if (player.hasPermission("activecraft.ban")) {
                violationInv.setItem(14, banStack);
            } else violationInv.setItem(14, noPermissionStack);
        }
        {
            warnStack = new ItemBuilder(Material.GRASS_BLOCK)
                    .displayname(ChatColor.GOLD + "Warn " + target.getName())
                    .build();
            if (player.hasPermission("activecraft.warn.add")) {
                violationInv.setItem(11, warnStack);
            } else violationInv.setItem(11, noPermissionStack);
        }
        if (!profile.isMuted()) {
            muteStack = new ItemBuilder(Material.GRASS_BLOCK)
                    .displayname(ChatColor.GOLD + "Mute " + target.getName())
                    .lore(ChatColor.GRAY + target.getName() + " is not muted.")
                    .build();
            if (player.hasPermission("activecraft.mute")) {
                violationInv.setItem(12, muteStack);
            } else violationInv.setItem(12, noPermissionStack);
        } else {
            muteStack = new ItemBuilder(Material.GRASS_BLOCK)
                    .displayname(ChatColor.GOLD + "Unmute " + target.getName())
                    .lore(ChatColor.GRAY + target.getName() + " is muted.")
                    .build();
            if (player.hasPermission("activecraft.mute")) {
                violationInv.setItem(12, muteStack);
            } else violationInv.setItem(12, noPermissionStack);
        }
        {
            ipBanStack = new ItemBuilder(Material.GRASS_BLOCK)
                    .displayname(ChatColor.GOLD + "Ban-IP " + ChatColor.AQUA + "(" +
                            target.getAddress().getAddress().toString().replace("/", "") + ")")
                    .build();
            if (player.hasPermission("activecraft.ban")) {
                violationInv.setItem(15, ipBanStack);
            } else violationInv.setItem(15, noPermissionStack);
        }
        {
            kickStack = new ItemBuilder(Material.GRASS_BLOCK)
                    .displayname(ChatColor.GOLD + "Kick " + target.getName())
                    .build();
            if (player.hasPermission("activecraft.kick")) {
                violationInv.setItem(13, kickStack);
            } else violationInv.setItem(13, noPermissionStack);
        }
        violationInv.setItem(21, backStack.lore(ChatColor.GRAY + "Go back to " + ChatColor.DARK_AQUA + "profile").build());
        violationInv.setItem(22, closeStack);
    }

    private void renewGamemodeSwitcher() {
        gamemodeSwitcherInv = Bukkit.createInventory(null, 3 * 9, "Gamemode Switcher");
        fillWithGlass(gamemodeSwitcherInv);
        {
            gamemodeSwitcherInv.setItem(4, playerhead);
        }
        {
            creativeStack = new ItemBuilder(Material.GRASS_BLOCK)
                    .displayname(ChatColor.GOLD + "Set " + ChatColor.AQUA + target.getName() + "'s " + ChatColor.GOLD + "gamemode to " + ChatColor.AQUA + "Creative")
                    .build();
            if (player.hasPermission("activecraft.gamemode.creative")) {
                gamemodeSwitcherInv.setItem(11, creativeStack);
            } else gamemodeSwitcherInv.setItem(11, noPermissionStack);
        }
        {
            survivalStack = new ItemBuilder(Material.IRON_SWORD)
                    .displayname(ChatColor.GOLD + "Set " + ChatColor.AQUA + target.getName() + "'s " + ChatColor.GOLD + "gamemode to " + ChatColor.AQUA + "Survival")
                    .build();
            if (player.hasPermission("activecraft.gamemode.survival")) {
                gamemodeSwitcherInv.setItem(12, survivalStack);
            } else gamemodeSwitcherInv.setItem(12, noPermissionStack);
        }
        {
            ItemBuilder currentGamemodeStackBuilder = new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE);

            switch (target.getGameMode()) {
                case CREATIVE:
                    currentGamemodeStackBuilder.displayname(ChatColor.GOLD + "Current Gamemode: " + ChatColor.AQUA + "Creative");
                    break;
                case SURVIVAL:
                    currentGamemodeStackBuilder.displayname(ChatColor.GOLD + "Current Gamemode: " + ChatColor.AQUA + "Survival");
                    break;
                case SPECTATOR:
                    currentGamemodeStackBuilder.displayname(ChatColor.GOLD + "Current Gamemode: " + ChatColor.AQUA + "Spectator");
                    break;
                case ADVENTURE:
                    currentGamemodeStackBuilder.displayname(ChatColor.GOLD + "Current Gamemode: " + ChatColor.AQUA + "Adventure");
                    break;
            }

            gamemodeSwitcherInv.setItem(13, currentGamemodeStackBuilder.build());
        }
        {
            spectatorStack = new ItemBuilder(Material.ENDER_EYE)
                    .displayname(ChatColor.GOLD + "Set " + ChatColor.AQUA + target.getName() + "'s " + ChatColor.GOLD + "gamemode to " + ChatColor.AQUA + "Spectator")
                    .build();
            if (player.hasPermission("activecraft.gamemode.spectator")) {
                gamemodeSwitcherInv.setItem(15, spectatorStack);
            } else gamemodeSwitcherInv.setItem(15, noPermissionStack);
        }
        {
            adventureStack = new ItemBuilder(Material.MAP)
                    .displayname(ChatColor.GOLD + "Set " + ChatColor.AQUA + target.getName() + "'s " + ChatColor.GOLD + "gamemode to " + ChatColor.AQUA + "Adventure")
                    .build();
            if (player.hasPermission("activecraft.gamemode.adventure")) {
                gamemodeSwitcherInv.setItem(14, adventureStack);
            } else gamemodeSwitcherInv.setItem(14, noPermissionStack);
        }
        gamemodeSwitcherInv.setItem(21, backStack.lore(ChatColor.GRAY + "Go back to " + ChatColor.DARK_AQUA + "profile").build());
        gamemodeSwitcherInv.setItem(22, closeStack);
    }

    private void renewStorageMenu() {
        storageMenuInv = Bukkit.createInventory(null, 3 * 9, "Storage Menu");
        fillWithGlass(storageMenuInv);
        {
            storageMenuInv.setItem(4, playerhead);
        }
        {
            invSeeStack = new ItemBuilder(Material.CHEST)
                    .displayname(ChatColor.GOLD + "Open " + ChatColor.AQUA + target.getName() + "'s" + ChatColor.GOLD + " Inventory")
                    .build();
            if (player.hasPermission("activecraft.invsee")) {
                storageMenuInv.setItem(12, invSeeStack);
            } else storageMenuInv.setItem(12, noPermissionStack);
        }
        {
            enderchestStack = new ItemBuilder(Material.ENDER_CHEST)
                    .displayname(ChatColor.GOLD + "Open " + ChatColor.AQUA + target.getName() + "'s" + ChatColor.GOLD + " enderchest")
                    .build();
            if (player.hasPermission("activecraft.enderchest.others")) {
                storageMenuInv.setItem(14, enderchestStack);
            } else storageMenuInv.setItem(14, noPermissionStack);
        }
        {
            offInvStack = new ItemBuilder(Material.SHIELD)
                    .displayname(ChatColor.GOLD + "Open " + ChatColor.AQUA + target.getName() + "'s" + ChatColor.GOLD + " armor inventory")
                    .lore(ChatColor.RED + "Comming soon")
                    .build();
            if (player.hasPermission("activecraft.enderchest.others")) {
                storageMenuInv.setItem(13, offInvStack);
            } else storageMenuInv.setItem(13, noPermissionStack);
        }
        storageMenuInv.setItem(21, backStack.lore(ChatColor.GRAY + "Go back to " + ChatColor.DARK_AQUA + "profile").build());
        storageMenuInv.setItem(22, closeStack);
    }

    private void renewReasonsMenu(boolean useTime) {
        reasonsTimeInv = Bukkit.createInventory(null, 6 * 9, "Choose a reason");
        reasonsInv = Bukkit.createInventory(null, 6 * 9, "Choose a reason");
        fillWithGlass(reasonsTimeInv);
        fillWithGlass(reasonsInv);
        {
            reasonsTimeInv.setItem(4, playerhead);
            reasonsInv.setItem(4, playerhead);
        }
        {
            reasonStack_hacking = new ItemBuilder(Material.PAPER)
                    .displayname(ChatColor.GOLD + Reasons.HACKING())
                    .lore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.HACKING())
                    .build();

            activeReason = Reason.HACKING;

            reasonsTimeInv.setItem(10, reasonStack_hacking);
            reasonsInv.setItem(10, reasonStack_hacking);
        }
        {
            reasonStack_botting = new ItemBuilder(Material.PAPER)
                    .displayname(ChatColor.GOLD + Reasons.BOTTING())
                    .lore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.BOTTING())
                    .build();

            activeReason = Reason.BOTTING;

            reasonsTimeInv.setItem(11, reasonStack_botting);
            reasonsInv.setItem(11, reasonStack_botting);
        }
        {
            reasonStack_abusive_lang = new ItemBuilder(Material.PAPER)
                    .displayname(ChatColor.GOLD + Reasons.ABUSIVE_LANGUAGE())
                    .lore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.ABUSIVE_LANGUAGE())
                    .build();

            activeReason = Reason.ABUSIVE_LANGUAGE;

            reasonsTimeInv.setItem(12, reasonStack_abusive_lang);
            reasonsInv.setItem(12, reasonStack_abusive_lang);
        }
        {
            reasonStack_spam = new ItemBuilder(Material.PAPER)
                    .displayname(ChatColor.GOLD + Reasons.SPAM())
                    .lore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.SPAM())
                    .build();

            activeReason = Reason.CHATFILL_SPAM;

            reasonsTimeInv.setItem(13, reasonStack_spam);
            reasonsInv.setItem(13, reasonStack_spam);
        }
        {
            reasonStack_griefing = new ItemBuilder(Material.PAPER)
                    .displayname(ChatColor.GOLD + Reasons.GRIEFING())
                    .lore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.GRIEFING())
                    .build();

            activeReason = Reason.GRIEFING_STEALING;

            reasonsTimeInv.setItem(14, reasonStack_griefing);
            reasonsInv.setItem(14, reasonStack_griefing);
        }
        {
            reasonStack_scamming = new ItemBuilder(Material.PAPER)
                    .displayname(ChatColor.GOLD + Reasons.STEALING())
                    .lore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.STEALING())
                    .build();

            activeReason = Reason.SCAMMING_STEALING;

            reasonsTimeInv.setItem(15, reasonStack_scamming);
            reasonsInv.setItem(15, reasonStack_scamming);
        }
        {
            reasonStack_unauthorized_alt_acc = new ItemBuilder(Material.PAPER)
                    .displayname(ChatColor.GOLD + Reasons.UNAUTHORIZED_ALTERNATE_ACCOUNT())
                    .lore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.UNAUTHORIZED_ALTERNATE_ACCOUNT())
                    .build();

            activeReason = Reason.UNAUTHORIZED_ALTERNATE_ACCOUNT;

            reasonsTimeInv.setItem(16, reasonStack_unauthorized_alt_acc);
            reasonsInv.setItem(16, reasonStack_unauthorized_alt_acc);
        }
        if (useTime) {
            {
                m15Stack = new ItemBuilder(Material.CLOCK)
                        .displayname(ChatColor.GOLD + "15 Minutes")
                        .lore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "15 Minutes")
                        .build();

                reasonsTimeInv.setItem(28, m15Stack);
            }
            {
                h1Stack = new ItemBuilder(Material.CLOCK)
                        .displayname(ChatColor.GOLD + "1 Hours")
                        .lore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "1 Hour")
                        .build();

                reasonsTimeInv.setItem(29, h1Stack);
            }
            {
                h8Stack = new ItemBuilder(Material.CLOCK)
                        .displayname(ChatColor.GOLD + "8 Hours")
                        .lore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "8 Hours")
                        .build();

                reasonsTimeInv.setItem(30, h8Stack);
            }
            {
                d1Stack = new ItemBuilder(Material.CLOCK)
                        .displayname(ChatColor.GOLD + "1 Day")
                        .lore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "1 Day")
                        .build();

                reasonsTimeInv.setItem(31, d1Stack);
            }
            {
                d7Stack = new ItemBuilder(Material.CLOCK)
                        .displayname(ChatColor.GOLD + "7 Days")
                        .lore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "7 Days")
                        .build();

                reasonsTimeInv.setItem(32, d7Stack);
            }
            {
                M1Stack = new ItemBuilder(Material.CLOCK)
                        .displayname(ChatColor.GOLD + "1 Month")
                        .lore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "1 Month")
                        .build();

                reasonsTimeInv.setItem(33, M1Stack);
            }
            {
                permanentStack = new ItemBuilder(Material.CLOCK)
                        .displayname(ChatColor.GOLD + "Permanent")
                        .lore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + " permanent")
                        .build();

                reasonsTimeInv.setItem(34, permanentStack);
            }
            verificationStack = new ItemBuilder(Material.LIME_DYE)
                    .displayname(ChatColor.GREEN + "Confirm")
                    .build();
            reasonsTimeInv.setItem(48, backStack.lore(ChatColor.GRAY + "Go back to " + ChatColor.DARK_AQUA + "profile").build());
            reasonsTimeInv.setItem(49, closeStack);
            reasonsTimeInv.setItem(50, verificationStack);
        } else {
            verificationStack = new ItemBuilder(Material.LIME_DYE)
                    .displayname(ChatColor.GREEN + "Confirm")
                    .build();
            reasonsInv.setItem(48, backStack.lore(ChatColor.GRAY + "Go back to " + ChatColor.DARK_AQUA + "profile").build());
            reasonsInv.setItem(49, closeStack);
            reasonsInv.setItem(50, verificationStack);
        }
        {
            not_selectedStack = new ItemBuilder(Material.RED_STAINED_GLASS_PANE)
                    .displayname(ChatColor.RED + "Not selected")
                    .build();
            selectedStack = new ItemBuilder(Material.LIME_STAINED_GLASS_PANE)
                    .displayname(ChatColor.GREEN + "Selected")
                    .build();
        }
        {
            for (int i = 19; i < 26; i++) {
                reasonsTimeInv.setItem(i, not_selectedStack);
                reasonsTimeInv.setItem(i + 18, not_selectedStack);
                reasonsInv.setItem(i, not_selectedStack);
            }


        }
    }

    public void getConfirmation(ItemStack targetForConfirmation) {
        confirmationInv = Bukkit.createInventory(null, 3 * 9, "Confirm");
        //borderWithGlass(3, confirmationInv);
        stackToBeConfirmed = targetForConfirmation;
        {
            confirmStack = new ItemBuilder(Material.LIME_CONCRETE)
                    .displayname(ChatColor.GREEN + "Confirm")
                    .build();
            confirmationInv.setItem(11, confirmStack);
        }
        {
            cancelStack = new ItemBuilder(Material.RED_CONCRETE)
                    .displayname(ChatColor.RED + "Cancel")
                    .build();
            confirmationInv.setItem(15, cancelStack);
        }
        player.openInventory(confirmationInv);
    }

    @EventHandler
    private void onSelect(InventoryClickEvent event) {
        Inventory eventInv = event.getClickedInventory();
        if (Objects.equals(event.getClickedInventory(), this.reasonsTimeInv)) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) return;
            if (Objects.equals(event.getCurrentItem(), this.reasonStack_hacking)) {
                if ((Objects.equals(reasonsTimeInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 19; i < 26; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsTimeInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsTimeInv.setItem(event.getSlot() + 9, selectedStack);
                    violationReason = Reasons.HACKING();
                }
            } else if (Objects.equals(event.getCurrentItem(), this.reasonStack_botting)) {
                if ((Objects.equals(reasonsTimeInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 19; i < 26; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsTimeInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsTimeInv.setItem(event.getSlot() + 9, selectedStack);
                    violationReason = Reasons.BOTTING();
                }
            } else if (Objects.equals(event.getCurrentItem(), this.reasonStack_unauthorized_alt_acc)) {
                if ((Objects.equals(reasonsTimeInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 19; i < 26; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsTimeInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsTimeInv.setItem(event.getSlot() + 9, selectedStack);
                    violationReason = Reasons.UNAUTHORIZED_ALTERNATE_ACCOUNT();
                }
            } else if (Objects.equals(event.getCurrentItem(), this.reasonStack_griefing)) {
                if ((Objects.equals(reasonsTimeInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 19; i < 26; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsTimeInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsTimeInv.setItem(event.getSlot() + 9, selectedStack);
                    violationReason = Reasons.GRIEFING();
                }
            } else if (Objects.equals(event.getCurrentItem(), this.reasonStack_scamming)) {
                if ((Objects.equals(reasonsTimeInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 19; i < 26; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsTimeInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsTimeInv.setItem(event.getSlot() + 9, selectedStack);
                    violationReason = Reasons.STEALING();
                }
            } else if (Objects.equals(event.getCurrentItem(), this.reasonStack_spam)) {
                if ((Objects.equals(reasonsTimeInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 19; i < 26; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsTimeInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsTimeInv.setItem(event.getSlot() + 9, selectedStack);
                    violationReason = Reasons.SPAM();
                }
            } else if (Objects.equals(event.getCurrentItem(), this.reasonStack_abusive_lang)) {
                if ((Objects.equals(reasonsTimeInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 19; i < 26; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsTimeInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsTimeInv.setItem(event.getSlot() + 9, selectedStack);
                    violationReason = Reasons.ABUSIVE_LANGUAGE();
                }
            }


            if (Objects.equals(event.getCurrentItem(), this.m15Stack)) {
                if ((Objects.equals(reasonsTimeInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 37; i < 44; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsTimeInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsTimeInv.setItem(event.getSlot() + 9, selectedStack);
                    banTime = 15;
                }
            } else if (Objects.equals(event.getCurrentItem(), this.h1Stack)) {
                if ((Objects.equals(reasonsTimeInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 37; i < 44; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsTimeInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsTimeInv.setItem(event.getSlot() + 9, selectedStack);
                    banTime = 60;
                }
            } else if (Objects.equals(event.getCurrentItem(), this.h8Stack)) {
                if ((Objects.equals(reasonsTimeInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 37; i < 44; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsTimeInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsTimeInv.setItem(event.getSlot() + 9, selectedStack);
                    banTime = 480;
                }
            } else if (Objects.equals(event.getCurrentItem(), this.d1Stack)) {
                if ((Objects.equals(reasonsTimeInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 37; i < 44; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsTimeInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsTimeInv.setItem(event.getSlot() + 9, selectedStack);
                    banTime = 1440;
                }
            } else if (Objects.equals(event.getCurrentItem(), this.d7Stack)) {
                if ((Objects.equals(reasonsTimeInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 37; i < 44; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsTimeInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsTimeInv.setItem(event.getSlot() + 9, selectedStack);
                    banTime = 10080;
                }
            } else if (Objects.equals(event.getCurrentItem(), this.M1Stack)) {
                if ((Objects.equals(reasonsTimeInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 37; i < 44; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsTimeInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsTimeInv.setItem(event.getSlot() + 9, selectedStack);
                    banTime = 302400;
                }
            } else if (Objects.equals(event.getCurrentItem(), this.permanentStack)) {
                if ((Objects.equals(reasonsTimeInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 37; i < 44; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsTimeInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsTimeInv.setItem(event.getSlot() + 9, selectedStack);
                    banTime = -1;
                }
            }


        } else if (Objects.equals(eventInv, this.reasonsInv)) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) return;
            if (Objects.equals(event.getCurrentItem(), this.reasonStack_hacking)) {
                if ((Objects.equals(reasonsInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 19; i < 26; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsInv.setItem(event.getSlot() + 9, selectedStack);
                    violationReason = Reasons.HACKING();
                }
            } else if (Objects.equals(event.getCurrentItem(), this.reasonStack_botting)) {
                if ((Objects.equals(reasonsInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 19; i < 26; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsInv.setItem(event.getSlot() + 9, selectedStack);
                    violationReason = Reasons.BOTTING();
                }
            } else if (Objects.equals(event.getCurrentItem(), this.reasonStack_unauthorized_alt_acc)) {
                if ((Objects.equals(reasonsInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 19; i < 26; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsInv.setItem(event.getSlot() + 9, selectedStack);
                    violationReason = Reasons.UNAUTHORIZED_ALTERNATE_ACCOUNT();
                }
            } else if (Objects.equals(event.getCurrentItem(), this.reasonStack_griefing)) {
                if ((Objects.equals(reasonsInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 19; i < 26; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsInv.setItem(event.getSlot() + 9, selectedStack);
                    violationReason = Reasons.GRIEFING();
                }
            } else if (Objects.equals(event.getCurrentItem(), this.reasonStack_scamming)) {
                if ((Objects.equals(reasonsInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 19; i < 26; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsInv.setItem(event.getSlot() + 9, selectedStack);
                    violationReason = Reasons.STEALING();
                }
            } else if (Objects.equals(event.getCurrentItem(), this.reasonStack_spam)) {
                if ((Objects.equals(reasonsInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 19; i < 26; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsInv.setItem(event.getSlot() + 9, selectedStack);
                    violationReason = Reasons.SPAM();
                }
            } else if (Objects.equals(event.getCurrentItem(), this.reasonStack_abusive_lang)) {
                if ((Objects.equals(reasonsInv.getItem(event.getSlot() + 9), not_selectedStack))) {
                    for (int i = 19; i < 26; i++) {
                        if (!(i == event.getSlot())) {
                            reasonsInv.setItem(i, not_selectedStack);
                        }
                    }
                    reasonsInv.setItem(event.getSlot() + 9, selectedStack);
                    violationReason = Reasons.ABUSIVE_LANGUAGE();
                }
            }
        }
    }

    @EventHandler
    private void onConfirm(InventoryClickEvent event) {
        Inventory eventInv = event.getClickedInventory();
        if (Objects.equals(event.getClickedInventory(), this.confirmationInv)) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) return;
            if (Objects.equals(event.getCurrentItem(), this.confirmStack)) {
                switch (activeConfirmation) {
                    case MUTE:
                        if (profile.isMuted()) {
                            player.performCommand("unmute " + target.getName());
                            renewViolationsInventory();
                            player.openInventory(violationInv);
                        } else player.performCommand("mute " + target.getName());
                        break;
                    case CLEAR_INV:
                        if (player.hasPermission("activecraft.clearinv")) {
                            player.performCommand("clear " + target.getName());
                            renewActionInventory();
                            player.openInventory(actionMenuInv);
                        } else player.sendMessage(Errors.NO_PERMISSION());
                        break;
                    case TP:
                        if (player.hasPermission("activecraft.tp")) {
                            player.teleport(target.getLocation());
                            renewActionInventory();
                            player.openInventory(actionMenuInv);
                        } else player.sendMessage(Errors.NO_PERMISSION());
                        break;
                    case TP_HERE:
                        if (player.hasPermission("activecraft.tp")) {
                            target.teleport(player.getLocation());
                            renewActionInventory();
                            player.openInventory(actionMenuInv);
                        } else player.sendMessage(Errors.NO_PERMISSION());
                        break;
                    case KILL:
                        if (player.hasPermission("activecraft.kill")) {
                            target.setHealth(0);
                            renewActionInventory();
                            player.openInventory(actionMenuInv);
                        } else player.sendMessage(Errors.NO_PERMISSION());
                        break;
                }
                activeConfirmation = null;
            } else if (Objects.equals(event.getCurrentItem(), this.cancelStack)) {
                activeConfirmation = null;
                player.openInventory(lastInv);
            }
        }
    }

    @EventHandler (priority = EventPriority.LOWEST)
    private void onClick(InventoryClickEvent event) {
        List<Inventory> invList = new ArrayList<>();
        invList.add(profileInventory);
        invList.add(actionMenuInv);
        invList.add(violationInv);
        invList.add(gamemodeSwitcherInv);
        invList.add(confirmationInv);
        invList.add(reasonsInv);
        invList.add(reasonsTimeInv);
        invList.add(storageMenuInv);
        if (event.getCurrentItem() == null) return;
        if (invList.contains(event.getClickedInventory())) {
            if (!Objects.equals(event.getCurrentItem().getType(), Material.GRAY_STAINED_GLASS_PANE)) {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1f, 1f);
            }
        }
    }

    @EventHandler
    private void onViolationConfirm(InventoryClickEvent event) {
        if (Objects.equals(event.getInventory(), this.reasonsTimeInv) || Objects.equals(event.getInventory(), this.reasonsInv)) {
            if (Objects.equals(event.getCurrentItem(), this.closeStack)) player.closeInventory();
            if (Objects.equals(event.getCurrentItem(), this.verificationStack)) {
                switch (activeConfirmation) {
                    case BAN:
                        if (player.hasPermission("activecraft.ban")) {
                            if (banTime == -1) {
                                nameBanManager.ban(target, violationReason, null, player.getName());
                                target.kickPlayer(violationReason);
                            } else {
                                Date nowDate = new Date();
                                long nowMillis = nowDate.getTime();
                                Date expires = new Date((long) banTime * 60 * 1000 + nowMillis);
                                nameBanManager.ban(target, violationReason, expires, player.getName());
                                target.kickPlayer(violationReason);
                            }
                            player.closeInventory();
                        } else player.sendMessage(Errors.NO_PERMISSION());
                        break;
                    case BAN_IP:
                        if (player.hasPermission("activecraft.ban")) {
                            if (banTime == -1) {
                                ipBanManager.ban(target.getAddress().getAddress().toString().replace("/", ""), violationReason, null, player.getName());
                                target.kickPlayer(violationReason);
                            } else {
                                Date nowDate = new Date();
                                long nowMillis = nowDate.getTime();
                                Date expires = new Date((long) banTime * 60 * 1000 + nowMillis);
                                ipBanManager.ban(target.getAddress().getAddress().toString().replace("/", ""), violationReason, expires, player.getName());
                                target.kickPlayer(violationReason);
                            }
                            player.closeInventory();
                        } else player.sendMessage(Errors.NO_PERMISSION());
                        break;
                    case WARN:
                        if (player.hasPermission("activecraft.warn")) {
                            player.performCommand("warn add " + target.getName() + " " + violationReason);
                            player.closeInventory();
                        } else player.sendMessage(Errors.NO_PERMISSION());
                        break;
                    case KICK:
                        if (player.hasPermission("activecraft.kick")) {
                            target.kickPlayer(violationReason);
                            player.closeInventory();
                        } else player.sendMessage(Errors.NO_PERMISSION());
                        break;
                }
            }
        }
    }


    @EventHandler
    private void onConfirmationInvClose(InventoryCloseEvent event) {
        if (Objects.equals(event.getInventory(), this.confirmationInv)) {
            activeConfirmation = null;
        }
    }

    @EventHandler
    private void onItemClick(InventoryClickEvent event) {
        Inventory eventInv = event.getClickedInventory();
        if (Objects.equals(event.getClickedInventory(), this.profileInventory)) {
            event.setCancelled(true);
            if (Objects.equals(event.getCurrentItem(), this.closeStack)) player.closeInventory();
            if (event.getCurrentItem() == null) return;
            if (Objects.equals(event.getCurrentItem(), this.punishmentStack)) {
                renewViolationsInventory();
                player.openInventory(violationInv);
            } else if (Objects.equals(event.getCurrentItem(), this.gamemodeSwitcherStack)) {
                renewGamemodeSwitcher();
                player.openInventory(gamemodeSwitcherInv);
            } else if (Objects.equals(event.getCurrentItem(), this.storageMenuStack)) {
                renewStorageMenu();
                player.openInventory(storageMenuInv);
            } else if (Objects.equals(event.getCurrentItem(), this.actionMenuStack)) {
                renewActionInventory();
                player.openInventory(actionMenuInv);
            }

        } else if (Objects.equals(eventInv, violationInv)) {
            event.setCancelled(true);
            if (Objects.equals(event.getCurrentItem(), this.closeStack)) player.closeInventory();
            if (event.getCurrentItem() == null) return;
            if (Objects.equals(event.getCurrentItem(), this.banStack)) {

                violationReason = "Banned by an operator.";
                banTime = -1;
                newBackItem();
                activeConfirmation = Confirmable.BAN;
                renewReasonsMenu(true);
                player.openInventory(reasonsTimeInv);
            } else if (Objects.equals(event.getCurrentItem(), this.ipBanStack)) {
                violationReason = "Banned by an operator.";
                banTime = -1;
                newBackItem();
                activeConfirmation = Confirmable.BAN_IP;
                renewReasonsMenu(true);
                player.openInventory(reasonsTimeInv);
            } else if (Objects.equals(event.getCurrentItem(), this.warnStack)) {
                violationReason = "Warned by a moderator.";
                banTime = -1;
                newBackItem();
                activeConfirmation = Confirmable.WARN;
                renewReasonsMenu(false);
                player.openInventory(reasonsInv);
            } else if (Objects.equals(event.getCurrentItem(), this.muteStack)) {
                activeConfirmation = Confirmable.MUTE;
                getConfirmation(muteStack);
                lastInv = violationInv;
            } else if (Objects.equals(event.getCurrentItem(), this.kickStack)) {
                violationReason = "Kicked by an operator.";
                banTime = -1;
                newBackItem();
                activeConfirmation = Confirmable.KICK;
                renewReasonsMenu(false);
                player.openInventory(reasonsInv);
            }
        } else if (Objects.equals(eventInv, gamemodeSwitcherInv)) {
            event.setCancelled(true);
            if (Objects.equals(event.getCurrentItem(), this.closeStack)) player.closeInventory();
            if (event.getCurrentItem() == null) return;
            if (Objects.equals(event.getCurrentItem(), this.survivalStack)) {
                newBackItem();
                player.performCommand("su " + target.getName());
                renewGamemodeSwitcher();
                player.openInventory(gamemodeSwitcherInv);
            } else if (Objects.equals(event.getCurrentItem(), this.creativeStack)) {
                newBackItem();
                player.performCommand("cr " + target.getName());
                renewGamemodeSwitcher();
                player.openInventory(gamemodeSwitcherInv);
            } else if (Objects.equals(event.getCurrentItem(), this.spectatorStack)) {
                newBackItem();
                player.performCommand("sp " + target.getName());
                renewGamemodeSwitcher();
                player.openInventory(gamemodeSwitcherInv);
            } else if (Objects.equals(event.getCurrentItem(), this.adventureStack)) {
                newBackItem();
                player.performCommand("ad " + target.getName());
                renewGamemodeSwitcher();
                player.openInventory(gamemodeSwitcherInv);
            }
        } else if (Objects.equals(eventInv, storageMenuInv)) {
            event.setCancelled(true);
            if (Objects.equals(event.getCurrentItem(), this.closeStack)) player.closeInventory();
            if (event.getCurrentItem() == null) return;
            if (Objects.equals(event.getCurrentItem(), this.invSeeStack)) {
                if (player.hasPermission("activecraft.invsee")) {
                    player.closeInventory();
                    player.performCommand("invsee " + target.getName());
                    player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1f, 1f);
                } else player.sendMessage(Errors.NO_PERMISSION());
            } else if (Objects.equals(event.getCurrentItem(), this.enderchestStack)) {
                if (player.hasPermission("activecraft.enderchest.others")) {
                    player.openInventory(target.getEnderChest());
                    player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1f, 1f);
                } else player.sendMessage(Errors.NO_PERMISSION());
            }
        } else if (Objects.equals(eventInv, actionMenuInv)) {
            event.setCancelled(true);
            if (Objects.equals(event.getCurrentItem(), this.closeStack)) player.closeInventory();
            if (event.getCurrentItem() == null) return;
            if (Objects.equals(event.getCurrentItem(), this.godModeStack)) {
                player.performCommand("god " + target.getName());
                newBackItem();
                renewActionInventory();
                player.openInventory(actionMenuInv);
            } else if (Objects.equals(event.getCurrentItem(), this.flyStack)) {
                player.performCommand("fly " + target.getName());
                newBackItem();
                renewActionInventory();
                player.openInventory(actionMenuInv);
            } else if (Objects.equals(event.getCurrentItem(), this.vanishStack)) {
                player.performCommand("vanish " + target.getName());
                newBackItem();
                renewActionInventory();
                player.openInventory(actionMenuInv);
            } else if (Objects.equals(event.getCurrentItem(), this.feedStack)) {
                player.performCommand("feed " + target.getName());
            } else if (Objects.equals(event.getCurrentItem(), this.healStack)) {
                player.performCommand("heal " + target.getName());
            } else if (Objects.equals(event.getCurrentItem(), this.clearInvStack)) {
                activeConfirmation = Confirmable.CLEAR_INV;
                getConfirmation(clearInvStack);
                lastInv = actionMenuInv;
            } else if (Objects.equals(event.getCurrentItem(), this.homeStack)) {
                player.performCommand("home " + target.getName());
            } else if (Objects.equals(event.getCurrentItem(), this.tpToPlayerStack)) {
                activeConfirmation = Confirmable.TP;
                getConfirmation(tpToPlayerStack);
                lastInv = actionMenuInv;
            } else if (Objects.equals(event.getCurrentItem(), this.tpherePlayerStack)) {
                activeConfirmation = Confirmable.TP_HERE;
                getConfirmation(tpherePlayerStack);
                lastInv = actionMenuInv;
            } else if (Objects.equals(event.getCurrentItem(), this.strikeStack)) {
                player.performCommand("strike " + target.getName());
            } else if (Objects.equals(event.getCurrentItem(), this.explodeStack)) {
                player.performCommand("explode " + target.getName());
            } else if (Objects.equals(event.getCurrentItem(), this.killStack)) {
                activeConfirmation = Confirmable.KILL;
                getConfirmation(killStack);
                lastInv = actionMenuInv;
            }
        }
    }

    @EventHandler
    private void onBack(InventoryClickEvent event) {
        Inventory eventInv = event.getClickedInventory();
        if (Objects.equals(event.getCurrentItem(), this.backStack.build())) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) return;
            if (Objects.equals(eventInv, this.violationInv)) {
                newBackItem();
                renewProfileInventory();
                player.openInventory(profileInventory);
            } else if (Objects.equals(eventInv, this.gamemodeSwitcherInv)) {
                newBackItem();
                renewProfileInventory();
                player.openInventory(profileInventory);
            } else if (Objects.equals(eventInv, this.storageMenuInv)) {
                newBackItem();
                renewProfileInventory();
                player.openInventory(profileInventory);
            } else if (Objects.equals(eventInv, this.actionMenuInv)) {
                newBackItem();
                renewProfileInventory();
                player.openInventory(profileInventory);
            } else if (Objects.equals(eventInv, this.reasonsTimeInv) || Objects.equals(eventInv, this.reasonsInv)) {
                newBackItem();
                renewViolationsInventory();
                player.openInventory(violationInv);
            }
        }
    }


    private void newBackItem() {
        backStack = new ItemBuilder(Material.ARROW)
                .displayname("Go back");
    }

    private void renewActionInventory() {
        actionMenuInv = Bukkit.createInventory(null, 6 * 9, "Action Menu");
        profile = new Profile(target);
        fillWithGlass(actionMenuInv);

        {
            actionMenuInv.setItem(4, playerhead);
        }
        {
            vanishItemBuilder = new ItemBuilder(Material.GLASS_BOTTLE);

            if (profile.isVanished()) {
                vanishItemBuilder.displayname(ChatColor.GOLD + "Unvanish " + ChatColor.AQUA + target.getName())
                        .lore(ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY + " is currently vanished.");
            } else vanishItemBuilder.displayname(ChatColor.GOLD + "Vanish " + ChatColor.AQUA + target.getName())
                    .lore(ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY + " is not vanished.");

            vanishStack = vanishItemBuilder.build();
            if (player.hasPermission("activecraft.vanish.others")) {
                actionMenuInv.setItem(28, vanishStack);
            } else actionMenuInv.setItem(28, noPermissionStack);
        }
        {
            godModeItemBuilder = new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE);

            if (profile.isGodmode()) {
                godModeItemBuilder.displayname(ChatColor.GOLD + "Disable God Mode for " + ChatColor.AQUA + target.getName())
                        .lore(ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY + " is currently in God Mode.");
            } else
                godModeItemBuilder.displayname(ChatColor.GOLD + "Enable God Mode for " + ChatColor.AQUA + target.getName())
                        .lore(ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY + " is not in God Mode.");

            godModeStack = godModeItemBuilder.build();
            if (player.hasPermission("activecraft.god.others")) {
                actionMenuInv.setItem(10, godModeStack);
            } else actionMenuInv.setItem(10, noPermissionStack);
        }
        {
            flyItemBuilder = new ItemBuilder(Material.FEATHER);

            if (profile.isFly()) {
                flyItemBuilder.displayname(ChatColor.GOLD + "Disable Flight for " + ChatColor.AQUA + target.getName())
                        .lore(ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY + " is currently in flight mode.");
            } else flyItemBuilder.displayname(ChatColor.GOLD + "Enable Flight for " + ChatColor.AQUA + target.getName())
                    .lore(ChatColor.DARK_AQUA + target.getName() + ChatColor.GRAY + " is not in flight mode.");

            flyStack = flyItemBuilder.build();
            if (player.hasPermission("activecraft.fly.others")) {
                actionMenuInv.setItem(19, flyStack);
            } else actionMenuInv.setItem(19, noPermissionStack);
        }

        {
            clearInvStack = new ItemBuilder(Material.STRUCTURE_VOID)
                    .displayname(ChatColor.GOLD + "Clear " + ChatColor.AQUA + target.getName() + "'s " + ChatColor.GOLD + "inventory")
                    .build();
            if (player.hasPermission("activecraft.clearinv")) {
                actionMenuInv.setItem(30, clearInvStack);
            } else actionMenuInv.setItem(30, noPermissionStack);
        }

        {
            tpToPlayerStack = new ItemBuilder(Material.ENDER_PEARL)
                    .displayname(ChatColor.GOLD + "Teleport to " + ChatColor.AQUA + target.getName())
                    .build();
            if (player.hasPermission("activecraft.tp")) {
                actionMenuInv.setItem(23, tpToPlayerStack);
            } else actionMenuInv.setItem(23, noPermissionStack);
        }
        {
            tpherePlayerStack = new ItemBuilder(Material.ENDER_EYE)
                    .displayname(ChatColor.GOLD + "Teleport " + ChatColor.AQUA + target.getName() + ChatColor.GOLD + " to you")
                    .build();
            if (player.hasPermission("activecraft.tphere")) {
                actionMenuInv.setItem(32, tpherePlayerStack);
            } else actionMenuInv.setItem(32, noPermissionStack);
        }
        {
            homeStack = new ItemBuilder(Material.RED_BED)
                    .displayname(ChatColor.GOLD + "Teleport to " + ChatColor.AQUA + target.getName() + "'s" + ChatColor.GOLD + " Home")
                    .build();
            if (player.hasPermission("activecraft.home.others")) {
                actionMenuInv.setItem(14, homeStack);
            } else actionMenuInv.setItem(14, noPermissionStack);
        }
        {
            healStack = new ItemBuilder(Material.POTION)
                    .displayname(ChatColor.GOLD + "Heal " + ChatColor.AQUA + target.getName())
                    .build();
            PotionMeta potionMeta = (PotionMeta) healStack.getItemMeta();
            potionMeta.setColor(Color.FUCHSIA);
            potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            healStack.setItemMeta(potionMeta);
            if (player.hasPermission("activecraft.heal")) {
                actionMenuInv.setItem(21, healStack);
            } else actionMenuInv.setItem(21, noPermissionStack);
        }
        {
            feedStack = new ItemBuilder(Material.COOKED_BEEF)
                    .displayname(ChatColor.GOLD + "Feed " + ChatColor.AQUA + target.getName())
                    .build();
            if (player.hasPermission("activecraft.feed")) {
                actionMenuInv.setItem(12, feedStack);
            } else actionMenuInv.setItem(12, noPermissionStack);
        }
        {
            strikeStack = new ItemBuilder(Material.LIGHTNING_ROD)
                    .displayname(ChatColor.GOLD + "Strike " + ChatColor.AQUA + target.getName())
                    .build();
            if (player.hasPermission("activecraft.strike")) {
                actionMenuInv.setItem(16, strikeStack);
            } else actionMenuInv.setItem(16, noPermissionStack);
        }
        {
            explodeStack = new ItemBuilder(Material.TNT)
                    .displayname(ChatColor.GOLD + "Blow " + ChatColor.AQUA + target.getName() + ChatColor.GOLD + " up")
                    .build();
            if (player.hasPermission("activecraft.explode")) {
                actionMenuInv.setItem(25, explodeStack);
            } else actionMenuInv.setItem(25, noPermissionStack);
        }
        {
            killStack = new ItemBuilder(Material.SKELETON_SKULL)
                    .displayname(ChatColor.GOLD + "Kill " + ChatColor.AQUA + target.getName())
                    .build();
            if (player.hasPermission("activecraft.kill")) {
                actionMenuInv.setItem(34, killStack);
            } else actionMenuInv.setItem(34, noPermissionStack);
        }
        actionMenuInv.setItem(48, backStack.lore(ChatColor.GRAY + "Go back to " + ChatColor.DARK_AQUA + "profile").build());
        actionMenuInv.setItem(49, closeStack);
    }

    private void renewProfileInventory() {

        profile = new Profile(target);
        fillWithGlass(profileInventory);
        {
            ItemStack slotEmpty = new ItemBuilder(Material.RED_STAINED_GLASS_PANE)
                    .displayname(ChatColor.RED + "Slot is empty")
                    .build();
            ItemStack helmet = target.getInventory().getHelmet();
            ItemStack chestplate = target.getInventory().getChestplate();
            ItemStack leggins = target.getInventory().getLeggings();
            ItemStack boots = target.getInventory().getBoots();
            ItemStack offHand = target.getInventory().getItemInOffHand();
            ItemStack mainHand = target.getInventory().getItemInMainHand();
            if (mainHand.getType() != Material.AIR) {
                profileInventory.setItem(29, mainHand);
            } else profileInventory.setItem(29, slotEmpty);
            if (offHand.getType() != Material.AIR) {
                profileInventory.setItem(20, offHand);
            } else profileInventory.setItem(20, slotEmpty);
            if (helmet != null) {
                profileInventory.setItem(10, helmet);
            } else profileInventory.setItem(10, slotEmpty);
            if (chestplate != null) {
                profileInventory.setItem(19, chestplate);
            } else profileInventory.setItem(19, slotEmpty);
            if (leggins != null) {
                profileInventory.setItem(28, leggins);
            } else profileInventory.setItem(28, slotEmpty);
            if (boots != null) {
                profileInventory.setItem(37, boots);
            } else profileInventory.setItem(37, slotEmpty);
        }

        { //connection information
            if (player.hasPermission("activecraft.connection.info")) {
                connectionInfoStack = new ItemBuilder(Material.STRUCTURE_VOID)
                        .displayname(ChatColor.GOLD + "Connection Information")
                        .lore(ChatColor.DARK_AQUA + "IP: " + ChatColor.GRAY + target.getAddress().getAddress().toString().replace("/", ""))
                        .lore(ChatColor.DARK_AQUA + "Port: " + ChatColor.GRAY + target.getAddress().getPort())
                        .lore(ChatColor.DARK_AQUA + "Ping: " + ChatColor.GRAY + target.getPing())
                        .build();

                profileInventory.setItem(21, connectionInfoStack);
            } else profileInventory.setItem(21, noPermissionStack);
        }

        {  //playerhead
            playerhead = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) playerhead.getItemMeta();
            skullMeta.setDisplayName(ChatColor.GOLD + target.getName());
            List<String> loreList = new ArrayList<>();
            loreList.add(ChatColor.GRAY + "aka " + profile.getNickname());
            loreList.add(ChatColor.AQUA + profile.getUuid().toString());
            skullMeta.setLore(loreList);
            skullMeta.setOwningPlayer(target);
            playerhead.setItemMeta(skullMeta);
            profileInventory.setItem(4, playerhead);
        }

        { //player information
            ItemBuilder gameStats = new ItemBuilder(Material.GRASS_BLOCK)
                    .displayname(ChatColor.GOLD + "Player Information")
                    .lore(ChatColor.DARK_AQUA + "Health: " + ChatColor.GRAY + target.getHealth(),
                            ChatColor.DARK_AQUA + "Food: " + ChatColor.GRAY + target.getFoodLevel(),
                            ChatColor.DARK_AQUA + "Exp: " + ChatColor.GRAY + target.getLevel());
            switch (target.getGameMode()) {
                case CREATIVE:
                    gameStats.lore(ChatColor.DARK_AQUA + "Gamemode: " + ChatColor.GRAY + "Creative");
                    break;
                case SURVIVAL:
                    gameStats.lore(ChatColor.DARK_AQUA + "Gamemode: " + ChatColor.GRAY + "Survival");
                    break;
                case SPECTATOR:
                    gameStats.lore(ChatColor.DARK_AQUA + "Gamemode: " + ChatColor.GRAY + "Spectator");
                    break;
                case ADVENTURE:
                    gameStats.lore(ChatColor.DARK_AQUA + "Gamemode: " + ChatColor.GRAY + "Adventure");
                    break;
            }
            profileInventory.setItem(12, gameStats.build());
        }
        {
            punishmentStack = new ItemBuilder(Material.COMMAND_BLOCK)
                    .displayname(ChatColor.GOLD + "Punishment Options")
                    .build();
            profileInventory.setItem(42, punishmentStack);
        }
        {
            if (player.hasPermission("activecraft.violations.info")) {
                violationInfoStack = new ItemBuilder(Material.COMMAND_BLOCK)
                        .displayname(ChatColor.GOLD + "Violations")
                        .lore(ChatColor.DARK_AQUA + "Bans: " + ChatColor.GRAY + profile.getBans())
                        .lore(ChatColor.DARK_AQUA + "IP-Bans: " + ChatColor.GRAY + profile.getIpBans())
                        .lore(ChatColor.DARK_AQUA + "Warns: " + ChatColor.GRAY + profile.getWarns())
                        .lore(ChatColor.DARK_AQUA + "Mutes: " + ChatColor.GRAY + profile.getMutes())
                        .build();
                profileInventory.setItem(30, violationInfoStack);
            } else profileInventory.setItem(30, noPermissionStack);
        }
        {
            activeEffectsBuilder = new ItemBuilder(Material.POTION)
                    .displayname(ChatColor.GOLD + "Active Effects");
            for (PotionEffect effect : target.getActivePotionEffects()) {
                activeEffectsBuilder.lore(effect.getType().getName() + "; " + effect.getAmplifier() + "; " + IntegerUtils.shortInteger(effect.getDuration() / 20));

            }
            activeEffectsStack = activeEffectsBuilder.build();
            PotionMeta potionMeta = (PotionMeta) activeEffectsStack.getItemMeta();
            potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            activeEffectsStack.setItemMeta(potionMeta);
            if (player.hasPermission("activecraft.activeeffects")) {
                profileInventory.setItem(14, activeEffectsStack);
            } else profileInventory.setItem(14, noPermissionStack);
        }
        {
            gamemodeSwitcherStack = new ItemBuilder(Material.GRASS_BLOCK)
                    .displayname(ChatColor.GOLD + "Gamemode Switcher")
                    .build();
            profileInventory.setItem(32, gamemodeSwitcherStack);
        }
        {
            storageMenuStack = new ItemBuilder(Material.CHEST)
                    .displayname(ChatColor.GOLD + "Storage Menu")
                    .build();
            profileInventory.setItem(33, storageMenuStack);
        }
        {
            actionMenuStack = new ItemBuilder(Material.LEVER)
                    .displayname(ChatColor.GOLD + "Action Menu")
                    .build();
            profileInventory.setItem(34, actionMenuStack);
        }
        {
            playerLocationStack = new ItemBuilder(Material.REDSTONE_TORCH)
                    .displayname(ChatColor.GOLD + "Player Location")
                    .lore(ChatColor.DARK_AQUA + target.getWorld().getName() + "; " + target.getLocation().getBlockX() + ", "
                            + target.getLocation().getBlockY() + ", " + target.getLocation().getBlockZ())
                    .build();
            if (player.hasPermission("activecraft.location")) {
                profileInventory.setItem(16, playerLocationStack);
            } else profileInventory.setItem(16, noPermissionStack);
        }
        {
            playtimeStack = new ItemBuilder(Material.CLOCK)
                    .displayname(ChatColor.GOLD + "Playtime")
                    .lore(ChatColor.DARK_AQUA + "" + profile.getPlaytimeHours() + " Hours" + ChatColor.GRAY + " and " + ChatColor.DARK_AQUA + profile.getPlaytimeMinutes() + " Minutes")
                    .build();
            if (player.hasPermission("activecraft.playtime")) {
                profileInventory.setItem(15, playtimeStack);
            } else profileInventory.setItem(15, noPermissionStack);
            profileInventory.setItem(49, closeStack);
        }
    }

    private void fillWithGlass(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            glassFillItem = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
                    .displayname(" ")
                    .build();
            inventory.setItem(i, glassFillItem);
        }
    }

}
