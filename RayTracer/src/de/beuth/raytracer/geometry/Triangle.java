/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.geometry;

import de.beuth.raytracer.material.Material;
import de.beuth.raytracer.mathlibrary.*;

/**
 * class represents a triangle
 */
public class Triangle extends Geometry {

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

    public final Normal3 an;
    public final Normal3 bn;
    public final Normal3 cn;

    /**
     * creates a new triangle with the 3 Points
     * @param a one edge of triangle
     * @param b one edge of triangle
     * @param c one edge of triangle
     * @param material material of the geometry
     */
    public Triangle(final Point3 a, final Point3 b, final Point3 c, Normal3 an, Normal3 bn, Normal3 cn, final Material material) {
        super(material);
        this.a = a;
        this.b = b;
        this.c = c;
        this.an = an;
        this.bn = bn;
        this.cn = cn;

    }

    /**
     * looks for a hit between a ray and a triangle
     * @param r the ray to test the hit with
     * @return a Hit object or null if they dont hit
     */
    @Override
    public Hit hit(final Ray r) {

        Mat3x3 mat = new Mat3x3( a.x - b.x, a.x - c.x, r.d.x,
                a.y - b.y, a.y - c.y, r.d.y,
                a.z - b.z, a.z - c.z, r.d.z);

        Vector3 vec = new Vector3(a.x - r.o.x, a.y - r.o.y, a.z - r.o.z);

        double beta = mat.changeCol1(vec).determinant / mat.determinant;
        double gamma = mat.changeCol2(vec).determinant / mat.determinant;
        double t = mat.changeCol3(vec).determinant / mat.determinant;

        if (beta < 0.0 || gamma < 0.0 || beta + gamma > 1.0 || t < Geometry.EPSILON) {
            return null;
        }
        else {
            double alpha = 1 - beta - gamma;
            Normal3 n = an.mul(alpha).add(bn.mul(alpha)).add(cn.mul(gamma));
            return new Hit(t, r, this, n);
        }


    }

    /**
     * converts any Material from a geometry into a singleColorMaterial
     *
     * @return new geometry with a singleColorMaterial
     */
    @Override
    public Geometry convertToSingleColorMaterial() {
        return new Triangle(this.a, this.b, this.c, this.an, this.bn, this.cn, this.material.convertToSingelColorMaterial());
    }

    /**
     * converts any Material from a geometry into a celShadingMaterial
     *
     * @return new geometry with a celShadingMaterial
     */
    @Override
    public Geometry convertToCelShadingMaterial() {
        return new Triangle(this.a, this.b, this.c, this.an, this.bn, this.cn, this.material.convertToCelShadingMaterial());
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
