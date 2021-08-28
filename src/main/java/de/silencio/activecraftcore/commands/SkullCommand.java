package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                if (sender.hasPermission("activecraft.skull.self")) {

                    ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
                    SkullMeta meta = (SkullMeta) skull.getItemMeta();
                    meta.setOwner(player.getName());
                    skull.setItemMeta(meta);
                    player.getInventory().addItem(skull);

                } else sender.sendMessage(Errors.NO_PERMISSION);
            }

            if (args.length == 1) {
                if (sender.hasPermission("activecraft.skull.others")) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Errors.INVALID_PLAYER);
                        return false;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                        sender.sendMessage(Errors.CANNOT_TARGET_SELF);
                        return false;
                    }
                    ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
                    SkullMeta meta = (SkullMeta) skull.getItemMeta();
                    meta.setOwner(target.getName());
                    skull.setItemMeta(meta);
                    player.getInventory().addItem(skull);

                } else sender.sendMessage(Errors.NO_PERMISSION);
            }

        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
