package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.messages.Language;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class LanguageCommand extends ActiveCraftCommand {

    public LanguageCommand() {
        super("language");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        if (args.length == 0) {
            checkPermission(sender, "language.see");
            String lang = ActiveCraftCore.getLanguage().getName();
            String code = ActiveCraftCore.getLanguage().getCode();
            sendMessage(sender, CommandMessages.CURRENT_LANGUAGE(lang, code));
        } else if (args.length == 1) {
            checkPermission(sender, "language.change");
            ActiveCraftCore.getPlugin().setLanguage(Language.valueOf(args[0].toUpperCase()));
            String lang = Language.valueOf(args[0].toUpperCase()).getName();
            String code = Language.valueOf(args[0].toUpperCase()).getCode();
            sendMessage(sender, CommandMessages.LANGUAGE_SET(lang, code));
        } else sendMessage(sender, Errors.TOO_MANY_ARGUMENTS());
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 1)
            for (Language language : Language.values())
                list.add(language.getCode());
        return list;
    }
}

