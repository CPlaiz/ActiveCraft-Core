package de.silencio.activecraftcore.profilemenu.listeners;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.gui.Gui;
import de.silencio.activecraftcore.gui.GuiClickEvent;
import de.silencio.activecraftcore.gui.GuiItem;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.messages.Reasons;
import de.silencio.activecraftcore.profilemenu.ProfileMenu2;
import de.silencio.activecraftcore.profilemenu.inventories.ReasonsProfile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Date;

public class ReasonsProfileListener implements Listener {

    @EventHandler
    public void onSelect(GuiClickEvent event) {
        if (!Main.getPlugin().getProfileMenuList().containsKey((Player) event.getView().getPlayer())) return;
        Player player = (Player) event.getView().getPlayer();
        ProfileMenu2 profileMenu = Main.getPlugin().getFromProfileMenuList(player);
        Gui gui = event.getGui();

        if (!event.getGui().getAssociatedGuiCreator().getInternalName().equals("reasons_profile")) return;

        ReasonsProfile reasonsProfile = profileMenu.getReasonsProfile();

        GuiItem item = event.getCurrentItem();

        boolean needRefresh = true;

        if (item == reasonsProfile.getM15Stack()) {
            reasonsProfile.setBanTime(15);
            clearSelection(37, 44, event.getSlot(), profileMenu);
            reasonsProfile.getGuiCreator().setItemInSlot(reasonsProfile.getSelectedStack(), event.getSlot() + 9);
        } else if (item == reasonsProfile.getH1Stack()) {
            reasonsProfile.setBanTime(60);
            clearSelection(37, 44, event.getSlot(), profileMenu);
            reasonsProfile.getGuiCreator().setItemInSlot(reasonsProfile.getSelectedStack(), event.getSlot() + 9);
        } else if (item == reasonsProfile.getH8Stack()) {
            reasonsProfile.setBanTime(480);
            clearSelection(37, 44, event.getSlot(), profileMenu);
            reasonsProfile.getGuiCreator().setItemInSlot(reasonsProfile.getSelectedStack(), event.getSlot() + 9);
        } else if (item == reasonsProfile.getD1Stack()) {
            reasonsProfile.setBanTime(1440);
            clearSelection(37, 44, event.getSlot(), profileMenu);
            reasonsProfile.getGuiCreator().setItemInSlot(reasonsProfile.getSelectedStack(), event.getSlot() + 9);
        } else if (item == reasonsProfile.getD7Stack()) {
            reasonsProfile.setBanTime(10080);
            clearSelection(37, 244, event.getSlot(), profileMenu);
            reasonsProfile.getGuiCreator().setItemInSlot(reasonsProfile.getSelectedStack(), event.getSlot() + 9);
        } else if (item == reasonsProfile.getM1Stack()) {
            reasonsProfile.setBanTime(302400);
            clearSelection(37, 44, event.getSlot(), profileMenu);
            reasonsProfile.getGuiCreator().setItemInSlot(reasonsProfile.getSelectedStack(), event.getSlot() + 9);
        } else if (item == reasonsProfile.getPermanentStack()) {
            reasonsProfile.setBanTime(-1);
            clearSelection(37, 44, event.getSlot(), profileMenu);
            reasonsProfile.getGuiCreator().setItemInSlot(reasonsProfile.getSelectedStack(), event.getSlot() + 9);
        } else if (item == reasonsProfile.getReasonStack_abusive_lang()) {
            reasonsProfile.setActiveReason(ReasonsProfile.Reason.ABUSIVE_LANGUAGE);
            reasonsProfile.setViolationReason(Reasons.ABUSIVE_LANGUAGE());
            clearSelection(19, 26, event.getSlot(), profileMenu);
            reasonsProfile.getGuiCreator().setItemInSlot(reasonsProfile.getSelectedStack(), event.getSlot() + 9);
        } else if (item == reasonsProfile.getReasonStack_botting()) {
            reasonsProfile.setActiveReason(ReasonsProfile.Reason.BOTTING);
            reasonsProfile.setViolationReason(Reasons.BOTTING());
            clearSelection(19, 26, event.getSlot(), profileMenu);
            reasonsProfile.getGuiCreator().setItemInSlot(reasonsProfile.getSelectedStack(), event.getSlot() + 9);
        } else if (item == reasonsProfile.getReasonStack_hacking()) {
            reasonsProfile.setActiveReason(ReasonsProfile.Reason.HACKING);
            reasonsProfile.setViolationReason(Reasons.HACKING());
            clearSelection(19, 26, event.getSlot(), profileMenu);
            reasonsProfile.getGuiCreator().setItemInSlot(reasonsProfile.getSelectedStack(), event.getSlot() + 9);
        } else if (item == reasonsProfile.getReasonStack_griefing()) {
            reasonsProfile.setActiveReason(ReasonsProfile.Reason.GRIEFING_STEALING);
            reasonsProfile.setViolationReason(Reasons.GRIEFING());
            clearSelection(19, 26, event.getSlot(), profileMenu);
            reasonsProfile.getGuiCreator().setItemInSlot(reasonsProfile.getSelectedStack(), event.getSlot() + 9);
        } else if (item == reasonsProfile.getReasonStack_spam()) {
            reasonsProfile.setActiveReason(ReasonsProfile.Reason.CHATFILL_SPAM);
            reasonsProfile.setViolationReason(Reasons.SPAM());
            clearSelection(19, 26, event.getSlot(), profileMenu);
            reasonsProfile.getGuiCreator().setItemInSlot(reasonsProfile.getSelectedStack(), event.getSlot() + 9);
        } else if (item == reasonsProfile.getReasonStack_scamming()) {
            reasonsProfile.setActiveReason(ReasonsProfile.Reason.SCAMMING_STEALING);
            reasonsProfile.setViolationReason(Reasons.STEALING());
            clearSelection(19, 26, event.getSlot(), profileMenu);
            reasonsProfile.getGuiCreator().setItemInSlot(reasonsProfile.getSelectedStack(), event.getSlot() + 9);
        } else if (item == reasonsProfile.getReasonStack_unauthorized_alt_acc()) {
            reasonsProfile.setActiveReason(ReasonsProfile.Reason.UNAUTHORIZED_ALTERNATE_ACCOUNT);
            reasonsProfile.setViolationReason(Reasons.UNAUTHORIZED_ALTERNATE_ACCOUNT());
            clearSelection(19, 26, event.getSlot(), profileMenu);
            reasonsProfile.getGuiCreator().setItemInSlot(reasonsProfile.getSelectedStack(), event.getSlot() + 9);
        } else if (item == profileMenu.getReasonsProfile().getVerificationStack()) {
            needRefresh = false;
            switch (reasonsProfile.getActiveConfirmation()) {
                case BAN:
                    if (player.hasPermission("activecraft.ban")) {
                        if (profileMenu.getReasonsProfile().getBanTime() == -1) {
                            profileMenu.getNameBanManager().ban(profileMenu.getTarget(), reasonsProfile.getViolationReason(), null, player.getName());
                            profileMenu.getTarget().kickPlayer(reasonsProfile.getViolationReason());
                        } else {
                            Date nowDate = new Date();
                            long nowMillis = nowDate.getTime();
                            Date expires = new Date((long) profileMenu.getReasonsProfile().getBanTime() * 60 * 1000 + nowMillis);
                            profileMenu.getNameBanManager().ban(profileMenu.getTarget(), profileMenu.getReasonsProfile().getViolationReason(), expires, player.getName());
                            profileMenu.getTarget().kickPlayer(profileMenu.getReasonsProfile().getViolationReason());
                        }
                        player.openInventory(Main.getPlugin().getGuiHistoryMap().getGuiStack(player).pop());
                    } else player.sendMessage(Errors.NO_PERMISSION());
                    break;
                case BAN_IP:
                    if (player.hasPermission("activecraft.ban")) {
                        if (profileMenu.getReasonsProfile().getBanTime() == -1) {
                            profileMenu.getNameBanManager().ban(profileMenu.getTarget(), reasonsProfile.getViolationReason(), null, player.getName());
                            profileMenu.getTarget().kickPlayer(reasonsProfile.getViolationReason());
                        } else {
                            Date nowDate = new Date();
                            long nowMillis = nowDate.getTime();
                            Date expires = new Date((long) profileMenu.getReasonsProfile().getBanTime() * 60 * 1000 + nowMillis);
                            profileMenu.getIpBanManager().ban(profileMenu.getTarget().getAddress().getAddress().toString().replace("/", ""),
                                    reasonsProfile.getViolationReason(), expires, player.getName());
                            profileMenu.getTarget().kickPlayer(profileMenu.getReasonsProfile().getViolationReason());
                        }
                        player.openInventory(Main.getPlugin().getGuiHistoryMap().getGuiStack(player).pop());
                    } else player.sendMessage(Errors.NO_PERMISSION());
                    break;
                case WARN:
                    if (player.hasPermission("activecraft.warn")) {
                        player.performCommand("warn add " + profileMenu.getTarget().getName() + " " + reasonsProfile.getViolationReason());
                        player.openInventory(Main.getPlugin().getGuiHistoryMap().getGuiStack(player).pop());
                    } else player.sendMessage(Errors.NO_PERMISSION());
                    break;
                case KICK:
                    if (player.hasPermission("activecraft.kick")) {
                        profileMenu.getTarget().kickPlayer(reasonsProfile.getViolationReason());
                        player.openInventory(Main.getPlugin().getGuiHistoryMap().getGuiStack(player).pop());
                    } else player.sendMessage(Errors.NO_PERMISSION());
                    break;
            }
        }
        profileMenu.setReasonsProfile(reasonsProfile);
        player.openInventory(profileMenu.getReasonsProfile().getGuiCreator().build().getInventory());
    }

    private void clearSelection(int start, int end, int slot, ProfileMenu2 profileMenu) {
        for (int i = start; i < end; i++) {
            if (!(i == slot)) {
                profileMenu.getReasonsProfile().getGuiCreator().setItemInSlot(
                        profileMenu.getReasonsProfile().getNot_selectedStack(), i);
            }
        }
    }

}
