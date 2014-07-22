/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.mathlibrary.interfaces;

import de.beuth.raytracer.mathlibrary.Mat3x3;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Vector3;

public interface IMat3x3 {
    public Mat3x3 mul(final Mat3x3 m);
    public Vector3 mul(final Vector3 v);
    public Point3 mul(final Point3 p);
    public Mat3x3 changeCol1(final Vector3 v);
    public Mat3x3 changeCol2(final Vector3 v);
    public Mat3x3 changeCol3(final Vector3 v);

    public String toString();
    public int hashCode();
    public boolean equals(final Object obj);

}


