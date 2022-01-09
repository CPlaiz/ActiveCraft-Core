package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SudoCommand extends ActiveCraftCommand {

    public SudoCommand() {
        super("sudo");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "sudo");
        Player target = getPlayer(args[0]);
        String executedCommand = combineArray(args, 1);
        if (isValidCommand(executedCommand)) target.performCommand(executedCommand);
        else sendMessage(sender, Errors.WARNING() + "Invalid Command!");
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();

        if (args.length == 1) list.addAll(getBukkitPlayernames());
        else if (args.length == 2) {
            FileConfig mainConfig = new FileConfig("config.yml");
            if (mainConfig.getBoolean("hide-commands-after-plugin-name.enable")) {
                List<String> pluginNames = new ArrayList<>();
                pluginNames.add("minecraft");
                pluginNames.add("bukkit");
                pluginNames.add("spigot");
                pluginNames.add("paper");
                list.addAll(getBukkitPlayernames());
                List<String> toBeRemoved = new ArrayList<>();
                for (String cmd : Bukkit.getCommandMap().getKnownCommands().keySet())
                    for (String pluginName : pluginNames)
                        if (cmd.startsWith(pluginName + ":"))
                            toBeRemoved.add(cmd);
                for (String cmd : Bukkit.getCommandMap().getKnownCommands().keySet())
                    if (!toBeRemoved.contains(cmd))
                        list.add(cmd);
            }
        }
        return list;
    }
}
