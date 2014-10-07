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

public class OrenNayarMaterial extends Material {

    /**
     * the diffusing color
     */
    public final Texture diffuse;

    /**
     * the specular color
     */
    public final Texture specular;

    /**
     * the exponent
     */
    public final int exponent;

    /**
     * instanciates a new oren-nayar material
     * @param diffuse color of the material
     * @param exponent the exponent
     */
    public OrenNayarMaterial(final Texture diffuse, final Texture specular, final int exponent) {
        this.diffuse = diffuse;
        this.specular = specular;
        this.exponent = exponent;
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

        final Color c = this.diffuse.getColor(hit.tc.u, hit.tc.v).mul(world.ambientLight);

        final Point3 pointHit = hit.r.at(hit.t);
        final Vector3 viewVector = hit.r.d.mul(-1);

        Color returnColorMultiplier = World.BACKGROUND_COLOR;
        final double roughness = Math.pow(this.exponent, 2);
        final double a = 1.0f - 0.5f * (roughness / (roughness + 0.57f));
        final double b = 0.45f * (roughness / (roughness + 0.09f));

        for (Light light : world.ambientLights) {
            if (light.illuminates(pointHit, world)) {
                final Vector3 lightVector = light.directionFrom(pointHit).normalized();
                final double alpha = Math.max(Math.acos(viewVector.dot(hit.n)), Math.acos(lightVector.dot(hit.n)));
                final double beta = Math.min(Math.acos(viewVector.dot(hit.n)), Math.acos(viewVector.dot(hit.n)));
                returnColorMultiplier = this.diffuse.getColor(hit.tc.u, hit.tc.v).mul(hit.n.dot(lightVector)).mul(a + b * Math.max(0, hit.n.dot(lightVector)) * Math.sin(alpha) * Math.tan(beta));
            }
        }

        return c.add(returnColorMultiplier);

    }

    /**
     * converts any Material into a CelShadingMaterial
     * @return a new CelShadingMaterial
     */
    @Override
    public CelShadingMaterial convertToCelShadingMaterial() {
        return new CelShadingMaterial(this.diffuse);
    }

    /**
     * converts any Material into a SingleColorMaterial
     *
     * @return a new SingleColorMaterial
     */
    @Override
    public SingleColorMaterial convertToSingelColorMaterial() {
        return new SingleColorMaterial(this.diffuse);
    }

    @Override
    public String toString() {
        return "OrenNayarMaterial{" +
                "diffuse=" + diffuse +
                ", specular=" + specular +
                ", exponent=" + exponent +
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

        OrenNayarMaterial that = (OrenNayarMaterial) o;

        if (exponent != that.exponent) {
            return false;
        }
        if (diffuse != null ? !diffuse.equals(that.diffuse) : that.diffuse != null) {
            return false;
        }
        if (specular != null ? !specular.equals(that.specular) : that.specular != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = diffuse != null ? diffuse.hashCode() : 0;
        result = 31 * result + (specular != null ? specular.hashCode() : 0);
        result = 31 * result + exponent;
        return result;
    }
}
