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
 * class represents a directional light
 */
public class DirectionalLight extends Light {

    /**
     * the direction of the light
     */
    public final Vector3 direction;

    public DirectionalLight(final Color color, final Vector3 direction, final boolean castsShadows) {
        super(color, castsShadows);
        this.direction = direction;
    }

    /**
     * this method proofs if the point is illuminated by the light
     * @param point the point to proof
     * @param world to check if light is between object and world
     * @return true or false wheter it hits or not
     * * @param castsShadows if element drops shadow
     */
    @Override
    public boolean illuminates(final Point3 point, final World world) {
        if (this.castsShadows) {
            // create a new ray and let it hit the world
            Ray r = new Ray(point, directionFrom(point));
            Hit hit = world.hit(r);

            // if there is no hit, world is illuminated, else there is a shadow
            if (hit == null){
                return true;
            }
            else {
                return false;
            }
        }
        // if shadows are false, do nothing
        else {
            return true;
        }

    }

    /**
     * calculates the vector where the light comes from
     * @param point the point to check the direction of light from
     * @return vector which points to source of light
     */
    @Override
    public Vector3 directionFrom(final Point3 point) {
        return direction.mul(-1);
    }

    @Override
    public String toString() {
        return "DirectionalLight{" +
                "direction=" + direction +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DirectionalLight that = (DirectionalLight) o;

        if (direction != null ? !direction.equals(that.direction) : that.direction != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }
}
