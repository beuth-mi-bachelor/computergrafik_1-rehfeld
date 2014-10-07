/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.geometry;

import de.beuth.raytracer.material.Material;
import de.beuth.raytracer.mathlibrary.Normal3;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Ray;

/**
 * class represents a box
 */
public class AxisAlignedBox extends Geometry {

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
     * @param material material
     */
    public AxisAlignedBox(final Point3 lbf, final Point3 run, final Material material) {
        super(material);
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

        int front;
        int back;

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
            if (a >= 0.0) {
                front = 0;
            } else {
                front = 3;
            }
        } else {
            t0 = ty_min;
            if (b >= 0.0) {
                front = 1;
            } else {
                front = 4;
            }
        }
        if (tz_min > t0) {
            t0 = tz_min;
            if (c >= 0.0) {
                front = 2;
            } else {
                front = 5;
            }
        }
        if (tx_max < ty_max) {
            t1 = tx_max;
            if (a >= 0.0) {
                back = 3;
            } else {
                back = 0;
            }
        } else {
            t1 = ty_max;
            if (b >= 0.0) {
                back = 4;
            } else {
                back = 1;
            }
        }
        if (tz_max < t1) {
            t1 = tz_max;
            if (c >= 0.0) {
                back = 5;
            } else {
                back = 2;
            }
        }

        if (t0 < t1 && t1 > 0) {
            if (t0 > 0) {
                tmin = t0;
                Normal3 n = this.getNormalOfSite(front);
                return new Hit(tmin, r, this, n);
            } else {
                tmin = t1;
                Normal3 n = this.getNormalOfSite(front);
                return new Hit(tmin, r, this, n);
            }
        } else {
            return null;
        }
    }

    private Normal3 getNormalOfSite(final int inBetween) {
        switch (inBetween) {
            case 0:
                return (new Normal3(1.0, 0.0, 0.0));
            case 1:
                return (new Normal3(0.0, 1.0, 0.0));
            case 2:
                return (new Normal3(0.0, 0.0, 1.0));
            case 3:
                return (new Normal3(1.0, 0.0, 0.0));
            case 4:
                return (new Normal3(0.0, 1.0, 0.0));
            case 5:
                return (new Normal3(0.0, 0.0, 1.0));
            default:
                return (null);
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
