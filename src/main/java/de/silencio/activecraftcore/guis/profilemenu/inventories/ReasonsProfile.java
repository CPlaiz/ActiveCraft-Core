package de.silencio.activecraftcore.guis.profilemenu.inventories;

import de.silencio.activecraftcore.guicreator.*;
import de.silencio.activecraftcore.guis.ProfileMenu;
import de.silencio.activecraftcore.manager.WarnManager;
import de.silencio.activecraftcore.messages.Reasons;
import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ReasonsProfile extends GuiCreator {

    private ProfileMenu profileMenu;
    private Player player;
    private Player target;
    private Profile profile;
    private WarnManager warnManager;
    private GuiPlayerHead playerHead;
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

    @Override
    public void refresh() {
    }

    public enum Confirmable {
        BAN,
        BAN_IP,
        WARN,
        KICK
    }

    public ReasonsProfile(ProfileMenu profileMenu) {
        super("reasons_profile", 6, "Choose a reason");
        this.profileMenu = profileMenu;
        this.player = profileMenu.getPlayer();
        this.target = profileMenu.getTarget();
        warnManager = profileMenu.getWarnManager();
        profile = profileMenu.getProfile();
        activeReason = Reason.MODERATOR;
    }
    public enum Reason {
        MODERATOR,
        HACKING,
        BOTTING,
        UNAUTHORIZED_ALTERNATE_ACCOUNT,
        CHATFILL_SPAM,
        ABUSIVE_LANGUAGE,
        SCAMMING_STEALING,
        GRIEFING_STEALING
    }

    
    public void renew(boolean useTime) {

        setPlayerHead(profileMenu.getPlayerHead());
        setCloseItem(new GuiCloseItem(49));
        setBackItem(new GuiBackItem(48));
        fillBackground(true);

        if (!useTime) {
            {
                reasonStack_hacking = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.HACKING())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.HACKING());

                setItemInSlot(reasonStack_hacking, 10);
            }
            {
                reasonStack_botting = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.BOTTING())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.BOTTING());

                setItemInSlot(reasonStack_botting, 11);
            }
            {
                reasonStack_abusive_lang = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.ABUSIVE_LANGUAGE())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.ABUSIVE_LANGUAGE());

                setItemInSlot(reasonStack_abusive_lang, 12);
            }
            {
                reasonStack_spam = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.SPAM())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.SPAM());

                setItemInSlot(reasonStack_spam, 13);
            }
            {
                reasonStack_griefing = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.GRIEFING())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.GRIEFING());

                setItemInSlot(reasonStack_griefing, 14);
            }
            {
                reasonStack_scamming = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.STEALING())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.STEALING());

                setItemInSlot(reasonStack_scamming, 15);
            }
            {
                reasonStack_unauthorized_alt_acc = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.UNAUTHORIZED_ALTERNATE_ACCOUNT())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.UNAUTHORIZED_ALTERNATE_ACCOUNT());

                setItemInSlot(reasonStack_unauthorized_alt_acc, 16);
            }

            verificationStack = new GuiItem(Material.LIME_DYE)
                    .setDisplayName(ChatColor.GREEN + "Confirm");
            setItemInSlot(verificationStack, 50);

            for (int i = 28; i < 35; i++) {
                setItemInSlot(null, i);
                setItemInSlot(null, i+9);
            }

        } else {
            {
                reasonStack_hacking = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.HACKING())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.HACKING());

                setItemInSlot(reasonStack_hacking, 10);
            }
            {
                reasonStack_botting = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.BOTTING())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.BOTTING());

                setItemInSlot(reasonStack_botting, 11);
            }
            {
                reasonStack_abusive_lang = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.ABUSIVE_LANGUAGE())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.ABUSIVE_LANGUAGE());

                setItemInSlot(reasonStack_abusive_lang, 12);
            }
            {
                reasonStack_spam = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.SPAM())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.SPAM());

                setItemInSlot(reasonStack_spam, 13);
            }
            {
                reasonStack_griefing = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.GRIEFING())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.GRIEFING());

                setItemInSlot(reasonStack_griefing, 14);
            }
            {
                reasonStack_scamming = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.STEALING())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.STEALING());

                setItemInSlot(reasonStack_scamming, 15);
            }
            {
                reasonStack_unauthorized_alt_acc = new GuiItem(Material.PAPER)
                        .setDisplayName(ChatColor.GOLD + Reasons.UNAUTHORIZED_ALTERNATE_ACCOUNT())
                        .setLore(ChatColor.GRAY + "Set the reason to " + ChatColor.DARK_AQUA + Reasons.UNAUTHORIZED_ALTERNATE_ACCOUNT());

                setItemInSlot(reasonStack_unauthorized_alt_acc, 16);
            }
            {
                m15Stack = new GuiItem(Material.CLOCK)
                        .setDisplayName(ChatColor.GOLD + "15 Minutes")
                        .setLore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "15 Minutes");

                setItemInSlot(m15Stack, 28);
            }
            {
                h1Stack = new GuiItem(Material.CLOCK)
                        .setDisplayName(ChatColor.GOLD + "1 Hours")
                        .setLore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "1 Hour");

                setItemInSlot(h1Stack, 29);
            }
            {
                h8Stack = new GuiItem(Material.CLOCK)
                        .setDisplayName(ChatColor.GOLD + "8 Hours")
                        .setLore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "8 Hours");

                setItemInSlot(h8Stack, 30);
            }
            {
                d1Stack = new GuiItem(Material.CLOCK)
                        .setDisplayName(ChatColor.GOLD + "1 Day")
                        .setLore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "1 Day");

                setItemInSlot(d1Stack, 31);
            }
            {
                d7Stack = new GuiItem(Material.CLOCK)
                        .setDisplayName(ChatColor.GOLD + "7 Days")
                        .setLore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "7 Days");

                setItemInSlot(d7Stack, 32);
            }
            {
                M1Stack = new GuiItem(Material.CLOCK)
                        .setDisplayName(ChatColor.GOLD + "1 Month")
                        .setLore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "1 Month");

                setItemInSlot(M1Stack, 33);
            }
            {
                permanentStack = new GuiItem(Material.CLOCK)
                        .setDisplayName(ChatColor.GOLD + "Permanent")
                        .setLore(ChatColor.GRAY + "Set the time to " + ChatColor.DARK_AQUA + "permanent");

                setItemInSlot(permanentStack, 34);
            }
            verificationStack = new GuiItem(Material.LIME_DYE)
                    .setDisplayName(ChatColor.GREEN + "Confirm");
            setItemInSlot(verificationStack, 50);
        }
        {
            not_selectedStack = new GuiItem(Material.RED_STAINED_GLASS_PANE)
                    .setDisplayName(ChatColor.RED + "Not selected");
            selectedStack = new GuiItem(Material.LIME_STAINED_GLASS_PANE)
                    .setDisplayName(ChatColor.GREEN + "Selected");
        }
        {
            for (int i = 19; i < 26; i++) {
                setItemInSlot(not_selectedStack, i);
                if (useTime) setItemInSlot(not_selectedStack, i + 18);
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

    public void setActiveConfirmation(Confirmable activeConfirmation) {
        this.activeConfirmation = activeConfirmation;
    }

    public Confirmable getActiveConfirmation() {
        return activeConfirmation;
    }
}
