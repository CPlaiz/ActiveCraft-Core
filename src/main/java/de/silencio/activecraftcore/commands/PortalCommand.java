package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.utils.FileConfig;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.EndGateway;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PortalCommand implements CommandExecutor, TabCompleter {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        if (args[0].equalsIgnoreCase("create")) {
            if (sender.hasPermission("portals.create")) {
                if (args.length == 8) {
                    FileConfig portalsConfig = new FileConfig("portals.yml");
                    List<String> portalList = portalsConfig.getStringList("portallist");
                    if (!portalList.contains(args[1])) {
                        portalList.add(args[1]);
                    }
                    portalsConfig.set("portallist", portalList);
                    ;
                    portalsConfig.set(args[1] + ".portal.name", args[1]);
                    portalsConfig.set(args[1] + ".portal.x", Integer.parseInt(args[2]));
                    portalsConfig.set(args[1] + ".portal.y", Integer.parseInt(args[3]));
                    portalsConfig.set(args[1] + ".portal.z", Integer.parseInt(args[4]));
                    portalsConfig.set(args[1] + ".portal.world", "" + p.getLocation().getWorld().getName());
                    portalsConfig.set(args[1] + ".to.x", Integer.parseInt(args[5]));
                    portalsConfig.set(args[1] + ".to.y", Integer.parseInt(args[6]));
                    portalsConfig.set(args[1] + ".to.z", Integer.parseInt(args[7]));
                    portalsConfig.set(args[1] + ".to.world", p.getLocation().getWorld().getName());
                    portalsConfig.saveConfig();

                    Block block = p.getWorld().getBlockAt(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
                    block.setType(Material.END_GATEWAY);
                    EndGateway endGateway = (EndGateway) block.getState();
                    endGateway.setExactTeleport(true);
                    Location loc = new Location(p.getWorld(), Integer.parseInt(args[5]), Integer.parseInt(args[6]), Integer.parseInt(args[7]));
                    endGateway.setExitLocation(loc);
                    endGateway.setAge(-999999999);
                    endGateway.update();
                    sender.sendMessage(CommandMessages.PORTAL_CREATED());

                } else sender.sendMessage(Errors.TOO_MANY_ARGUMENTS());

            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else if (args[0].equalsIgnoreCase("destroy")) {
            if (sender.hasPermission("portals.destroy")) {
                FileConfig portalsConfig = new FileConfig("portals.yml");
                List<String> portalList = portalsConfig.getStringList("portallist");
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

                    sender.sendMessage(CommandMessages.PORTAL_DESTROYED(args[1], portalx + portaly + portalz + ""));

                } else sender.sendMessage(Errors.WARNING() + CommandMessages.PORTAL_DOESNT_EXIST());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else if (args[0].equalsIgnoreCase("list")) {
            if (sender.hasPermission("portals.list")) {
                FileConfig portalsConfig = new FileConfig("portals.yml");
                List<String> portalList = portalsConfig.getStringList("portallist");

                if (!portalList.isEmpty()) {
                    sender.sendMessage(CommandMessages.PORTAL_LIST());
                    for (String s : portalList) {
                        int x = portalsConfig.getInt(s + ".portal.x");
                        int y = portalsConfig.getInt(s + ".portal.y");
                        int z = portalsConfig.getInt(s + ".portal.z");
                        String worldname = portalsConfig.getString(s + ".portal.world");
                        sender.sendMessage(ChatColor.BOLD + "" + ChatColor.GOLD + s + ": " + ChatColor.GRAY + worldname + "; " + x + ", " + y + ", " + z);
                    }
                } else sender.sendMessage(Errors.WARNING() + CommandMessages.PORTAL_NO_LIST());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        } else sender.sendMessage(Errors.INVALID_ARGUMENTS());


        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();

        Player p = (Player) sender;

        if (args.length == 0) return list;
        if (args.length == 1) {
            list.add("create");
            list.add("destroy");
            list.add("list");
        }
        if (args[0].equals("create")) {
            if (args.length == 2) {

            } else if (args.length == 3) {
                if (p.getTargetBlock(5) != null) {
                    list.add(p.getTargetBlock(5).getX() + "");
                } else list.add("~");
            } else if (args.length == 4) {
                if (p.getTargetBlock(5) != null) {
                    list.add(p.getTargetBlock(5).getY() + "");
                } else list.add("~");
            } else if (args.length == 5) {
                if (p.getTargetBlock(5) != null) {
                    list.add(p.getTargetBlock(5).getZ() + "");
                } else list.add("~");
            } else if (args.length == 6) {
                if (p.getTargetBlock(5) != null) {
                    list.add(p.getTargetBlock(5).getX() + "");
                } else list.add("~");
            } else if (args.length == 7) {
                if (p.getTargetBlock(5) != null) {
                    list.add(p.getTargetBlock(5).getY() + "");
                } else list.add("~");
            } else if (args.length == 8) {
                if (p.getTargetBlock(5) != null) {
                    list.add(p.getTargetBlock(5).getZ() + "");
                } else list.add("~");
            }
        } else if (args[0].equals("destroy")) {
            if (args.length == 2) {
                FileConfig fileConfig = new FileConfig("portals.yml");
                for (String s : fileConfig.getStringList("portallist")) {
                    list.add(s);
                }
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
