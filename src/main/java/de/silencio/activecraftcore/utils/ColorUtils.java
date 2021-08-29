package de.silencio.activecraftcore.utils;

import org.bukkit.Color;

public class ColorUtils {

    public static Color bukkitColorFromString(String string) {
        Color color = null;

        switch (string.toLowerCase()) {
            case "green":
                color = Color.GREEN;
                break;
            case "black":
                color = Color.BLACK;
                break;
            case "dark_blue":
                color = Color.NAVY;
                break;
            case "dark_green":
                color = Color.OLIVE;
                break;
            case "dark_aqua":
                color = Color.TEAL;
                break;
            case "dark_red":
                color = Color.MAROON;
                break;
            case "light_purple":
                color = Color.FUCHSIA;
                break;
            case "pink":
                int[] rgbArray_pink = ColorUtils.getRGB("#F38BAA");
                color = Color.fromRGB(rgbArray_pink[0], rgbArray_pink[1], rgbArray_pink[2]);
                break;
            case "orange":
                int[] rgbArray_orange = ColorUtils.getRGB("#F9801D");
                color = Color.fromRGB(rgbArray_orange[0], rgbArray_orange[1], rgbArray_orange[2]);
                break;
            case "gray":
                color = Color.SILVER;
                break;
            case "dark_gray":
                color = Color.GRAY;
                break;
            case "blue":
                color = Color.BLUE;
                break;
            case "aqua":
                color = Color.AQUA;
                break;
            case "red":
                color = Color.RED;
                break;
            case "dark_purple":
                color = Color.PURPLE;
                break;
            case "yellow":
                color = Color.YELLOW;
                break;
            case "white":
                color = Color.WHITE;
                break;
            case "pepega_green":
                int[] rgbArray = ColorUtils.getRGB("#0aad1b");
                color = Color.fromRGB(rgbArray[0], rgbArray[1], rgbArray[2]);
                break;

        }

        return color;
    }

    public static int[] getRGB(String rgb) {
        rgb = rgb.replace("#", "");
        final int[] ret = new int[3];
        for (int i = 0; i < 3; i++)
        {
            ret[i] = Integer.parseInt(rgb.substring(i * 2, i * 2 + 2), 16);
        }
        return ret;
    }

}
