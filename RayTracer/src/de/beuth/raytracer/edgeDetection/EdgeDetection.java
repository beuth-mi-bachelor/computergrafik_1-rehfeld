/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.edgeDetection;


import de.beuth.raytracer.mathlibrary.Mat3x3;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * class describes the edge detection of images
 */

public class EdgeDetection {

    /**
     * the method calculates the edges of the given image
     * @param image BufferedImage the method gets and returns only with edges
     * @return an image only with edges
     */
    public BufferedImage edgeDetect(BufferedImage image) {
        if (image == null) {
            throw new IllegalArgumentException("The Image cannot be null!");
        }
        final BufferedImage edgeImage = new BufferedImage(image.getWidth(),
                image.getHeight(), image.getType());
        /**
         *  Sobel operator uses a pair of 3x3 convolution masks
         */
        final Mat3x3 ma1 = new Mat3x3(-1, -2, -1, 0, 0, 0, 1, 2, 1);
        final Mat3x3 ma2 = new Mat3x3(-1, 0, 1, -2, 0, 2, -1, 0, 1);
        for (int i = 1; i < image.getWidth() - 1; i++) {
            for (int j = 1; j < image.getHeight() - 1; j++) {
                /**
                 * creates an 3x3 matrix with the neighbours of a pixel
                 */
                final Mat3x3 newMatrix = new Mat3x3(
                        image.getRGB(i - 1, j - 1), image.getRGB(i, j - 1),
                        image.getRGB(i + 1, j - 1), image.getRGB(i - 1, j),
                        image.getRGB(i, j), image.getRGB(i + 1, j),
                        image.getRGB(i - 1, j + 1), image.getRGB(i, j + 1),
                        image.getRGB(i + 1, j + 1));

                final double pixel_x = (ma1.m11 * newMatrix.m11)
                        + (ma1.m12 * newMatrix.m12)
                        + (ma1.m13 * newMatrix.m13)
                        + (ma1.m21 * newMatrix.m21)
                        + (ma1.m22 * newMatrix.m22)
                        + (ma1.m23 * newMatrix.m23)
                        + (ma1.m31 * newMatrix.m31)
                        + (ma1.m32 * newMatrix.m32)
                        + (ma1.m33 * newMatrix.m33);

                final double pixel_y = (ma2.m11 * newMatrix.m11)
                        + (ma2.m12 * newMatrix.m12)
                        + (ma2.m13 * newMatrix.m13)
                        + (ma2.m21 * newMatrix.m21)
                        + (ma2.m22 * newMatrix.m22)
                        + (ma2.m23 * newMatrix.m23)
                        + (ma2.m31 * newMatrix.m31)
                        + (ma2.m32 * newMatrix.m32)
                        + (ma2.m33 * newMatrix.m33);

                double val = Math.sqrt((pixel_x * pixel_x)
                        + (pixel_y * pixel_y));
                /**
                 * val must be between 0 and 255 because it's the value for color
                 */
                val = Math.max(0, val);
                val = Math.min(val, 255);

                final Color color = new Color((int) val);

                edgeImage.setRGB(i, j, color.getRGB());


            }
        }

        return edgeImage;
    }



            }
