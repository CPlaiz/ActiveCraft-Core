package de.silencio.activecraftcore.utils;

public class IntegerUtils {

    public static String shortInteger(int duration) {
        String string = "";
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        if (duration / 60 / 60 / 24 >= 1) {
            duration -= duration / 60 / 60 / 24 * 60 * 60 * 24;
        }
        if (duration / 60 / 60 >= 1) {
            hours = duration / 60 / 60;
            duration -= duration / 60 / 60 * 60 * 60;
        }
        if (duration / 60 >= 1) {
            minutes = duration / 60;
            duration -= duration / 60 * 60;
        }
        if (duration >= 1)
            seconds = duration;
        if (hours <= 9) {
            string += "0" + hours + ":";
        } else {
            string += hours + ":";
        }
        if (minutes <= 9) {
            string += "0" + minutes + ":";
        } else {
            string += minutes + ":";
        }
        if (seconds <= 9) {
            string += "0" + seconds;
        } else {
            string += + seconds;
        }
        return string;
    }

    public static String shortIntWithoutHours(int duration) {
        {
            String string = "";
            int minutes = 0;
            int seconds = 0;
            if (duration / 60 / 60 / 24 >= 1) {
                duration -= duration / 60 / 60 / 24 * 60 * 60 * 24;
            }
            if (duration / 60 >= 1) {
                minutes = duration / 60;
                duration -= duration / 60 * 60;
            }
            if (duration >= 1)
                seconds = duration;

            if (minutes <= 9) {
                string += "0" + minutes + ":";
            } else {
                string += minutes + ":";
            }
            if (seconds <= 9) {
                string += "0" + seconds;
            } else {
                string += seconds;
            }
            return string;
        }
    }

    public static boolean compareInt(int i1, ComparisonType compType, int i2) {
        return switch (compType) {
            case GREATER -> i1 > i2;
            case GREATER_EQUAL -> i1 >= i2;
            case EQUAL ->  i1 == i2;
            case LESS_EQUAL -> i1 <= i2;
            case LESS -> i1 < i2;
            case NOT_EQUAL -> i1 != i2;
        };
    }

}
