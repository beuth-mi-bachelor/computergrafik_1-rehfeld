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
     * the method for calculating the color of the hit points
     * @param hit the hit with an object
     * @param world the world is needed to find the lights
     * @return the color for the material
     */
    @Override
    public Color colorFor(final Hit hit, final World world) {

        Point3 point = hit.r.at(hit.t);

        Color color = world.ambientLight.mul(this.diffuse);

        for (Light light : world.ambientLights) {

            Color lightColor = World.BACKGROUND_COLOR;

            if (light.illuminates(hit.r.at(hit.t))) {

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

}
