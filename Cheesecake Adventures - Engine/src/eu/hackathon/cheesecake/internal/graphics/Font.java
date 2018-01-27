/*
 * Copyright (c) 2017, Tiny Little Studio and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.internal.graphics;

public class Font {
    public static final int MAXIMUM_CHARACTERS = 256;

    private Image image;
    private int[] offset;
    private int[] size;

    public Font(String path) {
        this.image = new Image(path, ImageType.PNG);

        this.offset = new int[Font.MAXIMUM_CHARACTERS];
        this.size = new int[Font.MAXIMUM_CHARACTERS];

        int unicode = 0;

        for (int i = 0; i < image.getWidth(); i++) {
            if (image.getPixel(i) == 0xff0000ff) {
                offset[unicode] = i;
            }

            if (image.getPixel(i) == 0xffffff00) {
                size[unicode] = i - offset[unicode];
                unicode++;
            }
        }
    }

    public Image getImage() {
        return image;
    }

    public int[] getOffset() {
        return offset;
    }

    public int[] getSize() {
        return size;
    }

    public int getHeight() {
        return image.getHeight();
    }

    public int getWidth(String text) {
        int length = 0;

        if (text != null) {
            for (int i = 0; i < text.length(); i++) {
                int unicode = text.codePointAt(i);

                if (unicode < scale()) {
                    length += getSize()[unicode];
                }
            }
        }
        return length;
    }

    public int scale() {
        return Font.MAXIMUM_CHARACTERS;
    }
}
