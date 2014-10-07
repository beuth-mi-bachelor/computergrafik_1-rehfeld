/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.mathlibrary;

/**
 * This class represents a transform that is used to transform the geometries
 */
public class Transform {

    /**
     * The 4x4 matrix as transformation matrix
     */
    public final Mat4x4 m;

    /**
     * The inverse transformation matrix of this transformation
     */
    public final Mat4x4 i;

    /**
     * creates a new transformation object initializing the unit matrix as transformation matrix
     */
    public Transform() {

        this.m = new Mat4x4(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
        this.i = new Mat4x4(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
    }

    /**
     * creates a new matrix object having two 4x4 matrix as transformation matrix and inverse transformation matrix as param
     * @param m transformation matrix of this transformation
     * @param i inverse transformation matrix of this transformation
     */
    private Transform(final Mat4x4 m, final Mat4x4 i) {

        this.m = m;
        this.i = i;
    }

    /**
     * method appends a translation by the given x, y, and z values to the current transformation and returns
     * a new Transformation object.
     * @param x value for the translation
     * @param y value for the translation
     * @param z value for the translation
     * @return new Transformation object with the appended translation
     */
    public Transform translate(final double x, final double y, final double z) {

        Transform t = new Transform(
                new Mat4x4(1.0, 0.0, 0.0, x,
                           0.0, 1.0, 0.0, y,
                           0.0, 0.0, 1.0, z,
                           0.0, 0.0, 0.0, 1.0),
                new Mat4x4(1.0, 0.0, 0.0, -x,
                           0.0, 1.0, 0.0, -y,
                           0.0, 0.0, 1.0, -z,
                           0.0, 0.0, 0.0, 1.0));

        return new Transform(m.mul(t.m), t.i.mul(i));
    }

    /**
     * method appends a scale transformation by given  x, y, and z values to an existing transformation and returns a new
     * transformation object
     * @param x scale factor for the x axis
     * @param y scale factor for the y axis
     * @param z scale factor for the z axis
     * @return a new transformation object with scale transformation
     */
    public Transform scale(final double x, final double y, final double z) {
        Transform t = new Transform(
                new Mat4x4(x, 0.0, 0.0, 0.0,
                           0.0, y, 0.0, 0.0,
                           0.0, 0.0, z, 0.0,
                           0.0, 0.0, 0.0, 1.0),
                new Mat4x4(1.0/x, 0.0, 0.0, 0.0,
                           0.0, 1.0/y, 0.0, 0.0,
                           0.0, 0.0, 1.0/z, 0.0,
                           0.0, 0.0, 0.0, 1.0));

        return new Transform(m.mul(t.m), t.i.mul(i));
    }

    /**
     * method appends a rotation around the x axis to the transformation and returns a new transformation object
     * @param angle the angle of rotation
     * @return a new transformation object with a rotation around the x axis
     */
    public Transform rotateX(final double angle) {
        Transform t = new Transform(
                new Mat4x4(1.0, 0.0, 0.0, 0.0,
                           0.0, Math.cos(angle), -Math.sin(angle), 0.0,
                           0.0, Math.sin(angle), Math.cos(angle), 0.0,
                           0.0, 0.0, 0.0, 1.0),
                new Mat4x4(1.0, 0.0, 0.0, 0.0,
                           0.0, Math.cos(angle), Math.sin(angle), 0.0,
                           0.0, -Math.sin(angle), Math.cos(angle), 0.0,
                           0.0, 0.0, 0.0, 1.0));

        return new Transform(m.mul(t.m), t.i.mul(i));
    }

    /**
     * method appends a rotation around the y axis to the transformation and returns a new transformation object
     * @param angle the angle of rotation
     * @return a new transformation object with a rotation around the y axis
     */
    public Transform rotateY(final double angle) {
        Transform t = new Transform(
                new Mat4x4(Math.cos(angle), 0.0, Math.sin(angle), 0.0,
                           0.0, 1.0, 0.0, 0.0,
                           -Math.sin(angle), 0.0, Math.cos(angle), 0.0,
                           0.0, 0.0, 0.0, 1.0),
                new Mat4x4(Math.cos(angle), 0.0, -Math.sin(angle), 0.0,
                           0.0, 1.0, 0.0, 0.0,
                           Math.sin(angle), 0.0, Math.cos(angle), 0.0,
                           0.0, 0.0, 0.0, 1.0));

        return new Transform(m.mul(t.m), t.i.mul(i));
    }

    /**
     * method appends a rotation around the z axis to the transformation and returns a new transformation object
     * @param angle the angle of rotation
     * @return a new transformation object with a rotation around the z axis
     */
    public Transform rotateZ(final double angle) {
        Transform t = new Transform(
                new Mat4x4(Math.cos(angle), -Math.sin(angle), 0.0, 0.0,
                           Math.sin(angle), Math.cos(angle), 0.0, 0.0,
                           0.0, 0.0, 1.0, 0.0,
                           0.0, 0.0, 0.0, 1.0),
                new Mat4x4(Math.cos(angle), Math.sin(angle), 0.0, 0.0,
                           -Math.sin(angle), Math.cos(angle), 0.0, 0.0,
                           0.0, 0.0, 1.0, 0.0,
                           0.0, 0.0, 0.0, 1.0));

        return new Transform(m.mul(t.m), t.i.mul(i));
    }

    /**
     * method transforms a ray by multiplying the origin and the direction with the inverse transformation matrix
     * @param r the ray to transform
     * @return a transformed ray
     */
    public Ray mul(final Ray r) {
        return new Ray(i.mul(r.o), i.mul(r.d));
    }

    /**
     * method transforms a normal by multiplying it with the transposed inverse transformation matrix.
     * @param n the normal to transform
     * @return a transformed normal
     */
    public Normal3 mul(final Normal3 n) {
        return (i.transposed().mul(new Vector3(n.x, n.y, n.z)).normalized().asNormal());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Transform transform = (Transform) o;

        if (i != null ? !i.equals(transform.i) : transform.i != null) {
            return false;
        }
        if (m != null ? !m.equals(transform.m) : transform.m != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = m != null ? m.hashCode() : 0;
        result = 31 * result + (i != null ? i.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transform{" +
                "m=" + m +
                ", i=" + i +
                '}';
    }
}
