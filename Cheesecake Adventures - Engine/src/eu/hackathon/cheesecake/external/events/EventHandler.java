/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.external.events;

/**
 * The EventHandler registers all EventListeners and is required to dispatch
 * event and find the corresponding methods in the listeners.
 *
 * @author Onur Alici
 * @version 1.0.0
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventHandler {
    private static final EventHandler EVENT_HANDLER = new EventHandler();

    private List<EventListener> eventListeners = new ArrayList<EventListener>();

    private EventHandler() {

    }

    public void register(EventListener eventListener) {
        List<EventListener> list = Collections.synchronizedList(eventListeners);

        synchronized (list) {
            if (!list.contains(eventListener)) {
                list.add(eventListener);
            }
        }
    }

    public void unregister(EventListener eventListener) {
        List<EventListener> list = Collections.synchronizedList(eventListeners);

        synchronized (list) {
            if (list.contains(eventListener)) {
                list.remove(eventListener);
            }
        }
    }

    public static void dispatch(Event event) {
        if (event == null) {
            return;
        }

        List<EventListener> list = Collections.synchronizedList(EventHandler.getEventHandler().eventListeners);

        synchronized (list) {
            for (EventListener eventListener : list) {
                for (Method method : eventListener.getClass().getMethods()) {
                    final Class<?>[] args = method.getParameterTypes();

                    if (args.length == 1) {
                        if (args[0].equals(event.getClass())) {
                            try {
                                method.invoke(eventListener, event);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    public static EventHandler getEventHandler() {
        return EventHandler.EVENT_HANDLER;
    }
}
