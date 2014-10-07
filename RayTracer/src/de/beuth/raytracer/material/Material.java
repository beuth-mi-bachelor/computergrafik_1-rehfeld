/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.material;

import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.geometry.Hit;
import de.beuth.raytracer.world.World;

/**
 * describes a general material
 */
public abstract class Material {

    /**
     * this method returns the color for one Hit-Object
     * @param hit the hit with an object
     * @param world the world is needed to find the lights
     * @param tracer function for raytracing
     * @return color for hit
     */
    public abstract Color colorFor(final Hit hit, final World world, final Tracer tracer);

    /**
     * converts any Material into a CelShadingMaterial
     * @return a new CelShadingMaterial
     */
    public abstract CelShadingMaterial convertToCelShadingMaterial();

    /**
     * converts any Material into a SingleColorMaterial
     * @return a new SingleColorMaterial
     */
    public abstract SingleColorMaterial convertToSingelColorMaterial();

}
