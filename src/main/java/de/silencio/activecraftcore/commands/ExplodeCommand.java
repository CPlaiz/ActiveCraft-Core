package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ExplodeCommand implements CommandExecutor, TabCompleter {

    private float DEFAULT_POWER = 4f;
    private boolean DEFAULT_FIRE = true;
    private boolean DEFAULT_BREAK_BLOCKS = true;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.getWorld().createExplosion(player.getLocation(), DEFAULT_POWER, DEFAULT_FIRE, DEFAULT_BREAK_BLOCKS);
                return false;
            } else sender.sendMessage(Errors.NOT_A_PLAYER());
        }
        if (Bukkit.getPlayer(args[0]) == null) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("activecraft.explode.self")) {
                    switch (args.length) {
                        case 1:
                            Float num = null;
                            try {
                                num = Float.valueOf(args[0]);
                            } catch (NumberFormatException ignored) {
                            }
                            if (num == null) {
                                sender.sendMessage(Errors.INVALID_NUMBER());
                                return false;
                            }

                            player.getWorld().createExplosion(player.getLocation(), Float.parseFloat(args[0]), DEFAULT_FIRE, DEFAULT_BREAK_BLOCKS);
                            break;
                        case 2:
                            Float num1 = null;
                            try {
                                num1 = Float.valueOf(args[0]);
                            } catch (NumberFormatException ignored) {
                            }
                            if (num1 == null) {
                                sender.sendMessage(Errors.INVALID_NUMBER());
                                return false;
                            }
                            Boolean bool = null;
                            try {
                                bool = Boolean.valueOf(args[1]);
                            } catch (NumberFormatException ignored) {
                            }
                            if (bool == null) {
                                sender.sendMessage(Errors.INVALID_ARGUMENTS());
                                return false;
                            }
                            player.getWorld().createExplosion(player.getLocation(), Float.parseFloat(args[0]), Boolean.parseBoolean(args[1]), DEFAULT_BREAK_BLOCKS);
                            break;
                        case 3:
                            Float num2 = null;
                            try {
                                num2 = Float.valueOf(args[0]);
                            } catch (NumberFormatException ignored) {
                            }
                            if (num2 == null) {
                                sender.sendMessage(Errors.INVALID_NUMBER());
                                return false;
                            }
                            Boolean bool1 = null;
                            try {
                                bool1 = Boolean.valueOf(args[1]);
                            } catch (NumberFormatException ignored) {
                            }
                            if (bool1 == null) {
                                sender.sendMessage(Errors.INVALID_ARGUMENTS());
                                return false;
                            }
                            Boolean bool2 = null;
                            try {
                                bool2 = Boolean.valueOf(args[2]);
                            } catch (NumberFormatException ignored) {
                            }
                            if (bool2 == null) {
                                sender.sendMessage(Errors.INVALID_ARGUMENTS());
                                return false;
                            }
                            player.getWorld().createExplosion(player.getLocation(), Float.parseFloat(args[0]), Boolean.parseBoolean(args[1]), Boolean.parseBoolean(args[2]));
                            break;
                    }
                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.NOT_A_PLAYER());
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            if (sender.hasPermission("activecraft.explode.others")) {
                if(sender.getName().toLowerCase().equals(target.getName().toLowerCase())) {
                    if (!sender.hasPermission("activecraft.explode.self")) {
                        sender.sendMessage(Errors.CANNOT_TARGET_SELF());
                        return false;
                    }
                }
                switch (args.length) {
                    case 1:
                        target.getWorld().createExplosion(target.getLocation(), DEFAULT_POWER, DEFAULT_FIRE, DEFAULT_BREAK_BLOCKS);
                        break;
                    case 2:
                        Float num = null;
                        try {
                            num = Float.valueOf(args[1]);
                        } catch (NumberFormatException ignored) {
                        }
                        if (num == null) {
                            sender.sendMessage(Errors.INVALID_NUMBER());
                            return false;
                        }

                        target.getWorld().createExplosion(target.getLocation(), Float.parseFloat(args[1]), DEFAULT_FIRE, DEFAULT_BREAK_BLOCKS);
                        break;
                    case 3:
                        Float num1 = null;
                        try {
                            num1 = Float.valueOf(args[1]);
                        } catch (NumberFormatException ignored) {
                        }
                        if (num1 == null) {
                            sender.sendMessage(Errors.INVALID_NUMBER());
                            return false;
                        }
                        Boolean bool = null;
                        try {
                            bool = Boolean.valueOf(args[2]);
                        } catch (NumberFormatException ignored) {
                        }
                        if (bool == null) {
                            sender.sendMessage(Errors.INVALID_ARGUMENTS());
                            return false;
                        }
                        target.getWorld().createExplosion(target.getLocation(), Float.parseFloat(args[1]), Boolean.parseBoolean(args[2]), DEFAULT_BREAK_BLOCKS);
                        break;
                    case 4:
                        Float num2 = null;
                        try {
                            num2 = Float.valueOf(args[1]);
                        } catch (NumberFormatException ignored) {
                        }
                        if (num2 == null) {
                            sender.sendMessage(Errors.INVALID_NUMBER());
                            return false;
                        }
                        Boolean bool1 = null;
                        try {
                            bool1 = Boolean.valueOf(args[2]);
                        } catch (NumberFormatException ignored) {
                        }
                        if (bool1 == null) {
                            sender.sendMessage(Errors.INVALID_ARGUMENTS());
                            return false;
                        }
                        Boolean bool2 = null;
                        try {
                            bool2 = Boolean.valueOf(args[3]);
                        } catch (NumberFormatException ignored) {
                        }
                        if (bool2 == null) {
                            sender.sendMessage(Errors.INVALID_ARGUMENTS());
                            return false;
                        }
                        target.getWorld().createExplosion(target.getLocation(), Float.parseFloat(args[1]), Boolean.parseBoolean(args[2]), Boolean.parseBoolean(args[3]));
                        break;
                }
            } else sender.sendMessage(Errors.NO_PERMISSION());
        }
        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Player p = (Player) sender;

        if (args.length == 0) return list;
        if (args.length == 1) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                list.add(player.getName());
            }
        }
        if (Bukkit.getPlayer(args[0]) != null) {
        if (args.length == 2) {
        } else if (args.length == 3) {
            list.add("true");
            list.add("false");
        } else if (args.length == 4) {
            list.add("true");
            list.add("false");
        }
        } else {
            if (args.length == 2) {
                list.add("true");
                list.add("false");
            } else if (args.length == 3) {
                list.add("true");
                list.add("false");
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

// power:
// fire: true/false
// breakblocks: true/false