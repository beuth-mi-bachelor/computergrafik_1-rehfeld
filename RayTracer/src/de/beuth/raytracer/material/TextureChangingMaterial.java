/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.material;

import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.geometry.Hit;
import de.beuth.raytracer.light.Light;
import de.beuth.raytracer.mathlibrary.Normal3;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Vector3;
import de.beuth.raytracer.texture.Texture;
import de.beuth.raytracer.world.World;

/**
 * describes a texture changing material
 */
public class TextureChangingMaterial extends Material {

    /**
     * the Color attribute
     */
    public final Texture texture1;
    public final Texture texture2;

    /**
     * creates an instance of a texture changing  material
     * @param texture1 the color of the material
     * @param texture2 the color of the material
     */
    public TextureChangingMaterial(final Texture texture1, final Texture texture2) {
        this.texture1 = texture1;
        this.texture2 = texture2;
    }

    /**
     * the method for calculating the color of the hit points
     * @param hit the hit with an object
     * @param world the world is needed to find the lights
     * @param tracer function for raytracing
     * @return the color for a hitpoint
     */
    public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
        Point3 pointHit = hit.r.at(hit.t);
        for (Light light : world.ambientLights) {
            if (light.illuminates(pointHit, world)) {
                return this.texture1.getColor(hit.tc.u, hit.tc.v);
            } else {
                return this.texture2.getColor(hit.tc.u, hit.tc.v);
            }
        }
        return this.texture1.getColor(hit.tc.u, hit.tc.v);
    }

    /**
     * converts any Material into a CelShadingMaterial
     *
     * @return a new CelShadingMaterial
     */
    @Override
    public CelShadingMaterial convertToCelShadingMaterial() {
        return new CelShadingMaterial(this.texture1);
    }

    /**
     * converts any Material into a SingleColorMaterial
     *
     * @return a new SingleColorMaterial
     */
    @Override
    public SingleColorMaterial convertToSingelColorMaterial() {
        return new SingleColorMaterial(this.texture1);
    }

    @Override
    public String toString() {
        return "TextureChangingMaterial{" +
                "texture1=" + texture1 +
                ", texture2=" + texture2 +
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

        TextureChangingMaterial that = (TextureChangingMaterial) o;

        if (texture1 != null ? !texture1.equals(that.texture1) : that.texture1 != null) {
            return false;
        }
        if (texture2 != null ? !texture2.equals(that.texture2) : that.texture2 != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = texture1 != null ? texture1.hashCode() : 0;
        result = 31 * result + (texture2 != null ? texture2.hashCode() : 0);
        return result;
    }
}
