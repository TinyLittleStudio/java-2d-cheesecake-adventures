package eu.hackathon.game.utils.external;

import eu.hackathon.cheesecake.internal.graphics.Image;
import eu.hackathon.game.utils.Resources;

public enum PlayerDirection {
    UP(0, -1, Resources.TILESET[70], Resources.TILESET[71], Resources.TILESET[70], Resources.TILESET[72]),

    DOWN(0, 1, Resources.TILESET[60], Resources.TILESET[61], Resources.TILESET[60], Resources.TILESET[62]),

    LEFT(-1, 0, Resources.TILESET[90], Resources.TILESET[91], Resources.TILESET[90], Resources.TILESET[92]),

    RIGHT(1, 0, Resources.TILESET[80], Resources.TILESET[81], Resources.TILESET[80], Resources.TILESET[82]);

    private int x, y;
    private Image[] images;

    PlayerDirection(int x, int y, Image... images) {
        this.x = x;
        this.y = y;
        this.images = images;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image[] getImages() {
        return images;
    }
}
