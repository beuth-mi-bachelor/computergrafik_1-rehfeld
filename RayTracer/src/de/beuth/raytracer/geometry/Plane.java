/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.geometry;

import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.material.Material;
import de.beuth.raytracer.mathlibrary.Normal3;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Ray;

/**
 * the object of a plane-geometry
 */
public class Plane extends Geometry {

    /**
     * point of a plane
     */
    public final Point3 a;

    /**
     * normal of a plane
     */
    public final Normal3 n;

    /**
     * defines a new plane
     * @param a point of the plane
     * @param n normal of the plane
     * @param material material of the geometry
     */
    public Plane(final Point3 a, final Normal3 n, final Material material) {
        super(material);
        this.a = a;
        this.n = n;
    }

    /**
     * looks for a hit between a ray and a plane
     * @param r the ray tested with the hit
     * @return a hit or null
     */
    @Override
    public Hit hit(final Ray r) {
        double t2 = r.d.dot(n);
        if(t2 != 0.0) {
            double t = a.sub(r.o).dot(n) / t2;
            if (t < Geometry.EPSILON) {
                return null;
            }

            return new Hit(t, r, this, n);
        }

        return null;
    }

    /**
     * converts any Material from a geometry into a singleColorMaterial
     *
     * @return new geometry with a singleColorMaterial
     */
    @Override
    public Geometry convertToSingleColorMaterial() {
        return new Plane(this.a, this.n, this.material.convertToSingelColorMaterial());
    }

    /**
     * converts any Material from a geometry into a celShadingMaterial
     *
     * @return new geometry with a celShadingMaterial
     */
    @Override
    public Geometry convertToCelShadingMaterial() {
        return new Plane(this.a, this.n, this.material.convertToCelShadingMaterial());
    }

    @Override
    public String toString() {
        return "Plane{" +
                "a=" + a +
                ", n=" + n +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plane plane = (Plane) o;

        if (a != null ? !a.equals(plane.a) : plane.a != null) return false;
        if (n != null ? !n.equals(plane.n) : plane.n != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (n != null ? n.hashCode() : 0);
        return result;
    }
}
