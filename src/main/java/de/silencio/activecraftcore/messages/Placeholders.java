package de.silencio.activecraftcore.messages;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class Placeholders {
    //player
    public String replacePlaceholders(String target, Player player) {

        int dataSize = 1024 * 1024;

        Runtime runtime = Runtime.getRuntime();
        long usedram = runtime.totalMemory() - runtime.freeMemory();

        target = target.replace("%playername%", player.getName())
                                     .replace("%displayname% ", player.getDisplayName())
                                     .replace("%ping%", player.getDisplayName()) //ping !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                     .replace("%canpickupitems%", player.getCanPickupItems() + "")
                                     .replace("%exp%", player.getTotalExperience() + "")
                                     .replace("%flyspeed%", player.getFlySpeed() + "")
                                     .replace("%walkspeed%", player.getWalkSpeed() + "")
                                     .replace("%gamemode%", player.getGameMode() + "")
                                     .replace("%foodlevel%", player.getFoodLevel() + "")
                                     .replace("%healthlevel%", player.getHealth() + "")
                                     .replace("%allowflight% ", player.getAllowFlight() + "")
                                     .replace("%iswhitelisted%", player.isWhitelisted() + "")
                                     .replace("%isbanned%", player.isBanned() + "")
                                     .replace("%isflying%", player.isFlying() + "")
                                     .replace("%isinvehicle%", player.isInsideVehicle() + "")
                                     .replace("%isop%", player.isOp() + "")
                                     .replace("%timelived%", player.getTicksLived() + "")
                                     .replace("%uuid%", player.getUniqueId().toString() + "")
                                     .replace("%inworld%", player.getWorld().getName() + "")
                                     .replace("%coords%", player.getLocation() + "")
                                     .replace("%facing%", player.getFacing() + "")
                                     .replace("%maxplayers%", Bukkit.getMaxPlayers() + "")
                                     .replace("%whitelistenabled%", Bukkit.hasWhitelist() + "")
                                     .replace("%worldname%", player.getWorld().getName() + "")
                                     .replace("%worldseed%", player.getWorld().getSeed() + "")
                                     .replace("%pvpenabled%", player.getWorld().getPVP() + "")
                                     .replace("%difficulty%", player.getWorld().getDifficulty() + "")
                                     .replace("%ramused%", usedram/dataSize + "MB")
                                     .replace("%ramfree%", runtime.freeMemory()/dataSize + "MB")
                                     .replace("%ramtotal%", runtime.totalMemory()/dataSize + "MB")
                                     .replace("%ip%", player.getAddress() + "");

        return target;
    }

    //server
    public String replacePlaceholders(String target, Server server) {

        String editedMessage = target.replace("%servername%", server.getName());

        return editedMessage;
    }
}
