package de.silencio.activecraftcore.ownlisteners;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface SocialSpyListener {

    public void onSocialSpy(CommandSender sender, Player target, String message);

}
