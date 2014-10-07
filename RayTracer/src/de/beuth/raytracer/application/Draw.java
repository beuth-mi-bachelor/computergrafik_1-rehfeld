/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */
package de.beuth.raytracer.application;

import de.beuth.raytracer.camera.Camera;
import de.beuth.raytracer.geometry.Hit;
import de.beuth.raytracer.mathlibrary.Ray;
import de.beuth.raytracer.world.World;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * draws a JComponent, where the image is drawn in there
 */
public class Draw extends JComponent {

    public BufferedImage image;

    private final World world;
    private final Camera camera;

    /**
     * standard constructor
     */
    public Draw(final World world, final Camera camera) {
        this.world = world;
        this.camera = camera;
    }

    /**
     * paint method creates the image with the red line
     * @param g hte graphics object drawn on
     */
    @Override
    public void paint(Graphics g) {
        /**
         * Set the image width and height to frame size
         */
        this.image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        WritableRaster writableRaster = image.getRaster();
        ColorModel colorModel = image.getColorModel();

        /**
         * Set the two colors needed
         */
        final int blackValue = Color.black.getRGB();
        final int redValue = Color.red.getRGB();
        /**
         * draw whole image black
         */
        writableRaster.setDataElements(0, 0, 1, 1, colorModel.getDataElements(blackValue, null));


        /**
         * save width and height
         */
        final int w = image.getWidth();
        final int h = image.getHeight();
        Ray ray;
        de.beuth.raytracer.color.Color color;

        /**
         * go through image pixel by pixel
         */
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {

                // get the ray for the current position
                ray = camera.rayFor(w, h, x, y);

                // look if there is a hit
                Hit hit = world.hit(ray);

                /**
                 * if hit is found, get color of geometry,
                 * else get default black background color
                 */
                if (hit != null) {
                    color = hit.geo.color;
                } else {
                    color = World.BACKGROUND_COLOR;
                }

                writableRaster.setDataElements(x, h - y - 1, colorModel.getDataElements(color.toRGB().getRGB(), null));

            }
        }

        /**
         * draw image on graphic object and show it to the user
         */
        g.drawImage(image, 0, 0, Color.BLACK, this);
    }

}