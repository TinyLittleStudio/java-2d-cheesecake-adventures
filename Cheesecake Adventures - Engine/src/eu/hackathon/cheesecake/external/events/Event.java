/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.external.events;

/**
 * An event is dispatched by the EventHandler when something is triggered.
 *
 * @author Onur Alici
 * @version 1.0.0
 * @see EventHandler
 */
public abstract class Event {
    /**
     * The name of the event for identification purposes.
     */
    private String name;

    /**
     * The Constructor.
     * <p>
     * <p>The Constructor can only be accessed by inherting classes.</p>
     */
    protected Event(String name) {
        this.name = name;
    }

    /**
     * Returns the name.
     * <p>
     * <p>This method returns the name of the event for identification purposes. This method is final.</p>
     *
     * @Return String The name of the event.
     */
    public final String name() {
        return name;
    }
}
