/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.texture;

import de.beuth.raytracer.color.Color;

public class SingleColorTexture implements Texture {

    /**
     * the color used for the texture
     */
    public final Color color;

    /**
     * instanciates an image texture
     * @param color the color for the texture
     */
    public SingleColorTexture(final Color color) {
        this.color = color;
    }

    /**
     * method for finding the right Color at position
     * @param u position mapped to texture
     * @param v position mapped to texture
     * @return the Color for the current u, v
     */
    @Override
    public Color getColor(final double u, final double v) {
        return this.color;
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

        SingleColorTexture that = (SingleColorTexture) o;

        if (color != null ? !color.equals(that.color) : that.color != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return color != null ? color.hashCode() : 0;
    }
}
