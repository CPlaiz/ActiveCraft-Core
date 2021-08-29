package de.silencio.activecraftcore.utils;

import org.bukkit.Color;

public class ColorUtils {

    public static Color bukkitColorFromString(String string) {
        Color color = null;

        switch (string.toLowerCase()) {
            case "green":
                int[] rgbArray_green = ColorUtils.getRGB("#5E7C16");
                color = Color.fromRGB(rgbArray_green[0], rgbArray_green[1], rgbArray_green[2]);
                break;
            case "black":
                int[] rgbArray_black = ColorUtils.getRGB("#1D1D21");
                color = Color.fromRGB(rgbArray_black[0], rgbArray_black[1], rgbArray_black[2]);
                break;
            case "blue":
                int[] rgbArray_blue = ColorUtils.getRGB("#3C44AA");
                color = Color.fromRGB(rgbArray_blue[0], rgbArray_blue[1], rgbArray_blue[2]);
                break;
            case "lime":
                int[] rgbArray_lime = ColorUtils.getRGB("#80C71F");
                color = Color.fromRGB(rgbArray_lime[0], rgbArray_lime[1], rgbArray_lime[2]);
                break;
            case "cyan":
                int[] rgbArray_cyan = ColorUtils.getRGB("#169C9C");
                color = Color.fromRGB(rgbArray_cyan[0], rgbArray_cyan[1], rgbArray_cyan[2]);
                break;
            case "red":
                int[] rgbArray_red = ColorUtils.getRGB("#B02E26");
                color = Color.fromRGB(rgbArray_red[0], rgbArray_red[1], rgbArray_red[2]);
                break;
            case "magenta":
                int[] rgbArray_magenta = ColorUtils.getRGB("#C74EBD");
                color = Color.fromRGB(rgbArray_magenta[0], rgbArray_magenta[1], rgbArray_magenta[2]);
                break;
            case "pink":
                int[] rgbArray_pink = ColorUtils.getRGB("#F38BAA");
                color = Color.fromRGB(rgbArray_pink[0], rgbArray_pink[1], rgbArray_pink[2]);
                break;
            case "orange":
                int[] rgbArray_orange = ColorUtils.getRGB("#F9801D");
                color = Color.fromRGB(rgbArray_orange[0], rgbArray_orange[1], rgbArray_orange[2]);
                break;
            case "light_gray":
                int[] rgbArray_light_gray = ColorUtils.getRGB("#9D9D97");
                color = Color.fromRGB(rgbArray_light_gray[0], rgbArray_light_gray[1], rgbArray_light_gray[2]);
                break;
            case "gray":
                int[] rgbArray_gray = ColorUtils.getRGB("#474F52");
                color = Color.fromRGB(rgbArray_gray[0], rgbArray_gray[1], rgbArray_gray[2]);
                break;
            case "light_blue":
                int[] rgbArray_light_blue = ColorUtils.getRGB("#3AB3DA");
                color = Color.fromRGB(rgbArray_light_blue[0], rgbArray_light_blue[1], rgbArray_light_blue[2]);
                break;
            case "purple":
                int[] rgbArray_purple = ColorUtils.getRGB("#8932B8");
                color = Color.fromRGB(rgbArray_purple[0], rgbArray_purple[1], rgbArray_purple[2]);
                break;
            case "yellow":
                int[] rgbArray_yellow = ColorUtils.getRGB("#FED83D");
                color = Color.fromRGB(rgbArray_yellow[0], rgbArray_yellow[1], rgbArray_yellow[2]);
                break;
            case "white":
                int[] rgbArray_white = ColorUtils.getRGB("#F9FFFE");
                color = Color.fromRGB(rgbArray_white[0], rgbArray_white[1], rgbArray_white[2]);
                break;
            case "brown":
                int[] rgbArray_brown = ColorUtils.getRGB("#835432");
                color = Color.fromRGB(rgbArray_brown[0], rgbArray_brown[1], rgbArray_brown[2]);
                break;
            case "pepega_green":
                int[] rgbArray_pepega_green = ColorUtils.getRGB("#0aad1b");
                color = Color.fromRGB(rgbArray_pepega_green[0], rgbArray_pepega_green[1], rgbArray_pepega_green[2]);
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
