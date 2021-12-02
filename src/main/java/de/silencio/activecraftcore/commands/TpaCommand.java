package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;


public class TpaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {

            if(sender.hasPermission("activecraft.tpa")) {

                if(args.length == 1) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Errors.INVALID_PLAYER());
                        return false;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    Player player = (Player) sender;
                    TextComponent accept = new TextComponent(CommandMessages.TPA_ACCEPT() + " ");
                    accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(CommandMessages.TPA_ACCEPT_HOVER()).create()));
                    accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept"));

                    TextComponent deny = new TextComponent(CommandMessages.TPA_DENY());
                    deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(CommandMessages.TPA_DENY_HOVER()).create()));
                    deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpadeny"));

                    player.sendMessage(CommandMessages.TPA_REQUEST_TO(target));
                    target.sendMessage(" ");
                    target.sendMessage(CommandMessages.TPA_REQUEST_FROM(sender));
                    target.spigot().sendMessage(accept, deny);
                    target.sendMessage(" ");
                    HashMap<Player, Player> tpaList = ActiveCraftCore.getPlugin().getTpaList();
                    tpaList.put(target, player);
                    ActiveCraftCore.getPlugin().setTpaList(tpaList);

                } else sender.sendMessage(Errors.INVALID_ARGUMENTS());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else sender.sendMessage(Errors.NOT_A_PLAYER());
        return true;
    }
}