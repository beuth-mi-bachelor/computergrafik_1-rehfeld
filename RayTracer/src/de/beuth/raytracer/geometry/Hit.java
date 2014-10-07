/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.geometry;

import de.beuth.raytracer.mathlibrary.Ray;

/**
 * class displays a hit between two objects
 */
public class Hit {

    /**
     * where do they hit
     */
    public final double t;

    /**
     * the ray
     */
    public final Ray r;

    /**
     * the geometry hit with
     */
    public final Geometry geo;

    /**
     * displays a  hit between two objects
     * @param t where do they hit
     * @param r the ray hit with
     * @param geo the geometry hit with
     */
    public Hit(final double t, final Ray r, final Geometry geo) {
        this.t = t;
        this.r = r;
        this.geo = geo;
    }

    @Override
    public String toString() {
        return "Hit{" +
                "t=" + t +
                ", r=" + r +
                ", geo=" + geo +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hit hit = (Hit) o;

        if (Double.compare(hit.t, t) != 0) return false;
        if (geo != null ? !geo.equals(hit.geo) : hit.geo != null) return false;
        if (r != null ? !r.equals(hit.r) : hit.r != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(t);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (r != null ? r.hashCode() : 0);
        result = 31 * result + (geo != null ? geo.hashCode() : 0);
        return result;
    }
}
