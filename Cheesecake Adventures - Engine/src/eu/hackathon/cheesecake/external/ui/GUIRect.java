package eu.hackathon.cheesecake.external.ui;

import java.io.Serializable;

public class GUIRect implements Cloneable, Serializable {
    int x = 0, y = 0, width = 1, height = 1;

    public GUIRect() {

    }

    public GUIRect(int x, int y) {
        setLocation(x, y);
    }

    public GUIRect(int x, int y, int width, int height) {
        setRect(x, y, width, height);
    }

    public GUIRect(GUIRect rect) {
        setRect(rect);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setScale(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setScale(GUIRect rect) {
        setScale(rect.width, rect.height);
    }

    public void setLocation(GUIRect rect) {
        setLocation(rect.x, rect.y);
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GUIRect getRect() {
        return this;
    }

    public void setRect(GUIRect rect) {
        setRect(rect.x, rect.y, rect.width, rect.height);
    }

    public void setRect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public GUIRect clone() {
        return new GUIRect(x, y, width, height);
    }

    @Override
    public String toString() {
        return "{ GUIRect: " + x + ", " + y + ", " + width + ", " + height + " }";
    }
}
