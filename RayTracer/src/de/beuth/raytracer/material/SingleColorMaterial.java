/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.material;


import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.geometry.Hit;
import de.beuth.raytracer.world.World;

/**
 * describes a single color material
 */
public class SingleColorMaterial extends Material {

    /**
     * the color of the material
     */
    public final Color color;

    /**
     * create an instance of a single color material
     * @param color the color of the material
     */
    public SingleColorMaterial(final Color color) {
        this.color = color;
    }

    /**
     * this method returns the color for one Hit-Object
     * @param hit the hit with an object
     * @param world the world is needed to find the lights
     * @param tracer function for raytracing
     * @return color for hit
     */
    public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
        return this.color;
    }

    /**
     * converts any Material into a CelShadingMaterial
     *
     * @return a new CelShadingMaterial
     */
    @Override
    public CelShadingMaterial convertToCelShadingMaterial() {
        return new CelShadingMaterial(this.color);
    }

    /**
     * converts any Material into a SingleColorMaterial
     *
     * @return a new SingleColorMaterial
     */
    @Override
    public SingleColorMaterial convertToSingelColorMaterial() {
        return this;
    }

    @Override
    public String toString() {
        return "SingleColorMaterial{" +
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

        SingleColorMaterial that = (SingleColorMaterial) o;

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
