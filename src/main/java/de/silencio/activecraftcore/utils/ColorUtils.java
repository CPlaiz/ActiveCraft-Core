package de.silencio.activecraftcore.utils;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.ChatColor;
import org.bukkit.Color;

import java.util.Random;

public class ColorUtils {

    public static ChatColor[] getColorsOnly() {
        return (ChatColor[]) ArrayUtils.subarray(ChatColor.values(), 0, ChatColor.values().length - 6);
    }

    public static ChatColor getRandomColor() {
        return getColorsOnly()[new Random().nextInt(getColorsOnly().length)];
    }

    public static ChatColor getColorByName(String name) {
        for (ChatColor color : getColorsOnly())
            if (name.equalsIgnoreCase(color.name())) return color;
        return null;
    }

    public static String replaceColorAndFormat(String message) {
        return replaceColor(replaceFormat(message));
    }

    public static String replaceColor(String message) {
        message = replaceHex(message);
        return message.replace("&0", "§0")
                .replace("&1", "§1")
                .replace("&2", "§2")
                .replace("&3", "§3")
                .replace("&4", "§4")
                .replace("&5", "§5")
                .replace("&6", "§6")
                .replace("&7", "§7")
                .replace("&8", "§8")
                .replace("&9", "§9")
                .replace("&a", "§a")
                .replace("&b", "§b")
                .replace("&c", "§c")
                .replace("&d", "§d")
                .replace("&e", "§e")
                .replace("&f", "§f");
    }

    public static String replaceFormat(String message) {
        return message.replace("&0", "§0")
                .replace("&k", "§k")
                .replace("&l", "§l")
                .replace("&m", "§m")
                .replace("&n", "§n")
                .replace("&o", "§o")
                .replace("&r", "§r");
    }

    public static String removeColorAndFormat(String string) {

        return string
                .replaceAll("§x(§[a-fA-F0-9]){6}", "")
                .replace("§0", "")
                .replace("§1", "")
                .replace("§2", "")
                .replace("§3", "")
                .replace("§4", "")
                .replace("§5", "")
                .replace("§6", "")
                .replace("§7", "")
                .replace("§8", "")
                .replace("§9", "")
                .replace("§a", "")
                .replace("§b", "")
                .replace("§c", "")
                .replace("§d", "")
                .replace("§e", "")
                .replace("§f", "")
                .replace("§k", "")
                .replace("§l", "")
                .replace("§m", "")
                .replace("§n", "")
                .replace("§o", "")
                .replace("§r", "")
                .replace("§x", "");
    }


    public static Color bukkitColorFromString(String string) {
        Color color = null;

        switch (string.toLowerCase()) {
            case "green" -> {
                int[] rgbArray_green = ColorUtils.getRGB("#5E7C16");
                color = Color.fromRGB(rgbArray_green[0], rgbArray_green[1], rgbArray_green[2]);
            }
            case "black" -> {
                int[] rgbArray_black = ColorUtils.getRGB("#1D1D21");
                color = Color.fromRGB(rgbArray_black[0], rgbArray_black[1], rgbArray_black[2]);
            }
            case "blue" -> {
                int[] rgbArray_blue = ColorUtils.getRGB("#3C44AA");
                color = Color.fromRGB(rgbArray_blue[0], rgbArray_blue[1], rgbArray_blue[2]);
            }
            case "lime" -> {
                int[] rgbArray_lime = ColorUtils.getRGB("#80C71F");
                color = Color.fromRGB(rgbArray_lime[0], rgbArray_lime[1], rgbArray_lime[2]);
            }
            case "cyan" -> {
                int[] rgbArray_cyan = ColorUtils.getRGB("#169C9C");
                color = Color.fromRGB(rgbArray_cyan[0], rgbArray_cyan[1], rgbArray_cyan[2]);
            }
            case "red" -> {
                int[] rgbArray_red = ColorUtils.getRGB("#B02E26");
                color = Color.fromRGB(rgbArray_red[0], rgbArray_red[1], rgbArray_red[2]);
            }
            case "magenta" -> {
                int[] rgbArray_magenta = ColorUtils.getRGB("#C74EBD");
                color = Color.fromRGB(rgbArray_magenta[0], rgbArray_magenta[1], rgbArray_magenta[2]);
            }
            case "pink" -> {
                int[] rgbArray_pink = ColorUtils.getRGB("#F38BAA");
                color = Color.fromRGB(rgbArray_pink[0], rgbArray_pink[1], rgbArray_pink[2]);
            }
            case "orange" -> {
                int[] rgbArray_orange = ColorUtils.getRGB("#F9801D");
                color = Color.fromRGB(rgbArray_orange[0], rgbArray_orange[1], rgbArray_orange[2]);
            }
            case "light_gray" -> {
                int[] rgbArray_light_gray = ColorUtils.getRGB("#9D9D97");
                color = Color.fromRGB(rgbArray_light_gray[0], rgbArray_light_gray[1], rgbArray_light_gray[2]);
            }
            case "gray" -> {
                int[] rgbArray_gray = ColorUtils.getRGB("#474F52");
                color = Color.fromRGB(rgbArray_gray[0], rgbArray_gray[1], rgbArray_gray[2]);
            }
            case "light_blue" -> {
                int[] rgbArray_light_blue = ColorUtils.getRGB("#3AB3DA");
                color = Color.fromRGB(rgbArray_light_blue[0], rgbArray_light_blue[1], rgbArray_light_blue[2]);
            }
            case "purple" -> {
                int[] rgbArray_purple = ColorUtils.getRGB("#8932B8");
                color = Color.fromRGB(rgbArray_purple[0], rgbArray_purple[1], rgbArray_purple[2]);
            }
            case "yellow" -> {
                int[] rgbArray_yellow = ColorUtils.getRGB("#FED83D");
                color = Color.fromRGB(rgbArray_yellow[0], rgbArray_yellow[1], rgbArray_yellow[2]);
            }
            case "white" -> {
                int[] rgbArray_white = ColorUtils.getRGB("#F9FFFE");
                color = Color.fromRGB(rgbArray_white[0], rgbArray_white[1], rgbArray_white[2]);
            }
            case "brown" -> {
                int[] rgbArray_brown = ColorUtils.getRGB("#835432");
                color = Color.fromRGB(rgbArray_brown[0], rgbArray_brown[1], rgbArray_brown[2]);
            }
            case "pepega_green" -> {
                int[] rgbArray_pepega_green = ColorUtils.getRGB("#0aad1b");
                color = Color.fromRGB(rgbArray_pepega_green[0], rgbArray_pepega_green[1], rgbArray_pepega_green[2]);
            }
        }

        return color;
    }

    public static int[] getRGB(String rgb) {
        rgb = rgb.replace("#", "");
        final int[] ret = new int[3];
        for (int i = 0; i < 3; i++) {
            ret[i] = Integer.parseInt(rgb.substring(i * 2, i * 2 + 2), 16);
        }
        return ret;
    }

    public static String replaceHex(String input) {
        String[] inputArray = input.split("&#");
        StringBuilder outputStringBuilder = new StringBuilder();
        outputStringBuilder.append(inputArray[0]);
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i].length() < 6) continue;
            String substring = inputArray[i].substring(0, 6);
            String rest = inputArray[i].length() > 5 ? inputArray[i].substring(6) : "";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("§x");
            for (char strChar : substring.toCharArray()) {
                String s = String.valueOf(strChar);
                stringBuilder.append("§").append(s);
            }
            outputStringBuilder.append(stringBuilder).append(rest);
        }
        return outputStringBuilder.toString();
    }

}
