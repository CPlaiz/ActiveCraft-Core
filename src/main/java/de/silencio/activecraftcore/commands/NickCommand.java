package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class NickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            Player player = (Player) sender;


                if(args.length == 1) {
                    if(sender instanceof Player) {
                        if (sender.hasPermission("activecraft.nick")) {

                            FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName() + ".yml");

                            player.setDisplayName(args[0]);
                            player.setPlayerListName(args[0]);
                            playerdataConfig.set("nickname", args[0]);
                            playerdataConfig.saveConfig();
                            player.sendMessage(ChatColor.GOLD + "Nick set to " + ChatColor.AQUA + args[0]);
                        }
                    } else sender.sendMessage(Errors.INVALID_PLAYER);
                } else if(args.length == 2) {
                    if(sender.hasPermission("activecraft.nick.others")) {

                    Player target = Bukkit.getPlayer(args[0]);
                    FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + target.getName() + ".yml");

                    setDisplaycolorFromConfig(target, playerdataConfig.getString("colornick"));
                    playerdataConfig.set("nickname", args[1]);
                    playerdataConfig.saveConfig();
                    player.sendMessage(ChatColor.GOLD + "Set nickname of " + ChatColor.AQUA + target.getName() + ChatColor.GOLD + " to " + ChatColor.AQUA + args[1]);
                    target.sendMessage(ChatColor.GOLD + "Nickname set to " + ChatColor.AQUA + args[1] + ChatColor.GOLD + " by " + ChatColor.AQUA + player.getDisplayName());
                    target.setDisplayName(args[1]);
                    target.setPlayerListName(args[1]);
                } else sender.sendMessage(Errors.NO_PERMISSION);
            } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
        return true;
    }

    public void setDisplaycolorFromConfig(Player p, String s) {
        for (ChatColor color : ChatColor.values()) {
            if (s.toLowerCase().equals(color.name().toLowerCase())) {
                if (!s.equals("BOLD") && !s.equals("MAGIC") && !s.equals("STRIKETHROUGH") &&
                        !s.equals("ITALIC") && !s.equals("UNDERLINE") && !s.equals("RESET")) {
                    p.setDisplayName(color + p.getDisplayName());
                    p.setPlayerListName(color + p.getDisplayName());
                }
            }
        }
    }
}