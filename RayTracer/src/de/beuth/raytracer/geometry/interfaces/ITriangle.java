/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.geometry.interfaces;

import de.beuth.raytracer.geometry.Hit;
import de.beuth.raytracer.mathlibrary.Ray;

public interface ITriangle extends IGeometry {
    public Hit hit(Ray r);
}