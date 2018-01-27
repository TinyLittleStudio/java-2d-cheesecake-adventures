/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.internal.controls;

import eu.hackathon.cheesecake.utils.MathUtils;

/**
 * This class stores the given input data of a key.
 * <p>
 * <p>This class stores the given input data of a key by storing its value and current state.</p>
 * <p>This class also gives the key a buffer to limit the requests per tick.</p>
 *
 * @author Onur Alici
 * @version 1.0.0
 * @see InputHandler
 */
public class Input {

    /* The default buffer size. */
    public static final int DEFAULT_BUFFER = 5;

    /**
     * If the key or button is currently active.
     */
    private boolean isKey;

    /**
     * Whether the key is currently pressed or released.
     */
    private int isPressed, isReleased;

    /**
     * The buffer size for this specific key or button.
     */
    private int inputBuffer;

    /**
     * The Constructor.
     */
    public Input() {
        this(Input.DEFAULT_BUFFER);
    }

    /**
     * The Constructor.
     * <p>
     * <p>The Constructor may take a buffer size for variable request limits.</p>
     *
     * @param inputBuffer The specific buffer size for this key instance.
     */
    public Input(int inputBuffer) {
        this.inputBuffer = inputBuffer;
    }

    /**
     * Returns if the key is pressed.
     * <p>
     * <p>Returns if the key or button is currently pressed.</p>
     * <p>
     * <p>Only returns buffer size times true per tick if the key is pressed.</p>
     * <p>If this method is invoked the buffer for the pressed state will be decreased. If the buffer
     * size reached 0, this method returns false.</p>
     *
     * @return Returns if the key is pressed a limited times per tick.
     */
    public boolean isPressed() {
        return (isPressed = MathUtils.clamp(isPressed - 1, 0, getInputBuffer() + 1)) > 0;
    }

    /**
     * Sets if the key is currently pressed or not.
     * <p>
     * <p>If this method is invoked, the buffer for the pressed state will be resetted.</p>
     *
     * @param isPressed If the key is currently pressed or not.
     */
    public void setPressed(boolean isPressed) {
        this.isPressed = isPressed ? getInputBuffer() + 1 : 0;
    }

    /**
     * Returns if the key is released.
     * <p>
     * <p>Returns if the key or button is currently released.</p>
     * <p>
     * <p>Only returns buffer size times true per tick if the key is released.</p>
     * <p>If this method is invoked the buffer for the released state will be decreased. If the buffer
     * size reached 0, this method returns false.</p>
     *
     * @return Returns if the key is released a limited times per tick.
     */
    public boolean isReleased() {
        return (isReleased = MathUtils.clamp(isReleased - 1, 0, getInputBuffer() + 1)) > 0;
    }

    /**
     * Sets if the key is currently released or not.
     * <p>
     * <p>If this method is invoked, the buffer for the released state will be resetted.</p>
     *
     * @param isReleased If the key is currently released or not.
     */
    public void setReleased(boolean isReleased) {
        this.isReleased = isReleased ? getInputBuffer() + 1 : 0;
    }

    /**
     * Returns true if the key is active.
     * <p>
     * <p>This method is not limited.</p>
     *
     * @return Returns if the key is currently active in use.
     */
    public boolean isKey() {
        return isKey;
    }

    /**
     * Sets the current activity of the key.
     *
     * @param isKey If the key is currently active.
     */
    public void isKey(boolean isKey) {
        this.isKey = isKey;
    }

    /**
     * Clears all stored values.
     */
    public void clear() {
        setPressed(false);
        setReleased(false);
    }

    /**
     * Returns the buffer size.
     * <p>
     * <p>This method is package-private.</p>
     *
     * @return Returns the buffer size for this specific instance of the key.
     */
    int getInputBuffer() {
        return inputBuffer;
    }
}
