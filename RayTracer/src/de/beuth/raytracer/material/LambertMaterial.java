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
import de.beuth.raytracer.texture.SingleColorTexture;
import de.beuth.raytracer.texture.Texture;
import de.beuth.raytracer.world.World;

/**
 * describes a lambert material
 */
public class LambertMaterial extends Material {

    /**
     * the Color attribute
     */
    public final Texture texture;

    /**
     * creates an instance of a lambert material
     * @param texture the color of the material
     */
    public LambertMaterial(final Texture texture) {
        this.texture = texture;
    }

    /**
     * the method for calculating the color of the hit points
     * @param hit the hit with an object
     * @param world the world is needed to find the lights
     * @param tracer function for raytracing
     * @return the color for a hitpoint
     */
    public Color colorFor(final Hit hit, final World world, final Tracer tracer) {

        Normal3 hitNormal = hit.n;
        Color c = this.texture.getColor(hit.tc.u, hit.tc.v).mul(world.ambientLight);

        Point3 pointHit = hit.r.at(hit.t);
        for (Light light : world.ambientLights) {
            Color lightColor = light.color;

            if (light.illuminates(pointHit, world)) {
                Vector3 l = light.directionFrom(pointHit).normalized();
                double max = Math.max(0.0, l.dot(hitNormal));

                c = c.add(this.texture.getColor(hit.tc.u, hit.tc.v).mul(lightColor).mul(max));

            }
        }

        return c;
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
        return new SingleColorMaterial(this.texture);
    }

    @Override
    public String toString() {
        return "LambertMaterial{" +
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

        LambertMaterial that = (LambertMaterial) o;

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
