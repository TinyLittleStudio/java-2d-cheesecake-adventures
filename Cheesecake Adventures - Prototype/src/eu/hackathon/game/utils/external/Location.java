package eu.hackathon.game.utils.external;

import java.io.Serializable;

public class Location implements Cloneable, Serializable {
    private double x = 0.0d, y = 0.0d;

    public Location() {

    }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return (int) x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public int getY() {
        return (int) y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRawX() {
        return x;
    }

    public double getRawY() {
        return y;
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setLocation(Location location) {
        setLocation(location.x, location.y);
    }

    public Location getLocation() {
        return this;
    }

    @Override
    public Location clone() {
        return new Location(x, y);
    }

    @Override
    public String toString() {
        return "{ Location: " + x + ", " + y + " }";
    }
}
