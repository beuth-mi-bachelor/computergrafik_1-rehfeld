/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.mathlibrary;

import de.beuth.raytracer.mathlibrary.interfaces.IPoint3;

public class Point3 implements IPoint3 {

    /**
     * the x-position of the point
     */
    public final double x;
    /**
     * the y-position of the point
     */
    public final double y;
    /**
     * the z-position of the point
     */
    public final double z;

    /**
     * creates a new point object
     * @param x first coordinate of the point.
     * @param y second coordinate of the point.
     * @param z third coordinate of the point.
     */
    public Point3(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * subs a point and a point
     * @param p is a point with three coordinates.
     * @return the method returns a new vector.
     */
    public Vector3 sub(final Point3 p) {

        final double a = x - p.x;
        final double b = y - p.y;
        final double c = z - p.z;

        return new Vector3(a, b, c);
    }

    /**
     * subs a point and a vector
     * @param v is a vector with three components.
     * @return the method returns a new translated point.
     */
    public Point3 sub(final Vector3 v) {

        final double a = x - v.x;
        final double b = y - v.y;
        final double c = z - v.z;

        return new Point3(a, b, c);
    }

    /**
     * adds a point and a vector
     * @param v is a vector with three components.
     * @return the method returns a new translated point.
     */
    public Point3 add(final Vector3 v) {

        final double a = x + v.x;
        final double b = y + v.y;
        final double c = z + v.z;

        return new Point3(a, b, c);

    }

    /**
     * Shows the point as a string
     * @return this method returns a String which shows the point.
     */
    @Override
    public String toString() {
        return "Point3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    /**
     * compare two points with each other
     * @param o is an object which can be compared to the point.
     * @return the method returns true if the two objects are the same.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point3 point3 = (Point3) o;

        if (Double.compare(point3.x, x) != 0) return false;
        if (Double.compare(point3.y, y) != 0) return false;
        if (Double.compare(point3.z, z) != 0) return false;

        return true;
    }

    /**
     * builds a hashcode for a point
     * @return an integer which represents the point as hash
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
