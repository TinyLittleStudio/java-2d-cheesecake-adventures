/*
 * Copyright (c) 2017, Tiny Little Studio and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.internal.graphics;

import eu.hackathon.cheesecake.utils.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Image {
    private int width;
    private int height;

    private int[] data;

    private ImageType imageType;

    public Image(File file) {
        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);

                if (bufferedImage != null) {
                    setPixel(bufferedImage);
                }
            } catch (IOException ignored) { /* ... */ }
        }
    }

    public Image(File file, int width, int height) {
        if (file != null && width > 0 && height > 0) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);

                if (bufferedImage != null) {
                    setPixel(Utils.resize(bufferedImage, width, height));
                }
            } catch (IOException ignored) { /* ... */ }
        }
    }

    public Image(BufferedImage bufferedImage) {
        if (bufferedImage != null) {
            setPixel(bufferedImage);
        }
    }

    public Image(BufferedImage bufferedImage, int width, int height) {
        if (bufferedImage != null && width > 0 && height > 0) {
            setPixel(Utils.resize(bufferedImage, width, height));
        }
    }

    public Image(String path, ImageType imageType) {
        this.imageType = imageType;

        if (path != null && path.length() > 0) {
            setPixel(Utils.loadImage(path + getImageType().format()));
        }
    }

    public Image(String path, int width, int height, ImageType imageType) {
        this.imageType = imageType;

        if (path != null && path.length() > 0 && width > 0 && height > 0) {
            setPixel(Utils.resize(Utils.loadImage(path + getImageType().format()), width, height));
        }
    }

    private Image(int[] data, int width, int height) {
        this.data = data;

        this.width = width;
        this.height = height;
    }

    public Image getSubimage(int x, int y, int width, int height) {
        if (x + width < 0 || x + width > getWidth() || y + height < 0 || y + height > getHeight()) {
            return null;
        }
        int[] data = new int[width * height];

        for (int _x = x; _x < x + width; _x++) {
            for (int _y = y; _y < y + height; _y++) {
                data[(_x - x) + (_y - y) * width] = getPixel(_x, _y);
            }
        }
        return new Image(data, width, height);
    }

    public Image[] getSubimages(int width, int height) {
        List<Image> result = new LinkedList<Image>();

        for (int y = 0; y < Math.floor(getHeight() / height); y++) {
            for (int x = 0; x < Math.floor(getWidth() / width); x++) {
                result.add(getSubimage(x * width, y * height, width, height));
            }
        }
        return result.toArray(new Image[result.size()]);
    }

    public ImageType getImageType() {
        return imageType;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setPixel(BufferedImage bufferedImage) {
        if (bufferedImage != null) {
            this.width = bufferedImage.getWidth();
            this.height = bufferedImage.getHeight();

            this.data = bufferedImage.getRGB(0, 0, getWidth(), getHeight(), null, 0, getWidth());

            bufferedImage.flush();
        }
    }

    public int getPixel(int index) {
        return data[index];
    }

    public int getPixel(int x, int y) {
        return data[x + y * getWidth()];
    }

    public BufferedImage asBufferedImage() {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferedImage.setRGB(0, 0, getWidth(), getHeight(), data, 0, getWidth());
        return bufferedImage;
    }
}
