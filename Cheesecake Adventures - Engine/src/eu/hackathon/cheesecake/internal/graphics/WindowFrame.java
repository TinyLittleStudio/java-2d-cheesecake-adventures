package eu.hackathon.cheesecake.internal.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class WindowFrame extends JFrame {
    private Dimension dimension;

    private WindowCanvas windowCanvas = null;

    private Window window;

    public WindowFrame(Window window) {
        super();

        this.window = window;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                window.getApplication().stop();
            }
        });

        setBackground(Color.BLACK);

        requestFocus();
        requestFocusInWindow();

        setFixedSize(getWindow().getWindowConfiguration().getDimension());

        setLocationRelativeTo(null);

        setResizable(false);

        setVisible(true);

        URL url = getClass().getClassLoader().getResource("icon.png");

        if (url != null) {
            Image image = new ImageIcon(url).getImage();

            if (image != null) {
                setIconImage(image);
            }
        }
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

    public void addWindowCanvas(WindowCanvas windowCanvas) {
        if (getWindowCanvas() == null) {
            add((this.windowCanvas = windowCanvas));

            pack();
        }
    }

    public WindowCanvas getWindowCanvas() {
        return windowCanvas;
    }

    public Window getWindow() {
        return window;
    }
}
