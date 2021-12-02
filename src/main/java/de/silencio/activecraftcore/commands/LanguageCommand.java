package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Language;
import org.bukkit.command.CommandExecutor;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LanguageCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0){
            if(sender.hasPermission("activecraft.language.see")) {
                String lang = ActiveCraftCore.getPlugin().getLanguage().getName();
                String code = ActiveCraftCore.getPlugin().getLanguage().getCode();
                sender.sendMessage(CommandMessages.CURRENT_LANGUAGE(lang, code));
            } else sender.sendMessage(Errors.NO_PERMISSION());
        }
        if(args.length == 1) {
            if (sender.hasPermission("activecraft.language.change")) {

                ActiveCraftCore.getPlugin().setLanguage(Language.valueOf(args[0].toUpperCase()));
                String lang = Language.valueOf(args[0].toUpperCase()).getName();
                String code = Language.valueOf(args[0].toUpperCase()).getCode();
                sender.sendMessage(CommandMessages.LANGUAGE_SET(lang, code));

            } else sender.sendMessage(Errors.NO_PERMISSION());
        }
        if(args.length >= 2) {
            sender.sendMessage(Errors.TOO_MANY_ARGUMENTS());
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Player p = (Player) sender;

        if (args.length == 1) {
            for (Language language : Language.values()) {
                list.add(language.getCode());
            }
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

