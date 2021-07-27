package de.silencio.activecraftcore.messages;


import org.bukkit.ChatColor;

public interface Errors {

        String NO_PERMISSION = ChatColor.RED + "Warning!" + ChatColor.GRAY + " You don't have the permission to do that!";

        String INVALID_ARGUMENTS = ChatColor.RED + "Invalid arguments!" + ChatColor.GRAY + " Please recheck the command!";

        String TOO_MANY_ARGUMENTS = ChatColor.RED + "Too many arguments!" + ChatColor.GRAY + " Please recheck the command!";

        String INVALID_PLAYER = ChatColor.RED + "Invalid player!" + ChatColor.GRAY + " Please recheck the command!";

        String NOT_A_PLAYER = ChatColor.RED + "Warning!" + ChatColor.GRAY + " You are not a player!";

}
