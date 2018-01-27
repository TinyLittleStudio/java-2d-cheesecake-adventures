package eu.hackathon.cheesecake.internal.graphics;

import eu.hackathon.cheesecake.Application;
import eu.hackathon.cheesecake.internal.controls.InputHandler;
import eu.hackathon.cheesecake.internal.controls.keyboard.Keyboard;
import eu.hackathon.cheesecake.internal.controls.mouse.Mouse;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Window {
    private static final int MAX_BUFFER_STRATEGY = 3;

    private WindowCanvas windowCanvas;
    private WindowFrame windowFrame;

    private Application application;

    private Graphics graphics;
    private ExtendedGraphics extendedGraphics;

    private BufferedImage bufferedImage;

    private BufferedImage lastImage;
    private BufferedImage invisible = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    private boolean isHidden = false;

    private List<InputHandler> handlerList = new ArrayList<InputHandler>();

    public Window(Application application) {
        this.application = application;
    }

    public void init() {
        this.windowCanvas = new WindowCanvas(this);
        this.windowFrame = new WindowFrame(this);

        this.extendedGraphics = new ExtendedGraphics(getWidth(), getHeight());

        this.bufferedImage = getExtendedGraphics().getBufferedImage();

        getWindowCanvas().addKeyListener(Keyboard.getKeyboard());
        getWindowCanvas().addMouseListener(Mouse.getMouse());
        getWindowCanvas().addMouseMotionListener(Mouse.getMouse());
        getWindowCanvas().addMouseWheelListener(Mouse.getMouse());

        getWindowFrame().setTitle(getTitle());

        getWindowFrame().addWindowCanvas(getWindowCanvas());

        focus();
    }

    public final void tick() {

    }

    public final void draw() {
        BufferStrategy bufferStrategy = getBufferStrategy();

        Graphics graphics = bufferStrategy.getDrawGraphics();

        getExtendedGraphics().pack();

        graphics.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), null);

        getExtendedGraphics().dispose();

        graphics.dispose();

        bufferStrategy.show();
    }

    public final void dispose() {

    }

    private BufferStrategy getBufferStrategy() {
        if (getWindowCanvas().getBufferStrategy() == null) {
            getWindowCanvas().createBufferStrategy(Window.MAX_BUFFER_STRATEGY);
        }
        return getWindowCanvas().getBufferStrategy();
    }

    public void register(InputHandler inputHandler) {
        handlerList.add(inputHandler);
    }

    public void unregister(InputHandler inputHandler) {
        handlerList.remove(inputHandler);
    }

    public List<InputHandler> getInputHandler() {
        return handlerList;
    }

    public void focus() {
        getWindowCanvas().requestFocus();

        getWindowCanvas().requestFocusInWindow();
    }

    public void setCursor(BufferedImage image) {
        setCursor(image, new Point(0, 0));
    }

    public void setCursor(BufferedImage image, Point point) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Cursor cursor = toolkit.createCustomCursor(image, point, "img");

        getWindowFrame().setCursor(cursor);

        if (image != invisible) {
            this.lastImage = image;
        }
    }

    public ExtendedGraphics getExtendedGraphics() {
        return extendedGraphics;
    }

    public int getWidth() {
        return getWindowConfiguration().getWidth();
    }

    public int getHeight() {
        return getWindowConfiguration().getHeight();
    }

    public WindowFrame getWindowFrame() {
        return windowFrame;
    }

    public WindowCanvas getWindowCanvas() {
        return windowCanvas;
    }

    public String getTitle() {
        return getApplication().getApplicationConfiguration().getTitle();
    }

    public WindowConfiguration getWindowConfiguration() {
        return getApplication().getApplicationConfiguration().getWindowConfiguration();
    }

    public Application getApplication() {
        return application;
    }
}
