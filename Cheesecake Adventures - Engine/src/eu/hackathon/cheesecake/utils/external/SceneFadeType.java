/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.utils.external;

/**
 * The SceneFadeType is required to fade into a specific direction (in or out).
 *
 * @author Onur Alici
 * @version 1.0.0
 */
public enum SceneFadeType {

    /* The start color and fading direction for IN. */
    IN(-1, 0xff000000),

    /* The start color and fading direction for OUT. */
    OUT(1, 0x00000000);

    /**
     * The multiplier to define the direction to fade.
     */
    private int multiplier;

    /**
     * The starting color when fading.
     */
    private int color;

    /**
     * The Constructor.
     *
     * @param multiplier The multiplier.
     * @param color      The starting color.
     */
    SceneFadeType(int multiplier, int color) {
        this.multiplier = multiplier;
        this.color = color;
    }

    /**
     * Returns the multiplier.
     *
     * @return int Returns the multiplier for directional fade.
     * */
    public int multiplier() {
        return multiplier;
    }

    /**
     * Returns the initial color.
     *
     * @return int Returns the initial color for the fading.
     * */
    public int getInitialColor() {
        return color;
    }
}