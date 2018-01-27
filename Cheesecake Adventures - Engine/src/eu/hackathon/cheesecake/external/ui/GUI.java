package eu.hackathon.cheesecake.external.ui;

import eu.hackathon.cheesecake.internal.controls.KeyCode;
import eu.hackathon.cheesecake.internal.controls.mouse.Mouse;
import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;
import eu.hackathon.cheesecake.internal.graphics.Font;
import eu.hackathon.cheesecake.internal.graphics.Image;

public class GUI {
    public static void drawText(ExtendedGraphics extendedGraphics, String text, Font font, GUIRect rect) {
        extendedGraphics.drawText(text, font, rect.x, rect.y, 0xffffffff);
    }

    public static void drawText(ExtendedGraphics extendedGraphics, String text, Font font, int color, GUIRect rect) {
        extendedGraphics.drawText(text, font, rect.x, rect.y, color);
    }

    public static void drawTexture(ExtendedGraphics extendedGraphics, Image image, GUIRect rect) {
        extendedGraphics.drawImage(image, rect.x, rect.y);
    }

    public static boolean drawButton(ExtendedGraphics extendedGraphics, Image image, GUIRect rect) {
        extendedGraphics.drawImage(image, rect.x, rect.y);
        return Mouse.isInArea(rect.x, rect.y, rect.width, rect.height) && Mouse.getKeyUp(KeyCode.MOUSE_LEFT);
    }

    public static boolean drawButton(ExtendedGraphics extendedGraphics, Image image, int x, int y, int width, int height) {
        extendedGraphics.drawImage(image, x, y);
        return Mouse.isInArea(x, y, width, height) && Mouse.getKeyUp(KeyCode.MOUSE_LEFT);
    }

    public static boolean drawButton(ExtendedGraphics extendedGraphics, String text, Font font, int color, GUIRect rect, boolean isVisible) {
        if(isVisible) {
            extendedGraphics.drawText(text, font, rect.x, rect.y, color);
        }
        return Mouse.isInArea(rect.x, rect.y, rect.width, rect.height) && Mouse.getKeyUp(KeyCode.MOUSE_LEFT);
    }
}
