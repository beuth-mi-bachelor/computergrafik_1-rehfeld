/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.geometry;

import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.geometry.interfaces.IPlane;
import de.beuth.raytracer.mathlibrary.Normal3;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Ray;
import de.beuth.raytracer.world.World;

/**
 * the object of a plane-geometry
 */
public class Plane extends Geometry implements IPlane {

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
     * @param color Color of the geometry
     */
    public Plane(final Point3 a, final Normal3 n, final Color color) {
        super(color);
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
        if( t2 != 0.0 ) {
            double t = a.sub( r.o ).dot( n ) / t2;
            if(t < 0) {
                return null;
            }
            return new Hit(t, r, this);
        }

        return null;
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
