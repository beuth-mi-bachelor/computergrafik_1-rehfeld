/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.mathlibrary.interfaces;

import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Vector3;

public interface IPoint3 {

    public Vector3 sub(final Point3 p);
    public Point3 sub(final Vector3 v);
    public Point3 add(final Vector3 v);

    public String toString();
    public int hashCode();
    public boolean equals(final Object obj);

}
