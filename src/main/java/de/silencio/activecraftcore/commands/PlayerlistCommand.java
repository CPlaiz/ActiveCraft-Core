package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.playermanagement.Profile;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerlistCommand extends ActiveCraftCommand {

    public PlayerlistCommand() {
        super("playerlist");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "playerlist");
        StringBuilder stringBuilder = new StringBuilder();
        FileConfig mainConfig = new FileConfig("config.yml");
        boolean isFirst = true;
        for (Player player : Bukkit.getOnlinePlayers()) {
            Profile profile = getProfile(player);
            if (profile.isVanished()) {
                if (sender.hasPermission("activecraft.vanish.see")) {
                    if (!isFirst) stringBuilder.append(ChatColor.WHITE + ", ");
                    else isFirst = false;
                    stringBuilder.append(player.getName() + ChatColor.GRAY + " " + mainConfig.getString("vanish-format"));
                }
            } else {
                if (!isFirst) stringBuilder.append(ChatColor.WHITE + ", ");
                else isFirst = false;
                stringBuilder.append(player.getDisplayName());
            }
        }
        sendMessage(sender, CommandMessages.PLAYERLIST_HEADER());
        sendMessage(sender, stringBuilder.toString());
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
