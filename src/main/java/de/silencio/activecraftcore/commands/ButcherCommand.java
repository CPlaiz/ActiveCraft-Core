package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.ComparisonType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import java.util.List;

public class ButcherCommand extends ActiveCraftCommand {

    public ButcherCommand() {
        super("butcher");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        checkPermission(sender, "butcher");
        Player player = getPlayer(sender);
        checkArgsLength(args, ComparisonType.EQUAL, 0);
        List<Entity> entities = player.getNearbyEntities(200, 500, 200);
        if(entities.size() == 0) {
            sendMessage(sender, Errors.WARNING() + CommandMessages.NO_MOBS());
            return;
        }

        int killed = 0;
        for (Entity e : entities) {
            if (e instanceof Monster) {
                ((Monster) e).setHealth(0);
                killed += 1;
            } else if (e instanceof Flying) {
                ((Flying) e).setHealth(0);
                killed += 1;
            } else if (e instanceof Slime) {
                ((Slime) e).setHealth(0);
                killed += 1;
            }
        }
        sendMessage(sender, CommandMessages.KILLED_MOBS(killed));
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}