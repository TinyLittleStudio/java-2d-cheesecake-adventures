/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.internal.controls.mouse;

import eu.hackathon.cheesecake.Structure;

import java.awt.event.*;

/**
 * This interface acts as a listener for receiving "interesting" mouse events
 * (press, release, click, move, wheel) on a component.
 * <p>
 * <p>This interface basically inherits basic java.awt.* events for mouse handling.</p>
 *
 * @author Onur Alici
 * @version 1.0.0
 * @see Mouse
 * @see MouseListener
 * @see MouseMotionListener
 * @see MouseWheelListener
 */
public interface MouseStructure extends Structure, MouseListener, MouseMotionListener, MouseWheelListener {
    @Override
    default void mouseClicked(MouseEvent e) {
        onMouseClick(e);
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released).
     * <p>
     * <p>This method will only be invoked from inside the Application.</p>
     * <p>
     * <p>This method is package-private.</p>
     *
     * @param e Basic java.awt.event.MouseEvent
     * @see MouseEvent
     */
    void onMouseClick(MouseEvent e);

    @Override
    default void mousePressed(MouseEvent e) {
        onMouseDown(e);
    }

    /**
     * Invoked when a mouse button has been pressed.
     * <p>
     * <p>This method will only be invoked from inside the Application.</p>
     * <p>
     * <p>This method is package-private.</p>
     *
     * @param e Basic java.awt.event.MouseEvent
     * @see MouseEvent
     */
    void onMouseDown(MouseEvent e);

    @Override
    default void mouseReleased(MouseEvent e) {
        onMouseUp(e);
    }

    /**
     * Invoked when a mouse button has been released.
     * <p>
     * <p>This method will only be invoked from inside the Application.</p>
     * <p>
     * <p>This method is package-private.</p>
     *
     * @param e Basic java.awt.event.MouseEvent
     * @see MouseEvent
     */
    void onMouseUp(MouseEvent e);

    @Override
    default void mouseEntered(MouseEvent e) { /* ... */ }

    @Override
    default void mouseExited(MouseEvent e) { /* ... */ }

    @Override
    default void mouseDragged(MouseEvent e) {
        onMouseMove(e, true);
    }

    @Override
    default void mouseMoved(MouseEvent e) {
        onMouseMove(e, false);
    }

    /**
     * Invoked when the mouse cursor has been moved.
     * <p>
     * <p>This method will only be invoked from inside the Application.</p>
     * <p>
     * <p>This method is package-private.</p>
     *
     * @param e          Basic java.awt.event.MouseEvent.MouseWheelEvent
     * @param isDragging If the mouse was dragging or not.
     * @see MouseEvent
     */
    void onMouseMove(MouseEvent e, boolean isDragging);

    @Override
    default void mouseWheelMoved(MouseWheelEvent e) {
        onMouseScroll(e);
    }

    /**
     * Invoked when the mouse wheel is rotated.
     * <p>
     * <p>This method will only be invoked from inside the Application.</p>
     * <p>
     * <p>This method is package-private.</p>
     *
     * @param e Basic java.awt.event.MouseEvent.MouseWheelEvent
     * @see MouseWheelEvent
     */
    void onMouseScroll(MouseWheelEvent e);
}
