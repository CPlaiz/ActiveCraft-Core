package de.silencio.activecraftcore.messages;


import org.bukkit.ChatColor;

public interface Errors {

        String NO_PERMISSION = ChatColor.RED + "Warning! " + ChatColor.GRAY + "You don't have the permission to do that!";

        String INVALID_ARGUMENTS = ChatColor.RED + "Warning! " + ChatColor.GRAY + "Invalid Arguments! Please recheck the command!";

        String TOO_MANY_ARGUMENTS = ChatColor.RED + "Warning! " + ChatColor.GRAY + "Too many Arguments! Please recheck the command!";

        String INVALID_PLAYER = ChatColor.RED + "Warning! " + ChatColor.GRAY + "Player not found!";

        String INVALID_NUMBER = ChatColor.RED + "Warning! " + ChatColor.GRAY + "This is not a valid number!";

        String NOT_A_PLAYER = ChatColor.RED + "Warning! " + ChatColor.GRAY + "You are not a player!";

        String WARNING = ChatColor.RED + "Warning! " + ChatColor.GRAY;


}
