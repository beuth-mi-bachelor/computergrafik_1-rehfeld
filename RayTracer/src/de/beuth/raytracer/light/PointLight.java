/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.light;

import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Vector3;

/**
 * class represent a point of light
 */
public class PointLight extends Light {

    /**
     * the position of the light
     */
    public final Point3 position;

    /**
     * creates an instance of a point of light
     * @param color color of the light
     * @param position position of the light
     */
    public PointLight(final Color color, final Point3 position) {
        super(color);
        this.position = position;
    }

    /**
     * this method proofs if the point is illuminated by the light
     *
     * @param point the point to proof
     * @return true or false wheter it hits or not
     */
    @Override
    public boolean illuminates(final Point3 point) {
        return true;
    }

    /**
     * calculates the vector where the light comes from
     *
     * @param point the point to check the direction of light from
     * @return vector which points to source of light
     */
    @Override
    public Vector3 directionFrom(final Point3 point) {
        return position.sub(point).normalized();
    }

    @Override
    public String toString() {
        return "PointLight{" +
                "position=" + position +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PointLight that = (PointLight) o;

        if (position != null ? !position.equals(that.position) : that.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}
