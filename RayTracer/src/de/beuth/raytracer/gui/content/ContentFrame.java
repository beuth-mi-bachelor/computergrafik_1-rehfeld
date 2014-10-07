/*
* Copyright (c) 2014. by Angelina Staeck und Michael Duve
*/
package de.beuth.raytracer.gui.content;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * draws a JComponent, where the image is drawn in there
 */
public class ContentFrame extends JComponent {

    public BufferedImage image;

    /**
     * standard constructor
     */
    public ContentFrame() {

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
         * create the red diagonal line pixel by pixel
         */
        for (int w = 0; w < image.getWidth(); w++) {
            for (int h = 0; h < image.getHeight(); h++) {
                if (w == h) {
                    writableRaster.setDataElements(w, h, colorModel.getDataElements(redValue, null));
                }
            }
        }

        /**
         * draw image on graphic object and show it to the user
         */
        g.drawImage(image, 0, 0, Color.BLACK, this);
    }

}