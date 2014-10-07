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
import de.beuth.raytracer.world.World;

/**
 * describes a phong material
 */
public class PhongMaterial extends Material {

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
     * creates an instance of a phong material
     * @param diffuse the diffusing color
     * @param specular the specular color
     * @param exponent the exponent of the material
     */
    public PhongMaterial(final Color diffuse, final Color specular, final int exponent) {
        this.diffuse = diffuse;
        this.specular = specular;
        this.exponent = exponent;
    }

    /**
     * the method for calculating the color of the hit points
     * @param hit the hit with an object
     * @param world the world is needed to find the lights
     * @return the color for a hitpoint
     */
    public Color colorFor(final Hit hit, final World world) {

        Normal3 hitNormal = hit.n;
        Color c = world.ambientLight.mul(this.diffuse);
        Point3 pointHit = hit.r.at(hit.t);

        for (Light light : world.ambientLights) {
            if (light.illuminates(pointHit)) {
                Vector3 l = light.directionFrom(pointHit).normalized();
                Vector3 r = l.reflectedOn(hit.n);
                double max = Math.max(0.0, l.dot(hitNormal));
                double max2 = Math.pow(Math.max(0.0, hit.r.d.mul(-1).dot(r)), this.exponent);
                Color lightColor = light.color;
                c = c.add(this.diffuse.mul(lightColor).mul(max)).add(
                        specular.mul(lightColor).mul(max2));
            }
        }
        return c;
    }
}
