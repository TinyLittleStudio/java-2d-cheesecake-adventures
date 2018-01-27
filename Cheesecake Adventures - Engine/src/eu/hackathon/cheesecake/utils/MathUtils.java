/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.utils;

/**
 * This class consists exclusively of static methods that operate on or return values. All methods
 * are of mathematical origin.
 *
 * @author Onur Alici
 * @version 1.0.0
 */
public final class MathUtils {

    /**
     * The Constructor.
     * <p>
     * <p>The Constructor is private.</p>
     */
    private MathUtils() {

    }

    /**
     * Returns the given value clamped between minimum and maximum.
     * <p>
     * <p>This method checks if a value is bigger than a given maximum and returns the maximum if its bigger. This
     * method checks if a value is smaller than a given minimum and returns the minimum if its smaller. Else the original
     * value is returned.</p>
     *
     * @param value (integer) the value to clamp
     * @param min   (integer) the minimum of the value
     * @param max   (integer) the maximum of the value
     * @return the clamped value as integer.
     */
    public static int clamp(int value, int min, int max) {
        return (int) clamp((double) value, (double) min, (double) max);
    }

    /**
     * Returns the given value clamped between minimum and maximum.
     * <p>
     * <p>This method checks if a value is bigger than a given maximum and returns the maximum if its bigger. This
     * method checks if a value is smaller than a given minimum and returns the minimum if its smaller. Else the original
     * value is returned.</p>
     *
     * @param value (double) the value to clamp
     * @param min   (double) the minimum of the value
     * @param max   (double) the maximum of the value
     * @return the clamped value as double.
     */
    public static double clamp(double value, double min, double max) {
        if (value > max) {
            return max;
        }

        if (value < min) {
            return min;
        }
        return value;
    }

    /* A character index for roman characters. */
    private static final String[] ROMAN_CHARACTERS = {"M", "CM", "D", "C", "XC", "L", "X", "IX", "V", "I"};

    /* A value index for the corresponding integer value of the roman characters. */
    private static final int[] ROMAN_CHARACTER_VALUES = {1000, 900, 500, 100, 90, 50, 10, 9, 5, 1};

    /**
     * Returns the number as roman number.
     * <p>
     * <p>This method converts a given integer value into a roman number. This number is represented as a
     * simple string.</p>
     *
     * @param value The number to format into a roman number.
     * @return Returns the formatted number.
     */
    public static String toRoman(int value) {
        String result = "";

        for (int i = 0; i < ROMAN_CHARACTER_VALUES.length; i++) {
            int numberInPlace = value / ROMAN_CHARACTER_VALUES[i];

            if (numberInPlace == 0) {
                continue;
            }
            result += numberInPlace == 4 && i > 0 ? ROMAN_CHARACTERS[i] + ROMAN_CHARACTERS[i - 1] : new String(new char[numberInPlace]).replace("\0", ROMAN_CHARACTERS[i]);

            value = value % ROMAN_CHARACTER_VALUES[i];
        }
        return result;
    }
}
