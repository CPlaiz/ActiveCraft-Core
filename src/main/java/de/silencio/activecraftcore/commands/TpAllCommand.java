package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TpAllCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if(sender instanceof Player) {
                Player player = (Player) sender;
                if (sender.hasPermission("activecraft.tpall")) {
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        if (!target.hasPermission("activecraft.tpall.exept")) {
                            target.teleport(player.getLocation());
                            target.sendMessage(CommandMessages.TPALL_MESSAGE(sender));
                        } else {
                            if(target != sender) {
                                target.sendMessage(CommandMessages.TPALL_EXEPT(sender));
                            }
                        }
                    }
                    sender.sendMessage(CommandMessages.TPALL());
                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }
}