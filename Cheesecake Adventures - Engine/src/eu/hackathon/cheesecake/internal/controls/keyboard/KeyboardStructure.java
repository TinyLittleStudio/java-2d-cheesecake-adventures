/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.internal.controls.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This interface acts as a listener for receiving "interesting" keyboard events
 * (press, release) on a component.
 * <p>
 * <p>This interface basically inherits basic java.awt.* events for keyboard handling.</p>
 *
 * @author Onur Alici
 * @version 1.0.0
 * @see Keyboard
 * @see KeyListener
 */
public interface KeyboardStructure extends KeyListener {
    @Override
    default void keyTyped(KeyEvent e) { /* ... */ }

    @Override
    default void keyPressed(KeyEvent e) {
        onKeyDown(e);
    }

    /**
     * This method is invoked when a key has been pressed.
     *
     * @param e Basic java.awt.event.KeyEvent
     * @see KeyEvent
     */
    void onKeyDown(KeyEvent e);

    @Override
    default void keyReleased(KeyEvent e) {
        onKeyUp(e);
    }

    /**
     * Invoked when a key has been released.
     *
     * @param e Basic java.awt.event.KeyEvent
     * @see KeyEvent
     */
    void onKeyUp(KeyEvent e);
}
