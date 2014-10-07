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
     * the method for calculating the color of the hit points
     * @param hit the hit with an object
     * @param world the world is needed to find the lights
     * @return the color for a hitpoint
     */
    public Color colorFor(final Hit hit, final World world) {
        return this.color;
    }
}
