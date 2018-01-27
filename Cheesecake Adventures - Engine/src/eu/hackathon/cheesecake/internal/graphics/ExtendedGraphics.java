/*
 * Copyright (c) 2017, Tiny Little Studio and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.internal.graphics;

import eu.hackathon.cheesecake.Application;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * The ExtendedGraphics class is used for drawing to one single BufferedImage by using the pixel-data array inside of it
 * and modifying each pixel. (Also known as software-rendering.)
 * <p>
 * <p>This class only supports alpha blending and normal image and font drawing.</p>
 *
 * @author Onur Alici
 * @version 1.0.0
 * @see Window
 * @see Application
 */
public class ExtendedGraphics {

    /**
     * The actual BufferedImage which is modified.
     *
     * @see BufferedImage
     */
    private BufferedImage bufferedImage;

    /**
     * The pixel-data int array from the BufferedImage.
     */
    private int[] data;

    /**
     * The stored width and height of the actual image.
     */
    private int width, height;

    /**
     * The ambient (background) color of the screen.
     */
    private int ambient = 0xff000000;

    /**
     * The Constructor.
     * <p>
     * <p>The Constructor takes in the actual size it should render for.
     * This does not affect the actual screen size, but only the scale in which the image
     * is rendered. The Image can be rescaled after when rendering it to the window.</p>
     * <p>
     * <p>The Constructor is package-private.</p>
     *
     * @param width  the width.
     * @param height the height.
     */
    ExtendedGraphics(int width, int height) {
        this.width = width;
        this.height = height;

        this.bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.data = ((DataBufferInt) getBufferedImage().getRaster().getDataBuffer()).getData();
    }

    /**
     * This method sets the pixel-color on a certain coordinate.
     * <p>
     * <p>This method checks for the screen size and only draws the pixel which are in the bounds of the window/ screen.</p>
     * <p>
     * <p>This method checks for the alpha value of the color and uses alpha blending if necessary.</p>
     * <p>
     * <p>This method is private.</p>
     *
     * @param x     the X coordinate on the image.
     * @param y     the Y coordinate on the image.
     * @param value the actual color value for this certain pixel.
     */
    private void setPixel(int x, int y, int value) {
        int alpha = ((value >> 24) & 0xff);

        if (x < 0 || x > getWidth() - 1 || y < 0 || y > getHeight() - 1 || alpha == 0) {
            return;
        }
        int index = x + y * getWidth();

        if (alpha == 255) {
            data[index] = value;
        } else {
            int pixel = data[index];

            int r = ((pixel >> 16) & 0xff) - (int) ((((pixel >> 16) & 0xff) - ((value >> 16) & 0xff)) * (alpha / 255.0f));
            int g = ((pixel >> 8) & 0xff) - (int) ((((pixel >> 8) & 0xff) - ((value >> 8) & 0xff)) * (alpha / 255.0f));
            int b = (pixel & 0xff) - (int) (((pixel & 0xff) - (value & 0xff)) * (alpha / 255.0f));

            data[index] = (r << 16 | g << 8 | b);
        }
    }

    /**
     * This method draws a given text.
     * <p>
     * <p>This method requires a Font to draw a text. The text will be drawn in the given color at the given location.</p>
     * <p>
     * <p>The actual font size is taken from the Font object.</p>
     *
     * @param text  the text which will be drawn.
     * @param font  the font which contains the actual font file and style.
     * @param x     the X coordinate on which the text will be drawn.
     * @param y     the Y coordinate on which the text will be drawn.
     * @param color the text color.
     */

    /**
     * This method sets the pixel-color on a certain coordinate.
     * <p>
     * <p>This method checks for the screen size and only draws the pixel which are in the bounds of the window/ screen.</p>
     * <p>
     * <p>This method checks for the alpha value of the color and uses alpha blending if necessary.</p>
     * <p>
     * <p>This method is private.</p>
     *
     * @param text     the X coordinate on the image.
     * @param font     the Y coordinate on the image.
     * @param x the actual color value for this certain pixel.
     * @param y the actual color value for this certain pixel.
     * @param color the actual color value for this certain pixel.
     */
    public final void drawText(String text, Font font, int x, int y, int color) {
        int offset = 0;

        if (text == null) {
            return;
        }

        for (int i = 0; i < text.length(); i++) {
            int unicode = text.codePointAt(i);

            if (unicode < font.scale()) {
                for (int j = 0; j < font.getImage().getHeight(); j++) {
                    for (int k = 0; k < font.getSize()[unicode]; k++) {
                        if (font.getImage().getPixel(k + font.getOffset()[unicode], j) == 0xffffffff) {
                            setPixel(k + x + offset, j + y, color);
                        }
                    }
                }
                offset += font.getSize()[unicode];
            }
        }
    }

    /**
     * This method draws a given image.
     * <p>
     * <p>This method requires an image to draw. The given image will be drawn at the given location.</p>
     *
     * @param image the image which will be drawn.
     * @param x     the X coordinate on which the image will be drawn.
     * @param y     the Y coordinate on which the image will be drawn.
     */
    public final void drawImage(Image image, int x, int y) {
        if (image == null) {
            return;
        }

        int width = image.getWidth(), height = image.getHeight();
        int offsetx = 0, offsety = 0;

        if (x < -width || y < -height || x > getWidth() || y > getHeight()) {
            return;
        }

        if (offsetx + x > getWidth()) {
            width -= width + x - getWidth();
        }

        if (x < 0) {
            offsetx -= x;
        }

        if (height + offsety > getHeight()) {
            height -= height + y - getHeight();
        }

        if (y < 0) {
            offsety -= y;
        }

        for (int i = offsety; i < height; i++) {
            for (int j = offsetx; j < width; j++) {
                setPixel(j + x, i + y, image.getPixel(j, i));
            }
        }
    }

    public void fillRect(int color, int x, int y, int width, int height) {
        int offsetx = 0, offsety = 0;

        if (x < -width || y < -height || x > getWidth() || y > getHeight()) {
            return;
        }

        if (offsetx + x > getWidth()) {
            width -= width + x - getWidth();
        }

        if (x < 0) {
            offsetx -= x;
        }

        if (height + offsety > getHeight()) {
            height -= height + y - getHeight();
        }

        if (y < 0) {
            offsety -= y;
        }

        for (int i = offsety; i < height; i++) {
            for (int j = offsetx; j < width; j++) {
                setPixel(j + x, i + y, color);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setBackgroundColor(int ambient) {
        this.ambient = ambient;
    }

    public int getBackgroundColor() {
        return ambient;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void pack() {
        for (int i = 0; i < data.length; i++) {
            data[i] = (255 << 24 | ((data[i] >> 16) & 0xff) << 16 | ((data[i] >> 8) & 0xff) << 8 | (data[i] & 0xff));
        }
    }

    public void dispose() {
        for (int i = 0; i < data.length; i++) {
            data[i] = ambient;
        }
    }
}
