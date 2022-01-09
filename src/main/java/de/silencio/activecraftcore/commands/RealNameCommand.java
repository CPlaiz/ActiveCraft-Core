package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.playermanagement.Profile;
import de.silencio.activecraftcore.utils.ColorUtils;
import de.silencio.activecraftcore.utils.ComparisonType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RealNameCommand extends ActiveCraftCommand {

    public RealNameCommand() {
        super("realname");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        List<String> associatedPlayerList = new ArrayList<>();
        checkPermission(sender, "realname");
        checkArgsLength(args, ComparisonType.GREATER_EQUAL, 1);
        String displayname = combineArray(args, 0).trim();
        for (Profile profile : ActiveCraftCore.getProfiles().values())
            if (displayname.equalsIgnoreCase(ColorUtils.removeColorAndFormat(profile.getNickname())))
                associatedPlayerList.add(profile.getName());
        sendMessage(sender, CommandMessages.REALNAME_HEADER(combineList(associatedPlayerList, 0, ", "), displayname));
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 1)
            for (Player player : Bukkit.getOnlinePlayers())
                list.add(ColorUtils.removeColorAndFormat(getProfile(player).getNickname()));
        return list;
    }
}