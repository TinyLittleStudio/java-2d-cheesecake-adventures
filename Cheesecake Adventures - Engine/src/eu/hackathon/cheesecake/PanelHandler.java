/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake;

import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;

import java.util.HashMap;
import java.util.Map;

/**
 * The PanelHandler manages the Game-State classes known as Panel.
 * <p>
 * <p>The PanelHandler is a part of the base TLS-Application and allows to
 * tick and draw a certain selected panel at the time.</p>
 *
 * @author Onur Alici
 * @version 1.0.0
 * @see Panel
 */
public class PanelHandler implements Structure {

    /**
     * This HashMap contains a list of panels with corrosponding keys for
     * fast access.
     *
     * @see HashMap
     * @see Panel
     */
    private Map<String, Panel> map = new HashMap<String, Panel>();

    /**
     * The key corresponding to the current panel.
     * <p>
     * <p>This panel is the one currently in use.</p>
     */
    private String key;

    /**
     * The Constructor.
     * <p>
     * <p>This constructor is package-private.</p>
     */
    PanelHandler() {

    }

    @Override
    public void tick() {
        Panel panel = map.getOrDefault(key, null);

        if (panel != null) {
            panel.tick();
        }
    }

    @Override
    public void draw(ExtendedGraphics extendedGraphics) {
        Panel panel = map.getOrDefault(key, null);

        if (panel != null) {
            panel.draw(extendedGraphics);
        }
    }

    @Override
    public void dispose() {
        for (Panel panel : map.values()) {
            panel.dispose();
        }
        map.clear();
    }

    /**
     * Adds a panel.
     * <p>
     * <p>Adds a new panel with the key to the map.</p>
     * <p>This method is package-private.</p>
     *
     * @param panel The required panel.
     * @throws IllegalArgumentException Will be thrown if the key from the Panel already exists.
     * @see Panel
     */
    void add(Panel panel) {
        if (panel != null && panel.key() != null) {
            if (contains(panel.key())) {
                throw new IllegalArgumentException("Panel-Key already exists: " + panel.key());
            }
            map.put(panel.key(), panel);
        }
    }

    /**
     * Removes a panel.
     * <p>
     * <p>Removes an existing panel with the given key from the map.</p>
     * <p>This method is package-private.</p>
     *
     * @param key The required key.
     * @see Panel
     */
    void remove(String key) {
        if (key != null) {
            map.remove(key);
        }
    }

    /**
     * Removes a panel.
     * <p>
     * <p>Removes an existing panel from the map.</p>
     * <p>This method is package-private.</p>
     *
     * @param panel The required panel.
     * @see Panel
     */
    void remove(Panel panel) {
        if (panel != null) {
            remove(panel.key());
        }
    }

    /**
     * Returns true if map contains key, else false.
     * <p>
     * <p>This method returns true if the given key corresponds to an existing panel.</p>
     * <p>This method is package-private.</p>
     *
     * @param key The key to check.
     * @return Returns if a panel with the given key exists, else returns false.
     * @see Panel
     */
    boolean contains(String key) {
        return find(key) != null;
    }

    /**
     * Returns true if map contains key, else false.
     * <p>
     * <p>This method returns true if the given key corresponds to an existing panel.</p>
     * <p>This method is package-private.</p>
     *
     * @param key The key corresponding to the panel.
     * @return Returns a panel with the given key if exists, else returns null.
     * @see Panel
     */
    Panel find(String key) {
        return map.getOrDefault(key.toLowerCase(), null);
    }

    /**
     * Sets the current panel corresponding to the given key.
     * <p>
     * <p>This method gets the panel by the given key if it exists. If it does not exists it throws an IllegalArgumentException.
     * Otherwise the current panel is set to the panel corresponding to the key.</p>
     * <p>
     * <p>The current panel is the panel that will be updated in the Main-Game-Loop.</p>
     *
     * @param key The key corresponding to the panel. The panel has to exist.
     * @throws IllegalArgumentException Will be thrown if the key does not exist in current map.
     * @see Panel
     */
    public void setPanel(String key) {
        key = key.toLowerCase();

        Panel panel = find(key);

        if (panel == null) {
            throw new IllegalArgumentException("Panel-Key does not exist: " + key);
        }

        if (this.key != null) {
            getPanel().unload();
        }

        this.key = key;

        getPanel().load();
    }

    /**
     * Sets the current panel corresponding to the given key.
     * <p>
     * <p>This method gets the current panel, if one exists. If it does not exists it returns null.
     * Otherwise the current panel is returned.</p>
     * <p>
     * <p>The current panel is the panel that will be updated in the Main-Game-Loop.</p>
     *
     * @return The current panel that is used in the Main-Game-Loop.
     * @see Panel
     */
    public Panel getPanel() {
        return find(key);
    }
}
