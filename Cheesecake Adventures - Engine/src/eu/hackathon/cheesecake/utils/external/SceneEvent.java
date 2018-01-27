/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.utils.external;

/**
 *
 *
 * @author Onur Alici
 * @version 1.0.0
 */
public abstract class SceneEvent {
    private SceneFadeType sceneFadeType;

    public SceneEvent(SceneFadeType sceneFadeType) {
        this.sceneFadeType = sceneFadeType;
    }

    public SceneFadeType getSceneFadeType() {
        return sceneFadeType;
    }

    public abstract void execute();
}
