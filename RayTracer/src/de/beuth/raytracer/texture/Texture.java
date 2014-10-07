/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.texture;

import de.beuth.raytracer.color.Color;

public interface Texture {

    /**
     * method for finding the right Color at position
     * @param u position mapped to texture
     * @param v position mapped to texture
     * @return the Color for the current u, v
     */
    public Color getColor(final double u, final double v);

}
