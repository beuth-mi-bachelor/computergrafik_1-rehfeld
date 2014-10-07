/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.mathlibrary;

/**
 * describes a ray
 */
public class Ray {

    /**
     * is the origin of the ray
     */
    public final Point3 o;

    /**
     * is a non-normalized direction, which will be normalized in the constructor
     */
    public final Vector3 d;

    /**
     * represents a ray
     * @param o is the origin of the ray
     * @param d is the normalized direction of a ray
     */
    public Ray(final Point3 o, final Vector3 d) {
        this.o = o;
        this.d = d.normalized();
    }

    /**
     * with this method every single point can be reached
     * o + (d * t)
     * @param t double is the distance from origin o
     */
    public Point3 at(final double t){
        return o.add(d.mul(t));
    }

    /**
     * gives you the distance of a point on the ray
     * (p - o) / d
     * @param p Point3 is a point on the ray
     */
    public double tOf(final Point3 p){
        return p.sub(o).magnitude / this.d.magnitude;
    }

    /**
     * this method multiplies the direction of the ray with a distance and adds it to
     * the origin point of the ray.
     * @param distance to an object
     * @return a new vector
     */
    public Vector3 retrievePoint(double distance){
        Point3 p = o.add(d.mul(distance));
        return new Vector3(p.x, p.y, p.z);
    }


    @Override
    public boolean equals(Object o1) {
        if (this == o1) return true;
        if (o1 == null || getClass() != o1.getClass()) return false;

        Ray ray = (Ray) o1;

        if (d != null ? !d.equals(ray.d) : ray.d != null) return false;
        if (o != null ? !o.equals(ray.o) : ray.o != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = o != null ? o.hashCode() : 0;
        result = 31 * result + (d != null ? d.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "o=" + o +
                ", d=" + d +
                '}';
    }

    public static void main(String[] args) {

    }

}
