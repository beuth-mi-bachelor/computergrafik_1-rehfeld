/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.mathlibrary;

import de.beuth.raytracer.mathlibrary.interfaces.IVector3;

public class Vector3 implements IVector3 {

    /**
     * the x-position of the vector
     */
    public final double x;

    /**
     * the y-position of the vector
     */
    public final double y;

    /**
     * the z-position of the vector
     */
    public final double z;

    /**
     * the length of the vector
     */
    public final double magnitude;

    /**
     * creates a new vector
     * @param x the x-position of the vector
     * @param y the y-position of the vector
     * @param z the z-position of the vector
     * @param magnitude the length of the vector
     */
    public Vector3(final double x, final double y, final double z, final double magnitude) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.magnitude = magnitude;
    }

    /**
     * creates a new vector
     * @param x the x-position of the vector
     * @param y the y-position of the vector
     * @param z the z-position of the vector
     */
    public Vector3(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.magnitude = this.calculateMagnitude();
    }

    /**
     * sums up two vectors
     * @param v the vector which is going to be added
     * @return a new summed up vector
     */
    public Vector3 add(final Vector3 v) {
        final double x = this.x + v.x;
        final double y = this.y + v.y;
        final double z = this.z + v.z;
        return new Vector3(x, y, z);
    }

    /**
     * calculates the magnitude of this vector
     * @return the length of the vector
     */
    public double calculateMagnitude() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    /**
     * adds a vector and a normal
     * @param n the normal which should be added
     * @return a new vector
     */
    public Vector3 add(final Normal3 n) {
        final double x = this.x + n.x;
        final double y = this.y + n.y;
        final double z = this.z + n.z;
        return new Vector3(x, y, z);
    }

    /**
     * subs a vector and a normal
     * @param n the normal which should be substracted
     * @return a new vector
     */
    public Vector3 sub(final Normal3 n) {
        final double x = this.x - n.x;
        final double y = this.y - n.y;
        final double z = this.z - n.z;
        return new Vector3(x, y, z);
    }

    public Vector3 sub(final Vector3 v) {
        final double x = this.x - v.x;
        final double y = this.y - v.y;
        final double z = this.z - v.z;
        return new Vector3(x, y, z);
    }

    /**
     * multiplicates a constant with a vector
     * @param c the constant which should be multiplied with the vector
     * @return the new vector
     */
    public Vector3 mul(final double c) {
        return new Vector3((this.x * c), (this.y * c), (this.z * c));
    }

    /**
     * builds the dot product of two vectors
     * @param v the vector which dot product is build with
     * @return the value of the dot product
     */
    public double dot(final Vector3 v) {
        return (this.x * v.x) + (this.y * v.y) + (this.z * v.z);
    }

    /**
     * builds the dot product of a vector and a normal
     * @param n the normal which dot product is build with
     * @return the value of the dot product
     */
    public double dot(final Normal3 n) {
        return (this.x * n.x) + (this.y * n.y) + (this.z * n.z);
    }

    /**
     * normalizes an vector
     * @return the normalized vector
     */
    public Vector3 normalized() {
        return this.mul(1 / this.magnitude);
    }

    /**
     * Changes a Vector3 to a Normal3
     * @return a new Normal
     */
    public Normal3 asNormal() {
        return new Normal3(this.x, this.y, this.z);
    }

    /**
     * reflects a vector3 on a normal3
     * @param n the normal on which vector is reflected
     * @return the reflected new vector
     */
    public Vector3 reflectedOn(final Normal3 n) {
        return new Vector3( 2 * this.dot(n) * n.x - this.x , 2 * this.dot(n) * n.y - this.y , 2 * this.dot(n) * n.z - this.z );
    }

    /**
     * builds the cross product of two vectors
     * @param v the vector which cross product is build with
     * @return the new vector
     */
    public Vector3 x(final Vector3 v) {
        final double x = (this.y * v.z) - (this.z * v.y);
        final double y = (this.z * v.x) - (this.x * v.z);
        final double z = (this.x * v.y) - (this.y * v.x);
        return new Vector3(x, y, z);
    }

    /**
     * this method substracts a normal with a vector and calculates the magnitude
     * of the new vector.
     * @param v is a normal
     * @return a new vector which is a distance
     */
    public double distance(Point3 v)
    {
        Normal3 n = new Normal3(v.x, v.y, v.z);
        return sub(n).calculateMagnitude();
    }

    /**
     * Shows the vector as a string
     * @return a string which represents the vector
     */
    @Override
    public String toString() {
        return "Vector3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", magnitude=" + magnitude +
                '}';
    }

    /**
     * compare two vectors with each other
     * @param o is an object, which should be a vector - else values are not equal
     * @return true if vectors are the same
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector3 vector3 = (Vector3) o;

        if (Double.compare(vector3.magnitude, magnitude) != 0) return false;
        if (Double.compare(vector3.x, x) != 0) return false;
        if (Double.compare(vector3.y, y) != 0) return false;
        if (Double.compare(vector3.z, z) != 0) return false;

        return true;
    }

    /**
     * builds a hashcode for a vector
     * @return an integer which represents the vector as hash
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
        temp = Double.doubleToLongBits(magnitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
