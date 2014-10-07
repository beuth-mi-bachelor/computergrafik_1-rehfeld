/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.material;


import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.geometry.Hit;
import de.beuth.raytracer.texture.SingleColorTexture;
import de.beuth.raytracer.texture.Texture;
import de.beuth.raytracer.world.World;

/**
 * describes a single color material
 */
public class SingleColorMaterial extends Material {

    /**
     * the color of the material
     */
    public final Texture texture;

    /**
     * create an instance of a single color material
     * @param texture the color of the material
     */
    public SingleColorMaterial(final Texture texture) {
        this.texture = texture;
    }

    /**
     * this method returns the color for one Hit-Object
     * @param hit the hit with an object
     * @param world the world is needed to find the lights
     * @param tracer function for raytracing
     * @return color for hit
     */
    public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
        return this.texture.getColor(hit.tc.u, hit.tc.v);
    }

    /**
     * converts any Material into a CelShadingMaterial
     *
     * @return a new CelShadingMaterial
     */
    @Override
    public CelShadingMaterial convertToCelShadingMaterial() {
        return new CelShadingMaterial(this.texture);
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
                "texture=" + texture +
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

        if (texture != null ? !texture.equals(that.texture) : that.texture != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return texture != null ? texture.hashCode() : 0;
    }
}
