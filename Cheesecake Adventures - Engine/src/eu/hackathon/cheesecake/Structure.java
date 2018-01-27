/*
 * Copyright (c) 2017, Tiny Little Studio and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake;

import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;

/**
 * This interface consists of base methods required for most of the classes in the application.
 *
 * @author Onur Alici
 * @version 1.0.0
 */
public interface Structure {

    /**
     * Method initializes base-values for objects in the application.
     * <p>
     * <p>This method is mostly for initialization purposes.</p>
     */
    default void init() { /* ... */ }

    /**
     * Ticks x times per second.
     * <p>
     * <p>The Application is calling the tick Method to update the
     * state x times per second.</p>
     */
    default void tick() { /* ... */ }

    /**
     * Draws x times per second.
     * <p>
     * <p>The Application is calling the draw Method to update the
     * screen x times per second.</p>
     *
     * @param extendedGraphics the drawing object passed down from the Window class.
     */
    default void draw(ExtendedGraphics extendedGraphics) { /* ... */ }

    /**
     * Method is called last in the application.
     * <p>
     * <p>This method is mostly for cleaning purposes.</p>
     */
    void dispose();
}
