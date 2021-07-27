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
import org.bukkit.inventory.Inventory;

import java.util.Objects;

public class OffInvSeeCommand implements CommandExecutor, Listener {

    private final Inventory armorinv;

    public OffInvSeeCommand() {
        this.armorinv = Bukkit.createInventory(null, 9, "§6Armor");

        this.armorinv.setItem(0, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE)
                .displayname("§6Survival")
                .lore("§7Die Surivival Welt")
                .build());
        this.armorinv.setItem(1, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE)
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
    }

    @EventHandler
    private void onItemClick(InventoryClickEvent event) {
        if (Objects.equals(event.getClickedInventory(), this.armorinv)) {
            event.setCancelled(true);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            if (sender.hasPermission("activecraft.invsee")) {
                Player target = Bukkit.getPlayer(args[0]);

                this.armorinv.setItem(2, target.getInventory().getHelmet());
                this.armorinv.setItem(3, target.getInventory().getChestplate());
                this.armorinv.setItem(4, target.getInventory().getLeggings());
                this.armorinv.setItem(5, target.getInventory().getBoots());
                this.armorinv.setItem(6, target.getInventory().getItemInOffHand());

                ((Player) sender).openInventory(this.armorinv);
            } else sender.sendMessage(Errors.NO_PERMISSION);
        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }
}
