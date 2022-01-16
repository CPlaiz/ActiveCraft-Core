package de.silencio.activecraftcore.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private static final String IPV4_REGEX =
            "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private static final Pattern IPv4_PATTERN = Pattern.compile(IPV4_REGEX);

    public static boolean isValidInet4Address(String ip) {
        if (ip == null) {
            return false;
        }

        Matcher matcher = IPv4_PATTERN.matcher(ip);

        return matcher.matches();
    }

    public static String messageWithColor(Player p, String displayname, String colorname) {
        String outputDisplayname = null;
        for (ChatColor color : ChatColor.values()) {
            if (colorname.equalsIgnoreCase(color.name())) {
                if (!colorname.equals("BOLD") && !colorname.equals("MAGIC") && !colorname.equals("STRIKETHROUGH") &&
                        !colorname.equals("ITALIC") && !colorname.equals("UNDERLINE") && !colorname.equals("RESET")) {
                    outputDisplayname = color + displayname;
                }
            }
        }
        return outputDisplayname;
    }

    public static void setDisplaynameFromConfig(Player p, String colorname, String displayname) {
        for (ChatColor color : ChatColor.values()) {
            if (colorname.equalsIgnoreCase(color.name())) {
                if (!colorname.equals("BOLD") && !colorname.equals("MAGIC") && !colorname.equals("STRIKETHROUGH") &&
                        !colorname.equals("ITALIC") && !colorname.equals("UNDERLINE") && !colorname.equals("RESET")) {
                    p.setDisplayName(color + displayname);
                    p.setPlayerListName(color + displayname);
                }
            }
        }
    }

    public static void setDisplaynameFromConfig(Player p, ChatColor color, String prefix, String displayname) {
        if (!Arrays.stream(ColorUtils.getColorsOnly()).toList().contains(color)) return;
        if (prefix == null) prefix = "";
        prefix = prefix.strip() + (prefix.strip().equals("") ? "" : " ");
        p.setDisplayName(prefix + color + displayname);
        p.setPlayerListName(prefix + color + displayname);
    }

    public static String joinQuitWithColor(Player p, String displayname, String colorname) {
        String outputDisplayname = null;
        for (ChatColor color : ChatColor.values()) {
            if (colorname.equalsIgnoreCase(color.name())) {
                if (!colorname.equals("BOLD") && !colorname.equals("MAGIC") && !colorname.equals("STRIKETHROUGH") &&
                        !colorname.equals("ITALIC") && !colorname.equals("UNDERLINE") && !colorname.equals("RESET")) {
                    outputDisplayname = color + displayname;
                }
            }
        }
        return outputDisplayname;
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
}