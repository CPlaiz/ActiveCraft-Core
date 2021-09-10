package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;


public class TpaCommand implements CommandExecutor {

    public static HashMap<Player, Player> tpaList = new HashMap<Player, Player>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {

            if(sender.hasPermission("activecraft.tpa")) {

                if(args.length == 1) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Errors.INVALID_PLAYER);
                        return false;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    Player player = (Player) sender;
                    TextComponent accept = new TextComponent(ChatColor.GREEN + "[ACCEPT]");
                    accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GREEN + "Click here to accept.").create()));
                    accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept"));

                    TextComponent deny = new TextComponent(ChatColor.RED + "[DENY]");
                    deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.RED + "Click here to deny.").create()));
                    deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpadeny"));

                    player.sendMessage(ChatColor.GOLD + "Tpa request sent to " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + ".");
                    target.sendMessage(" ");
                    target.sendMessage(ChatColor.GOLD + "Tpa request from " + ChatColor.AQUA + player.getDisplayName() + ChatColor.GOLD + ".");
                    target.spigot().sendMessage(accept, deny);
                    target.sendMessage(" ");

                    tpaList.put(target, player);

                } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
            } else sender.sendMessage(Errors.NO_PERMISSION);
        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
