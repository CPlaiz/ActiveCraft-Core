package de.silencio.activecraftcore.commands;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.exceptions.*;
import de.silencio.activecraftcore.messages.CommandMessages;
import de.silencio.activecraftcore.messages.Errors;
import de.silencio.activecraftcore.playermanagement.Profile;
import de.silencio.activecraftcore.utils.*;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ActiveCraftCommand implements CommandExecutor, TabCompleter {

    private final String[] commands;

    public ActiveCraftCommand(String... commands) {
        this.commands = commands;
    }

    public static Player getPlayer(String input) throws InvalidPlayerException {
        if (Bukkit.getPlayer(input) == null) throw new InvalidPlayerException(input);
        return Bukkit.getPlayer(input);
    }

    public static World getWorld(String input) throws InvalidWorldException {
        if (Bukkit.getWorld(input) == null) throw new InvalidWorldException(input);
        return Bukkit.getWorld(input);
    }

    public static String combineArray(String[] args) {
        return combineArray(args, 0, args.length, " ");
    }

    public static String combineArray(String[] args, int start) {
        return combineArray(args, start, args.length, " ");
    }

    public static String combineArray(String[] args, int start, int stop) {
        return combineArray(args, start, stop, " ");
    }

    public static String combineArray(String[] args, int start, String splitter) {
        return combineArray(args, start, args.length, splitter);
    }


    public static String combineArray(String[] args, int start, int stop, String splitter) {
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = start; i < stop; i++) {
            if (i != start) resultBuilder.append(splitter);
            resultBuilder.append(args[i]);
        }
        return resultBuilder.toString();
    }

    public static String combineList(List<String> args) {
        return combineList(args, 0, args.size(), " ");
    }

    public static String combineList(List<String> args, int start) {
        return combineList(args, start, args.size(), " ");
    }

    public static String combineList(List<String> args, int start, int stop) {
        return combineList(args, start, stop, " ");
    }

    public static String combineList(List<String> args, int start, String splitter) {
        return combineList(args, start, args.size(), splitter);
    }

    public static String combineList(List<String> args, int start, int stop, String splitter) {
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = start; i < stop; i++) {
            if (i != start) resultBuilder.append(splitter);
            resultBuilder.append(args.get(i));
        }
        return resultBuilder.toString();
    }

    public static Material getMaterial(String inputString) throws InvalidArgumentException {
        if (Material.getMaterial(inputString.toUpperCase()) == null) throw new InvalidArgumentException();
        return Material.getMaterial(inputString.toUpperCase());
    }

    public static Integer parseInt(String numStr) throws InvalidNumberException {
        try {
            return Integer.valueOf(numStr);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException(numStr);
        }
    }

    public static Long parseLong(String numStr) throws InvalidNumberException {
        try {
            return Long.valueOf(numStr);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException(numStr);
        }
    }

    public static Double parseDouble(String numStr) throws InvalidNumberException {
        try {
            return Double.valueOf(numStr);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException(numStr);
        }
    }

    public static Float parseFloat(String numStr) throws InvalidNumberException {
        try {
            return Float.valueOf(numStr);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException(numStr);
        }
    }

    public static EntityType parseEntityType(String mobName) throws InvalidEntityException {
        try {
            EntityType entityType = EntityType.valueOf(mobName.toUpperCase());
            if (entityType == EntityType.UNKNOWN) throw new InvalidEntityException(mobName);
            return entityType;
        } catch (IllegalArgumentException e) {
            throw new InvalidEntityException(mobName);
        }
    }

    public static boolean isValidCommand(String input) {
        for (String registeredCommand : Bukkit.getCommandMap().getKnownCommands().keySet())
            if (input.replace("/", "").equals(registeredCommand))
                return true;
        return false;
    }

    public static boolean checkTargetSelf(CommandSender sender, CommandSender target, String permission) throws SelfTargetException {
        return checkTargetSelf(sender, target.getName(), permission);
    }

    public static boolean checkTargetSelf(CommandSender sender, String name2, String permission) throws SelfTargetException {
        if (sender.getName().equalsIgnoreCase(name2)) {
            if (!sender.hasPermission(permission)) throw new SelfTargetException(sender, permission);
            else return true;
        } else return false;
    }

    public static void checkTargetSelf(CommandSender sender, CommandSender target) throws SelfTargetException {
        if (sender.getName().equalsIgnoreCase(target.getName())) throw new SelfTargetException(sender, "null");
    }

    public static void checkTargetSelf(CommandSender sender, String target) throws SelfTargetException {
        if (sender.getName().equalsIgnoreCase(target)) throw new SelfTargetException(sender, "null");
    }

    public static boolean checkPermission(Permissible permissible, String perm) throws NoPermissionException {
        if (!permissible.hasPermission(perm)) {
            throw new NoPermissionException(permissible, perm);
        } else return true;
    }

    public static Player getPlayer(CommandSender sender) throws NoPlayerException {
        if (!(sender instanceof Player)) throw new NoPlayerException(sender.getName());
        return (Player) sender;
    }

    public static boolean checkHoldingItem(Player player, NotHoldingItemException.ExpectedItem expectedItem, Material... material) throws NotHoldingItemException {
        if (expectedItem == NotHoldingItemException.ExpectedItem.ANY && player.getInventory().getItemInMainHand().getType() != Material.AIR)
            return true;
        if (!(Arrays.stream(material).toList().contains(player.getInventory().getItemInMainHand().getType())))
            throw new NotHoldingItemException(player, expectedItem);
        return true;
    }

    public static boolean checkHoldingItem(Player player, NotHoldingItemException.ExpectedItem expectedItem, Material material) throws NotHoldingItemException {
        if (expectedItem == NotHoldingItemException.ExpectedItem.ANY && player.getInventory().getItemInMainHand().getType() != Material.AIR)
            return true;
        if (player.getInventory().getItemInMainHand().getType() != material) {
            throw new NotHoldingItemException(player, expectedItem);
        }
        return true;
    }

    public static boolean checkArgsLength(String[] args, ComparisonType compType, int i2) throws InvalidArgumentException {
        if (!IntegerUtils.compareInt(args.length, compType, i2)) throw new InvalidArgumentException();
        return true;
    }

    public static boolean checkArgsLength(String[] args, ComparisonType compType, int i2, InvalidArgumentException.ErrorType errorType) throws InvalidArgumentException {
        if (!IntegerUtils.compareInt(args.length, compType, i2)) throw new InvalidArgumentException(errorType);
        return true;
    }

    public static Profile getProfile(String playername) throws InvalidPlayerException {
        if (ActiveCraftCore.getProfile(playername) == null) throw new InvalidPlayerException(playername);
        return ActiveCraftCore.getProfile(playername);
    }

    public static Color getColor(String input) throws InvalidColorException {
        if (!input.startsWith("#")) {
            if (ColorUtils.bukkitColorFromString(input) == null) throw new InvalidColorException();
            return ColorUtils.bukkitColorFromString(input);
        } else {
            if (input.length() != 7) throw new InvalidColorException();
            if (!input.replace("#", "").toLowerCase().matches("(\\d|[a-f])(\\d|[a-f])(\\d|[a-f])(\\d|[a-f])(\\d|[a-f])(\\d|[a-f])"))
                throw new InvalidColorException();
            int[] rgbArray = ColorUtils.getRGB(input);
            return Color.fromRGB(rgbArray[0], rgbArray[1], rgbArray[2]);
        }
    }

    public static Profile getProfile(Player player) {
        return ActiveCraftCore.getProfile(player.getName());
    }

    public static void sendMessage(CommandSender receiver, String message) {
        receiver.sendMessage(message);
    }

    public static void sendMessage(CommandSender receiver, BaseComponent... message) {
        receiver.sendMessage(message);
    }

    public static void sendSilentMessage(CommandSender receiver, String message) {
        FileConfig mainConfig = ConfigUtils.getMainConfig();
        if (!mainConfig.getBoolean("silent-mode")) receiver.sendMessage(message);
    }

    public static void sendSilentMessage(CommandSender receiver, BaseComponent... message) {
        FileConfig mainConfig = ConfigUtils.getMainConfig();
        if (!mainConfig.getBoolean("silent-mode")) receiver.sendMessage(message);
    }

    public static ChatColor getChatColor(String name) throws InvalidColorException {
        if (ColorUtils.getColorByName(name) == null) throw new InvalidColorException();
        return ColorUtils.getColorByName(name);
    }

    public abstract void runCommand(CommandSender sender, Command command, String label, String[] args) throws ActiveCraftException;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        try {
            runCommand(commandSender, command, s, strings);
        } catch (ActiveCraftException e) {
            switch (e.getClass().getSimpleName()) {
                case "InvalidArgumentException" -> {
                    switch (((InvalidArgumentException) e).getErrorType()) {
                        case INCLUDE_MESSAGE -> commandSender.sendMessage(Errors.WARNING() + CommandMessages.INCLUDE_MESSAGE());
                        case DEFAULT -> commandSender.sendMessage(Errors.INVALID_ARGUMENTS());
                    }
                }
                case "InvalidColorException" -> commandSender.sendMessage(Errors.INVALID_COLOR());
                case "InvalidEntityException" -> commandSender.sendMessage(Errors.INVALID_ENTITY());
                case "InvalidHomeException" -> {
                    if (((InvalidHomeException) e).getPlayer() != null)
                        commandSender.sendMessage(CommandMessages.HOME_OTHERS_NOT_SET(((InvalidHomeException) e).getPlayer(), ((InvalidHomeException) e).getInvalidString()));
                    else
                        commandSender.sendMessage(CommandMessages.HOME_NOT_SET(((InvalidHomeException) e).getInvalidString()));
                }
                case "InvalidNumberException" -> commandSender.sendMessage(Errors.INVALID_NUMBER());
                case "InvalidPlayerException" -> commandSender.sendMessage(Errors.INVALID_PLAYER());
                case "InvalidWorldException" -> commandSender.sendMessage(Errors.INVALID_WORLD());
                case "NoPermissionException" -> commandSender.sendMessage(Errors.NO_PERMISSION());
                case "NoPlayerException" -> commandSender.sendMessage(Errors.NOT_A_PLAYER());
                case "NotHoldingItemException" -> {
                    switch (((NotHoldingItemException) e).getExpectedItem()) {
                        case WRITTEN_BOOK -> commandSender.sendMessage(Errors.WARNING() + CommandMessages.NOT_HOLDING_BOOK());
                        case LEATHER_ITEM -> commandSender.sendMessage(Errors.WARNING() + CommandMessages.NO_LEATHER_ITEM());
                        case ANY -> commandSender.sendMessage(Errors.NOT_HOLDING_ITEM());
                    }
                }
                case "SelfTargetException" -> commandSender.sendMessage(Errors.CANNOT_TARGET_SELF());
            }
        }
        return false;
    }

    public abstract List<String> onTab(CommandSender sender, Command command, String label, String[] args);

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = onTab(sender, command, alias, args);
        if (list == null) list = new ArrayList<>();

        ArrayList<String> completerList = new ArrayList<>();
        String currentarg = args[args.length - 1].toLowerCase();
        for (String s : list) {
            String s1 = s.toLowerCase();
            if (s1.startsWith(currentarg))
                completerList.add(s);
        }
        return completerList;
    }

    protected List<String> getBukkitPlayernames() {
        List<String> list = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers())
            list.add(player.getName());
        return list;
    }

    protected List<String> getProfileNames() {
        List<String> list = new ArrayList<>();
        for (String playername : ActiveCraftCore.getProfiles().keySet())
            list.add(ActiveCraftCore.getProfile(playername).getName());
        return list;
    }

    public String[] getCommands() {
        return commands;
    }
}
