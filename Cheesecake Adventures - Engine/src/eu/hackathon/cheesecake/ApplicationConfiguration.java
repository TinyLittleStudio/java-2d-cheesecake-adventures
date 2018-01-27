/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake;

import eu.hackathon.cheesecake.internal.graphics.WindowConfiguration;
import eu.hackathon.cheesecake.internal.graphics.Window;

/**
 * This class is an container for all settings the application requires.
 *
 * @author Onur Alici
 * @version 1.0.0
 */
public class ApplicationConfiguration {

    /* default value for the Title if none is given */
    public static final String DEFAULT_TITLE = "PIT-Hackathon 2017";

    /* default value for the WindowConfiguration if none is given */
    public static final WindowConfiguration DEFAULT_WINDOW_MODE = WindowConfiguration.WINDOW_1280x720;

    /**
     * The title of the window and thread.
     *
     * @see Window
     * @see ApplicationThread
     * @see Application
     */
    private String title;

    /**
     * The window mode of the window.
     *
     * @see Window
     * @see WindowConfiguration
     */
    private WindowConfiguration windowConfiguration;

    /**
     * The Constructor.
     */
    public ApplicationConfiguration() {
        this(ApplicationConfiguration.DEFAULT_TITLE, ApplicationConfiguration.DEFAULT_WINDOW_MODE);
    }

    /**
     * The Constructor.
     * <p>
     * <p>This constructor takes only in the window mode.</p>
     *
     * @param windowConfiguration for the resolution of the window.
     * @see Window
     * @see WindowConfiguration
     */
    public ApplicationConfiguration(WindowConfiguration windowConfiguration) {
        this(ApplicationConfiguration.DEFAULT_TITLE, windowConfiguration);
    }

    /**
     * The Constructor.
     * <p>
     * <p>This constructor takes only in the title for the window and thread.</p>
     *
     * @param title is the name of the window and thread.
     * @see Window
     * @see ApplicationThread
     */
    public ApplicationConfiguration(String title) {
        this(title, ApplicationConfiguration.DEFAULT_WINDOW_MODE);
    }

    /**
     * The Constructor.
     * <p>
     * <p>This constructor takes only in the title for the window and thread.</p>
     *
     * @param title      is the name of the window and thread.
     * @param windowConfiguration for the resolution of the window.
     * @see Window
     * @see WindowConfiguration
     * @see ApplicationThread
     */
    public ApplicationConfiguration(String title, WindowConfiguration windowConfiguration) {
        this.title = title;
        this.windowConfiguration = windowConfiguration;
    }

    /**
     * Sets the title.
     * <p>
     * <p>This method takes in a title and sets it in the configuration</p>
     * <p>
     * <p>The title is used for the window and thread.</p>
     *
     * @param title The title.
     * @see Window
     * @see ApplicationThread
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the title.
     * <p>
     * <p>This method returns the title which is set in the configuration</p>
     * <p>
     * <p>The title is used for the window and thread.</p>
     *
     * @return Returns the title.
     * @see Window
     * @see ApplicationThread
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the window mode.
     * <p>
     * <p>This method takes in a title and sets it in the configuration</p>
     * <p>
     * <p>The window mode is used for the windows resolution.</p>
     *
     * @param windowConfiguration The window mode.
     * @see Window
     * @see WindowConfiguration
     */
    public void setWindowConfiguration(WindowConfiguration windowConfiguration) {
        this.windowConfiguration = windowConfiguration;
    }

    /**
     * Returns the window mode.
     * <p>
     * <p>This method returns the window mode which is set in the configuration</p>
     * <p>
     * <p>The window mode is used for the windows resolution.</p>
     *
     * @return WindowConfiguration Returns the window mode.
     * @see Window
     * @see WindowConfiguration
     */
    public WindowConfiguration getWindowConfiguration() {
        return windowConfiguration;
    }
}
