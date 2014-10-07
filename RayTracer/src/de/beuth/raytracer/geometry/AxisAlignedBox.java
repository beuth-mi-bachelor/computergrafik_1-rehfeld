/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.geometry;

import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.geometry.interfaces.IAxisAlignedBox;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Ray;
import de.beuth.raytracer.world.World;

/**
 * class represents a box
 */
public class AxisAlignedBox extends Geometry implements IAxisAlignedBox {

    /**
     * left bottom point
     */
    public final Point3 lbf;

    /**
     * right upper point
     */
    public final Point3 run;

    /**
     * defines a axis aligned box
     * @param lbf point
     * @param run point
     * @param color color
     */
    public AxisAlignedBox(final Point3 lbf, final Point3 run, final Color color) {
        super(color);
        this.lbf = lbf;
        this.run = run;
    }

    /**
     * looks for a hit between a ray and a axis aligned box
     * @param r the ray to test the hit with
     * @return a Hit object or null if they dont hit
     */
    @Override
    public Hit hit(final Ray r) {
        final double ox = r.o.x;
        final double oy = r.o.y;
        final double oz = r.o.z;
        final double dx = r.d.x;
        final double dy = r.d.y;
        final double dz = r.d.z;
        final double tx_min;
        final double ty_min;
        final double tz_min;
        final double tx_max;
        final double ty_max;
        final double tz_max;
        final double a = 1.0 / dx;
        final double b = 1.0 / dy;
        final double c = 1.0 / dz;

        double t0;
        double t1;

        double tmin;

        // from book: raytracing from ground up
        if ((a) >= 0.0) {
            tx_min = (lbf.x - ox) * (a);
            tx_max = (run.x - ox) * (a);
        } else {
            tx_min = (run.x - ox) * (a);
            tx_max = (lbf.x - ox) * (a);
        }
        if ((b) >= 0.0) {
            ty_min = (lbf.y - oy) * (b);
            ty_max = (run.y - oy) * (b);
        } else {
            ty_min = (run.y - oy) * (b);
            ty_max = (lbf.y - oy) * (b);
        }
        if ((c) >= 0.0) {
            tz_min = (lbf.z - oz) * (c);
            tz_max = (run.z - oz) * (c);
        } else {
            tz_min = (run.z - oz) * (c);
            tz_max = (lbf.z - oz) * (c);
        }

        if (tx_min > ty_min) {
            t0 = tx_min;
        } else {
            t0 = ty_min;
        }
        if (tz_min > t0) {
            t0 = tz_min;
        }
        if (tx_max < ty_max) {
            t1 = tx_max;
        } else {
            t1 = ty_max;
        }
        if (tz_max < t1) {
            t1 = tz_max;
        }

        if (t0 < t1 && t1 > 0) {
            if (t0 > 0) {
                tmin = t0;
                return new Hit(tmin, r, this);
            } else {
                tmin = t1;
                return new Hit(tmin, r, this);
            }
        } else {
            return null;
        }
    }

        @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AxisAlignedBox that = (AxisAlignedBox) o;

        if (lbf != null ? !lbf.equals(that.lbf) : that.lbf != null) return false;
        if (run != null ? !run.equals(that.run) : that.run != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lbf != null ? lbf.hashCode() : 0;
        result = 31 * result + (run != null ? run.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AxisAlignedBox{" +
                "lbf=" + lbf +
                ", run=" + run +
                '}';
    }
}
