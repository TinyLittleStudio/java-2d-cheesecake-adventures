package eu.hackathon.game.utils.internal;

import eu.hackathon.cheesecake.Structure;
import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;
import eu.hackathon.cheesecake.internal.graphics.Image;

public class Animation implements Structure {
    private double lastTime = System.currentTimeMillis(), time;

    private Image[] images;
    private int index = 0;
    private int x, y;

    private float velocity = 100.0f;

    private boolean isSingleton, isFinished = false;

    public Animation(int x, int y, Image... images) {
        this(x, y, false, images);
    }

    public Animation(int x, int y, boolean isSingleton, Image... images) {
        this.images = images;
        this.x = x;
        this.y = y;
    }

    @Override
    public void tick() {
        if (isSingleton) {
            if (isFinished) {
                return;
            }
        }

        time += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (time >= velocity) {
            index++;

            if (index > images.length - 1) {
                index = 0;
            }
            time -= velocity;
        }
    }

    @Override
    public void draw(ExtendedGraphics extendedGraphics) {
        if (images != null && index > -1 && index < images.length) {
            extendedGraphics.drawImage(images[index], x, y);
        }
    }

    @Override
    public void dispose() { /* ... */ }

    public final void update() {
        time = 0.0d;
        lastTime = System.currentTimeMillis();
        index = 0;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public float getVelocity() {
        return velocity;
    }

    public Image[] getImages() {
        return images;
    }

    public void setImages(Image[] images) {
        lastTime = System.currentTimeMillis();
        time = 0.0d;

        index = 0;

        this.images = images;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setSingleton(boolean isSingleton) {
        this.isSingleton = isSingleton;
    }

    public boolean isSingleton() {
        return isSingleton;
    }
}
