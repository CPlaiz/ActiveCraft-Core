package de.silencio.activecraftcore.profilemenu.inventories;

import de.silencio.activecraftcore.gui.*;
import de.silencio.activecraftcore.manager.BanManager;
import de.silencio.activecraftcore.manager.WarnManager;
import de.silencio.activecraftcore.messages.Reasons;
import de.silencio.activecraftcore.profilemenu.ProfileMenu2;
import de.silencio.activecraftcore.utils.ItemBuilder;
import de.silencio.activecraftcore.utils.Profile;
import de.silencio.activecraftcore.utils.ProfileMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ReasonsProfile {

    private ProfileMenu2 profileMenu;
    private Player player;
    private Player target;
    private Profile profile;
    private BanManager nameBanManager;
    private BanManager ipBanManager;
    private WarnManager warnManager;
    private GuiPlayerHead playerHead;
    private GuiCreator guiCreator;
    private Inventory reasonsTimeInventory;
    private Inventory reasonsInventory;

    private Confirmable activeConfirmation;
    private Reason activeReason;
    private String violationReason;
    private int banTime;
    private GuiItem reasonStack_hacking;
    private GuiItem reasonStack_botting;
    private GuiItem reasonStack_unauthorized_alt_acc;
    private GuiItem reasonStack_spam;
    private GuiItem reasonStack_abusive_lang;
    private GuiItem reasonStack_scamming;
    private GuiItem reasonStack_griefing;
    private GuiItem not_selectedStack;
    private GuiItem selectedStack;
    private GuiItem m15Stack;
    private GuiItem h1Stack;
    private GuiItem h8Stack;
    private GuiItem d1Stack;
    private GuiItem d7Stack;
    private GuiItem M1Stack;
    private GuiItem permanentStack;
    private GuiItem verificationStack;

    public enum Confirmable {
        BAN,
        BAN_IP,
        WARN,
        KICK;
    }

    public ReasonsProfile(ProfileMenu2 profileMenu) {
        this.profileMenu = profileMenu;
        this.player = profileMenu.getPlayer();
        this.target = profileMenu.getTarget();
        nameBanManager = profileMenu.getNameBanManager();
        ipBanManager = profileMenu.getIpBanManager();
        warnManager = profileMenu.getWarnManager();
        guiCreator = new GuiCreator("reasons_profile", 6, "Choose a reason");
        profile = profileMenu.getProfile();
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

    public void renew(boolean useTime) {

        guiCreator.setPlayerHead(profileMenu.getPlayerHead());
        guiCreator.setCloseItem(new GuiCloseItem(49));
        guiCreator.setBackItem(new GuiBackItem(48));
        guiCreator.fillBackground(true);

        if (!useTime) {
            {
                reasonStack_hacking = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.HACKING())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.HACKING());

                activeReason = Reason.HACKING;

                guiCreator.setItemInSlot(reasonStack_hacking, 10);
            }
            {
                reasonStack_botting = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.BOTTING())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.BOTTING());

                activeReason = Reason.BOTTING;

                guiCreator.setItemInSlot(reasonStack_botting, 11);
            }
            {
                reasonStack_abusive_lang = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.ABUSIVE_LANGUAGE())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.ABUSIVE_LANGUAGE());

                activeReason = Reason.ABUSIVE_LANGUAGE;

                guiCreator.setItemInSlot(reasonStack_abusive_lang, 12);
            }
            {
                reasonStack_spam = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.SPAM())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.SPAM());

                activeReason = Reason.CHATFILL_SPAM;

                guiCreator.setItemInSlot(reasonStack_spam, 13);
            }
            {
                reasonStack_griefing = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.GRIEFING())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.GRIEFING());

                activeReason = Reason.GRIEFING_STEALING;

                guiCreator.setItemInSlot(reasonStack_griefing, 14);
            }
            {
                reasonStack_scamming = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.STEALING())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.STEALING());

                activeReason = Reason.SCAMMING_STEALING;

                guiCreator.setItemInSlot(reasonStack_scamming, 15);
            }
            {
                reasonStack_unauthorized_alt_acc = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.UNAUTHORIZED_ALTERNATE_ACCOUNT())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.UNAUTHORIZED_ALTERNATE_ACCOUNT());

                activeReason = Reason.UNAUTHORIZED_ALTERNATE_ACCOUNT;

                guiCreator.setItemInSlot(reasonStack_unauthorized_alt_acc, 16);
            }

            verificationStack = new GuiItem(Material.LIME_DYE)
                    .setDisplayName(ChatColor.GREEN + "Confirm");
            guiCreator.setItemInSlot(verificationStack, 50);

        } else {
            {
                reasonStack_hacking = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.HACKING())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.HACKING());

                activeReason = Reason.HACKING;

                guiCreator.setItemInSlot(reasonStack_hacking, 10);
            }
            {
                reasonStack_botting = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.BOTTING())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.BOTTING());

                activeReason = Reason.BOTTING;

                guiCreator.setItemInSlot(reasonStack_botting, 11);
            }
            {
                reasonStack_abusive_lang = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.ABUSIVE_LANGUAGE())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.ABUSIVE_LANGUAGE());

                activeReason = Reason.ABUSIVE_LANGUAGE;

                guiCreator.setItemInSlot(reasonStack_abusive_lang, 12);
            }
            {
                reasonStack_spam = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.SPAM())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.SPAM());

                activeReason = Reason.CHATFILL_SPAM;

                guiCreator.setItemInSlot(reasonStack_spam, 13);
            }
            {
                reasonStack_griefing = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.GRIEFING())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.GRIEFING());

                activeReason = Reason.GRIEFING_STEALING;

                guiCreator.setItemInSlot(reasonStack_griefing, 14);
            }
            {
                reasonStack_scamming = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.STEALING())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.STEALING());

                activeReason = Reason.SCAMMING_STEALING;

                guiCreator.setItemInSlot(reasonStack_scamming, 15);
            }
            {
                reasonStack_unauthorized_alt_acc = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.UNAUTHORIZED_ALTERNATE_ACCOUNT())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.UNAUTHORIZED_ALTERNATE_ACCOUNT());

                activeReason = Reason.UNAUTHORIZED_ALTERNATE_ACCOUNT;

                guiCreator.setItemInSlot(reasonStack_unauthorized_alt_acc, 16);
            }
            {
                m15Stack = new GuiItem(Material.CLOCK)
                        .setDisplayName(ChatColor.GOLD + "15 Minutes")
                        .setLore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "15 Minutes");

                guiCreator.setItemInSlot(m15Stack, 28);
            }
            {
                h1Stack = new GuiItem(Material.CLOCK)
                        .setDisplayName(ChatColor.GOLD + "1 Hours")
                        .setLore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "1 Hour");

                guiCreator.setItemInSlot(h1Stack, 29);
            }
            {
                h8Stack = new GuiItem(Material.CLOCK)
                        .setDisplayName(ChatColor.GOLD + "8 Hours")
                        .setLore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "8 Hours");

                guiCreator.setItemInSlot(h8Stack, 30);
            }
            {
                d1Stack = new GuiItem(Material.CLOCK)
                        .setDisplayName(ChatColor.GOLD + "1 Day")
                        .setLore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "1 Day");

                guiCreator.setItemInSlot(d1Stack, 31);
            }
            {
                d7Stack = new GuiItem(Material.CLOCK)
                        .setDisplayName(ChatColor.GOLD + "7 Days")
                        .setLore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "7 Days");

                guiCreator.setItemInSlot(d7Stack, 32);
            }
            {
                M1Stack = new GuiItem(Material.CLOCK)
                        .setDisplayName(ChatColor.GOLD + "1 Month")
                        .setLore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "1 Month");

                guiCreator.setItemInSlot(M1Stack, 33);
            }
            {
                permanentStack = new GuiItem(Material.CLOCK)
                        .setDisplayName(ChatColor.GOLD + "Permanent")
                        .setLore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "permanent");

                guiCreator.setItemInSlot(permanentStack, 34);
            }
            verificationStack = new GuiItem(Material.LIME_DYE)
                    .setDisplayName(ChatColor.GREEN + "Confirm");
            guiCreator.setItemInSlot(verificationStack, 50);
        }
        {
            not_selectedStack = new GuiItem(Material.RED_STAINED_GLASS_PANE)
                    .setDisplayName(ChatColor.RED + "Not selected");
            selectedStack = new GuiItem(Material.LIME_STAINED_GLASS_PANE)
                    .setDisplayName(ChatColor.GREEN + "Selected");
        }
        {
            for (int i = 19; i < 26; i++) {
                guiCreator.setItemInSlot(not_selectedStack, i);
                if (useTime) guiCreator.setItemInSlot(not_selectedStack, i + 18);
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Reason getActiveReason() {
        return activeReason;
    }

    public void setActiveReason(Reason activeReason) {
        this.activeReason = activeReason;
    }

    public String getViolationReason() {
        return violationReason;
    }

    public void setViolationReason(String violationReason) {
        this.violationReason = violationReason;
    }

    public int getBanTime() {
        return banTime;
    }

    public void setBanTime(int banTime) {
        this.banTime = banTime;
    }

    public GuiItem getReasonStack_hacking() {
        return reasonStack_hacking;
    }

    public void setReasonStack_hacking(GuiItem reasonStack_hacking) {
        this.reasonStack_hacking = reasonStack_hacking;
    }

    public GuiItem getReasonStack_botting() {
        return reasonStack_botting;
    }

    public void setReasonStack_botting(GuiItem reasonStack_botting) {
        this.reasonStack_botting = reasonStack_botting;
    }

    public GuiItem getReasonStack_unauthorized_alt_acc() {
        return reasonStack_unauthorized_alt_acc;
    }

    public void setReasonStack_unauthorized_alt_acc(GuiItem reasonStack_unauthorized_alt_acc) {
        this.reasonStack_unauthorized_alt_acc = reasonStack_unauthorized_alt_acc;
    }

    public GuiItem getReasonStack_spam() {
        return reasonStack_spam;
    }

    public void setReasonStack_spam(GuiItem reasonStack_spam) {
        this.reasonStack_spam = reasonStack_spam;
    }

    public GuiItem getReasonStack_abusive_lang() {
        return reasonStack_abusive_lang;
    }

    public void setReasonStack_abusive_lang(GuiItem reasonStack_abusive_lang) {
        this.reasonStack_abusive_lang = reasonStack_abusive_lang;
    }

    public GuiItem getReasonStack_scamming() {
        return reasonStack_scamming;
    }

    public void setReasonStack_scamming(GuiItem reasonStack_scamming) {
        this.reasonStack_scamming = reasonStack_scamming;
    }

    public GuiItem getReasonStack_griefing() {
        return reasonStack_griefing;
    }

    public void setReasonStack_griefing(GuiItem reasonStack_griefing) {
        this.reasonStack_griefing = reasonStack_griefing;
    }

    public GuiItem getNot_selectedStack() {
        return not_selectedStack;
    }

    public void setNot_selectedStack(GuiItem not_selectedStack) {
        this.not_selectedStack = not_selectedStack;
    }

    public GuiItem getSelectedStack() {
        return selectedStack;
    }

    public void setSelectedStack(GuiItem selectedStack) {
        this.selectedStack = selectedStack;
    }

    public GuiItem getM15Stack() {
        return m15Stack;
    }

    public void setM15Stack(GuiItem m15Stack) {
        this.m15Stack = m15Stack;
    }

    public GuiItem getH1Stack() {
        return h1Stack;
    }

    public void setH1Stack(GuiItem h1Stack) {
        this.h1Stack = h1Stack;
    }

    public GuiItem getH8Stack() {
        return h8Stack;
    }

    public void setH8Stack(GuiItem h8Stack) {
        this.h8Stack = h8Stack;
    }

    public GuiItem getD1Stack() {
        return d1Stack;
    }

    public void setD1Stack(GuiItem d1Stack) {
        this.d1Stack = d1Stack;
    }

    public GuiItem getD7Stack() {
        return d7Stack;
    }

    public void setD7Stack(GuiItem d7Stack) {
        this.d7Stack = d7Stack;
    }

    public GuiItem getM1Stack() {
        return M1Stack;
    }

    public void setM1Stack(GuiItem m1Stack) {
        M1Stack = m1Stack;
    }

    public GuiItem getPermanentStack() {
        return permanentStack;
    }

    public void setPermanentStack(GuiItem permanentStack) {
        this.permanentStack = permanentStack;
    }

    public GuiItem getVerificationStack() {
        return verificationStack;
    }

    public void setVerificationStack(GuiItem verificationStack) {
        this.verificationStack = verificationStack;
    }

    public GuiCreator getGuiCreator() {
        return guiCreator;
    }

    public void setGuiCreator(GuiCreator guiCreator) {
        this.guiCreator = guiCreator;
    }

    public void setActiveConfirmation(Confirmable activeConfirmation) {
        this.activeConfirmation = activeConfirmation;
    }

    public Confirmable getActiveConfirmation() {
        return activeConfirmation;
    }
}
