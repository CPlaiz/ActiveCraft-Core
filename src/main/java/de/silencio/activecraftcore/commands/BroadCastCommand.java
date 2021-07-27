package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadCastCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("activecraft.broadcast")) {

            if(args.length >= 1) {
                String msg = "";
                for(int i = 0; i< args.length;i++) {
                    msg = msg + args[i] + " ";
                }

                Bukkit.broadcastMessage("§6[BroadCast]§r " + msg);

            } else sender.sendMessage(ChatColor.GOLD + "Usage: /broadcast <Message>.");

        } else sender.sendMessage(Errors.NO_PERMISSION);

        return true;
    }
}
