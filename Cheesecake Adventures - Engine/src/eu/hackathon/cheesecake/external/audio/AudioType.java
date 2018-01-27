/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.external.audio;

public enum AudioType {
    WAV(".wav");

    private String format;

    AudioType(String format) {
        this.format = format;
    }

    public String format() {
        return format;
    }
}
