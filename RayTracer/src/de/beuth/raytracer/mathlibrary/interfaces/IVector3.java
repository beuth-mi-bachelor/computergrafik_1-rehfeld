/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.mathlibrary.interfaces;

import de.beuth.raytracer.mathlibrary.Normal3;
import de.beuth.raytracer.mathlibrary.Vector3;

public interface IVector3 {

    public Vector3 add(final Vector3 v);
    public Vector3 add(final Normal3 n);
    public Vector3 sub(final Normal3 n);
    public Vector3 mul(final double c);
    public double dot(final Vector3 v);
    public double dot(final Normal3 n);
    public Vector3 normalized();
    public Normal3 asNormal();
    public Vector3 reflectedOn(final Normal3 n);
    public Vector3 x(final Vector3 v);

    public String toString();
    public int hashCode();
    public boolean equals(final Object obj);

}
