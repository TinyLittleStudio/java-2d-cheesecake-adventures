/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake;

import eu.hackathon.cheesecake.external.audio.AudioSource;
import eu.hackathon.cheesecake.external.events.Event;
import eu.hackathon.cheesecake.external.events.EventHandler;
import eu.hackathon.cheesecake.external.events.EventListener;
import eu.hackathon.cheesecake.internal.Manager;
import eu.hackathon.cheesecake.internal.controls.InputHandler;
import eu.hackathon.cheesecake.internal.controls.keyboard.Keyboard;
import eu.hackathon.cheesecake.internal.controls.mouse.Mouse;
import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;
import eu.hackathon.cheesecake.internal.graphics.Window;
import eu.hackathon.cheesecake.utils.external.Scene;

import java.util.List;

/**
 * This class is the core of the 'PIT-Hackathon 2017 - Development Kit'.
 *
 * @author Onur Alici
 * @version 1.0.0
 */
public abstract class Application implements Structure {

    /**
     * ApplicationConfiguration containing all information to configure the Application.
     *
     * @see ApplicationConfiguration
     */
    private final ApplicationConfiguration applicationConfiguration;

    /**
     * The ApplicationThread running the Main-Game-Loop.
     *
     * @see ApplicationThread
     */
    private ApplicationThread applicationThread;

    /**
     * The Window.
     * <p>
     * <p>The entire Application is drawing to this window.</p>
     *
     * @see Window
     */
    private Window window;

    /**
     * The default EventHandler.
     * <p>
     * <p>The Application creates one single EventHandler for Event-Management.</p>
     * <p>
     * <p>There can only exist one instance of the EventHandler class.</p>
     *
     * @see EventHandler
     */
    private EventHandler eventHandler = EventHandler.getEventHandler();

    /**
     * The default PanelHandler.
     * <p>
     * <p>The Application creates one single PanelHandler for Game-State-Management.</p>
     * <p>
     * <p>There can only exist one instance of the PanelHandler class.</p>
     *
     * @see PanelHandler
     */
    private PanelHandler panelHandler = new PanelHandler();

    /**
     * The default InputHandler.
     * <p>
     * <p>The Application creates one single InputHandler for default settings and registers it. Other InputHandler
     * can be added manually.</p>
     *
     * @see InputHandler
     */
    private InputHandler inputHandler = new InputHandler();

    /**
     * The default AudioSource.
     * <p>
     * <p>The Application creates one AudioSource.</p>
     *
     * @see AudioSource
     */
    private AudioSource audioSource = new AudioSource();

    /**
     * The basic Game-Manager for global access.
     *
     * @see Manager
     */
    private Manager manager;

    /**
     * The default Scene.
     * <p>
     * <p>The scene allows modification to the total scene e.g. Fading/Splash.</p>
     *
     * @see Scene
     */
    private Scene scene;

    /**
     * If the Application is started.
     */
    private boolean isInitialized = false, isInternalInitialized = false;

    /**
     * The Constructor.
     * <p>
     * <p>This Constructor generates an ApplicationConfiguration which is required.</p>
     *
     * @see ApplicationConfiguration
     */
    public Application() {
        this(new ApplicationConfiguration());
    }

    /**
     * The Constructor.
     * <p>
     * <p>The Constructor takes in an ApplicationConfiguration Object which defines the Application.</p>
     *
     * @param applicationConfiguration The configuration that defines the Application.
     * @see ApplicationConfiguration
     */
    public Application(final ApplicationConfiguration applicationConfiguration) {
        this.applicationConfiguration = applicationConfiguration;

        this.applicationThread = new ApplicationThread(new ApplicationRunnable() {
            @Override
            public void init() {
                Application.this.init();
            }

            @Override
            public void tick() {
                Application.this.tick();
            }

            @Override
            public void draw(ExtendedGraphics extendedGraphics) {
                Application.this.draw();
            }

            @Override
            public void dispose() {
                Application.this.dispose();
            }
        }, getApplicationConfiguration().getTitle());

        this.window = new Window(this);

        this.scene = new Scene(this);
    }

    /**
     * Ticks x times per second.
     * <p>
     * <p>The Application is calling the tick Method to update the
     * state x times per second.</p>
     */
    public final void tick() {
        if (!isInitialized) {
            return;
        }

        window.tick();

        if (isInternalInitialized) {
            panelHandler.tick();
        }

        if (manager != null) {
            manager.tick();
        }

        scene.tick();

        inputHandler.tick();

        if (!isInternalInitialized) {
            if (!scene.isSplashPlaying()) {
                isInternalInitialized = true;
            }
        }
    }

    /**
     * Draws x times per second.
     * <p>
     * <p>The Application is calling the draw Method to update the
     * scene x times per second.</p>
     */
    public final void draw() {
        if (!isInitialized) {
            return;
        }

        window.draw();

        ExtendedGraphics extendedGraphics = window.getExtendedGraphics();

        if (isInternalInitialized) {
            panelHandler.draw(extendedGraphics);
        }

        if (manager != null) {
            manager.draw(extendedGraphics);
        }

        scene.draw(extendedGraphics);

        inputHandler.draw(extendedGraphics);
    }

    public final void dispose() {
        window.dispose();

        panelHandler.dispose();

        audioSource.dispose();
    }

    /**
     * Starts the Application.
     * <p>
     * <p>This method starts the Application as long as it is not running.
     * If it is already running, this method will have no effect.</p>
     */
    public final void start() {
        applicationThread.open();

        window.init();

        register(inputHandler);

        init();

        scene.init();

        audioSource.init();

        isInitialized = true;
    }

    /**
     * Stops the Application.
     * <p>
     * <p>This method stops the Application as long as it is running.
     * After stopping the dispose method will be called.</p>
     */
    public final void stop() {
        applicationThread.close();

        isInitialized = false;
    }

    /**
     * Returns the ApplicationThread.
     * <p>
     * <p>This method returns the current used ApplicationThread.</p>
     * <p>
     * <p>The ApplicationThread is created inside of the Application. Each Application Object contains exactly one ApplicationThread.</p>
     *
     * @return Returns the currently used ApplicationThread.
     */
    ApplicationThread getApplicationThread() {
        return applicationThread;
    }

    /**
     * Returns the Window.
     * <p>
     * <p>Returns the Window on which the Application is drawing.</p>
     *
     * @return Returns the current used Window.
     * @see Window
     */
    public Window getWindow() {
        return window;
    }

    /**
     * Returns the Keyboard.
     * <p>
     * <p>Returns the Keyboard on which the Application is receiving keyboard input.</p>
     *
     * @return Returns the current used Keyboard.
     * @see Keyboard
     */
    public Keyboard getKeyboard() {
        return Keyboard.getKeyboard();
    }

    /**
     * Returns the Mouse.
     * <p>
     * <p>Returns the Mouse on which the Application is receiving mouse input.</p>
     *
     * @return Returns the current used Mouse.
     * @see Mouse
     */
    public Mouse getMouse() {
        return Mouse.getMouse();
    }

    /**
     * Registers a new EventListener.
     * <p>
     * <p>This method registers a new EventListener.</p>
     * <p>
     * <p>If it is already registered, nothing will change.</p>
     *
     * @param eventListener The EventListener which will be registered.
     * @see EventListener
     */
    public final void register(EventListener eventListener) {
        getEventHandler().register(eventListener);
    }

    /**
     * Unregisters a EventListener.
     * <p>
     * <p>This method unregisters a EventListener.</p>
     * <p>
     * <p>If it is not registered, nothing will change.</p>
     *
     * @param eventListener The EventListener which will be unregistered.
     * @see EventListener
     */
    public final void unregister(EventListener eventListener) {
        getEventHandler().unregister(eventListener);
    }

    /**
     * Send an Event to all EventListeners.
     * <p>
     * <p>This method uses the EventHandler to send an Object that inherits from the Event class to all registered EventListeners.</p>
     *
     * @param event The Event which will be send to all EventListeners.
     * @see Event
     * @see EventListener
     */
    public final void dispatch(Event event) {
        EventHandler.dispatch(event);
    }

    /**
     * Returns the EventHandler.
     * <p>
     * <p>This method returns the default EventHandler. There exists only one instance of this class.</p>
     *
     * @return Returns the default EventHandler.
     * @see EventHandler
     */
    public EventHandler getEventHandler() {
        return eventHandler;
    }

    /**
     * Adds a new Panel.
     * <p>
     * <p>This method adds a new Panel to the PanelHandler. If this Panel is already registered,
     * then nothing will change. If a Panel with the same key already exists, the PanelManager will
     * throw an IllegalArgumentException. </p>
     *
     * @param panel The Panel which will be added.
     * @throws IllegalArgumentException Will be thrown when the key of an Panel already exists.
     * @see Panel
     * @see PanelHandler
     */
    public final void add(Panel panel) {
        getPanelHandler().add(panel);
    }

    /**
     * Removes a Panel.
     * <p>
     * <p>This method removes a existing Panel from the PanelHandler. If this Panel is no registered,
     * then nothing will change. If a Panel with the same key already exists, the PanelManager will
     * throw an IllegalArgumentException. </p>
     *
     * @param panel The Panel which will be removed.
     * @see Panel
     * @see PanelHandler
     */
    public final void remove(Panel panel) {
        getPanelHandler().remove(panel);
    }

    /**
     * Sets the current Panel.
     * <p>
     * <p>Sets the current Panel corresponding to the given key. If that key does not exists, an
     * IllegalArgumentException will be thrown. Else the current Panel will be set to the key.</p>
     *
     * @param key The key to the Panel.
     * @throws IllegalArgumentException Will be thrown if the given key does not correspond to a Panel.
     * @see Panel
     * @see PanelHandler
     */
    public final void setPanel(String key) {
        getPanelHandler().setPanel(key);
    }

    /**
     * Returns the current Panel.
     * <p>
     * <p>Gets the current Panel from the PanelHandler.</p>
     *
     * @return Returns the current Panel.
     * @see Panel
     * @see PanelHandler
     */
    public final Panel getPanel() {
        return getPanelHandler().getPanel();
    }

    /**
     * Returns the PanelHandler.
     * <p>
     * <p>This method returns the default PanelHandler which was initialized from inside of the Application.</p>
     *
     * @return Returns the default PanelHandler.
     * @see PanelHandler
     */
    public PanelHandler getPanelHandler() {
        return panelHandler;
    }

    /**
     * Registers a new InputHandler.
     * <p>
     * <p>This method registers a new InputHandler for various purposes.</p>
     * <p>
     * <p>If it is already registered, nothing will change.</p>
     *
     * @param inputHandler The InputHandler which will be registered.
     * @see InputHandler
     */
    public final void register(InputHandler inputHandler) {
        getWindow().register(inputHandler);
    }

    /**
     * Unregisters an existing InputHandler.
     * <p>
     * <p>This method unregisters an already existing InputHandler.</p>
     * <p>
     * <p>If it is not registered, nothing will change.</p>
     *
     * @param inputHandler The InputHandler which will be unregistered.
     * @see InputHandler
     */
    public final void unregister(InputHandler inputHandler) {
        getWindow().unregister(inputHandler);
    }

    /**
     * Returns a List of InputHandler.
     * <p>
     * <p>This method returns a list of all registered InputHandler.</p>
     *
     * @return Returns a list of InputHandler.
     * @see InputHandler
     * @see List
     */
    public List<InputHandler> getInputHandlerList() {
        return getWindow().getInputHandler();
    }

    /**
     * Returns the default InputHandler.
     * <p>
     * <p>This method returns the default InputHandler which was initialized from inside of the Application.</p>
     *
     * @return Returns the default InputHandler.
     * @see InputHandler
     */
    public InputHandler getInputHandler() {
        return inputHandler;
    }

    /**
     * Returns the default AudioSource.
     * <p>
     * <p>This method returns the default AudioSource which was initialized from inside of the Application.</p>
     *
     * @return Returns the default AudioSource.
     * @see AudioSource
     */
    public AudioSource getAudioSource() {
        return audioSource;
    }

    /**
     * Returns the default Scene.
     * <p>
     * <p>This method returns the default Scene which was initialized from inside of the Application.</p>
     *
     * @return Returns the default Scene.
     * @see Scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Sets the Game-Manager.
     * <p>
     * <p>This method sets the Game-Manager.</p>
     *
     * @param manager The Game-Manager.
     * @see Manager
     */
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    /**
     * Returns the Game-Manager.
     * <p>
     * <p>This method returns the Game-Manager.</p>
     *
     * @return Returns the Game-Manager.
     * @see Manager
     */
    public Manager getManager() {
        return manager;
    }

    /**
     * Returns if the Application is initialized and started.
     *
     * @return Returns if the Application is initialized and no other function needs to be started.
     */
    public boolean isInitialized() {
        return isInitialized && isInternalInitialized;
    }

    /**
     * Returns the ApplicationConfiguration.
     * <p>
     * <p>This method returns the currently used ApplicationConfiguration.</p>
     *
     * @return Returns the ApplicationConfiguration that was used to create the Application.
     */
    public ApplicationConfiguration getApplicationConfiguration() {
        return applicationConfiguration;
    }
}
