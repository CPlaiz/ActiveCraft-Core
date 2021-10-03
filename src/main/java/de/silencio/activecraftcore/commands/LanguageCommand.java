package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.Main;
import de.silencio.activecraftcore.messages.Language;
import org.bukkit.command.CommandExecutor;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LanguageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0){
            if(sender.hasPermission("activecraft.language.see")) {

            } //error
        }
        if(args.length == 1) {
            if (sender.hasPermission("activecraft.language.change")) {

                Main.getPlugin().setLanguage(Language.valueOf(args[0]));

            } //error
        }
        if(args.length >= 2) {
            sender.sendMessage(Errors.TOO_MANY_ARGUMENTS());
        }
        return true;
    }
}
