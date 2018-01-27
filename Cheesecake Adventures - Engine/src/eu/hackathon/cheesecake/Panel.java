/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake;

import eu.hackathon.cheesecake.internal.Manager;

/**
 * The abstract Panel class represents potential Game-State classes.
 * <p>
 * <p>The Panel class is used as a form for the Game-States which then
 * are added to the PanelHandler class. </p>
 *
 * @author Onur Alici
 * @version 1.0.0
 * @see PanelHandler
 */
public abstract class Panel implements Structure {

    /**
     * The key corresponding to the panel.
     */
    private String key;

    /**
     * The Manager for global data management.
     * <p>
     * <p>This variable is final.</p>
     *
     * @see Manager
     */
    private final Manager manager;

    /**
     * The Constructor.
     * <p>
     * <p>The panel requires a key for identification purposes.</p>
     *
     * @param key     The key is required for identification purposes of the panel.
     *                The key is NOT case-sensitive.
     * @param manager The Manager is required as a global instance for data
     *                management.
     */
    public Panel(String key, final Manager manager) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("Panel-Key can not be null");
        }
        this.key = key.toLowerCase().trim();

        this.manager = manager;

        init();
    }

    /**
     * When the Panel is enabled.
     * <p>
     * <p>This method is not required.</p>
     * <p>This method is package-private.</p>
     */
    protected void load() { /* ... */ }

    /**
     * When the Panel is disabled.
     * <p>
     * <p>This method is not required.</p>
     * <p>This method is package-private.</p>
     */
    protected void unload() { /* ... */ }

    /**
     * Returns the key of the Panel.
     * <p>
     * <p>This method always returns a string.</p>
     *
     * @return Returns the key corresponding to the Panel. (Required for identification purposes).
     */
    final String key() {
        return key;
    }

    /**
     * Returns the Manager.
     * <p>
     * <p>This method always returns the same instance of the Manager and is used as global instance
     * for data management purposes.</p>
     *
     * @return Returns the Manager.
     */
    public final Manager getManager() {
        return manager;
    }
}
