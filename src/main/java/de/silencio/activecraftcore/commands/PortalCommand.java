package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.ComparisonType;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.EndGateway;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PortalCommand extends ActiveCraftCommand {

    public PortalCommand() {
        super("portal");
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException {

        Player player = getPlayer(sender);
        FileConfig portalsConfig = new FileConfig("portals.yml");
        List<String> portalList = portalsConfig.getStringList("portallist");

        switch (args[0].toLowerCase()) {
            case "create" -> {
                checkPermission(sender, "portals.create");
                checkArgsLength(args, ComparisonType.GREATER_EQUAL, 8);
                if (!portalList.contains(args[1])) portalList.add(args[1]);
                portalsConfig.set("portallist", portalList);
                portalsConfig.set(args[1] + ".portal.name", args[1]);
                portalsConfig.set(args[1] + ".portal.x", Integer.parseInt(args[2]));
                portalsConfig.set(args[1] + ".portal.y", Integer.parseInt(args[3]));
                portalsConfig.set(args[1] + ".portal.z", Integer.parseInt(args[4]));
                portalsConfig.set(args[1] + ".portal.world", "" + player.getLocation().getWorld().getName());
                portalsConfig.set(args[1] + ".to.x", Integer.parseInt(args[5]));
                portalsConfig.set(args[1] + ".to.y", Integer.parseInt(args[6]));
                portalsConfig.set(args[1] + ".to.z", Integer.parseInt(args[7]));
                portalsConfig.set(args[1] + ".to.world", player.getLocation().getWorld().getName());
                portalsConfig.saveConfig();

                Block block = player.getWorld().getBlockAt(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
                block.setType(Material.END_GATEWAY);
                EndGateway endGateway = (EndGateway) block.getState();
                endGateway.setExactTeleport(true);
                Location loc = new Location(player.getWorld(), Integer.parseInt(args[5]), Integer.parseInt(args[6]), Integer.parseInt(args[7]));
                endGateway.setExitLocation(loc);
                endGateway.setAge(-999999999);
                endGateway.update();
                sendMessage(sender, CommandMessages.PORTAL_CREATED());
            }
            case "destroy" -> {
                checkPermission(sender, "portals.destroy");
                if (portalList.contains(args[1])) {
                    int portalx = portalsConfig.getInt(args[1] + ".portal.x");
                    int portaly = portalsConfig.getInt(args[1] + ".portal.y");
                    int portalz = portalsConfig.getInt(args[1] + ".portal.z");
                    World portalworld = Bukkit.getWorld(portalsConfig.getString(args[1] + ".portal.world"));
                    Block block = portalworld.getBlockAt(portalx, portaly, portalz);
                    block.setType(Material.AIR);
                    portalsConfig.set(args[1], null);
                    portalList.remove(args[1]);
                    portalsConfig.set("portallist", portalList);
                    portalsConfig.saveConfig();
                    sendMessage(sender, CommandMessages.PORTAL_DESTROYED(args[1], portalx + portaly + portalz + ""));
                } else sendMessage(sender,Errors.WARNING() + CommandMessages.PORTAL_DOESNT_EXIST());
            }
            case "list" -> {
                checkPermission(sender, "portals.destroy");
                if (!portalList.isEmpty()) {
                    sendMessage(sender, CommandMessages.PORTAL_LIST());
                    StringBuilder messageBuilder = new StringBuilder();
                    boolean isFirst = true;
                    for (String s : portalList) {
                        int x = portalsConfig.getInt(s + ".portal.x");
                        int y = portalsConfig.getInt(s + ".portal.y");
                        int z = portalsConfig.getInt(s + ".portal.z");
                        String worldname = portalsConfig.getString(s + ".portal.world");
                        if (isFirst) {
                            messageBuilder.append(ChatColor.BOLD + "" + ChatColor.GOLD + s + ": " + ChatColor.GRAY + worldname + "; " + x + ", " + y + ", " + z);
                            isFirst = false;
                        } else
                            messageBuilder.append("\n" + ChatColor.BOLD + "" + ChatColor.GOLD + s + ": " + ChatColor.GRAY + worldname + "; " + x + ", " + y + ", " + z);
                    }
                    sendMessage(sender, messageBuilder.toString());
                } else sendMessage(sender,Errors.WARNING() + CommandMessages.PORTAL_NO_LIST());
            }
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();

        Player p = (Player) sender;
        FileConfig fileConfig = new FileConfig("portals.yml");

        if (args.length == 1) {
            list.add("create");
            list.add("destroy");
            list.add("list");
        }
        if (args[0].equals("create")) {
            if (p.getTargetBlock(9999) == null) return null;
            switch (args.length) {
                case 3, 6 -> list.add(p.getTargetBlock(5).getX() + "");
                case 4, 7 -> list.add(p.getTargetBlock(5).getY() + "");
                case 5, 8 -> list.add(p.getTargetBlock(5).getZ() + "");
            }
        } else if (args[0].equals("destroy"))
            if (args.length == 2) list.addAll(fileConfig.getStringList("portallist"));
        return list;
    }
}
