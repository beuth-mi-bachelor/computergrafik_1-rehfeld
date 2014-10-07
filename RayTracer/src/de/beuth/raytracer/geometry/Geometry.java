/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.geometry;

import de.beuth.raytracer.material.Material;
import de.beuth.raytracer.mathlibrary.Ray;

/**
 * the abstract geometry object
 */
public abstract class Geometry {

    /**
     * defines the color of the geometry
     */
    public final Material material;

    /**
     * creates an instance of geometry
     * @param material defines a base material
     */
    public Geometry(final Material material) {
        this.material = material;
    }

    public abstract Hit hit(final Ray r);

    @Override
    public String toString() {
        return "Geometry{" +
                "material=" + material +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Geometry geometry = (Geometry) o;

        if (material != null ? !material.equals(geometry.material) : geometry.material != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return material != null ? material.hashCode() : 0;
    }
}
