/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.internal.graphics;

/**
 * This enum defines all available image types.
 * <p>
 * <p>This enum is required to load images of a specific type.</p>
 *
 * @author Onur Alici
 * @version 1.0.0
 * @see Image
 */
public enum ImageType {
    /* .PNG */
    PNG(".png"),

    /* .GIF */
    GIF(".gif"),

    /* .JPG */
    JPG(".jpg"),

    /* .JPEG */
    JPEG(".jpeg");

    /**
     * The image format ending.
     */
    private String format;

    /**
     * The Constructor.
     *
     * @param format The image ending (required).
     */
    ImageType(String format) {
        this.format = format;
    }

    /**
     * Returns the format (package-private).
     *
     * @Return String The required format.
     */
    String format() {
        return format;
    }
}

