/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.light;

import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Vector3;

/**
 * class represents a spotlight
 */
public class SpotLight extends Light {

    /**
     * position of light
     */
    public final Point3 position;

    /**
     * direction of light
     */
    public final Vector3 direction;

    /**
     * open lense angle of spotlight
     */
    public final double halfAngle;

    /**
     * instanciates a spotlight
     * @param color color of the light
     * @param position position of the light
     * @param direction direction of the light
     * @param halfAngle open lense angle of the light
     */
    public SpotLight(final Color color, final Point3 position, final Vector3 direction, final double halfAngle) {
        super(color);
        this.position = position;
        this.direction = direction;
        this.halfAngle = halfAngle;
    }

    /**
     * this method proofs if the point is illuminated by the light
     *
     * @param point the point to proof
     * @return true or false wheter it hits or not
     */
    @Override
    public boolean illuminates(final Point3 point) {

        if(Math.sin(point.sub(position).normalized().x(direction).magnitude) <= halfAngle){
            return true;
        }
        return false;
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
        return "SpotLight{" +
                "position=" + position +
                ", direction=" + direction +
                ", halfAngle=" + halfAngle +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SpotLight spotLight = (SpotLight) o;

        if (Double.compare(spotLight.halfAngle, halfAngle) != 0) return false;
        if (direction != null ? !direction.equals(spotLight.direction) : spotLight.direction != null) return false;
        if (position != null ? !position.equals(spotLight.position) : spotLight.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        temp = Double.doubleToLongBits(halfAngle);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
