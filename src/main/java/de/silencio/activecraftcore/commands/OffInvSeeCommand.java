package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.Objects;

public class OffInvSeeCommand implements CommandExecutor, Listener {

    private Inventory armorinv;
    private Player target;
    private Player player;



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            if (sender.hasPermission("activecraft.invsee")) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER);
                    return false;
                }
                target = Bukkit.getPlayer(args[0]);
                this.player = (Player) sender;

                this.armorinv = Bukkit.createInventory(null, 9, "§6Armor");

                this.armorinv.setItem(4, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE)
                        .displayname("§6Survival")
                        .lore("§7Die Surivival Welt")
                        .build());
                this.armorinv.setItem(6, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE)
                        .displayname("§6Survival")
                        .lore("§7Die Surivival Welt")
                        .build());
                this.armorinv.setItem(7, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE)
                        .displayname("§6Survival")
                        .lore("§7Die Surivival Welt")
                        .build());
                this.armorinv.setItem(8, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE)
                        .displayname("§6Survival")
                        .lore("§7Die Surivival Welt")
                        .build());

                this.armorinv.setItem(0, target.getInventory().getHelmet());
                this.armorinv.setItem(1, target.getInventory().getChestplate());
                this.armorinv.setItem(2, target.getInventory().getLeggings());
                this.armorinv.setItem(3, target.getInventory().getBoots());
                this.armorinv.setItem(5, target.getInventory().getItemInOffHand());

                ((Player) sender).openInventory(this.armorinv);
            } else sender.sendMessage(Errors.NO_PERMISSION);
        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }

    //@EventHandler
    private void onItemClick(InventoryClickEvent event) {
        if (Objects.equals(event.getClickedInventory(), this.armorinv)) {
            if (event.getCurrentItem().getType() == Material.GRAY_STAINED_GLASS_PANE) {
                event.setCancelled(true);
            } else {
                target.getInventory().setHelmet(armorinv.getItem(0));
                target.getInventory().setChestplate(armorinv.getItem(1));
                target.getInventory().setLeggings(armorinv.getItem(2));
                target.getInventory().setBoots(armorinv.getItem(3));
                target.getInventory().setItemInOffHand(armorinv.getItem(5));
            }
        }
    }

    //@EventHandler
    private void onInvClose(InventoryCloseEvent event) {
        if (Objects.equals(event.getInventory(), this.armorinv)) {
            target.getInventory().setHelmet(armorinv.getItem(0));
            target.getInventory().setChestplate(armorinv.getItem(1));
            target.getInventory().setLeggings(armorinv.getItem(2));
            target.getInventory().setBoots(armorinv.getItem(3));
            target.getInventory().setItemInOffHand(armorinv.getItem(5));
        }
    }
}
