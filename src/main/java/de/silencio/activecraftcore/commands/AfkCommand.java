package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import de.silencio.activecraftcore.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class AfkCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfig fileConfig = new FileConfig("config.yml");
            
            if(args.length == 0) {
                if(sender instanceof Player) {
                    Player player = (Player) sender;
                    FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + player.getName().toLowerCase() + ".yml");
                    if (sender.hasPermission("activecraft.afk.self")) {
                        
                        if(!playerdataConfig.getBoolean("afk")) {
                            setDisplaynameFromConfig(player, playerdataConfig.getString("colornick"), playerdataConfig.getString("nickname") + ChatColor.GRAY + " " + fileConfig.getString("afk-format"));
                            //player.setDisplayName(playerdataConfig.getString("nickname")+ ChatColor.GRAY + " " + fileConfig.getString("afk-format"));
                            player.sendMessage(ChatColor.GOLD + "You are now afk.");
                            playerdataConfig.set("afk", true);
                            playerdataConfig.saveConfig();

                            if(fileConfig.getBoolean("announce-afk")) {
                                Bukkit.broadcastMessage(ChatColor.GRAY + "" + MessageUtils.removeColorAndFormat(fileConfig.getString("afk-format-yes").replace("%displayname%", player.getDisplayName())));
                            }

                        } else if(playerdataConfig.getBoolean("afk")) {
                            setDisplaynameFromConfig(player, playerdataConfig.getString("colornick"), playerdataConfig.getString("nickname"));
                            //player.setDisplayName(playerdataConfig.getString("nickname"));
                            player.sendMessage(ChatColor.GOLD + "You are no longer afk.");
                            playerdataConfig.set("afk", false);
                            playerdataConfig.saveConfig();

                            if(fileConfig.getBoolean("announce-afk")) {
                                Bukkit.broadcastMessage(ChatColor.GRAY + "" + MessageUtils.removeColorAndFormat(fileConfig.getString("afk-format-no").replace("%displayname%", player.getDisplayName())));
                            }
                        }
                    } else sender.sendMessage(Errors.NO_PERMISSION);
                } else sender.sendMessage(Errors.NOT_A_PLAYER);
            } else if(args.length == 1) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER);
                    return false;
                }
                Player target = Bukkit.getPlayer(args[0]);
                FileConfig playerdataConfig = new FileConfig("playerdata" + File.separator + target.getName().toLowerCase() + ".yml");
                if (sender.hasPermission("activecraft.afk.others")) {
                    if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                        sender.sendMessage(Errors.CANNOT_TARGET_SELF);
                        return false;
                    }
                    if(!playerdataConfig.getBoolean("afk")) {
                        setDisplaynameFromConfig(target, playerdataConfig.getString("colornick"), playerdataConfig.getString("nickname") + ChatColor.GRAY + " " + fileConfig.getString("afk-format"));
                        //target.setDisplayName(playerdataConfig.getString("nickname") + ChatColor.GRAY + " " + fileConfig.getString("afk-format"));
                        target.sendMessage(ChatColor.GOLD + "You are now afk.");
                        sender.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " is now afk.");
                        playerdataConfig.set("afk", true);
                        playerdataConfig.saveConfig();

                        if(fileConfig.getBoolean("announce-afk")) {
                            Bukkit.broadcastMessage(ChatColor.GRAY + "" + MessageUtils.removeColorAndFormat(fileConfig.getString("afk-format-yes").replace("%displayname%", target.getDisplayName())));
                        }

                    } else if(playerdataConfig.getBoolean("afk")) {
                        setDisplaynameFromConfig(target, playerdataConfig.getString("colornick"), playerdataConfig.getString("nickname"));

                        //target.setDisplayName(playerdataConfig.getString("nickname"));
                        target.sendMessage(ChatColor.GOLD + "You are no longer afk.");
                        sender.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.GOLD + " is no longer afk.");
                        playerdataConfig.set("afk", false);
                        playerdataConfig.saveConfig();

                        if(fileConfig.getBoolean("announce-afk")) {
                            Bukkit.broadcastMessage(ChatColor.GRAY + "" + MessageUtils.removeColorAndFormat(fileConfig.getString("afk-format-no").replace("%displayname%", target.getDisplayName())));
                        }
                    }
                } else sender.sendMessage(Errors.NO_PERMISSION);
            } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
        return true;
    }

    public void setDisplaynameFromConfig(Player p, String colorname, String displayname) {
        for (ChatColor color : ChatColor.values()) {
            if (colorname.toLowerCase().equals(color.name().toLowerCase())) {
                if (!colorname.equals("BOLD") && !colorname.equals("MAGIC") && !colorname.equals("STRIKETHROUGH") &&
                        !colorname.equals("ITALIC") && !colorname.equals("UNDERLINE") && !colorname.equals("RESET")) {
                    p.setDisplayName(color + displayname);
                    p.setPlayerListName(color + displayname);
                }
            }
        }
    }
}