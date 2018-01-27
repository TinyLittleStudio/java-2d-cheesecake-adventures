/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.utils.internal;

/**
 * This class consists exclusively of static methods that operate on or return time based values.
 *
 * @author Onur Alici
 * @version 1.0.0
 */
public final class Time {

    /**
     * This static final variable marks the maximum tick-draw-rate of the TLS-Application.
     */
    static final int MAX_FPS_TPS = 60;

    /**
     * This static final variable marks the time in between the tick and draw updates.
     */
    static final double PSD_DELTA_TIME = 1.0d / (double) MAX_FPS_TPS;

    /**
     * This method returns the time between tick and draw updates.
     * <p>
     * <p>This is the time that normally passes between updates. This method can be used to normalize
     * time or updated based calculations and smooth interpolation.</p>
     *
     * @return (float) the adjusted time between updates to normalize values.
     */
    public static float deltaTime() {
        return (float) PSD_DELTA_TIME;
    }
}
