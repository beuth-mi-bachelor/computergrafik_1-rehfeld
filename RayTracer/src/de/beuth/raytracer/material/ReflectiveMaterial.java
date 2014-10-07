/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.material;


import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.geometry.Hit;
import de.beuth.raytracer.light.Light;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Ray;
import de.beuth.raytracer.mathlibrary.Vector3;
import de.beuth.raytracer.world.World;

/**
 * describes a reflective material
 */
public class ReflectiveMaterial extends Material {

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
     * the reflection color
     */
    public final Color reflection;

    /**
     * creates an instance of a phong material
     * @param diffuse the diffusing color
     * @param specular the specular color
     * @param exponent the exponent
     * @param reflection the reflection of the material
     */
    public ReflectiveMaterial(final Color diffuse, final Color specular, final int exponent, final Color reflection) {
        this.diffuse = diffuse;
        this.specular = specular;
        this.reflection = reflection;
        this.exponent = exponent;
    }

    /**
     * this method returns the color for one Hit-Object
     * @param hit the hit with an object
     * @param world the world is needed to find the lights
     * @param tracer function for raytracing
     * @return color for hit
     */
    public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
        Color colorOfMaterial = world.ambientLight.mul(this.diffuse);
        final Point3 hitPoint = hit.r.at(hit.t);
        final double cosinusPhi = hit.n.dot(hit.r.d.mul(-1.0)) * 2;
        final Vector3 v = hit.r.d.mul(-1).normalized();
        // check each light in scene
        for (final Light currentLight : world.ambientLights) {
            // check if light illuminates this point
            if (currentLight.illuminates(hitPoint, world)) {
                Vector3 normalVector = currentLight.directionFrom(hitPoint);
                // the opposite vector
                Vector3 reflectedVector = normalVector.reflectedOn(hit.n);
                // if light illuminates hit point, there is need to calculate new xolor
                double firstMaximum = Math.max(0.0, hit.n.dot(normalVector));
                double secondMaximum = Math.pow(Math.max(0.0, reflectedVector.dot(v)), this.exponent);
                colorOfMaterial = colorOfMaterial.add(currentLight.color.mul(this.diffuse).mul(firstMaximum)).add(currentLight.color.mul(this.specular).mul(secondMaximum));
            }
        }
        Color reflectedColor = tracer.colorFor(new Ray(hitPoint, hit.r.d.add(hit.n.mul(cosinusPhi))));
        return colorOfMaterial.add(reflection.mul((reflectedColor)));
    }

    /**
     * converts any Material int        final double cosinusPhi = hit.normal.dot(hit.ray.direction.mul(-1.0)) * 2;
     o a CelShadingMaterial
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
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReflectiveMaterial that = (ReflectiveMaterial) o;

        if (exponent != that.exponent) return false;
        if (diffuse != null ? !diffuse.equals(that.diffuse) : that.diffuse != null) return false;
        if (reflection != null ? !reflection.equals(that.reflection) : that.reflection != null) return false;
        if (specular != null ? !specular.equals(that.specular) : that.specular != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = diffuse != null ? diffuse.hashCode() : 0;
        result = 31 * result + (specular != null ? specular.hashCode() : 0);
        result = 31 * result + exponent;
        result = 31 * result + (reflection != null ? reflection.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReflectiveMaterial{" +
                "diffuse=" + diffuse +
                ", specular=" + specular +
                ", exponent=" + exponent +
                ", reflection=" + reflection +
                '}';
    }
}
