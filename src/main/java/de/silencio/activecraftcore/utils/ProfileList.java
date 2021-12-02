package de.silencio.activecraftcore.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class ProfileList {

    public HashMap<Player, OldProfileMenu> profileList = new HashMap<>();

    public void add(Player player, OldProfileMenu profileMenu) {
        profileList.put(player, profileMenu);
    }

    public HashMap<Player, OldProfileMenu> getProfileList() {
        return profileList;
    }
}
