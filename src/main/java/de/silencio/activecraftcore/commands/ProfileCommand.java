package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.guis.ProfileMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ProfileCommand extends ActiveCraftCommand {

    public ProfileCommand() {
        super("profile");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        Player player = getPlayer(sender);
        checkPermission(sender, "profile");
        if (args.length == 0) {
            ProfileMenu profileMenu = new ProfileMenu(player, player);
            ActiveCraftCore.addToProfileMenuList(player, profileMenu);
            player.openInventory(profileMenu.getMainProfile().build().getInventory());
        } else if (args.length == 1) {
            Player target = getPlayer(args[0]);
            ProfileMenu profileMenu = new ProfileMenu(player, target);
            ActiveCraftCore.addToProfileMenuList(player, profileMenu);
            player.openInventory(profileMenu.getMainProfile().build().getInventory());
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}
