/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.mathlibrary;

import de.beuth.raytracer.mathlibrary.interfaces.INormal3;

public class Normal3 implements INormal3 {

    /**
     * the x-position of the normal
     */
    public final double x;

    /**
     * the y-position of the normal
     */
    public final double y;

    /**
     * the z-position of the normal
     */
    public final double z;

    /**
     * creates a new normal
     * @param x the x-position of the normal
     * @param y the y-position of the normal
     * @param z the z-position of the normal
     */
    public Normal3(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * multiplicates a constant with a normal
     * @param n the constant which should be multiplied with the normal
     * @return the new normal
     */
    public Normal3 mul(final double n) {
        return new Normal3((this.x * n), (this.y * n), (this.z * n));
    }

    /**
     * adds two normals
     * @param n the other normal which is going to be added
     * @return the new sumed up normal
     */
    public Normal3 add(final Normal3 n) {
        final double x = this.x + n.x;
        final double y = this.y + n.y;
        final double z = this.z + n.z;
        return new Normal3(x, y, z);
    }

    /**
     * builds the dot product of a normal and a vector
     * @param v the vector which dot product is build with
     * @return the value of the dot product
     */
    public double dot(final Vector3 v) {
        return (this.x * v.x) + (this.y * v.y) + (this.z * v.z);
    }

    /**
     * Shows the normal as a string
     * @return a string which represents the normal
     */
    @Override
    public String toString() {
        return "Normal3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    /**
     * compare two normals with each other
     * @param o is an object, which should be a normal - else values are not equal
     * @return true if normals are the same
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Normal3 normal3 = (Normal3) o;

        if (Double.compare(normal3.x, x) != 0) return false;
        if (Double.compare(normal3.y, y) != 0) return false;
        if (Double.compare(normal3.z, z) != 0) return false;

        return true;
    }

    /**
     * builds a hashcode for a normal
     * @return an integer which represents the normal as hash
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
