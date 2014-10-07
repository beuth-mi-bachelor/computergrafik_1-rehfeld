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

                final double pixel_x = sobelFunction(ma1, newMatrix);
                final double pixel_y = sobelFunction(ma2, newMatrix);

                double val = Math.sqrt((pixel_x * pixel_x)
                        + (pixel_y * pixel_y));
                /**
                 * val must be between 0 and 255 because it's the value for color
                 */
                val = Math.max(0, val);
                val = Math.min(val, 255);

                final Color color = new Color((int) val, (int) val, (int) val);

                edgeImage.setRGB(i, j, color.getRGB());
                if (val == 0.0) {
                    edgeImage.setRGB(i, j,new de.beuth.raytracer.color.Color(1, 1, 1).toRGB().getRGB());
                }
                else{
                    edgeImage.setRGB(i, j,new de.beuth.raytracer.color.Color(0, 0, 0).toRGB().getRGB());
                }


            }
        }

        return edgeImage;
    }

    private double sobelFunction(Mat3x3 matrix1, Mat3x3 matrix2) {
        return (matrix1.m11 * matrix2.m11)
                + (matrix1.m12 * matrix2.m12)
                + (matrix1.m13 * matrix2.m13)
                + (matrix1.m21 * matrix2.m21)
                + (matrix1.m22 * matrix2.m22)
                + (matrix1.m23 * matrix2.m23)
                + (matrix1.m31 * matrix2.m31)
                + (matrix1.m32 * matrix2.m32)
                + (matrix1.m33 * matrix2.m33);
    }

}
