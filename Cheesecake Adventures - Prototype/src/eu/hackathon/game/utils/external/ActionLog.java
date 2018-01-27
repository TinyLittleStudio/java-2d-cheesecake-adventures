package eu.hackathon.game.utils.external;

import eu.hackathon.cheesecake.Structure;
import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;
import eu.hackathon.cheesecake.utils.internal.Time;
import eu.hackathon.game.GameHandler;
import eu.hackathon.game.external.entity.Entity;
import eu.hackathon.game.utils.Resources;

import java.util.LinkedList;
import java.util.Queue;

public final class ActionLog implements Structure {
    private static final Queue<ActionLogRequest> REQUESTS = new LinkedList<ActionLogRequest>();

    private int x, y;
    private boolean isVisible = true;

    private double lastTime = System.currentTimeMillis();
    private double time = 0.0d;
    private int limit = 120, offset = limit;
    private boolean fadeIn, display, fadeOut;

    public ActionLog() {

    }

    @Override
    public void tick() {
        if (isVisible) {
            if (REQUESTS.size() > 0) {
                if (display) {
                    time += System.currentTimeMillis() - lastTime;
                    lastTime = System.currentTimeMillis();

                    if (time > REQUESTS.peek().duration()) {
                        fadeOut = true;
                        time -= REQUESTS.peek().duration();
                    }
                } else {
                    if (!fadeOut) {
                        fadeIn = true;
                    }
                }
            }

            if (fadeIn) {
                offset -= Time.deltaTime() * 600;
            }

            if (fadeOut) {
                offset += Time.deltaTime() * 600;
            }

            if (offset >= limit || offset <= -limit) {
                if (fadeIn) {
                    time = 0.0d;
                    lastTime = System.currentTimeMillis();

                    display = true;
                }

                if (fadeOut) {
                    REQUESTS.remove();

                    display = false;
                }
                fadeIn = false;
                fadeOut = false;
            }

            this.x = (GameHandler.instance().getWindow().getWidth() - ((GameHandler.instance().getWindow().getWidth() / Entity.SQR_SIZE) - 3) * Entity.SQR_SIZE) / 2;
            this.y = GameHandler.instance().getWindow().getHeight();
        }
    }

    @Override
    public void draw(ExtendedGraphics extendedGraphics) {
        if (isVisible) {
            if (REQUESTS.size() > 0) {
                int size = (GameHandler.instance().getWindow().getWidth() / Entity.SQR_SIZE) - 5;

                extendedGraphics.drawImage(Resources.ACTION_BOX[0], x, offset + y);

                for (int i = 0; i < size; i++) {
                    extendedGraphics.drawImage(Resources.ACTION_BOX[1], x + (i + 1) * Entity.SQR_SIZE, offset + y);
                }
                extendedGraphics.drawImage(Resources.ACTION_BOX[2], x + (size + 1) * Entity.SQR_SIZE, offset + y);

                extendedGraphics.drawText(REQUESTS.peek().message(), Resources.FONT, x + ((size + 2) * Entity.SQR_SIZE - Resources.FONT.getWidth(REQUESTS.peek().message())) / 2, offset + y + Resources.FONT.getHeight() / 2 + 3, 0xffe2e2e2);
            }
        }
    }

    @Override
    public void dispose() { /* ... */ }

    public void cancel() {
        fadeOut = false;
        fadeIn = false;
        display = false;
        lastTime = System.currentTimeMillis();
        time = 0.0d;
        REQUESTS.clear();
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public static void request(String text, int duration) {
        REQUESTS.add(new ActionLogRequest(text, duration));
    }
}
