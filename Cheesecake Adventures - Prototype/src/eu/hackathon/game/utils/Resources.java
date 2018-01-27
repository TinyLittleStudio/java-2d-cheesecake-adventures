package eu.hackathon.game.utils;

import eu.hackathon.cheesecake.external.audio.AudioClip;
import eu.hackathon.cheesecake.external.audio.AudioType;
import eu.hackathon.cheesecake.internal.graphics.Font;
import eu.hackathon.cheesecake.internal.graphics.Image;
import eu.hackathon.cheesecake.internal.graphics.ImageType;

public final class Resources {
    public static final Font FONT = new Font("/utils/32-original");

    public static final Image SPLASH = new Image("/images/splash", 640, 640, ImageType.PNG);
    public static final Image LOGO = new Image("/images/logo", 640, 640, ImageType.PNG);
    public static final Image BACKGROUND = new Image("/images/background", 704, 640, ImageType.PNG);
    public static final Image GAME_WON = new Image("/images/game-won", 640, 640, ImageType.PNG);

    public static final AudioClip THEME = new AudioClip("/audio/theme", AudioType.WAV);
    public static final AudioClip CLICK_01 = new AudioClip("/audio/click_01", AudioType.WAV);
    public static final AudioClip CLICK_02 = new AudioClip("/audio/click_02", AudioType.WAV);
    public static final AudioClip COUGH = new AudioClip("/audio/cough", AudioType.WAV);

    public static final Image[] TILESET = new Image("/images/tileset-16x16", 640, 640, ImageType.PNG).getSubimages(64, 64);
    public static final Image PLAYER = new Image("/images/splash", 64, 64, ImageType.PNG);
    public static final Image INVENTORY_SLOT = TILESET[73];

    public static final Image ICON_KEY_Q = TILESET[50];
    public static final Image ICON_KEY_E = TILESET[51];
    public static final Image ICON_KEY_ESC = TILESET[52];

    public static final Image REFRESH = TILESET[83];

    public static final Image[] ACTION_BOX = new Image[]{TILESET[7], TILESET[8], TILESET[9]};

    public static void init() { /* ... */ }
}
