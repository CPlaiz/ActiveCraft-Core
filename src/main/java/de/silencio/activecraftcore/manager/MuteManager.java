package de.silencio.activecraftcore.manager;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.events.PlayerMuteEvent;
import de.silencio.activecraftcore.events.PlayerUnmuteEvent;
import de.silencio.activecraftcore.playermanagement.Profile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MuteManager {

    public static void mutePlayer(Player player) {
        //call event
        PlayerUnmuteEvent event = new PlayerUnmuteEvent(player);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;

        Profile profile = ActiveCraftCore.getProfile(player);
        profile.set(Profile.Value.MUTES, profile.getMutes() + 1);
        profile.set(Profile.Value.MUTED, true);
    }
    public static void unmutePlayer(Player player) {
        //call event
        PlayerMuteEvent event = new PlayerMuteEvent(player);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;

        Profile profile = ActiveCraftCore.getProfile(player);
        profile.set(Profile.Value.MUTED, false);

    }

}
