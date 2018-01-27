/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.internal;

import eu.hackathon.cheesecake.Application;
import eu.hackathon.cheesecake.PanelHandler;
import eu.hackathon.cheesecake.external.audio.AudioSource;
import eu.hackathon.cheesecake.external.events.EventHandler;
import eu.hackathon.cheesecake.internal.controls.InputHandler;
import eu.hackathon.cheesecake.internal.graphics.Window;
import eu.hackathon.cheesecake.utils.external.Scene;

/**
 * This abstract class acts as a form for a Game-Manager to store global values.
 *
 * @author Onur Alici
 * @version 1.0.0
 */
public abstract class Manager implements Handler {

    /**
     * The Application.
     */
    public Application application;

    /**
     * The Constructor.
     * <p>
     * <p>The Manager class requires the Application instance it is running on as a reference and can be used
     * for global access to each Handler class.</p>
     *
     * @param application The Application that is currently running.
     */
    protected Manager(Application application) {
        this.application = application;
    }

    /**
     * Returns the InputHandler.
     *
     * @return Returns the InputHandler.
     * @see InputHandler
     */
    public InputHandler getInputHandler() {
        return getApplication().getInputHandler();
    }

    /**
     * Returns the PanelHandler.
     *
     * @return Returns the PanelHandler.
     * @see PanelHandler
     */
    public PanelHandler getPanelHandler() {
        return getApplication().getPanelHandler();
    }

    /**
     * Returns the EventHandler.
     *
     * @return Returns the EventHandler.
     * @see EventHandler
     */
    public EventHandler getEventHandler() {
        return getApplication().getEventHandler();
    }

    /**
     * Returns the AudioSource.
     *
     * @return Returns the AudioSource.
     * @see AudioSource
     */
    public AudioSource getAudioSource() {
        return getApplication().getAudioSource();
    }

    /**
     * Returns the Scene.
     *
     * @return Returns the Scene.
     * @see Scene
     */
    public Scene getScreen() {
        return getApplication().getScene();
    }

    /**
     * Returns the Window.
     *
     * @return Returns the Window.
     * @see Window
     */
    public Window getWindow() {
        return getApplication().getWindow();
    }

    /**
     * Returns the Application.
     * <p>
     * <p>Returns the Application which is currently in use.</p>
     *
     * @return Returns the Application that is being used.
     * @see Application
     */
    public Application getApplication() {
        return application;
    }
}
