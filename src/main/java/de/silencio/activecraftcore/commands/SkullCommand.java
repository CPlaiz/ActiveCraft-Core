package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class SkullCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length == 0) {
                if (sender.hasPermission("activecraft.skull")) {

                    ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
                    SkullMeta meta = (SkullMeta) skull.getItemMeta();
                    meta.setOwner(player.getName());
                    skull.setItemMeta(meta);
                    player.getInventory().addItem(skull);

                } else sender.sendMessage(Errors.NO_PERMISSION);
            }

            if(args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (sender.hasPermission("activecraft.skull.other")) {

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
