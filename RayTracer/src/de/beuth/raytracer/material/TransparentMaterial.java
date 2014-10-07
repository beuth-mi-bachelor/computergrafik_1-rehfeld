/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.material;

import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.geometry.Geometry;
import de.beuth.raytracer.geometry.Hit;
import de.beuth.raytracer.mathlibrary.Normal3;
import de.beuth.raytracer.mathlibrary.Ray;
import de.beuth.raytracer.mathlibrary.Vector3;
import de.beuth.raytracer.texture.SingleColorTexture;
import de.beuth.raytracer.world.World;

/**
 * describes a transparent material
 */
public class TransparentMaterial extends Material {

    /**
     * the index of refraction
     */
    public double refractionIndex;

    /**
     * instanciates a transparent material
     * @param refractionIndex the index of refraction
     */
    public TransparentMaterial(double refractionIndex) {
        this.refractionIndex = refractionIndex;
    }

    /**
     * this method returns the color for one Hit-Object
     * @param hit the hit with an object
     * @param world the world is needed to find the lights
     * @param tracer function for raytracing
     * @return color for hit
     */
    public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
        Normal3 normal = hit.n;

        // rd reflected vector
        final Vector3 rd = hit.r.d.mul(-1).reflectedOn(normal);

        // rate of refraction
        double Nqout;
        if (rd.dot(normal) < 0) {
            Nqout = refractionIndex / world.refractionIndex;
            normal = normal.mul(-1);
        } else {
            Nqout = world.refractionIndex / refractionIndex;
        }

        final double alpha = normal.dot(rd);

        if ((1 - (Math.pow(Nqout, 2.0) * (1 - Math.pow(alpha, 2.0)))) < 0) {
            return tracer.colorFor(new Ray(hit.r.at(hit.t - Geometry.EPSILON), rd));
        } else {

            final double beta = Math.sqrt(1 - (Math.pow(Nqout, 2.0) * (1 - Math.pow(alpha, 2.0))));
            final Vector3 t = hit.r.d.mul(Nqout).sub(normal.mul(beta - Nqout * alpha));

            // Schlick's approximation

            final double R0 = Math.pow((world.refractionIndex - refractionIndex)/(world.refractionIndex + refractionIndex), 2);
             // R the rate of reflection
            final double R = R0 + (1 - R0) * Math.pow(1 - alpha, 5);
            // T the rate of transmission
            final double T = 1 - R;

            // R and T are multiplied with the colors of the raytraced rays
            return tracer.colorFor(new Ray(hit.r.at(hit.t - Geometry.EPSILON), rd)).mul(R)
                    .add(tracer.colorFor(new Ray(hit.r.at(hit.t + Geometry.EPSILON), t)).mul(T));
        }
    }

    /**
     * converts any Material into a CelShadingMaterial
     *
     * @return a new CelShadingMaterial
     */
    @Override
    public CelShadingMaterial convertToCelShadingMaterial() {
        return new CelShadingMaterial(new SingleColorTexture(new Color(1, 1, 1)));
    }

    /**
     * converts any Material into a SingleColorMaterial
     *
     * @return a new SingleColorMaterial
     */
    @Override
    public SingleColorMaterial convertToSingelColorMaterial() {
        return new SingleColorMaterial(new SingleColorTexture(new Color(1, 1, 1)));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransparentMaterial that = (TransparentMaterial) o;

        if (Double.compare(that.refractionIndex, refractionIndex) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(refractionIndex);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return "TransparentMaterial{" +
                "refractionIndex=" + refractionIndex +
                '}';
    }
}
