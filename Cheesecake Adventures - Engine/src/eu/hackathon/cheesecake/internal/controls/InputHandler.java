/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.internal.controls;

import eu.hackathon.cheesecake.internal.Handler;
import eu.hackathon.cheesecake.internal.controls.keyboard.Keyboard;
import eu.hackathon.cheesecake.internal.controls.mouse.Mouse;
import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;

/**
 * This class acts as a handler for input.
 * <p>
 * <p>This class acts as a container for the Keyboard and Mouse.</p>
 *
 * @author Onur Alici
 * @version 1.0.0
 * @see Keyboard
 * @see Mouse
 */
public class InputHandler implements Handler {

    /**
     * The Constructor.
     */
    public InputHandler() {

    }

    @Override
    public void tick() {
        getKeyboard().tick();
        getMouse().tick();
    }

    @Override
    public void draw(ExtendedGraphics extendedGraphics) {
        getKeyboard().draw(extendedGraphics);
        getMouse().draw(extendedGraphics);
    }

    @Override
    public void dispose() {

    }

    /**
     * Returns the Keyboard.
     * <p>
     * <p>Returns the currently registered Keyboard.</p>
     *
     * @return Returns the Keyboard.
     */
    public Keyboard getKeyboard() {
        return Keyboard.getKeyboard();
    }

    /**
     * Returns the Mouse.
     * <p>
     * <p>Returns the currently registered Mouse.</p>
     *
     * @return Returns the Mouse.
     */
    public Mouse getMouse() {
        return Mouse.getMouse();
    }
}
