/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.geometry;

import de.beuth.raytracer.material.Material;
import de.beuth.raytracer.mathlibrary.Normal3;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Ray;
import de.beuth.raytracer.mathlibrary.Vector3;

/**
 * class represents a 3d circle
 */
public class Sphere extends Geometry {

    /**
     * center of the sphere
     */
    public final Point3 c;

    /**
     * radius of the sphere
     */
    public final double r;

    /**
     * creates a new sphere
     * @param c point in center of the sphere
     * @param r radius of the sphere
     * @param material material of the sphere
     */
    public Sphere(final Point3 c, final double r, final Material material) {
        super(material);
        this.c = c;
        this.r = r;
    }

    /**
     * looks for a hit between a ray and a sphere
     * @param r the ray the hit is tested with
     * @return a Hit object or null if they dont hit
     */
    @Override
    public Hit hit(final Ray r) {
        final Point3 o = r.o;
        final Vector3 d = r.d;

        double a = d.dot(d);
        double b = d.dot((o.sub(c)).mul(2.0));
        Vector3 h = o.sub(c);
        double c2 = (h.dot(h)) - (this.r * this.r);

        double discriminant = (b * b) - (4 * a * c2);

        if (discriminant > 0.0 ) {
            double t1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double t2 = (-b - Math.sqrt(discriminant)) / (2 * a);

            double t = Math.min(t1, t2);

            if(t1 < 0 && t2 < 0.0) {
                return null;
            }
            if(t1 < 0 && t2 >= 0) {
                t = t2;
            }
            if(t < 0) {
                return null;
            }

            Normal3 n = r.at(t).sub(this.c).normalized().asNormal();

            return new Hit (t, r, this, n);
        }
        else {
            return null;

        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sphere sphere = (Sphere) o;

        if (Double.compare(sphere.r, r) != 0) return false;
        if (c != null ? !c.equals(sphere.c) : sphere.c != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = c != null ? c.hashCode() : 0;
        temp = Double.doubleToLongBits(r);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "c=" + c +
                ", r=" + r +
                '}';
    }
}
