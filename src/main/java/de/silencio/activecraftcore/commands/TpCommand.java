package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.exceptions.ActiveCraftException;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TpCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            if (sender instanceof Player) {
                Player target = Bukkit.getPlayer(args[0]);
                Player player = (Player) sender;

                if (sender.hasPermission("activecraft.tp.self")) {
                    if (target != null) {
                        if (target != player) {

                            player.teleport(target.getLocation());
                            player.sendMessage(CommandMessages.TELEPORT_TO_PLAYER(target));
                            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);

                        } else sender.sendMessage(Errors.WARNING() + CommandMessages.CANNOT_TP_TO_SELF_TELEPORT());
                    } else sender.sendMessage(Errors.INVALID_PLAYER());
                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.NOT_A_PLAYER());
        }
        if (args.length == 3) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (sender.hasPermission("activecraft.tp.self")) {

                    double finalNumX = 0;
                    double finalNumY = 0;
                    double finalNumZ = 0;

                    if (args[0].startsWith("~")) {
                        if (args[0].length() == 1) {
                            finalNumX = player.getLocation().getX();
                        } else {
                            args[0] = args[0].replace("~", "");
                            Double numX = null;
                            try {
                                numX = Double.valueOf(args[0]);
                            } catch (NumberFormatException ignored) {
                            }
                            if (numX == null) {
                                sender.sendMessage(Errors.INVALID_NUMBER());
                                return false;
                            }
                            finalNumX = player.getLocation().getX() + Double.parseDouble(args[0]);
                        }
                    } else {
                        Double numX = null;
                        try {
                            numX = Double.valueOf(args[0]);
                        } catch (NumberFormatException ignored) {
                        }
                        if (numX == null) {
                            sender.sendMessage(Errors.INVALID_NUMBER());
                            return false;
                        }
                        finalNumX = numX;
                    }
                    if (args[1].startsWith("~")) {
                        if (args[1].length() == 1) {
                            finalNumY = player.getLocation().getY();
                        } else {
                            args[1] = args[1].replace("~", "");
                            Double numY = null;
                            try {
                                numY = Double.valueOf(args[1]);
                            } catch (NumberFormatException ignored) {
                            }
                            if (numY == null) {
                                sender.sendMessage(Errors.INVALID_NUMBER());
                                return false;
                            }
                            finalNumY = player.getLocation().getY() + Double.parseDouble(args[1]);
                        }
                    } else {
                        Double numY = null;
                        try {
                            numY = Double.valueOf(args[1]);
                        } catch (NumberFormatException ignored) {
                        }
                        if (numY == null) {
                            sender.sendMessage(Errors.INVALID_NUMBER());
                            return false;
                        }
                        finalNumY = numY;
                    }
                    if (args[2].startsWith("~")) {
                        if (args[2].length() == 1) {
                            finalNumZ = player.getLocation().getZ();
                        } else {
                            args[2] = args[2].replace("~", "");
                            Double numZ = null;
                            try {
                                numZ = Double.valueOf(args[2]);
                            } catch (NumberFormatException ignored) {
                            }
                            if (numZ == null) {
                                sender.sendMessage(Errors.INVALID_NUMBER());
                                return false;
                            }
                            finalNumZ = player.getLocation().getZ() + Double.parseDouble(args[2]);
                        }
                    } else {
                        Double numZ = null;
                        try {
                            numZ = Double.valueOf(args[2]);
                        } catch (NumberFormatException ignored) {
                        }
                        if (numZ == null) {
                            sender.sendMessage(Errors.INVALID_NUMBER());
                            return false;
                        }
                        finalNumZ = numZ;
                    }

                    player.teleport(new Location(player.getWorld(), finalNumX, finalNumY, finalNumZ));
                    player.sendMessage(CommandMessages.TELEPORT_TO_COORDS(args[0] + ", " + args[1] + ", " + args[2]));
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);

                } else sender.sendMessage(Errors.NO_PERMISSION());
            } else sender.sendMessage(Errors.NOT_A_PLAYER());
        }

        if (args.length == 2) {
            if (sender.hasPermission("activecraft.tp.others")) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER());
                    return false;
                }
                if (Bukkit.getPlayer(args[1]) == null) {
                    sender.sendMessage(Errors.INVALID_PLAYER());
                    return false;
                }

                Player target1 = Bukkit.getPlayer(args[0]);
                Player target2 = Bukkit.getPlayer(args[1]);

                if (target1 != target2) {
                    target1.teleport(target2.getLocation());
                    sender.sendMessage(CommandMessages.TELEPORT_PLAYER_TO_PLAYER(target1, target2));
                    target1.playSound(target1.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
                } else sender.sendMessage(Errors.WARNING() + CommandMessages.CANNOT_TP_OTHERS_TO_THEMSELF());
            } else sender.sendMessage(Errors.NO_PERMISSION());
        }
        if (args.length == 4) {
            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(Errors.INVALID_PLAYER());
                return false;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (sender.hasPermission("activecraft.tp.others")) {

                double finalNumX = 0;
                double finalNumY = 0;
                double finalNumZ = 0;

                if (args[1].startsWith("~")) {
                    if (args[1].length() == 1) {
                        finalNumX = target.getLocation().getX();
                    } else {
                        args[1] = args[1].replace("~", "");
                        Double numX = null;
                        try {
                            numX = Double.valueOf(args[1]);
                        } catch (NumberFormatException ignored) {
                        }
                        if (numX == null) {
                            sender.sendMessage(Errors.INVALID_NUMBER());
                            return false;
                        }
                        finalNumX = target.getLocation().getX() + Double.parseDouble(args[1]);
                    }
                } else {
                    Double numX = null;
                    try {
                        numX = Double.valueOf(args[1]);
                    } catch (NumberFormatException ignored) {
                    }
                    if (numX == null) {
                        sender.sendMessage(Errors.INVALID_NUMBER());
                        return false;
                    }
                    finalNumX = numX;
                }
                if (args[2].startsWith("~")) {
                    if (args[2].length() == 1) {
                        finalNumY = target.getLocation().getY();
                    } else {
                        args[2] = args[2].replace("~", "");
                        Double numY = null;
                        try {
                            numY = Double.valueOf(args[2]);
                        } catch (NumberFormatException ignored) {
                        }
                        if (numY == null) {
                            sender.sendMessage(Errors.INVALID_NUMBER());
                            return false;
                        }
                        finalNumY = target.getLocation().getY() + Double.parseDouble(args[2]);
                    }
                } else {
                    Double numY = null;
                    try {
                        numY = Double.valueOf(args[2]);
                    } catch (NumberFormatException ignored) {
                    }
                    if (numY == null) {
                        sender.sendMessage(Errors.INVALID_NUMBER());
                        return false;
                    }
                    finalNumY = numY;
                }
                if (args[3].startsWith("~")) {
                    if (args[3].length() == 1) {
                        finalNumZ = target.getLocation().getZ();
                    } else {
                        args[3] = args[3].replace("~", "");
                        Double numZ = null;
                        try {
                            numZ = Double.valueOf(args[3]);
                        } catch (NumberFormatException ignored) {
                        }
                        if (numZ == null) {
                            sender.sendMessage(Errors.INVALID_NUMBER());
                            return false;
                        }
                        finalNumZ = target.getLocation().getZ() + Double.parseDouble(args[3]);
                    }
                } else {
                    Double numZ = null;
                    try {
                        numZ = Double.valueOf(args[3]);
                    } catch (NumberFormatException ignored) {
                    }
                    if (numZ == null) {
                        sender.sendMessage(Errors.INVALID_NUMBER());
                        return false;
                    }
                    finalNumZ = numZ;
                }

                target.teleport(new Location(target.getWorld(), finalNumX, finalNumY, finalNumZ));
                sender.sendMessage(CommandMessages.TELEPORT_PLAYER_TO_COORDS(target, finalNumX + finalNumY + finalNumZ + ""));
                target.playSound(target.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);

            } else sender.sendMessage(Errors.NO_PERMISSION());
        }
        if (args.length == 0) {
            sender.sendMessage(Errors.INVALID_ARGUMENTS());
        }
        return true;
    }
}