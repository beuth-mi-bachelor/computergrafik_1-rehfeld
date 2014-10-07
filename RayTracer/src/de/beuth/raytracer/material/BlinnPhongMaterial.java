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
 * describes a blinn phong material
 */
public class BlinnPhongMaterial extends Material {

    /**
     * the diffusing color
     */
    public final Color diffuse;

    /**
     * the specular color
     */
    public final Color specular;

    /**
     * the exponent
     */
    public final int exponent;

    /**
     * instantiates a blinn phong material
     * @param diffuse the diffusing color
     * @param specular the specular color
     * @param exponent the exponent
     */
    public BlinnPhongMaterial(Color diffuse, Color specular, int exponent) {
        this.diffuse = diffuse;
        this.specular = specular;
        this.exponent = exponent;
    }

    /**
     * this method returns the color for one Hit-Object
     * @param hit the hit with an object
     * @param world the world is needed to find the lights
     * @param tracer function for raytracing
     * @return color for hit
     */
    @Override
    public Color colorFor(final Hit hit, final World world, final Tracer tracer) {

        Point3 point = hit.r.at(hit.t);

        Color color = world.ambientLight.mul(this.diffuse);

        for (Light light : world.ambientLights) {

            Color lightColor = World.BACKGROUND_COLOR;

            if (light.illuminates(hit.r.at(hit.t), world)) {

                final Vector3 lightVector = light.directionFrom(point).normalized();

                final Vector3 h = lightVector.add((hit.r.d.mul(-1)).normalized()).normalized();

                final double NdotH = h.dot(hit.n);

                final double maxNL = Math.max(0.0, lightVector.dot(hit.n));
                final double maxER = Math.pow(Math.max(0.0, NdotH), this.exponent);

                lightColor = lightColor.add(light.color.mul(this.diffuse).mul(maxNL)).add(light.color.mul(this.specular).mul(maxER));

            }

            color = color.add(lightColor);
        }
        return color;

    }

    /**
     * converts any Material into a CelShadingMaterial
     *
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
        return "BlinnPhongMaterial{" +
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

        BlinnPhongMaterial that = (BlinnPhongMaterial) o;

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
