package de.silencio.activecraftcore.utils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class Placeholder {

    private String edited;

        Type() {
        }
    }

    private Player player;
    private String edited = "";


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
