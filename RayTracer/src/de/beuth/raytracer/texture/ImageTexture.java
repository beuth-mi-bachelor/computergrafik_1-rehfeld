/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.texture;

import de.beuth.raytracer.color.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTexture implements Texture {

    /**
     * the path relative to the project path
     */
    public static final String baseURL = "src/de/beuth/raytracer/assets/";

    /**
     * the color used for the texture
     */
    public final Color color;

    /**
     * the image used for the texture
     */
    public BufferedImage image;

    /**
     * instanciates an image texture
     * @param color the color for the texture
     * @param pathToImage the image path used for the texture
     */
    public ImageTexture(final Color color, final String pathToImage) {
        this.color = color;
        this.image = null;
        try {
            this.image = ImageIO.read(new File(baseURL + pathToImage));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * method for finding the right Color at position
     * @param u position mapped to texture
     * @param v position mapped to texture
     * @return the Color for the current u, v
     */
    @Override
    public Color getColor(final double u, final double v) {
        final double coord1 = ImageTexture.getRelativeCoord(u);
        final double coord2 = ImageTexture.getRelativeCoord(v);
        final double x = (this.image.getWidth() - 1) * coord1;
        final double y = (this.image.getHeight() - 1) - ((image.getHeight() - 1) * coord2);
        return ImageTexture.getColorOfPosition(this.image, (int) Math.round(x), (int) Math.round(y));
    }

    public static double getRelativeCoord(final double in) {
        double out = in % 1.0;
        if (out < 0.0) {
            out = out + 1.0;
        }
        return out;
    }

    public static Color getColorOfPosition(final BufferedImage image, final int x, final int y) {
        final java.awt.Color c = new java.awt.Color(image.getRGB(x, y));
        return new Color(c);
    }

    @Override
    public String toString() {
        return "SingleColorTexture{" +
                "color=" + color +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageTexture that = (ImageTexture) o;

        if (color != null ? !color.equals(that.color) : that.color != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return color != null ? color.hashCode() : 0;
    }
}
