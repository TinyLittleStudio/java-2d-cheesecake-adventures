/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.internal.controls;

import eu.hackathon.cheesecake.internal.controls.keyboard.Keyboard;
import eu.hackathon.cheesecake.internal.controls.mouse.Mouse;

import java.awt.event.KeyEvent;

/**
 * This enum contains all valid input types.
 * <p>
 * <p>This enum contains all valid input types for mouse and keyboard only.</p>
 *
 * @author Onur Alici
 * @version 1.0.0
 */
public enum KeyCode {
    A(KeyEvent.VK_A, true, false), B(KeyEvent.VK_B, true, false), C(KeyEvent.VK_C, true, false),
    D(KeyEvent.VK_D, true, false), E(KeyEvent.VK_E, true, false), F(KeyEvent.VK_F, true, false),
    G(KeyEvent.VK_G, true, false), H(KeyEvent.VK_H, true, false), I(KeyEvent.VK_I, true, false),
    J(KeyEvent.VK_J, true, false), K(KeyEvent.VK_K, true, false), L(KeyEvent.VK_L, true, false),
    M(KeyEvent.VK_M, true, false), N(KeyEvent.VK_N, true, false), O(KeyEvent.VK_O, true, false),
    P(KeyEvent.VK_P, true, false), Q(KeyEvent.VK_Q, true, false), R(KeyEvent.VK_R, true, false),
    S(KeyEvent.VK_S, true, false), T(KeyEvent.VK_T, true, false), U(KeyEvent.VK_U, true, false),
    V(KeyEvent.VK_V, true, false), W(KeyEvent.VK_W, true, false), X(KeyEvent.VK_X, true, false),
    Y(KeyEvent.VK_Y, true, false), Z(KeyEvent.VK_Z, true, false),

    ARROW_DOWN(KeyEvent.VK_DOWN, true, false), ARROW_UP(KeyEvent.VK_UP, true, false),
    ARROW_LEFT(KeyEvent.VK_LEFT, true, false), ARROW_RIGHT(KeyEvent.VK_RIGHT, true, false),

    SHIFT(KeyEvent.VK_SHIFT, true, false), ALT_GR(KeyEvent.VK_ALT_GRAPH, true, false),
    ALT(KeyEvent.VK_ALT, true, false), CTRL(KeyEvent.VK_CONTROL, true, false),
    SPACE(KeyEvent.VK_SPACE, true, false), ESCAPE(KeyEvent.VK_ESCAPE, true, false),
    BACKSPACE(KeyEvent.VK_BACK_SPACE, true, false), ENTER(KeyEvent.VK_ENTER, true, false),

    PLUS(KeyEvent.VK_PLUS, true, false), MINUS(KeyEvent.VK_MINUS, true, false),
    AMPERSAND(KeyEvent.VK_AMPERSAND, true, false), DOT(KeyEvent.VK_PERIOD, true, false),
    COMMA(KeyEvent.VK_COMMA, true, false), SEMICOLON(KeyEvent.VK_SEMICOLON, true, false),
    COLON(KeyEvent.VK_COLON, true, false),

    NUM_0(KeyEvent.VK_NUMPAD0, true, false), NUM_1(KeyEvent.VK_NUMPAD1, true, false),
    NUM_2(KeyEvent.VK_NUMPAD2, true, false), NUM_3(KeyEvent.VK_NUMPAD3, true, false),
    NUM_4(KeyEvent.VK_NUMPAD4, true, false), NUM_5(KeyEvent.VK_NUMPAD5, true, false),
    NUM_6(KeyEvent.VK_NUMPAD6, true, false), NUM_7(KeyEvent.VK_NUMPAD7, true, false),
    NUM_8(KeyEvent.VK_NUMPAD8, true, false), NUM_9(KeyEvent.VK_NUMPAD9, true, false),

    KEY_0(KeyEvent.VK_0, true, false), KEY_1(KeyEvent.VK_1, true, false), KEY_2(KeyEvent.VK_2, true, false),
    KEY_3(KeyEvent.VK_3, true, false), KEY_4(KeyEvent.VK_4, true, false), KEY_5(KeyEvent.VK_5, true, false),
    KEY_6(KeyEvent.VK_6, true, false), KEY_7(KeyEvent.VK_7, true, false), KEY_8(KeyEvent.VK_8, true, false),
    KEY_9(KeyEvent.VK_9, true, false),

    F1(KeyEvent.VK_F1, true, false), F2(KeyEvent.VK_F2, true, false), F3(KeyEvent.VK_F3, true, false),
    F4(KeyEvent.VK_F4, true, false), F5(KeyEvent.VK_F5, true, false), F6(KeyEvent.VK_F6, true, false),
    F7(KeyEvent.VK_F7, true, false), F8(KeyEvent.VK_F8, true, false), F9(KeyEvent.VK_F9, true, false),
    F10(KeyEvent.VK_F10, true, false), F11(KeyEvent.VK_F11, true, false), F12(KeyEvent.VK_F12, true, false),

    MOUSE_LEFT(1, false, true), MOUSE_RIGHT(3, false, true),
    MOUSE_WHEEL(2, false, true);

    /**
     * The Key-Value.
     */
    private int value;

    /**
     * Whether it is for a keyboard or a mouse.
     */
    private boolean isKeyboard, isMouse;

    /**
     * The Constructor.
     * <p>
     * <p>The Constructor requires all data for each possible input.</p>
     * <p>
     * <p>The Constructor is package-private.</p>
     *
     * @param value      The value of the key.
     * @param isKeyboard If the Key-Value is for the keyboard.
     * @param isMouse    If the Key-Value is for the mouse.
     * @see Keyboard
     * @see Mouse
     */
    KeyCode(int value, boolean isKeyboard, boolean isMouse) {
        this.value = value;

        this.isKeyboard = isKeyboard;
        this.isMouse = isMouse;
    }

    /**
     * Returns the value.
     * <p>
     * <p>Returns the KeyCode value.</p>
     *
     * @return Returns the KeyCode value.
     */
    public int value() {
        return value;
    }

    /**
     * Returns if KeyCode is for the keyboard.
     * <p>
     * <p>Returns if the KeyCode is for a key on the keyboard. It the KeyCode is for a specific
     * key on the keyboard, it will return true otherwise false.</p>
     *
     * @return Returns if the KeyCode is for a key on the keyboard. It the KeyCode is for a specific
     * key on the keyboard, it will return true otherwise false.
     */
    public boolean isKeyboard() {
        return isKeyboard;
    }

    /**
     * Returns if KeyCode is for the mouse.
     * <p>
     * <p>Returns if the KeyCode is for a button on the mouse. It the KeyCode is for a specific
     * button on the mouse, it will return true otherwise false.</p>
     *
     * @return Returns if the KeyCode is for a button on the mouse. It the KeyCode is for a specific
     * button on the mouse, it will return true otherwise false.
     */
    public boolean isMouse() {
        return isMouse;
    }

    /**
     * Returns if KeyCode is for another external device.
     * <p>
     * <p>Returns if the KeyCode is not for a key on the keyboard or mouse. It the KeyCode is not for a specific
     * key on the keyboard or mouse, it will return true otherwise false.</p>
     *
     * @return Returns if the KeyCode is not for a key on the keyboard or mouse. It the KeyCode is not for a specific
     * key on the keyboard or mouse, it will return true otherwise false.
     */
    public boolean isOther() {
        return !isKeyboard && !isMouse;
    }
}
