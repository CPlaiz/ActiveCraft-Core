package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class KnockbackStickCommand extends ActiveCraftCommand {

    public KnockbackStickCommand() {
        super("knockbackstick");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        if (args.length == 0) {
            checkPermission(sender, "knockbackstick.self");
            Player player = getPlayer(sender);
            ItemStack stick = new ItemStack(Material.STICK);
            ItemMeta stickmeta = stick.getItemMeta();
            stickmeta.setDisplayName(ChatColor.GOLD + "Knockback Stick");
            stickmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            stickmeta.setLore(Collections.singletonList(CommandMessages.KNOCKBACKSTICK_LORE()));
            stick.setItemMeta(stickmeta);
            stick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 255);
            player.getInventory().addItem(stick);
            sendMessage(sender, CommandMessages.KNOCKBACKSTICK());
        } else if (args.length == 1) {
            checkPermission(sender, "knockbackstick.others");
            Player target = getPlayer(args[0]);
            if (!checkTargetSelf(sender, target, "knockbackstick.self")) sendSilentMessage(target, CommandMessages.KNOCKBACKSTICK_OTHERS_MESSAGE(sender));
            ItemStack stick = new ItemStack(Material.STICK);
            ItemMeta stickmeta = stick.getItemMeta();
            stickmeta.setDisplayName(ChatColor.GOLD + "Knockback Stick");
            stickmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            stickmeta.setLore(Collections.singletonList(CommandMessages.KNOCKBACKSTICK_LORE()));
            stick.setItemMeta(stickmeta);
            stick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 255);
            target.getInventory().addItem(stick);
            sendMessage(sender, CommandMessages.KNOCKBACKSTICK_OTHERS(target));
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String alias, String[] args) {
        return args.length == 1 ? getBukkitPlayernames() : null;
    }
}