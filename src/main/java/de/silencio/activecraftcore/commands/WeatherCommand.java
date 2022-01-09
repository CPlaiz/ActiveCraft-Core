package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.ComparisonType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class WeatherCommand extends ActiveCraftCommand {

    public WeatherCommand() {
        super("weather");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "weather");
        Player player = getPlayer(sender);
        checkArgsLength(args, ComparisonType.EQUAL, 1);
        if (Objects.equals(args[0], "thunder")) {
            player.getWorld().setThundering(true);
            sendMessage(sender, CommandMessages.TO_THUNDER(player.getWorld().getName()));
        } else if (Objects.equals(args[0], "rain")) {
            player.getWorld().setStorm(true);
            sendMessage(sender, CommandMessages.TO_RAIN(player.getWorld().getName()));
        } else if (Objects.equals(args[0], "clear")) {
            player.getWorld().setClearWeatherDuration(999999999);
            sendMessage(sender, CommandMessages.TO_CLEAR(player.getWorld().getName()));
        } else sendMessage(sender, Errors.INVALID_ARGUMENTS());
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? List.of("clear", "thunder", "rain") : null;
    }
}
