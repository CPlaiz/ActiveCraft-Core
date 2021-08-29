package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class BookCommand implements CommandExecutor, TabCompleter {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String title = "";
        String author = "";
        String editpage = "";
        String addpage = "";

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (sender.hasPermission("activecraft.book")) {

                if (player.getInventory().getItemInMainHand().getTranslationKey().equals("item.minecraft.written_book")) {
                    if (args.length > 0) {
                        if (args[0].equalsIgnoreCase("title")) {
                            if (args.length >= 2) {
                                ItemStack book = player.getInventory().getItemInMainHand();
                                BookMeta bookmeta = (BookMeta) book.getItemMeta();
                                boolean isFirst = true;
                                for (int i = 1; i < args.length; i++) {
                                    if (!isFirst) {
                                        title = title + " ";
                                    } else isFirst = false;
                                    title = title + args[i];
                                }
                                bookmeta.setTitle(title);
                                book.setItemMeta(bookmeta);
                                sender.sendMessage(ChatColor.GOLD + "Changed book title to " + ChatColor.AQUA + title + ChatColor.GOLD + ".");
                            } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
                        } else if (args[0].equalsIgnoreCase("author")) {
                            if (args.length >= 2) {
                                ItemStack book = player.getInventory().getItemInMainHand();
                                BookMeta bookmeta = (BookMeta) book.getItemMeta();
                                boolean isFirst = true;
                                for (int i = 1; i < args.length; i++) {
                                    if (!isFirst) {
                                        author = author + " ";
                                    } else isFirst = false;
                                    author = author + args[i];
                                }
                                bookmeta.setAuthor(author);
                                book.setItemMeta(bookmeta);
                                sender.sendMessage(ChatColor.GOLD + "Changed book author to " + ChatColor.AQUA + author + ChatColor.GOLD + ".");
                            } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
                        } else if (args[0].equalsIgnoreCase("editpage")) {
                            if (args.length >= 3) {
                                ItemStack book = player.getInventory().getItemInMainHand();
                                BookMeta bookmeta = (BookMeta) book.getItemMeta();
                                for (int i = 2; i < args.length; i++) {
                                    editpage = editpage + args[i] + " ";
                                }
                                Integer num = null;
                                try {
                                    num = Integer.valueOf(args[1]);
                                } catch (NumberFormatException ignored) {
                                }
                                if (num == null) {
                                    sender.sendMessage(Errors.INVALID_NUMBER);
                                    return false;
                                }
                                bookmeta.setPage(Integer.parseInt(args[1]), editpage);
                                book.setItemMeta(bookmeta);
                                sender.sendMessage(ChatColor.GOLD + "Changed the page " + ChatColor.AQUA + num + ChatColor.GOLD + ".");
                            } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
                        } else if (args[0].equalsIgnoreCase("addpage")) {
                            if (args.length >= 2) {
                                ItemStack book = player.getInventory().getItemInMainHand();
                                BookMeta bookmeta = (BookMeta) book.getItemMeta();
                                for (int i = 1; i < args.length; i++) {
                                    addpage = addpage + args[i] + " ";
                                }
                                bookmeta.addPage(addpage);
                                book.setItemMeta(bookmeta);
                                sender.sendMessage(ChatColor.GOLD + "Added a new page.");
                            }
                        } else if (args[0].equalsIgnoreCase("generation")) {
                            if (args.length == 2) {

                                if (args[1].equalsIgnoreCase("original")) {
                                    ItemStack book = player.getInventory().getItemInMainHand();
                                    BookMeta bookmeta = (BookMeta) book.getItemMeta();

                                    bookmeta.setGeneration(BookMeta.Generation.ORIGINAL);
                                    book.setItemMeta(bookmeta);
                                    sender.sendMessage(ChatColor.GOLD + "Changed book generation to " + ChatColor.AQUA + "original" + ChatColor.GOLD + ".");
                                } else if (args[1].equalsIgnoreCase("copy")) {
                                    ItemStack book = player.getInventory().getItemInMainHand();
                                    BookMeta bookmeta = (BookMeta) book.getItemMeta();

                                    bookmeta.setGeneration(BookMeta.Generation.COPY_OF_ORIGINAL);
                                    book.setItemMeta(bookmeta);
                                    sender.sendMessage(ChatColor.GOLD + "Changed book generation to " + ChatColor.AQUA + "copy of original" + ChatColor.GOLD + ".");
                                } else if (args[1].equalsIgnoreCase("copy_of_copy")) {
                                    ItemStack book = player.getInventory().getItemInMainHand();
                                    BookMeta bookmeta = (BookMeta) book.getItemMeta();

                                    bookmeta.setGeneration(BookMeta.Generation.COPY_OF_COPY);
                                    book.setItemMeta(bookmeta);
                                    sender.sendMessage(ChatColor.GOLD + "Changed book generation to " + ChatColor.AQUA + "copy of copy" + ChatColor.GOLD + ".");
                                } else if (args[1].equalsIgnoreCase("tattered")) {
                                    ItemStack book = player.getInventory().getItemInMainHand();
                                    BookMeta bookmeta = (BookMeta) book.getItemMeta();

                                    bookmeta.setGeneration(BookMeta.Generation.TATTERED);
                                    book.setItemMeta(bookmeta);
                                    sender.sendMessage(ChatColor.GOLD + "Changed book generation to " + ChatColor.AQUA + "tattered" + ChatColor.GOLD + ".");
                                } else sender.sendMessage(Errors.INVALID_ARGUMENTS);

                            } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
                        } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
                    } else sender.sendMessage(Errors.INVALID_ARGUMENTS);
                } else sender.sendMessage(Errors.WARNING + "You are not holding a book!");
            } else sender.sendMessage(Errors.NO_PERMISSION);
        } else sender.sendMessage(Errors.NOT_A_PLAYER);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Player p = (Player) sender;

        if (args.length == 0) return list;
        if (args.length == 1) {
            list.add("title");
            list.add("author");
            list.add("editpage");
            list.add("addpage");
            list.add("generation");
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("generation")) {
            list.add("original");
            list.add("copy");
            list.add("copy_of_copy");
            list.add("tattered");
        }

        ArrayList<String> completerList = new ArrayList<>();
        String currentarg = args[args.length - 1].toLowerCase();
        for (String s : list) {
            String s1 = s.toLowerCase();
            if (s1.startsWith(currentarg)) {
                completerList.add(s);
            }
        }
        return completerList;
    }
}
