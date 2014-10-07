/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.mathlibrary;

/**
 * describes a 4x4 matrix
 */
public class Mat4x4 {

    /**
     * matrix positions i,j
     */
    public final double m11;
    public final double m12;
    public final double m13;
    public final double m14;
    public final double m21;
    public final double m22;
    public final double m23;
    public final double m24;
    public final double m31;
    public final double m32;
    public final double m33;
    public final double m34;
    public final double m41;
    public final double m42;
    public final double m43;
    public final double m44;


/**
 * creates a new matrix object
 * @param m11 is a component of a 4x4 matrix;
 * @param m12 is a component of a 4x4 matrix;
 * @param m13 is a component of a 4x4 matrix;
 * @param m14 is a component of a 4x4 matrix;
 * @param m21 is a component of a 4x4 matrix;
 * @param m22 is a component of a 4x4 matrix;
 * @param m23 is a component of a 4x4 matrix;
 * @param m24 is a component of a 4x4 matrix;
 * @param m31 is a component of a 4x4 matrix;
 * @param m32 is a component of a 4x4 matrix;
 * @param m33 is a component of a 4x4 matrix;
 * @param m34 is a component of a 4x4 matrix;
 * @param m41 is a component of a 4x4 matrix;
 * @param m42 is a component of a 4x4 matrix;
 * @param m43 is a component of a 4x4 matrix;
 * @param m44 is a component of a 4x4 matrix;
 */

public Mat4x4(final double m11, final double m12, final double m13, final double m14, final double m21,
              final double m22, final double m23, final double m24, final double m31, final double m32,
              final double m33, final double m34, final double m41, final double m42, final double m43,
              final double m44) {
    this.m11 = m11;
    this.m12 = m12;
    this.m13 = m13;
    this.m14 = m14;
    this.m21 = m21;
    this.m22 = m22;
    this.m23 = m23;
    this.m24 = m24;
    this.m31 = m31;
    this.m32 = m32;
    this.m33 = m33;
    this.m34 = m34;
    this.m41 = m41;
    this.m42 = m42;
    this.m43 = m43;
    this.m44 = m44;
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
     * multiplicates a matrix with a matrix
     * @param m is a 4x4 matrix.
     * @return the method returns a 4x4 matrix.
     */
    public Mat4x4 mul(final Mat4x4 m) {

        final double mn11 = m11 * m.m11 + m12 * m.m21 + m13 * m.m31 + m14 * m.m41;
        final double mn12 = m11 * m.m12 + m12 * m.m22 + m13 * m.m32 + m14 * m.m42;
        final double mn13 = m11 * m.m13 + m12 * m.m23 + m13 * m.m33 + m14 * m.m43;
        final double mn14 = m11 * m.m14 + m12 * m.m24 + m13 * m.m34 + m14 * m.m44;

        final double mn21 = m21 * m.m11 + m22 * m.m21 + m23 * m.m31 + m24 * m.m41;
        final double mn22 = m21 * m.m12 + m22 * m.m22 + m23 * m.m32 + m24 * m.m42;
        final double mn23 = m21 * m.m13 + m22 * m.m23 + m23 * m.m33 + m24 * m.m43;
        final double mn24 = m21 * m.m14 + m22 * m.m24 + m23 * m.m34 + m24 * m.m44;

        final double mn31 = m31 * m.m11 + m32 * m.m21 + m33 * m.m31 + m34 * m.m41;
        final double mn32 = m31 * m.m12 + m32 * m.m22 + m33 * m.m32 + m34 * m.m42;
        final double mn33 = m31 * m.m13 + m32 * m.m23 + m33 * m.m33 + m34 * m.m43;
        final double mn34 = m31 * m.m14 + m32 * m.m24 + m33 * m.m34 + m34 * m.m44;

        final double mn41 = m41 * m.m11 + m42 * m.m21 + m43 * m.m31 + m44 * m.m41;
        final double mn42 = m41 * m.m12 + m42 * m.m22 + m43 * m.m32 + m44 * m.m42;
        final double mn43 = m41 * m.m13 + m42 * m.m23 + m43 * m.m33 + m44 * m.m43;
        final double mn44 = m41 * m.m14 + m42 * m.m24 + m43 * m.m34 + m44 * m.m44;

        return new Mat4x4( mn11, mn12, mn13, mn14,
                           mn21, mn22, mn23, mn24,
                           mn31, mn32, mn33, mn34,
                           mn41, mn42, mn43, mn44 );
}

    /**
     * multiplicates a matrix with a point
     * @param p is a point with 3 coordinates.
     * @return the method returns a point with 3 coordinates.
     */
    public Point3 mul(final Point3 p) {

        final double x = m11*p.x + m12*p.y + m13*p.z + m14;
        final double y = m21*p.x + m22*p.y + m23*p.z + m24;
        final double z = m31*p.x + m32*p.y + m33*p.z + m34;

        return new Point3(x, y, z);
    }

    /**
     * makes a transposed matrix
     * @return the method returns a new matrix.
     */
    public Mat4x4 transposed() {

        final double a11 = this.m11;
        final double a21 = m12;
        final double a31 = m13;
        final double a41 = m14;

        final double a12 = m21;
        final double a22 = this.m22;
        final double a32 = m23;
        final double a42 = m24;

        final double a13 = m31;
        final double a23 = m32;
        final double a33 = this.m33;
        final double a43 = m34;

        final double a14 = m41;
        final double a24 = m42;
        final double a34 = m43;
        final double a44 = this.m44;

        return new Mat4x4(a11, a12, a13, a14, a21, a22, a23, a24, a31, a32, a33, a34, a41, a42, a43, a44);

    }

}