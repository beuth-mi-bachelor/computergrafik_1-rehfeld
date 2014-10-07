/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.geometry;

import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.geometry.interfaces.ITriangle;
import de.beuth.raytracer.mathlibrary.*;
import de.beuth.raytracer.world.World;

/**
 * class represents a triangle
 */
public class Triangle extends Geometry implements ITriangle {

    /**
     * one edge of triangle
     */
    public final Point3 a;

    /**
     * one edge of triangle
     */
    public final Point3 b;

    /**
     * one edge of triangle
     */
    public final Point3 c;

    /**
     * creates a new triangle with the 3 Points
     * @param a one edge of triangle
     * @param b one edge of triangle
     * @param c one edge of triangle
     * @param color color of the geometry
     */
    public Triangle(final Point3 a, final Point3 b, final Point3 c, final Color color) {
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * looks for a hit between a ray and a triangle
     * @param r the ray to test the hit with
     * @return a Hit object or null if they dont hit
     */
    @Override
    public Hit hit(final Ray r) {

        Vector3 edge1 = this.b.sub(this.a);
        Vector3 edge2 = this.c.sub(this.a);

        /**
         * Find the cross product of edge2 and the ray direction
         */
        Vector3 s1 = r.d.x(edge2);

        /**
         * Find the divisor, if its zero, return false as the triangle is degenerated
         */
        double divisor = s1.dot(edge1);
        if (divisor == 0.0) {
            return null;
        }

        /**
         * A inverted divisor, as multipling is faster then division
         */
        double invDivisor = 1 / divisor;

        /**
         * Calculate the first barycentic coordinate. Barycentic coordinates are between 0.0 and 1.0
         */
        Vector3 distance = r.o.sub(this.a);
        double barycCoord_1 = distance.dot(s1) * invDivisor;
        if (barycCoord_1 < 0.0 || barycCoord_1 > 1.0) {
            return null;
        }

        /**
         * Calculate the second barycentic coordinate.
         */
        Vector3 s2 = distance.x(edge1);
        double barycCoord_2 = r.d.dot(s2) * invDivisor;
        if (barycCoord_2 < 0.0 || (barycCoord_1 + barycCoord_2) > 1.0) {
            return null;
        }

        double t = edge2.dot(s2) * invDivisor;

        return new Hit(t, r, this);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triangle triangle = (Triangle) o;

        if (a != null ? !a.equals(triangle.a) : triangle.a != null) return false;
        if (b != null ? !b.equals(triangle.b) : triangle.b != null) return false;
        if (c != null ? !c.equals(triangle.c) : triangle.c != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        result = 31 * result + (c != null ? c.hashCode() : 0);
        return result;
    }
}
