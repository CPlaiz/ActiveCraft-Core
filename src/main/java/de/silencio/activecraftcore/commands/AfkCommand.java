package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.MessageCutter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class AfkCommand implements CommandExecutor {

    private MessageCutter messageCutter = new MessageCutter();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfig fileConfig = new FileConfig("config.yml");
            
            if(args.length == 0) {
                if(sender instanceof Player) {
                    Player player = (Player) sender;
                    FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName() + ".yml");
                    if (sender.hasPermission("activecraft.afk")) {
                        
                        if(!playerdataConfig.getBoolean("afk")) {
                            player.setPlayerListName(playerdataConfig.get("nickname") + "" + ChatColor.GRAY + " " +  fileConfig.get("afk-format"));
                            player.setPlayerListName(playerdataConfig.get("nickname") + "" + ChatColor.GRAY + " " + fileConfig.get("afk-format"));
                            player.sendMessage(ChatColor.GOLD + "You are now afk.");
                            playerdataConfig.set("afk", true);
                            playerdataConfig.saveConfig();

                            if(fileConfig.getBoolean("announce-afk")) {
                                Bukkit.broadcastMessage(ChatColor.GRAY + "" + messageCutter.removeColorAndFormat(fileConfig.getString("afk-format-yes").replace("%displayname%", player.getDisplayName())));
                            }

                        } else if(playerdataConfig.getBoolean("afk")) {
                            player.setPlayerListName(playerdataConfig.getString("nickname").toString());
                            player.setPlayerListName(playerdataConfig.getString("nickname").toString());
                            player.sendMessage(ChatColor.GOLD + "You are no longer afk.");
                            playerdataConfig.set("afk", false);
                            playerdataConfig.saveConfig();

                            if(fileConfig.getBoolean("announce-afk")) {
                                Bukkit.broadcastMessage(ChatColor.GRAY + "" + messageCutter.removeColorAndFormat(fileConfig.getString("afk-format-no").replace("%displayname%", player.getDisplayName())));
                            }
                        }
                    } else sender.sendMessage(Errors.NO_PERMISSION);
                } else sender.sendMessage(Errors.NOT_A_PLAYER);
            } else if(args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + target.getName() + ".yml");
                if (sender.hasPermission("activecraft.afk.other")) {

                    if(!playerdataConfig.getBoolean("afk")) {
                        target.setPlayerListName(playerdataConfig.getString("nickname") + "" + ChatColor.GRAY + " " + fileConfig.getString("afk-format"));
                        target.setPlayerListName(playerdataConfig.getString("nickname") + "" + ChatColor.GRAY + " " + fileConfig.getString("afk-format"));
                        target.sendMessage(ChatColor.GOLD + "You are now afk.");
                        playerdataConfig.set("afk", true);
                        playerdataConfig.saveConfig();

                        if(fileConfig.getBoolean("announce-afk")) {
                            Bukkit.broadcastMessage(ChatColor.GRAY + "" + messageCutter.removeColorAndFormat(fileConfig.getString("afk-format-yes").replace("%displayname%", target.getDisplayName())));
                        }

                    } else if(playerdataConfig.getBoolean("afk")) {
                        target.setPlayerListName(playerdataConfig.getString("nickname").toString());
                        target.setPlayerListName(playerdataConfig.getString("nickname").toString());
                        target.sendMessage(ChatColor.GOLD + "You are no longer afk.");
                        playerdataConfig.set("afk", false);
                        playerdataConfig.saveConfig();

                        if(fileConfig.getBoolean("announce-afk")) {
                            Bukkit.broadcastMessage(ChatColor.GRAY + "" + messageCutter.removeColorAndFormat(fileConfig.getString("afk-format-no").replace("%displayname%", target.getDisplayName())));
                        }
                    }
                } else sender.sendMessage(Errors.NO_PERMISSION);
            } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
        return true;
    }
}