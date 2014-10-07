/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.light;

import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.geometry.Hit;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Ray;
import de.beuth.raytracer.mathlibrary.Vector3;
import de.beuth.raytracer.world.World;

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
     * @param castsShadows if element drops shadow
     */
    public PointLight(final Color color, final Point3 position, final boolean castsShadows) {
        super(color, castsShadows);
        this.position = position;
    }

    /**
     * this method proofs if the point is illuminated by the light
     *
     * @param point the point to proof
     * @param world to check if light is between object and world
     * @return true or false wheter it hits or not
     */
    @Override
    public boolean illuminates(final Point3 point, final World world) {
        if (this.castsShadows) {
            // create a new ray and let it hit the world
            Ray r = new Ray(point, this.directionFrom(point));
            Hit hit = world.hit(r);

            // if there is no hit, world is illuminated, else there is a shadow
            if (hit == null){
                return true;
            }

            // if point is behind the position of the light, there is a shadow
            double distanceOfRayAndPosition = r.tOf(this.position);
            if (hit.t < distanceOfRayAndPosition) {
                return false;
            }
            // else light would illuminate it
            else {
                return true;
            }
        }
        // if shadows are false, do nothing
        else {
            return true;
        }
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
