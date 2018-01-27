package eu.hackathon.cheesecake.internal.graphics;

import java.awt.*;

public class WindowCanvas extends Canvas {
    private Dimension dimension;

    private Window window;

    public WindowCanvas(Window window) {
        super();

        this.window = window;

        setBackground(Color.BLACK);

        setFixedSize(getWindow().getWindowConfiguration().getDimension());

        validate();

        setVisible(true);
    }

    public void setFixedSize(int width, int height) {
        setFixedSize(new Dimension(width, height));
    }

    public void setFixedSize(Dimension dimension) {
        this.dimension = dimension;

        setMinimumSize(dimension);
        setMaximumSize(dimension);

        setPreferredSize(dimension);

        setSize(dimension);
    }

    public Dimension getFixedSize() {
        return dimension;
    }

    public Window getWindow() {
        return window;
    }
}
