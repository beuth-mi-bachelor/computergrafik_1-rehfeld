/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.geometry.interfaces;

import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.mathlibrary.Ray;
import de.beuth.raytracer.geometry.Hit;

public abstract interface IGeometry {
    public Hit hit(Ray r);
}
