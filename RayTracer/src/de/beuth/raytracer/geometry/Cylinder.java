/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.geometry;

import de.beuth.raytracer.material.Material;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Ray;
import de.beuth.raytracer.mathlibrary.Vector3;

public class Cylinder extends Geometry {

    /**
     * the start cap point of the cylinder
     */
    public final Vector3 C;

    /**
     * is a unit length vector that determines cylinder's axis
     */
    public final Vector3 V;

    /**
     * is the cylinder's radius
     */
    public final double r;

    /**
     * the length of the cylinder
     */
    public final double length;


    /**
     * creates a new instance of geometry
     *
     * @param material defines a base material for the geometry
     */
    public Cylinder(final Vector3 C, final Vector3 V, final double r, final double length, final Material material) {
        super(material);
        this.C = C;
        this.V = V;
        this.r = r;
        this.length = length;
    }

    /**
     looks for a hit between a ray and a cylinder
     * @param ray the ray the hit is tested with
     * @return a Hit object or null if they dont hit
     */
    @Override
    public Hit hit(final Ray ray) {

        Point3 ao = ray.o.sub(C);
        Vector3 AO = new Vector3(ao.x, ao.y, ao.z);
        Vector3 AOxAB = AO.x(V);
        Vector3 VxAB = ray.d.x(V);
        double a = VxAB.dot(VxAB);
        double b = VxAB.dot(AOxAB) * 2.0;
        double c = AOxAB.dot(AOxAB) - (r * r);


        double[] t = quadricRoots(a, b, c);

        if ( t == null ) return null;

        if ( (t[0] < 0.0) && (t.length > 1) && (t[1] < 0.0) )
            return null;
        else if ( t[0] < 0.0 ){
            if ( t.length < 2 ) return null;
            t = new double[]{t[1]};
        }
        Vector3 int1 = ray.retrievePoint(t[0]);
        if ( t.length > 1 ) {
            if ( t[1] > 0.0 ){
                Vector3 int2 = ray.retrievePoint(t[1]);
                if ( int1.distance(ray.o) > int2.distance(ray.o)) {
                    if ( pointOnCylinder(int2) ) {
                        Vector3 onAxis = findNearestPointOnLine(int2, C, V);
                        Vector3 normal = int2.sub(onAxis);
                        if ( normal.dot(ray.d) > 0.0 )
                        return new Hit( normal.dot(ray.d)*(-1.0), ray, this, normal.normalized().asNormal());//getNormal(int2));
                    }
                }
            }
        }
        if ( pointOnCylinder(int1) ) {
            Vector3 onAxis = findNearestPointOnLine(int1, C, V);
            Vector3 normal = int1.sub(onAxis);
            if ( normal.dot(ray.d) > 0.0 )
            return new Hit(normal.dot(ray.d)*(-1.0), ray, this, normal.normalized().asNormal());//getNormal(int1));
        }
        return null;

    }

    /**
     * That method gives a quadratic equation that we have to solve. To simplify, we have the following trinomial coefficients:
     * @param a double coefficient
     * @param b double coefficient
     * @param c double coefficient
     * @return an array of the needed double values
     */
    public static double[] quadricRoots(final double a, final double b, final double c) {
        // a = 0
        if (a == 0.0) return new double[]{(-c / b)};

        // discremenant
        double det = b * b - 4.0 * a * c;
        if (det < 0)
            return null;

        if (det == 0)
            return new double[]{(-b / (2.0 * a))};

        return new double[]{
                (-b + Math.sqrt(det)) / (2.0 * a),
                (-b - Math.sqrt(det)) / (2.0 * a)
        };
    }

    /**
     * proofs if the point of the ray hits the cylinder
     * @param point is the vector which hits the cylinder
     * @return true or false
     */
     private boolean pointOnCylinder(final Vector3 point) {
            Vector3 diff = point.sub(C);
            double projection = V.dot(diff);
            return ((projection >= 0) && (projection <= length));
        }

    /**
     * the method subtracts the the start point from the hit point and builds the scalar product
     * of the new vector and the direction vector. Than it multiplies this double value with the direction vector
     * and adds it to the start point.
     * @param point vector which hits the cylinder
     * @param origin start vector of the cylinder
     * @param direction direction vector of the cylinder
     * @return the closest point on the axis to the hit point.
     */
    public static Vector3 findNearestPointOnLine(final Vector3 point, final Vector3 origin, final Vector3 direction) {
        Vector3 PO = point.sub(origin);
        double size = PO.dot(direction);
        return origin.add(direction.mul(size));
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "C=" + C +
                ", V=" + V +
                ", r=" + r +
                ", length=" + length +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Cylinder cylinder = (Cylinder) o;

        if (Double.compare(cylinder.length, length) != 0) return false;
        if (Double.compare(cylinder.r, r) != 0) return false;
        if (C != null ? !C.equals(cylinder.C) : cylinder.C != null) return false;
        if (V != null ? !V.equals(cylinder.V) : cylinder.V != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (C != null ? C.hashCode() : 0);
        result = 31 * result + (V != null ? V.hashCode() : 0);
        temp = Double.doubleToLongBits(r);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(length);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
