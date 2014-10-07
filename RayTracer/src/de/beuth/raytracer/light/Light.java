/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.light;

import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Vector3;

/**
 * class to represent a light
 */
public abstract class Light {

    /**
     * color of the light
     */
    public final Color color;

    /**
     * creates an instance of a light
     * @param color the color of the light
     */
    public Light(final Color color) {
        this.color = color;
    }

    /**
     * this method proofs if the point is illuminated by the light
     * @param point the point to proof
     * @return true or false wheter it hits or not
     */
    public abstract boolean illuminates(final Point3 point);

    /**
     * calculates the vector where the light comes from
     * @param point the point to check the direction of light from
     * @return vector which points to source of light
     */
    public abstract Vector3 directionFrom(final Point3 point);

    @Override
    public String toString() {
        return "Light{" +
                "color=" + color +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Light light = (Light) o;

        if (color != null ? !color.equals(light.color) : light.color != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return color != null ? color.hashCode() : 0;
    }
}
