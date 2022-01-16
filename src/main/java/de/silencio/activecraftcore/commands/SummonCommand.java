package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.exceptions.InvalidEntityException;
import de.silencio.activecraftcore.messages.CommandMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SummonCommand extends ActiveCraftCommand {

    public SummonCommand() {
        super("summon");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {
        List<EntityType> forbiddenEntityTypes = List.of(EntityType.UNKNOWN, EntityType.LEASH_HITCH, EntityType.PLAYER);
        switch (args.length) {
            case 1 -> {
                checkPermission(sender, "summon.self");
                Player player = getPlayer(sender);
                EntityType entityType = parseEntityType(args[0]);
                if (forbiddenEntityTypes.contains(entityType)) throw new InvalidEntityException(args[0]);
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.valueOf(args[0]));
                    sendMessage(sender, CommandMessages.SUMMON(args[0]));
            }
            case 2 -> {
                if (Bukkit.getPlayer(args[0]) == null) {
                    checkPermission(sender, "summon.self.multiple");
                    Player player = getPlayer(sender);
                    EntityType entityType = parseEntityType(args[0]);
                    if (forbiddenEntityTypes.contains(entityType)) throw new InvalidEntityException(args[0]);
                        sendMessage(sender, CommandMessages.SUMMON_MULTIPLE(parseInt(args[1]) + "", args[0]));
                    for (int i = 0; i < Integer.parseInt(args[1]); i++)
                        player.getWorld().spawnEntity(player.getLocation(), entityType);
                } else {
                    checkPermission(sender, "summon.others");
                    Player target = getPlayer(args[0]);
                    checkTargetSelf(sender, target, "summon.self");
                    EntityType entityType = parseEntityType(args[1]);
                    if (forbiddenEntityTypes.contains(entityType)) throw new InvalidEntityException(args[1]);
                        target.getWorld().spawnEntity(target.getLocation(), entityType);
                        sendMessage(sender, CommandMessages.SUMMON_OTHERS(target, args[1]));
                }
            }
            case 3 -> {
                checkPermission(sender, "summon.others.multiple");
                Player target = getPlayer(args[0]);
                checkTargetSelf(sender, target,"summon.self.multiple");
                EntityType entityType = parseEntityType(args[1]);
                if (forbiddenEntityTypes.contains(entityType)) throw new InvalidEntityException(args[1]);
                    sendMessage(sender, CommandMessages.SUMMON_OTHERS_MULTIPLE(target, parseInt(args[2]) + "", parseEntityType(args[1]).name()));
                    for (int i = 0; i < parseInt(args[2]); i++)
                        target.getWorld().spawnEntity(target.getLocation(), entityType);

            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();

        if (args.length == 1) {
            for (EntityType entityType : EntityType.values())
                if (!entityType.name().equals("UNKNOWN") && !entityType.name().equals("LEASH_HITCH") && !entityType.name().equals("PLAYER"))
                    list.add(entityType.name());
            list.addAll(getBukkitPlayernames());
        } else if (args.length == 2) {
            if (Bukkit.getPlayer(args[0]) != null)
                for (EntityType entityType : EntityType.values())
                    if (!entityType.name().equals("UNKNOWN") && !entityType.name().equals("LEASH_HITCH") && !entityType.name().equals("PLAYER"))
                        list.add(entityType.name());
        }
        return list;
    }
}