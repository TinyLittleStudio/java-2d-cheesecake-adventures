/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.internal.controls.mouse;

import eu.hackathon.cheesecake.Structure;
import eu.hackathon.cheesecake.internal.controls.Input;
import eu.hackathon.cheesecake.internal.controls.KeyCode;
import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.HashMap;

/**
 * This class listens for every kind of Mouse input.
 *
 * @author Onur Alici
 * @version 1.0.0
 */
public class Mouse implements Structure, MouseStructure {

    /* The mouse instance. */
    private static final Mouse MOUSE = new Mouse();

    /**
     * A map of all key states.
     */
    private HashMap<Integer, Input> input = new HashMap<Integer, Input>();

    /**
     * The location of the cursor.
     */
    private static Rectangle location = new Rectangle(0, 0, 1, 1);

    /**
     * The mouse wheel scroll value.
     */
    private static double mouseWheelValue = 0.0d;

    /**
     * If the mouse is enabled.
     */
    private boolean isEnabled = true;

    /**
     * The Constructor.
     */
    private Mouse() {

    }

    @Override
    public void onMouseClick(MouseEvent e) {

    }

    @Override
    public void onMouseDown(MouseEvent e) {
        Input input = getInput(e.getButton());

        if (input.isReleased()) {
            input.setReleased(false);
        }

        if (!input.isKey()) {
            input.setPressed(true);
        }
        input.isKey(true);
    }

    @Override
    public void onMouseUp(MouseEvent e) {
        Input input = getInput(e.getButton());

        if (input.isPressed()) {
            input.setPressed(false);
        }

        if (input.isKey()) {
            input.isKey(false);
        }
        input.setReleased(true);
    }

    @Override
    public void onMouseMove(MouseEvent e, boolean isDragging) {
        location.setLocation(e.getPoint());
    }

    @Override
    public void onMouseScroll(MouseWheelEvent e) {
        mouseWheelValue = e.getPreciseWheelRotation();
    }

    @Override
    public void tick() {

    }

    @Override
    public void draw(ExtendedGraphics extendedGraphics) {
        for (Input input : this.input.values()) {
            input.clear();
        }
        mouseWheelValue = 0.0d;
    }

    @Override
    public void dispose() {

    }

    /**
     * Returns the stored input object.
     * <p>
     * <p>This method returns a stored input object corresponding to the given keycode. If
     * no such elemen exists, one will be created.</p>
     * <p>
     * <p>This method is private.</p>
     *
     * @param keyCode The keycode corresponding to the input state object.
     * @return Returns the stored input object.
     * @see Input
     */
    private Input getInput(int keyCode) {
        if (!input.containsKey(keyCode)) {
            input.put(keyCode, new Input());
        }
        return input.get(keyCode);
    }

    /**
     * Returns if the cursor is in the given area.
     * <p>
     * <p>This method draws a rectangle and checks if the cursors last position is intersecting with the given location.</p>
     *
     * @param x      The x coordinate of the area.
     * @param y      The y coordinate of the area.
     * @param width  The width of the area.
     * @param height The height of the area.
     * @return Returns if the cursor is in the given area.
     */
    public static boolean isInArea(int x, int y, int width, int height) {
        return location.intersects(x, y, width, height);
    }

    /**
     * Returns if the button is currently active.
     * <p>
     * <p>Checks for the stored Input object corresponding to the given button value and
     * returns if the button is currently active in use.</p>
     * <p>
     * <p>This method ist static.</p>
     *
     * @param keyCode The button value.
     * @return Returns whether the button is active in use or not.
     */
    public static boolean getKey(int keyCode) {
        return Mouse.getMouse().getInput(keyCode).isKey();
    }

    /**
     * Returns if the button is currently active.
     * <p>
     * <p>Checks for the stored Input object corresponding to the given button value and
     * returns if the button is currently active in use.</p>
     * <p>
     * <p>This method ist static.</p>
     *
     * @param keyCode The button value from the KeyCode enum.
     * @return Returns whether the button is active in use or not.
     * @see KeyCode
     */
    public static boolean getKey(KeyCode keyCode) {
        return Mouse.getMouse().getInput(keyCode.value()).isKey();
    }

    /**
     * Returns if the button is currently pressed.
     * <p>
     * <p>Checks for the stored Input object corresponding to the given button value and
     * returns if the button is currently pressed.</p>
     * <p>
     * <p>This method can only be requested a few times depending on the given input buffer.</p>
     * <p>
     * <p>This method ist static.</p>
     *
     * @param keyCode The button value.
     * @return Returns whether the button is pressed or not.
     */
    public static boolean getKeyDown(int keyCode) {
        return Mouse.getMouse().getInput(keyCode).isPressed();
    }

    /**
     * Returns if the button is currently pressed.
     * <p>
     * <p>Checks for the stored Input object corresponding to the given button value and
     * returns if the button is currently pressed.</p>
     * <p>
     * <p>This method can only be requested a few times depending on the given input buffer.</p>
     * <p>
     * <p>This method ist static.</p>
     *
     * @param keyCode The button value from the KeyCode enum.
     * @return Returns whether the button is pressed or not.
     * @see KeyCode
     */
    public static boolean getKeyDown(KeyCode keyCode) {
        return Mouse.getMouse().getInput(keyCode.value()).isPressed();
    }

    /**
     * Returns if the button is currently released.
     * <p>
     * <p>Checks for the stored Input object corresponding to the given button value and
     * returns if the button is currently released.</p>
     * <p>
     * <p>This method can only be requested a few times depending on the given input buffer.</p>
     * <p>
     * <p>This method ist static.</p>
     *
     * @param keyCode The button value.
     * @return Returns whether the button is released or not.
     */
    public static boolean getKeyUp(int keyCode) {
        return Mouse.getMouse().getInput(keyCode).isReleased();
    }

    /**
     * Returns if the button is currently released.
     * <p>
     * <p>Checks for the stored Input object corresponding to the given button value and
     * returns if the button is currently released.</p>
     * <p>
     * <p>This method can only be requested a few times depending on the given input buffer.</p>
     * <p>
     * <p>This method ist static.</p>
     *
     * @param keyCode The button value from the KeyCode enum.
     * @return Returns whether the button is released or not.
     * @see KeyCode
     */
    public static boolean getKeyUp(KeyCode keyCode) {
        return Mouse.getMouse().getInput(keyCode.value()).isReleased();
    }

    /**
     * Retuns the mouse location.
     * <p>
     * <p>This method returns the last tracked location of the mouse.</p>
     * <p>
     * <p>This method ist static.</p>
     *
     * @return Returns the last position as Point.
     * @see Point
     */
    public static Point getMouseLocation() {
        return location.getLocation();
    }

    /**
     * Returns the mouse wheel scroll value.
     *
     * @return Returns the stored mouse wheel scroll value.
     */
    public static double getMouseWheelValue() {
        return mouseWheelValue;
    }


    /**
     * Enables the mouse input.
     */
    public static void enable() {
        Mouse.getMouse().isEnabled = true;
    }

    /**
     * Disables the mouse input.
     */
    public static void disable() {
        Mouse.getMouse().isEnabled = false;
    }

    /**
     * Returns if the mouse is enabled.
     * <p>
     * <p>Returns whether the mouse is enabled or not. If it is not, then no input will be received.</p>
     *
     * @return Returns whether the mouse is enabled or not.
     */
    public static boolean isEnabled() {
        return Mouse.getMouse().isEnabled;
    }

    /**
     * Returns if the mouse is disabled.
     * <p>
     * <p>Returns whether the mouse is disabled or not. If it is, then no input will be received.</p>
     *
     * @return Returns whether the mouse is disabled or not.
     */
    public static boolean isDisabled() {
        return !Mouse.getMouse().isEnabled;
    }

    /**
     * Returns the mouse instance.
     * <p>
     * <p>There will only be one instance created.</p>
     * <p>
     * <p>This method is static.</p>
     *
     * @return Returns the active mouse instance.
     */
    public static Mouse getMouse() {
        return Mouse.MOUSE;
    }
}
