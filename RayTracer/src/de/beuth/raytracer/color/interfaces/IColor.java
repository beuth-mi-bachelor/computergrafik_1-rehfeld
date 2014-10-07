/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.color.interfaces;

import de.beuth.raytracer.color.Color;

public interface IColor {

    public Color add(final Color c);
    public Color sub(final Color c);
    public Color mul(final Color c);
    public Color mul(final double v);

    public String toString();
    public int hashCode();
    public boolean equals(final Object obj);


}
