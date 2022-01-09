package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.exceptions.NotHoldingItemException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.ColorUtils;
import de.silencio.activecraftcore.utils.ComparisonType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class BookCommand extends ActiveCraftCommand implements TabCompleter {

    public BookCommand() {
        super("book");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        
        checkPermission(sender, "book");
        Player player = getPlayer(sender);
        checkHoldingItem(player, NotHoldingItemException.ExpectedItem.WRITTEN_BOOK, Material.WRITTEN_BOOK);

        ItemStack book = player.getInventory().getItemInMainHand();
        BookMeta bookmeta = (BookMeta) book.getItemMeta();

        checkArgsLength(args, ComparisonType.GREATER, 0);

        switch (args[0].toLowerCase()) {
            case "title" -> {
                checkArgsLength(args, ComparisonType.GREATER_EQUAL, 2);
                String title = combineArray(args, 1);
                bookmeta.setTitle(ColorUtils.replaceColorAndFormat(title));
                sendMessage(sender, CommandMessages.CHANGED_TITLE(title));
            }
            case "author" -> {
                checkArgsLength(args, ComparisonType.GREATER_EQUAL, 2);
                String author = combineArray(args, 1);
                bookmeta.setAuthor(ColorUtils.replaceColorAndFormat(author));
                sendMessage(sender, CommandMessages.CHANGED_AUTHOR(author));
            }
            case "editpage" -> {
                checkArgsLength(args, ComparisonType.GREATER_EQUAL, 3);
                if (bookmeta.getPageCount() < parseInt(args[1])) {
                    sendMessage(sender, Errors.NUMBER_TOO_LARGE());
                    return;
                }
                String editpage = combineArray(args, 2);
                bookmeta.setPage(parseInt(args[1]), ColorUtils.replaceColorAndFormat(editpage));
                sendMessage(sender, CommandMessages.CHANGED_PAGE(args[1]).replace("%page%", ChatColor.AQUA + args[1] + ChatColor.GOLD));
            }
            case "addpage" -> {
                checkArgsLength(args, ComparisonType.GREATER_EQUAL, 2);
                bookmeta.addPage(combineArray(args, 1));
                sendMessage(sender, CommandMessages.ADDED_PAGE());
            }
            case "generation" -> {
                checkArgsLength(args, ComparisonType.EQUAL, 2);
                switch (args[1].toLowerCase()) {
                    case "original" -> {
                        bookmeta.setGeneration(BookMeta.Generation.ORIGINAL);
                        sendMessage(sender, CommandMessages.CHANGED_GENERATION(CommandMessages.ORIGINAL()));
                    }
                    case "copy_of_original" -> {
                        bookmeta.setGeneration(BookMeta.Generation.COPY_OF_ORIGINAL);
                        sendMessage(sender, CommandMessages.CHANGED_GENERATION(CommandMessages.COPY_ORIGINAL()));
                    }
                    case "copy_of_copy" -> {
                        bookmeta.setGeneration(BookMeta.Generation.COPY_OF_COPY);
                        sendMessage(sender, CommandMessages.CHANGED_GENERATION(CommandMessages.COPY_COPY()));
                    }
                    case "tattered" -> {
                        bookmeta.setGeneration(BookMeta.Generation.TATTERED);
                        sendMessage(sender, CommandMessages.CHANGED_GENERATION(CommandMessages.TATTERED()));
                    }
                }
            }
        }
        book.setItemMeta(bookmeta);
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();

        switch (args.length) {
            case 0 -> {return list;}
            case 1 -> {
                list.add("title");
                list.add("author");
                list.add("editpage");
                list.add("addpage");
                list.add("generation");
            }
            case 2 -> {
                if (!args[0].equalsIgnoreCase("generation")) break;
                list.add("original");
                list.add("copy");
                list.add("copy_of_copy");
                list.add("tattered");
            }
        }
        return list;
    }
}
