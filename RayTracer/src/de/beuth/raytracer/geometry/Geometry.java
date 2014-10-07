/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.geometry;

import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.geometry.interfaces.IGeometry;
import de.beuth.raytracer.mathlibrary.Ray;

/**
 * the abstract geometry object
 */
public abstract class Geometry implements IGeometry {

    /**
     * defines the color of the geometry
     */
    public final Color color;
    /**
     * creates a new instance of geometry. If no color is there, color will be black
     */
    /*public Geometry() {
        this.color = new Color(0, 0, 0);
    }
*/
    /**
     * creates a new instance of geometry
     * @param color defines a base color for the geometry
     */
    public Geometry(final Color color) {
        this.color = color;
    }

    public abstract Hit hit(final Ray r);

    @Override
    public String toString() {
        return "Geometry{" +
                "color=" + color +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Geometry geometry = (Geometry) o;

        if (color != null ? !color.equals(geometry.color) : geometry.color != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return color != null ? color.hashCode() : 0;
    }
}
