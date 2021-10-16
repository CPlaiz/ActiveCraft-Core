package de.silencio.activecraftcore.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class ProfileList {

    public HashMap<Player, ProfileMenu> profileList = new HashMap<>();

    public void add(Player player, ProfileMenu profileMenu) {
        profileList.put(player, profileMenu);
    }

    public HashMap<Player, ProfileMenu> getProfileList() {
        return profileList;
    }
}
