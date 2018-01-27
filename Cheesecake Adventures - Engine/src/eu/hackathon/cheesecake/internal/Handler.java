/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.internal;

import eu.hackathon.cheesecake.Structure;
import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;

/**
 * This interface acts as a form for every Handler class in the TLS-Devkit.
 *
 * @author Onur Alici
 * @version 1.0.0
 */
public interface Handler extends Structure {

    void tick();

    void draw(ExtendedGraphics extendedGraphics);
}
