package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.exceptions.NotHoldingItemException;
import de.silencio.activecraftcore.utils.ComparisonType;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class LeatherColorCommand extends ActiveCraftCommand {

    public LeatherColorCommand() {
        super("leathercolor");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "leathercolor");
        Player player = getPlayer(sender);
        checkArgsLength(args, ComparisonType.EQUAL, 1);
        ItemStack mainhanditem = player.getInventory().getItemInMainHand();
        checkHoldingItem(player, NotHoldingItemException.ExpectedItem.LEATHER_ITEM,
                Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.LEATHER_HORSE_ARMOR);
        LeatherArmorMeta itemmeta = (LeatherArmorMeta) mainhanditem.getItemMeta();
        if (!args[0].startsWith("#")) checkPermission(sender, "leathercolor.vanilla");
        else checkPermission(sender, "leathercolor.hex");
        itemmeta.setColor(getColor(args[0]));
        mainhanditem.setItemMeta(itemmeta);
        player.getInventory().setItemInMainHand(mainhanditem);
    }
    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 1) {
            list.add("green");
            list.add("black");
            list.add("blue");
            list.add("lime");
            list.add("cyan");
            list.add("red");
            list.add("magenta");
            list.add("pink");
            list.add("orange");
            list.add("light_gray");
            list.add("gray");
            list.add("light_blue");
            list.add("purple");
            list.add("yellow");
            list.add("white");
            list.add("brown");
        }
        return list;
    }
}