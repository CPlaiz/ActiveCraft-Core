package de.silencio.activecraftcore.utils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class Placeholder {

    private String edited;

    public Player player;

    public String PLAYERNAME = player.getDisplayName();
    public String DISPLAYNAME = player.getDisplayName();
    public String PING = player.getDisplayName();
    public boolean CAN_PICKUP_ITEMS = player.getCanPickupItems();
    public String EXP = player.getDisplayName();
    public float FLYSPEED = player.getFlySpeed();
    public float WALKSPEED = player.getWalkSpeed();
    public GameMode GAMEMODE = player.getGameMode();
    public double FOOD_LEVEL = player.getHealth();
    public int HEALTH_LEVEL = player.getFoodLevel();
    public boolean ALLOW_FLIGHT = player.getAllowFlight();
    public boolean IS_WHITELISTED = player.isWhitelisted();
    public boolean IS_BANNED = player.isBanned();

    public Placeholder(Player p) {
        player = p;
    }

    public String replace(String string) {

        switch (string) {
            case "%displayname%":
                String edited = string.replace("%displayname%",  DISPLAYNAME);

        }



        return edited;
    }
}
