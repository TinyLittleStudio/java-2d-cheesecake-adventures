/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class consists exclusively of static methods that operate on or return values.
 *
 * @author Onur Alici
 * @version 1.0.0
 */
public final class Utils {

    /**
     * The Constructor.
     *
     * <p>The Constructor is private.</p>
     * */
    private Utils() {

    }

    /**
     * This method returns a BufferedImage.
     * <p>
     * <p>This method takes in a path in where the actual images is located
     * and loads it as a BufferedImage.</p>
     *
     * @param path The location of the image.
     * @return the loaded images as BufferedImage
     * @see BufferedImage
     */
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Utils.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method returns a rescaled BufferedImage.
     * <p>
     * <p>This method takes in an image and a new size to rescale the given image to the new sizes.</p>
     *
     * @param image (BufferedImage) The image which will be rescaled.
     * @param width The new width of the image.
     * @param height The new height of the image.
     * @return the loaded images as BufferedImage
     * @see BufferedImage
     */
    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = temp.createGraphics();
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();

        return temp;
    }
}
