/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.mathlibrary.interfaces;

import de.beuth.raytracer.mathlibrary.Point3;

/**
 * Created by Angi on 30.04.2014.
 */
public interface IRay {

    public Point3 at(final double t);
    public double tOf(final Point3 p);

    public String toString();
    public int hashCode();
    public boolean equals(final Object obj);
}
