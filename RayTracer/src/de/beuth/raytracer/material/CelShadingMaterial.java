/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.material;

import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.geometry.Hit;
import de.beuth.raytracer.light.Light;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Vector3;
import de.beuth.raytracer.world.World;

/**
 * describes a material used for cel-shading
 */
public class CelShadingMaterial extends Material {

    /**
     * the color
     */
    public Color color;

    /**
     * the factor to calculate the different grades
     */
    public static final double FACTOR_SEPARATOR = 1.0 / 6.0;

    /**
     * instanciates a cel-shading material
     *
     * @param color the material color
     */
    public CelShadingMaterial(final Color color) {
        this.color = color;
    }

    /**
     * this method returns the color for one Hit-Object
     *
     * @param hit    the hit with an object
     * @param world  the world is needed to find the lights
     * @param tracer function for raytracing
     * @return color for hit
     */
    @Override
    public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
        final Point3 hitPoint = hit.r.at(hit.t);
        Color returnColor = this.color.mul(world.ambientLight);
        for (Light currentLight : world.ambientLights) {
            if (currentLight.illuminates(hitPoint, world)) {
                final Vector3 l = currentLight.directionFrom(hitPoint).normalized();
                double c = hit.n.dot(l);
                if (c < 0) {
                    c = FACTOR_SEPARATOR;
                }
                if (c > 0 && c <= FACTOR_SEPARATOR) {
                    c = FACTOR_SEPARATOR;
                }
                if (c > FACTOR_SEPARATOR && c <= (2 * FACTOR_SEPARATOR)) {
                    c = (2 * FACTOR_SEPARATOR);
                }
                if (c > (2 * FACTOR_SEPARATOR) && c <= (3 * FACTOR_SEPARATOR)) {
                    c = 3 * FACTOR_SEPARATOR;
                }
                if (c > (3 * FACTOR_SEPARATOR) && c <= (4 * FACTOR_SEPARATOR)) {
                    c = (4 * FACTOR_SEPARATOR);
                }
                if (c > (4 * FACTOR_SEPARATOR) && c <= (5 * FACTOR_SEPARATOR)) {
                    c = (5 * FACTOR_SEPARATOR);
                }
                if (c > (5 * FACTOR_SEPARATOR) && c <= 1) {
                    c = 1;
                }
                returnColor = returnColor.add(color.mul(currentLight.color).mul(c));

            }
        }

        return returnColor;
    }

    /**
     * converts any Material into a CelShadingMaterial
     *
     * @return a new CelShadingMaterial
     */
    @Override
    public CelShadingMaterial convertToCelShadingMaterial() {
        return this;
    }

    /**
     * converts any Material into a SingleColorMaterial
     *
     * @return a new SingleColorMaterial
     */
    @Override
    public SingleColorMaterial convertToSingelColorMaterial() {
        return new SingleColorMaterial(this.color);
    }

    @Override
    public String toString() {
        return "CelShadingMaterial{" +
                "color=" + color +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CelShadingMaterial that = (CelShadingMaterial) o;

        if (color != null ? !color.equals(that.color) : that.color != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return color != null ? color.hashCode() : 0;
    }
}