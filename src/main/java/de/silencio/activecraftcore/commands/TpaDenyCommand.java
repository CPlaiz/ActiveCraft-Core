package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.naming.ldap.HasControls;
import java.util.HashMap;

public class TpaDenyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        HashMap<Player, Player> tpaList = Main.getPlugin().getTpaList();

        if(sender instanceof Player) {
            if(args.length == 0) {
                if (sender.hasPermission("activecraft.tpadeny")) {
                    if (tpaList.containsKey(sender)) {

                        Player player = (Player) sender;
                        Player target = tpaList.get(sender);

                        player.sendMessage(CommandMessages.TPADENY_RECIEVER_MESSAGE(sender));
                        target.sendMessage(CommandMessages.TPADENY_SENDER_MESSAGE(target));

                        Main.getPlugin().removeFromTpaList((Player) sender);

                    } else sender.sendMessage(Errors.WARNING() + CommandMessages.NO_REQUESTS_DENY());
                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }
}