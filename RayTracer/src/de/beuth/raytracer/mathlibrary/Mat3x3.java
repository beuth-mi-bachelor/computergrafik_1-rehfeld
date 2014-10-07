/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.mathlibrary;

/**
 * describes a 3x3 matrix
 */
public class Mat3x3 {

    /**
     * matrix positions i,j
     */
    public double m11;
    public double m12;
    public double m13;
    public double m21;
    public double m22;
    public double m23;
    public double m31;
    public double m32;
    public double m33;

    /**
     * matrix determinant
     */
    public double determinant;

    /**
     * creates a new matrix object having the determinant as param
     * @param m11 is a component of a 3x3 matrix;
     * @param m12 is a component of a 3x3 matrix;
     * @param m13 is a component of a 3x3 matrix;
     * @param m21 is a component of a 3x3 matrix;
     * @param m22 is a component of a 3x3 matrix;
     * @param m23 is a component of a 3x3 matrix;
     * @param m31 is a component of a 3x3 matrix;
     * @param m32 is a component of a 3x3 matrix;
     * @param m33 is a component of a 3x3 matrix;
     * @param determinant is the determinant of the new matrix-object.
     */
    public Mat3x3(final double m11, final double m12, final double m13, final double m21, final double m22, final double m23, final double m31, final double m32, final double m33, final double determinant) {
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
        this.determinant = determinant;
    }

    /**
     * creates a new matrix object without having the determinant as param
     * @param m11 is a component of a 3x3 matrix;
     * @param m12 is a component of a 3x3 matrix;
     * @param m13 is a component of a 3x3 matrix;
     * @param m21 is a component of a 3x3 matrix;
     * @param m22 is a component of a 3x3 matrix;
     * @param m23 is a component of a 3x3 matrix;
     * @param m31 is a component of a 3x3 matrix;
     * @param m32 is a component of a 3x3 matrix;
     * @param m33 is a component of a 3x3 matrix;
     */
    public Mat3x3(final double m11, final double m12, final double m13, final double m21, final double m22, final double m23, final double m31, final double m32, final double m33) {
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
        this.determinant = m11*m22*m33 + m12*m23*m31 + m13*m21*m32 - m31*m22*m13 - m32*m23*m11 - m33*m21*m12;
    }

    /**
     * multiplicates a matrix with a matrix
     * @param m is a 3x3 matrix.
     * @return the method returns a 3x3 matrix.
     */
    public Mat3x3 mul(final Mat3x3 m) {

        double a11 = m11*m.m11 + m12*m.m21 + m13*m.m31;
        double a21 = m21*m.m11 + m22*m.m21 + m23*m.m31;
        double a31 = m31*m.m11 + m32*m.m21 + m33*m.m31;

        double a12 = m11*m.m12 + m12*m.m22 + m13*m.m32;
        double a22 = m21*m.m12 + m22*m.m22 + m23*m.m32;
        double a32 = m31*m.m12 + m32*m.m22 + m33*m.m32;

        double a13 = m11*m.m13 + m12*m.m23 + m13*m.m33;
        double a23 = m21*m.m13 + m22*m.m23 + m23*m.m33;
        double a33 = m31*m.m13 + m32*m.m23 + m33*m.m33;

        return new Mat3x3(a11, a12, a13, a21, a22, a23, a31, a32, a33);
    }

    /**
     * multiplicates a matrix with a vector
     * @param v is a vector with 3 components.
     * @return the method returns a vector with 3 components.
     */
    public Vector3 mul(final Vector3 v) {

        final double x = m11*v.x + m12*v.y + m13*v.z;
        final double y = m21*v.x + m22*v.y + m23*v.z;
        final double z = m31*v.x + m32*v.y + m33*v.z;

        return new Vector3(x, y, z);
    }

    /**
     * multiplicates a matrix with a point
     * @param p is a point with 3 coordinates.
     * @return the method returns a point with 3 coordinates.
     */
    public Point3 mul(final Point3 p) {

        final double x = m11*p.x + m12*p.y + m13*p.z;
        final double y = m21*p.x + m22*p.y + m23*p.z;
        final double z = m31*p.x + m32*p.y + m33*p.z;

        return new Point3(x, y, z);
    }

    /**
     * changes first column of a matrix
     * @param v is a vector with 3 components.
     * @return the method returns a 3x3 matrix.
     */
    public Mat3x3 changeCol1(final Vector3 v) {

        final double a11 = v.x;
        final double a21 = v.y;
        final double a31 = v.z;

        return new Mat3x3(a11, m12, m13, a21, m22, m23, a31, m32, m33);
    }

    /**
     * changes second column of a matrix
     * @param v is a vector with 3 components.
     * @return the method returns a 3x3 matrix.
     */
    public Mat3x3 changeCol2(final Vector3 v) {

        final double a12 = v.x;
        final double a22 = v.y;
        final double a32 = v.z;

        return new Mat3x3(m11, a12, m13, m21, a22, m23, m31, a32, m33);
    }

    /**
     * changes third column of a matrix
     * @param v is a vector with 3 components.
     * @return the method returns a 3x3 matrix.
     */
    public Mat3x3 changeCol3(final Vector3 v) {

        final double a13 = v.x;
        final double a23 = v.y;
        final double a33 = v.z;

        return new Mat3x3(m11, m12, a13, m21, m22, a23, m31, m32, a33);
    }

    /**
     * multiplicates a matrix with a normal
     * @param n is a normal-vector with three components.
     * @return the method multiplizes the normal vector with th matrix und returns a new normal vector.
     */
    public Normal3 mul(Normal3 n) {

        final double x = m11*n.x + m12*n.y + m13*n.z;
        final double y = m21*n.x + m22*n.y + m23*n.z;
        final double z = m31*n.x + m32*n.y + m33*n.z;

        return new Normal3(x, y, z);

    }

    /**
     * Shows the matrix as a string
     * @return the method returns a String which shows all components of the matrix.
     */
    @Override
    public String toString() {
        return "Mat3x3{" +
                "m11=" + m11 +
                ", m12=" + m12 +
                ", m13=" + m13 +
                ", m21=" + m21 +
                ", m22=" + m22 +
                ", m23=" + m23 +
                ", m31=" + m31 +
                ", m32=" + m32 +
                ", m33=" + m33 +
                ", determinant=" + determinant +
                '}';
    }

    /**
     * compare two matrix with each other
     * @param o is an object which can be compared to the matrix.
     * @return the method returns true if the two objects are the same.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mat3x3 mat3x3 = (Mat3x3) o;

        if (Double.compare(mat3x3.determinant, determinant) != 0) return false;
        if (Double.compare(mat3x3.m11, m11) != 0) return false;
        if (Double.compare(mat3x3.m12, m12) != 0) return false;
        if (Double.compare(mat3x3.m13, m13) != 0) return false;
        if (Double.compare(mat3x3.m21, m21) != 0) return false;
        if (Double.compare(mat3x3.m22, m22) != 0) return false;
        if (Double.compare(mat3x3.m23, m23) != 0) return false;
        if (Double.compare(mat3x3.m31, m31) != 0) return false;
        if (Double.compare(mat3x3.m32, m32) != 0) return false;
        if (Double.compare(mat3x3.m33, m33) == 0) return true;
        else return false;

    }

    /**
     * builds a hashcode for a matrix
     * @return an integer which represents the matrix as hash
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(m11);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m12);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m13);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m21);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m22);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m23);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m31);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m32);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m33);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(determinant);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
