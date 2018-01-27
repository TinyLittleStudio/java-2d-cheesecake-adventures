/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.internal.controls.keyboard;

import eu.hackathon.cheesecake.Structure;
import eu.hackathon.cheesecake.internal.controls.Input;
import eu.hackathon.cheesecake.internal.controls.KeyCode;
import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;

import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * This class listens for every kind of keyboard input.
 *
 * @author Onur Alici
 * @version 1.0.0
 */
public class Keyboard implements Structure, KeyboardStructure {

    /* The Keyboard instance. */
    private static final Keyboard KEYBOARD = new Keyboard();

    /**
     * A map of all key states.
     */
    private HashMap<Integer, Input> keyboardInput = new HashMap<Integer, Input>();

    /**
     * The last input.
     */
    private char last = Character.MIN_VALUE;

    /**
     * If the keyboard is enabled.
     */
    private boolean isEnabled = true;

    /**
     * The Constructor.
     */
    private Keyboard() {

    }

    @Override
    public void onKeyDown(KeyEvent e) {
        if (isEnabled()) {
            Input input = getInput(e.getKeyCode());

            if (input.isReleased()) {
                input.setReleased(false);
            }

            if (!input.isKey()) {
                input.setPressed(true);
            }
            input.isKey(true);

            Keyboard.getKeyboard().last = e.getKeyChar();
        }
    }

    @Override
    public void onKeyUp(KeyEvent e) {
        if (isEnabled()) {
            Input input = getInput(e.getKeyCode());

            if (input.isPressed()) {
                input.setPressed(false);
            }

            if (input.isKey()) {
                input.isKey(false);
            }
            input.setReleased(true);
        }
    }

    @Override
    public void tick() {
        for (Input input : keyboardInput.values()) {
            input.clear();
        }
    }

    @Override
    public void draw(ExtendedGraphics extendedGraphics) {

    }

    @Override
    public void dispose() {

    }

    /**
     * Returns the Input object for the specific KeyCode.
     * <p>
     * <p>Returns the Input object stored in the map. If it does not exist a new one
     * will be created and put into the map.</p>
     *
     * @param keyCode The key value.
     * @return Returns the Input object stored in the map of states.
     * @see Input
     */
    private Input getInput(int keyCode) {
        if (!keyboardInput.containsKey(keyCode)) {
            keyboardInput.put(keyCode, new Input());
        }
        return keyboardInput.get(keyCode);
    }

    /**
     * Returns if the key is currently active.
     * <p>
     * <p>Checks for the stored Input object corresponding to the given key value and
     * returns if the key is currently active in use.</p>
     * <p>
     * <p>This method ist static.</p>
     *
     * @param keyCode The key value.
     * @return Returns whether the key is active in use or not.
     */
    public static boolean getKey(int keyCode) {
        return Keyboard.getKeyboard().getInput(keyCode).isKey();
    }

    /**
     * Returns if the key is currently active.
     * <p>
     * <p>Checks for the stored Input object corresponding to the given key value and
     * returns if the key is currently active in use.</p>
     * <p>
     * <p>This method ist static.</p>
     *
     * @param keyCode The key value from the KeyCode enum.
     * @return Returns whether the key is active in use or not.
     * @see KeyCode
     */
    public static boolean getKey(KeyCode keyCode) {
        return Keyboard.getKeyboard().getInput(keyCode.value()).isKey();
    }

    /**
     * Returns if the key is currently pressed.
     * <p>
     * <p>Checks for the stored Input object corresponding to the given key value and
     * returns if the key is currently pressed.</p>
     * <p>
     * <p>This method can only be requested a few times depending on the given input buffer.</p>
     * <p>
     * <p>This method ist static.</p>
     *
     * @param keyCode The key value.
     * @return Returns whether the key is pressed or not.
     */
    public static boolean getKeyDown(int keyCode) {
        return Keyboard.getKeyboard().getInput(keyCode).isPressed();
    }

    /**
     * Returns if the key is currently pressed.
     * <p>
     * <p>Checks for the stored Input object corresponding to the given key value and
     * returns if the key is currently pressed.</p>
     * <p>
     * <p>This method can only be requested a few times depending on the given input buffer.</p>
     * <p>
     * <p>This method ist static.</p>
     *
     * @param keyCode The key value from the KeyCode enum.
     * @return Returns whether the key is pressed or not.
     * @see KeyCode
     */
    public static boolean getKeyDown(KeyCode keyCode) {
        return Keyboard.getKeyboard().getInput(keyCode.value()).isPressed();
    }

    /**
     * Returns if the key is currently released.
     * <p>
     * <p>Checks for the stored Input object corresponding to the given key value and
     * returns if the key is currently released.</p>
     * <p>
     * <p>This method can only be requested a few times depending on the given input buffer.</p>
     * <p>
     * <p>This method ist static.</p>
     *
     * @param keyCode The key value.
     * @return Returns whether the key is released or not.
     */
    public static boolean getKeyUp(int keyCode) {
        return Keyboard.getKeyboard().getInput(keyCode).isReleased();
    }

    /**
     * Returns if the key is currently released.
     * <p>
     * <p>Checks for the stored Input object corresponding to the given key value and
     * returns if the key is currently released.</p>
     * <p>
     * <p>This method can only be requested a few times depending on the given input buffer.</p>
     * <p>
     * <p>This method ist static.</p>
     *
     * @param keyCode The key value from the KeyCode enum.
     * @return Returns whether the key is released or not.
     * @see KeyCode
     */
    public static boolean getKeyUp(KeyCode keyCode) {
        return Keyboard.getKeyboard().getInput(keyCode.value()).isReleased();
    }

    /**
     * Returns the last stored input as char.
     * <p>
     * <p>This method checks for the last stored input char and removes it. The last input can only be requested once per update.</p>
     *
     * @return Returns the input as char which was typed last.
     */
    public static char getLastInput() {
        if (Keyboard.getKeyboard().last != Character.MIN_VALUE) {
            char result = Keyboard.getKeyboard().last;

            Keyboard.getKeyboard().last = Character.MIN_VALUE;

            return result;
        }
        return Character.MIN_VALUE;
    }

    /**
     * Enables the Keyboard input.
     */
    public static void enable() {
        Keyboard.getKeyboard().isEnabled = true;
    }

    /**
     * Disables the Keyboard input.
     */
    public static void disable() {
        Keyboard.getKeyboard().isEnabled = false;
    }

    /**
     * Returns if the Keyboard is enabled.
     * <p>
     * <p>Returns whether the Keyboard is enabled or not. If it is not, then no input will be received.</p>
     *
     * @return Returns whether the Keyboard is enabled or not.
     */
    public static boolean isEnabled() {
        return Keyboard.getKeyboard().isEnabled;
    }

    /**
     * Returns if the Keyboard is disabled.
     * <p>
     * <p>Returns whether the Keyboard is disabled or not. If it is, then no input will be received.</p>
     *
     * @return Returns whether the Keyboard is disabled or not.
     */
    public static boolean isDisabled() {
        return !Keyboard.getKeyboard().isEnabled;
    }

    /**
     * Returns the Keyboard instance.
     * <p>
     * <p>There will only be one instance created.</p>
     * <p>
     * <p>This method is static.</p>
     *
     * @return Returns the active Keyboard instance.
     */
    public static Keyboard getKeyboard() {
        return Keyboard.KEYBOARD;
    }
}
