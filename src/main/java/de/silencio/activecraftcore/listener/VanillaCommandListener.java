package de.silencio.activecraftcore.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class VanillaCommandListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerExecuteCommand(PlayerCommandPreprocessEvent event) {
        
        String message = event.getMessage().replace("/", "");
        System.out.println(message);

        String[] args;

        if (message.contains(" ")) {
            args = message.split(" ");
        } else {
            args = new String[1];
            args[0] = message;
        }

        System.out.println(args[0]);

        if (args[0].equalsIgnoreCase("pl")  || args[0].equalsIgnoreCase("plugins") ||
                args[0].equalsIgnoreCase("bukkit:pl") || args[0].equalsIgnoreCase("bukkit:plugins")) {
            if(!event.getPlayer().hasPermission("activecraft.vanilla.plugins")) {
                event.getPlayer().sendMessage("§cWarning! §7You don't have the permission to do that!");
                event.setCancelled(true);
            }
        }

        if (args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help") ||
                args[0].equalsIgnoreCase("bukkit:?") || args[0].equalsIgnoreCase("bukkit:help")) {
            if(!event.getPlayer().hasPermission("activecraft.vanilla.help")) {
                event.getPlayer().sendMessage("§cWarning! §7You don't have the permission to do that!");
                event.setCancelled(true);
            }
        }

        if (args[0].equalsIgnoreCase("icanhasbukkit") || args[0].equalsIgnoreCase("about") ||
                args[0].equalsIgnoreCase("bukkit:icanhasbukkit") || args[0].equalsIgnoreCase("bukkit:about")) {
            if(!event.getPlayer().hasPermission("activecraft.vanilla.about")) {
                event.getPlayer().sendMessage("§cWarning! §7You don't have the permission to do that!");
                event.setCancelled(true);
            }
        }

        if (args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("minecraft:list")) {
            if(!event.getPlayer().hasPermission("activecraft.vanilla.list")) {
                event.getPlayer().sendMessage("§cWarning! §7You don't have the permission to do that!");
                event.setCancelled(true);
            }
        }

        if (args[0].equalsIgnoreCase("me") || args[0].equalsIgnoreCase("minecraft:me")) {
            if(!event.getPlayer().hasPermission("activecraft.vanilla.me")) {
                event.getPlayer().sendMessage("§cWarning! §7You don't have the permission to do that!");
                event.setCancelled(true);
            }
        }

        if (args[0].equalsIgnoreCase("teammsg") || args[0].equalsIgnoreCase("tm") ||
                args[0].equalsIgnoreCase("minecraft:teammsg") || args[0].equalsIgnoreCase("minecraft:tm")) {
            if(!event.getPlayer().hasPermission("activecraft.vanilla.teammsg")) {
                event.getPlayer().sendMessage("§cWarning! §7You don't have the permission to do that!");
                event.setCancelled(true);
            }
        }

        if (args[0].equalsIgnoreCase("tell") || args[0].equalsIgnoreCase("w") ||
                args[0].equalsIgnoreCase("minecraft:tell") || args[0].equalsIgnoreCase("minecraft:w")) {
            if(!event.getPlayer().hasPermission("activecraft.vanilla.msg")) {
                event.getPlayer().sendMessage("§cWarning! §7You don't have the permission to do that!");
                event.setCancelled(true);
            }
        }

        if (args[0].equalsIgnoreCase("trigger") || args[0].equalsIgnoreCase("minecraft:trigger")) {
            if(!event.getPlayer().hasPermission("activecraft.vanilla.trigger")) {
                event.getPlayer().sendMessage("§cWarning! §7You don't have the permission to do that!");
                event.setCancelled(true);
            }
        }

        if (args[0].equalsIgnoreCase("ver") || args[0].equalsIgnoreCase("version") ||
                message.equalsIgnoreCase("bukkit:ver") || message.equalsIgnoreCase("bukkit:version")) {
            if(!event.getPlayer().hasPermission("activecraft.vanilla.version")) {
                event.getPlayer().sendMessage("§cWarning! §7You don't have the permission to do that!");
                event.setCancelled(true);
            }
        }
    }
}