package de.silencio.activecraftcore.exceptions;

import org.bukkit.command.CommandSender;

public class SelfTargetException extends NoPermissionException {

    public SelfTargetException(String message, CommandSender commandSender, String permission) {
        super(message, commandSender, permission, false);
    }

    public SelfTargetException(CommandSender commandSender, String permission) {
        this(commandSender.getName() + "doesn't have the permission to target themself (\"" + permission + "\")", commandSender, permission);
    }

}
