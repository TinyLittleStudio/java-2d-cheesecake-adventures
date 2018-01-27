package eu.hackathon.cheesecake.internal.graphics;

import java.awt.*;

public enum WindowConfiguration {
    WINDOW_1280x720(1280, 720),
    WINDOW_704x640(704, 640);

    private int width, height;

    private Dimension dimension;

    WindowConfiguration(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Dimension getDimension() {
        return dimension != null ? dimension : (dimension = new Dimension(width, height));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
