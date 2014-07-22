/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.mathlibrary.interfaces;

import de.beuth.raytracer.mathlibrary.Normal3;
import de.beuth.raytracer.mathlibrary.Vector3;

public interface INormal3 {

    public Normal3 mul(final double n);
    public Normal3 add(final Normal3 n);
    public double dot(final Vector3 v);

    public String toString();
    public int hashCode();
    public boolean equals(final Object obj);

}
